package com.component;

import java.awt.image.BufferedImage;

public class CircularSprite extends SpriteElement {

	BufferedImage image;
	int elementX; 
	int elementY;
	int radius;
	
	BufferedImage firstInstanceOfImage;
	int firstInstanceOfX;
	int firstInstanceOfY;
	int firstInstanceOfRadius;
	
	
	public CircularSprite(BufferedImage image, int elementX, int elementY, int radius) {
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
