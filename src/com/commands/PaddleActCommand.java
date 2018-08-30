package com.commands;

import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.component.Paddle;
import com.dimension.Rectangle;
import com.dimension.Coordinate;


public class PaddleActCommand implements Command{
	
	Paddle paddle;
	Coordinate prevTopleft;
	int prevDeltaX;

	public PaddleActCommand(Paddle paddle) {
		super();
		this.paddle = paddle;
	}

	@Override
	public void execute() {
		
		Rectangle rectangle = paddle.getRectangle();
		int topX = rectangle.getTopLeftCoordinate().getX();
		int topY = rectangle.getTopLeftCoordinate().getY();

		prevTopleft = rectangle.getTopLeftCoordinate();
		prevDeltaX= paddle.getDeltaX();
		Coordinate newCoordinate = new Coordinate(topX+ prevDeltaX, topY);
		rectangle.setTopLeftCoordinate(newCoordinate);
		checkBounds();
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
			rectangle.setTopLeftCoordinate(new Coordinate(0,rectangle.getTopLeftCoordinate().getY()));
		}else if(right >= Constants.BOARD_PANEL_WIDTH) {
			rectangle.setTopLeftCoordinate(new Coordinate(Constants.BOARD_PANEL_WIDTH - rectangle.getWidth(),rectangle.getTopLeftCoordinate().getY()));
		}
			
	}
	

}
