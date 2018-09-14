package com.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.json.JSONObject;

import com.infrastruture.Constants;
import com.infrastruture.Observer;
import com.infrastruture.Savable;
import com.infrastruture.Element;


public class Clock implements Element,Savable{

	private long milisecondsElapsed;

	public Clock() {
		milisecondsElapsed = 0;
	}

	public String getTime() {
		if (getSeconds() >= 10) {
			return Integer.toString(getMinutes()) + ":" + Integer.toString(getSeconds());
		} else {
			return Integer.toString(getMinutes()) + ":0" + Integer.toString(getSeconds());
		}
	}
	
	@Override
	public void draw(Graphics g) {
				
		// TODO center box around the time 
//		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawRect(0, 150, 250, 100);
//		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		String time = getTime();
		g.drawString(time, 10+ 5, 200);
	}

	@Override
	public void reset() {
		milisecondsElapsed = 0;	
	}
	
	public long getMilisecondsElapsed() {
		return milisecondsElapsed;
	}

	public void setMilisecondsElapsed(long milisecondsElapsed) {
		this.milisecondsElapsed = milisecondsElapsed;
	}

	private int getMinutes() {
		return (int) (milisecondsElapsed / 60000);
	}

	private int getSeconds() {
		return (int) ((milisecondsElapsed / 1000) % 60);
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
	public void addComponent(Savable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Savable e) {
		// TODO Auto-generated method stub
		
	}

}