package com.commands;

import java.util.ArrayList;
import java.util.Collections;

import com.infrastruture.*;

public class MacroCommand implements Command{
	private ArrayList<Command> commands;
	
	public MacroCommand() {
		commands = new ArrayList<>();
	}
	
	public void addCommand(Command c) {
		commands.add(c);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (Command c :commands) {
			c.execute();
		}
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		Collections.reverse(commands);
		for (Command c :commands) {
			c.undo();
		}
	}

}
