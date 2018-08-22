package com.component;

import java.awt.Color;
import java.awt.Graphics;
import com.dimension.*;
public class Brick extends Element{

	private Rectangle rectangle;
	private boolean visible;
	private Color color;
	public Brick(Rectangle rectangle, boolean visible,Color color) {
		super();
		this.setRectangle(rectangle);
		this.setVisible(visible);
		this.color = color;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public void enact() {
		
	}
	@Override
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(rectangle.getTopLeftCoordinate().getX(), rectangle.getTopLeftCoordinate().getY(), rectangle.getWidth(), rectangle.getHeight());
	}
	
}