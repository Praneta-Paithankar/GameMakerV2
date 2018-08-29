package com.component;

import java.awt.Color;
import java.awt.Graphics;

import com.dimension.Circle;
import com.dimension.Coordinate;
import com.infrastruture.Sprite;

public class Ball implements Sprite{
    private Circle circle;
    private Coordinate delta;
    private Color color;
	public Ball(Circle circle, Coordinate delta,Color color) {
		this.setCircle(circle); 
		this.setDelta(delta);
		this.color= color;
	}
    
    public void enact(){
    		// Move ball 
    	 int newCenterX = circle.getCenter().getX() + delta.getX() ;
         int newCenterY = circle.getCenter().getY() + delta.getY() ;
   	     circle.setCenter(new Coordinate(newCenterX, newCenterY));
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

}
