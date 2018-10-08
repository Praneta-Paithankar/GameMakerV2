/**
 *This class contains all the buttons*/
package com.ui;

import java.awt.Graphics;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.behavior.FlowLayoutBehavior;

import com.controller.GameDriver;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class ControlPanel  extends AbstractPanel implements Element {

	private GameDriver driver;

	public ControlPanel() {
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT-Constants.TIMER_PANEL_WIDTH);
	}
	
	public void createButtons(GameDriver driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
	    createPause();
	    createSave();
	    createLoad();
	    createLayout();
	}
	
	public void createReplay() {
		PanelButton replayButton = new PanelButton("Replay", "replay", driver);
		this.add(replayButton);
	}
	
	public void createUndo() {
		PanelButton undoButton = new PanelButton("Undo", "undo", driver);
		this.add(undoButton);

	}
	
	public void createStart() {
		PanelButton startButton = new PanelButton("Start", "start", driver);
		this.add(startButton);
	}
	
	public void createPause() {
		PanelButton pauseButton = new PanelButton("Pause", "pause", driver);
		this.add(pauseButton);
	}

	public void createLayout() {
		PanelButton layoutButton = new PanelButton("Layout", "layout", driver);
		this.add(layoutButton);
	}

	public void createLoad() {
		PanelButton layoutButton = new PanelButton("Load", "load", driver);
		this.add(layoutButton);
	}

	public void createSave() {
		PanelButton layoutButton = new PanelButton("Save", "save", driver);
		this.add(layoutButton);
	}
	@Override
	public void draw(Graphics g) {
		revalidate();
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void addComponent(Element e) {
		

	}

	@Override
	public void removeComponent(Element e) {
		
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
