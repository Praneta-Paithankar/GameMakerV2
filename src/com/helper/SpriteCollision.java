package com.helper;

import org.apache.log4j.Logger;

import com.component.SpriteElement;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

public class SpriteCollision {
protected static Logger log = Logger.getLogger(CollisionChecker.class);
	
	public void checkCollisionOfSprites(SpriteElement sourceSprite) {
		if(sourceSprite.getClass().getClass().toString().contains("CircularSprite")) {
			checkCollisionBetweenBallAndWall(sourceSprite);
		}else {
			checkCollisionBetweenRectangleAndWall(sourceSprite);
		}
	}
	
	public void checkCollisionOfSprites(SpriteElement sourceSprite,SpriteElement destinationSprite) {

		if(sourceSprite.getClass().getClass().toString().contains("CircularSprite") && destinationSprite.getClass().toString().contains("CircularSprite")) {
			checkCollisionBetweenTwoCircles(sourceSprite, destinationSprite);
		}
		else if(sourceSprite.getClass().getClass().toString().contains("RectangularSprite") && destinationSprite.getClass().toString().contains("RectangularSprite")) {
			checkCollisionBetweenTwoRectangles(sourceSprite, destinationSprite);
		}
		else if(sourceSprite.getClass().getClass().toString().contains("CircularSprite") && destinationSprite.getClass().toString().contains("RectangularSprite")) {
			checkCollisionBetweenTwoRectangles(sourceSprite, destinationSprite);
		}
		else if(sourceSprite.getClass().getClass().toString().contains("RectangularSprite") && destinationSprite.getClass().toString().contains("CircularSprite")) {
			checkCollisionBetweenTwoRectangles(sourceSprite, destinationSprite);
		}
		
	}
	
	public  Direction checkCollisionBetweenBallAndWall(SpriteElement sprite) {
		//Collision between ball and wall
		int spriteX = sprite.getElementX();
		int spriteY = sprite.getElementY();
		int velX = sprite.getXVel();
		int velY = sprite.getYVel();
		
 		//get current position of ball
 		int left =  spriteX - sprite.getRadius();
 		int right = spriteX + sprite.getRadius();
 		int top = spriteY - sprite.getRadius();
 		int bottom = spriteY + sprite.getRadius();
 		
 		
 		if((left <=0) && (velX < 0))
 		{
 		    return Direction.X;
 		}
 		if((right >= Constants.BOARD_PANEL_WIDTH) && (velX > 0))
 		{
 			return Direction.X;
 		}
 		if((top <=0) && (velY < 0))
 		{
 			return Direction.Y;
 		}
 		if((bottom >= Constants.BOARD_PANEL_HEIGHT) && (velY > 0))
 		{
 			return Direction.Y;
 		}
 	
		return Direction.NONE;
	}
	
	public Direction checkCollisionBetweenRectangleAndWall(SpriteElement sprite) {
		int topLeftX = sprite.getElementX();
		int topLeftY = sprite.getElementY();
		
		//collision with left wall
		if(topLeftX == 0) {
			return Direction.X;
		}
		//collision with right wall
		if((topLeftX+sprite.getWidth()) == Constants.BOARD_PANEL_WIDTH) {
			return Direction.X;
		}
		//collision with top wall
		if(topLeftY == 0) {
			return Direction.Y;
		}
		//collision with bottom wall
		if((topLeftY+sprite.getHeight()) == Constants.BOARD_PANEL_HEIGHT) {
			return Direction.Y;
		}
		
		return Direction.NONE;
	}
	
	public Direction checkCollisionBetweenTwoRectangles(SpriteElement sourceSprite, SpriteElement destinationSprite){
		
		// If one rectangle is on left side of other 
		if (sourceSprite.getElementX() > (destinationSprite.getElementX()+destinationSprite.getWidth()) || 
				destinationSprite.getElementX() > (sourceSprite.getElementX()+sourceSprite.getWidth())) 
	        return Direction.NONE; 
	  
	    // If one rectangle is above other 
		if (sourceSprite.getElementY() > (destinationSprite.getElementY()+destinationSprite.getHeight()) || 
				destinationSprite.getElementY() > (sourceSprite.getElementY()+sourceSprite.getHeight())) 
	        return Direction.NONE;
	    
		return Direction.X;
		
	}
	
