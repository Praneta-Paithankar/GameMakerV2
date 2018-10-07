package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.*;
import com.component.SpriteElement;
import com.infrastruture.Direction;

class BounceCommandTest {

	private SpriteElement sprite;
	private Direction direction;
	private BounceCommand command;
	
	@BeforeEach
	void setUp() throws Exception {
		sprite = mock(SpriteElement.class);
		direction = Direction.X;
		command =  new BounceCommand(sprite, direction);
		
	}
	
	@Test
	void testExecuteIfDirectionisX() {
		when(sprite.getXVel()).thenReturn(10);
		command.execute();
		verify(sprite).setXVel(anyInt());
	}
	
	@Test
	void testUndo() {
		when(sprite.getXVel()).thenReturn(10);
		command.undo();
		verify(sprite).setXVel(anyInt());
	}

}
