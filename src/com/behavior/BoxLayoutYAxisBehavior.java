package com.behavior;

import javax.swing.BoxLayout;

import com.infrastruture.AbstractPanel;
import com.infrastruture.LayoutBehavior;

public class BoxLayoutYAxisBehavior implements LayoutBehavior {

	@Override
	public void updateLayoutBehavior(AbstractPanel abstractPanel, int width, int height) {
		
		abstractPanel.setLayout(new BoxLayout(abstractPanel, BoxLayout.Y_AXIS));
	}
}
