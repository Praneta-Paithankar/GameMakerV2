package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.component.Clock;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.helper.GameObject;
import com.infrastruture.Element;
import com.timer.BreakoutTimer;
import com.ui.CreateSpriteRequest;
import com.ui.GUI;

public class GameMakerController implements  ActionListener {
	protected static Logger log = Logger.getLogger(GameMakerController.class);
    private GUI gui;
	private CreateSpriteRequest sprite;
	private GameObject gameObject;
	private SpriteElement newSprite;
	//private ActionLink actionLink;
	//private List<SpriteElement> spriteList  ;
	private Map<String, List<ActionLink>> eventMap;
	private GameDriver gameDriver;
	private BreakoutTimer timer;
	private Clock clock;
	
	public GameMakerController(GUI gui, Clock clock) {
		this.gui = gui;
		//this.spriteList = new ArrayList<>();
		this.gameObject = new GameObject();
		this.timer = new BreakoutTimer();
		this.clock = clock;
		//this.actionLink = new ActionLink();
	}
	
	public void addElementToGame(ArrayList<Element> elementList) {
		
	}
	
	/*This method is called from action performed method .. when user clicks Save on make panel.*/
	public void done() {
		try {
			System.out.println("in done");
			this.sprite = gui.getMakePanel().getNewSprite();
			this.newSprite = gameObject.spriteDecoder(sprite.getElementName(), sprite.getXlocation(), sprite.getYlocation());
			this.gameDriver.addSpriteElements(newSprite);
			this.gui.addSpriteToPanel(newSprite);
			//this.spriteList.add(newSprite);
			//this.actionLink = new ActionLink(newSprite,sprite.getEventAction().values().toString().replace("[", "").replace("]", ""));
			//this.eventMap.put(sprite.getEventAction().keySet().toString().replace("[", "").replace("]", ""), this.actionLink);
			//this.gameDriver.setEventMap(eventMap);
			//this.gameDriver.setSprites(spriteList);
			//this.gameDriver.setEventMap(eventMap);
			eventMap = gameDriver.getEventMap();
			for (Map.Entry<String,String> entry:sprite.getEventAction().entrySet()) {
				if (eventMap.containsKey(entry.getKey())) {
					eventMap.get(entry.getKey()).add(new ActionLink(newSprite, entry.getValue()));
				} else {
					List<ActionLink> listAction = new ArrayList<>();
					listAction.add(new ActionLink(newSprite, entry.getValue()));
					eventMap.put(entry.getKey(), listAction);
				}
			}
			this.gui.draw(null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		System.out.println("In Play =========");
		this.gameDriver.InitPlay();
	}
	
	public void make() {
		this.gameDriver = new GameDriver(this.gui, timer, clock);
		gui.addDriver(gameDriver);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String commandText= e.getActionCommand();
		System.out.println("GMController - action -- "+commandText);
		if(commandText.equals("play")) {
			play();
		}else if(commandText.equals("make")) {
			make();
		}
	}

}
