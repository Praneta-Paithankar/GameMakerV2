package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class CircularSprite extends SpriteElement implements Serializable {
	
	private int radius;
	private int firstInstanceOfRadius;
	
	public CircularSprite(String image, int elementX, int elementY, int width, int height, int velX, int velY,
			int radius, String spriteId, String category,Color color) throws IOException {
		super(image, elementX, elementY, radius*2, radius*2, velX, velY, spriteId, category,color);
		this.firstInstanceOfRadius = this.radius = radius;
	}
	public CircularSprite(CircularSprite circularSprite) throws IOException {
		this(circularSprite.getImagePath(), circularSprite.getElementX(), circularSprite.getElementY(), circularSprite.getRadius()*2, circularSprite.getRadius()*2, circularSprite.getXVel(), circularSprite.getYVel(),circularSprite.getRadius(),circularSprite.getSpriteId(),circularSprite.getCategory(),circularSprite.getColor());	
	}
	public void draw(Graphics g) {
		//logger.trace("Drawing a circle");
		if (isVisible()) {
			if(getImagePath().length()>0) {
				super.draw(g);
			}else {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Ellipse2D.Double ball = new Ellipse2D.Double(getElementX(), getElementY(), radius*2, radius*2);
				g2.setPaint(Color.black);
				g2.fill(ball);
			}
			
		}
	}
	
	public void reset() {
		super.reset();
		this.setRadius(radius);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getFirstInstanceOfRadius() {
		return firstInstanceOfRadius;
	}

	public void setFirstInstanceOfRadius(int firstInstanceOfRadius) {
		this.firstInstanceOfRadius = firstInstanceOfRadius;
	}
	
	
}
