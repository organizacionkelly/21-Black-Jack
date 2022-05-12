package com.cucumber.stepDefinitionJugador;

import com.ing_software.veintiuno.Carta;
import com.ing_software.veintiuno.GameAsker;
import com.ing_software.veintiuno.Jugador;
import com.ing_software.veintiuno.Veintiuno;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;


public class jugadorSteps {
	private Veintiuno veintiuno;
	public boolean estado;
	private Jugador jugador;
	private Jugador casa;
	private GameAsker gameAsker;
	private List<Jugador> jugadores = new ArrayList<>();

	@BeforeStep
	public void setup(){
		veintiuno = new Veintiuno(gameAsker);
		casa = new Jugador("casa");
		jugadores.add(casa);
		jugador = new Jugador("jugador");
		jugadores.add(jugador);
		veintiuno.setJugadores(jugadores);

		gameAsker = Mockito.mock(GameAsker.class);
		when(gameAsker.stringAsk()).thenReturn("no");
	}

	@Given("un jugador")
	public void un_jugador() {
		jugador = veintiuno.getJugador("jugador");
	}

	@When("el juego inicia")
	public void el_juego_inicia() {
		veintiuno.empezarJuego();
	}

	@Then("el jugador tiene dos cartas")
	public void el_jugador_tiene_dos_cartas() {
		jugador = veintiuno.getJugador("jugador");
		assert(jugador.getCartas().size() == 2);
	}
//--------------------------------------------------------------------------------------------

	@When("el jugador tiene un {int}")
	public void el_jugador_tiene_un(int puntaje) {
		jugador = Mockito.mock(Jugador.class);
		when(jugador.getPuntaje()).thenReturn(puntaje);
	}

	@When("el jugador solicita una carta")
	public void el_jugador_solicita_una_carta() {
		veintiuno.setJugadores(jugadores);
		veintiuno.jugar("jugador");
		jugador = veintiuno.getJugador("jugador");
		if(!(jugador.getPuntaje()<=21))
			estado = true;
	}

	//-------------------------------------------------------------------------------------------------------------

	@When("se conoce el {int} y el {int}")
	public void se_conoce_el_puntaje_jugador_y_el_puntaje_casa(int puntaje_jugador, int puntaje_casa) {
		when(gameAsker.stringAsk()).thenReturn("no");
		jugadores = new ArrayList<>();
		casa = Mockito.mock(Jugador.class);
		jugador = Mockito.mock(Jugador.class);
		jugadores.add(casa);
		jugadores.add(jugador);
		veintiuno.setJugadores(jugadores);
		when(jugador.getNombre()).thenReturn("jugador");
		when(casa.getNombre()).thenReturn("casa");
		when(jugador.getPuntaje()).thenReturn(puntaje_jugador);
		when(casa.getPuntaje()).thenReturn(puntaje_casa);
	}

	@Then("se conoce el {string}")
	public void se_conoce_el_ganador(String ganador) {
		assertEquals(ganador, veintiuno.imprimirResultado());
	}
}
