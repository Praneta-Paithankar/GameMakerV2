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

	
	GameObject(String element, int elementX, int elementY){
		this.element = element;
		this.elementX = elementX;
		this.elementY = elementY;
	}
	
	public SpriteElement spriteDecoder(String element) throws IOException {
		
		switch(element) {
		
		case "ball":
			return spriteCreator("circular",Constants.BALL_IMAGE,Constants.BALL_RADIUS);
			
		case "brick":
			return spriteCreator("rectangular",Constants.BRICK_IMAGE, Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
	
		case "paddle":
			return spriteCreator("rectangular",Constants.PADDLE_IMAGE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);	
			
		}
		return null;
		
	}
	
	
	public SpriteElement spriteCreator(String type, String imagePath, int dimensionOne) throws IOException {
		BufferedImage image = ImageIO.read(new File(imagePath));
		int centerX = elementX+dimensionOne;
		int centerY = elementY+dimensionOne;
		CircularSprite sprite = new CircularSprite(image, centerX, centerY, dimensionOne);
		return sprite;
	}
	
	public SpriteElement spriteCreator(String type, String imagePath, int dimensionOne, int dimensionTwo) throws IOException {
		BufferedImage image = ImageIO.read(new File(imagePath));
		RectangularSprite sprite = new RectangularSprite(image,elementX, elementY,dimensionOne, dimensionTwo);
		return sprite;
	}
		
}
