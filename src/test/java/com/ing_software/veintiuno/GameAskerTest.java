package com.ing_software.veintiuno;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameAskerTest {
	private GameAsker gameAsker;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		gameAsker = new GameAsker();

		String data = "no";
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		Scanner scanner = new Scanner(System.in);
		System.setIn(stdin);
		gameAsker.setScanner(scanner);
	}

	@Test
	public void stringAsk() {
		assertEquals("no", gameAsker.stringAsk());
	}
}