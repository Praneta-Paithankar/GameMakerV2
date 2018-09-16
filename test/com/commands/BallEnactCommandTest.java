package com.commands;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.component.Ball;
import com.dimension.Circle;
import com.dimension.Coordinate;

class BallEnactCommandTest {

    @Mock private Ball ball;
	
	@InjectMocks private BallEnactCommand ballEnactCommand;
	
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void ShouldChangeBallDirectionWhenExecuteCalled (){
		Coordinate cord = mock(Coordinate.class);
		Circle c =mock(Circle.class);
		
		when(ball.getDelta()).thenReturn(cord);
		when(ball.getCircle()).thenReturn(c);
		when(c.getCenter()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		when(cord.getX()).thenReturn(40);
		when(c.getRadius()).thenReturn(10);
		
		ballEnactCommand.execute();
		
		verify(c).setCenter(any());
		
	}
	@Test
	void ShouldChangeBallDirectionWhenUndoCalled (){
		Coordinate cord = mock(Coordinate.class);
		Circle c =mock(Circle.class);
		
		when(ball.getDelta()).thenReturn(cord);
		when(ball.getCircle()).thenReturn(c);
		when(c.getCenter()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		when(cord.getX()).thenReturn(40);
		when(c.getRadius()).thenReturn(10);
		
		ballEnactCommand.undo();
		
		verify(c).setCenter(any());
		
	}
}