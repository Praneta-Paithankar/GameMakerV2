package com.commands;

import com.infrastruture.Command;
import com.component.Brick;

public class BrickActCommand implements Command {

	Brick brick;
	boolean prevState;
	
	public BrickActCommand(Brick brick) {
		super();
		this.brick = brick;
		this.prevState = false;
	}

	@Override
	public void execute() {
		prevState = brick.isVisible();
		brick.setVisible(false);
		//queue.add(this);
	}

	@Override
	public void undo() {
		brick.setVisible(prevState);
		System.out.println(brick.isVisible() +"undo");
	}

}
