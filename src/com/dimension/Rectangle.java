package com.dimension;

public class Rectangle {
	
	private int width;
	private int height;
	private Coordinates topLeft;
	
	public Rectangle(int width, int height, Coordinates topLeft) {
		super();
		this.width = width;
		this.height = height;
		this.topLeft = topLeft;
	}
	public Rectangle(int width, int height, int topLeftX,int topLeftY) {
		super();
		this.width = width;
		this.height = height;
		this.topLeft = new Coordinates(topLeftX,topLeftY);
	}
	public Coordinates getTopLeftCoordinate() {
		return topLeft;
	}
	public void setTopLeftCoordinate(Coordinates topLeft) {
		this.topLeft = topLeft;
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
