package com.ui;

import java.awt.Dimension;

import javax.swing.JButton;

import com.controller.GameController;

@SuppressWarnings("serial")
public class ControlPanelButton extends JButton {
	private int width = 100;
	private int height = 30;
	public ControlPanelButton(String name, String command, GameController driver) {
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
}
