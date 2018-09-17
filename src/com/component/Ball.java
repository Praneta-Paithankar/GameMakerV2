package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.dimension.Circle;
import com.dimension.Coordinate;
import com.infrastruture.Constants;
import com.infrastruture.Element;


public class Ball implements Element,Serializable{
	
	protected static Logger log = Logger.getLogger(Ball.class);
    private Circle circle;
    private Coordinate delta;
    private Color color;
    
	public Ball(Circle circle, Coordinate delta,Color color) {
		this.setCircle(circle); 
		this.setDelta(delta);
		this.color = color;
	}
	//Copy Constructor
    public Ball(Ball ball) {
    	Circle c = ball.getCircle();
    	this.circle = new  Circle(c.getRadius(), c.getCenter().getX(),  c.getCenter().getY());
    	this.delta = new Coordinate (ball.getDelta().getX(),ball.getDelta().getY());
    }
	public void draw(Graphics g){
    	int radius =  circle.getRadius();
        int upperLeftX = circle.getCenter().getX() - radius;
        int upperLeftY = circle.getCenter().getY() - radius;
        int diameter = 2 * radius;
        
        g.setColor(color);
        g.fillOval(upperLeftX, upperLeftY, diameter, diameter);
    }

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Coordinate getDelta() {
		return delta;
	}

	public void setDelta(Coordinate delta) {
		this.delta = delta;
	}
	
	public void reset(){
		circle.setCenter(new Coordinate(Constants.BALL_POS_X,Constants.BALL_POS_Y));
		this.setDelta(new Coordinate(Constants.BALL_DELTA_X, Constants.BALL_DELTA_Y));
	}

	public void reset(Ball ball) {
		Circle c = ball.getCircle();
    	this.circle = new  Circle(c.getRadius(), c.getCenter().getX(),  c.getCenter().getY());
    	this.delta = new Coordinate (ball.getDelta().getX(),ball.getDelta().getY());
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
			Ball obj = (Ball)ip.readObject();
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
