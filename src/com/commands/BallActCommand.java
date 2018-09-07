package com.commands;

import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate; 

public class BallActCommand implements Command {

	Ball ball;
	Coordinate prevCenter;
	Coordinate prevDelta;
	Coordinate currentCenter;
	Coordinate currentDelta;
	
	public BallActCommand(Ball ball) {
		super();
		this.ball = ball;
		this.currentCenter = null;
		this.currentDelta = null;
	}

	@Override
	public void execute() {
		// Move ball 
		Circle circle = ball.getCircle();
		
		if(currentCenter != null) {
			 circle.setCenter(currentCenter);
			 if(currentDelta != null) ball.setDelta(currentDelta);
		}else{
		prevCenter = circle.getCenter();
		prevDelta = new Coordinate(ball.getDelta().getX(), ball.getDelta().getY());
		
   	    int newCenterX = circle.getCenter().getX() + ball.getDelta().getX() ;
        int newCenterY = circle.getCenter().getY() + ball.getDelta().getY() ;
        currentCenter = new Coordinate(newCenterX, newCenterY);
        
  	    circle.setCenter(currentCenter);
		checkBounds();
		}
	}
	
    public void execute(int newCenterX,int newCenterY, int deltaX, int deltaY)
    {
    	Circle circle = ball.getCircle();
		
		prevCenter = circle.getCenter();
		prevDelta = ball.getDelta();
		
		ball.setDelta(new Coordinate(deltaX, deltaY));
		circle.setCenter(new Coordinate(newCenterX, newCenterY));
		checkBounds();
    }
	private void checkBounds() {
		//Collision between ball and wall
		
		Circle circle = ball.getCircle();
		Coordinate delta = ball.getDelta();
		
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
 			currentCenter = new Coordinate(newCenterX, newCenterY);
 			currentDelta = delta;
 			circle.setCenter(currentCenter);
 		}
	}

	@Override
	public void undo() { 
		Circle circle  = ball.getCircle();
		circle.setCenter(prevCenter);
		ball.setDelta(prevDelta);
	}

}
