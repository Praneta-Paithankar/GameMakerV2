package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.driver.Driver;
import com.infrastruture.Constants;

@SuppressWarnings("serial")
public class StaticPanel extends JPanel{
	private JLabel timerlabel;
	private JLabel score;
	private Driver driver;

	
	public StaticPanel() {
		this.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        createTimerLabel();
	}

	public void createButtons(Driver driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
	}
	public void createTimerLabel() {
        timerlabel = new JLabel("0::0::0",SwingConstants.LEFT);
		timerlabel.setSize(100,100);
		timerlabel.setForeground(Color.WHITE);
		Font font = new Font("Helvetica", Font.BOLD,30);
		timerlabel.setFont(font);
		this.add(timerlabel);
		this.setBackground(Color.black);
	}
	
	public void updateTime(long milliseconds) {
		int seconds = (int) (milliseconds/60)  ;
		int minutes = (int) (seconds/60);
		int hours   = (int) (minutes/60);
		 
		String time = String.format("%d::%d::%d", hours,minutes,seconds);
		timerlabel.setText(time);
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
	
}
