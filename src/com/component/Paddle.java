package com.component;
import com.dimension.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Element{

	private Rectangle rectangle;
	private int deltaY;
	private Color color;

	
	public Paddle(Rectangle rectangle, int deltaY, Color color) {
		super();
		this.rectangle = rectangle;
		this.deltaY = deltaY;
		this.color =color;
	}

	public void enact() {
		
    }
	

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	@Override
	public void draw(Graphics g) {  
	      g.setColor(color);
	      g.fillRect(rectangle.getTopLeftCoordinate().getX(), rectangle.getTopLeftCoordinate().getY(), rectangle.getWidth(), rectangle.getHeight());
	}
	
	

}
