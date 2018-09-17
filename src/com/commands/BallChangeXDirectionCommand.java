package com.commands;
import org.apache.log4j.Logger;
import com.component.Ball;
import com.infrastruture.Command;

public class BallChangeXDirectionCommand implements Command {
	protected static Logger log = Logger.getLogger(BallChangeXDirectionCommand.class);
	private Ball ball;
	
	public BallChangeXDirectionCommand(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
      ball.getDelta().setX(-1* ball.getDelta().getX());
	}

	@Override
	public void undo() {
		ball.getDelta().setX(-1* ball.getDelta().getX());
	}

}
