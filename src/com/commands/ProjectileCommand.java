package com.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import com.component.SpriteElement;
import com.infrastruture.Command;
import com.infrastruture.Constants;

public class ProjectileCommand implements Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890565196288726491L;
	
	SpriteElement sprite;
	int heightX;
	int heightY;
	Timer timer;
	Boolean projectileFlag;
	
	public ProjectileCommand(SpriteElement sprite, int heightX, int heightY){

		this.sprite = sprite;
		this.heightX = heightX;
		this.heightY = heightY;
		calculateProjectile(sprite,heightX, heightY);
		

		this.projectileFlag = true;
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void calculateProjectile(SpriteElement sprite, int heightX, int heightY) {
		
		double startY = (double) sprite.getElementY();
		double startX = (double) sprite.getElementX();
		
		double tan = (startY- heightY)/(startX-heightX);
		double Height = startY - heightY;
		double Range = heightX - startX;
		double gravity = Constants.PROJECTILE_GRAVITY;
		double deltaY = Math.sqrt((Height) * 2* gravity);
		double deltaX = deltaY/tan;

		sprite.setXVel((int) (-1* deltaX)); 
		sprite.setYVel((int) deltaY);
		
	}


	@Override
	public void execute() {
		
        ActionListener actionPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
   			         	
            		if(sprite.getElementY() >= Constants.BOARD_PANEL_HEIGHT) {
            			timer.stop();
            			sprite.setVisible(false);
            		}
            	
				if(sprite.getElementY() <= heightY) {
					projectileFlag = false;
				}
				if(projectileFlag == false) {sprite.setElementY(sprite.getElementY()+sprite.getYVel());}
				else {sprite.setElementY(sprite.getElementY()-sprite.getYVel());}
				
				sprite.setElementX(sprite.getElementX()+ sprite.getXVel());
            }
            
            
        };
         
        this.timer = new Timer(50,(ActionListener) actionPerformer);
        startTimer();
 	
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}