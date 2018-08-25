package com.breakout;

import java.awt.Color;

import com.component.Ball;
import com.component.Brick;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinates;
import com.dimension.Rectangle;
import com.driver.Driver;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import com.ui.GamePanel;

public class Breakout {
	
	public static void main(String[] args){
		
		BreakoutTimer timer  = new BreakoutTimer(6);
		GamePanel boardPanel =new GamePanel();
		
		Ball ball = new Ball(new Circle(15, 0, 200), new Coordinates(20, 40), Color.CYAN);
		boardPanel.addElement(ball);

		Paddle paddle = new Paddle(new Rectangle(50, 200, 350, 200), 30, Color.BLACK);
		boardPanel.addElement(paddle);
		
		Brick brick = new Brick(new Rectangle(50, 75, 600, 100), true, Color.GREEN);
		boardPanel.addElement(brick);
		
		GUI gui = new GUI(boardPanel);
		Driver driver = new Driver(ball, paddle, brick, gui);
		
		gui.addDriver(driver);
		timer.addObserver(driver);
    	    timer.startTimer();
       	gui.pack();
		gui.setVisible(true);
			
	}

}
