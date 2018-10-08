package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.commands.BounceCommand;
import com.commands.MacroCommand;
import com.commands.MoveCommand;
import com.commands.ProjectileCommand;
import com.commands.SpriteBlowCommand;
import com.commands.TimerCommand;
import com.component.CircularSprite;
import com.component.Clock;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.helper.SpriteCollision;
import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.infrastruture.Observer;
import com.timer.BreakoutTimer;
import com.ui.GUI;


public class GameDriver implements Observer, KeyListener, ActionListener, MouseListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1504747393198580722L;

	protected static Logger log = Logger.getLogger(GameDriver.class);
	private ArrayList<SpriteElement> sprites;
	private Map<String, List<ActionLink>> eventMap;
	private GUI gui;
	private BreakoutTimer timer;
	private SpriteCollision collision;
	private HashSet<SpriteElement> gameWinSet;
	private HashSet<SpriteElement> gameLoseSet;
	private MouseEvent e;

	Boolean Projectileflag ;
    private boolean isGamePaused ;
    
	private Deque<Command> commandQueue;
    private TimerCommand timerCommand;
    private Map<SpriteElement, SpriteElement> bulletElementMap;
    private Map<SpriteElement,Integer> shooterSpriteBulletCountMap;


	public GameDriver(GUI gui, BreakoutTimer timer, Clock clock, Map<SpriteElement, SpriteElement> bulletElementMap,Map<SpriteElement,Integer>shooterSpriteBulletCountMap){
		 log.info("Initializing GameDriver");

		this.sprites = new ArrayList<SpriteElement>();
		this.eventMap = new HashMap<>();
		this.gui = gui;
		this.timer = timer;
		this.bulletElementMap=bulletElementMap;
		this.shooterSpriteBulletCountMap=shooterSpriteBulletCountMap;
		this.collision = new SpriteCollision();
		isGamePaused = false;
		timerCommand = new TimerCommand(clock);
		commandQueue = new ArrayDeque<Command>();
		this.gameWinSet = new HashSet<>();
		this.gameLoseSet = new HashSet<>();
	}

	public void addGameEndSprite(SpriteElement element) {
//		gameEndSet.add(element);
//		addGameWinSprite(element);
//		addGameLoseSprite(element);
	}

	public void addGameWinSprite(SpriteElement element) {

		gameWinSet.add(element);
	}

	public void addGameLoseSprite(SpriteElement element) {

		gameLoseSet.add(element);
	}

	public boolean containsGameLoseSprite(SpriteElement element) {
		return gameLoseSet.contains(element);
	}

	public boolean containsGameWinSprite(SpriteElement element) {
		return gameWinSet.contains(element);
	}

	public void removeGameEndSprite(SpriteElement element) {
		if (gameWinSet.contains(element)) {
			gameWinSet.remove(element);
		}
		if (gameLoseSet.contains(element)) {
			gameLoseSet.remove(element);
		}
	}

	public void addSpriteElements(SpriteElement sprite) {
		sprites.add(sprite);
	}

	public void removeSprite(SpriteElement sprite) {
		sprites.remove(sprite);
	}

	public List<SpriteElement> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<SpriteElement> sprites) {
		this.sprites = sprites;
	}

	public Map<String, List<ActionLink>> getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map<String, List<ActionLink>> eventMap) {
		this.eventMap = eventMap;
	}

	public void save() {
		FileOutputStream fileOut;
		try {
			String fileName = gui.showSaveDialog();
			fileOut = new FileOutputStream(fileName);
			ObjectOutputStream op = new ObjectOutputStream(fileOut);

			op.writeObject(eventMap);
			op.writeObject(gameWinSet);
			op.writeObject(gameLoseSet);
			op.writeObject(sprites);
			op.writeObject(bulletElementMap);
			op.writeObject(shooterSpriteBulletCountMap);
			ImageIO.write(gui.getBoardPanel().getImage(), "png", op);


			op.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}

	}

	public void load() {

		FileInputStream fileIn;
		try {
			String fileName = gui.showOpenDialog();
			fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			sprites.clear();
			eventMap.clear();
			gameWinSet.clear();
			gameLoseSet.clear();
			bulletElementMap.clear();
			shooterSpriteBulletCountMap.clear();
			eventMap.putAll((Map<String, List<ActionLink>>) in.readObject());
			gameWinSet.addAll((HashSet<SpriteElement>)in.readObject());
			gameLoseSet.addAll((HashSet<SpriteElement>)in.readObject());
			setSprites((ArrayList<SpriteElement>)in.readObject());
			bulletElementMap.putAll((Map<SpriteElement,SpriteElement>) in.readObject());
			shooterSpriteBulletCountMap.putAll((Map<SpriteElement,Integer>) in.readObject());
			gui.getBoardPanel().setElements(sprites);
			gui.getBoardPanel().setImage((ImageIO.read(in)));

			gui.getBoardPanel().revalidate();
			this.gui.paintView();

			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
	}

	public void InitPlay() {
		gui.paintView();
		timerCommand.execute();
		timer.registerObserver(this);
		commandQueue.addLast(timerCommand);
	}

	@Override
	public void update() {
		timerCommand.execute();
		commandQueue.addLast(timerCommand);
		checkCollision();
//		log.error( eventMap);
		eventHandler("OnTick");
		checkIfGameEnd();
		gui.paintView();
	}

	public void checkIfGameEnd() {

		if (gameWinSet.isEmpty() || gameLoseSet.isEmpty()) {
			timer.removeObserver(this);
			gui.paintView();
			String gameMsg = gameWinSet.isEmpty() ? "You Win :)" : "You Lose! :( ";
			int option = JOptionPane.showConfirmDialog(null, gameMsg, "Game Status", JOptionPane.DEFAULT_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}

	}

	public void checkCollision() {
		MacroCommand macroCommand = new MacroCommand();
		Direction d;

		Set<SpriteElement> visited = new HashSet();
		if (eventMap.containsKey("OnCollision")) {
			List<ActionLink> eventObservers = eventMap.get("OnCollision");
			for (ActionLink actionObserver : eventObservers) {
				if (!visited.contains(actionObserver.getSprite()) && actionObserver.getSpriteElement2IdOrCategory().isEmpty()) {
					visited.add(actionObserver.getSprite());
					d = collision.checkCollisionOfSpriteWithWall(actionObserver.getSprite());
					actionForCollision(actionObserver, d, macroCommand);
				}
				for (SpriteElement element : sprites) {
					if (element.isVisible()) {
						SpriteElement currentSprite = actionObserver.getSprite();
						if (!(element.getCategory().equals(currentSprite.getCategory()))) {
//								(actionObserver.getSpriteElement2IdOrCategory().isEmpty() || 

							if (element.getCategory().equals(actionObserver.getSpriteElement2IdOrCategory())
									|| element.getSpriteId().equals(actionObserver.getSpriteElement2IdOrCategory())
									|| actionObserver.getSpriteElement2IdOrCategory().isEmpty()) {
								d = collision.checkCollisionOfSprites(currentSprite, element);
								actionForCollision(actionObserver, d, macroCommand);
								if (d != Direction.NONE)
									break;
							}
						}
					}
				}
			}
			macroCommand.execute();
			commandQueue.addLast(macroCommand);
		}
	}

	public void actionForCollision(ActionLink action, Direction d, MacroCommand macroCommand) {
		if (d != Direction.NONE) {
			switch (action.getAction()) {
			case "blow":
				macroCommand.addCommand(new SpriteBlowCommand(action.getSprite()));
				removeGameEndSprite(action.getSprite());
				break;
			case "bounce":
				macroCommand.addCommand(new BounceCommand(action.getSprite(), d));
				break;
			case "move":
				macroCommand.addCommand(new MoveCommand(action.getSprite()));
				break;
			default:
				break;
			}
		}
	}

	public void eventHandler(String event) {

		MacroCommand macroCommand = new MacroCommand();
		if (eventMap.containsKey(event)) {
			List<ActionLink> eventObservers = eventMap.get(event);

			List<ActionLink> tempEventObservers = new ArrayList<>(eventObservers);
			for(ActionLink actionObserver: tempEventObservers) {
				switch(actionObserver.getAction()) {
					case "blow": 
						macroCommand.addCommand(new SpriteBlowCommand(actionObserver.getSprite()));
						removeGameEndSprite(actionObserver.getSprite());
						break;
					case "move": 
						SpriteElement currentSpriteElement= actionObserver.getSprite();
						int counter=currentSpriteElement.getCounter();
						if(counter==0) {
							macroCommand.addCommand(new MoveCommand(actionObserver.getSprite()));
						}
						counter=(counter+1)%currentSpriteElement.getCounterInterval();
						currentSpriteElement.setCounter(counter);
						break;
					case "shoot":
						try {
							currentSpriteElement = actionObserver.getSprite();
							if(bulletElementMap.containsKey(currentSpriteElement)) {
								counter=currentSpriteElement.getCounter();
								if(counter==0) {
										shoot(currentSpriteElement);
								}
								counter=(counter+1)%currentSpriteElement.getCounterInterval();
								currentSpriteElement.setCounter(counter);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

						break;
					default: break;
				}
			}

			macroCommand.execute();
			commandQueue.addLast(macroCommand);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		MacroCommand macroCommand = new MacroCommand();

		if (eventMap.containsKey("keyPressed")) {
			List<ActionLink> eventObservers = eventMap.get("keyPressed");
			for (ActionLink actionObserver : eventObservers) {
				switch (actionObserver.getAction()) {
				case "blow":
					macroCommand.addCommand(new SpriteBlowCommand(actionObserver.getSprite()));
					removeGameEndSprite(actionObserver.getSprite());
					break;
				case "shoot":
					// macroCommand.addCommand(shoot);
					break;
				case "move":
					try {
						setSpriteDirection(e, actionObserver.getSprite());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					macroCommand.addCommand(new MoveCommand(actionObserver.getSprite()));
					break;
				default:
					break;
				}
			}

			macroCommand.execute();
			commandQueue.addLast(macroCommand);
		}
	}

	public void setSpriteDirection(KeyEvent e, SpriteElement element) throws IOException {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (element.getElementX() - element.getXVel() > 0) {
				element.setElementX(element.getElementX() - element.getXVel());
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (element.getElementX() + element.getWidth() + element.getXVel() < Constants.BOARD_PANEL_WIDTH) {
				element.setElementX(element.getElementX() + element.getXVel());
			}
			break;
		case KeyEvent.VK_UP:
			if (element.getElementY() - element.getYVel() > 0) {
				element.setElementY(element.getElementY() - element.getYVel());
			}
			break;
		case KeyEvent.VK_DOWN:
			if (element.getElementY() + element.getHeight() + element.getYVel() < Constants.BOARD_PANEL_HEIGHT) {
				element.setElementY(element.getElementY() + element.getYVel());
			}
			break;
		case KeyEvent.VK_SPACE:

			//CircularSprite bullet = (CircularSprite) element.shoot(new CircularSprite("", element.getElementX() + 
			//			element.getWidth()/2, element.getElementY(), 10, 10, 0, -1, "bullet1", "bullet",
			//			Color.BLACK,Constants.GAME_NOT_APPLICABLE_COMPONENT,5));
			//addSpriteElements(bullet);
			
//			log.error("Before" + sprites);
//			log.error("Before" + eventMap);
//			log.error("Before" + bulletElementMap);
			if(bulletElementMap.containsKey(element)) {
				shoot(element);
			}
			break;
			
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	private void shoot(SpriteElement element) throws IOException {
		SpriteElement bullet=element.shoot(bulletElementMap.get(element));
		log.error(sprites);
		log.info(shooterSpriteBulletCountMap);
		int bulletCount=shooterSpriteBulletCountMap.get(element);
		if(bulletCount>0) { 
			if(bullet instanceof CircularSprite) {
				addSpriteElements((CircularSprite) bullet);
			}
			else {
				addSpriteElements((RectangularSprite) bullet);
			}		
//			log.error(sprites);
			eventMap.putIfAbsent("OnTick", new ArrayList<ActionLink>());
			eventMap.get("OnTick").add(new ActionLink(bullet, "move"));
			eventMap.putIfAbsent("OnCollision", new ArrayList<ActionLink>());
			eventMap.get("OnCollision").add(new ActionLink(bullet, "blow"));
			gui.getBoardPanel().setElements(sprites);
//			log.error( eventMap);
			gui.getBoardPanel().revalidate();
			this.gui.paintView();			
			shooterSpriteBulletCountMap.put(element, bulletCount-1);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText = e.getActionCommand();
		if (commandText.equals("undo")) {
			if (!isGamePaused) {
				pause();
				undoAction();
				unPause();
			} else {
				undoAction();
			}
			gui.changeFocus();
			gui.paintView();

		} else if (commandText.equals("start")) {
			if (isGamePaused) {
				unPause();
				gui.changeFocus();
				gui.paintView();

			} else {
				InitPlay();
			}
		} else if (commandText.equals("pause")) {
			pause();
			gui.changeFocus();
			gui.paintView();
		} else if (commandText.equals("replay")) {
			replay();
			gui.changeFocus();
		} else if (commandText.equals("layout")) {
			pause();
			gui.modifyLayout();
			gui.changeFocus();
			gui.draw(null);
			unPause();
		}
	}

	public boolean isGamePaused() {
		return isGamePaused;
	}

	public Deque<Command> getCommandQueue() {
		return commandQueue;
	}

	public void replay() {
		pause();
		gameReset();
		replayAction();
		gui.changeFocus();
	}

	public void gameReset() {
		for (SpriteElement element : sprites) {
			element.reset();
		}
	}

	private void replayAction() {
		Iterator<Command> itr = commandQueue.iterator();
		new Thread() {
			public void run() {
				while (itr.hasNext()) {
					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							Command val = (Command) itr.next();

							@Override
							public void run() {
								// TODO Auto-generated method stub
								val.execute();
								gui.paintView();
								try {
									currentThread();
									Thread.sleep(5);
								} catch (InterruptedException e) {
									log.error(e.getMessage());
									Thread.currentThread().interrupt();
									throw new RuntimeException(e);
								}
							}
						});
					} catch (InvocationTargetException | InterruptedException e) {
						log.error(e.getMessage());
						Thread.currentThread().interrupt();
						throw new RuntimeException(e);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.error(e.getMessage());
					Thread.currentThread().interrupt();
					throw new RuntimeException(e);
				}
			}
		}.start();
	}

	public void undoAction() {

		int count = 0;
		while (count != Constants.TIMER_COUNT) {
			Command val = commandQueue.pollLast();
			if (val == null)
				break;
			if (val instanceof TimerCommand) {
				count++;
			}
			val.undo();
		}

	}

	public void pause() {
//		if(isGamePaused)
//			unPause();
//		else{
		isGamePaused = true;

		if (!timer.isObserverListEmpty()) {
			timer.removeObserver(this);
		}
//		}
	}

	public void unPause() {
		isGamePaused = false;
		timer.registerObserver(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		MacroCommand macroCommand = new MacroCommand();

		if (eventMap.containsKey("OnClick")) {
			List<ActionLink> eventObservers = eventMap.get("OnClick");
			for (ActionLink actionObserver : eventObservers) {
				if (actionObserver.getAction() == "shoot") {
					macroCommand.addCommand(new ProjectileCommand(actionObserver.getSprite(), e.getX(), e.getY()));
				}
			}
			macroCommand.execute();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Map<SpriteElement, SpriteElement> getBulletElementMap() {
		return bulletElementMap;
	}

	public void setBulletElementMap(Map<SpriteElement, SpriteElement> bulletElementMap) {
		this.bulletElementMap = bulletElementMap;
	}
	
	public void setGamePaused(boolean isGamePaused) {
		this.isGamePaused = isGamePaused;
	}

	public Map<SpriteElement, Integer> getShooterSpriteBulletCountMap() {
		return shooterSpriteBulletCountMap;
	}

	public void setShooterSpriteBulletCountMap(Map<SpriteElement, Integer> shooterSpriteBulletCountMap) {
		this.shooterSpriteBulletCountMap = shooterSpriteBulletCountMap;
	}

	
}
