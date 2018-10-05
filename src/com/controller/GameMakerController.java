package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
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

public class GameMakerController implements  ActionListener, MouseListener {
	protected static Logger log = Logger.getLogger(GameMakerController.class);
    private GUI gui;
	private CreateSpriteRequest sprite;
	private GameObject gameObject;
	private SpriteElement newSprite;
	private Map<String, List<ActionLink>> eventMap;
	private GameDriver gameDriver;
	private BreakoutTimer timer;
	private Clock clock;
	
	public GameMakerController(GUI gui, Clock clock) {
		log.info("Initializing game-maker-controller");
		this.gui = gui;
		this.gameObject = new GameObject();
		this.timer = new BreakoutTimer();
		this.clock = clock; 
		this.gameDriver = new GameDriver(this.gui, timer, clock);
		gui.addDriver(gameDriver);
	}
	
	public void addElementToGame(ArrayList<Element> elementList) {
		
	}
	
	/*This method is called from action performed method .. when user clicks Save on make panel.*/
	public void done() {
		try {
			this.sprite = gui.getMakePanel().getNewSprite();
			this.newSprite = gameObject.spriteDecoder(sprite);
			this.gameDriver.addSpriteElements(newSprite);
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
		for(int i=0;i<gui.getMakePanel().getCheckBox().size();i++) {
			gui.getMakePanel().getCheckBox().get(i).setVisible(true);
		}
//		gui.getMakePanel().createImage();
		gui.getMakePanel().createSpriteButtons();
		//gui.getMakePanel().createSaveButton();
		//gui.getMakePanel().createLoadButton();
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
		}else if(commandText.equals("save")) {
			save();
		}else if(commandText.equals("load")) {
			load();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		SpriteElement spriteElement = null;
		SpriteElement gamePanelSelected = gui.getBoardPanel().getSpriteElement();
		
		if(gamePanelSelected != null)
		{
			if(gamePanelSelected instanceof CircularSprite) {
				try {
					spriteElement = new  CircularSprite((CircularSprite)gamePanelSelected);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				return new CircularSprite(shootingObject.imagePath,shootingObject.elementX,shootingObject.elementY,shootingObject.imageWidth, shootingObject.imageHeight,shootingObject.XVel, shootingObject.YVel,((CircularSprite) shootingObject).getRadius());

			} else {
				try {
					spriteElement = new  RectangularSprite((RectangularSprite)gamePanelSelected);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			spriteElement.setElementX(e.getX());
			spriteElement.setElementY(e.getY());
			System.out.println("Mouse clicked on = " + spriteElement.getElementX() + " " + spriteElement.getElementY());
			
			gui.getBoardPanel().addComponent(spriteElement);
			
			
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

}
