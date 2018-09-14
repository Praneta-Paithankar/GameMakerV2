package com.breakout;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import com.component.Ball;
import com.component.Brick;
import com.component.Clock;
import com.component.Paddle;
import com.controller.GameController;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.helper.CollisionChecker;
import com.infrastruture.*;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import com.ui.GamePanel;
import com.ui.MainPanel;
import com.ui.StaticPanel;

public class Breakout {
	protected Logger log = Logger.getLogger(Breakout.class);
	public static void startGame(boolean isRestart){
		
		BreakoutTimer observable  = new BreakoutTimer();
		GamePanel boardPanel =new GamePanel();
		
		Circle c = new Circle(Constants.BALL_RADIUS, Constants.BALL_POS_X,Constants.BALL_POS_Y);
		Ball ball = new Ball(c, new Coordinate(Constants.BALL_DELTA_X, Constants.BALL_DELTA_Y), Constants.BALL_COLOR);
		boardPanel.addComponent(ball);
        
        
		Rectangle r =new Rectangle(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,Constants.PADDLE_POS_X, Constants.PADDLE_POS_Y);
		Paddle paddle = new Paddle(r,Constants.PADDLE_DELTA_X,Constants.PADDLE_COLOR);
		boardPanel.addComponent(paddle);
		
		
		ArrayList<Brick> bricks = new ArrayList<>();
		int brickPosX = Constants.BRICK_START_X; 
		int brickPosY = Constants.BRICK_START_Y;
		for(int i=0; i< Constants.BRICK_NO; i++) {
			r = new Rectangle(Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT, brickPosX, brickPosY);
			Brick brick = new Brick(r , true,Constants.BRICK_COLOR);
			bricks.add(brick);
			boardPanel.addComponent(brick);
			
			brickPosX += 2* Constants.BRICK_WIDTH+ Constants.BRICK_DISTANCE_X ;
			brickPosY +=  Constants.BRICK_DISTANCE_Y;
		}
		
		Clock clock = new Clock();
		clock.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_WIDTH));
		clock.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_WIDTH));
//		clock.setPreferredSize(new Dimension(50, 50));
//		clock.setMaximumSize(new Dimension(50, 50));
		StaticPanel timerPanel = new StaticPanel();
		timerPanel.addComponent(clock);
		
		MainPanel mainPanel = new MainPanel();
		mainPanel.addComponent(timerPanel);
		mainPanel.addComponent(boardPanel);

		GUI gui = new GUI(mainPanel, boardPanel,timerPanel);
		
		gui.addComponent(mainPanel);
		
		CollisionChecker checker = new CollisionChecker();
		
		GameController driver = new GameController(ball, paddle, bricks, gui,observable, clock,checker);
		
		gui.addDriver(driver);
		observable.startTimer();
		gui.setVisible(true);
//		gui.changeUI();
		System.out.println("Print from Breakout");
		gui.draw(null);
		if(isRestart)
			observable.registerObserver(driver);
		else
			driver.pause();
	}
	public static void main(String args[]) {
		PropertyConfigurator.configure("log4j.properties");
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				startGame(false);	
			}
		});
	}
}
