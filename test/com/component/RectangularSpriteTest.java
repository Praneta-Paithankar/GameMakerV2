package com.component;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class RectangularSpriteTest {

	@Test
	void testReset() {
		try {
			RectangularSprite rectangularSprite = new RectangularSprite("", 10, 20, 30,40,3,3, "id","category", Color.BLACK, 0,2);
			rectangularSprite.setElementX(40);
			rectangularSprite.reset();
			assertEquals(10, rectangularSprite.getElementX());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
