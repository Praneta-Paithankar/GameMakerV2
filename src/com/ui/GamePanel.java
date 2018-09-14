package com.ui;

import java.awt.Graphics;
import com.image.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.json.JSONObject;

import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Element {
	
	private BufferedImage image;
	private ArrayList<Element> elements;

	
	public GamePanel()
	{
	    elements = new ArrayList<Element>();
        try {
            image = ImageIO.read(new File("./src/com/image/nature.jpg"));
            image = resize(image, Constants.BOARD_PANEL_HEIGHT, Constants.BOARD_PANEL_WIDTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setLayout();
	}
	
	public void setLayout() {
		
		setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    setMaximumSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
		setBackground(Color.black);
		setMaximumSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	}
	private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
	public ArrayList<Element> getElements(){
		return elements;
	}
	public void addElement(Element element){
		elements.add(element);
		
	}
	public void removeElement(Element element){
		elements.remove(element);
	}
	

	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (image != null) {
	        g.drawImage(image, 0, 0, this);
	    }
		for(Element element : elements)
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
		for(Element element : elements) {
			element.reset();
		}
	}

	public void addComponent(Element e) {
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

}
