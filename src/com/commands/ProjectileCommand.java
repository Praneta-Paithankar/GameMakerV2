package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;
import com.infrastruture.Constants;

public class ProjectileCommand implements Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890565196288726491L;
	
	SpriteElement sprite;
//	int heightX;
	int heightY;
//	double deltaX;
//	double deltaY;
//	double Range;
	Boolean flag;
	
	public ProjectileCommand(SpriteElement sprite, int heightY, Boolean flag){

		this.sprite = sprite;
//		this.heightX = heightX;
		this.heightY = heightY;
		this.flag = flag;
		
	}
	
	
//	
//	public void calculateProjectile( int heightX, int heightY) {
//		
//		double startY = (double) sprite.getElementY();
//		double startX = (double) sprite.getElementX();
//		
//		double tan = (startY- heightY)/(startX-heightX);
//		double Height = startY-heightY;
//		double Range = heightX - startX;
//		System.out.println("Height is "+ Height);
//		double gravity = Constants.PROJECTILE_GRAVITY;
//		deltaY = Math.sqrt((Height) * 2* gravity);
//		deltaX = deltaY/tan;
//	}

	@Override
	public void execute() {
//	
//		int firstY = sprite.getElementY();
//		
//		calculateProjectile(heightX, heightY);
//		if(Range >=0) {sprite.setXVel((int)-deltaX);}
//		else {sprite.setXVel((int)deltaX);}
//		
//		while(sprite.getElementY() <= firstY) {

			if(flag == false) {sprite.setElementY(sprite.getElementY()+sprite.getYVel());}
			else {sprite.setElementY(sprite.getElementY()-sprite.getYVel());}
			
			sprite.setElementX(sprite.getElementX()+ sprite.getXVel());
//		}

//			sprite.setElementX((int)(sprite.getElementX() - deltaX));
//		else sprite.setElementX((int)(sprite.getElementX()+deltaX));
		
//		if(flag == false) {sprite.setElementY((int) (sprite.getElementY() + deltaY)); System.out.println("down");}
//		else {sprite.setElementY((int) (sprite.getElementY() - deltaY));  System.out.println("Up");}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
//		if(sprite.getElementY() <= heightY) {
//			flag = true;
//		}
//		calculateProjectile(heightX, heightY);
//		
//		sprite.setElementX((int)(sprite.getElementX() - deltaX));
//		
//		if(flag == false) {sprite.setElementX((int) (sprite.getElementX() - deltaY)); System.out.println("down");}
//		else {sprite.setElementX((int) (sprite.getElementX() + deltaY));  System.out.println("Up");}
		
		
	
		
	}

}