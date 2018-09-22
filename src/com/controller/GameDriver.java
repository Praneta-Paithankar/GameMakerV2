package com.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.component.*;
import com.helper.ActionLink;
import com.ui.GUI;

public class GameDriver {
	private List<SpriteElement> sprites  ;
	private Map<String, List<ActionLink>> eventMap;
	private GUI gui;
	
	public GameDriver(GUI gui){
		this.sprites = new ArrayList<SpriteElement>();
		this.eventMap = new HashMap<>();
		this.gui = gui;
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
		for(SpriteElement sprite: sprites) {
			System.out.println("sprite -- "+sprite.toString());
		}
		gui.draw(null);
		for (Map.Entry<String,List<ActionLink>> entry:eventMap.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue().size());
		}
	}
	
}
