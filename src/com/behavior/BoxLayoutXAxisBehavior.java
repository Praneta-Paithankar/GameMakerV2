package com.behavior;

import java.awt.Dimension;

import javax.swing.BoxLayout;

import com.infrastruture.AbstractPanel;
import com.infrastruture.LayoutBehavior;

public class BoxLayoutXAxisBehavior implements LayoutBehavior {

	@Override
	public void updateLayoutBehavior(AbstractPanel abstractPanel, int width, int height) {
		abstractPanel.setLayout(new BoxLayout(abstractPanel, BoxLayout.X_AXIS));
		abstractPanel.setPreferredSize(new Dimension(width, height));
	}

}
