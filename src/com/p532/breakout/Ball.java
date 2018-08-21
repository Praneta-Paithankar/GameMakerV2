package com.p532.breakout;

public class Ball {

	private int width;
	private int height;
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;

	public Ball(int width, int height, int x, int y, int xSpeed, int ySpeed) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public Ball() {

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public void move() {
		this.x = this.x + this.xSpeed;
		this.y = this.y + this.ySpeed;
	}

}
