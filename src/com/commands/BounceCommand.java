package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;
import com.infrastruture.Direction;

public class BounceCommand implements Command{
	
	private SpriteElement element;
	private Direction d;
	
	public BounceCommand(SpriteElement element, Direction d) {
		// TODO Auto-generated constructor stub
		this.element = element;
		this.d = d;
	}
	
	@Override
	public void execute() {
		if (d == Direction.X) {
			element.setXVel(-1 * element.getXVel());
		} else if (d == Direction.Y) {
			element.setYVel(-1 * element.getYVel());
		}
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if (d == Direction.X) {
			element.setXVel(-1 * element.getXVel());
		} else if (d == Direction.Y) {
			element.setYVel(-1 * element.getYVel());
		}
		
	}
	

}
