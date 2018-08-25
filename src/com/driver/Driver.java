package com.driver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.component.Ball;
import com.component.Brick;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinates;
import com.dimension.Rectangle;
import com.infrastruture.ClockObserver;
import com.infrastruture.Constants;
import com.ui.GUI;

public class Driver implements ClockObserver, KeyListener{
	
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
    private GUI gui;
    
    
	public Driver(Ball ball, Paddle paddle, Brick brick, GUI gui) {
		super();
		this.ball = ball;
		this.paddle = paddle;
		this.brick = brick;
		this.gui = gui;
    }
	@Override
	public void update(long milliseconds) {
		ball.enact();
		collision();
		checkCollisionBetweenBallAndWalls();
		if(checkCollisionBetweenBallAndBrick()) {
			brick.enact();
		}
		gui.update(milliseconds);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(paddle.getDeltaY()>0)
				paddle.setDeltaY(-paddle.getDeltaY());
			paddle.enact();
			checkCollisionBetweenPaddleAndWalls();
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(paddle.getDeltaY()<0)
				paddle.setDeltaY(-paddle.getDeltaY());
			paddle.enact();
			checkCollisionBetweenPaddleAndWalls();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkCollisionBetweenBallAndBrick() {
		Circle circle = ball.getCircle();
		Rectangle rectangle = brick.getRectangle();
		return checkCollisionBetweenCircleAndRectangle(circle, rectangle);
	}
	
	private boolean checkCollisionBetweenCircleAndRectangle(Circle circle, Rectangle rectangle)
	{
		int centerX = circle.getCenter().getX();
		int centerY = circle.getCenter().getY();
		
		int nearestX = circle.getCenter().getX();
		int nearestY = circle.getCenter().getY();
		
		int topRectangleX= rectangle.getTopLeftCoordinate().getX();
		int topRectangleY = rectangle.getTopLeftCoordinate().getY();
		
		if (centerX < topRectangleX) {
			nearestX = topRectangleX;
		} else if (centerX > topRectangleX + rectangle.getWidth()) {
			nearestX = topRectangleX + rectangle.getWidth();
		}
		
		if (centerY < topRectangleY) {
			nearestY = topRectangleY;
		} else if (centerY > topRectangleY + rectangle.getHeight()) {
			nearestY = topRectangleY + rectangle.getHeight();
		}
		
		//find distance
		int distance = (int) Math.sqrt(Math.pow(centerX-nearestX, 2) + Math.pow(centerY-nearestY, 2));
		if(distance <= circle.getRadius())
		{
			return true;
		}
		return false;
		
	}
	private void collision()
	{
		Circle circle = ball.getCircle();
		Rectangle rectangle = paddle.getRectangle();
		boolean isHit = checkCollisionBetweenCircleAndRectangle(circle,rectangle);
		if(isHit) {
			int centerX = circle.getCenter().getX();
			int centerY = circle.getCenter().getY();
			
			int topRectangleX = rectangle.getTopLeftCoordinate().getX();
			int topRectangleY = rectangle.getTopLeftCoordinate().getY();
			
			int topLeftRectangleX = rectangle.getTopLeftCoordinate().getX() + circle.getRadius();
			int topLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + circle.getRadius();
			int topRightRectangleX = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth() - circle.getRadius();
			int topRightRectangleY = topLeftRectangleY;
			
			
			int bottomLeftRectangleX = topLeftRectangleX ;
			int bottomLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight() - circle.getRadius();
			int bottomRightRectangleX =  topRightRectangleX;
			int bottomRightRectangleY = bottomLeftRectangleY;
			
			int newCenterX = centerX;
			int newCenterY = centerY;
			Coordinates delta = ball.getDelta();
			
			// left side 
			if(centerX  <= topLeftRectangleX && centerY > topLeftRectangleY  && centerY < bottomLeftRectangleY ) {
				System.out.println("Left");
				delta.setX(-delta.getX());
				newCenterX = topRectangleX - circle.getRadius();
			}else if(centerX > topLeftRectangleX && centerX < topRightRectangleX && centerY<= topLeftRectangleY) {
				//top
				System.out.println("Top");
				delta.setY(-delta.getY());
				newCenterY = topRectangleY - circle.getRadius();
			}else if(centerX> bottomLeftRectangleX && centerX< bottomRightRectangleX && centerY>=bottomLeftRectangleY) {
				//bottom
				System.out.println("Bottom");
				delta.setY(-delta.getY());
				newCenterY = topRectangleY + rectangle.getHeight() + circle.getRadius();
			}else if(centerX >= topRightRectangleX && centerY> topRightRectangleY && centerY < bottomRightRectangleY) {
				//right
				System.out.println("Right");
				delta.setX(-delta.getX());
				newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
			}else if( centerX <= topLeftRectangleX && centerY <= topLeftRectangleY) {
				// left top
				System.out.println("Left Top");
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = topRectangleX - circle.getRadius();
				newCenterY = topRectangleY - circle.getRadius();
			}else if(centerX <= bottomLeftRectangleX && centerY >= bottomLeftRectangleY) {
				System.out.println("Left bottom");
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = topRectangleX+ rectangle.getHeight() - circle.getRadius();
				newCenterY = topRectangleY+ rectangle.getHeight() + circle.getRadius();
			}else if(centerX >= topRightRectangleX && centerY <= topRightRectangleY) {
				// right top
				System.out.println("Right top");
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
				newCenterY = topRectangleY - circle.getRadius();
			}else if (centerX >= topRightRectangleX && centerY >= bottomRightRectangleY) {
				// right bottom
				System.out.println("Right bottom");
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = topRectangleX+ rectangle.getWidth() + circle.getRadius();
				newCenterY = topRectangleY+  rectangle.getHeight() + circle.getRadius();
			}
			circle.setCenter(new Coordinates(newCenterX, newCenterY));
		}
	}
	private void checkCollisionBetweenBallAndPaddle(){
		
		Circle circle = ball.getCircle();
		Rectangle rectangle = paddle.getRectangle();
		boolean isHit = checkCollisionBetweenCircleAndRectangle(circle,rectangle);
		if(isHit) {
			int centerX = circle.getCenter().getX();
			int centerY = circle.getCenter().getY();
			int topRectangleX = rectangle.getTopLeftCoordinate().getX();
			int topRectangleY = rectangle.getTopLeftCoordinate().getY();
			int newCenterX = centerX;
			int newCenterY = centerY;
			Coordinates delta = ball.getDelta();
			
			if(centerX <= topRectangleX + circle.getRadius() && centerY >= topRectangleY && centerY <= topRectangleY+ rectangle.getHeight()) {
				//left side of rectangle
				if(centerY > topRectangleY && centerY < topRectangleY+ rectangle.getHeight()) {
					//left side
					System.out.println("Left");
					delta.setX(-delta.getX());
					newCenterX = topRectangleX - circle.getRadius();
				}else if(centerY >= topRectangleY + circle.getRadius()) {
					// left top corner
					System.out.println("Left Top");
					delta.setX(-delta.getX());
					delta.setY(-delta.getY());
					newCenterX = topRectangleX - circle.getRadius();
					newCenterY = topRectangleY - circle.getRadius();
				}else if( centerY <= topRectangleY + rectangle.getHeight()+circle.getRadius()) {
					// bottom left corner
					System.out.println("Left bottom");
					delta.setX(-delta.getX());
					delta.setY(-delta.getY());
					newCenterX = topRectangleX+ rectangle.getHeight() + circle.getRadius();
					newCenterY = topRectangleY+ rectangle.getHeight() + circle.getRadius();
				}	
			}else if(centerX >= topRectangleX + rectangle.getWidth()) {
				// right side of rectangle
				if(centerY > topRectangleY && centerY < topRectangleY+ rectangle.getHeight()) {
					//right side 
					System.out.println("Right");
					delta.setX(-delta.getX());
					newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
				}else if(centerY >= topRectangleY + circle.getRadius()) {
					// right top corner
					System.out.println("Right top");
					delta.setX(-delta.getX());
					delta.setY(-delta.getY());
					newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
					newCenterY = topRectangleY + rectangle.getWidth() + circle.getRadius();
				}else if( centerY <= topRectangleY + rectangle.getHeight() + circle.getRadius()) {
					// right bottom corner
					System.out.println("Right bottom");
					delta.setX(-delta.getX());
					delta.setY(-delta.getY());
					newCenterX = topRectangleX+ rectangle.getWidth()+ rectangle.getHeight() + circle.getRadius();
					newCenterY = topRectangleY+ rectangle.getWidth() + rectangle.getHeight() + circle.getRadius();
				}	
			}else if(centerY <= topRectangleY + circle.getRadius()) {
				//top side
				System.out.println("Top");
				delta.setY(-delta.getY());
				newCenterY = topRectangleY - circle.getRadius();
			}else if(centerY >= topRectangleY+ rectangle.getHeight() - circle.getRadius()) {
				System.out.println("Bottom");
				delta.setY(-delta.getY());
				newCenterY = topRectangleY + rectangle.getHeight() + circle.getRadius();
			}else {
				System.out.println("Not covered");
			}
			
			circle.setCenter(new Coordinates(newCenterX, newCenterY));
			
		}
		
	}
	
	private void checkCollisionBetweenBallAndWalls() {
		
		Circle circle = ball.getCircle();
		Coordinates delta = ball.getDelta();
		
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
 
	private void checkCollisionBetweenPaddleAndWalls() {
		Rectangle rectangle = paddle.getRectangle();
		int top = rectangle.getTopLeftCoordinate().getY();
		int bottom = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight();
				
		if(top <= 0) {
			rectangle.setTopLeftCoordinate(new Coordinates(rectangle.getTopLeftCoordinate().getX(),0));
		}else if(bottom >= Constants.BOARD_PANEL_HEIGHT) {
			rectangle.setTopLeftCoordinate(new Coordinates(rectangle.getTopLeftCoordinate().getX(),Constants.BOARD_PANEL_HEIGHT-Constants.PADDLE_HEIGHT));
		}
			
	}

}
