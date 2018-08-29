package com.component;
import java.awt.Color;
import java.awt.Graphics;

import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Sprite;

public class Paddle implements Sprite{

	private Rectangle rectangle;
	private int deltaX;
	private Color color;
	
	public Paddle(Rectangle rectangle, int deltaX, Color color) {
		this.rectangle = rectangle;
		this.deltaX = deltaX;
		this.color = color;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	@Override
	public void draw(Graphics g) {  
	      g.setColor(color);
	      g.fillRect(rectangle.getTopLeftCoordinate().getX(), rectangle.getTopLeftCoordinate().getY(), rectangle.getWidth(), rectangle.getHeight());
	}

	@Override
	public void enact() {
		int topX = rectangle.getTopLeftCoordinate().getX();
		int topY = rectangle.getTopLeftCoordinate().getY();

		Coordinate newCoordinate = new Coordinate(topX+ deltaX, topY);
		rectangle.setTopLeftCoordinate(newCoordinate);
		
	}
}