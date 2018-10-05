package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;
import com.infrastruture.Constants;

public class MoveCommand implements Command{
	
	SpriteElement sprite;
	int elementX;
	int elementY;

	public MoveCommand(SpriteElement sprite){
		this.sprite = sprite;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.elementX = sprite.getElementX();
		this.elementY = sprite.getElementY();
		
		sprite.setElementX(elementX+sprite.getXVel());
		sprite.setElementY(elementY+sprite.getYVel());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		sprite.setElementX(elementX);
		sprite.setElementY(elementY);
		
	}

}
