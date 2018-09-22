package com.controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commands.BounceCommand;
import com.commands.MacroCommand;
import com.commands.MoveCommand;
import com.commands.SpriteBlowCommand;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.infrastruture.Observer;
import com.timer.BreakoutTimer;
import com.ui.GUI;

public class GameDriver implements Observer, KeyListener{
	private List<SpriteElement> sprites  ;
	private Map<String, List<ActionLink>> eventMap;
	private GUI gui;
	private BreakoutTimer timer;

	public GameDriver(GUI gui, BreakoutTimer timer){
		this.sprites = new ArrayList<SpriteElement>();
		this.eventMap = new HashMap<>();
		this.gui = gui;
		this.timer = timer;
	}

	public void addSpriteElements(SpriteElement sprite) {
		System.out.println("in sprite add " + sprite.toString());
		sprites.add(sprite);
	}

	public void removeSprite(SpriteElement sprite) {
		sprites.remove(sprite);
	}

	public List<SpriteElement> getSprites() {
		return sprites;
	}

	public void setSprites(List<SpriteElement> sprites) {
		this.sprites = sprites;
	}

	public Map<String, List<ActionLink>> getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map<String, List<ActionLink>> eventMap) {
		this.eventMap = eventMap;
	}

	public void InitPlay() {

		System.out.println("InitPlay ::: "+sprites.toString());
		gui.draw(null);
		
		timer.registerObserver(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		checkCollision();
		eventHandler("OnTick");
		gui.paintView();
	}

	public void checkCollision() {
		MacroCommand macroCommand = new MacroCommand();
		
		Direction d;
		if (eventMap.containsKey("onCollision")) {
			List<ActionLink> eventObservers = eventMap.get("onCollision");
			for(ActionLink actionObserver: eventObservers) {
				//d = checkCollisionToWall(actionObserver.getSprite());
				//actionForCollision(actionObserver, d, macroCommand);
				for(SpriteElement element: sprites) {
					//d = checkCollisionOfSprite(actionObserver.getSprite(),element);
					//actionForCollision(actionObserver, d, macroCommand);
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
					//macroCommand.addCommand(shoot);
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

}
