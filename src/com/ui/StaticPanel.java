package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;

import com.controller.GameController;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class StaticPanel extends JPanel implements Element{
	protected Logger log = Logger.getLogger(StaticPanel.class);
	private JLabel score;
	private GameController driver;
	private ArrayList<Element> elements;
	private JsonObject jsonObject;
	
	public StaticPanel() {
		this.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        elements = new ArrayList<>();
	}
	public ArrayList<Element> getElements(){
		return elements;
	}
	
	public void addElement(Element element){
		elements.add(element);
		
	}
	public void removeElement(Element element)
	{
		elements.remove(element);
	}
	
	public void createButtons(GameController driver)
	{
		this.driver = driver;
	    createReplay();
	    createUndo();
	    createStart();
	    createPause();
	    createSave();
	    createLoad();
	}
	
	
	public void createReplay() {
		JButton replayButton = new JButton("Replay");
		replayButton.setActionCommand("replay");
		replayButton.addActionListener(driver);
		replayButton.setVisible(true);
		this.add(replayButton);
	}
	
	public void createUndo() {
		JButton undoButton = new JButton("Undo");
		undoButton.setActionCommand("undo");
		undoButton.addActionListener(driver);
		undoButton.setVisible(true);
		this.add(undoButton);
	}
	
	public void createStart() {
		JButton startButton = new JButton("Start");
     	startButton.setActionCommand("start");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		this.add(startButton);
	}
	
	public void createPause() {
		JButton startButton = new JButton("Pause");
     	startButton.setActionCommand("pause");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		this.add(startButton);
	}
	
	public void createSave() {
		JButton saveButton = new JButton("Save");
		saveButton.setActionCommand("save");
		saveButton.addActionListener(driver);
		saveButton.setVisible(true);
		this.add(saveButton);
	}
	
	public void createLoad() {
		JButton loadButton = new JButton("Load");
		loadButton.setActionCommand("load");
		loadButton.addActionListener(driver);
		loadButton.setVisible(true);
		this.add(loadButton);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Element component : elements)
		{
			component.draw(g);
		}
	}
	
	
	public void addComponent(Element e) {
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

	@Override
	public void draw(Graphics g) {
		repaint();
	}
	@Override
	public void reset() {
		for(Element element : elements) {
			element.reset();
		}
	}
	@Override
	public JsonObject save() {
		// TODO Auto-generated method stub
		jsonObject = new JsonObject();
		try {
			for (Element element : elements) {
					jsonObject.put(element.getClass().toString(), element.save());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return jsonObject;
	}
	@Override
	public int load(Object object) {
		jsonObject = (JsonObject) object;
		for (Element element : elements) {
			element.load(jsonObject.get(element.getClass().toString()));
		}
		return 1;
	}
}
