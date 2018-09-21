package com.helper;

import java.util.ArrayList;
import java.util.Map;

public class GameObject {
	private String element;
	private String elementX;
	private String elementY;
	
	private Map<String, String> eventActions;

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getElementX() {
		return elementX;
	}

	public void setElementX(String elementX) {
		this.elementX = elementX;
	}

	public String getElementY() {
		return elementY;
	}

	public void setElementY(String elementY) {
		this.elementY = elementY;
	}

	public Map<String, String> getEventActions() {
		return eventActions;
	}

	public void setEventActions(Map<String, String> eventActions) {
		this.eventActions = eventActions;
	}
}