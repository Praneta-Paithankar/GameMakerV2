package com.infrastruture;

import org.json.JSONObject;

public interface Savable {
	public JSONObject save();
	 public void load();
	 
	 public void addComponent(Savable s);
	 public void removeComponent(Savable s);
}
