package com.component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class CircularSprite extends SpriteElement implements Serializable {

	String image;
	int elementX; 
	int elementY;
	int radius;
	
	String firstInstanceOfImage;
	int firstInstanceOfX;
	int firstInstanceOfY;
	int firstInstanceOfRadius;
	
	public CircularSprite(String image, int elementX, int elementY, int radius) throws IOException {
		super(image,elementX,elementY,radius);
		this.firstInstanceOfImage = this.image = image;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.firstInstanceOfRadius = this.radius = radius;
	}
	
	public void reset() {
		super.reset();
	}

}
