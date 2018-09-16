package com.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.component.Brick;
import static org.mockito.Mockito.verify;

class BrickEnactCommandTest {

	@Mock Brick brick;
	
	@InjectMocks BrickEnactCommand brickEnactCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	void ShouldSetBrickVisibilityToFalse() {
		brickEnactCommand.execute();
		verify(brick).setVisible(false);
	}
	
	@Test
	void ShouldUndoCommand() {
		brickEnactCommand.undo();
		verify(brick).setVisible(true);
	}

}