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
	
	public BallActCommand(Ball ball) {
		super();
		this.ball = ball;
	}

	@Override
	public void execute() {
		// Move ball 
		Circle circle = ball.getCircle();
		
		prevCenter = circle.getCenter();
		prevDelta = ball.getDelta();
		
   	    int newCenterX = circle.getCenter().getX() + ball.getDelta().getX() ;
        int newCenterY = circle.getCenter().getY() + ball.getDelta().getY() ;
  	    circle.setCenter(new Coordinate(newCenterX, newCenterY));
		checkBounds();
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
 			 circle.setCenter(new Coordinate(newCenterX, newCenterY));
 		}
	}

	@Override
	public void undo() { 
		Circle circle  = ball.getCircle();
		circle.setCenter(prevCenter);
		ball.setDelta(prevDelta);
	}

}
