/**
 *This class is used to define all the constants used in our application*/
package com.infrastruture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Constants {
	public final static int FRAME_WIDTH = 1400;
	public final static int  FRAME_HEIGHT = 750;
	public final static int MAIN_PANEL_WIDTH =  1400;
	public final static int MAIN_PANEL_HEIGHT =  750;
	public final static int TIMER_PANEL_WIDTH =  150;
	public final static int TIMER_PANEL_HEIGHT =  750;
	public final static int BOARD_PANEL_WIDTH =  750;
	public final static int BOARD_PANEL_HEIGHT =  750;
	public final static int MAKE_PANEL_WIDTH =  495;
	public final static int MAKE_PANEL_HEIGHT =  750;
	
	public final static int TIMER_COUNT = 15;
	
	public final static int BRICK_NO = 4;
	public final static int BRICK_WIDTH = 75;
	public final static int BRICK_HEIGHT = 30;
	public final static int BRICK_START_Y = 100;
	public final static int BRICK_START_X = 0;
	public final static int BRICK_DISTANCE_X = 75;
	public final static int BRICK_DISTANCE_Y = 50;
	public final static Color BRICK_COLOR =  new Color(78,104,130);
	public final static Color BRICK_BORDER = new Color(62,83,104);	
	
	public final static int BALL_POS_X = 15;
	public final static int  BALL_DELTA_X = 5;
	public final static int BALL_DELTA_Y = 5;
	public final static int BALL_POS_Y = 200;
	public final static int BALL_RADIUS = 15;
	public final static Color BALL_COLOR = new Color(155,94,155);
	
	public final static int PADDLE_POS_X = 350; 
	public final static int PADDLE_POS_Y = 600;
	public final static int PADDLE_WIDTH = 200 ;
	public final static int PADDLE_HEIGHT = 40;
	public final static int PADDLE_DELTA_X = 20;
	public final static Color PADDLE_COLOR = new Color(91,33,91);
	
//	public final static List<String> spriteTypes = Arrays.asList("Ball", "Brick", "Paddle");
	public final static List<String> spriteTypes = Arrays.asList("Rectangle", "Circle");
	public final static String[] AVAILABLE_EVENTS = {"OnTick", "OnCollision", "keyPressed", "OnClick", "GameEnd"};
	public final static String[] AVAILABLE_ACTIONS = {"move", "bounce", "shoot", "blow"};
	
	public final static String BALL_IMAGE = "./src/com/image/ball.png";
	public final static String BRICK_IMAGE = "./src/com/image/brick.jpg";
	public final static String PADDLE_IMAGE = "./src/com/image/paddle.png";
	
	public final static int CHECKBOX_WIDTH = 200; 
	public final static int CHECKBOX_HEIGHT = 100;
	public final static int CHECKBOX_X = 10;
	public final static int CHECKBOX_Y = 80;
	
	public final static int X_Velocity = 5;
	public final static int Y_Velocity = 5;
	
	public final static int PROJECTILE_GRAVITY = 1;
	
}
