package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;

import org.apache.log4j.Logger;

//import org.json.JSONObject;

import org.json.simple.JsonObject;
import javax.swing.BorderFactory;

//import org.json.JSONObject;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.BoxLayoutYAxisBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class MainPanel extends AbstractPanel implements Element {

	private ArrayList<Element> elements;
	private JsonObject jsonObject;
	protected Logger log = Logger.getLogger(MainPanel.class);

	
	public MainPanel() {
//		setBorder(BorderFactory.createLineBorder(Color.black));
        elements = new ArrayList<>();
		setLayoutBehavior(new BoxLayoutXAxisBehavior());
		performUpdateLayout(this, Constants.MAIN_PANEL_WIDTH,Constants.MAIN_PANEL_HEIGHT);
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
		jsonObject = new JsonObject();
		try {
			for (Element element : elements) {
					jsonObject.put(element.getClass().toString(), element.save());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public int load(Object object) {
		// TODO Auto-generated method stub
		jsonObject = (JsonObject) object;
		int brickCount = 0;
		
		for (Element element : elements) {
			if(element.getClass().toString().contains("GamePanel")) {
				brickCount = element.load(jsonObject.get(element.getClass().toString()));
			}else {
				element.load(jsonObject.get(element.getClass().toString()));
			}
		}
		return brickCount;
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
