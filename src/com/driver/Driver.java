package com.driver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.commands.*;
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

public class Driver implements ClockObserver, KeyListener,ActionListener{
	
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Brick> bricks;
    private GUI gui;
    private BreakoutTimer timer;
    private static int counter ;
    private static int noOfBricks;
    private BrickActCommand[] brickActCommands;
    private BallActCommand ballActCommand;
    private PaddleActCommand paddleActCommand;
    
	public Driver(Ball ball, Paddle paddle, ArrayList<Brick> bricks, GUI gui,BreakoutTimer timer) {
		super();
		this.ball = ball;
		this.paddle = paddle;
		this.bricks = bricks;
		this.gui = gui;
		this.timer = timer;
		this.noOfBricks = bricks.size();
		
		counter = 0;
		
		initCommands();
    }
	private void initCommands()
	{
		brickActCommands = new BrickActCommand [noOfBricks];
		int i=0;
		for(Brick b : bricks)
		{
			brickActCommands[i] = new BrickActCommand(b);
			i++;
		}
		ballActCommand = new BallActCommand(ball);
		paddleActCommand = new PaddleActCommand(paddle);
	}

	@Override
	public void update(long milliseconds) {
		//ball.enact();
		ballActCommand.execute();
		counter +=1;
		int i= 0;
		
		for(Brick b : bricks) {
			if(b.isVisible())
			{
				if(checkCollision(b.getRectangle(),false)){
					brickActCommands[i].execute();
					noOfBricks--;
				}
			}
			i++;
		}
		if(noOfBricks ==0)
		{   
			timer.stopTimer();
			gui.updateTime(milliseconds);
			gui.removeKeyListner();
  			gui.changeUI();;
  			gui.addGameOverPane();
  			return;
		}
		//Check collision between ball and paddle
		checkCollision(paddle.getRectangle(), true);
		
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
			//paddle.enact();
			paddleActCommand.execute();
			
			checkCollision(paddle.getRectangle(), true);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(paddle.getDeltaX()<0)
				paddle.setDeltaX(-paddle.getDeltaX());
			//paddle.enact();
			paddleActCommand.execute();
			checkCollision(paddle.getRectangle(), true);
			
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
		if(distance < circle.getRadius())
		{
			if(distance == 0 && isPaddle) {
				centerX = rectangle.getTopLeftCoordinate().getX() + circle.getRadius();
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
			ballActCommand.execute();
			return true;
        }
		return false;
	}	
 

	
	@Override
	public void actionPerformed(ActionEvent e) {
		ballActCommand.undo();
		paddleActCommand.undo();
		gui.changeFocus();
		gui.changeUI();
	}

}
