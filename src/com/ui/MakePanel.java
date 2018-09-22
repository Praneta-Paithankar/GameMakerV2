package com.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.controller.GameMakerController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element, ItemListener{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private List<String> actions;
	private List<String> events;
	JComboBox<String> spriteSelection;
	private GameMakerController controller;
	private BufferedImage ballImage;
	private BufferedImage brickImage;
	private BufferedImage paddleImage;
	private List<JCheckBox> checkBox = new ArrayList<>();
	private CreateSpriteRequest newSprite;
	HashMap<String, CreateSpriteRequest> spriteRequestMap;

	public MakePanel() {
		setLayoutBehavior(new NullLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		//createButtons();
		sprites = new ArrayList<>(Constants.spriteTypes);
		spriteRequestMap = new HashMap<>();
		//createSpriteSelectionList();
		createImage();
		createCheckbox();
		//createBallActions();
		//createBallEvents();
	}
	
	public void createButtons(GameMakerController controller) {
		this.controller = controller;
		this.add(createMakeButton());
		this.add(createPlayButton());
		createDoneButton();
	}
	
	public void createCheckbox() {
		int y = Constants.CHECKBOX_Y;
		Font f = new Font("Serif", Font.BOLD, 20);
		for (String s: sprites) {
			JCheckBox c = new JCheckBox(s);
			c.setBounds(Constants.CHECKBOX_X, y, Constants.CHECKBOX_WIDTH, Constants.CHECKBOX_HEIGHT);
			c.setFont(f);
			c.addItemListener(this);
			checkBox.add(c);
			this.add(c);
			y += 150;
		}
	}
	
	public void createImage() {
		try {
            ballImage = ImageIO.read(new File(Constants.BALL_IMAGE));
            ballImage = resize(ballImage, 80, 70);
            brickImage = ImageIO.read(new File(Constants.BRICK_IMAGE));
            brickImage = resize(brickImage, 70, 70);
            paddleImage = ImageIO.read(new File(Constants.PADDLE_IMAGE));
            paddleImage = resize(paddleImage, 80, 50);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	log.error(e.getMessage());
        }
	}
	
	public PanelButton createMakeButton() {
		PanelButton make = new PanelButton("Make", "make");
		make.setBounds(50, 10, 100, 50);
		make.addActionListener(controller);
		return make;
	}
	
	public PanelButton createPlayButton() {
		PanelButton play = new PanelButton("Play", "play");
		play.setBounds(200, 10, 100, 50);
		return play;
	}
	
	public void createDoneButton() {
		JButton doneButton = new JButton("Done");
		doneButton.setBounds(350, 10, 100, 50);
		doneButton.setActionCommand("done");
		doneButton.addActionListener(controller);
		doneButton.setVisible(true);
		this.add(doneButton);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (ballImage != null) {
			g.drawImage(ballImage, 100, 100, this);
	    }
		if (ballImage != null) {
			g.drawImage(brickImage, 100, 250, this);
		}
		if (ballImage != null) {
			g.drawImage(paddleImage, 100, 400, this);
		}
	}
	
	private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int sel = e.getStateChange();
        if (sel == ItemEvent.SELECTED) {
        	for (JCheckBox c: checkBox) {
        		if (c==e.getSource()) {
        			
        			JTextField x = new JTextField();
        			JTextField y = new JTextField();
        			JComboBox<String> eventBox = new JComboBox<>(Constants.AVAILABLE_EVENTS);
        			JComboBox<String> actionBox = new JComboBox<>(Constants.AVAILABLE_ACTIONS);
        			actionBox.setVisible(true);
        			JOptionPane pane = new JOptionPane();
        			Object[] message = {
        			    "X Coordinate:", x,
        			    "Y Coordinate:", y,
        			    "Events:", eventBox,
        			    "Actions:", actionBox
        			};
        			
//        			eventBox.addActionListener(al -> {
//        				actionBox.setName("Actions:");
//        				actionBox.setVisible(true);
//        				
//        			});
        			Object[] options = {"More Action", "Ok", "Exit"}; 
        			UIManager.put("OptionPane.yesButtonText", "Save");
        			UIManager.put("OptionPane.noButtonText", "More Event-Action");
        			UIManager.put("OptionPane.cancelButtonText", "Cancel");
        			int option = pane.showConfirmDialog(null, message, "Sprite Details", pane.YES_NO_CANCEL_OPTION);
        			//System.out.println(x.getText()+ " "+ y.getText());
        			if (option == JOptionPane.YES_OPTION || option == JOptionPane.NO_OPTION) {	
        				if(spriteRequestMap.containsKey(c.getText()))
        					newSprite = spriteRequestMap.get(c.getText());
        				else
        					newSprite = new CreateSpriteRequest(c.getText(), Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
        				newSprite.addEventAction(eventBox.getSelectedItem().toString(), actionBox.getSelectedItem().toString());
        				//newSprite.getEventAction().put(eventBox.getSelectedItem().toString(), actionBox.getSelectedItem().toString());
        				//spriteRequestMap.put(c.getText(), newSprite);
        			} 
        			if (option == JOptionPane.NO_OPTION) {
        				Object[] eventMessage = {
                			    "Events:", eventBox,
                			    "Actions:", actionBox
                		};
        				while (option == JOptionPane.NO_OPTION) {
        					option = pane.showConfirmDialog(null, eventMessage, "Add event-action Details", pane.YES_NO_CANCEL_OPTION);
        					if (option == JOptionPane.CANCEL_OPTION) {
        						break;
        					}
        					newSprite.addEventAction(eventBox.getSelectedItem().toString(), actionBox.getSelectedItem().toString());
        				}
        			}
        		}
        	}
        } else {

        }
	}

}

