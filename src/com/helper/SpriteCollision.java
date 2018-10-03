package com.helper;

import org.apache.log4j.Logger;

import com.component.CircularSprite;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

public class SpriteCollision {
protected static Logger log = Logger.getLogger(SpriteCollision.class);
	
	public Direction checkCollisionOfSprites(SpriteElement c) {
		if (c.getElementX() <= 0 || c.getElementX() + c.getImageWidth() >=  Constants.BOARD_PANEL_WIDTH) {
			return Direction.X;
		}
		if (c.getElementY() <= 0 || c.getElementY() + c.getImageHeight() >= Constants.BOARD_PANEL_HEIGHT) {
			return Direction.Y;
		}
		return Direction.NONE;
	}
	public Direction checkCollisionOfSprites(SpriteElement c1,SpriteElement c2) {
		Direction currentDirection = Direction.NONE;
//		if (c1.intersects(c2) && (topCollision(c1, c2) || botCollision(c1, c2))) { // c1: top/bot c2: bot/top
//			currentDirection = Direction.Y;
//		} 
//		if (c1.intersects(c2) && (rightCollision(c1, c2) ||  leftCollision(c1, c2))) { // c1: right/left c2: left/right
//			currentDirection = currentDirection==Direction.NONE? Direction.X:Direction.X;
//		}
		if (c1.intersects(c2) && rightCollision(c1, c2)) { // c1: right c2: left
			currentDirection = Direction.X;
		} else if (c1.intersects(c2) && leftCollision(c1, c2)) { // c1: left c2: right
			currentDirection = Direction.X;
		}
		if (c1.intersects(c2) && topCollision(c1, c2)) { // c1: top c2: bot
			currentDirection = currentDirection==Direction.NONE? Direction.Y:Direction.BOTH;
		} else if (c1.intersects(c2) && botCollision(c1, c2)) { // c1: bot c2: top
			currentDirection = currentDirection==Direction.NONE? Direction.Y:Direction.BOTH;
		} else if (c1.intersects(c2)) {
			currentDirection = Direction.BOTH;
		}
		return currentDirection;
	}

	private boolean topCollision(SpriteElement c1, SpriteElement c2) {
		int oneTop = c1.getElementY();
		int oneBot = c1.getElementY() + c1.getImageHeight();
		int twoTop = c2.getElementY();
		int twoBot = c2.getElementY() + c2.getImageHeight();

		return (oneTop >= twoTop && oneTop <= twoBot);
	}

	private boolean rightCollision(SpriteElement c1, SpriteElement c2) {
		int oneRight = c1.getElementX() + c1.getImageWidth();
		int twoLeft = c2.getElementX();
		int twoRight = c2.getElementX() + c2.getImageWidth();
		return oneRight >= twoLeft && oneRight <= twoRight;
	}

	private boolean botCollision(SpriteElement c1, SpriteElement c2) {
		int oneBot = c1.getElementY() + c1.getImageHeight();
		int twoTop = c2.getElementY();
		int twoBot = c2.getElementY() + c2.getImageHeight();

		return oneBot >= twoTop && oneBot <= twoBot;
	}

	private boolean leftCollision(SpriteElement c1, SpriteElement c2) {
		int oneLeft = c1.getElementX();
		int c2Left = c2.getElementX();
		int c2Right = c2.getElementX() + c2.getImageWidth();

		return oneLeft >= c2Left && oneLeft <= c2Right;
	}

	public Direction checkCollisionOfSprites2(SpriteElement sourceSprite,SpriteElement destinationSprite) {
		if (sourceSprite.isVisible() && destinationSprite.isVisible()) {
			if(sourceSprite.getClass().toString().contains("CircularSprite") && destinationSprite.getClass().toString().contains("CircularSprite")) {
				return checkCollisionBetweenTwoCircles((CircularSprite)sourceSprite, (CircularSprite)destinationSprite);
			}
			else if(sourceSprite.getClass().toString().contains("RectangularSprite") && destinationSprite.getClass().toString().contains("RectangularSprite")) {
				return checkCollisionBetweenTwoRectangles((RectangularSprite) sourceSprite, (RectangularSprite) destinationSprite);
			}
			else if(sourceSprite.getClass().toString().contains("CircularSprite") && destinationSprite.getClass().toString().contains("RectangularSprite")) {
				return checkCollisionBetweenCircleAndRectangle((CircularSprite) sourceSprite, (RectangularSprite) destinationSprite);
			}
			else if(sourceSprite.getClass().toString().contains("RectangularSprite") && destinationSprite.getClass().toString().contains("CircularSprite")) {
				return checkCollisionBetweenCircleAndRectangle((RectangularSprite)sourceSprite, (CircularSprite) destinationSprite);
			}
		}
		return Direction.NONE;
	}
	
	public  Direction checkCollisionBetweenBallAndWall(CircularSprite sprite) {
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
	
	public Direction checkCollisionBetweenRectangleAndWall(RectangularSprite sprite) {
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
	
	public Direction checkCollisionBetweenTwoRectangles(RectangularSprite sourceSprite, RectangularSprite destinationSprite){
		
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
	
	public Direction checkCollisionBetweenTwoCircles(CircularSprite sourceSprite,CircularSprite destinationSprite) {
		
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
		CircularSprite circleSprite;
		RectangularSprite rectangleSprite;
		
		if(sourceSprite.getClass().toString().contains("Circular")) {
			
			circleSprite = (CircularSprite) sourceSprite;
			rectangleSprite = (RectangularSprite) destinationSprite;
		}else {
			circleSprite = (CircularSprite) destinationSprite;
			rectangleSprite = (RectangularSprite) sourceSprite;
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
