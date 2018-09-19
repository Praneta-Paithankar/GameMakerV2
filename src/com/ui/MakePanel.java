package com.ui;

import java.awt.Graphics;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JComponent;

import org.apache.log4j.Logger;

import com.behavior.FlowLayoutBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private List<String> actions;
	private List<String> events;
	
	public MakePanel() {
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		createButtons();
	}
	
	public void createButtons() {
		this.add(createMakeButton());
		this.add(createPlayButton());
	}
	
	public PanelButton createMakeButton() {
		return new PanelButton("Make", "make");
	}
	
	public PanelButton createPlayButton() {
		return new PanelButton("Play", "play");
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Element load(ObjectInputStream ip) {
		// TODO Auto-generated method stub
		return null;
	}

}
