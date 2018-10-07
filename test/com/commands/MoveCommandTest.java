package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.component.SpriteElement;
import com.infrastruture.Direction;

class MoveCommandTest {

	private SpriteElement sprite;
	private MoveCommand command;
	
	@BeforeEach
	void setUp() throws Exception {
		sprite = mock(SpriteElement.class);
		command =  new MoveCommand(sprite);
		
	}
	
	@Test
	void testExecute() {
		when(sprite.getElementX()).thenReturn(30);
		when(sprite.getElementY()).thenReturn(40);
		when(sprite.getXVel()).thenReturn(3);
		when(sprite.getYVel()).thenReturn(4);
		command.execute();
		verify(sprite).setElementX(anyInt());
		verify(sprite).setElementY(anyInt());
	}

	@Test
	void testUndo() {
		command.undo();
		verify(sprite).setElementX(anyInt());
		verify(sprite).setElementY(anyInt());
	}
}
