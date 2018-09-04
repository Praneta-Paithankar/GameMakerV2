package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.driver.Driver;
import com.infrastruture.Constants;
import com.infrastruture.Sprite;

@SuppressWarnings("serial")
public class StaticPanel extends JPanel{
	//private JLabel timerlabel;
	private JLabel score;
	private Driver driver;
	private ArrayList<Sprite> elements;
	
	public StaticPanel() {
		this.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        elements = new ArrayList<>();
	}
	public ArrayList<Sprite> getElements(){
		return elements;
	}
	
	public void addElement(Sprite element){
		elements.add(element);
		
	}
	public void removeElement(Sprite element)
	{
		elements.remove(element);
	}
	
	public void createButtons(Driver driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
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
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Sprite element : elements)
		{
			element.draw(g);
		}
    }
	
}
