package com.timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.infrastruture.ClockObserver;

public class BreakoutTimer implements Runnable {

	private long counter;
	private List<ClockObserver> observers;
	private Thread thread;
	private AtomicBoolean running;
	private int tickPerSecond;
	private int sleepTime;
	
	public BreakoutTimer(int tickPerSecond) {
		this.tickPerSecond =tickPerSecond;
		observers =  Collections.synchronizedList(new ArrayList<ClockObserver>());
		
	}

	public void startTimer() {
		counter =0;
		sleepTime = 1000 / tickPerSecond;
		thread = new Thread(this);
		running = new AtomicBoolean(true);
		thread.start();
		
	}
	public void stopTimer(){
		running.set(false);
			
	}
	@Override
	public void run() {
		while(running.get()) {
			counter += sleepTime; 
			notifyObserver(counter);
			pauseThread();
		}
		if(!running.get()) {
			observers.removeAll(observers);
		}	
		
	}

	private void pauseThread() {
		try {
			Thread.sleep(sleepTime);
		}catch (InterruptedException exception) {
		}
	}
		
	public void resetTimer()
	{
		counter =0;
	}
	
	public void addObserver(ClockObserver observer){
		synchronized (observer) {
			observers.add(observer);
		}
	}
	
	public void removeObserver(ClockObserver observer){
		synchronized (observer) {
			observers.remove(observer);
		}
	}
	
	public void notifyObserver(long time) {
		synchronized (observers) {
			for(ClockObserver observer : observers)
			{
				observer.update(time);
			}
		}
		
	}
}
