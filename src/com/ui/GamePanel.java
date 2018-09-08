package com.ui;

import java.awt.Graphics;
import com.image.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.infrastruture.Constants;
import com.infrastruture.Sprite;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private BufferedImage image;
	private ArrayList<Sprite> elements;
	
	public GamePanel()
	{
	    elements = new ArrayList<Sprite>();
        try {
            image = ImageIO.read(new File("./src/com/image/nature.jpg"));
            image = resize(image, Constants.BOARD_PANEL_HEIGHT, Constants.BOARD_PANEL_WIDTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public ArrayList<Sprite> getElements(){
		return elements;
	}
	
	private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
	public void addElement(Sprite element){
		elements.add(element);
		
	}
	public void removeElement(Sprite element)
	{
		elements.remove(element);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
		
		for(Sprite element : elements)
		{
			element.draw(g);
		}
    }
}
