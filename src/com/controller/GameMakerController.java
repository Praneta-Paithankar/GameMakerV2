package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SpinnerDateModel;

import org.apache.log4j.Logger;

import com.component.CircularSprite;
import com.component.Clock;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.helper.GameObject;
import com.infrastruture.Constants;
import com.infrastruture.Element;
import com.timer.BreakoutTimer;
import com.ui.CreateSpriteRequest;
import com.ui.GUI;

public class GameMakerController implements  ActionListener, MouseListener,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5964800676330256965L;
	protected static Logger log = Logger.getLogger(GameMakerController.class);
    private GUI gui;
	private CreateSpriteRequest sprite;
	private GameObject gameObject;
	private SpriteElement newSprite;
	private Map<String, List<ActionLink>> eventMap;
	private Map<SpriteElement,SpriteElement> bulletElementMap;
	private GameDriver gameDriver;
	private BreakoutTimer timer;
	private Clock clock;
	private CreateSpriteRequest bulletSprite;
	private SpriteElement newBulletSprite;
	private Map<SpriteElement, Integer> shooterSpriteBulletCountMap;
	
	
	public GameMakerController(GUI gui, Clock clock) {
		log.info("Initializing game-maker-controller");
		this.gui = gui;
		this.gameObject = new GameObject();
		this.timer = new BreakoutTimer();
		this.bulletElementMap=new HashMap<>();
		this.clock = clock; 
		this.shooterSpriteBulletCountMap=new HashMap<>();
		this.gameDriver = new GameDriver(this.gui, timer, clock,bulletElementMap,shooterSpriteBulletCountMap);
		
		gui.addDriver(gameDriver);
	}
	
	public void addElementToGame(ArrayList<Element> elementList) {
		
	}
	
	/*This method is called from action performed method .. when user clicks Save on make panel.*/
	public void done() {
		try {
			this.sprite = gui.getMakePanel().getNewSprite();
			this.newSprite = gameObject.spriteDecoder(sprite);
			
			this.bulletSprite=gui.getMakePanel().getShootSprite();
			if(this.bulletSprite !=null) {
				this.newBulletSprite = gameObject.spriteDecoder(bulletSprite);
				this.newBulletSprite.setElementX(newSprite.getElementX()+newSprite.getWidth()/2);
				this.newBulletSprite.setElementY(newSprite.getElementY());
				bulletElementMap.put(newSprite,newBulletSprite);
				shooterSpriteBulletCountMap.put(newSprite, gui.getMakePanel().getNumberOfBullets());
				log.info("shooterSpriteBulletCountMap" + shooterSpriteBulletCountMap);
			}
			gui.getMakePanel().setShootSprite(null);
			this.gameDriver.addSpriteElements(newSprite);
			gui.getBoardPanel().setSpriteElement(newSprite);
			this.gui.addSpriteToPanel(newSprite);
			eventMap = gameDriver.getEventMap();
			switch (sprite.getGameEndDependency()) {
			case Constants.GAME_WIN_COMPONENT:
				gameDriver.addGameWinSprite(newSprite);
				break;
			case Constants.GAME_LOSE_COMPONENT:
				gameDriver.addGameLoseSprite(newSprite);
				break;
			default:
				break;
			}
			
			for (Map.Entry<String,String> entry:sprite.getCollisionMap().entrySet()) {			 
				eventMap.putIfAbsent("OnCollision", new ArrayList<>());
				eventMap.get("OnCollision").add(new ActionLink(newSprite,entry.getKey(),entry.getValue()));
//				eventMap.get("OnCollision").add(new ActionLink(newSprite, entry.getValue(), entry.getKey()));
			}
			for (Map.Entry<String,String> entry:sprite.getEventAction().entrySet()) {			 
				eventMap.putIfAbsent(entry.getKey(), new ArrayList<>());
				eventMap.get(entry.getKey()).add(new ActionLink(newSprite, entry.getValue()));
			}
				/*if (eventMap.containsKey(entry.getKey())) {	
				// System.out.println("here");
					eventMap.get(entry.getKey()).add(new ActionLink(newSprite, entry.getValue()));
				}
				else {
					List<ActionLink> listAction = new ArrayList<>();
					listAction.add(new ActionLink(newSprite, entry.getValue()));
					
//					

					eventMap.put(entry.getKey(), listAction);
				}*/
			
			this.gui.draw(null);
			
		} catch (IOException e) {
			 log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void play() {
		this.gameDriver.InitPlay();
	}
	
	public void make() {
		
		gui.getMakePanel().buttonVisiblity();
		
	}
	
	public void setBackground()
	{
		String backgroundImagePath;
		backgroundImagePath = gui.getMakePanel().getBackgroundImagePath();
		gui.getBoardPanel().setImage(backgroundImagePath);
		
	}
	
	public void save() {
		gameDriver.save();
	}
	
	public void load() {
		gameDriver.load();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText= e.getActionCommand();
		if(commandText.equals("play")) {
			play();
		}else if(commandText.equals("make")) {
			make();
		}else if(commandText.equals("setBackground")) {
			setBackground();
		}else if(commandText.equals("save")) {
			save();
		}else if(commandText.equals("load")) {
			load();
		}else if(commandText.equals("Circle")) {
			gui.getMakePanel().createSpriteElement(commandText);
		}else if(commandText.equals("Rectangle")) {
			gui.getMakePanel().createSpriteElement(commandText);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		SpriteElement spriteElement = null;
		SpriteElement oldElement = gui.getBoardPanel().getSpriteElement();
		if(oldElement != null)
		{
			if(oldElement instanceof CircularSprite) {
				try {
					spriteElement = new  CircularSprite((CircularSprite)oldElement);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} else {
				try {
					spriteElement = new  RectangularSprite((RectangularSprite)oldElement);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			spriteElement.setElementX(e.getX());
			spriteElement.setElementY(e.getY());
			gui.getBoardPanel().addComponent(spriteElement);
			gameDriver.addSpriteElements(spriteElement);
			
			for(List <ActionLink> actionList : eventMap.values())
			{
				List <ActionLink> tempList = new ArrayList<>();
				for(ActionLink actionLink : actionList)
				{
					if(actionLink.getSprite()==oldElement) {
						tempList.add(new ActionLink(spriteElement, actionLink.getSpriteElement2IdOrCategory(), actionLink.getAction()));
					}
				}
				actionList.addAll(tempList);
			}
			
			if(gameDriver.containsGameLoseSprite(oldElement))
			{
				gameDriver.addGameLoseSprite(spriteElement);
			}
			
			if(gameDriver.containsGameWinSprite(oldElement))
			{
				gameDriver.addGameWinSprite(spriteElement);
			}
			gui.draw(null);
			
			
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public Map<String, List<ActionLink>> getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map<String, List<ActionLink>> eventMap) {
		this.eventMap = eventMap;
	}

	
}
