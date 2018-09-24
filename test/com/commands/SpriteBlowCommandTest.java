package com.commands;

import com.component.SpriteElement;
import com.infrastruture.Command;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;
import org.mockito.junit.MockitoJUnitRunner;

import com.commands.TimerCommand;

import com.component.Clock;
import com.component.SpriteElement;
import com.helper.SpriteCollision;
import com.infrastruture.Constants;
import com.infrastruture.Direction;
import com.timer.BreakoutTimer;
import com.ui.GUI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

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