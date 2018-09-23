package com.behavior;

import java.awt.Dimension;
import java.awt.FlowLayout;

import com.infrastruture.AbstractPanel;
import com.infrastruture.LayoutBehavior;

public class NullLayoutBehavior implements LayoutBehavior{

	@Override
	public void updateLayoutBehavior(AbstractPanel abstractPanel, int width, int height) {
		abstractPanel.setLayout(null);
		abstractPanel.setPreferredSize(new Dimension(width, height));
		
	}

}
