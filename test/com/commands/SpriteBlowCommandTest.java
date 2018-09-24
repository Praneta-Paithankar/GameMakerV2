package com.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.component.SpriteElement;

public class SpriteBlowCommandTest {
	
	
	private SpriteElement sprite;
	private SpriteBlowCommand command;
	
	@InjectMocks SpriteBlowCommand spriteBlowCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		sprite = mock(SpriteElement.class);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testExecute() {
		when(sprite.isVisible()).thenReturn(true);
		spriteBlowCommand.execute();
		verify(sprite).setVisible(false);
	}
	
	@Test
	void testUndo() {
		when(sprite.isVisible()).thenReturn(false);
		spriteBlowCommand.undo();
		verify(sprite).setVisible(true);
	}
	
	

}