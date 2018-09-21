package com.helper;

import com.component.SpriteElement;

public class ActionLink {

	
	String action;
	SpriteElement sprite;
	
	ActionLink(SpriteElement sprite, String action){
		this.sprite = sprite;
		this.action = action;
	}
}
