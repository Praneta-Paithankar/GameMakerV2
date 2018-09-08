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

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.breakout.Breakout;
import com.commands.BallActCommand;
import com.commands.BrickActCommand;
import com.commands.PaddleActCommand;
import com.commands.TimerCommand;
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
    private boolean isGamePaused = false;

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
  			SwingUtilities.invokeLater(
  					new Runnable() {

  						@Override
  						public void run() {
  						
  							gameOver();	
  						}
			});
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
		
		Iterator<Command> itr = commandQueue.iterator();
		this.gameReset();
		
		new Thread(){
			public void run(){
				GUI replayWindow = new GUI();
				replayWindow.setTitle("Replay");
				replayWindow.getBoardPanel().addElement(ball);
				replayWindow.getBoardPanel().addElement(paddle);
				replayWindow.getTimerPanel().addElement(clock);
				for (Brick b : bricks) {	
					replayWindow.getBoardPanel().addElement(b);
				}
				replayWindow.setVisible(true);
				while(itr.hasNext()){
					try {
						SwingUtilities.invokeAndWait(new Runnable(){
							Command val = (Command) itr.next();
							@Override
							public void run() {
								// TODO Auto-generated method stub
								val.execute();
								replayWindow.changeUI();
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				replayWindow.dispose();
				
			}
		}.start();
		
	}
	
	
	public void pause() {
		isGamePaused = true;
		if(!observable.isObserverListEmpty()) {
		observable.removeObserver(this);
		}
	}

	public void unPause() {
		isGamePaused = false;
		observable.registerObserver(this);
	}
	
	//Switch between actions when a button is pressed
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText= e.getActionCommand();
		if(commandText.equals("undo")) {
			if(!isGamePaused) {
				pause();
				undoAction();
				unPause();
			} else {
				undoAction();
			}
			gui.changeFocus();
			gui.changeUI();

		}
		
		else if(commandText.equals("replay")) {
			pause();
			replayAction();
			gui.changeFocus();
		}
		
		else if(commandText.equals("start")) {
			if(isGamePaused) {
				unPause();
				gui.changeFocus();
				gui.changeUI();
			}else {
				Breakout.startGame(true);
			}
		}
		else if(commandText.equals("pause")) {
			pause();
			gui.changeFocus();
			gui.changeUI();
		}
		
	}
	
	public void gameReset() {
		
		ball.reset();
		paddle.reset();
		clock.reset();
		noOfBricks = Constants.BRICK_NO;
		for (Brick b : bricks) {
			b.reset();	
		}
	}
	
	public void gameOver() {
		pause();
		Object[] options = {  "Reset", "Exit", "Replay"}; 
		String outputMsg = new String();
		String endTime = new String();
		endTime = Integer.toString(clock.getMinutes()*60 + clock.getSeconds());
		outputMsg = "Your Score is " + endTime;
		int a = JOptionPane.showOptionDialog(gui.getBoardPanel(), outputMsg, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
				, null, options, null);
		
		if(a == JOptionPane.YES_OPTION) {
			gui.dispose();
			commandQueue = new ArrayDeque<Command>();
			gameReset();
			gui.revalidate();
			Breakout.startGame(true);			
		}
		else if(a == JOptionPane.CANCEL_OPTION) {
			replayAction();
		}
		else
			System.exit(0);
		isGamePaused = false;
	}

}
