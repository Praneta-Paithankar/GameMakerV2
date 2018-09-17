package com.infrastruture;

import java.awt.Graphics;


public interface Element {
	 void draw(Graphics g);
	 void reset();  
	 void addComponent(Element e);
	 void removeComponent(Element e);
	 
}
