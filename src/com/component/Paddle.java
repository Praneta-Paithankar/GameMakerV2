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

public class Paddle  implements Element,Serializable{
	protected static Logger log = Logger.getLogger(Paddle.class);
	private Rectangle rectangle;
	private int deltaX;
	private Color color;
	
	public Paddle(Rectangle rectangle, int deltaX, Color color) {
		this.rectangle = rectangle;
		this.deltaX = deltaX;
		this.color = color;
	}
	
	public Paddle(Paddle p) {
		Rectangle r = p.getRectangle();
		Coordinate t = new Coordinate(r.getTopLeftCoordinate().getX(), r.getTopLeftCoordinate().getY());
		this.rectangle = new Rectangle(r.getWidth(), r.getHeight(), t);
		this.deltaX = p.getDeltaX();
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

	
	public void enact() {
		int topX = rectangle.getTopLeftCoordinate().getX();
		int topY = rectangle.getTopLeftCoordinate().getY();

		Coordinate newCoordinate = new Coordinate(topX+ deltaX, topY);
		rectangle.setTopLeftCoordinate(newCoordinate);
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		Coordinate newCoordinate = new Coordinate(Constants.PADDLE_POS_X, Constants.PADDLE_POS_Y);
		rectangle.setTopLeftCoordinate(newCoordinate);	 
	}
	public void reset (Paddle p) {
		Rectangle r = p.getRectangle();
		Coordinate t = new Coordinate(r.getTopLeftCoordinate().getX(), r.getTopLeftCoordinate().getY());
		this.rectangle = new Rectangle(r.getWidth(), r.getHeight(), t);
		this.deltaX = p.getDeltaX();
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
			Paddle obj = (Paddle)ip.readObject();
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}