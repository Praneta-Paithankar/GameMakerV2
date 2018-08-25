package com.timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.infrastruture.ClockObserver;

public class BreakoutTimer implements Runnable {

	private long startTime;
	private long counter;
	private List<ClockObserver> observers;
	private Thread thread;
	private AtomicBoolean running;
	private int tickPerSecond;
	private int sleepTime;
	
	public BreakoutTimer(int tickPerSecond) {
		this.tickPerSecond =tickPerSecond;
		setStartTime(System.currentTimeMillis());
		observers =  Collections.synchronizedList(new ArrayList<ClockObserver>());
		
	}

	public void startTimer() {
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
		counter = System.currentTimeMillis() - startTime;
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
		setStartTime(System.currentTimeMillis());
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
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
