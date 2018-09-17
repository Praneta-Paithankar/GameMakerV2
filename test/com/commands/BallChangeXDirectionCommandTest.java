package com.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.component.Ball;
import com.dimension.Coordinate;

//@RunWith(MockitoJUnitRunner.class)
class BallChangeXDirectionCommandTest {

	@Mock private Ball ball;
	
	@InjectMocks private BallChangeXDirectionCommand ballChangeXDirectionCommand ;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void ShouldChangeBallDirectionWhenExecuteCalled (){
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(cord.getX()).thenReturn(40);
		
		ballChangeXDirectionCommand.execute();
		
		verify(ball, times(2)).getDelta();
		
	}
	@Test
	void ShouldChangeBallDirectionWhenUndoCalled (){
		Coordinate cord = mock(Coordinate.class);
		when(ball.getDelta()).thenReturn(cord);
		when(cord.getX()).thenReturn(40);
		
		ballChangeXDirectionCommand.undo();
		
		verify(ball, times(2)).getDelta();
		
	}
}