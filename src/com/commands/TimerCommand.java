package com.commands;

import com.component.Clock;
import com.infrastruture.Command;
import com.infrastruture.Constants;
import com.timer.BreakoutTimer;
import com.ui.StaticPanel;

public class TimerCommand implements Command {


	private int previousSecond;
	private int previousMinute;
	private int count;
	private Clock clock;
	
	public TimerCommand(Clock clock) {
		this.clock = clock;
		count = 1000/Constants.TIMER_COUNT; //number of frames that should be skipped, to update a second.
	}

	@Override
	public void execute() {
	
		if(count>0) {
			count--;
		}
		else {
			previousSecond = clock.getSeconds();
			previousMinute = clock.getMinutes();
			if(clock.getSeconds() == 59) {clock.setSeconds(0); clock.setMinutes(clock.getMinutes()+1);}
			else if(clock.getSeconds() < 60) clock.setSeconds(clock.getSeconds()+1);
			count = 1000/Constants.TIMER_COUNT;
		}
	}

	@Override
	public void undo() {
		clock.setMinutes(previousMinute);
		clock.setSeconds(previousSecond);
	}
}
