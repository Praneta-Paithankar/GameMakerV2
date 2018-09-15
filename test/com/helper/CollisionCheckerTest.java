package com.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

class CollisionCheckerTest {

	private CollisionChecker collisionChecker;
	private Ball ball;
	
	@BeforeEach
	void setUp() throws Exception {
		collisionChecker = new CollisionChecker();
		Circle c = new Circle(Constants.BALL_RADIUS, Constants.BALL_POS_X,Constants.BALL_POS_Y);
		ball = new Ball(c, new Coordinate(Constants.BALL_DELTA_X, Constants.BALL_DELTA_Y), Constants.BALL_COLOR);
	}


	@Test
	void checkCollisionWithBallShouldReturnNoneIfNoCollisionOccur() {
		Direction result = collisionChecker.checkCollisionBetweenBallAndWall(ball);
		assertEquals(Direction.NONE, result);
	}
	@Test
	void checkCollisionWithBallShouldReturnDirectionIfCollisonOccur() {
		ball.getCircle().setCenter(new Coordinate(Constants.FRAME_WIDTH, 0));
		Direction result = collisionChecker.checkCollisionBetweenBallAndWall(ball);
		assertEquals(Direction.X, result);
	}
}
