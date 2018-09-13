package com.commands;

import com.component.Ball;
import com.infrastruture.Command;

public class BallChangeYDirectionCommand implements Command {

	private Ball ball;
	
	public BallChangeYDirectionCommand(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
		ball.getDelta().setY(-1* ball.getDelta().getY());

	}

	@Override
	public void undo() {
		ball.getDelta().setY(-1* ball.getDelta().getY());

	}

}
