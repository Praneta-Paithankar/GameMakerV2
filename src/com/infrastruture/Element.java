package com.infrastruture;

import java.awt.Graphics;

import org.json.simple.*;;

public interface Element {
	 void draw(Graphics g);
	 void reset();  
	 void addComponent(Element e);
	 void removeComponent(Element e);
	 public JsonObject save();
	 public int load(Object object);
}
