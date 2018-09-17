package com.ui;

import java.awt.Graphics;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.BoxLayoutYAxisBehavior;
import com.behavior.FlowLayoutBehavior;
import com.behavior.GridBagLayoutBehavior;
import com.controller.GameController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Element{
	protected Logger log = Logger.getLogger(GUI.class);	
	private GamePanel boardPanel;
	private ArrayList<Element> elementList;

	private GameController driver;
	private MainPanel mainPanel;
	private JsonObject jsonObject;
	private JFileChooser c;
	private FileWriter fileWriter;
	private String filePath;
	private FileReader fileReader;

	private StaticPanel staticPanel;
	private TimerPanel timerPanel;
	private ControlPanel controlPanel;
	private boolean toggleLayout;
	
	public GUI(MainPanel mainPanel, GamePanel boardPanel, StaticPanel staticPanel, TimerPanel timerPanel, ControlPanel controlPanel) {
		super("Breakout Game");
		this.mainPanel = mainPanel;
		this.boardPanel = boardPanel;
		this.staticPanel = staticPanel;
		this.timerPanel = timerPanel;
		this.controlPanel = controlPanel;
		toggleLayout = false;
		initializeUI();
		elementList = new ArrayList<>();
	}

	private void initializeUI() {
		
	   setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);	
	}

	public GamePanel getBoardPanel() {
		return boardPanel;
	}
	public void removeKeyListner() {
		mainPanel.removeKeyListener(driver);
	}
	public void addDriver(GameController driver){
		this.driver = driver;
		mainPanel.addKeyListener(driver);
        controlPanel.createButtons(driver);
	}

	public void changeFocus()
	{
		mainPanel.requestFocus();
	}


	@Override
	public void draw(Graphics g) {
		for(Element element : elementList) {
			element.draw(g);
		}	
	}

	@Override
	public void reset() {
		for(Element element : elementList) {
			element.reset();
		}
	}

	@Override
	public void addComponent(Element e) {
		add((AbstractPanel)e);
		elementList.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elementList.remove(e);
	}

	@Override
	public JsonObject save() {
		// TODO Auto-generated method stub
		jsonObject = new JsonObject();
		try {
			c = new JFileChooser();
			c.setDialogType(JFileChooser.SAVE_DIALOG);
			c.setFileFilter(new FileNameExtensionFilter("json file","json"));
			int rVal = c.showSaveDialog(this);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        String name = c.getSelectedFile().toString();
		    	if (!name.endsWith(".json"))
		    	        name += ".json";
		    	setFilePath(name);
		        for (Element element : elementList) {
					jsonObject.put(element.getClass().toString(), element.save());
		        }
		        
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        setFilePath("");
		        jsonObject = null;
		      }
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		return jsonObject;
	}

	@Override
	public int load(Object object) {
		int brickCount = 0;
		try {
			c = new JFileChooser();
			c.setFileFilter(new FileNameExtensionFilter("json file","json"));
			int rVal = c.showOpenDialog(boardPanel);
		    if (rVal == JFileChooser.APPROVE_OPTION) {
		    	String name = c.getSelectedFile().toString();
		    	setFilePath(name);
		    	setFileReader(new FileReader(filePath));
				Object obj = Jsoner.deserialize(getFileReader());
				jsonObject = (JsonObject) obj;
				for (Element element : elementList) {
						brickCount = element.load(jsonObject.get(element.getClass().toString()));
					}
				}
		    
		     if (rVal == JFileChooser.CANCEL_OPTION) {
		    	 setFilePath("");
		     }
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return brickCount;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}	
	public FileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}		

	public void modifyLayout() {
		toggleLayout = !toggleLayout;
		
		if (toggleLayout) {
			mainPanel.setLayoutBehavior(new BoxLayoutYAxisBehavior());
			mainPanel.performUpdateLayout(mainPanel, Constants.MAIN_PANEL_HEIGHT, Constants.MAIN_PANEL_WIDTH);
			
			staticPanel.setLayoutBehavior(new BoxLayoutXAxisBehavior());
			staticPanel.performUpdateLayout(staticPanel, Constants.BOARD_PANEL_HEIGHT,Constants.TIMER_PANEL_WIDTH);

			timerPanel.setLayoutBehavior(new FlowLayoutBehavior());
			timerPanel.performUpdateLayout(timerPanel, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_WIDTH);

			controlPanel.setLayoutBehavior(new GridBagLayoutBehavior());
			controlPanel.performUpdateLayout(controlPanel,Constants.TIMER_PANEL_HEIGHT-Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_WIDTH);
			
			boardPanel.setLayoutBehavior(new FlowLayoutBehavior());
			boardPanel.performUpdateLayout(boardPanel, Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT);
		} else {
			mainPanel.setLayoutBehavior(new BoxLayoutXAxisBehavior());
			mainPanel.performUpdateLayout(mainPanel, Constants.MAIN_PANEL_WIDTH,Constants.MAIN_PANEL_HEIGHT);

			staticPanel.setLayoutBehavior(new BoxLayoutYAxisBehavior());
			staticPanel.performUpdateLayout(staticPanel, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT);

			timerPanel.setLayoutBehavior(new FlowLayoutBehavior());
			timerPanel.performUpdateLayout(timerPanel, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_WIDTH);

			controlPanel.setLayoutBehavior(new FlowLayoutBehavior());
			controlPanel.performUpdateLayout(controlPanel, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT-Constants.TIMER_PANEL_WIDTH);

			boardPanel.setLayoutBehavior(new FlowLayoutBehavior());
			boardPanel.performUpdateLayout(boardPanel, Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT);
		}
		
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.revalidate();
		this.pack();
	}
}
