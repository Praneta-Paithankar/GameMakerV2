/**
 *This panel holds all the graphic objects like brick, ball and paddle*/
package com.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.behavior.FlowLayoutBehavior;
import com.component.SpriteElement;
import com.controller.GameDriver;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class GamePanel extends AbstractPanel implements Element, Serializable {
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private BufferedImage image;
	private ArrayList<SpriteElement> elements;
	private GameDriver driver;
	private SpriteElement spriteElement;
	
	public GamePanel()
	{
		log.info("Initializing GamePanel");
	    elements = new ArrayList<>();
        setLayout();
	}

	
	
	public void setLayout() {
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT);
	}
	
	private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
	public ArrayList<SpriteElement> getElements(){
		return elements;
	}

	public void setElements(ArrayList<SpriteElement> elements) {
		this.elements = elements;
	}

	@Override
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		if (image != null) {
	        g.drawImage(image, 0, 0, this);
	    }
		for(SpriteElement element:elements)
		{
			element.draw(g);
		}
	}

	@Override
	public void draw(Graphics g) {
		repaint();
	}

	@Override
	public void reset() {
		for(SpriteElement element:elements) {
			element.reset();
		}
	}

	public void addComponent(SpriteElement e) {
		elements.add(e);
	}
	

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}



	@Override
	public void save(ObjectOutputStream op) {
		for(SpriteElement element:elements) {
			element.save(op);
		}
	}

	@Override
	public Element load(ObjectInputStream ip) {
		ArrayList<SpriteElement> loadComponents = new ArrayList<>();
		for(SpriteElement element:elements) {
			loadComponents.add(element.load(ip));
		}
		elements.clear();
		elements.addAll(loadComponents);
		return null;
	}

	public void addMouseEvent(GameDriver driver) {
		this.driver = driver;
		this.addMouseListener(driver);
	}


	@Override
	public void addComponent(Element e) {
		
	}

	public void setImage(String path)
	{
		try {
			image = ImageIO.read(new File(path));
			image = resize(image, Constants.BOARD_PANEL_WIDTH, Constants.BOARD_PANEL_HEIGHT);
			draw(null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public SpriteElement getSpriteElement() {
		return spriteElement;
	}

	public void setSpriteElement(SpriteElement spriteElement) {
		this.spriteElement = spriteElement;
	}



	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
