package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.behavior.NullLayoutBehavior;
import com.component.SpriteElement;
import com.controller.GameMakerController;
import com.infrastruture.AbstractPanel;
import com.infrastruture.Constants;
import com.infrastruture.Element;

public class MakePanel extends AbstractPanel implements Element, ActionListener{
	protected static Logger log = Logger.getLogger(GamePanel.class);
	private List<String> sprites;
	private GameMakerController controller;
	private BufferedImage ballImage;
	private BufferedImage brickImage;
	private BufferedImage paddleImage;
	private List<JCheckBox> checkBox = new ArrayList<>();
	private CreateSpriteRequest newSprite;
	
	private boolean isShooter;
	
	private int shooterXvel;
	private int shooterYvel;
	private int shooterWidth;
	private int shooterHeight;
	
	private String shooterSpriteID;
	private String shooterCategory;
	private String imagePath;
	private String path;
	
	private JPanel mainOptionPanel;
	private JPanel subOptionPanel1;
	private JPanel subOptionPanel2;
	
	private GridLayout gbLayout;
	
	private JTextField x;
	private JTextField y;
	private JTextField xVel;
	private JTextField yVel;
	private JTextField spriteTextField;
	private JTextField categoryTextField;
	private JTextField width;
	private JTextField height;
	
	private JLabel xlabel;
	private JLabel ylabel;
	private JLabel xVellabel;	
	private JLabel yVellabel;	
	private JLabel spriteId;	
	private JLabel categoryId;
	private JLabel colorlabel;
	private JLabel imagelabel;
	private JLabel shooterlabel;

	private JLabel collisionslabel;
	private JLabel actionDropDownlabel;
	private JLabel eventDropDownlabel;
	private JLabel actionlabel;

	private JLabel spriteCategoryDropDownlabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel actionIDDropDownlabel;

	private ButtonGroup group;
	private JButton colorButton;
	private JButton imageButton;
	private JButton shooterButton;
	private JButton actionsButton;
	private JButton collisionsButton;
	
	private JRadioButton gameWinRadioButton;
	private JRadioButton gameLoseRadioButton;
	private JRadioButton notApplicableRadioButton;
	private JButton createRectangle;
	private JButton createCircle;
	
	private SpriteElement spriteElement;
	private Color spriteColor;
	private HashMap<String,String> collidables;
	
	private HashMap<String,String> collisionMap=new HashMap<>();
	private HashMap<String,String> shooterEventActionMap=new HashMap<>();
	private HashMap<String, CreateSpriteRequest> spriteRequestMap;
	private HashMap<String,String> eventActionMap=new HashMap<String, String>();

