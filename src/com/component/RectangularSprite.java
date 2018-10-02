package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

public class RectangularSprite extends SpriteElement implements Serializable{
	
	private int width;
	private int height;
	private int firstInstanceOfWidth;
	private int firstInstanceOfHeight;

	public RectangularSprite(String image, int elementX, int elementY, int width, int height,int velX, int velY) throws IOException {
		super(image,elementX,elementY,width,height,velX,velY);
		this.firstInstanceOfWidth = this.width = width;
		this.firstInstanceOfHeight = this.height = height;
	}
	public RectangularSprite(RectangularSprite rectangularSprite) throws IOException {
		this(rectangularSprite.getImagePath(),rectangularSprite.getElementX(),rectangularSprite.getElementY(),rectangularSprite.getWidth(),rectangularSprite.getHeight(),rectangularSprite.getXVel(),rectangularSprite.getYVel());
	}
	
	public void reset() {
		super.reset();
		this.setWidth(firstInstanceOfWidth);
		this.setHeight(firstInstanceOfHeight);
	}

	public void draw(Graphics g) {
		//logger.debug("rectangle.isVisible() = " + isVisible());
		if (isVisible()) {
			if(getImagePath().length()>0) {
				super.draw(g);
			}else {
				Graphics2D g2 = (Graphics2D) g;
				g2.setPaint(Color.BLACK);
				g2.fill(new Rectangle2D.Double(getElementX(), getElementY(), width, height));
			}
		}
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

	public int getFirstInstanceOfWidth() {
		return firstInstanceOfWidth;
	}

	public void setFirstInstanceOfWidth(int firstInstanceOfWidth) {
		this.firstInstanceOfWidth = firstInstanceOfWidth;
	}

	public int getFirstInstanceOfHeight() {
		return firstInstanceOfHeight;
	}


	public void setFirstInstanceOfHeight(int firstInstanceOfHeight) {
		this.firstInstanceOfHeight = firstInstanceOfHeight;
	}
	
}