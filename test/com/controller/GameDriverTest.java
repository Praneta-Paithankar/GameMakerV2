package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.NeverWantedButInvoked;
import org.mockito.internal.verification.AtLeast;
import org.mockito.junit.MockitoJUnitRunner;

import com.commands.TimerCommand;
import com.component.CircularSprite;
import com.component.Clock;
import com.component.SpriteElement;
import com.helper.SpriteCollision;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

//@RunWith(MockitoJUnitRunner.class)
public class GameDriverTest {
	
	private Clock clock;
	private BreakoutTimer timer;
	private GUI gui;
	private GameDriver gameDriver;
	private CircularSprite ball;
	
	@BeforeEach
	void setUp() throws Exception {
		clock = mock(Clock.class);
		timer = mock(BreakoutTimer.class);
		gui = mock(GUI.class);
		
		gameDriver = new GameDriver(gui, timer, clock,null);
		
		ball = mock(CircularSprite.class);
		gameDriver.addSpriteElements(ball);
		gameDriver.addGameEndSprite(ball);
	}
	
	@Test
	void checkifGameEnd() {
		gameDriver.checkIfGameEnd();
		verify(timer, times(0)).removeObserver(any());
		verify(gui, times(0)).paintView();
	}
	
	@Test
	void update() {
		gameDriver.update();
		verify(gui).paintView();
		assertEquals(gameDriver.getCommandQueue().size(), 1);
	}
	
	@Test
	void pause() {
		when(timer.isObserverListEmpty()).thenReturn(false);
		gameDriver.pause();
		verify(timer).removeObserver(any());
		assertEquals(gameDriver.isGamePaused(), true);
	}
	
	@Test
	void undo() {
		gameDriver.undoAction();
		assertEquals(gameDriver.getCommandQueue().size(), 0);
	}
	
	@Test 
	void unpause() {
		gameDriver.unPause();
		verify(timer).registerObserver(any());
		assertEquals(gameDriver.isGamePaused(), false);
	}
			
}