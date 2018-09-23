package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;

public class ProjectileCommand implements Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890565196288726491L;
	
	SpriteElement sprite;

	int heightY;

	Boolean flag;
	
	public ProjectileCommand(SpriteElement sprite, int heightY, Boolean flag){

		this.sprite = sprite;
		this.heightY = heightY;
		this.flag = flag;
		
	}
	


	@Override
	public void execute() {


			if(flag == false) {sprite.setElementY(sprite.getElementY()+sprite.getYVel());}
			else {sprite.setElementY(sprite.getElementY()-sprite.getYVel());}
			
			sprite.setElementX(sprite.getElementX()+ sprite.getXVel());


	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}