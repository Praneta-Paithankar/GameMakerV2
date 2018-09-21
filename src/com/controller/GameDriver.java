package com.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.component.*;
import com.helper.ActionLink;

public class GameDriver {
	
	List<SpriteElement> sprites  ;
	Map<String, ActionLink> eventMap;
	
	public GameDriver(){
		this.sprites = new ArrayList<SpriteElement>();
		this.eventMap = new HashMap<String, ActionLink>();
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

	public void setSprites(List<SpriteElement> sprites) {
		this.sprites = sprites;
	}

	public Map<String, ActionLink> getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map<String, ActionLink> eventMap) {
		this.eventMap = eventMap;
	}

	public void InitPlay() {
		
		for(SpriteElement sprite: sprites) {
			sprite.draw();
		}
	}
	
	
	
	
	
	
}
