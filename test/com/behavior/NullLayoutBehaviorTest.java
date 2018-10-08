package com.behavior;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.infrastruture.AbstractPanel;

class NullLayoutBehaviorTest {

	@Test
	void testUpdateLayoutBehavior() {
		NullLayoutBehavior  nullLayoutBehavior= new  NullLayoutBehavior();
		AbstractPanel abstractPanel = mock(AbstractPanel.class);
		
		nullLayoutBehavior.updateLayoutBehavior(abstractPanel, 0, 0);
		verify(abstractPanel).setLayout(any());
		verify(abstractPanel).setPreferredSize(any());
	}

}
