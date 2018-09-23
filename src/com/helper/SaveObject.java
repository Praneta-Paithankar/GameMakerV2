package com.helper;

import java.io.Serializable;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import com.component.SpriteElement;
import com.infrastruture.Command;

public class SaveObject implements Serializable{
	//private Deque<Command> commandQueue;
	private List<SpriteElement> sprites;
	private Map<String, List<ActionLink>> eventMap;
	
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
	
}
