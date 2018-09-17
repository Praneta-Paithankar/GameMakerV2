package com.ui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import org.apache.log4j.Logger;

import com.behavior.FlowLayoutBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class TimerPanel extends AbstractPanel implements Element {
	protected Logger log = Logger.getLogger(TimerPanel.class);
	private ArrayList<Element> components;

	public TimerPanel() {

		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_WIDTH);

        components = new ArrayList<>();
	}

	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Element component : components)
		{
			component.draw(g);

		}
		
	}

	@Override
	public void draw(Graphics g) {
		repaint();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		for(Element component : components) {
			component.reset();
		}
	}

	@Override
	public void addComponent(Element e) {
		components.add(e);
		add((JComponent)e);
	}

	@Override
	public void removeComponent(Element e) {
		components.remove(e);		
	}
}
