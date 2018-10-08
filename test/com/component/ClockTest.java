package com.component;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ClockTest {

	private Clock clock;
	
	@BeforeEach
	void setup() {
		clock = new Clock();
	}
	@Test
	void testGetTime() {
		clock.setMilisecondsElapsed(63000);
		String result = clock.getTime();
		assertEquals("1:03", result);
	}

	@Test
	void testReset() {
		clock.reset();
		assertEquals(0, clock.getMilisecondsElapsed());
	}

	@Test
	void testResetClock() {
		Clock c = new Clock();
		c.setMilisecondsElapsed(63);
		clock.reset(c);
		assertEquals(c.getMilisecondsElapsed(), clock.getMilisecondsElapsed());
	}

}