	private JLabel shooterIntervallabel;
	private JTextField shooterIntervalTextField;
	private String shooterInterval;
	private BufferedImage image;
	
	
	public MakePanel() {
		setLayoutBehavior(new NullLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		sprites = new ArrayList<>(Constants.spriteTypes);
		spriteRequestMap = new HashMap<>();
		setShooter(false);
	}
	
	public HashMap<String, String> getShooterEventActionMap() {
		return shooterEventActionMap;
	}
	
	public void setShooterEventActionMap(HashMap<String, String> shooterEventActionMap) {
		this.shooterEventActionMap = shooterEventActionMap;
	}
	
	public HashMap getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(HashMap collisionMap) {
		this.collisionMap = collisionMap;
	}
	
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

	public boolean isShooter() {
		return isShooter;
	}

	public void setShooter(boolean isShooter) {
		this.isShooter = isShooter;
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
	
	public SpriteElement getSpriteElement() {
		return spriteElement;
	}

	public void setSpriteElement(SpriteElement spriteElement) {
		this.spriteElement = spriteElement;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	protected int getSelectedRadioButton() {
		int counter=0;
		for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {	
	                return counter;
	        }
	        counter++;
	    }
		return 2;
	}
	
	public void createButtons(GameMakerController controller) {
		this.controller = controller;
		this.add(createMakeButton());
		this.add(createPlayButton());
		this.add(createSetBackgroundButton());
	
		createCircleButton();
		createRectangleButton();
		createSaveButton();
		createLoadButton();
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
	
	public PanelButton createSetBackgroundButton()
	{
		PanelButton setBackground = new PanelButton("Background", "setBackground");
		setBackground.setBounds(50, 100, 104, 50);
		setBackground.addActionListener(controller);
		setBackground.setFocusable(false);
		return setBackground;
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
	
	public void createCircleButton()
	{
		createCircle = new JButton("Circle");
		createCircle.setBounds(50, 200, 100, 50);
		createCircle.setActionCommand("Circle");
		createCircle.addActionListener(controller);
		createCircle.setVisible(false);
		createCircle.setFocusable(false);
		this.add(createCircle);
	}
	
	public void createRectangleButton()
	{
		createRectangle = new JButton("Rectangle");
		createRectangle.setBounds(50, 300, 100, 50);
		createRectangle.setActionCommand("Rectangle");
		createRectangle.addActionListener(controller);
		createRectangle.setVisible(false);
		createRectangle.setFocusable(false);
		this.add(createRectangle);
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
	
	public void buttonVisiblity()
	{
		createCircle.setVisible(true);
		createRectangle.setVisible(true);
	}
	
	private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

	public int createOptionPopUp(boolean isShooter) {
		mainOptionPanel= new JPanel();
    	mainOptionPanel.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
    	subOptionPanel1=new JPanel();
    	subOptionPanel2=new JPanel();
    	gbLayout=new GridLayout(0,2,10,10);
    	subOptionPanel1.setLayout(gbLayout);
    	
    	spriteId = new JLabel("Sprite ID :");
    	subOptionPanel1.add(spriteId);
    	
    	spriteTextField = new JTextField(10);
    	subOptionPanel1.add(spriteTextField);
    	
    	categoryId = new JLabel("Category :");
    	subOptionPanel1.add(categoryId);
    	
    	categoryTextField = new JTextField(10);
    	subOptionPanel1.add(categoryTextField);
    	
    	if(!isShooter) {
	    	xlabel = new JLabel("X :");
	    	subOptionPanel1.add(xlabel);
	    	
	    	x = new JTextField(10);
	    	subOptionPanel1.add(x);
	    	
	    	ylabel = new JLabel("Y :");
	    	subOptionPanel1.add(ylabel);
	    	
	    	y = new JTextField(10);
	    	subOptionPanel1.add(y);
	    	}
    	widthLabel = new JLabel("Width :");
    	subOptionPanel1.add(widthLabel);
    	
    	width = new JTextField(10);
    	subOptionPanel1.add(width);
    	
    	heightLabel = new JLabel("Height :");
    	subOptionPanel1.add(heightLabel);
    	
    	height = new JTextField(10);
    	subOptionPanel1.add(height);
    	
    	xVellabel = new JLabel("XVelocity :");
    	subOptionPanel1.add(xVellabel);
    	
    	xVel = new JTextField(10);
    	subOptionPanel1.add(xVel);
    	
    	yVellabel = new JLabel("YVelocity :");
    	subOptionPanel1.add(yVellabel);
    	
    	yVel = new JTextField(10);
    	subOptionPanel1.add(yVel);
    	
    	colorlabel = new JLabel("Color :");
    	subOptionPanel1.add(colorlabel);
    	
    	colorButton = new JButton("Select Color");
    	colorButton.setActionCommand("Color");
    	colorButton.addActionListener(this);
    	subOptionPanel1.add(colorButton);
    	
    	imagelabel = new JLabel("Image :");
    	subOptionPanel1.add(imagelabel);
    	
    	imageButton = new JButton("Image");
    	subOptionPanel1.add(imageButton);
    	imageButton.addActionListener(this);
    	
    	if(!isShooter) {
	    	shooterlabel = new JLabel("Shooter :");
	    	subOptionPanel1.add(shooterlabel);
	    	
	    	
	    	shooterButton = new JButton("Shooter");
	    	shooterButton.addActionListener(this);
	    	subOptionPanel1.add(shooterButton);
	    	
    	}
    	else {
    		shooterIntervallabel = new JLabel("Time in MilliSeconds :");
	    	subOptionPanel1.add(shooterIntervallabel);
	    	
	    	shooterIntervalTextField = new JTextField(10);
	    	subOptionPanel1.add(shooterIntervalTextField);
    	}
    	actionlabel = new JLabel("Actions :");
    	subOptionPanel1.add(actionlabel);
    	
    	actionsButton = new JButton("Actions");
    	subOptionPanel1.add(actionsButton);
    	actionsButton.addActionListener(this);
    	
    	if(!isShooter) {
	    	collisionslabel = new JLabel("Collisions :");
	    	subOptionPanel1.add(collisionslabel);
	    	
	    	
	    	collisionsButton = new JButton("Collisions");
	    	subOptionPanel1.add(collisionsButton);
	    	collisionsButton.addActionListener(this);
	    	
	    	gameWinRadioButton = new JRadioButton("Game Win");
	    	gameLoseRadioButton=new JRadioButton("Game Lose");
	    	notApplicableRadioButton=new JRadioButton("Not Applicable");
	    	
	    	notApplicableRadioButton.setSelected(true);
	    	group = new ButtonGroup();
	    	group.add(gameWinRadioButton);
	    	group.add(gameLoseRadioButton);
	    	group.add(notApplicableRadioButton);
	    	
	    	subOptionPanel2.add(gameWinRadioButton);
	    	subOptionPanel2.add(gameLoseRadioButton);
	    	subOptionPanel2.add(notApplicableRadioButton);
    	}
    	mainOptionPanel.add(subOptionPanel1);
    	mainOptionPanel.add(subOptionPanel2);
    	
    	eventActionMap=new HashMap();
    	collisionMap=new HashMap();
    	
    	JOptionPane pane = new JOptionPane();
    	int option = pane.showConfirmDialog(null, mainOptionPanel, "Sprite Details", pane.YES_NO_CANCEL_OPTION);
    	
    	return option;
	}
	private void getFormData(int option,boolean isShooter, String objectType) {
		// TODO Auto-generated method stub
		if(isShooter) {
			shooterXvel=Integer.parseInt(xVel.getText());
			shooterYvel=Integer.parseInt(yVel.getText());
			shooterWidth=Integer.parseInt(width.getText());
			shooterHeight=Integer.parseInt(height.getText());
			shooterSpriteID = spriteTextField.getText();
			shooterCategory = categoryTextField.getText();
			shooterEventActionMap = getShooterEventActionMap();
			shooterInterval=shooterIntervalTextField.getText();
			setShooter(false);
			log.info(shooterXvel);
			log.info(shooterYvel);
			log.info(shooterWidth);
			log.info(shooterHeight);
			log.info(shooterSpriteID);
			log.info(shooterEventActionMap);
			return;
			
		}
		if(option == JOptionPane.YES_OPTION) {
			
			int tempX = Integer.parseInt(x.getText());
			int tempY = Integer.parseInt(y.getText());
			int tempXVel = Integer.parseInt(xVel.getText());
			int tempYVel = Integer.parseInt(yVel.getText());
			int tempWidth = Integer.parseInt(width.getText());
			int tempHeight = Integer.parseInt(height.getText());
			String spriteID = spriteTextField.getText();
			String category = categoryTextField.getText();
			HashMap<String, String> eventAction = getEventActionMap();
			
			
			newSprite = new CreateSpriteRequest(objectType, tempX, tempY, tempXVel, tempYVel, tempWidth, tempHeight, 
												spriteColor, getPath(), spriteID, category, eventAction,getSelectedRadioButton(),getCollisionMap(),1);
//			getEventActionMap();// to get event and action mapping
			getCollisionMap();	// to get mapping of event and collision
		}
		else {
//			spriteElement = new SpriteElement("", x, y,  )
		}
		
		if(option != JOptionPane.CANCEL_OPTION) {
        	controller.done();
		}
		
	}
	
	public void createSpriteElement(String objectType) {
		int option=createOptionPopUp(isShooter());
    	if(option == JOptionPane.YES_OPTION)
    		getFormData(option,isShooter(),objectType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "Actions" :
						createActionPopUp(isShooter);
						break;
		case "Collisions":
						   createCollisionPopUp();
						   break;
		case "Shooter" :  	setShooter(true);
							int option=createOptionPopUp(isShooter());
							if(option == JOptionPane.YES_OPTION)
					    		getFormData(option,isShooter(),"Circle");
							else
								setShooter(false);
							break;			
		case "Image" : 
			
					setPath(fileExplorer());
						break;
		case "Color":
					setSpriteColor(colorChooser());
					break;
		default : log.error("switch hitting the default case of makepanel actionlistner");
						break;
		}
		//createOptionPopUp();
		
	}

	public String fileExplorer()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		return "";
	}
	
	public String getBackgroundImagePath()
	{
		return fileExplorer();
//		windowFrame.getGamePanel().setImage(imagePath);
	}
	
	public Color colorChooser() {
		Color color = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
		return color;
	}

	private void createActionPopUp(Boolean isShooter) {
		// TODO Auto-generated method stub
		
		JOptionPane subActionpane = new JOptionPane();
		subActionpane.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
		
		actionDropDownlabel = new JLabel("Actions :");
		JComboBox<String> actionDropDownList = new JComboBox<>(Constants.AVAILABLE_ACTIONS);
		
		eventDropDownlabel = new JLabel("Events :");
		JComboBox<String> eventDropDownList = new JComboBox<>(Constants.AVAILABLE_EVENTS);
		
		Object[] message = {  
				eventDropDownlabel, eventDropDownList,
			    actionDropDownlabel, actionDropDownList
			};
    	
		int option = subActionpane.showConfirmDialog(null, message, "Event Action Mapping", subActionpane.OK_CANCEL_OPTION);
		
		if(option== JOptionPane.OK_OPTION) {
			if(!isShooter) {
			eventActionMap.put( eventDropDownList.getSelectedItem().toString(),actionDropDownList.getSelectedItem().toString());
			}
			else
			{
				shooterEventActionMap.put( eventDropDownList.getSelectedItem().toString(),actionDropDownList.getSelectedItem().toString());
			}
		}	
		else {
			
		}
		
	}
	
	private void createCollisionPopUp() {
		// TODO Auto-generated method stub
		
		JOptionPane subCollisionpane = new JOptionPane();
		subCollisionpane.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
		
		String [] spriteIds= {"b1","b2", "b3"};
		String[] categoryIds= {"category1"};
//		String[] categoryandsprite.add
		
		actionIDDropDownlabel = new JLabel("Actions :");
		
		JComboBox<String> actionDropDownList1 = new JComboBox<>(Constants.AVAILABLE_ACTIONS);
		
		spriteCategoryDropDownlabel = new JLabel("Sprite Id/Category Id :");
		JTextField categoryTextField1 = new JTextField();
		
		Object[] message = {  
				actionIDDropDownlabel, actionDropDownList1,
				spriteCategoryDropDownlabel, categoryTextField1
			};
    	
		int option = subCollisionpane.showConfirmDialog(null, message, "Collison between Ids", subCollisionpane.OK_CANCEL_OPTION);
		
		if(option== JOptionPane.OK_OPTION) {
			collisionMap.put(categoryTextField1.getText(),actionDropDownList1.getSelectedItem().toString());
			log.error(collisionMap.entrySet().toString());
		}	
		else {
			
		}
		
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
}

