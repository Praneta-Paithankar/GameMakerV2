package com.ui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONObject;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class GUI extends JFrame implements Element{
	
	private GamePanel boardPanel;
	private ArrayList<Element> elements;
	private GameController driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
	
	public GUI() {
		elements = new ArrayList<>();
		initializeUI();
	}

	public GUI(GamePanel boardPanel, StaticPanel timerPanel) {
		elements = new ArrayList<>();
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
	public void draw(Graphics g) {
		for(Element element : elements) {
			element.draw(g);
		}
		
	}

	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}

	@Override
	public void addComponent(Element e) {
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

	
	
}
