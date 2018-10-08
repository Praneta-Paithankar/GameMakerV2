package com.behavior;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.infrastruture.AbstractPanel;

class FlowLayoutBehaviorTest {

	@Test
	void testUpdateLayoutBehavior() {
		FlowLayoutBehavior  flowLayoutBehavior = new  FlowLayoutBehavior();
		AbstractPanel abstractPanel = mock(AbstractPanel.class);
		
		flowLayoutBehavior.updateLayoutBehavior(abstractPanel, 0, 0);
		verify(abstractPanel).setLayout(any());
		verify(abstractPanel).setPreferredSize(any());
	}

}
