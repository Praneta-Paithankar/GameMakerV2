package com.component;

import com.dimension.*;
import java.awt.Color;
import java.awt.Graphics;
import com.common.Constants;

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
    	    
    	    checkCollisionWithWalls();
    	    
    }
    
    private void checkCollisionWithWalls() {
		
    		//get current position of ball
    		int left =  circle.getCenter().getX() - circle.getRadius();
    		int right = circle.getCenter().getX() + circle.getRadius();
    		int top = circle.getCenter().getY() - circle.getRadius();
    		int bottom = circle.getCenter().getY() + circle.getRadius();
    		
    		boolean isHit = false;
    		int newCenterX = circle.getCenter().getX();
    		int newCenterY = circle.getCenter().getY();
    		
    		if((left <=0) && (delta.getX() < 0))
    		{
    			isHit = true;
    			delta.setX(-delta.getX());
    			newCenterX = circle.getRadius(); 
    		}
    		if((right >= Constants.BOARD_PANEL_WIDTH) && (delta.getX() > 0))
    		{
    			isHit = true;
    			delta.setX(-delta.getX());
    			newCenterX = Constants.BOARD_PANEL_WIDTH - circle.getRadius(); 
    		}
    		if((top <=0) && (delta.getY() < 0))
    		{
    			isHit = true;
    			delta.setY(-delta.getY());
    			newCenterY = circle.getRadius(); 
    		}
    		if((bottom >= Constants.BOARD_PANEL_HEIGHT) && (delta.getY() > 0))
    		{
    			isHit = true;
    			delta.setY(-delta.getY());
    			newCenterY = Constants.BOARD_PANEL_HEIGHT - circle.getRadius(); 
    		}
    		if(isHit)
    		{
    			 circle.setCenter(new Coordinates(newCenterX, newCenterY));
    		}
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
