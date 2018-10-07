package com.component;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularSpriteTest {

	CircularSprite circularSprite;
	
	@BeforeEach
	void setup() {
		try {
			circularSprite = new CircularSprite("", 10, 20, 30,40,3,3, "id","category", Color.BLACK, 0,2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testReset() {
			circularSprite.setElementX(100);
			circularSprite.reset();
			assertEquals(10, circularSprite.getElementX());
	}

	@Test
	void testShoot() {
		SpriteElement element;
		try {
		
			element = circularSprite.shoot(circularSprite);
			assertTrue(element instanceof CircularSprite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testIntersects() {
		try {
			SpriteElement element = new  CircularSprite("", 20, 20, 30,40,3,3, "id","category", Color.BLACK, 0,2);
			boolean result = circularSprite.intersects(element);
			assertEquals(true,result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
