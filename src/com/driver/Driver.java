package com.driver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.common.Constants;
import com.component.Ball;
import com.component.Brick;
import com.component.ClockObserver;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinates;
import com.dimension.Rectangle;
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
		checkCollisionBetweenBallAndPaddle();
		checkCollisionBetweenBallAndWalls();
		gui.update(milliseconds);
		
	}
	private void checkCollisionBetweenBallAndPaddle(){
		
		int nearestX = ball.getCircle().getCenter().getX();
		int nearestY = ball.getCircle().getCenter().getY();
		
		Circle circle = ball.getCircle();
		Rectangle rectangle = paddle.getRectangle();
		
		int centerX = circle.getCenter().getX();
		int centerY = circle.getCenter().getY();
		
		int topRectangleX= rectangle.getTopLeftCoordinate().getX();
		int topRectangleY = rectangle.getTopLeftCoordinate().getY();
		
		boolean isLeft = false;
		
		if (centerX < topRectangleX) {
			nearestX = topRectangleX;
			isLeft = true;
		} else if (centerX > topRectangleX + rectangle.getWidth()) {
			nearestX = topRectangleX + rectangle.getWidth();
		}
		
		if (centerY < topRectangleY) {
			nearestY = topRectangleY;
		} else if (centerY > topRectangleY + rectangle.getHeight()) {
			nearestY = topRectangleY + rectangle.getWidth();
		}
		Coordinates ballDelta = ball.getDelta(); 
		
		//find distance
		int distance = (int) Math.sqrt(Math.pow(centerX-nearestX, 2) + Math.pow(centerY-nearestY, 2));
		if(distance <= circle.getRadius())
		{
			//collision occurred
			if(isLeft) {
				if((centerY >= topRectangleY) && (centerY <= topRectangleY+ rectangle.getHeight())) {
					//left side
					ballDelta.setX(-ballDelta.getX());
					centerX = topRectangleX - circle.getRadius();
				}else if (centerY< topRectangleY){
					//top side
					ballDelta.setY(-ballDelta.getY());
					centerY = topRectangleY - circle.getRadius();
				}else{
					//bottom side
					ballDelta.setY(-ballDelta.getY());
					centerY = topRectangleY+ rectangle.getHeight() + circle.getRadius();
				}
					
			}else {
				if((centerY >= topRectangleY) && (centerY <= topRectangleY + rectangle.getHeight())) {
					//right Side
					ballDelta.setX(-ballDelta.getX());
					centerX = topRectangleX + rectangle.getWidth() + circle.getRadius();
				}else if  (centerY< topRectangleY) {
					//top
					ballDelta.setY(-ballDelta.getY());
					centerY = topRectangleY - circle.getRadius();
				}else{
					//bottom side
					ballDelta.setY(-ballDelta.getY());
					centerY = topRectangleY+rectangle.getHeight() + circle.getRadius();
				}
			}
			circle.setCenter(new Coordinates(centerX, centerY));
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
	
	

}
