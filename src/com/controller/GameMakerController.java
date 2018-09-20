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
	private ArrayList<Ball> ballList;
	private ArrayList<Paddle> paddleList;
	private ArrayList<Brick> brickList;
    private GUI gui;
    private int noOfBricks;
	
	public GameMakerController() {
		this.ballList = ballList;
		this.paddleList = paddleList;
		this.brickList = brickList;
		this.gui = gui;
		this.noOfBricks = brickList.size();
	}
	
	public void addElementToGame(ArrayList<Element> elementList) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
