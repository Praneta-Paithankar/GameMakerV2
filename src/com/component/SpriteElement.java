/**
 * 
 */
package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.controller.GameDriver;
import com.infrastruture.Constants;

public abstract class SpriteElement implements Serializable{
	protected static Logger log = Logger.getLogger(GameDriver.class);
	private int XVel;
	private int YVel;
	private int elementX;
	private int elementY;
	private boolean visible;
	
	private String spriteId;
	private String category;
	private Color color;
	
	private int firstInstanceOfX;
	private int firstInstanceOfY;
	private int firstInstanceOfVelX;
	private int firstInstanceOfVelY;
	private transient BufferedImage imageIcon;
	private String imagePath;
	private int imageWidth;
	private int imageHeight;
	private int imageType;
	private boolean isShooter;
	
	public SpriteElement(String image, int elementX, int elementY,int width,int height,int velX, int velY, String spriteId, String category,Color color) throws IOException {
		this.imagePath = image;
		this.imageIcon = ImageIO.read(new File(image));
		this.imageWidth = width;
		this.imageHeight = height;
		this.imageIcon = resize(imageIcon, imageWidth, imageHeight);
		this.firstInstanceOfVelX =this.XVel =velX;
		this.firstInstanceOfVelY = this.YVel = velY;
		this.visible = true;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.spriteId = spriteId;
		this.category = category;
		this.color = color;

	}
	public SpriteElement(SpriteElement element) throws IOException {
		this(element.imagePath,element.elementX,element.elementY,element.imageWidth,element.imageHeight, element.XVel, element.YVel, element.spriteId,element.category,element.color);
	}
	public BufferedImage resize(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		this.setElementX(firstInstanceOfX);
		this.setElementY(firstInstanceOfY);
		this.XVel = firstInstanceOfVelX;
		this.YVel = firstInstanceOfVelY;
		this.visible = true;

	}
	
	public void draw(Graphics g) {
		if (imageIcon == null) {
			try {
				this.imageIcon = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.imageIcon = resize(imageIcon, imageWidth, imageHeight);
		}

		if(isVisible()) {
			g.drawImage(this.imageIcon, getElementX(), getElementY(), null);
		}
	}
	
	public SpriteElement shoot(SpriteElement shootingObject) throws IOException {
		if(shootingObject instanceof CircularSprite) {
			return new CircularSprite((CircularSprite)shootingObject);
//			return new CircularSprite(shootingObject.imagePath,shootingObject.elementX,shootingObject.elementY,shootingObject.imageWidth, shootingObject.imageHeight,shootingObject.XVel, shootingObject.YVel,((CircularSprite) shootingObject).getRadius());

		} else {
			return new RectangularSprite((RectangularSprite)shootingObject);
		}
	}
		
	public boolean intersects(SpriteElement c) {

		java.awt.Rectangle one = new java.awt.Rectangle(elementX ,elementY, imageWidth, imageHeight);
		java.awt.Rectangle two = new java.awt.Rectangle(c.getElementX(), c.getElementY(), c.getImageWidth(), c.getImageHeight());

		return one.intersects(two);

	}
	public int getImageType() {
		return imageType;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

	public boolean isShooter() {
		return isShooter;
	}

	public void setShooter(boolean isShooter) {
		this.isShooter = isShooter;
	}

	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub
		try {
			op.writeObject(this);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public SpriteElement load(ObjectInputStream ip) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getFirstInstanceOfX() {
		return firstInstanceOfX;
	}

	public void setFirstInstanceOfX(int firstInstanceOfX) {
		this.firstInstanceOfX = firstInstanceOfX;
	}

	public int getFirstInstanceOfY() {
		return firstInstanceOfY;
	}

	public void setFirstInstanceOfY(int firstInstanceOfY) {
		this.firstInstanceOfY = firstInstanceOfY;
	}

	public int getFirstInstanceOfVelX() {
		return firstInstanceOfVelX;
	}

	public void setFirstInstanceOfVelX(int firstInstanceOfVelX) {
		this.firstInstanceOfVelX = firstInstanceOfVelX;
	}

	public int getFirstInstanceOfVelY() {
		return firstInstanceOfVelY;
	}

	public void setFirstInstanceOfVelY(int firstInstanceOfVelY) {
		this.firstInstanceOfVelY = firstInstanceOfVelY;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String image) {
		this.imagePath = image;
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

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getSpriteId() {
		return spriteId;
	}
	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
