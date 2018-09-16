package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Element{
	protected Logger log = Logger.getLogger(GUI.class);
	private JTextField filename = new JTextField(), dir = new JTextField();	
	private GamePanel boardPanel;
	private ArrayList<Element> elements;

	private GameController driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
	private JsonObject jsonObject;
	private JFileChooser c;
	private FileWriter file;
	
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
			int rVal = c.showSaveDialog(boardPanel);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		        for (Element element : elements) {
					jsonObject.put(element.getClass().toString(), element.save());
		        }
		        String filePath = Paths.get(dir.getText() , filename.getText()).toString();
		        file = new FileWriter(filePath);
				file.write(jsonObject.toJson());				
				file.flush();
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        filename.setText("You pressed cancel");
		        dir.setText("");
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
			int rVal = c.showOpenDialog(boardPanel);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		        String filePath = Paths.get(dir.getText() , filename.getText()).toString();
				Object obj = Jsoner.deserialize(new FileReader(filePath));
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
		        filename.setText("You pressed cancel");
		        dir.setText("");
		      }
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return brickCount;
	}		
}
