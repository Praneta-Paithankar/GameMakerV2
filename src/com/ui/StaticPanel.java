package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.JSONObject;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class StaticPanel extends JPanel implements Element{

	private GameController driver;
	private ArrayList<Element> elements;
	
	public StaticPanel() {
		this.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        elements = new ArrayList<>();
	}
	public ArrayList<Element> getElements(){
		return elements;
	}
	
	public void addElement(Element element){
		elements.add(element);
		
	}
	public void removeElement(Element element)
	{
		elements.remove(element);
	}
	
	public void createButtons(GameController driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
	    createPause();
	}
	
	
	public void createReplay() {
		JButton replayButton = new JButton("Replay");
		replayButton.setActionCommand("replay");
		replayButton.addActionListener(driver);
		replayButton.setVisible(true);
		this.add(replayButton);
	}
	
	public void createUndo() {
		JButton undoButton = new JButton("Undo");
		undoButton.setActionCommand("undo");
		undoButton.addActionListener(driver);
		undoButton.setVisible(true);
		this.add(undoButton);
	}
	
	public void createStart() {
		JButton startButton = new JButton("Start");
     	startButton.setActionCommand("start");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		this.add(startButton);
	}
	
	public void createPause() {
		JButton startButton = new JButton("Pause");
     	startButton.setActionCommand("pause");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		this.add(startButton);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Element component : elements)
		{
			component.draw(g);
		}
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
	public void addComponent(Element e) {
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

	@Override
	public void draw(Graphics g) {
		repaint();
	}
	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}
}
