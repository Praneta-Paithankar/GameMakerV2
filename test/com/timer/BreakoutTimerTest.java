package com.timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import  static org.mockito.Mockito.*;

import com.controller.GameDriver;

class BreakoutTimerTest {

	BreakoutTimer breakoutTimer;
	@BeforeEach
	void setup() {
		breakoutTimer = new BreakoutTimer();
	}
	
	@AfterEach
	void tearup() {
		breakoutTimer.stopTimer();
	}
	@Test
	void testNotifyObservers() {
		GameDriver gameDriver = mock(GameDriver.class);
		breakoutTimer.registerObserver(gameDriver);
		breakoutTimer.notifyObservers();
		verify(gameDriver,atLeast(1)).update();
	}
	
	@Test
	void testRemoveObserver() {
		GameDriver gameDriver = mock(GameDriver.class);
		breakoutTimer.registerObserver(gameDriver);
		assertFalse(breakoutTimer.isObserverListEmpty());
		breakoutTimer.removeObserver(gameDriver);
		assertTrue(breakoutTimer.isObserverListEmpty());
	}

}
