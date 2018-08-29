package com.driver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	private ArrayList<Brick> bricks;
    private GUI gui;
    private BreakoutTimer timer;
    private static int counter ;
    private static int noOfBricks;
	public Driver(Ball ball, Paddle paddle, ArrayList<Brick> bricks, GUI gui,BreakoutTimer timer) {
		super();
		this.ball = ball;
		this.paddle = paddle;
		this.bricks = bricks;
		this.gui = gui;
		this.timer = timer;
		this.noOfBricks = bricks.size();
		counter = 0;
    }

	@Override
	public void update(long milliseconds) {
		ball.enact();
		counter +=1;
		for(Brick b : bricks) {
			if(b.isVisible())
			{
				if(checkCollision(b.getRectangle(),false)){
					b.enact();
					noOfBricks--;
				}
			}	
		}
		if(noOfBricks ==0)
		{
			gui.updateTime(milliseconds);
			gui.removeKeyListner();
  			gui.changeUI();;
  			gui.addGameOverPane();
  			return;
		}
		//Check collision between ball and paddle
		checkCollision(paddle.getRectangle(), false);
		
		//check collision between ball and paddle 
		checkCollisionBetweenBallAndWalls();
		if( counter == Constants.TICK_PER_SECOND) {
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
	
	private boolean checkCollision(Rectangle rectangle, boolean isPaddle)
	{
		//ball and rectangle
		Circle circle = ball.getCircle();
		
		
		int centerX = circle.getCenter().getX();
		int centerY = circle.getCenter().getY();
		

		float nearestX = Math.max(rectangle.getTopLeftCoordinate().getX(), Math.min(centerX, rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth()));
		float nearestY = Math.max(rectangle.getTopLeftCoordinate().getY(), Math.min(centerY, rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight()));

		int distance = (int) Math.sqrt(Math.pow(centerX-nearestX, 2) + Math.pow(centerY-nearestY, 2));
		if(distance <= circle.getRadius())
		{
			if(distance == 0 && isPaddle) {
				centerX = centerX + 2 * paddle.getDeltaX();
			}
			float ux = nearestX - centerX;
			float uy = -nearestY + centerY;
			
			float vx = ball.getDelta().getX();
			float vy = -ball.getDelta().getY();
			double theta = Math.asin((ux * vy - vx * uy) / (Math.sqrt(ux * ux + uy * uy) * Math.sqrt(vx * vx + vy * vy)));

			double nDx = -Math.cos(2 * theta) * ball.getDelta().getX() - Math.sin(2 * theta) * (-ball.getDelta().getY());
			double nDy = Math.sin(2 * theta) * ball.getDelta().getX() - Math.cos(2 * theta) * (-ball.getDelta().getY());
			
			int newDeltaX = (int) Math.round(nDx);
			int newDeltaY = (int) Math.round(-nDy);
			
			ball.getDelta().setX(newDeltaX);
			ball.getDelta().setY(newDeltaY);
			ball.enact();
			return true;
        }
		return false;
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
