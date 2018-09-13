package com.infrastruture;

import java.awt.Graphics;

import org.json.JSONObject;

public interface Element {
	 public JSONObject save();
	 public void load();
	 void draw(Graphics g);
	 void reset();
	 public void addElement(Element e);
	 public void removeElement(Element e);
}
