package com.commands;

import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate; 

public class BallEnactCommand implements Command {

	Ball ball;
	
	public BallEnactCommand(Ball ball) {
		this.ball = ball;
		
	}

	@Override
	public void execute() {
		// Move ball 
		Circle circle = ball.getCircle();
	
   	    int newCenterX = circle.getCenter().getX() + ball.getDelta().getX() ;
        int newCenterY = circle.getCenter().getY() + ball.getDelta().getY() ;
        Coordinate currentCenter = new Coordinate(newCenterX, newCenterY);
        circle.setCenter(currentCenter);
		//checkBounds();
	}
	@Override
	public void undo() { 
		Circle circle = ball.getCircle();
		
	    int newCenterX = circle.getCenter().getX() - ball.getDelta().getX() ;
	    int newCenterY = circle.getCenter().getY() - ball.getDelta().getY() ;
	    Coordinate currentCenter = new Coordinate(newCenterX, newCenterY);
		
		circle.setCenter(currentCenter);
	}
	
}
