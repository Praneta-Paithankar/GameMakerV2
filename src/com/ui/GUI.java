package com.ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.breakout.GamePanel;
import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinates;

public class GUI extends JFrame{

	public GUI()
	{
		InitializeUI();
		
	}
	private void InitializeUI() {
        JPanel mainPanel = new JPanel();
		GamePanel boardPanel=new GamePanel();
		Ball b = new Ball(new Circle(20, 60, 90), new Coordinates(160, 80), Color.CYAN);
		boardPanel.addElement(b);
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(boardPanel);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		
		// Panel main
		//add board
		//add clock panel
		GUI g =new GUI();
		
	}
}
