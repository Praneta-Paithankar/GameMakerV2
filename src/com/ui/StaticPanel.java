/**
 *This class contains timer and control panel*/
package com.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.BoxLayoutYAxisBehavior;
import com.behavior.GridBagLayoutBehavior;
import com.component.Clock;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class StaticPanel extends AbstractPanel implements Element{
	protected static Logger log = Logger.getLogger(StaticPanel.class);
	private JLabel score;
	private ArrayList<Element> elements;
	
	public StaticPanel() {
		setBorder(BorderFactory.createLoweredBevelBorder());
		setLayoutBehavior(new BoxLayoutYAxisBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT);
        elements = new ArrayList<>();
	}
	public ArrayList<Element> getElements(){
		return elements;
	}
	
	public void addComponent(Element e) {
		this.add((AbstractPanel)e);
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

	@Override
	public void draw(Graphics g) {

	for(Element component : elements) {
		component.draw(null);
	}
	}
	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}
	@Override
	public void save(ObjectOutputStream op) {
		for (Element element : elements) {
			element.save(op);
		}
		
	}
	@Override
	public Element load(ObjectInputStream ip) {
		for (Element element : elements) {
			element.load(ip);
		}
		return null;
	}
	
}
