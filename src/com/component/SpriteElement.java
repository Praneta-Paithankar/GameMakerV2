/**
 * 
 */
package com.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.infrastruture.Constants;

public abstract class SpriteElement{

	private BufferedImage image;
	private int radius;
	private int width;
	private int height;
	private int XVel;
	private int YVel;
	private int elementX;
	private int elementY;
	private boolean visible;
	
	private int firstInstanceOfX;
	private int firstInstanceOfY;
	private int firstInstanceOfRadius;
	private int firstInstanceOfWidth;
	private int firstInstanceOfHeight;

	public SpriteElement(BufferedImage image, int elementX, int elementY, int radius) {
		this.image = image;
		this.width = radius *2;
		this.height = radius *2;
		this.XVel = Constants.X_Velocity;
		this.YVel = Constants.Y_Velocity;
		this.image = resize(image, width, height);
		this.visible = true;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.firstInstanceOfRadius = this.radius = radius;
	}

	public SpriteElement(BufferedImage image, int elementX, int elementY, int width, int height) {
		this.image = image;
		this.elementX = elementX;
		this.elementY = elementY;
		this.width = width;
		this.height = height;
		this.XVel = Constants.X_Velocity;
		this.YVel = Constants.Y_Velocity;
		this.image = resize(image, width, height);
		this.visible = true;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.firstInstanceOfWidth = this.width = width;
		this.firstInstanceOfHeight = this.height = height;
	}

	public int getXVel() {
		return XVel;
	}

	public void setXVel(int xVel) {
		XVel = xVel;
	}

	public int getYVel() {
		return YVel;
	}

	public void setYVel(int yVel) {
		YVel = yVel;
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
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	private BufferedImage resize(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public void draw(Graphics g) {
		if(this.visible) {
			g.drawImage(this.image, elementX, elementY, null);
		}
	}

	public void reset() {
		// TODO Auto-generated method stub
		this.setElementX(firstInstanceOfX);
		this.setElementY(firstInstanceOfY);
		this.setRadius(radius);
		this.setWidth(firstInstanceOfWidth);
		this.setHeight(firstInstanceOfHeight);
		this.XVel = Constants.X_Velocity;
		this.YVel = Constants.Y_Velocity;
		this.visible = true;

	}

	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub

	}

	public SpriteElement load(ObjectInputStream ip) {
		// TODO Auto-generated method stub
		return null;
	}
}
