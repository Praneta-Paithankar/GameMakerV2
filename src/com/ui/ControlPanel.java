package com.ui;

import java.awt.Graphics;

//import org.json.JSONObject;
import org.json.simple.JsonObject;

import com.behavior.FlowLayoutBehavior;
import com.controller.GameController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class ControlPanel  extends AbstractPanel implements Element {

	private GameController driver;

	public ControlPanel() {
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT-Constants.TIMER_PANEL_WIDTH);
	}
	
	public void createButtons(GameController driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
	    createPause();
//	    this.add(new JPanel());
//	    this.add(new JPanel());
	    
	    createSave();
	    createLoad();
	    createLayout();
	}
	
	public void createReplay() {
		ControlPanelButton replayButton = new ControlPanelButton("Replay", "replay", driver);

//		this.add(Box.createRigidArea(new Dimension(30,30)));
		this.add(replayButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createUndo() {
		ControlPanelButton undoButton = new ControlPanelButton("Undo", "undo", driver);
	
//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(undoButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createStart() {
		ControlPanelButton startButton = new ControlPanelButton("Start", "start", driver);

//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(startButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createPause() {
		ControlPanelButton pauseButton = new ControlPanelButton("Pause", "pause", driver);

//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(pauseButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}

	public void createLayout() {
		ControlPanelButton layoutButton = new ControlPanelButton("Layout", "layout", driver);

//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(layoutButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}

	public void createLoad() {
		ControlPanelButton layoutButton = new ControlPanelButton("Load", "load", driver);

//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(layoutButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
	}

	public void createSave() {
		ControlPanelButton layoutButton = new ControlPanelButton("Save", "save", driver);

//		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(layoutButton);
//		this.add(Box.createRigidArea(new Dimension(5,5)));
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
		System.out.println("Add component in ControlPanel");

	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonObject save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int load(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}
