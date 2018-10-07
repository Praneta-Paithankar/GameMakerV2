package com.helper;

import org.apache.log4j.Logger;

import com.component.SpriteElement;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

public class SpriteCollision {
	protected static Logger log = Logger.getLogger(SpriteCollision.class);

	public Direction checkCollisionOfSpriteWithWall(SpriteElement c) {
		Direction currentDirection = Direction.NONE;
		if (c.getElementX() <= 0 || c.getElementX() + c.getWidth() >= Constants.BOARD_PANEL_WIDTH) {
			//log.error(Direction.X);
			currentDirection = Direction.X;
		}
		if (c.getElementY() <= 0 || c.getElementY() + c.getHeight() >= Constants.BOARD_PANEL_HEIGHT) {
			//log.error(Direction.Y);
			currentDirection = currentDirection == Direction.NONE ? Direction.Y : Direction.BOTH;
		}
		//log.error(currentDirection);
		return currentDirection;
	}

	public Direction checkCollisionOfSprites(SpriteElement c1, SpriteElement c2) {
		Direction currentDirection = Direction.NONE;
		if (c1.intersects(c2) && rightCollision(c1, c2)) { // c1: right c2: left
			currentDirection = Direction.X;
		} else if (c1.intersects(c2) && leftCollision(c1, c2)) { // c1: left c2: right
			currentDirection = Direction.X;
		}
		if (c1.intersects(c2) && topCollision(c1, c2)) { // c1: top c2: bot
			currentDirection = currentDirection == Direction.NONE ? Direction.Y : Direction.BOTH;
		} else if (c1.intersects(c2) && botCollision(c1, c2)) { // c1: bot c2: top
			currentDirection = currentDirection == Direction.NONE ? Direction.Y : Direction.BOTH;
		}
		return currentDirection;
	}

	private boolean topCollision(SpriteElement c1, SpriteElement c2) {
		int oneTop = c1.getElementY();
		int twoTop = c2.getElementY();
		int twoBot = c2.getElementY() + c2.getHeight();

		return (oneTop >= twoTop && oneTop <= twoBot);
	}

	private boolean rightCollision(SpriteElement c1, SpriteElement c2) {
		int oneRight = c1.getElementX() + c1.getWidth();
		int twoLeft = c2.getElementX();
		int twoRight = c2.getElementX() + c2.getWidth();
		return oneRight >= twoLeft && oneRight <= twoRight;
	}

	private boolean botCollision(SpriteElement c1, SpriteElement c2) {
		int oneBot = c1.getElementY() + c1.getHeight();
		int twoTop = c2.getElementY();
		int twoBot = c2.getElementY() + c2.getHeight();

		return oneBot >= twoTop && oneBot <= twoBot;
	}

	private boolean leftCollision(SpriteElement c1, SpriteElement c2) {
		int oneLeft = c1.getElementX();
		int c2Left = c2.getElementX();
		int c2Right = c2.getElementX() + c2.getWidth();

		return oneLeft >= c2Left && oneLeft <= c2Right;
	}

}
