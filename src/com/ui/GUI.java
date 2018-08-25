package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.driver.Driver;
import com.infrastruture.Constants;

public class GUI extends JFrame {
	
	private GamePanel boardPanel;
	private JLabel label;
	private JLabel exitLabel;
	private Driver driver;
	private JPanel mainPanel;
	private JPanel timerPanel;
	public GUI() {
		super();
	}
	public GUI(GamePanel boardPanel)
	{
		super("Breakout Game");
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
	private void createTimerPanel() {
		
		timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        timerPanel.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        label = new JLabel("0::0::0",SwingConstants.LEFT);
		label.setSize(100,100);
		label.setForeground(Color.WHITE);
		timerPanel.add(label);
		timerPanel.setBackground(Color.black);
		mainPanel.add(timerPanel);
	}
	private void createBoardPanel() {

		boardPanel.setLayout(new GridBagLayout());
        boardPanel.setPreferredSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    boardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    boardPanel.setMaximumSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
		boardPanel.setBackground(Color.black);
		
		exitLabel = new JLabel();
		exitLabel.setForeground(Color.WHITE);
		exitLabel.setAlignmentX(SwingConstants.CENTER);
		exitLabel.setAlignmentY(SwingConstants.CENTER);
		Font font = new Font("Helvetica", Font.BOLD,50);
		
		exitLabel.setFont(font);
		boardPanel.add(exitLabel);
		boardPanel.setMaximumSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
		mainPanel.add(boardPanel);
		
	}
	private void initializeUI() {
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
       
        createTimerPanel();
        createBoardPanel();
        

		add(mainPanel);
		
		mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
		mainPanel.setFocusable(true);
		mainPanel.requestFocusInWindow();
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		
	}
	public void addDriver(Driver driver){
		this.setDriver(driver);
		mainPanel.addKeyListener(driver);
	}
	public void addGameOverPane() {
		exitLabel.setText("Game Over");
		boardPanel.repaint();

	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public GamePanel getBoardPanel() {
		return boardPanel;
	}
	public void setBoardPanel(GamePanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
}
