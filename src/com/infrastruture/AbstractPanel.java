package com.infrastruture;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {
	LayoutBehavior layoutBehavior;
	
	public void setLayoutBehavior(LayoutBehavior layoutBehavior) {
		this.layoutBehavior = layoutBehavior; 
	}
	
	public void performUpdateLayout(AbstractPanel abstractPanel, int width, int height) {
		layoutBehavior.updateLayoutBehavior(abstractPanel, width, height);
	}
}
