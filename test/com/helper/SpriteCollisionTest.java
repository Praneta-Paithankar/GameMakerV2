
package com.helper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.component.CircularSprite;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

//@RunWith(MockitoJUnitRunner.class)
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
		Direction result =spriteCollision.checkCollisionOfSpriteWithWall(sprite);
		assertEquals(Direction.X, result);		
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnXIfCollisionOccursWithRightWall() {
		
		when(sprite.getElementX()).thenReturn(700);
		when(sprite.getElementY()).thenReturn(20);
		when(sprite.getWidth()).thenReturn(50);
		Direction result = spriteCollision.checkCollisionOfSpriteWithWall(sprite);
		assertEquals(Direction.X, result);	
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnYIfCollisionOccursWithBottomWall() {
		
		when(sprite.getElementX()).thenReturn(20);
		when(sprite.getElementY()).thenReturn(Constants.BOARD_PANEL_HEIGHT);
		
		Direction result = spriteCollision.checkCollisionOfSpriteWithWall(sprite);
		assertEquals(Direction.Y, result);
	}
	
	@Test
	void checkCollisionWithSpriteElementShouldReturnYIfCollisionOccursWithTopWall() {
		
		when(sprite.getElementX()).thenReturn(20);
		when(sprite.getElementY()).thenReturn(0);
		Direction result = spriteCollision.checkCollisionOfSpriteWithWall(sprite);
		assertEquals(Direction.Y, result);
	}

	@Test 
	void checkCollisionOfSpritesShouldReturnYifCollidesVertically() {
		when(sprite.getElementX()).thenReturn(50);
		when(sprite.getWidth()).thenReturn(50);
		when(sprite.getElementY()).thenReturn(50);
		when(sprite.getHeight()).thenReturn(50);
		
		when(destinationSprite.getElementX()).thenReturn(60);
		when(destinationSprite.getWidth()).thenReturn(10);
		when(destinationSprite.getElementY()).thenReturn(40);
		when(destinationSprite.getHeight()).thenReturn(10);
		
		when(sprite.intersects(destinationSprite)).thenReturn(true);
		Direction result = spriteCollision.checkCollisionOfSprites(sprite, destinationSprite);
		assertEquals(Direction.Y, result);
	}

	
	@Test 
	void checkCollisionOfSpritesShouldReturnXifCollidesHorizontally() {
		when(sprite.getElementX()).thenReturn(50);
		when(sprite.getWidth()).thenReturn(50);
		when(sprite.getElementY()).thenReturn(50);
		when(sprite.getHeight()).thenReturn(50);
		
		when(destinationSprite.getElementX()).thenReturn(20);
		when(destinationSprite.getWidth()).thenReturn(30);
		when(destinationSprite.getElementY()).thenReturn(70);
		when(destinationSprite.getHeight()).thenReturn(10);
		
		when(sprite.intersects(destinationSprite)).thenReturn(true);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite, destinationSprite);
		assertEquals(Direction.X, result);
	}
	
	@Test 
	void checkCollisionOfSpritesShouldReturnNoneifNoCollisinOccured() {
		when(sprite.getElementX()).thenReturn(50);
		when(sprite.getWidth()).thenReturn(50);
		when(sprite.getElementY()).thenReturn(50);
		when(sprite.getHeight()).thenReturn(50);
		
		when(destinationSprite.getElementX()).thenReturn(200);
		when(destinationSprite.getWidth()).thenReturn(30);
		when(destinationSprite.getElementY()).thenReturn(700);
		when(destinationSprite.getHeight()).thenReturn(10);
		
		when(sprite.intersects(destinationSprite)).thenReturn(false);
		
		Direction result = spriteCollision.checkCollisionOfSprites(sprite, destinationSprite);
		assertEquals(Direction.NONE, result);
	}
}
