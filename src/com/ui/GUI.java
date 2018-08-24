package com.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.breakout.GamePanel;
import com.component.Ball;
import com.component.ClockObserver;
import com.dimension.Circle;
import com.dimension.Coordinates;
import com.timer.BreakoutTimer;
import com.common.Constants;

public class GUI extends JFrame implements ClockObserver{
	
	private String time;
	
	static GamePanel boardPanel;
	static JLabel label;
	static Ball b;
	static BreakoutTimer timer;
	
	public GUI()
	{
		InitializeUI();
		timer = new BreakoutTimer();
		timer.addObserver(this);
	}

	@Override
	public void update(long milliseconds) {
		int seconds = (int) (milliseconds / 1000) % 60 ;
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
		 
		time = String.format("%d::%d::%d", hours,minutes,seconds);
		label.setText(time);
		boardPanel.actElement();
		boardPanel.repaint();
	}
	
	private void InitializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
       
        JPanel panel1 = new JPanel();
        boardPanel=new GamePanel();
        panel1.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        
        boardPanel.setPreferredSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    boardPanel.setMaximumSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    
        b = new Ball(new Circle(20, 60, 90), new Coordinates(20, 40), Color.CYAN);
		boardPanel.addElement(b);
		
		label = new JLabel("Check",SwingConstants.LEFT);
		label.setSize(100,100);
		panel1.add(label);
		
		//add(boardPanel);
		mainPanel.add(panel1);
        mainPanel.add(boardPanel);

		add(mainPanel);
		
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		
	}
	
	public static void main(String[] args) {
		
	    JFrame gui = new GUI();
		timer.startTimer();
	    gui.setVisible(true);
		
	}
}
