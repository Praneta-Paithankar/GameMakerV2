package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
	private String objectType;
	private boolean isShooter;
	private String shooterImagePath;
	
	private int shooterXvel;
	private int shooterYvel;
	private int shooterWidth;
	private int shooterHeight;
	private int numberOfBullets;
	
	
	
	public int getNumberOfBullets() {
		int bulletCount=Integer.parseInt(shooterNumberOfBullets.getText());
		return (bulletCount==-1?Integer.MAX_VALUE:bulletCount);
	}

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
	
	private JLabel shooterXLabel;
	private JLabel shooterYLabel;
	private JLabel shooterXVel;
	private JLabel shooterYVel;
	private JLabel shooterColorLabel;
	private JLabel shooterWidthLabel;
	
	private JLabel shooterHeightLabel;
	private JLabel shooterSpriteIdLabel;
	private JLabel shooterCategoryLabel;
	private JLabel Intervallabel;
	private JLabel shooterActionLabel;
	private JLabel shooterIntervallabel;
	
	private JTextField IntervalTextField;
	private JTextField shooterWidthField;
	private JTextField shooterHeightField;
	private JTextField shooterSpriteCategory;
	private JTextField shooterSpriteId;
	private JTextField shooterYVelField;
	private JTextField shooterXVelField;
	private JTextField shooterIntervalTextField;
	
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
	
	private CreateSpriteRequest shootSprite;
	private int shooterInterval;
	private BufferedImage image;
	private JButton shooterActionsButton;
	private JButton shooterColorButton;
	private JPanel shooterSubOptionPanel1;
	private JLabel shooterImageLabel;
	private JButton shooterImageButton;
	private JLabel shooterNumberOfBulletsLabel;
	private JTextField shooterNumberOfBullets;

	
	
	public MakePanel() {
		setLayoutBehavior(new NullLayoutBehavior());
		performUpdateLayout(this, Constants.MAKE_PANEL_WIDTH,Constants.MAKE_PANEL_HEIGHT);
		sprites = new ArrayList<>(Constants.SPRITETYPES);
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
	public void setPath(String path, boolean isShooter) {
		if(isShooter)
			setShooterImagePath(path);
		else
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
		JOptionPane pane = new JOptionPane();
    	mainOptionPanel.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
    	subOptionPanel1=new JPanel();
    	subOptionPanel2=new JPanel();
    	gbLayout=new GridLayout(0,2,10,10);
    	subOptionPanel1.setLayout(gbLayout);
    	
    	
    	spriteId = new JLabel("Sprite ID :");
    	categoryId = new JLabel("Category :");
    	xlabel = new JLabel("X :");
    	ylabel = new JLabel("Y :");
    	widthLabel = new JLabel("Width :");
    	heightLabel = new JLabel("Height :");
    	xVellabel = new JLabel("XVelocity :");
    	yVellabel = new JLabel("YVelocity :");
    	Intervallabel = new JLabel("Time Interval in MilliSeconds :");
    	imagelabel = new JLabel("Image :");
    	shooterlabel = new JLabel("Shooter :");
    	actionlabel = new JLabel("Actions :");
    	colorlabel = new JLabel("Color :");
    	collisionslabel = new JLabel("Collisions :");
    	
 
    	spriteTextField = new JTextField(10);
    	categoryTextField = new JTextField(10);
    	x = new JTextField(10);
    	y = new JTextField(10);
    	width = new JTextField(10);
    	height = new JTextField(10);
    	xVel = new JTextField(10);
    	yVel = new JTextField(10);
    	IntervalTextField = new JTextField(10);
    	
    	colorButton = new JButton("Select Color");
    	colorButton.setActionCommand("Color");
    	colorButton.addActionListener(this);
    	
    	imageButton = new JButton("Image");
    	shooterButton = new JButton("Shooter");
    	actionsButton = new JButton("Actions");
    	collisionsButton = new JButton("Collisions");
    	
    	
    	imageButton.addActionListener(this);
    	shooterButton.addActionListener(this);
	    actionsButton.addActionListener(this);
    	collisionsButton.addActionListener(this);
    	
    	subOptionPanel1.add(spriteId);
    	subOptionPanel1.add(spriteTextField);
    	subOptionPanel1.add(categoryId);
    	subOptionPanel1.add(categoryTextField);
    	subOptionPanel1.add(xlabel);
    	subOptionPanel1.add(x);
	    subOptionPanel1.add(ylabel);
	    subOptionPanel1.add(y);
	    subOptionPanel1.add(widthLabel);
    	subOptionPanel1.add(width);
    	subOptionPanel1.add(heightLabel);
    	subOptionPanel1.add(height);
    	subOptionPanel1.add(xVellabel);
    	subOptionPanel1.add(xVel);
    	subOptionPanel1.add(yVellabel);
    	subOptionPanel1.add(yVel);
    	subOptionPanel1.add(Intervallabel);
	    subOptionPanel1.add(IntervalTextField);
    	subOptionPanel1.add(colorlabel);
    	subOptionPanel1.add(colorButton);
    	subOptionPanel1.add(imagelabel);
    	subOptionPanel1.add(imageButton);
    	subOptionPanel1.add(shooterlabel);
    	subOptionPanel1.add(shooterButton);
	    subOptionPanel1.add(actionlabel);
	    subOptionPanel1.add(actionsButton);
    	subOptionPanel1.add(collisionslabel);
    	subOptionPanel1.add(collisionsButton);   	
	    
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

    	mainOptionPanel.add(subOptionPanel1);
    	mainOptionPanel.add(subOptionPanel2);
    	
    	eventActionMap=new HashMap();
    	collisionMap=new HashMap();
    	
    	int option = pane.showConfirmDialog(null, mainOptionPanel, "Sprite Details", pane.YES_NO_CANCEL_OPTION);
    	
    	
    	return option;
	}
	private void getFormData(int option,boolean isShooter, String objectType) {
		// TODO Auto-generated method stub
		if(isShooter) {
			
			shooterXvel=Integer.parseInt(shooterXVelField.getText());
			shooterYvel=Integer.parseInt(shooterYVelField.getText());
			shooterWidth=Integer.parseInt(shooterWidthField.getText());
			shooterHeight=Integer.parseInt(shooterHeightField.getText());
			shooterSpriteID = shooterSpriteId.getText();
			shooterCategory = shooterSpriteCategory.getText();
			shooterEventActionMap = getShooterEventActionMap();
			shooterInterval=Integer.parseInt(shooterIntervalTextField.getText());
			
			shootSprite = new CreateSpriteRequest("Circle", -1, -1, shooterXvel, shooterYvel, shooterWidth, shooterHeight, 
					Color.BLACK, getShooterImagePath(), shooterSpriteID, shooterCategory, shooterEventActionMap,Constants.GAME_NOT_APPLICABLE_COMPONENT,new HashMap(),shooterInterval);
			
			setShooter(false);
			log.info(shooterSpriteID);
			log.info(shooterCategory);
			log.info(shooterXvel);
			log.info(shooterYvel);
			log.info(shooterWidth);
			log.info(shooterHeight);
			log.info(shooterEventActionMap);
			log.info(getNumberOfBullets());
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
			int spriteInterval=Integer.parseInt(IntervalTextField.getText());
			HashMap<String, String> eventAction = getEventActionMap();
			
			newSprite = new CreateSpriteRequest(objectType, tempX, tempY, tempXVel, tempYVel, tempWidth, tempHeight, 
												spriteColor, getPath(), spriteID, category, eventAction,getSelectedRadioButton(),getCollisionMap(),spriteInterval);

			getCollisionMap();	// to get mapping of event and collision
		}
		else {
			setShootSprite(null);
		}
		
		if(option != JOptionPane.CANCEL_OPTION) {
        	controller.done();
		}
		
	}
	
	public void createSpriteElement(String objectType) {
		setObjectType(objectType);
		int option=createOptionPopUp(isShooter());
    	if(option == JOptionPane.YES_OPTION)
    		getFormData(option,isShooter(),objectType);
	}


	public CreateSpriteRequest getShootSprite() {
		return shootSprite;
	}

	public void setShootSprite(CreateSpriteRequest shootSprite) {
		this.shootSprite = shootSprite;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Actions" :
						createActionPopUp(isShooter);
						break;
		case "Collisions":
						   createCollisionPopUp();
						   break;
		case "Shooter" :  
							createShooterPopUp();
							break;
							
		case "Image" : 
					setPath(fileExplorer(), isShooter());
						break;
		case "Color":
					setSpriteColor(colorChooser());
					break;
		default : log.error("switch hitting the default case of makepanel actionlistner");
						break;
		}
		
	}

	private void createShooterPopUp() {
		JOptionPane subActionpane = new JOptionPane();
		shooterSubOptionPanel1=new JPanel();
		gbLayout=new GridLayout(0,2,10,10);
		shooterSubOptionPanel1.setLayout(gbLayout);
		setShooter(true);
		
		shooterXVel=new JLabel("Xvel :");
		shooterYVel=new JLabel("YVel :");
		shooterColorLabel=new JLabel("Color :");
		shooterWidthLabel= new JLabel("Width :");
		shooterHeightLabel=new JLabel("Height :");
		shooterSpriteIdLabel=new JLabel("Sprite Id :");
		shooterCategoryLabel=new JLabel("Category :");
		shooterIntervallabel = new JLabel("Time Interval in MilliSeconds :");
		shooterActionLabel=new JLabel("Actions :");
		shooterImageLabel = new JLabel("Image :");
		shooterNumberOfBulletsLabel=new JLabel("<html>Number of Bullets :<br>(-1 for infinite bullets)</html>");
		
		shooterSpriteId=new JTextField(10);
		shooterSpriteCategory=new JTextField(10);
		shooterWidthField=new JTextField(10);
		shooterHeightField=new JTextField(10);
		shooterXVelField=new JTextField(10);
		shooterYVelField=new JTextField(10);
		shooterIntervalTextField = new JTextField(10);
		shooterNumberOfBullets=new JTextField(10);
		
		shooterActionsButton = new JButton("Actions");
		shooterColorButton=new JButton("Select Color");
		shooterColorButton.setActionCommand("Color");
		shooterImageButton = new JButton("Image");
		shooterImageButton.setActionCommand("Image");
		shooterImageButton.addActionListener(this);
		
		shooterActionsButton.addActionListener(this);
		shooterColorButton.addActionListener(this);
		
		shooterSubOptionPanel1.add(shooterSpriteIdLabel);
		shooterSubOptionPanel1.add(shooterSpriteId);
		shooterSubOptionPanel1.add(shooterCategoryLabel);
		shooterSubOptionPanel1.add(shooterSpriteCategory);
		shooterSubOptionPanel1.add(shooterWidthLabel);
		shooterSubOptionPanel1.add(shooterWidthField);
		shooterSubOptionPanel1.add(shooterHeightLabel);
		shooterSubOptionPanel1.add(shooterHeightField);
		shooterSubOptionPanel1.add(shooterXVel);
		shooterSubOptionPanel1.add(shooterXVelField);
		shooterSubOptionPanel1.add(shooterYVel);
		shooterSubOptionPanel1.add(shooterYVelField);		
		shooterSubOptionPanel1.add(shooterIntervallabel);
		shooterSubOptionPanel1.add(shooterIntervalTextField);	
		shooterSubOptionPanel1.add(shooterNumberOfBulletsLabel);
		shooterSubOptionPanel1.add(shooterNumberOfBullets);
		shooterSubOptionPanel1.add(shooterColorLabel);
		shooterSubOptionPanel1.add(shooterColorButton);
		shooterSubOptionPanel1.add(shooterImageLabel);
		shooterSubOptionPanel1.add(shooterImageButton);
		shooterSubOptionPanel1.add(shooterActionLabel);
		shooterSubOptionPanel1.add(shooterActionsButton);
		
		
		int option = subActionpane.showConfirmDialog(null, shooterSubOptionPanel1, "Shooter Attributes", subActionpane.OK_CANCEL_OPTION);
		
		if(option== JOptionPane.OK_OPTION) {
			getFormData(option, isShooter(), getObjectType() );
		}	
		else {
			setShooter(false);
		}
		
		
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
			return path;
		}
		return "";
	}
	
	public String getBackgroundImagePath()
	{
		return fileExplorer();
	}
	
	public Color colorChooser() {
		Color color = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
		return color==null?Color.BLACK:color;
	}

	private void createActionPopUp(Boolean isShooter) {
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
		
		JOptionPane subCollisionpane = new JOptionPane();
		subCollisionpane.setLayout(new BoxLayout(mainOptionPanel,BoxLayout.PAGE_AXIS));
		
		String [] spriteIds= {"b1","b2", "b3"};
		String[] categoryIds= {"category1"};
		
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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getShooterImagePath() {
		return shooterImagePath;
	}

	public void setShooterImagePath(String shooterImagePath) {
		this.shooterImagePath = shooterImagePath;
	}
	
}

