package com.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import com.infrastruture.Command;

class MacroCommandTest {

	private Command command;
	private MacroCommand macroCommand;
	
	@BeforeEach
	public void setup() {
		
		macroCommand = new MacroCommand();
	}

	@Test
	void testExecute() {
		command = mock(Command.class);
		macroCommand.addCommand(command);
		macroCommand.execute();
		verify(command).execute();
	}

	@Test
	void testUndo() {
		command = mock(Command.class);
		macroCommand.addCommand(command);
		macroCommand.undo();
		verify(command).undo();
	}

}
