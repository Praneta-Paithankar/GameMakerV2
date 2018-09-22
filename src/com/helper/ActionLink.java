package com.helper;

import com.component.SpriteElement;

public class ActionLink {

	
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
