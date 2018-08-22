package com.component;

import com.dimension.*;
import java.awt.Color;
import java.awt.Graphics;

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
