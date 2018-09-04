package com.commands;

import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.timer.BreakoutTimer;
import com.ui.StaticPanel;

public class TimerCommand implements Command {


	private static int counter;
	private static long prevTime;
	private static long currTime;
	
	public TimerCommand() {
		super();
		this.counter = 0;
		this.prevTime = 0;
		this.currTime = 0;
	}

	@Override
	public void execute() {
		// run  
		counter+=1;
		if( counter == Constants.TIMER_COUNT) {
			prevTime = currTime;
			currTime = currTime + 60;
			counter = 0;
		}
	}
	public long getCurrTime() {
		return(currTime);
	}
	
	@Override
	public void undo() {
		currTime = prevTime;
		prevTime = prevTime - 60;
	}
}
