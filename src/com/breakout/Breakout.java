package com.breakout;

import java.awt.Color;
import java.util.ArrayList;

import com.component.Ball;
import com.component.Brick;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.driver.Driver;
import com.infrastruture.Constants;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import com.ui.GamePanel;

public class Breakout {
	
	public static void startGame(){
		
		BreakoutTimer timer  = new BreakoutTimer();
		GamePanel boardPanel =new GamePanel();
		
		Circle c = new Circle(Constants.BALL_RADIUS, Constants.BALL_POS_X,Constants.BALL_POS_Y);
		Ball ball = new Ball(c, new Coordinate(Constants.BALL_DELTA_X, Constants.BALL_DELTA_Y), Constants.BALL_COLOR);
		boardPanel.addElement(ball);

		Rectangle r =new Rectangle(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,Constants.PADDLE_POS_X, Constants.PADDLE_POS_Y);
		Paddle paddle = new Paddle(r,Constants.PADDLE_DELTA_X,Constants.PADDLE_COLOR);
		boardPanel.addElement(paddle);
		

		
		GUI gui = new GUI(boardPanel);
		ArrayList<Brick> bricks = new ArrayList<>();
		int brickPosX = Constants.BRICK_START_X; 
		
		for(int i=0; i< Constants.BRICK_NO; i++) {
			r = new Rectangle(Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT, brickPosX, Constants.BRICK_START_Y);
			Brick brick = new Brick(r , true,Constants.BRICK_COLOR);
			bricks.add(brick);
			boardPanel.addElement(brick);
			brickPosX += Constants.BRICK_WIDTH ;
		}
		
		
		Driver driver = new Driver(ball, paddle, bricks, gui,timer);
		
		gui.addDriver(driver);
		timer.registerObserver(driver);
    	    timer.startTimer();
       	gui.pack();
		gui.setVisible(true);
			
	}

}
