
package com.helper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;
import org.mockito.junit.MockitoJUnitRunner;

import com.commands.TimerCommand;

import com.component.Clock;
import com.component.SpriteElement;
import com.helper.SpriteCollision;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SpriteCollisionTest {
	
	private SpriteCollision spriteCollision;
	private SpriteElement sprite;
	private SpriteElement destinationSprite;
	
	@BeforeEach
	void setUp() throws Exception {
		spriteCollision= new SpriteCollision();
		sprite = mock(SpriteElement.class);
		destinationSprite = mock(SpriteElement.class);
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnXIfCollisionOccursWithLeftWall() {
		
		when(sprite.getElementX()).thenReturn(0);
		when(sprite.getElementY()).thenReturn(20);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite);
		assertEquals(Direction.X, result);
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnXIfCollisionOccursWithRightWall() {
		
		when(sprite.getElementX()).thenReturn(Constants.BOARD_PANEL_WIDTH);
		when(sprite.getElementY()).thenReturn(20);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite);
		assertEquals(Direction.X, result);
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnYIfCollisionOccursWithBottomWall() {
		
		when(sprite.getElementX()).thenReturn(20);
		when(sprite.getElementY()).thenReturn(Constants.BOARD_PANEL_HEIGHT);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite);
		assertEquals(Direction.Y, result);
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnYIfCollisionOccursWithTopWall() {
		
		when(sprite.getElementX()).thenReturn(20);
		when(sprite.getElementY()).thenReturn(0);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite);
		assertEquals(Direction.Y, result);
	}

	@Test 
	void checkCollisionWithRectangularElementShouldReturnXIfCollisionOccursWithRectangularElement() {
		when(sprite.getElementX()).thenReturn(50);
		when(sprite.getWidth()).thenReturn(5);
		when(sprite.getElementY()).thenReturn(20);
		when(sprite.getHeight()).thenReturn(5);
		when(destinationSprite.getElementX()).thenReturn(50);
		when(destinationSprite.getWidth()).thenReturn(10);
		when(destinationSprite.getElementY()).thenReturn(25);
		when(destinationSprite.getHeight()).thenReturn(10);
		
		Direction result = spriteCollision.checkCollisionBetweenTwoRectangles(sprite, destinationSprite);
		assertEquals(Direction.X, result);
		
	
	}
		
		
}
