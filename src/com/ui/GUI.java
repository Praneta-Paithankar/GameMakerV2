package com.ui;

//import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.driver.Driver;
import com.infrastruture.Constants;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	private GamePanel boardPanel;
//	private JLabel label;
	private JLabel exitLabel;
	private Driver driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
//	private JButton button;
	public GUI() {
		super();
	}
	public GUI(GamePanel boardPanel)
	{
		super("Breakout Game");
		this.boardPanel = boardPanel;
		initializeUI();
	}
	
	public StaticPanel getStaticPanel() {
		return (timerPanel);
	}

	public void changeUI(long milliseconds)
	{
		System.out.println(milliseconds);
		boardPanel.repaint();
		timerPanel.updateTime(milliseconds);
	}
//	private void createTimerPanel() {
//		
//		timerPanel = new JPanel();
//        timerPanel.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
//        timerPanel.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
//        label = new JLabel("0::0::0",SwingConstants.LEFT);
//		label.setSize(100,100);
//		label.setForeground(Color.WHITE);
//		Font font = new Font("Helvetica", Font.BOLD,30);
//		label.setFont(font);
//		timerPanel.add(label);
//		timerPanel.setBackground(Color.black);
//	    button = new JButton("press");
//		
//		button.setActionCommand("undo");
//		button.setVisible(true);
//		timerPanel.add(button);
//		mainPanel.add(timerPanel);
//	}

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
       
        timerPanel = new StaticPanel();
        mainPanel.add(timerPanel);
//        createTimerPanel();
        createBoardPanel();
        
		add(mainPanel);
		
		mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
		mainPanel.setFocusable(true);
		mainPanel.requestFocusInWindow();
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
		
	}
	public void removeKeyListner() {
		mainPanel.removeKeyListener(driver);
	}
	public void addDriver(Driver driver){
		this.driver = driver;
		mainPanel.addKeyListener(driver);
		//button.addActionListener(driver);
	}
	public void changeFocus()
	{
		mainPanel.requestFocus();
	}
	public void addGameOverPane() {
		exitLabel.setText("Game Over");
		boardPanel.repaint();
	}
		
}
