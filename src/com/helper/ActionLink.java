package com.helper;

import java.io.Serializable;

import com.component.SpriteElement;

public class ActionLink implements Serializable{

	
	String action;
	SpriteElement sprite;
	
	public ActionLink() {
		
	}
	
	public String getAction() {
		return action;
	}

	public SpriteElement getSprite() {
		return sprite;
	}
	
	public ActionLink(SpriteElement sprite, String action){
		this.sprite = sprite;
		this.action = action;
	}
}
