package com.ui;

import java.util.HashMap;
import java.util.Map;

public class CreateSpriteRequest {
	private String elementName;
	private int xlocation;
	private int ylocation;
	private Map<String, String> eventAction;
	
	public CreateSpriteRequest(String name, int x, int y) {
		this.elementName = name;
		this.xlocation = x;
		this.ylocation = y;
		eventAction = new HashMap<>(); 
	}
	
	public void addEventAction(String event, String action) {
		eventAction.put(event, action);
	}
	
	public Map<String, String> getEventAction() {
		return eventAction;
	}

	public void setEventAction(Map<String, String> eventAction) {
		this.eventAction = eventAction;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public int getXlocation() {
		return xlocation;
	}

	public void setXlocation(int xlocation) {
		this.xlocation = xlocation;
	}

	public int getYlocation() {
		return ylocation;
	}

	public void setYlocation(int ylocation) {
		this.ylocation = ylocation;
	}
	
	
}
