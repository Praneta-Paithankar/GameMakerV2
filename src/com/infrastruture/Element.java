package com.infrastruture;

import java.awt.Graphics;

import org.json.simple.*;;

public interface Element {
	 void draw(Graphics g);
	 void reset();  
	 void addComponent(Element e);
	 void removeComponent(Element e);
	 void load();
	 JsonObject save(); 
}
