package com.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.component.CircularSprite;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.infrastruture.Constants;

public class GameObject {

	String element;  // String denoting SpriteType
	int elementX; // String denoting X coordinate of Sprite obtained from MakerEngine
	int elementY; // String denoting Y coordinate of Sprite obtained from MakerEngine
	
//	TODO: Add image path as parameter to constructor
	public SpriteElement spriteDecoder(String element, int elementX, int elementY) throws IOException { 
		this.elementX = elementX;
		this.elementY = elementY;
		switch(element) {
		
		case "Ball":
			return spriteCreator("circular",Constants.BALL_IMAGE,Constants.BALL_RADIUS);
			
		case "Brick":
			return spriteCreator("rectangular",Constants.BRICK_IMAGE, Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
	
		case "Paddle":
			return spriteCreator("rectangular",Constants.PADDLE_IMAGE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);	
			
		}
		return null;
	}
	
	
	public SpriteElement spriteCreator(String type, String imagePath, int radius) throws IOException {
		int centerX = elementX+radius;
		int centerY = elementY+radius;
		CircularSprite sprite = new CircularSprite(imagePath, centerX, centerY,radius*2,radius*2 ,Constants.X_Velocity,Constants.Y_Velocity,radius);
		return sprite;
	}
	
	public SpriteElement spriteCreator(String type, String imagePath, int dimensionOne, int dimensionTwo) throws IOException {
		RectangularSprite sprite = new RectangularSprite(imagePath,elementX, elementY,dimensionOne, dimensionTwo,0,0);
		return sprite;
	}
		
}