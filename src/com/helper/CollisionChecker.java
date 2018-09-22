package com.helper;

import org.apache.log4j.Logger;

import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

public class CollisionChecker {
	protected static Logger log = Logger.getLogger(CollisionChecker.class);
	public  Direction checkCollisionBetweenBallAndWall(Ball ball) {
		//Collision between ball and wall
		
		Circle circle = ball.getCircle();
		Coordinate delta = ball.getDelta();
		
 		//get current position of ball
 		int left =  circle.getCenter().getX() - circle.getRadius();
 		int right = circle.getCenter().getX() + circle.getRadius();
 		int top = circle.getCenter().getY() - circle.getRadius();
 		int bottom = circle.getCenter().getY() + circle.getRadius();
 		
 		
 		if((left <=0) && (delta.getX() < 0))
 		{
 		    return Direction.X;
 		}
 		if((right >= Constants.BOARD_PANEL_WIDTH) && (delta.getX() > 0))
 		{
 			return Direction.X;
 		}
 		if((top <=0) && (delta.getY() < 0))
 		{
 			return Direction.Y;
 		}
 		if((bottom >= Constants.BOARD_PANEL_HEIGHT) && (delta.getY() > 0))
 		{
 			return Direction.Y;
 		}
 	
		return Direction.NONE;
	}
	
	public Direction checkCollisionBetweenRectangleAndWall(Rectangle rectangle) {
		int topLeftX = rectangle.getTopLeftCoordinate().getX();
		int topLeftY = rectangle.getTopLeftCoordinate().getY();
		
		//collision with left wall
		if(topLeftX == 0) {
			return Direction.X;
		}
		//collision with right wall
		if((topLeftX+rectangle.getWidth()) == Constants.BOARD_PANEL_WIDTH) {
			return Direction.X;
		}
		//collision with top wall
		if(topLeftY == 0) {
			return Direction.Y;
		}
		//collision with bottom wall
		if((topLeftY+rectangle.getHeight()) == Constants.BOARD_PANEL_HEIGHT) {
			return Direction.Y;
		}
		
		return Direction.NONE;
	}
	
	public Direction checkCollisionBetweenTwoRectangles(Rectangle rectangle1, Rectangle rectangle2){
		Coordinate l1 = rectangle1.getTopLeftCoordinate();
		Coordinate l2 = rectangle2.getTopLeftCoordinate();
		Coordinate r1 = new Coordinate(l1.getX()+rectangle1.getWidth(), l1.getY()+rectangle1.getHeight());
		Coordinate r2 = new Coordinate(l2.getX()+rectangle2.getWidth(), l2.getY()+rectangle2.getHeight());
		
		// If one rectangle is on left side of other 
		if (l1.getX()> r2.getX()|| l2.getX() > r1.getX()) 
	        return Direction.NONE; 
	  
	    // If one rectangle is above other 
	    if (l1.getY() < r2.getY() || l2.getY() < r1.getY()) 
	        return Direction.NONE;
	    
		return Direction.X;
	}
	
	public Direction checkCollisionBetweenTwoCircles(Circle firstCircle,Circle secondCircle) {
		int firstCircleCenterX = firstCircle.getCenter().getX();
		int firstCircleCenterY = firstCircle.getCenter().getY();
		int secondCircleCenterX = secondCircle.getCenter().getX();
		int secondCircleCenterY = secondCircle.getCenter().getY();
		 
		int changeX = firstCircleCenterX-secondCircleCenterX;
		int changeY = firstCircleCenterY-secondCircleCenterY;
		if (Math.sqrt((changeX)*(changeX) + (changeY)*(changeY)) <= (firstCircle.getRadius()+secondCircle.getRadius())){
			return Direction.X;
		}
		
		return Direction.NONE;
	}

	public Direction checkCollisionBetweenCircleAndRectangle(Circle circle, Rectangle rectangle)
	{
		int centerX = circle.getCenter().getX();
		int centerY = circle.getCenter().getY();
		
		float nearestX = Math.max(rectangle.getTopLeftCoordinate().getX(), Math.min(centerX, rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth()));
		float nearestY = Math.max(rectangle.getTopLeftCoordinate().getY(), Math.min(centerY, rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight()));

		int distance = (int) Math.sqrt(Math.pow(centerX-nearestX, 2) + Math.pow(centerY-nearestY, 2));
		if(distance < circle.getRadius())
		{			
			int topRectangleX = rectangle.getTopLeftCoordinate().getX();
		
			int topLeftRectangleX = rectangle.getTopLeftCoordinate().getX() + circle.getRadius();
			int topLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + circle.getRadius();
			int topRightRectangleX = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth() - circle.getRadius();
			int bottomLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight() - circle.getRadius();
			
			
			if(centerX  <= topLeftRectangleX && centerY > topLeftRectangleY  && centerY < bottomLeftRectangleY ) {
				// left side
				return Direction.X;
			}else if(centerX > topLeftRectangleX && centerX < topRightRectangleX && centerY<= topLeftRectangleY) {
				//top
				return Direction.Y;
			}else if(centerX> topLeftRectangleX && centerX< topRightRectangleX && centerY>=bottomLeftRectangleY) {
				//bottom
				return Direction.Y;
			}else if(centerX >= topRightRectangleX && centerY> topLeftRectangleY && centerY < bottomLeftRectangleY) {
				//right
				return Direction.X;
			}else if( centerX <= topLeftRectangleX && centerY <= topLeftRectangleY && centerX >= (topRectangleX - circle.getRadius())) {
				// left top
				return Direction.BOTH;
			}else if(centerX <= topLeftRectangleX && centerY >= bottomLeftRectangleY) {
				//left bottom
				return Direction.BOTH;
			}else if(centerX >= topRightRectangleX && centerY <= topLeftRectangleY) {
				// right top
				return Direction.BOTH;
			}else if (centerX >= topRightRectangleX && centerY >= bottomLeftRectangleY) {
				// right bottom
				return Direction.BOTH;
			}
		}
		return Direction.NONE;
	}
}
