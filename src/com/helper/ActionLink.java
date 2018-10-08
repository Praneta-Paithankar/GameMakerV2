package com.helper;

import java.io.Serializable;

import com.component.SpriteElement;

public class ActionLink implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7933629464902243547L;
	String action;
	SpriteElement sprite;
	String spriteElement2IdOrCategory;
	
	
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
		this.spriteElement2IdOrCategory = "";
	}

	public ActionLink(SpriteElement sprite, String spriteElement2IdOrCategory, String action) {
		super();
		this.action = action;
		this.sprite = sprite;
		this.spriteElement2IdOrCategory = spriteElement2IdOrCategory;
	}

	public String getSpriteElement2IdOrCategory() {
		return spriteElement2IdOrCategory;
	}

	public void setSpriteElement2IdOrCategory(String spriteElement2IdOrCategory) {
		this.spriteElement2IdOrCategory = spriteElement2IdOrCategory;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setSprite(SpriteElement sprite) {
		this.sprite = sprite;
	}
	public String toString() {
		return sprite+" Action:" + action + " Element2IdOrCategory " + spriteElement2IdOrCategory;		
	}
}
