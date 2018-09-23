package com.component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class RectangularSprite extends SpriteElement implements Serializable{
	
	String image;
	int elementX; 
	int elementY;
	int width;
	int height;
	
	String firstInstanceOfImage;
	int firstInstanceOfX;
	int firstInstanceOfY;
	int firstInstanceOfWidth;
	int firstInstanceOfHeight;

	public RectangularSprite(String image, int elementX, int elementY, int width, int height) throws IOException {
		// TODO Auto-generated constructor stub
		super(image,elementX,elementY,width,height);
		this.firstInstanceOfImage = this.image = image;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.firstInstanceOfWidth = this.width = width;
		this.firstInstanceOfHeight = this.height = height;
	}

	
	public void reset() {
		super.reset();
//		this.setElementX(firstInstanceOfX);
//		this.setElementY(firstInstanceOfY);
//		this.setWidth(firstInstanceOfWidth);
//		this.setHeight(firstInstanceOfHeight);
//		this.setImage(firstInstanceOfImage);
	}
	
}
