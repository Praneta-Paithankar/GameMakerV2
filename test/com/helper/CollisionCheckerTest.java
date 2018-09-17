/**
 *This class is used to check collision between ball and other components*/
package com.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;

import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;
import com.infrastruture.Constants;
import com.infrastruture.Direction;

import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
class CollisionCheckerTest {

	private CollisionChecker collisionChecker;
	private Ball ball;
	
	@BeforeEach
	void setUp() throws Exception {
		collisionChecker = new CollisionChecker();
		ball = mock(Ball.class);
		
	}


	@Test
	void checkCollisionWithBallShouldReturnNoneIfNoCollisionOccur() {
		Circle c =mock(Circle.class);
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(ball.getCircle()).thenReturn(c);
		when(c.getCenter()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		when(cord.getX()).thenReturn(40);
		when(c.getRadius()).thenReturn(10);
		
		Direction result = collisionChecker.checkCollisionBetweenBallAndWall(ball);
		assertEquals(Direction.NONE, result);
	}
	@Test
	void checkCollisionWithBallShouldReturnDirectionIfCollisionOccur() {
		
		Circle c =mock(Circle.class);
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(ball.getCircle()).thenReturn(c);
		when(c.getCenter()).thenReturn(cord);
		when(cord.getY()).thenReturn(0);
		when(cord.getX()).thenReturn(Constants.BOARD_PANEL_WIDTH);
		when(c.getRadius()).thenReturn(1);
		
		Direction result = collisionChecker.checkCollisionBetweenBallAndWall(ball);
		assertEquals(Direction.X, result);
	}
	@Test
	void checkCollisionBetweenCircleAndRectangleShouldReturnDirectionIfCollisionOccur() {
		Circle circle =mock(Circle.class);
		Coordinate cord = mock(Coordinate.class);
		
		when(circle.getCenter()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		when(cord.getX()).thenReturn(30);
		when(circle.getRadius()).thenReturn(15);
		
		Rectangle rectangle = mock(Rectangle.class);
		when(rectangle.getHeight()).thenReturn(30);
		when(rectangle.getWidth()).thenReturn(40);
		when(rectangle.getTopLeftCoordinate()).thenReturn(cord);
		
		Direction result = collisionChecker.checkCollisionBetweenCircleAndRectangle(circle, rectangle);
		assertEquals(Direction.BOTH, result);
	}
}