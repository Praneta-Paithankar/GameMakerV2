package com.ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class CreateSpriteRequest {
	private String elementName;
	private int xlocation;
	private int ylocation;
	private int XVel;
	private int YVel;
	private String spriteId;
	private String category;
	private Color color;
	private String imagePath;
	private int width;
	private int height;
	
	private Map<String, String> eventAction;
	
	public CreateSpriteRequest(String name, int x, int y, int velX, int velY, int width, int height, Color color, String imageFilePath, String SpriteID) {
		this.elementName = name;
		this.xlocation = x;
		this.ylocation = y;
		this.XVel = velX;
		this.YVel = velY;
		this.width = width;
		this.height = height;
		this.color = color;
		this.imagePath = imageFilePath;
		this.spriteId = spriteId;
		this.category = category;
		
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

	public int getXVel() {
		return XVel;
	}

	public void setXVel(int xVel) {
		XVel = xVel;
	}

	public int getYVel() {
		return YVel;
	}

	public void setYVel(int yVel) {
		YVel = yVel;
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
