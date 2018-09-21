/**
 * 
 */
package com.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.infrastruture.Element;

public abstract class SpriteElement{
	
	private BufferedImage image;
	private int radius;
	private int width;
	private int height;
	private int elementX;
	private int elementY;
	
	
	public SpriteElement(BufferedImage image, int elementX, int elementY, int radius) {
		// TODO Auto-generated constructor stub
		this.image = image;
		this.elementX = elementX;
		this.elementY = elementY;
		this.radius = radius;
		this.width = radius *2;
		this.height = radius *2;
	}

	public SpriteElement(BufferedImage image2, int elementX2, int elementY2, int width2, int height2) {
		// TODO Auto-generated constructor stub
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public BufferedImage getImg() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getElementX() {
		return elementX;
	}

	public void setElementX(int elementX) {
		this.elementX = elementX;
	}

	public int getElementY() {
		return elementY;
	}

	public void setElementY(int elementY) {
		this.elementY = elementY;
	}

	public void draw(Graphics2D g) {
		 Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        	g = resized.createGraphics();
	        g.drawImage(tmp, elementX, elementY, null);
	        g.dispose();
	}
	


}
