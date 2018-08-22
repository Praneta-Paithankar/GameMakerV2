package com.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.breakout.GamePanel;
import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinates;

public class GUI extends JFrame{

	public GUI()
	{
		super();
	}
	public static void main(String[] args) {
		
		// Panel main
		//add board
		//add clock panel
		GUI g =new GUI();
		JPanel mainPanel = new JPanel();
		GamePanel boardPanel=new GamePanel();
		
		g.setSize(500,500);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.getContentPane().add(boardPanel);
		g.setVisible(true);
	}
}
