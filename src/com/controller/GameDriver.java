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
import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.commands.BounceCommand;
import com.commands.MacroCommand;
import com.commands.MoveCommand;
import com.commands.ProjectileCommand;
import com.commands.SpriteBlowCommand;
import com.commands.TimerCommand;
import com.component.Clock;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.helper.SpriteCollision;
import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.infrastruture.Observer;
import com.timer.BreakoutTimer;
import com.ui.GUI;

import jdk.incubator.http.internal.common.Log;

public class GameDriver implements Observer, KeyListener, ActionListener, MouseListener{
	protected static Logger log = Logger.getLogger(GameDriver.class);
	private ArrayList<SpriteElement> sprites  ;
	private Map<String, List<ActionLink>> eventMap;
	private GUI gui;
	private BreakoutTimer timer;
	private SpriteCollision collision;

	private MouseEvent e;
	Boolean Projectileflag ;

    private boolean isGamePaused ;
    private Deque<Command> commandQueue;
    private TimerCommand timerCommand;


	public GameDriver(GUI gui, BreakoutTimer timer, Clock clock){
		this.sprites = new ArrayList<SpriteElement>();
		this.eventMap = new HashMap<>();
		this.gui = gui;
		this.timer = timer;
		this.collision = new SpriteCollision();
		isGamePaused = false;
		timerCommand = new TimerCommand(clock);	
		commandQueue = new ArrayDeque<Command>();
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
			op.writeObject(sprites);

			//gui.getBoardPanel().setElements(sprites);
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
			
			eventMap.putAll((Map<String, List<ActionLink>>) in.readObject());
			setSprites((ArrayList<SpriteElement>)in.readObject());
			gui.getBoardPanel().setElements(sprites);
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
//		gui.draw(null);
		timerCommand.execute();
		timer.registerObserver(this);
	}

	@Override
	public void update() {
		timerCommand.execute();

		checkCollision();
		eventHandler("OnTick");
		eventHandler("OnClick");
		gui.paintView();
	}

	public void checkCollision() {
		MacroCommand macroCommand = new MacroCommand();
		Direction d;
		if (eventMap.containsKey("OnCollision")) {
			List<ActionLink> eventObservers = eventMap.get("OnCollision");
			for(ActionLink actionObserver: eventObservers) {
				
				d = collision.checkCollisionOfSprites(actionObserver.getSprite());
				actionForCollision(actionObserver, d, macroCommand);
				
				for(SpriteElement element: sprites) {
					if (element!=actionObserver.getSprite()) {
						d = collision.checkCollisionOfSprites(actionObserver.getSprite(),element);
						actionForCollision(actionObserver, d, macroCommand);
					}
				}
			}
			macroCommand.execute();
		}
	}
	
	public void actionForCollision(ActionLink action, Direction d, MacroCommand macroCommand) {
		if(d != Direction.NONE) {
			switch(action.getAction()) {
			case "blow": 
				macroCommand.addCommand(new SpriteBlowCommand(action.getSprite()));
				break;
			case "bounce":
				macroCommand.addCommand(new BounceCommand(action.getSprite(), d));
				break;
			case "shoot": 
				//macroCommand.addCommand(shoot);
				break;
			case "move": 
				macroCommand.addCommand(new MoveCommand(action.getSprite()));
				break;
			default: break;
			}
		}
	}

	public void eventHandler(String event) {
		
		MacroCommand macroCommand = new MacroCommand();

		if (eventMap.containsKey(event)) {
			List<ActionLink> eventObservers = eventMap.get(event);
			for(ActionLink actionObserver: eventObservers) {
				switch(actionObserver.getAction()) {
				case "blow": 
					macroCommand.addCommand(new SpriteBlowCommand(actionObserver.getSprite()));
					break;
				case "shoot":
					if(e != null) {
						if(actionObserver.getSprite().getElementY() <= e.getY()) {
							Projectileflag = false;
						}
						macroCommand.addCommand(new ProjectileCommand(actionObserver.getSprite(), e.getY(), Projectileflag));
					}
					break;
				case "move": 
					macroCommand.addCommand(new MoveCommand(actionObserver.getSprite()));
					break;
				default: break;
				}
			}

			macroCommand.execute();
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
			for(ActionLink actionObserver: eventObservers) {
				switch(actionObserver.getAction()) {
				case "blow": 
					macroCommand.addCommand(new SpriteBlowCommand(actionObserver.getSprite()));
					break;
				case "shoot": 
					//macroCommand.addCommand(shoot);
					break;
				case "move": 
					setSpriteDirection(e, actionObserver.getSprite());
					macroCommand.addCommand(new MoveCommand(actionObserver.getSprite()));
					break;
				default: break;
				}
			}

			macroCommand.execute();
		}
	}
	
