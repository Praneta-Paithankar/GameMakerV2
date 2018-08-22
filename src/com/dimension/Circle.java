package com.dimension;

public class Circle {
	private int radius;
	private Coordinates center;

	public Circle(int radius, Coordinates center) {
		super();
		this.radius = radius;
		this.center = center;
	}
	public Circle(int radius, int centerX, int centerY) {
		super();
		this.radius = radius;
		this.center = new Coordinates(centerX,centerY);
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Coordinates getCenter() {
		return center;
	}
	public void setCenter(Coordinates center) {
		this.center = center;
	}
	
}
