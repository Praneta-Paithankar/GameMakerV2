package com.behavior;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import com.infrastruture.AbstractPanel;
import com.infrastruture.LayoutBehavior;

public class BoxLayoutYAxisBehavior implements LayoutBehavior {

	@Override
	public void updateLayoutBehavior(AbstractPanel abstractPanel, int width, int height) {
		
		abstractPanel.setLayout(new BoxLayout(abstractPanel, BoxLayout.Y_AXIS));
		Component[] comp = abstractPanel.getComponents();
	}
}
