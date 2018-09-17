package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;

import com.commands.BallChangeXDirectionCommand;
import com.commands.BallEnactCommand;
import com.commands.BrickEnactCommand;
import com.commands.TimerCommand;
import com.component.Ball;
import com.component.Brick;
import com.component.Clock;
import com.component.Paddle;
import com.helper.CollisionChecker;
import com.infrastruture.Direction;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class GameControllerTest {
	
	Ball ball;
	Paddle paddle;
	ArrayList<Brick> bricks;
    GUI gui;
    BreakoutTimer observable;
    
    BrickEnactCommand[] brickActCommands;
  	BallEnactCommand ballActCommand;
  	TimerCommand timerCommand;
    Clock clock;
    
  	CollisionChecker collisionChecker;
   
	
    GameController gameController;
	

	@BeforeEach
	void setUp() throws Exception {
		bricks = new ArrayList<>();
		ball = mock(Ball.class);
		paddle = mock(Paddle.class);
		gui = mock(GUI.class);
		observable = mock(BreakoutTimer.class);
		ballActCommand = mock(BallEnactCommand.class);
		timerCommand = mock(TimerCommand.class);
		collisionChecker = mock(CollisionChecker.class);
		clock = mock(Clock.class);
		gameController = new GameController(ball, paddle, bricks, gui, observable, clock, collisionChecker);
		
		//set Command mock
		gameController.setBallActCommand(ballActCommand);	
	}
	
	@Test
	void TestUpdate (){
		gameController.update();
		verify(ballActCommand,atLeast(1)).execute();
		
	}
	@Test
	void TestSave (){
		when(gui.showSaveDialog()).thenReturn("");
		gameController.save();
		verify(gui,atLeast(1)).showSaveDialog();
		
	}
	@Test
	void TestLoad (){
		when(gui.showOpenDialog()).thenReturn("");
		gameController.load();
		verify(gui,atLeast(1)).showOpenDialog();
		
	}
	@Test
	void TestReplay(){
		
		gameController.replay();
		verify(ball,atLeast(1)).reset();
		
	}

}
