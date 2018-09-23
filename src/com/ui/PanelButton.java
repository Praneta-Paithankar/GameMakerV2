package com.ui;

import java.awt.Dimension;

import javax.swing.JButton;

import com.controller.GameDriver;
import com.controller.GameMakerController;

@SuppressWarnings("serial")
public class PanelButton extends JButton {
	private int width = 100;
	private int height = 30;
	public PanelButton(String name, String command, GameDriver driver) {
		setText(name);
		setActionCommand(command);
		addActionListener(driver);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}
	
	public PanelButton(String name, String command) {
		setText(name);
		setActionCommand(command);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}
}
