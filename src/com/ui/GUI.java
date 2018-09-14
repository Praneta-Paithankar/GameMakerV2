package com.ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;
import com.infrastruture.Savable;


@SuppressWarnings("serial")
public class GUI extends JFrame implements Savable{
	
	private GamePanel boardPanel;
	private ArrayList<Savable> components;
	private GameController driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
	
	public GUI() {
		components = new ArrayList<>();
		initializeUI();
	}

	public GUI(GamePanel boardPanel, StaticPanel timerPanel) {
		components = new ArrayList<>();
		this.boardPanel = boardPanel;
		this.timerPanel = timerPanel;
		initializeUI();
	}
	
	private void initializeUI() {
		
       mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
       
       mainPanel.add(timerPanel);
       mainPanel.add(boardPanel);
        
	   add(mainPanel);
		
	   mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	   mainPanel.setFocusable(true);
	   mainPanel.requestFocusInWindow();
	   setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);	
		
	}
	public void changeUI()
	{
		//boardPanel.repaint();
		boardPanel.paintImmediately(0, 0, Constants.BOARD_PANEL_WIDTH, Constants.BOARD_PANEL_HEIGHT);
		timerPanel.repaint();
	}
	public GamePanel getBoardPanel() {
		return boardPanel;
	}
	public void removeKeyListner() {
		mainPanel.removeKeyListener(driver);
	}
	public void addDriver(GameController driver){
		this.driver = driver;
		mainPanel.addKeyListener(driver);
        timerPanel.createButtons(driver);
	
	}
	public void changeFocus()
	{
		mainPanel.requestFocus();
	}
	
	
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addComponent(Savable s) {
		components.add(s);
	}


	@Override
	public void removeComponent(Savable s) {
		components.add(s);	
	}

	
	
}
