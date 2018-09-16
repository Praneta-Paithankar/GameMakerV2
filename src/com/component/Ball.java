package com.component;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;
import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.dimension.Circle;
import com.dimension.Coordinate;
import com.infrastruture.Constants;
import com.infrastruture.Element;


public class Ball implements Element{
	protected Logger log = Logger.getLogger(Ball.class);
    private Circle circle;
    private Coordinate delta;
    private Color color;
    private JsonObject jsonObject;
    
	public Ball(Circle circle, Coordinate delta,Color color) {
		this.setCircle(circle); 
		this.setDelta(delta);
		this.color = color;
	}
    
	public void draw(Graphics g){
    	int radius =  circle.getRadius();
        int upperLeftX = circle.getCenter().getX() - radius;
        int upperLeftY = circle.getCenter().getY() - radius;
        int diameter = 2 * radius;
        
        g.setColor(color);
        g.fillOval(upperLeftX, upperLeftY, diameter, diameter);
    }

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Coordinate getDelta() {
		return delta;
	}

	public void setDelta(Coordinate delta) {
		this.delta = delta;
	}
	
	public void reset(){
		circle.setCenter(new Coordinate(Constants.BALL_POS_X,Constants.BALL_POS_Y));
		this.setDelta(new Coordinate(Constants.BALL_DELTA_X, Constants.BALL_DELTA_Y));
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
	public JsonObject save() {
		jsonObject = new JsonObject();
		try {
			jsonObject.put("BallX", this.getCircle().getCenter().getX());
			jsonObject.put("BallY", this.getCircle().getCenter().getY());
			jsonObject.put("BallDeltaX", this.getDelta().getX());
			jsonObject.put("BallDeltaY", this.getDelta().getY());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public int load(Object object) {
		// TODO Auto-generated method stub
			jsonObject = (JsonObject)object;
			
			this.getCircle().getCenter().setX((jsonObject.getInteger("BallX")));
			this.getCircle().getCenter().setY((jsonObject.getInteger("BallY")));
			this.getDelta().setX((jsonObject.getInteger("BallDeltaX")));
			this.getDelta().setY((jsonObject.getInteger("BallDeltaY")));
		
		return 1;
	}


	

}
