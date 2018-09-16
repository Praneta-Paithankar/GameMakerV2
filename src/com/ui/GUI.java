package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Element{
	protected Logger log = Logger.getLogger(GUI.class);	
	private GamePanel boardPanel;
	private ArrayList<Element> elements;

	private GameController driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
	private JsonObject jsonObject;
	private JFileChooser c;
	private FileWriter fileWriter;
	private String filePath;
	private FileReader fileReader;
	

	public GUI() {
		boardPanel = new GamePanel();
		timerPanel = new StaticPanel();
		elements = new ArrayList<>();
		initializeUI();
	}

	public GUI(GamePanel boardPanel, StaticPanel timerPanel) {
		super("Breakout Game");
		this.boardPanel = boardPanel;
		this.timerPanel = timerPanel;
		elements = new ArrayList<>();
		initializeUI();
	}
	
	private void initializeUI() {
       mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));       
       mainPanel.add(timerPanel);
       mainPanel.add(boardPanel);
       
       add(mainPanel);
	   mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	   mainPanel.setFocusable(true);
	   mainPanel.requestFocusInWindow();
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
        timerPanel.createButtons(driver);
	
	}
	public void changeFocus()
	{
		mainPanel.requestFocus();
	}


	@Override
	public void draw(Graphics g) {
		for(Element element : elements) {
			element.draw(g);
		}	
	}

	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}

	@Override
	public void addComponent(Element e) {
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
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
		        for (Element element : elements) {
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
				for (Element element : elements) {
					if(element.getClass().toString().contains("GamePanel")) {
						brickCount = element.load(jsonObject.get(element.getClass().toString()));
					}else {
						element.load(jsonObject.get(element.getClass().toString()));
					}
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
}
