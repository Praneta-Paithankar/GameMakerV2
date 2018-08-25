package com.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.common.Constants;
import com.driver.Driver;

public class GUI extends JFrame {
	
	private GamePanel boardPanel;
	private JLabel label;
	private Driver driver;
	private JPanel mainPanel;
	
	public GUI() {
		super();
	}
	public GUI(GamePanel boardPanel)
	{
		this.boardPanel = boardPanel;
		initializeUI();
	}

	public void update(long milliseconds) {
		int seconds = (int) (milliseconds / 1000) % 60 ;
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
		 
		String time = String.format("%d::%d::%d", hours,minutes,seconds);
		label.setText(time);
		boardPanel.repaint();
	}
	
	private void initializeUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
       
        JPanel panel1 = new JPanel();
      
        panel1.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        panel1.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));

        boardPanel.setPreferredSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    boardPanel.setMaximumSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
		label = new JLabel("0::0::0",SwingConstants.LEFT);
		label.setSize(100,100);
		panel1.add(label);
		
		//add(boardPanel);
		mainPanel.add(panel1);
        mainPanel.add(boardPanel);

		add(mainPanel);
		
		mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	    boardPanel.setMaximumSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	    
		mainPanel.setFocusable(true);
		mainPanel.requestFocusInWindow();
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		
	}
	
	public void addDriver(Driver driver)
	{
		this.driver = driver;
		mainPanel.addKeyListener(driver);
	}
	
	public void addGameOverFrame() {
		JLabel label1 = new JLabel("Test");
		label1.setText("Label Text");
	}
}