	public Direction checkCollisionBetweenTwoCircles(SpriteElement sourceSprite,SpriteElement destinationSprite) {
		
		int sourceCircleX = sourceSprite.getElementX();
		int sourceCircleY = sourceSprite.getElementY();
		int destinationCircleX = destinationSprite.getElementX();
		int destinationCircleY = destinationSprite.getElementY();
		 
		int changeX = sourceCircleX-destinationCircleX;
		int changeY = sourceCircleY-destinationCircleY;
		if (Math.sqrt((changeX)*(changeX) + (changeY)*(changeY)) <= (sourceSprite.getRadius()+destinationSprite.getRadius())){
			if(sourceCircleX>destinationCircleX)
				return Direction.X;
			if(sourceCircleY>destinationCircleY)
				return Direction.Y;
		}
		return Direction.NONE;
		
	}

	public Direction checkCollisionBetweenCircleAndRectangle(SpriteElement sourceSprite,SpriteElement destinationSprite)
	{
		SpriteElement circleSprite;
		SpriteElement rectangleSprite;
	
		if(sourceSprite.getClass().toString().contains("Circular")) {
			circleSprite = sourceSprite;
			rectangleSprite = destinationSprite;
		}else {
			circleSprite = destinationSprite;
			rectangleSprite = sourceSprite;
		}
		
		int circleX = circleSprite.getElementX();
		int circleY = circleSprite.getElementY();
		
		float nearestX = Math.max(rectangleSprite.getElementX(), Math.min(circleX, rectangleSprite.getElementX() + rectangleSprite.getWidth()));
		float nearestY = Math.max(rectangleSprite.getElementY(), Math.min(circleY, rectangleSprite.getElementY() + rectangleSprite.getHeight()));

		int distance = (int) Math.sqrt(Math.pow(circleX-nearestX, 2) + Math.pow(circleY-nearestY, 2));
		if(distance < circleSprite.getRadius())
		{			
			int topRectangleX = rectangleSprite.getElementX();
		
			int topLeftRectangleX = rectangleSprite.getElementX() + circleSprite.getRadius();
			int topLeftRectangleY = rectangleSprite.getElementY() + circleSprite.getRadius();
			int topRightRectangleX = rectangleSprite.getElementX() + rectangleSprite.getWidth() - circleSprite.getRadius();
			int bottomLeftRectangleY = rectangleSprite.getElementY() + rectangleSprite.getHeight() - circleSprite.getRadius();
			
			
			if(circleX  <= topLeftRectangleX && circleY > topLeftRectangleY  && circleY < bottomLeftRectangleY ) {
				// left side
				return Direction.X;
			}else if(circleX > topLeftRectangleX && circleX < topRightRectangleX && circleY<= topLeftRectangleY) {
				//top
				return Direction.Y;
			}else if(circleX> topLeftRectangleX && circleX< topRightRectangleX && circleY>=bottomLeftRectangleY) {
				//bottom
				return Direction.Y;
			}else if(circleX >= topRightRectangleX && circleY> topLeftRectangleY && circleY < bottomLeftRectangleY) {
				//right
				return Direction.X;
			}else if( circleX <= topLeftRectangleX && circleY <= topLeftRectangleY && circleX >= (topRectangleX - circleSprite.getRadius())) {
				// left top
				return Direction.BOTH;
			}else if(circleX <= topLeftRectangleX && circleY >= bottomLeftRectangleY) {
				//left bottom
				return Direction.BOTH;
			}else if(circleX >= topRightRectangleX && circleY <= topLeftRectangleY) {
				// right top
				return Direction.BOTH;
			}else if (circleX >= topRightRectangleX && circleY >= bottomLeftRectangleY) {
				// right bottom
				return Direction.BOTH;
			}
		}
		return Direction.NONE;
	}
	
	
}
