package com.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.component.Ball;
import com.dimension.Coordinate;

class BallChangeYDirectionCommandTest {

    @Mock private Ball ball;
	
	@InjectMocks private BallChangeYDirectionCommand ballChangeYDirectionCommand ;
	
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void ShouldChangeBallDirectionWhenExecuteCalled (){
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		
		
		ballChangeYDirectionCommand.execute();
		
		verify(ball, times(2)).getDelta();
		
	}
	@Test
	void ShouldChangeBallDirectionWhenUndoCalled (){
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(cord.getY()).thenReturn(30);
		
		ballChangeYDirectionCommand.undo();
		
		verify(ball, times(2)).getDelta();
		
	}

}