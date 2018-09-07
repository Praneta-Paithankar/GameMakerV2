package com.driver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.commands.*;
import com.component.Ball;
import com.component.Brick;
import com.component.Clock;
import com.component.Paddle;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.infrastruture.Observer;
import com.timer.BreakoutTimer;
import com.ui.GUI;

public class Driver implements Observer, KeyListener,ActionListener{
	
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Brick> bricks;
    private GUI gui;
    private BreakoutTimer observable;
    private int noOfBricks;
    private BrickActCommand[] brickActCommands;
    private BallActCommand ballActCommand;
    private PaddleActCommand paddleActCommand;
    private TimerCommand timerCommand;
    private Clock clock;
//    private int count;
    private Deque<Command> commandQueue;
    
	public Driver(Ball ball, Paddle paddle, ArrayList<Brick> bricks, GUI gui,BreakoutTimer observable, Clock clock) {
		
		this.ball = ball;
		this.paddle = paddle;
		this.bricks = bricks;
		this.gui = gui;
		this.observable = observable;
		this.clock = clock;
		this.noOfBricks = bricks.size();
		brickActCommands = new BrickActCommand [noOfBricks];
		commandQueue = new ArrayDeque<Command>();
		timerCommand = new TimerCommand(clock);
//		count = 0;
		initCommands();
    }
	private void initCommands()
	{
		
		int i=0;
		for(Brick b : bricks)
		{
			brickActCommands[i] = new BrickActCommand(b);
			i++;
		}
		timerCommand = new TimerCommand(clock);
		ballActCommand = new BallActCommand(ball);
		paddleActCommand = new PaddleActCommand(paddle);
		
	}

