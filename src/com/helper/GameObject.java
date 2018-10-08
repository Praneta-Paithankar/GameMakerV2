package com.helper;

import java.awt.Color;
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
import com.ui.CreateSpriteRequest;

public class GameObject {
	
	public SpriteElement spriteDecoder(CreateSpriteRequest createSpriteRequest) throws IOException { 
		
		switch(createSpriteRequest.getElementName()) {
		
		case "Circle":
			return new CircularSprite(createSpriteRequest.getImagePath(), createSpriteRequest.getXlocation(), createSpriteRequest.getYlocation(), 
					createSpriteRequest.getWidth(), createSpriteRequest.getHeight(), createSpriteRequest.getXVel(), 
					createSpriteRequest.getYVel(), createSpriteRequest.getSpriteId(), createSpriteRequest.getCategory(), createSpriteRequest.getColor(),createSpriteRequest.getGameEndDependency(),createSpriteRequest.getCounterInterval());
			
		case "Rectangle":
			return new RectangularSprite(createSpriteRequest.getImagePath(), createSpriteRequest.getXlocation(), createSpriteRequest.getYlocation(), 
					createSpriteRequest.getWidth(), createSpriteRequest.getHeight(), createSpriteRequest.getXVel(), 
					createSpriteRequest.getYVel(), createSpriteRequest.getSpriteId(), createSpriteRequest.getCategory(), createSpriteRequest.getColor(),createSpriteRequest.getGameEndDependency(),createSpriteRequest.getCounterInterval());
				
		}
		return null;
	}
	
}
