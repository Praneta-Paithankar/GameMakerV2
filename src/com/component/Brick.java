package com.component;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;

import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class Brick  implements Element{

	protected Logger log = Logger.getLogger(Brick.class);
	private Rectangle rectangle;
	private boolean visible;
	private Color color;
	private JsonObject jsonObject;
	
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
	public JsonObject save() {
		jsonObject = new JsonObject();
		try {
			jsonObject.put("Brick", this.isVisible());
			jsonObject.put("BrickX", this.getRectangle().getTopLeftCoordinate().getX());
			jsonObject.put("BrickY", this.getRectangle().getTopLeftCoordinate().getY());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public int load(Object object) {
		// TODO Auto-generated method stub'
		jsonObject = (JsonObject) object;
		this.setVisible((Boolean)jsonObject.getBoolean("Brick"));
		this.getRectangle().getTopLeftCoordinate().setX((int)(long)jsonObject.getInteger("BrickX"));
		this.getRectangle().getTopLeftCoordinate().setY((int)(long)jsonObject.getInteger("BrickY"));
		
		if(this.isVisible() == true) {
			return 1;
		}else {
			return 0;
		}
	}	
}