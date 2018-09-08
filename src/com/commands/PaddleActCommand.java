package com.commands;

import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.component.Paddle;
import com.dimension.Rectangle;
import com.dimension.Coordinate;


public class PaddleActCommand implements Command{
	
	Paddle paddle;
	Coordinate prevTopleft;
	Coordinate currentTopLeft;
	int prevDeltaX;

	public PaddleActCommand(Paddle paddle) {
		this.paddle = paddle;
		currentTopLeft = null;
	}

	@Override
	public void execute() {
		Rectangle rectangle = paddle.getRectangle();
		if(currentTopLeft != null) {
			rectangle.setTopLeftCoordinate(currentTopLeft);
			
		}else {
		
		int topX = rectangle.getTopLeftCoordinate().getX();
		int topY = rectangle.getTopLeftCoordinate().getY();

		prevTopleft = rectangle.getTopLeftCoordinate();
		prevDeltaX= paddle.getDeltaX();
	    currentTopLeft = new Coordinate(topX+ prevDeltaX, topY);
		rectangle.setTopLeftCoordinate(currentTopLeft);
		
		checkBounds();
		}
		
	}

	@Override
	public void undo() {
		
		Rectangle rectangle = paddle.getRectangle();		
		rectangle.setTopLeftCoordinate(prevTopleft);
		
		paddle.setDeltaX(prevDeltaX);
		
	}
	private void checkBounds() {
		Rectangle rectangle = paddle.getRectangle();
		int left = rectangle.getTopLeftCoordinate().getX();
		int right = rectangle.getTopLeftCoordinate().getX() + rectangle.getWidth();
		
		if(left <= 0) {
			currentTopLeft =new Coordinate(0,rectangle.getTopLeftCoordinate().getY());
			rectangle.setTopLeftCoordinate(currentTopLeft);
		}else if(right >= Constants.BOARD_PANEL_WIDTH) {
			currentTopLeft = new Coordinate(Constants.BOARD_PANEL_WIDTH - rectangle.getWidth(),rectangle.getTopLeftCoordinate().getY());
			rectangle.setTopLeftCoordinate(currentTopLeft);
		}
			
	}
	

}
