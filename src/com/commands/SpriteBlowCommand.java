package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;

public class SpriteBlowCommand implements Command{

	SpriteElement sprite;

	public SpriteBlowCommand(SpriteElement sprite) {
		super();
		this.sprite = sprite;
	}

	@Override
	public void execute() {
		sprite.setVisible(false);
	}

	@Override
	public void undo() {
		sprite.setVisible(true);

	}

}
