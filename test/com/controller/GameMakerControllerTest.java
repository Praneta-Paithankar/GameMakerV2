package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  org.mockito.MockitoAnnotations;

import com.component.CircularSprite;
import com.component.Clock;
import com.component.SpriteElement;
import com.helper.ActionLink;
import com.ui.CreateSpriteRequest;
import com.ui.GUI;
import com.ui.GamePanel;
import com.ui.MakePanel;

class GameMakerControllerTest {

	@Mock GUI gui;
	@Mock Clock clock;
	@InjectMocks GameMakerController gameMakerController;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testDone() {
		MakePanel makePanel = mock(MakePanel.class);
		CreateSpriteRequest createSpriteRequest = mock(CreateSpriteRequest.class);
		CreateSpriteRequest bulletSprite = mock(CreateSpriteRequest.class);
		GamePanel boardPanel  = mock(GamePanel.class);
		
		when(createSpriteRequest.getElementName()).thenReturn("Circle");
		when(createSpriteRequest.getWidth()).thenReturn(40);
		when(createSpriteRequest.getXlocation()).thenReturn(30);
		when(createSpriteRequest.getYlocation()).thenReturn(30);
		when(createSpriteRequest.getHeight()).thenReturn(30);
		when(createSpriteRequest.getImagePath()).thenReturn("");
		when(createSpriteRequest.getXVel()).thenReturn(1);
		when(createSpriteRequest.getYVel()).thenReturn(1);
		when(createSpriteRequest.getSpriteId()).thenReturn("id");
		when(createSpriteRequest.getCategory()).thenReturn("c");
		when(createSpriteRequest.getColor()).thenReturn(Color.BLACK);
		when(createSpriteRequest.getGameEndDependency()).thenReturn(0);
		when(createSpriteRequest.getCounterInterval()).thenReturn(0);

		when(gui.getMakePanel()).thenReturn(makePanel);
		when(makePanel.getNewSprite()).thenReturn(createSpriteRequest);
		when(makePanel.getShootSprite()).thenReturn(null);
		when(gui.getBoardPanel()).thenReturn(boardPanel);
		
		gameMakerController.done();
		verify(gui).draw(any());
		
	}
	
	@Test
	void TestMake() {
		MakePanel makePanel = mock(MakePanel.class);
		when(gui.getMakePanel()).thenReturn(makePanel);
		
		gameMakerController.make();
		verify(makePanel).buttonVisiblity();
	}

	@Test
	void TestMouseClicked() {
		MouseEvent event = mock(MouseEvent.class);
		GamePanel boardPanel  = mock(GamePanel.class);
		SpriteElement spriteElement = mock(CircularSprite.class);
		ActionLink actionLink = mock(ActionLink.class);
		List<ActionLink> actionLinks = new ArrayList<>();
		actionLinks.add(actionLink);
		Map<String, List<ActionLink>> eventMap = new HashMap<>();
		eventMap.put("event", actionLinks);
		gameMakerController.setEventMap(eventMap);
		
		when(gui.getBoardPanel()).thenReturn(boardPanel);
		when(boardPanel.getSpriteElement()).thenReturn(spriteElement);
		when(event.getX()).thenReturn(10);
		when(event.getY()).thenReturn(10);
		
		gameMakerController.mouseClicked(event);
		
		verify(gui).draw(any());
	}

}
