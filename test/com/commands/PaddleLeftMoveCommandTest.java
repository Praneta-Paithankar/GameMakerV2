package com.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.component.Paddle;
import com.dimension.Coordinate;
import com.dimension.Rectangle;

class PaddleLeftMoveCommandTest {

	@Mock Paddle paddle;
	@Mock Coordinate prevTopLeft;

	@InjectMocks PaddleLeftMoveCommand paddleLeftMoveCommand;
	
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
		paddleLeftMoveCommand.execute();
		verify(rectangle,atLeast(1)).setTopLeftCoordinate(any());;
	}

	@Test
	void testUndo() {
		Rectangle rectangle = mock(Rectangle.class);
		Coordinate coordinate = mock(Coordinate.class);
		when(paddle.getRectangle()).thenReturn(rectangle);
		when(rectangle.getTopLeftCoordinate()).thenReturn(coordinate);
		when(paddle.getDeltaX()).thenReturn(20);
		
		paddleLeftMoveCommand.undo();
		verify(rectangle,atLeast(1)).setTopLeftCoordinate(any());;
	}

}