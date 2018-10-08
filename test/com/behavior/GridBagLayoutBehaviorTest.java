package com.behavior;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.awt.Component;

import org.junit.jupiter.api.Test;

import com.infrastruture.AbstractPanel;

class GridBagLayoutBehaviorTest {

	@Test
	void testUpdateLayoutBehavior() {
		GridBagLayoutBehavior  gridBagLayoutBehavior = new  GridBagLayoutBehavior();
		AbstractPanel abstractPanel = mock(AbstractPanel.class);
		Component[] comp = new Component[3];
		when(abstractPanel.getComponents()).thenReturn(comp);
		gridBagLayoutBehavior.updateLayoutBehavior(abstractPanel, 10, 10);
		verify(abstractPanel).setLayout(any());
		verify(abstractPanel).setPreferredSize(any());
	}

}
