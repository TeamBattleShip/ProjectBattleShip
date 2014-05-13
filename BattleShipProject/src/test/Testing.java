package test;

import multiplayer.MultiplayerClient;
import multiplayer.MultiplayerServer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {
	MultiplayerClient mc;
	MultiplayerServer ms;

	@Before
	public void setUp() throws Exception {
		new MultiplayerServer("A10").start();

	}

	@Test
	public void test() {
		assertEquals("A10", new MultiplayerClient().getMove());
		new MultiplayerServer("A1").start();
		assertEquals("A1", new MultiplayerClient().getMove());
	
	}
}
