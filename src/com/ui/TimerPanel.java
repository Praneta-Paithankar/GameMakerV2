package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.json.simple.JsonObject;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.FlowLayoutBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class TimerPanel extends AbstractPanel implements Element {
	protected Logger log = Logger.getLogger(TimerPanel.class);
	private ArrayList<Element> components;
	private JsonObject jsonObject;

	public TimerPanel() {
//		setBorder(BorderFactory.createLineBorder(Color.blue));
		
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_WIDTH);

        components = new ArrayList<>();
	}

	
	@Override
	public void paintComponent(Graphics g){
//		System.out.println("StaticPanel::paintComponent");

		super.paintComponent(g);
		for(Element component : components)
		{
//			double X = this.getComponent(0).getX();
//			double Y = this.getComponent(0).getY();
//			System.out.println("X: " + X + " Y: " + Y);
//			System.out.println("Width: " + Integer.toString(this.getComponent(0).getWidth()));
//			System.out.println("Height: " + Integer.toString(this.getComponent(0).getHeight()));
//			Graphics2D g2d = (Graphics2D) g.create();
//			g2d.translate(X, Y); 
//			component.draw(g2d);
			component.draw(g);
//			g2d.dispose();
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
		System.out.println("Add component in TimerPanel");
		components.add(e);
		add((JComponent)e);
	}

	@Override
	public void removeComponent(Element e) {
		components.remove(e);		
	}


	@Override
	public JsonObject save() {
		// TODO Auto-generated method stub
		jsonObject = new JsonObject();
		try {
			for (Element element : components) {
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
		for (Element element : components) {
			element.load(jsonObject.get(element.getClass().toString()));
		}
		return 1;
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
