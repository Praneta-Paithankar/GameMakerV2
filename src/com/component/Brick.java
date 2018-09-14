package com.component;

import java.awt.Color;
import java.awt.Graphics;

import org.json.JSONObject;

import com.dimension.Rectangle;
import com.infrastruture.Constants;
import com.infrastruture.Element;
import com.infrastruture.Savable;

public class Brick implements Element,Savable{

	private Rectangle rectangle;
	private boolean visible;
	private Color color;
	public Brick(Rectangle rectangle, boolean visible,Color color) {
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
	public void draw(Graphics g){
		if(visible) {
		g.setColor(color);
		g.fillRect(rectangle.getTopLeftCoordinate().getX(), rectangle.getTopLeftCoordinate().getY(), rectangle.getWidth(), rectangle.getHeight());
		g.setColor(Constants.BRICK_BORDER);
		g.drawRect(rectangle.getTopLeftCoordinate().getX(), rectangle.getTopLeftCoordinate().getY(), rectangle.getWidth(), rectangle.getHeight());
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		
	}

	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(Savable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Savable e) {
		// TODO Auto-generated method stub
		
	}

	
}
