package com.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

public class RectangularSprite extends SpriteElement implements Serializable{
	
	
	private int firstInstanceOfWidth;
	private int firstInstanceOfHeight;

	public RectangularSprite(String image, int elementX, int elementY, int width, int height,int velX, int velY, String spriteId, String category,Color color, int gameEndDependency, int counterInterval) throws IOException {
		super(image,elementX,elementY,width,height,velX,velY, spriteId, category,color, gameEndDependency,counterInterval);
		this.firstInstanceOfWidth = width;
		this.firstInstanceOfHeight = height;
	}
	public RectangularSprite(RectangularSprite rectangularSprite) throws IOException {
		this(rectangularSprite.getImagePath(),rectangularSprite.getElementX(),rectangularSprite.getElementY(),rectangularSprite.getWidth(),rectangularSprite.getHeight(),rectangularSprite.getXVel(),rectangularSprite.getYVel(),rectangularSprite.getSpriteId(),rectangularSprite.getCategory(),rectangularSprite.getColor(),rectangularSprite.getGameEndDependency(),rectangularSprite.getCounterInterval());
	}
	
	public void reset() {
		super.reset();
		this.setWidth(firstInstanceOfWidth);
		this.setHeight(firstInstanceOfHeight);
	}

	public void draw(Graphics g) {
		//logger.debug("rectangle.isVisible() = " + isVisible());
		if (isVisible()) {
			if(getImagePath()!= null && getImagePath().length()!=0) {
					super.draw(g);
				}else {
					Graphics2D g2 = (Graphics2D) g;
					g2.setPaint(getColor());
					g2.fill(new Rectangle2D.Double(getElementX(), getElementY(), getWidth(), getHeight()));
				}

			}
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
