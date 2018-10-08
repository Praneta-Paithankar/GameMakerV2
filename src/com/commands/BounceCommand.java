package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;
import com.infrastruture.Direction;

public class BounceCommand implements Command{
	
	private SpriteElement element;
	private Direction d;
	
	public BounceCommand(SpriteElement element, Direction d) {
		this.element = element;
		this.d = d;
	}
	
	@Override
	public void execute() {
		if (d == Direction.X) {
			element.setXVel(-1 * element.getXVel());
		} else if (d == Direction.Y) {
			element.setYVel(-1 * element.getYVel());
		} else if (d == Direction.BOTH) {
			element.setXVel(-1 * element.getXVel());
			element.setYVel(-1 * element.getYVel());
		}
		
	}

	@Override
	public void undo() {
		if (d == Direction.X) {
			element.setXVel(-1 * element.getXVel());
		} else if (d == Direction.Y) {
			element.setYVel(-1 * element.getYVel());
		}
		
	}
	

}
