/**
 *This is our main application class which creates and initializes all the game components*/
package com.gamemaker;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.component.Clock;
import com.controller.GameMakerController;
import com.timer.BreakoutTimer;
import com.ui.ControlPanel;
import com.ui.GUI;
import com.ui.GamePanel;
import com.ui.MainPanel;
import com.ui.MakePanel;
import com.ui.StaticPanel;
import com.ui.TimerPanel;

public class GameMaker {
	protected static Logger log = Logger.getLogger(GameMaker.class);
	public static void start(boolean isRestart){
		
		PropertyConfigurator.configure("log4j.properties");
		 log.info("Initializing game-maker");

		BreakoutTimer observable  = new BreakoutTimer();
		GamePanel boardPanel =new GamePanel();

		// Start - Create StaticPanel
		StaticPanel staticPanel = new StaticPanel();

		TimerPanel timerPanel = new TimerPanel();
		Clock clock = new Clock();
		timerPanel.addComponent(clock);
		
		ControlPanel controlPanel = new ControlPanel();
		MakePanel makePanel = new MakePanel();
		
		staticPanel.addComponent(timerPanel);
		staticPanel.revalidate();
		staticPanel.addComponent(controlPanel);
		// End - Create StaticPanel
		MainPanel mainPanel = new MainPanel();
		mainPanel.addComponent(staticPanel);
		mainPanel.addComponent(boardPanel);
		mainPanel.addComponent(makePanel);

		GUI gui = new GUI(mainPanel, boardPanel, staticPanel, timerPanel, controlPanel, makePanel);
		
		gui.addComponent(mainPanel);

		//gui.addDriver(driver);
		GameMakerController makerController = new GameMakerController(gui, clock);
		gui.addGameMakerDriver(makerController);

		observable.startTimer();
		gui.setVisible(true);

		gui.draw(null);
		gui.pack();
		
	}
}
