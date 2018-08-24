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
	private void checkCollisionBetweenBallAndPaddle()
	{
		// ball paddle
		// right left
		// bottom top
		// left right
		// top bottom
		boolean isHit = false;
		
		Circle circle = ball.getCircle();
		Coordinates ballDelta = ball.getDelta();
		
		int leftBall =  circle.getCenter().getX() - circle.getRadius();
 		int rightBall = circle.getCenter().getX() + circle.getRadius();
 		int topBall = circle.getCenter().getY() - circle.getRadius();
 		int bottomBall = circle.getCenter().getY() + circle.getRadius();
 		
 		int newCenterX = circle.getCenter().getX();
 		int newCenterY = circle.getCenter().getY();
 		
 		Rectangle rectangle = paddle.getRectangle();
		int topPaddle = rectangle.getTopLeftCoordinate().getY();
		int bottomPaddle = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight();
 		int leftPaddle = rectangle.getTopLeftCoordinate().getX();
 		int rightPaddle = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth();
 		
// 		if(rightBall >= leftPaddle && rightBall<= rightPaddle && topBall>= topPaddle && bottomBall <= bottomPaddle){
// 			isHit= true;
// 			ballDelta.setX(-ballDelta.getX());
// 			newCenterX= leftPaddle - circle.getRadius();
// 		}else if(leftBall <= rightPaddle && leftBall>= leftPaddle && topBall>= topPaddle && bottomBall <= bottomPaddle){
// 			isHit= true;
// 			ballDelta.setX(-ballDelta.getX());
// 			newCenterX= rightPaddle + circle.getRadius();
// 		}else if(bottomBall>= topPaddle && bottomBall <= bottomPaddle && leftBall >= leftPaddle && rightBall<= rightPaddle) {
// 			isHit= true;
// 			ballDelta.setY(-ballDelta.getY());
// 			newCenterY= topPaddle - circle.getRadius();
// 		}else if(topBall<= bottomPaddle && topBall >= topPaddle && leftBall >= leftPaddle && rightBall<= rightPaddle) {
// 			isHit= true;
// 			ballDelta.setY(-ballDelta.getY());
// 			newCenterY= bottomPaddle + circle.getRadius();
// 		}
// 		if(rightBall >= leftPaddle  && topBall>= topPaddle && bottomBall <= bottomPaddle){
// 			isHit= true;
// 			ballDelta.setX(-ballDelta.getX());
// 			newCenterX= leftPaddle - circle.getRadius();
// 		}else if(leftBall <= rightPaddle && topBall>= topPaddle && bottomBall <= bottomPaddle){
// 			isHit= true;
// 			ballDelta.setX(-ballDelta.getX());
// 			newCenterX= rightPaddle + circle.getRadius();
// 		}else if(bottomBall>= topPaddle  && leftBall >= leftPaddle && rightBall<= rightPaddle) {
// 			isHit= true;
// 			ballDelta.setY(-ballDelta.getY());
// 			newCenterY= topPaddle - circle.getRadius();
// 		}else if(topBall<= bottomPaddle  && leftBall >= leftPaddle && rightBall<= rightPaddle) {
// 			isHit= true;
// 			ballDelta.setY(-ballDelta.getY());
// 			newCenterY= bottomPaddle + circle.getRadius();
// 		}
 		int x1= rectangle.getTopLeftCoordinate().getX();
 		int x2 = rectangle.getTopLeftCoordinate().getX()+ rectangle.getWidth();
 		int y1= rectangle.getTopLeftCoordinate().getY();
 		int y2 = rectangle.getTopLeftCoordinate().getY()+ rectangle.getHeight();
 		

 		int centerX = circle.getCenter().getX();
 		int centerY = circle.getCenter().getY();
 		int radius = circle.getRadius();
 		
 		if (centerX >= x1+radius && centerX <=x2+radius && centerY >= y1+radius && centerY <= y2+radius)
 		{
 			if(centerX+ radius >= x1)
 			{
 				//left
 				ballDelta.setX(-ballDelta.getX());
 				centerX = x1 - radius;
 				circle.setCenter(new Coordinates(centerX, centerY)); 
 			}
 			else if(centerX - radius <= x2)
 			{
 				//right
 				ballDelta.setX(-ballDelta.getX());
 				centerX = x2+ radius;
 				circle.setCenter(new Coordinates(centerX, centerY)); 
 			}
 			else if(centerY - radius <= y2)
 			{
 				//bottom
 				ballDelta.setY(-ballDelta.getY());
 				centerY = y2+ radius;
 				circle.setCenter(new Coordinates(centerX, centerY)); 
 			}
 			else if(centerY + radius >= y1)
 			{
 				//top
 				ballDelta.setY(-ballDelta.getY());
 				centerY = y1 -radius;
 				circle.setCenter(new Coordinates(centerX, centerY)); 
 				
 			}
 		}
 		
// 		if(isHit)
// 		{
// 			circle.setCenter(new Coordinates(newCenterX, newCenterY)); 
// 		}
		
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
