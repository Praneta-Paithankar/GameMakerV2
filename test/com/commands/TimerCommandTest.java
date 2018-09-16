package com.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.*;

import com.component.Clock;

class TimerCommandTest {

	@Mock Clock clock;
	
	@InjectMocks TimerCommand timerCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void testExecute() {
		when(clock.getMilisecondsElapsed()).thenReturn((long) 10);
		timerCommand.execute();
		verify(clock).setMilisecondsElapsed(anyLong());
	}
	
	@Test
	void testUndo() {
		when(clock.getMilisecondsElapsed()).thenReturn((long) 10);
		timerCommand.execute();
		verify(clock).setMilisecondsElapsed(anyLong());
	}

}