package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.component.Ball;
import com.component.Brick;
import com.component.Paddle;
import com.infrastruture.Element;
import com.ui.GUI;

public class GameMakerController implements  KeyListener,ActionListener {
	protected static Logger log = Logger.getLogger(GameMakerController.class);
    private GUI gui;
	
	public GameMakerController(GUI gui) {
		this.gui = gui;
		//this.noOfBricks = brickList.size();
	}
	
	public void addElementToGame(ArrayList<Element> elementList) {
		
	}
	
	/*This method is called from action performed method .. when user clicks Done on make panel.*/
	public void done() {
		String spriteName = (String)gui.getMakePanel().getSpriteSelection().getSelectedItem();
		System.out.println("-----  "+spriteName);
	}
	
	public void make() {
		gui.getMakePanel().getSpriteSelection().setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String commandText= e.getActionCommand();
		System.out.println("GMController - action -- "+commandText);
		if(commandText.equals("done")) {
			done();
		}else if(commandText.equals("make")) {
			make();
		}
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
