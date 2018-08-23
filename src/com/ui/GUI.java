package com.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.breakout.GamePanel;
import com.component.Ball;
import com.component.ClockObserver;
import com.dimension.Circle;
import com.dimension.Coordinates;
import com.timer.BreakoutTimer;

public class GUI extends JFrame implements ClockObserver{
	
	private Coordinates timerLocation;
	private Color color;
	private String time;
	
	static GamePanel boardPanel;
	static JLabel label;
	static Ball b;
	static BreakoutTimer timer;
	
	public GUI(Coordinates timerLocation,Color color)
	{
		this.timerLocation= timerLocation;
		this.color= color;
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
        
        
	    b = new Ball(new Circle(20, 60, 90), new Coordinates(20, 40), Color.CYAN);
		boardPanel.addElement(b);
		
		label = new JLabel("Check",SwingConstants.LEFT);
		label.setSize(100,100);
		panel1.add(label);
		
		//add(boardPanel);
		mainPanel.add(panel1);
        mainPanel.add(boardPanel);

		add(mainPanel);
		
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//pack();
		
		
	}
	
	public static void main(String[] args) {
		
		// Panel main
		//add board
		//add clock panel
		Coordinates c =new Coordinates(20, 50);
	    JFrame gui = new GUI(c, Color.BLACK);
		timer.startTimer();
	    gui.setVisible(true);
		
	}
}
