package com.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.infrastruture.Constants;
import com.infrastruture.Observer;
import com.infrastruture.Sprite;


public class Clock implements Observer, Sprite{


	private int minutes;
	private int seconds;
	private int count;


	public Clock(Color fillColor, int x, int y, int width, int height) {
	
			count = 1000/Constants.TIMER_COUNT; //number of frames that should be skipped, to update a second.

		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		//		
		// TODO center box around the time 
//		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawRect(0, 0, 250, 250);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString((minutes+" : "+ seconds), 0+ 5, 0 +50);
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(count>0) {
			count--;
		}
		else {
			secondPerformed();
			count = 1000/Constants.TIMER_COUNT;
		}
	}

	public void secondPerformed() {
		// TODO Auto-generated method stub
		if(seconds == 59) {seconds =0; minutes++;}
		else if(seconds <60) seconds++;
	}


}