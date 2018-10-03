package com.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.behavior.NullLayoutBehavior;
import com.controller.GameMakerController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element, ItemListener, ActionListener{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private GameMakerController controller;
	private BufferedImage ballImage;
	private BufferedImage brickImage;
	private BufferedImage paddleImage;
	private List<JCheckBox> checkBox = new ArrayList<>();
	private CreateSpriteRequest newSprite;
	private HashMap<String, CreateSpriteRequest> spriteRequestMap;
	private HashMap<String,String> eventActionMap;
	
	private JPanel subOptionPanel1;
	private GridLayout gbLayout;
	private GridBagConstraints gbConstraints;
	
	private JTextField x;
	private JLabel xlabel;
	private JLabel ylabel;
	private JTextField y;
	private JLabel xVellabel;
	private JTextField xVel;
	private JLabel yVellabel;
	private JTextField yVel;
	private JLabel spriteId;
	private JTextField spriteTextField;
	private JLabel categoryId;
	private JTextField categoryTextField;
	private JLabel colorlabel;
	private JButton colorButton;
	private JButton imageButton;
	private JLabel imagelabel;
	private JLabel shooterlabel;
	private JCheckBox shooterCheckbox;
	private JButton shooterButton;
	private JRadioButton gameWinRadioButton;
	private JRadioButton gameLooseRadioButton;
	private JRadioButton notApplicableRadioButton;
	private JLabel gameWinlabel;
	private JLabel gameLooselabel;
	private JLabel notApplicablelabel;
	private JPanel mainOptionPanel;
	private JPanel subOptionPanel2;
	private JLabel actionlabel;
	private JButton actionsButton;
	private JLabel collisionslabel;
	private JButton collisionsButton;
	private JLabel actionDropDownlabel;
	private JLabel eventDropDownlabel;
	private String imagePath;
	private Color spriteColor;
	private HashMap<String,String> collidables;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Color getSpriteColor() {
		return spriteColor;
	}

	public void setSpriteColor(Color spriteColor) {
		this.spriteColor = spriteColor;
	}

	public HashMap<String, String> getCollidables() {
		return collidables;
	}

	public void setCollidables(HashMap<String, String> collidables) {
		this.collidables = collidables;
	}

	public HashMap<String, String> getEventActionMap() {
		return eventActionMap;
	}

	public void setEventActionMap(HashMap<String, String> eventActionMap) {
		this.eventActionMap = eventActionMap;
	}

	
	public MakePanel() {
		setLayoutBehavior(new NullLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		//createButtons();
		sprites = new ArrayList<>(Constants.spriteTypes);
		spriteRequestMap = new HashMap<>();
		//createImage();
		createCheckbox();
		//createBallActions();
		//createBallEvents();
	}
	
	public void createButtons(GameMakerController controller) {
		this.controller = controller;
		this.add(createMakeButton());
		this.add(createPlayButton());
		createSaveButton();
		createLoadButton();
	}
	
	public void createCheckbox() {
		int y = Constants.CHECKBOX_Y;
		Font f = new Font("Serif", Font.BOLD, 20);
		for (String s: sprites) {
			JCheckBox c = new JCheckBox(s);
			c.setBounds(Constants.CHECKBOX_X, y, Constants.CHECKBOX_WIDTH, Constants.CHECKBOX_HEIGHT);
			c.setFont(f);
			c.addItemListener(this);
			c.setFocusable(false);
			c.setVisible(false);
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
        	log.error(e.getMessage());
        }
	}
	
	public PanelButton createMakeButton() {
		PanelButton make = new PanelButton("Make", "make");
		make.setBounds(50, 10, 100, 50);
		make.addActionListener(controller);
		make.setFocusable(false);
		return make;
	}
	
	public PanelButton createPlayButton() {
		PanelButton play = new PanelButton("Play", "play");
		play.setBounds(200, 10, 100, 50);
		play.addActionListener(controller);
		play.setFocusable(false);
		return play;
	}
	
	public void createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(50, 650, 100, 50);
		saveButton.setActionCommand("save");
		saveButton.addActionListener(controller);
		saveButton.setVisible(true);
		saveButton.setFocusable(false);
		this.add(saveButton);
	}
	
	public void createLoadButton() {
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(200, 650, 100, 50);
		loadButton.setActionCommand("load");
		loadButton.addActionListener(controller);
		loadButton.setVisible(true);
		loadButton.setFocusable(false);
		this.add(loadButton);
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

	public GridBagConstraints setGridBagConstraints(int fill, int gridx, int gridy) {
		gbConstraints= new GridBagConstraints();
		gbConstraints.fill = fill;
    	gbConstraints.gridx = gridx;
    	gbConstraints.gridy = gridy;
    	gbConstraints.insets= new Insets(5, 10 , 10, 10);
		return gbConstraints;
	}
	
	public void createOptionPopUp() {
		mainOptionPanel= new JPanel();
    	mainOptionPanel.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
    	subOptionPanel1=new JPanel();
    	subOptionPanel2=new JPanel();
    	gbLayout=new GridLayout(0,2,10,10);
    	subOptionPanel1.setLayout(gbLayout);
    	
    	spriteId = new JLabel("Sprite ID :");
    	subOptionPanel1.add(spriteId);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 0));
    	
    	spriteTextField = new JTextField(10);
    	subOptionPanel1.add(spriteTextField);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 0));
    	
    	categoryId = new JLabel("category :");
    	subOptionPanel1.add(categoryId);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 0));
    	
    	categoryTextField = new JTextField(10);
    	subOptionPanel1.add(categoryTextField);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 0));
    	
    	
    	xlabel = new JLabel("X :");
    	subOptionPanel1.add(xlabel);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 1));
    	
    	x = new JTextField(10);
    	subOptionPanel1.add(x);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 1));
    	
    	ylabel = new JLabel("Y :");
    	subOptionPanel1.add(ylabel);//,setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 2));
    	
    	y = new JTextField(10);
    	subOptionPanel1.add(y);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 2));
    	
    	xVellabel = new JLabel("XVelocity :");
    	subOptionPanel1.add(xVellabel);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 3));
    	
    	xVel = new JTextField(10);
    	subOptionPanel1.add(xVel);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 3));
    	
    	yVellabel = new JLabel("YVelocity :");
    	subOptionPanel1.add(yVellabel);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 0, 4));
    	
    	yVel = new JTextField(10);
    	subOptionPanel1.add(yVel);//, setGridBagConstraints(GridBagConstraints.HORIZONTAL, 1, 4));
    	
    	colorlabel = new JLabel("Color :");
    	subOptionPanel1.add(colorlabel);
    	
    	colorButton = new JButton("Select Color");
    	subOptionPanel1.add(colorButton);
    	
    	imagelabel = new JLabel("Image :");
    	subOptionPanel1.add(imagelabel);
    	
    	imageButton = new JButton("Select Image");
    	subOptionPanel1.add(imageButton);
    	imageButton.addActionListener(this);
    	
    	shooterlabel = new JLabel("Shooter :");
    	subOptionPanel1.add(shooterlabel);
    	
    	shooterButton = new JButton("Select Shooter");
    	subOptionPanel1.add(shooterButton);
    	
    	actionlabel = new JLabel("Actions :");
    	subOptionPanel1.add(actionlabel);
    	
    	actionsButton = new JButton("Actions");
    	subOptionPanel1.add(actionsButton);
    	actionsButton.addActionListener(this);
    	
    	collisionslabel = new JLabel("Collisions :");
    	subOptionPanel1.add(collisionslabel);
    	
    	
    	collisionsButton = new JButton("Collisions");
    	subOptionPanel1.add(collisionsButton);
    	collisionsButton.addActionListener(this);
    	
    	gameWinRadioButton = new JRadioButton("Game Win");
    	gameLooseRadioButton=new JRadioButton("Game Loose");
    	notApplicableRadioButton=new JRadioButton("Not Applicable");
    	
    	ButtonGroup group = new ButtonGroup();
    	group.add(gameWinRadioButton);
    	group.add(gameLooseRadioButton);
    	group.add(notApplicableRadioButton);
    	
    	subOptionPanel2.add(gameWinRadioButton);
    	subOptionPanel2.add(gameLooseRadioButton);
    	subOptionPanel2.add(notApplicableRadioButton);
    
    	mainOptionPanel.add(subOptionPanel1);
    	mainOptionPanel.add(subOptionPanel2);
    	
    	
    	JOptionPane pane = new JOptionPane();
    	int option = pane.showConfirmDialog(null, mainOptionPanel, "Sprite Details", pane.YES_NO_CANCEL_OPTION);
    	
    	getFormData(option);
	}
	private void getFormData(int option) {
		// TODO Auto-generated method stub
		if(option == JOptionPane.YES_OPTION) {
			//newSprite = new CreateSpriteRequest(c.getText(), Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
			x.getText();
			y.getText();
			xVel.getText();
			yVel.getText();
			getEventActionMap();// to get event and action mapping
			
			
			
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int sel = e.getStateChange();
        if (sel == ItemEvent.SELECTED) {
        	createOptionPopUp();
        	//createOptionPopUp();
        } else {
        	
        }
	}

	public CreateSpriteRequest getNewSprite() {
		return newSprite;
	}

	public void setNewSprite(CreateSpriteRequest newSprite) {
		this.newSprite = newSprite;
	}

	public HashMap<String, CreateSpriteRequest> getSpriteRequestMap() {
		return spriteRequestMap;
	}

	public void setSpriteRequestMap(HashMap<String, CreateSpriteRequest> spriteRequestMap) {
		this.spriteRequestMap = spriteRequestMap;
	}

	public List<JCheckBox> getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(List<JCheckBox> checkBox) {
		this.checkBox = checkBox;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "Actions" :eventActionMap=new HashMap<>(); 
						createActionPopUp();
						break;
		default : log.error("switch hitting the default case of makepanel actionlistner");
						break;
		}
		System.out.println(e.getActionCommand());
		//createOptionPopUp();
		
	}


	private void createActionPopUp() {
		// TODO Auto-generated method stub
		
		JOptionPane subActionpane = new JOptionPane();
		subActionpane.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
		
		actionDropDownlabel = new JLabel("Actions :");
    	
		JComboBox<String> actionDropDownList = new JComboBox<>(Constants.AVAILABLE_ACTIONS);
		
		eventDropDownlabel = new JLabel("Actions :");
    	
		JComboBox<String> eventDropDownList = new JComboBox<>(Constants.AVAILABLE_ACTIONS);
		
		actionDropDownList.setVisible(true);
		Object[] message = {  
				eventDropDownlabel, eventDropDownList,
			    actionDropDownlabel, actionDropDownList
			};
    	
		int option = subActionpane.showConfirmDialog(null, message, "Event Action Mapping", subActionpane.OK_CANCEL_OPTION);
		
		if(option== JOptionPane.OK_OPTION) {
			eventActionMap.put(actionDropDownList.getSelectedItem().toString(), eventDropDownList.getSelectedItem().toString());
		}	
		
	}
	
	
	

}

