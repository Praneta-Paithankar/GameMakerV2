package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.apache.log4j.Logger;

import com.behavior.FlowLayoutBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private List<String> actions;
	private List<String> events;
	JComboBox<String> spriteSelection;
	
	public MakePanel() {
		setLayoutBehavior(new FlowLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		createButtons();
		sprites = new ArrayList<>(Constants.spriteTypes);
		createSpriteSelectionList();
	}
	
	public void createButtons() {
		this.add(createMakeButton());
		this.add(createPlayButton());
	}
	
	public void createSpriteSelectionList() {
		spriteSelection = new JComboBox<>();
		spriteSelection.addItem("Select Sprite");
		for (String sprite: sprites) {
			spriteSelection.addItem(sprite);
		}
		spriteSelection.setBounds(1050, 200, 100, 100);
		this.add(spriteSelection);
		spriteSelection.setPreferredSize(new Dimension(100, 100));
		spriteSelection.setVisible(false);
	}
	
	public PanelButton createMakeButton() {
		PanelButton make = new PanelButton("Make", "make");
		make.addActionListener(al ->{
			this.spriteSelection.setVisible(true);
		});
		return make;
	}
	
	public PanelButton createPlayButton() {
		PanelButton play = new PanelButton("Play", "play");
		return play;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Element load(ObjectInputStream ip) {
		// TODO Auto-generated method stub
		return null;
	}

}
