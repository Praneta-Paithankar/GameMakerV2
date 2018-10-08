package com.behavior;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import com.infrastruture.AbstractPanel;

class BoxLayoutXAxisBehaviorTest {

	@Test
	void testUpdateLayoutBehavior() {
		BoxLayoutXAxisBehavior boxLayoutXAxisBehavior = new BoxLayoutXAxisBehavior();
		AbstractPanel abstractPanel = mock(AbstractPanel.class);
		
		boxLayoutXAxisBehavior.updateLayoutBehavior(abstractPanel, 0, 0);
		verify(abstractPanel).setLayout(any());
		verify(abstractPanel).setPreferredSize(any());
	}

}
