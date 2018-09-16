package com.commands;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.component.Paddle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;

class PaddleRightMoveCommandTest {


	@Mock Paddle paddle;
	@Mock Coordinate prevTopLeft;

	@InjectMocks PaddleRightMoveCommand paddleRightMoveCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testExecute() {
		Rectangle rectangle = mock(Rectangle.class);
		Coordinate coordinate = mock(Coordinate.class);
		when(paddle.getRectangle()).thenReturn(rectangle);
		when(rectangle.getTopLeftCoordinate()).thenReturn(coordinate);
		when(paddle.getDeltaX()).thenReturn(20);
		paddleRightMoveCommand.execute();
		verify(rectangle,atLeast(1)).setTopLeftCoordinate(any());;
	}

	@Test
	void testUndo() {
		Rectangle rectangle = mock(Rectangle.class);
		Coordinate coordinate = mock(Coordinate.class);
		when(paddle.getRectangle()).thenReturn(rectangle);
		when(rectangle.getTopLeftCoordinate()).thenReturn(coordinate);
		when(paddle.getDeltaX()).thenReturn(20);
		
		paddleRightMoveCommand.undo();
		verify(rectangle,atLeast(1)).setTopLeftCoordinate(any());;
	}


}