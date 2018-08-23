package com.timer;

import java.util.ArrayList;

import com.component.ClockObserver;

public class BreakoutTimer implements Runnable {

	private long startTime;
	private long counter;
	private ArrayList<ClockObserver> observers;
	private Thread thread;
	private boolean running;
	
	public BreakoutTimer() {
		setStartTime(System.currentTimeMillis());
		observers = new ArrayList<ClockObserver>();
	}

	public void startTimer() {
		thread = new Thread(this);
		running =true;
		thread.start();
		
	}
	public void stopTimer(){
		running = false;
	}
	@Override
	public void run() {
		while(running) {
		counter = System.currentTimeMillis() - startTime;
		notifyObserver(counter);
		pauseThread();
		}
	}

	private void pauseThread() {
		try {Thread.sleep(500);}
	    catch (InterruptedException exception) {}
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
		observers.add(observer);
	}
	
	public void removeObserver(ClockObserver observer){
		observers.remove(observer);
	}
	
	public void notifyObserver(long time) {
		for(ClockObserver observer : observers)
		{
			observer.update(time);;
		}
	}
}
