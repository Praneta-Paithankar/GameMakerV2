package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.commands.BounceCommand;
import com.commands.MacroCommand;
import com.commands.MoveCommand;
import com.commands.SpriteBlowCommand;
import com.component.CircularSprite;
import com.component.Clock;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.infrastruture.Direction;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import com.ui.GamePanel;

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
		
		gameDriver = new GameDriver(gui, timer, clock,new HashMap<>(),new HashMap<>());
		
		ball = mock(CircularSprite.class);
		gameDriver.addSpriteElements(ball);
		gameDriver.addGameEndSprite(ball);
	}
	
	@Test
	void testInitPlay() {
		gameDriver.InitPlay();
		verify(gui).paintView();
		verify(timer).registerObserver(any());
	}
	
	@Test
	void testCheckCollision() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		CircularSprite ball1 = mock(CircularSprite.class);;
		eventMap.put("OnCollision", actionLinks);
		gameDriver.addSpriteElements(ball1);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("move");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		when(ball.intersects(ball1)).thenReturn(true);
		when(ball1.isVisible()).thenReturn(true);
		when(ball1.getCategory()).thenReturn("c");
		gameDriver.checkCollision();
		
		verify(ball,atLeast(1)).intersects(ball1);
	}
	@Test
	void testActionForCollisionforblow() {
		ActionLink actionLink = mock(ActionLink.class);
		MacroCommand macroCommand = mock(MacroCommand.class);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("blow");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		gameDriver.actionForCollision(actionLink, Direction.X, macroCommand);
		verify(macroCommand).addCommand(any(SpriteBlowCommand.class));
		
	}
	@Test
	void testActionForCollisionforMove() {
		ActionLink actionLink = mock(ActionLink.class);
		MacroCommand macroCommand = mock(MacroCommand.class);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("move");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		gameDriver.actionForCollision(actionLink, Direction.X, macroCommand);
		verify(macroCommand).addCommand(any(MoveCommand.class));
		
	}
	@Test
	void testEventHandlerForMove() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		CircularSprite ball1 = mock(CircularSprite.class);;
		eventMap.put("event", actionLinks);
		gameDriver.addSpriteElements(ball1);
		
		when(ball.getCounter()).thenReturn(0);
		when(ball.getCounterInterval()).thenReturn(1);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("move");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		gameDriver.eventHandler("event");
		verify(actionLink,atLeast(1)).getSprite();
		verify(ball).setCounter(anyInt());
		
	}
	
	@Test
	void testEventHandlerForShoot() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		
		CircularSprite ball1 = mock(CircularSprite.class);;
		eventMap.put("event", actionLinks);
		gameDriver.addSpriteElements(ball1);
		
		Map<SpriteElement, SpriteElement> bulletElementMap = gameDriver.getBulletElementMap();
		bulletElementMap.put(ball, ball1);
		
		Map<SpriteElement, Integer> shooterMap =gameDriver.getShooterSpriteBulletCountMap();
		shooterMap.put(ball, 1);
		
		try {
			when(ball.shoot(any())).thenReturn(ball1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GamePanel gamePanel = mock(GamePanel.class);
		when(gui.getBoardPanel()).thenReturn(gamePanel);
		
		when(ball.getCounter()).thenReturn(0);
		when(ball.getCounterInterval()).thenReturn(1);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("shoot");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		
		gameDriver.eventHandler("event");
		verify(gui).paintView();
		verify(actionLink,atLeast(1)).getSprite();
		verify(ball).setCounter(anyInt());
		verify(gamePanel).setElements(any());
	}
	
	@Test
	void testEventHandlerForBlow() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		CircularSprite ball1 = mock(CircularSprite.class);;
		eventMap.put("event", actionLinks);
		gameDriver.addSpriteElements(ball1);
		
		when(ball.getCounter()).thenReturn(0);
		when(ball.getCounterInterval()).thenReturn(1);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("blow");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		gameDriver.eventHandler("event");
		verify(actionLink,atLeast(1)).getSprite();
		
	}
	
	@Test
	void testKeyPressedForMove() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		eventMap.put("keyPressed", actionLinks);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("move");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		when(ball.getElementX()).thenReturn(50);
		when(ball.getXVel()).thenReturn(5);
		
		KeyEvent event = mock(KeyEvent.class);
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
		gameDriver.keyPressed(event);
		
		verify(ball).setElementX(anyInt());

	}
	@Test
	void testKeyPressedForBlow() {
		ActionLink actionLink = mock(ActionLink.class);
		Map<String, List<ActionLink>> eventMap = gameDriver.getEventMap();
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		eventMap.put("keyPressed", actionLinks);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("blow");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		
		KeyEvent event = mock(KeyEvent.class);

		gameDriver.keyPressed(event);
		
		verify(actionLink,atLeast(1)).getSprite();

	}
	
	@Test
	void testSetSpriteDirectionforRightArrow() {
		KeyEvent event = mock(KeyEvent.class);
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
		
		when(ball.getElementX()).thenReturn(50);
		when(ball.getXVel()).thenReturn(5);
		when(ball.getWidth()).thenReturn(50);
		try {
			gameDriver.setSpriteDirection(event, ball);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(ball).setElementX(anyInt());
	}
	
	@Test
	void testSetSpriteDirectionforUpArrow() {
		KeyEvent event = mock(KeyEvent.class);
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_UP);
		
		when(ball.getElementY()).thenReturn(50);
		when(ball.getYVel()).thenReturn(5);
		try {
			gameDriver.setSpriteDirection(event, ball);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(ball).setElementY(anyInt());
	}
	
	@Test
	void testSetSpriteDirectionforDownArrow() {
		KeyEvent event = mock(KeyEvent.class);
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
		
		when(ball.getElementY()).thenReturn(50);
		when(ball.getYVel()).thenReturn(5);
		when(ball.getHeight()).thenReturn(50);
		
		try {
			gameDriver.setSpriteDirection(event, ball);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(ball).setElementY(anyInt());
	}
	
	@Test
	void testSetSpriteDirectionforSpace() {
		KeyEvent event = mock(KeyEvent.class);
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);
		CircularSprite ball1 = mock(CircularSprite.class);;
		gameDriver.addSpriteElements(ball1);
		
		
		Map<SpriteElement, SpriteElement> bulletElementMap = gameDriver.getBulletElementMap();
		bulletElementMap.put(ball, ball1);
		
		Map<SpriteElement, Integer> shooterMap =gameDriver.getShooterSpriteBulletCountMap();
		shooterMap.put(ball, 1);
		GamePanel gamePanel = mock(GamePanel.class);
		
		try {
			when(ball.shoot(any())).thenReturn(ball1);
			
			when(gui.getBoardPanel()).thenReturn(gamePanel);
			
			when(ball.getCounter()).thenReturn(0);
			when(ball.getCounterInterval()).thenReturn(1);
			
			
			gameDriver.setSpriteDirection(event, ball);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameDriver.eventHandler("event");
		verify(gui).paintView();
		verify(gamePanel).setElements(any());
	}
	
	@Test
	void testActionForCollisionforBounce() {
		ActionLink actionLink = mock(ActionLink.class);
		MacroCommand macroCommand = mock(MacroCommand.class);
		
		when(actionLink.getSprite()).thenReturn(ball);
		when(actionLink.getAction()).thenReturn("bounce");
		when(actionLink.getSpriteElement2IdOrCategory()).thenReturn("c");
		
		gameDriver.actionForCollision(actionLink, Direction.X, macroCommand);
		verify(macroCommand).addCommand(any(BounceCommand.class));
		
	}
//	@Test
//	void checkifGameEnd() {
//		gameDriver.checkIfGameEnd();
//		verify(timer, times(0)).removeObserver(any());
//		verify(gui, times(0)).paintView();
//	}
	
//	@Test
//	void update() {
////		gameDriver.update();
//		verify(gui).paintView();
//		assertEquals(gameDriver.getCommandQueue().size(), 1);
//	}
	
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
	@Test 
	void testActionPerformedForUndo() {
		ActionEvent event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn("undo");
		
		gameDriver.actionPerformed(event);
		
		verify(gui).changeFocus();
		verify(gui).paintView();
	}
	@Test 
	void testActionPerformedForStartandGameIsPaused() {
		ActionEvent event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn("start");
		gameDriver.setGamePaused(true);
		gameDriver.actionPerformed(event);
		
		verify(gui).changeFocus();
		verify(gui).paintView();
	}
	@Test 
	void testActionPerformedForStart() {
		ActionEvent event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn("start");
		gameDriver.setGamePaused(false);
		gameDriver.actionPerformed(event);
		
		verify(gui).paintView();
		verify(timer).registerObserver(gameDriver);
	}	
	@Test 
	void testActionPerformedForLayout() {
		ActionEvent event = mock(ActionEvent.class);
		when(event.getActionCommand()).thenReturn("layout");
		gameDriver.setGamePaused(false);
		gameDriver.actionPerformed(event);
		
		verify(gui).modifyLayout();
		verify(gui).changeFocus();;
	}	
}