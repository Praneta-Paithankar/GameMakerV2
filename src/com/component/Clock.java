package com.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.apache.log4j.Logger;
import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.infrastruture.*;


public class Clock implements Element{
	protected Logger log = Logger.getLogger(Clock.class);
	private long milisecondsElapsed;
	private JsonObject jsonObject;
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
		g.drawRect(0, 150, 250, 100);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		String time = getTime();
		g.drawString(time, 15, 200);
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
	public void addComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonObject save() {
		jsonObject = new JsonObject();
		try {
			jsonObject.put("Clock", this.getMilisecondsElapsed());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public int load(Object object) {
		// TODO Auto-generated method stub
			jsonObject = (JsonObject) object;
			this.setMilisecondsElapsed((jsonObject.getLong("Clock")));
		return 1;
	}
}