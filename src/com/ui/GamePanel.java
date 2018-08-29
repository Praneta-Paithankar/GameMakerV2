package com.ui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.infrastruture.Sprite;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private ArrayList<Sprite> elements;
	
	public GamePanel()
	{
	    elements = new ArrayList<Sprite>();
	}
	
	public ArrayList<Sprite> getElements(){
		return elements;
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
		for(Sprite element : elements)
		{
			element.draw(g);
		}
    }
}
