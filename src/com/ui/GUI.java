package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;
import org.json.simple.parser.JSONParser;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Element{
	protected Logger log = Logger.getLogger(GUI.class);
	private JTextField filename = new JTextField(), dir = new JTextField();	
	private GamePanel boardPanel;
	private ArrayList<Element> elementList;
	private JLabel exitLabel;
	private GameController driver;
	private JPanel mainPanel;
	private StaticPanel timerPanel;
	private JsonObject jsonObject;
	private JSONParser parser;
	
	public GUI() {
		boardPanel = new GamePanel();
		timerPanel = new StaticPanel();
		elementList = new ArrayList<>();
		initializeUI();
	}

	public GUI(GamePanel boardPanel, StaticPanel timerPanel) {
		super("Breakout Game");
		this.boardPanel = boardPanel;
		this.timerPanel = timerPanel;
		elementList = new ArrayList<>();
		initializeUI();
	}
	private void createBoardPanel() {

		boardPanel.setLayout(new GridBagLayout());
        boardPanel.setPreferredSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
	    boardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    boardPanel.setMaximumSize(new Dimension(Constants.BOARD_PANEL_WIDTH,Constants.BOARD_PANEL_HEIGHT));
		boardPanel.setBackground(Color.black);
		
		exitLabel = new JLabel();
		exitLabel.setForeground(Color.WHITE);
		exitLabel.setAlignmentX(SwingConstants.CENTER);
		exitLabel.setAlignmentY(SwingConstants.CENTER);
		Font font = new Font("Helvetica", Font.BOLD,50);
		
		exitLabel.setFont(font);
		boardPanel.add(exitLabel);
		boardPanel.setMaximumSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
		mainPanel.add(boardPanel);
		elementList.add(boardPanel);
	}
	private void initializeUI() {
       mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));       
       mainPanel.add(timerPanel);
       elementList.add(timerPanel);
       mainPanel.add(boardPanel);    
       createBoardPanel();
       add(mainPanel);
	   mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
	   mainPanel.setFocusable(true);
	   mainPanel.requestFocusInWindow();
	   setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);	
	}
	public void changeUI() {
		//boardPanel.repaint();
		boardPanel.paintImmediately(0, 0, Constants.BOARD_PANEL_WIDTH, Constants.BOARD_PANEL_HEIGHT);
		timerPanel.repaint();
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
				
	}

	@Override
	public void reset() {
	}

	@Override
	public void addComponent(Element e) {
	
	}

	@Override
	public void removeComponent(Element e) {
	
	}

	@Override
	public JsonObject save() {
		// TODO Auto-generated method stub
		jsonObject = new JsonObject();
		try {
			JFileChooser c = new JFileChooser();
			int rVal = c.showSaveDialog(boardPanel);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		        for (Element element : elementList) {
					jsonObject.put(element.getClass().toString(), element.save());
		        }
				System.out.println(jsonObject.size());
		        FileWriter file = new FileWriter(dir.getText() + "\\" + filename.getText());
				//doubt here in GUI
				System.out.println("rohan" + jsonObject.toString());
				file.write(jsonObject.toString());				
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
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(boardPanel);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		        parser = new JSONParser();
				Object obj = parser.parse(new FileReader(dir.getText() + "\\" + filename.getText()));
				jsonObject = (JsonObject) obj;
				
				for (Element element : elementList) {
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
