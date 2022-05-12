package com.ing_software.veintiuno;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JugadorTest {
	private Jugador jugador;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		jugador = new Jugador("jugador");
		jugador.addCarta(new Carta("J", "Picas"));
		jugador.addCarta(new Carta("As", "Picas"));
	}

	@Test
	public void getCarta() {
		// Debe retornar el array de cartas no vacío
		assertNotNull(jugador.getCartas());
	}

	@Test
	public void imprimirCartas() {
		// Todas las cartas deben estar boca arriba
		assertEquals("[(J, Picas)], [(As, Picas)]", jugador.imprimirCartas(false));
	}

	@Test
	public void imprimirCartasOcultarPrimera() {
		// La primera carta debe estar boca abajo
		assertEquals("[(X, X)], [(As, Picas)]", jugador.imprimirCartas(true));
	}

	@Test
	public void getNombre() {
		// Se debe retornar el nombre del jugador
		assertEquals("jugador",jugador.getNombre());
	}

	@Test
	public void getPuntaje() {
		//Debe retornar el puntaje del mazo del jugador
		assertEquals(21,jugador.sumarPuntos());
		assertEquals(21,jugador.getPuntaje());
	}

	@Test
	public void sumarPuntos() {
		// Debe retornar la suma de puntos del mazo y tomar el As como 11
		assertEquals(21, jugador.sumarPuntos());
	}

	@Test
	public void wontAddCard() {
		jugador.setJugadorContinua(false);
		jugador.addCarta(new Carta("3", "Corazones"));
		//Debe retornar la suma de puntos del mazo y tomar el As como 1
		assertFalse(jugador.isJugadorContinua());
		assertEquals(21, jugador.sumarPuntos());
	}

	@Test
	public void sumarPuntosAs() {
		jugador.addCarta(new Carta("3", "Corazones"));
		//Debe retornar la suma de puntos del mazo y tomar el As como 1
		assertEquals(14, jugador.sumarPuntos());
	}
}