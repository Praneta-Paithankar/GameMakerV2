package com.driver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.component.Ball;
import com.component.Brick;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.ClockObserver;
import com.infrastruture.Constants;
import com.timer.BreakoutTimer;
import com.ui.GUI;

public class Driver implements ClockObserver, KeyListener{
	
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
    private GUI gui;
    private BreakoutTimer timer;
    private static int counter ;
	public Driver(Ball ball, Paddle paddle, Brick brick, GUI gui,BreakoutTimer timer) {
		super();
		this.ball = ball;
		this.paddle = paddle;
		this.brick = brick;
		this.gui = gui;
		this.timer = timer;
		counter =0;
    }

	@Override
	public void update(long milliseconds) {
		ball.enact();
		counter +=1;
		if(checkCollisionBetweenBallAndBrick()) {
			brick.enact();
			timer.stopTimer();
			gui.updateTime(milliseconds);
			gui.removeKeyListner();
  			gui.changeUI();;
  			gui.addGameOverPane();
 			return ;
		}
		checkCollisionBetweenBallAndPaddle();
		checkCollisionBetweenBallAndWalls();
		if( counter == Constants.tickPerSecond) {
			counter = 0;
			gui.updateTime(milliseconds);
		}
		gui.changeUI();
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(paddle.getDeltaX()>0)
				paddle.setDeltaX(-paddle.getDeltaX());
			paddle.enact();
			checkCollisionBetweenPaddleAndWalls();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(paddle.getDeltaX()<0)
				paddle.setDeltaX(-paddle.getDeltaX());
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
	private void checkCollisionBetweenBallAndPaddle()
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
			int bottomLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight() - circle.getRadius();
			
			
			int newCenterX = centerX;
			int newCenterY = centerY;
			Coordinate delta = ball.getDelta();
			 
			if(centerX  <= topLeftRectangleX && centerY > topLeftRectangleY  && centerY < bottomLeftRectangleY ) {
				// left side
				delta.setX(-delta.getX());
				newCenterX = topRectangleX - circle.getRadius();
			}else if(centerX > topLeftRectangleX && centerX < topRightRectangleX && centerY<= topLeftRectangleY) {
				//top
				delta.setY(-delta.getY());
				newCenterY = topRectangleY - circle.getRadius();
			}else if(centerX> topLeftRectangleX && centerX< topRightRectangleX && centerY>=bottomLeftRectangleY) {
				//bottom
				delta.setY(-delta.getY());
				newCenterY = topRectangleY + rectangle.getHeight() + circle.getRadius();
			}else if(centerX >= topRightRectangleX && centerY> topLeftRectangleY && centerY < bottomLeftRectangleY) {
				//right
				delta.setX(-delta.getX());
				newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
			}else if( centerX <= topLeftRectangleX && centerY <= topLeftRectangleY && centerX >= (topRectangleX - circle.getRadius())) {
				// left top
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX =(int) (topRectangleX - (circle.getRadius()/1.41));
				newCenterY =(int) (topRectangleY - (circle.getRadius()/1.41));
			}else if(centerX <= topLeftRectangleX && centerY >= bottomLeftRectangleY) {
				//left bottom
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = (int)(topRectangleX - (circle.getRadius()/1.41));
				newCenterY = (int)(topRectangleY+ rectangle.getHeight() + (circle.getRadius()/1.41));
			}else if(centerX >= topRightRectangleX && centerY <= topLeftRectangleY) {
				// right top
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = (int)(topRectangleX + rectangle.getWidth() + (circle.getRadius()/1.41));
				newCenterY = (int)(topRectangleY - (circle.getRadius()/1.41));
			}else if (centerX >= topRightRectangleX && centerY >= bottomLeftRectangleY) {
				// right bottom
				delta.setX(-delta.getX());
				delta.setY(-delta.getY());
				newCenterX = (int)(topRectangleX+ rectangle.getWidth() + (circle.getRadius()/1.41));
				newCenterY =  (int)(topRectangleY+  rectangle.getHeight() + (circle.getRadius()/1.41));
			}
			circle.setCenter(new Coordinate(newCenterX, newCenterY));
		}
	}
	
	private void checkCollisionBetweenBallAndWalls() {
		
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
 
	private void checkCollisionBetweenPaddleAndWalls() {
		Rectangle rectangle = paddle.getRectangle();
		int left = rectangle.getTopLeftCoordinate().getX();
		int right = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth();
		
		if(left <= 0) {
			rectangle.setTopLeftCoordinate(new Coordinate(0,rectangle.getTopLeftCoordinate().getY()));
		}else if(right >= Constants.BOARD_PANEL_WIDTH) {
			rectangle.setTopLeftCoordinate(new Coordinate(Constants.BOARD_PANEL_WIDTH - rectangle.getWidth(),rectangle.getTopLeftCoordinate().getY()));
		}
			
	}

}
