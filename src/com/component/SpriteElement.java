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
	private int width;
	private int height;
	private int imageType;
	private boolean isShooter;
	private int gameEndDependency;
	private int counterInterval;
	private int counter;
	public SpriteElement(String image, int elementX, int elementY,int width,int height,int velX, int velY, String spriteId, String category,Color color, int gameEndDependency, int counterInterval) throws IOException {
		this.imagePath = image;
		this.imageIcon = null;
		this.width = width;
		this.height = height;
		this.firstInstanceOfVelX =this.XVel =velX;
		this.firstInstanceOfVelY = this.YVel = velY;
		this.visible = true;
		this.firstInstanceOfX = this.elementX = elementX;
		this.firstInstanceOfY = this.elementY = elementY;
		this.spriteId = spriteId;
		this.category = category;
		this.color = color;
		this.gameEndDependency=gameEndDependency;
		this.counterInterval=counterInterval;
		this.counter=0;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public SpriteElement(SpriteElement element) throws IOException {
		this(element.imagePath,element.elementX,element.elementY,element.width,element.height, element.XVel, element.YVel, element.spriteId,element.category,element.color,element.gameEndDependency,element.getCounterInterval());
	}
	public int getCounterInterval() {
		return counterInterval;
	}
	public void setCounterInterval(int counterInterval) {
		this.counterInterval = counterInterval;
	}
	public int getGameEndDependency() {
		return gameEndDependency;
	}
	public void setGameEndDependency(int gameEndDependency) {
		this.gameEndDependency = gameEndDependency;
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
		if (imageIcon == null && imagePath != null && imagePath.length()>0 ) {
			try {
				this.imageIcon = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.imageIcon = resize(imageIcon, width, height);
		}

		if(isVisible()) {
			g.drawImage(this.imageIcon, getElementX(), getElementY(), null);
			
		}
	}
	
	public SpriteElement shoot(SpriteElement shootingObject) throws IOException {
		int signYVel = shootingObject.getYVel() >= 0 ? 1 : -1;
		
//		System.out.println(toString() + " Sign y = " + signYVel);
		if(shootingObject instanceof CircularSprite) {
			CircularSprite circularSprite= new CircularSprite((CircularSprite)shootingObject);
			circularSprite.setElementX(this.getElementX() + this.getWidth()/2 - 5);
			circularSprite.setElementY(this.getElementY() + (signYVel* this.getHeight()));
			return circularSprite;

		} else {
			RectangularSprite rectangularSprite=new RectangularSprite((RectangularSprite)shootingObject);
			rectangularSprite.setElementX(this.getElementX()+this.getWidth()/2 - 5);
			rectangularSprite.setElementY(this.getElementY() + (signYVel* this.getHeight()));
			return rectangularSprite;
		}
		
	}
		
	public boolean intersects(SpriteElement c) {

		java.awt.Rectangle one = new java.awt.Rectangle(elementX ,elementY, width, height);
		java.awt.Rectangle two = new java.awt.Rectangle(c.getElementX(), c.getElementY(), c.getWidth(), c.getHeight());

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
	public String toString() {
		return "ID:"+getSpriteId() + " Category: "+getCategory();
	}

	
}