	@Override
	public void update() {
		
		initCommands();
//		count ++;
//		if(count == Constants.TIMER_COUNT) {
//			timerCommand = new TimerCommand(clock);
//		}
		ballActCommand.execute();
		timerCommand.execute();
		commandQueue.addLast(timerCommand);
		commandQueue.addLast(ballActCommand);
		
		int i= 0;
		
		for(Brick b : bricks) {
			if(b.isVisible())
			{
				if(checkCollision(b.getRectangle())){
					brickActCommands[i].execute();
					commandQueue.addLast(brickActCommands[i]);
					noOfBricks--;
				}
			}
			i++;
		}
		if(noOfBricks == 0)
		{   
			// Stopping the observable
			observable.stopTimer();
			gui.removeKeyListner();
  			gui.changeUI();
  			gui.addGameOverPane();
  			return;
		}
		//Check collision between ball and paddle
		checkCollision(paddle.getRectangle());
		
		
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
			paddleActCommand.execute();
			commandQueue.addLast(paddleActCommand);
			checkCollision(paddle.getRectangle());
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(paddle.getDeltaX()<0)
				paddle.setDeltaX(-paddle.getDeltaX());
			paddleActCommand.execute();
			commandQueue.addLast(paddleActCommand);
			checkCollision(paddle.getRectangle());	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkCollision( Rectangle rectangle)
	{
		Circle circle = ball.getCircle();
		int centerX = circle.getCenter().getX();
		int centerY = circle.getCenter().getY();
		
		float nearestX = Math.max(rectangle.getTopLeftCoordinate().getX(), Math.min(centerX, rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth()));
		float nearestY = Math.max(rectangle.getTopLeftCoordinate().getY(), Math.min(centerY, rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight()));

		int distance = (int) Math.sqrt(Math.pow(centerX-nearestX, 2) + Math.pow(centerY-nearestY, 2));
		if(distance < circle.getRadius())
		{			
			int topRectangleX = rectangle.getTopLeftCoordinate().getX();
			int topRectangleY = rectangle.getTopLeftCoordinate().getY();
			
			int topLeftRectangleX = rectangle.getTopLeftCoordinate().getX() + circle.getRadius();
			int topLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + circle.getRadius();
			int topRightRectangleX = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth() - circle.getRadius();
			int bottomLeftRectangleY = rectangle.getTopLeftCoordinate().getY() + rectangle.getHeight() - circle.getRadius();
			
			int newCenterX = centerX;
			int newCenterY = centerY;
			Coordinate delta = ball.getDelta();
			 
			int deltaX = delta.getX();
			int deltaY = delta.getY();
			
			if(centerX  <= topLeftRectangleX && centerY > topLeftRectangleY  && centerY < bottomLeftRectangleY ) {
				// left side
				deltaX= -delta.getX();
				newCenterX = topRectangleX - circle.getRadius();
			}else if(centerX > topLeftRectangleX && centerX < topRightRectangleX && centerY<= topLeftRectangleY) {
				//top
				deltaY = -delta.getY();
				newCenterY = topRectangleY - circle.getRadius();
			}else if(centerX> topLeftRectangleX && centerX< topRightRectangleX && centerY>=bottomLeftRectangleY) {
				//bottom
				deltaY = -delta.getY() ;
				newCenterY = topRectangleY + rectangle.getHeight() + circle.getRadius();
			}else if(centerX >= topRightRectangleX && centerY> topLeftRectangleY && centerY < bottomLeftRectangleY) {
				//right
				deltaX = -delta.getX();
				newCenterX = topRectangleX + rectangle.getWidth() + circle.getRadius();
			}else if( centerX <= topLeftRectangleX && centerY <= topLeftRectangleY && centerX >= (topRectangleX - circle.getRadius())) {
				// left top
				deltaX = -delta.getX();
				deltaY = -delta.getY();
				newCenterX =(int) (topRectangleX - (circle.getRadius()/1.41));
				newCenterY =(int) (topRectangleY - (circle.getRadius()/1.41));
			}else if(centerX <= topLeftRectangleX && centerY >= bottomLeftRectangleY) {
				//left bottom
				deltaX = -delta.getX();
				deltaY =-delta.getY();
				newCenterX = (int)(topRectangleX - (circle.getRadius()/1.41));
				newCenterY = (int)(topRectangleY+ rectangle.getHeight() + (circle.getRadius()/1.41));
			}else if(centerX >= topRightRectangleX && centerY <= topLeftRectangleY) {
				// right top
				deltaX = -delta.getX();
				deltaY = -delta.getY();
				newCenterX = (int)(topRectangleX + rectangle.getWidth() + (circle.getRadius()/1.41));
				newCenterY = (int)(topRectangleY - (circle.getRadius()/1.41));
			}else if (centerX >= topRightRectangleX && centerY >= bottomLeftRectangleY) {
				// right bottom
				deltaX = -delta.getX();
				deltaY = -delta.getY();
				newCenterX = (int)(topRectangleX+ rectangle.getWidth() + (circle.getRadius()/1.41));
				newCenterY =  (int)(topRectangleY+  rectangle.getHeight() + (circle.getRadius()/1.41));
			}
			ballActCommand =  new BallActCommand(ball);
			ballActCommand.execute(newCenterX, newCenterY, deltaX, deltaY);
			return true;
		}
		return false;
	}
	
	private void undoAction() {

		int count = 0;
		while(count != Constants.TIMER_COUNT) {
			Command val=commandQueue.pollLast();
			if(val == null)
				break;
			if(val instanceof TimerCommand)
			{
				count++;
			}
			if(val instanceof BrickActCommand)
			{
				noOfBricks++;
			}
			val.undo();
		}
	  
	}
	
	private void replayAction() {
		// TODO Auto-generated method stub
//		Circle circle =  new Circle(Constants.BALL_RADIUS, Constants.BALL_POS_X,Constants.BALL_POS_Y);
//		clock.setSeconds(0);
//		clock.setMinutes(0);
//		ball.setCircle(circle);
		observable.removeObserver(this);
		System.out.println("in replay");
		this.gameReset();
		System.out.println("in replay");
		gui.changeUI();
		System.out.println("in replay");
		Iterator<Command> itr = commandQueue.iterator();
	
		
		new Thread(){
			public void run(){
				while(itr.hasNext()){
					try {
						SwingUtilities.invokeAndWait(new Runnable(){
							Command val = (Command) itr.next();
							@Override
							public void run() {
								// TODO Auto-generated method stub
								val.execute();
								gui.changeUI();
								try {
									currentThread();
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					} catch (InvocationTargetException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
		


////	    JFrame replayWindow = new GUI(null, null);
//	    
//////		new Thread(){
//////			public void run(){
//				Iterator<Command> itr = commandQueue.iterator();
//				while(itr.hasNext()){
//					Command val = itr.next();
//					val.execute();	
//					gui.changeUI();
//					System.out.println("GameReset");
//				}
//
//		System.out.println("done: "+ clock.getSeconds());

		//		while(itr.hasNext()) {
		//			itr.next().execute();
		//		}
//		System.out.println(itr.toString());
//		  while(itr.hasNext()) {
////			  System.out.println(itr.toString());
////			  itr.next();
////			  System.out.println(itr);
//			  Command val = (Command) itr.next();
//////			  
//////			  val.execute();
//		  }
//		  

//	}
//	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText= e.getActionCommand();
		if(commandText.equals("undo")) {
			observable.removeObserver(this);
			undoAction();
			observable.registerObserver(this);
		}
		
		else if(commandText.equals("replay")) {
			replayAction();
		}
		gui.changeFocus();
		gui.changeUI();
	}
	
	public void gameReset() {
	
		ball.reset();
		paddle.reset();
		clock.reset();

		for (Brick b : bricks) {
			b.reset();	
		}
	}

}
