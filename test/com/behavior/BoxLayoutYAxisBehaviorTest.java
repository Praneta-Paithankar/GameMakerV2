package com.behavior;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.infrastruture.AbstractPanel;

class BoxLayoutYAxisBehaviorTest {

	@Test
	void testUpdateLayoutBehavior() {
		BoxLayoutYAxisBehavior boxLayoutYAxisBehavior = new BoxLayoutYAxisBehavior();
		AbstractPanel abstractPanel = mock(AbstractPanel.class);
		
		boxLayoutYAxisBehavior.updateLayoutBehavior(abstractPanel, 0, 0);
		verify(abstractPanel).setLayout(any());
	}

}
