package com.infrastruture;

public interface Observable {
	 void addObserver(ClockObserver observer);
	 void removeObserver(ClockObserver observer);
	 void notifyObservers(long time);
}
