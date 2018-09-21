package com.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.behavior.BoxLayoutXAxisBehavior;
import com.behavior.BoxLayoutYAxisBehavior;
import com.behavior.FlowLayoutBehavior;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element, ItemListener{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private List<String> actions;
	private List<String> events;
	JComboBox<String> spriteSelection;
	private BufferedImage ballImage;
	private BufferedImage brickImage;
	private BufferedImage paddleImage;
	private List<JCheckBox> checkBox = new ArrayList<>();
	private CreateSpriteRequest newSprite;
	HashMap<String, CreateSpriteRequest> spriteRequestMap;

	public MakePanel() {
		setLayoutBehavior(new NullLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		createButtons();
		sprites = new ArrayList<>(Constants.spriteTypes);
		spriteRequestMap = new HashMap<>();
		//createSpriteSelectionList();
		createImage();
		createCheckbox();
		//createBallActions();
		//createBallEvents();
	}
	
	public void createButtons() {
		this.add(createMakeButton());
		this.add(createPlayButton());
	}
	
	public void createCheckbox() {
		JCheckBox ballCheckBox = new JCheckBox("Ball");
		ballCheckBox.setBounds(10, 100, 200, 100);
		ballCheckBox.setIcon(new ImageIcon(ballImage));
		ballCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
		ballCheckBox.addItemListener(this);
		checkBox.add(ballCheckBox);
		this.add(ballCheckBox);
		
		JCheckBox brickCheckBox = new JCheckBox("Brick", true);
		brickCheckBox.setBounds(10, 250, 200, 100);
		brickCheckBox.setIcon(new ImageIcon(brickImage));
		brickCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
		brickCheckBox.addItemListener(this);
		checkBox.add(brickCheckBox);
		this.add(brickCheckBox);
		
		JCheckBox paddleCheckBox = new JCheckBox("Paddle", true);
		paddleCheckBox.setBounds(10, 400, 200, 100);
		paddleCheckBox.setIcon(new ImageIcon(paddleImage));
		paddleCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
		paddleCheckBox.addItemListener(this);
		checkBox.add(paddleCheckBox);
		this.add(paddleCheckBox);
	}
	
	public void createImage() {
		try {
            ballImage = ImageIO.read(new File("./src/com/image/ball.png"));
            ballImage = resize(ballImage, 100, 100);
            brickImage = ImageIO.read(new File("./src/com/image/brick.jpg"));
            brickImage = resize(brickImage, 100, 100);
            paddleImage = ImageIO.read(new File("./src/com/image/paddle.png"));
            paddleImage = resize(paddleImage, 100, 100);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	log.error(e.getMessage());
        }
	}
	
	public void createBallActions() {
		JLabel ballAction = new JLabel("Action");
		ballAction.setBounds(180,100,150,30);
		ButtonGroup ball_action=new ButtonGroup();
		JRadioButton moveLeftRight=new JRadioButton("Move Left Right");
		moveLeftRight.setBounds(180,120,150,30);
		ball_action.add(moveLeftRight);
		
		JRadioButton moveUpDown=new JRadioButton("Move Up Down");
		moveUpDown.setBounds(180,140,150,30);
		ball_action.add(moveUpDown);
		
		JRadioButton shoot=new JRadioButton("Shoot");
		shoot.setBounds(180,160,150,30);
		ball_action.add(shoot);
		
		this.add(ballAction);
		this.add(moveLeftRight);
		this.add(moveUpDown);
		this.add(shoot);
	}
	
	public void createBallEvents() {
		JLabel ballEventLabel = new JLabel("Event");
		ballEventLabel.setBounds(330,100,150,30);
		
		ButtonGroup ball_events=new ButtonGroup();
		
		JRadioButton onTick=new JRadioButton("On Tick");
		onTick.setBounds(330,120,150,30);
		ball_events.add(onTick);
		
		JRadioButton onCollision=new JRadioButton("On collision");
		onCollision.setBounds(330,140,150,30);
		ball_events.add(onCollision);
		
		JRadioButton keyPressed=new JRadioButton("On Key Pressed");
		keyPressed.setBounds(330,160,150,30);
		ball_events.add(keyPressed);
		
		this.add(onCollision);
		this.add(onTick);
		this.add(keyPressed);
		this.add(ballEventLabel);
	}
	
	public void createBallEvent() {
		
	}
	
	public void createSpriteSelectionList() {
		spriteSelection = new JComboBox<>();
		spriteSelection.addItem("Select Sprite");
		for (String sprite: sprites) {
			spriteSelection.addItem(sprite);
		}
		spriteSelection.setBounds(100, 70, 200, 50);
		spriteSelection.setPreferredSize(new Dimension(300, 100));
		this.add(spriteSelection);
		spriteSelection.setVisible(false);
	}
	
	public PanelButton createMakeButton() {
		PanelButton make = new PanelButton("Make", "make");
		make.setBounds(150, 10, 100, 50);
		make.addActionListener(al ->{
			this.spriteSelection.setVisible(true);
		});
		return make;
	}
	
	public PanelButton createPlayButton() {
		PanelButton play = new PanelButton("Play", "play");
		play.setBounds(300, 10, 100, 50);
		return play;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (ballImage != null) {
	       // g.drawImage(ballImage, 10, 200, this);
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
//        			
        			int option = pane.showConfirmDialog(null, message, "Sprite Details", pane.OK_CANCEL_OPTION);
        			System.out.println(x.getText()+ " "+ y.getText());
        			if (option == JOptionPane.OK_OPTION) {
//        				int xVal = -1;
//        				int yVal = -1;
//        				if(Integer.parseInt(x.getText()) >= 0)
//        					if(xVal <= 0)
//        					
        				if(spriteRequestMap.containsKey(c.getText()))
        					newSprite = spriteRequestMap.get(c.getText());
        				else
        					newSprite = new CreateSpriteRequest(c.getText(), Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
        				newSprite.addEventAction(eventBox.getSelectedItem().toString(), actionBox.getSelectedItem().toString());
        				newSprite.getEventAction().put(eventBox.getSelectedItem().toString(), actionBox.getSelectedItem().toString());
        				spriteRequestMap.put(c.getText(), newSprite);
        			}      			
        		}
        	}
        } else {

        }
	}

}

