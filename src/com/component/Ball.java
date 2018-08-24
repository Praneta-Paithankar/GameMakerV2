package com.component;

import java.awt.Color;
import java.awt.Graphics;

import com.dimension.Circle;
import com.dimension.Coordinates;

public class Ball extends Element{
    private Circle circle;
    private Coordinates delta;
    private Color color;
	public Ball(Circle circle, Coordinates delta,Color color) {
		super();
		this.setCircle(circle); 
		this.setDelta(delta);
		this.color= color;
	}
    
    public void enact(){
    		// Move ball 
    	
    	    int newCenterX = circle.getCenter().getX() + delta.getX() ;
    	    int newCenterY = circle.getCenter().getY() + delta.getY() ;
    	    
    	    circle.setCenter(new Coordinates(newCenterX, newCenterY));
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

	public Coordinates getDelta() {
		return delta;
	}

	public void setDelta(Coordinates delta) {
		this.delta = delta;
	}

}
