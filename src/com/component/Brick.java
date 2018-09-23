package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class Brick  implements Element,Serializable{

	protected static Logger log = Logger.getLogger(Brick.class);
	private Rectangle rectangle;
	private boolean visible;
	private Color color;
	
	public Brick(Rectangle rectangle, boolean visible,Color color) {
		this.setRectangle(rectangle);
		this.setVisible(visible);
		this.color = color;
	}
	//copy constructor
	public Brick(Brick brick)
	{
		Rectangle r = brick.getRectangle();
		Coordinate t = new Coordinate(r.getTopLeftCoordinate().getX(), r.getTopLeftCoordinate().getY());
		this.rectangle = new Rectangle(r.getWidth(), r.getHeight(), t);
		this.visible = brick.isVisible();
		
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
	public void reset(Brick brick)
	{
		Rectangle r = brick.getRectangle();
		Coordinate t = new Coordinate(r.getTopLeftCoordinate().getX(), r.getTopLeftCoordinate().getY());
		this.rectangle = new Rectangle(r.getWidth(), r.getHeight(), t);
		this.visible = brick.isVisible();
	}


	@Override
	public void addComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(ObjectOutputStream op) {
		try {
			op.writeObject(this);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public Element load(ObjectInputStream ip) {
		try {
			Brick obj = (Brick)ip.readObject();
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}	
}