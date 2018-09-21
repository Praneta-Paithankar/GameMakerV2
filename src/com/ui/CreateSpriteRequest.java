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
	
}
