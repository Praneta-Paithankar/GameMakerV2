package com.ui;

import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;

//import org.json.JSONObject;

import org.json.simple.JsonObject;

import com.behavior.BoxLayoutXAxisBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class MainPanel extends AbstractPanel implements Element {

	private ArrayList<Element> elements;

	
	public MainPanel() {
        elements = new ArrayList<>();
		setLayoutBehavior(new BoxLayoutXAxisBehavior());
		performUpdateLayout(this, Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
//		performUpdateLayout(this, Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT);
		setFocusable(true);
		requestFocusInWindow();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Element component : elements)
		{
			component.draw(g);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		repaint();
//		System.out.println("MainPanel::draw");
	}

	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}

	@Override
	public void addComponent(Element e) {
//		System.out.println("Add component in MainPanel (mainPanel)");
		elements.add(e);
		this.add((AbstractPanel)e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
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
	}

	@Override
	public JSONObject save() {
		return null;
	}
	*/
}