	public void setSpriteDirection(KeyEvent e, SpriteElement element) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			element.setYVel(0);
			element.setXVel(-1*Constants.X_Velocity);
			break;
		case KeyEvent.VK_RIGHT:
			element.setYVel(0);
			element.setXVel(Constants.X_Velocity);
			break;
		case KeyEvent.VK_UP:
			element.setXVel(0);
			element.setYVel(-1*Constants.Y_Velocity);
			break;
		case KeyEvent.VK_DOWN:
			element.setXVel(0);
			element.setYVel(Constants.Y_Velocity);
			break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText= e.getActionCommand();
		if(commandText.equals("undo")) {
			if(!isGamePaused) {
				pause();
				undoAction();
				unPause();
			} else {
				undoAction();
			}
			gui.changeFocus();
			gui.draw(null);

		}/*else if(commandText.equals("replay")) {
			replay();
		}else*/ if(commandText.equals("start")) {
			if(isGamePaused) {
				unPause();
				gui.changeFocus();
				gui.paintView();
//				gui.draw(null);
			}else {
//				gui.dispose();
				InitPlay();
//				gui.revalidate();
//				gui.paintView();
//				GameMaker.start(true);
			}
		}else if(commandText.equals("pause")) {
			pause();
			gui.changeFocus();
			gui.draw(null);
		}
//		}else if(commandText.equals("save")) {
//			save();
//			gui.changeFocus();
//		}else if(commandText.equals("load")) {
//			load();
//			gui.changeFocus();
//			gui.draw(null);;
//		}
		else if(commandText.equals("layout")) {
			pause();
			gui.modifyLayout();
			gui.changeFocus();
			gui.draw(null);
			unPause();
		}
	}
	
	private void undoAction() {

		int count = 0;
		while(count != Constants.TIMER_COUNT) {
			Command val=commandQueue.pollLast();
			if(val == null)
				break;
			if(val instanceof TimerCommand)
			{
				count++;
			}
//			if(val instanceof BrickEnactCommand)
//			{
//				noOfBricks++;
//			}
			val.undo();
		}
		
	}
	
	public void pause() {
		if(isGamePaused)
			unPause();
		else{
			isGamePaused = true;

			if(!timer.isObserverListEmpty()) {
				timer.removeObserver(this);
			}
		}
	}
	
	public void unPause() {
		isGamePaused = false;
		timer.registerObserver(this);
	}

	
	public void calculateProjectile(SpriteElement sprite, int heightX, int heightY) {
		
		double startY = (double) sprite.getElementY();
		double startX = (double) sprite.getElementX();
		
		double tan = (startY- heightY)/(startX-heightX);
		double Height = startY-heightY;
		double Range = heightX - startX;
		double gravity = Constants.PROJECTILE_GRAVITY;
		double deltaY = Math.sqrt((Height) * 2* gravity);
		double deltaX = deltaY/tan;

		sprite.setXVel((int) (-1* deltaX)); 
		sprite.setYVel((int) deltaY);
		
	}
	
//	public void ProjectileVelocitySetter(SpriteElement sprite, int heightX, int heightY) {
//		
//	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		// TODO Auto-generated method stub
		
		if (eventMap.containsKey("OnClick")) {
			List<ActionLink> eventObservers = eventMap.get("OnClick");
			for(ActionLink actionObserver: eventObservers) {
				if (actionObserver.getAction() == "shoot") {
					calculateProjectile(actionObserver.getSprite() ,e.getX(),e.getY());
				}
			}

		}
		this.Projectileflag = true;
		this.e = e;
		
		
//		ProjectileCommmand projectileCommand = 
		/************************change this******************************
		ProjectileCommand command = new ProjectileCommand(null, noOfBricks);
		/************************change this******************************/
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

}
