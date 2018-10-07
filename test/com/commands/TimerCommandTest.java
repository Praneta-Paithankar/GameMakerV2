package com.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.component.Clock;

class TimerCommandTest {
	
	@Mock private Clock clock;
	@InjectMocks private TimerCommand timerCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testExecute() {
		timerCommand.execute();
		verify(clock).setMilisecondsElapsed(anyLong());
	}

	@Test
	void testUndo() {
		timerCommand.undo();
		verify(clock).setMilisecondsElapsed(anyLong());
	}

}
