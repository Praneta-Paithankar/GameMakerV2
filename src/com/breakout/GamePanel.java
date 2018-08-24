package com.breakout;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.component.Element;

public class GamePanel extends JPanel {
	
	private ArrayList<Element> elements;
	
	public GamePanel()
	{
	    elements = new ArrayList<Element>();
	    
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public void addElement(Element element)
	{
		elements.add(element);
		
	}
	public void removeElement(Element element)
	{
		elements.remove(element);
	}
	@Override
	public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		for(Element element : elements)
		{
			element.draw(g);
		}
    }
	public void actElement()
	{
		for(Element element : elements)
		{
			element.enact();
		} 
	}
	
}
