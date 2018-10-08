/**
 *This class is used to define all the constants used in our application*/
package com.infrastruture;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final  int FRAME_WIDTH = 1400;
	public static final int  FRAME_HEIGHT = 750;
	public static final int MAIN_PANEL_WIDTH =  1400;
	public static final int MAIN_PANEL_HEIGHT =  750;
	public static final int TIMER_PANEL_WIDTH =  150;
	public static final int TIMER_PANEL_HEIGHT =  750;
	public static final int BOARD_PANEL_WIDTH =  750;
	public static final int BOARD_PANEL_HEIGHT =  750;
	public static final int MAKE_PANEL_WIDTH =  495;
	public static final int MAKE_PANEL_HEIGHT =  750;
	
	public static final int TIMER_COUNT = 15;
	
	public static final int BRICK_NO = 4;
	public static final int BRICK_WIDTH = 75;
	public static final int BRICK_HEIGHT = 30;
	public static final int BRICK_START_Y = 100;
	public static final int BRICK_START_X = 0;
	public static final int BRICK_DISTANCE_X = 75;
	public static final int BRICK_DISTANCE_Y = 50;
	public static final Color BRICK_COLOR =  new Color(78,104,130);
	public static final  Color BRICK_BORDER = new Color(62,83,104);	
	
	public static final int BALL_POS_X = 15;
	public static final int  BALL_DELTA_X = 5;
	public static final int BALL_DELTA_Y = 5;
	public static final int BALL_POS_Y = 200;
	public static final int BALL_RADIUS = 15;
	public static final Color BALL_COLOR = new Color(155,94,155);
	
	public static final int PADDLE_POS_X = 350; 
	public static final int PADDLE_POS_Y = 600;
	public static final int PADDLE_WIDTH = 200 ;
	public static final int PADDLE_HEIGHT = 40;
	public static final int PADDLE_DELTA_X = 20;
	public static final Color PADDLE_COLOR = new Color(91,33,91);
	
	public static final List<String> SPRITETYPES = Arrays.asList("Rectangle", "Circle");
	public static final String[] AVAILABLE_EVENTS = {"OnTick", "OnCollision", "keyPressed", "OnClick", "GameEnd"};
	public static final String[] AVAILABLE_ACTIONS = {"move", "bounce", "shoot", "blow"};
	
	public static final String BALL_IMAGE = "./src/com/image/ball.png";
	public static final String BRICK_IMAGE = "./src/com/image/brick.jpg";
	public static final String PADDLE_IMAGE = "./src/com/image/paddle.png";
	
	public static final int CHECKBOX_WIDTH = 200; 
	public static final int CHECKBOX_HEIGHT = 100;
	public static final int CHECKBOX_X = 10;
	public static final int CHECKBOX_Y = 80;
	
	public static final int X_VELOCITY = 5;
	public static final int Y_VELOCITY = 5;
	
	public static final int PROJECTILE_GRAVITY = 1;
	
	public static final int GAME_WIN_COMPONENT=0;
	public static final int GAME_LOSE_COMPONENT=1;
	public static final int GAME_NOT_APPLICABLE_COMPONENT=2;
	
}
