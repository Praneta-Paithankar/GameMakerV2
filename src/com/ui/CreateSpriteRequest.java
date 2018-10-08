package com.ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class CreateSpriteRequest {
	private String elementName;
	private int xlocation;
	private int ylocation;
	private int xVel;
	private int yVel;
	private String spriteId;
	private String category;
	private Color color;
	private String imagePath;
	private int width;
	private int height;
	private int gameEndDependency;
	private Map<String, String> eventAction;
	HashMap<String,String> collisionMap;
	private int counterInterval;
	public CreateSpriteRequest(String name, int x, int y, int velX, int velY, int width, int height, Color color, 
								String imageFilePath, String spriteId, String category, Map<String, String> eventAction, int gameEndDependency, HashMap<String,String> collisionMap, int counterInterval) {
		this.elementName = name;
		this.xlocation = x;
		this.ylocation = y;
		this.xVel = velX;
		this.yVel = velY;
		this.width = width;
		this.height = height;
		this.color = color;
		this.imagePath = imageFilePath;
		this.spriteId = spriteId;
		this.category = category;
		this.eventAction = eventAction;
		this.gameEndDependency=gameEndDependency;
		this.collisionMap=collisionMap;
		this.counterInterval=counterInterval;
	}
	
	public int getCounterInterval() {
		return counterInterval;
	}

	public void setCounterInterval(int counterInterval) {
		this.counterInterval = counterInterval;
	}

	public HashMap<String, String> getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(HashMap<String, String> collisionMap) {
		this.collisionMap = collisionMap;
	}

	public int getGameEndDependency() {
		return gameEndDependency;
	}

	public void setGameEndDependency(int gameEndDependency) {
		this.gameEndDependency = gameEndDependency;
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

	public int getXVel() {
		return xVel;
	}

	public void setXVel(int xVel) {
		this.xVel = xVel;
	}

	public int getYVel() {
		return yVel;
	}

	public void setYVel(int yVel) {
		this.yVel = yVel;
	}

	public String getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
