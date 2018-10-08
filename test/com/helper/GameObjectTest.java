package com.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.component.CircularSprite;
import com.component.RectangularSprite;
import com.component.SpriteElement;
import com.ui.CreateSpriteRequest;

class GameObjectTest {

	CreateSpriteRequest createSpriteRequest;
	GameObject gameObject;
	@BeforeEach
	void setup() {
		createSpriteRequest = mock(CreateSpriteRequest.class);
		gameObject = new GameObject();
	}
	
	@Test
	void testSpriteDecoderIfCircularSpriteIsRequested() {
		when(createSpriteRequest.getElementName()).thenReturn("Circle");
		SpriteElement element;
		try {
			element = gameObject.spriteDecoder(createSpriteRequest);
			assertTrue(element instanceof CircularSprite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Test
	void testSpriteDecoderIfRectangularSpriteIsRequested() {
		when(createSpriteRequest.getElementName()).thenReturn("Rectangle");
		SpriteElement element;
		try {
			element = gameObject.spriteDecoder(createSpriteRequest);
			assertTrue(element instanceof RectangularSprite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
