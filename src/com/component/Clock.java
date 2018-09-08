package com.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.infrastruture.Constants;
import com.infrastruture.Observer;
import com.infrastruture.Sprite;


public class Clock implements Sprite{


	private int minutes;
	private int seconds;


	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public void draw(Graphics g) {
		//		
		// TODO center box around the time 
//		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawRect(0, 150, 250, 100);
//		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString((minutes+" : "+ seconds), 10+ 5, 200);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.minutes = 0;
		this.seconds = 0;
		
	}




}