package com.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.json.simple.JsonObject;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.BoxLayoutYAxisBehavior;
import com.behavior.GridBagLayoutBehavior;
import com.component.Clock;
import com.controller.GameController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;


@SuppressWarnings("serial")
public class StaticPanel extends AbstractPanel implements Element{
	protected Logger log = Logger.getLogger(StaticPanel.class);
	private JLabel score;
	private GameController driver;
	private ArrayList<Element> elements;
	private JsonObject jsonObject;
	
	public StaticPanel() {
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		setLayoutBehavior(new BoxLayoutYAxisBehavior());
//		setLayoutBehavior(new GridLayoutBehavior());
		performUpdateLayout(this, Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT);
//		this.setPreferredSize(new Dimension(Constants.TIMER_PANEL_WIDTH, Constants.TIMER_PANEL_HEIGHT));
//        this.setMaximumSize(new Dimension(Constants.TIMER_PANEL_WIDTH,Constants.TIMER_PANEL_HEIGHT));
        elements = new ArrayList<>();
	}
	public ArrayList<Element> getElements(){
		return elements;
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
	    createLayout();
	}
	
	
	public void createReplay() {
		JButton replayButton = new JButton("Replay");
		replayButton.setActionCommand("replay");
		replayButton.addActionListener(driver);
		replayButton.setVisible(true);
		replayButton.setAlignmentX(CENTER_ALIGNMENT);
		replayButton.setAlignmentY(CENTER_ALIGNMENT);

		this.add(Box.createRigidArea(new Dimension(30,30)));
		this.add(replayButton);
		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createUndo() {
		JButton undoButton = new JButton("Undo");
		undoButton.setActionCommand("undo");
		undoButton.addActionListener(driver);
		undoButton.setVisible(true);
		undoButton.setAlignmentX(CENTER_ALIGNMENT);
		undoButton.setAlignmentY(CENTER_ALIGNMENT);

		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(undoButton);
		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createStart() {
		JButton startButton = new JButton("Start");
     	startButton.setActionCommand("start");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.setAlignmentY(CENTER_ALIGNMENT);

		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(startButton);
		this.add(Box.createRigidArea(new Dimension(5,5)));
	}
	
	public void createPause() {
		JButton startButton = new JButton("Pause");
     	startButton.setActionCommand("pause");
     	startButton.addActionListener(driver);
		startButton.setVisible(true);
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.setAlignmentY(CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(startButton);
		this.add(Box.createRigidArea(new Dimension(5,5)));
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

	public void createLayout() {
		JButton layoutButton = new JButton("Layout");
		layoutButton.setActionCommand("layout");
		layoutButton.addActionListener(driver);
		layoutButton.setVisible(true);
		layoutButton.setAlignmentX(CENTER_ALIGNMENT);
		layoutButton.setAlignmentY(CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(layoutButton);
		this.add(Box.createRigidArea(new Dimension(5,5)));
	}

	
	/*
	@Override
	public void paintComponent(Graphics g){
//		System.out.println("StaticPanel::paintComponent");

		super.paintComponent(g);
		for(Element component : elements)
		{
			double X = this.getComponent(0).getX();
			double Y = this.getComponent(0).getY();
//			System.out.println("X: " + X + " Y: " + Y);
//			System.out.println("Width: " + Integer.toString(this.getComponent(0).getWidth()));
//			System.out.println("Height: " + Integer.toString(this.getComponent(0).getHeight()));
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.translate(X, Y); 
			component.draw(g2d);
			g2d.dispose();
		}
		
	}
	*/

	/*
	@Override
	public JSONObject save() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	*/
	public void addComponent(Element e) {
		this.add((AbstractPanel)e);
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		elements.remove(e);
	}

	@Override
	public void draw(Graphics g) {
//		System.out.println("StaticPanel::draw");
//		repaint();
	for(Element component : elements) {
		component.draw(null);
	}
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
