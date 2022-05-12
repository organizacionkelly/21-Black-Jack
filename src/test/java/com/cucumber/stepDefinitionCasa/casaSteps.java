package com.cucumber.stepDefinitionCasa;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class casaSteps {

    private Veintiuno veintiuno;
    public boolean estado;
    public int puntaje;
    private GameAsker gameAsker;
    private Jugador casa;
    private Carta carta;
    private List<Jugador> jugadores = new ArrayList<>();
    private Jugador jugador;

    @BeforeStep
    public void setup(){
        veintiuno = new Veintiuno(gameAsker);
        casa = new Jugador("casa");
        jugadores.add(casa);
        jugador = new Jugador("jugador");
        jugadores.add(jugador);
        veintiuno.setJugadores(jugadores);

        gameAsker = Mockito.mock(GameAsker.class);
        carta = new Carta("As", "Picas");
        when(gameAsker.stringAsk()).thenReturn("no");
    }

    @Given("la casa")
    public void la_casa() {
        casa = veintiuno.getJugador("casa");
    }

    @When("inicia el juego")
    public void el_juego_inicia() {
        veintiuno.empezarJuego();
    }


    @Then("la casa tiene dos cartas")
    public void la_casa_tiene_dos_cartas() {
        casa = veintiuno.getJugador("casa");
        assert(casa.getCartas().size() == 2);
    }

    //-------------------------------------------------------------------------------------------------

    @When("el jugador se planta")
    public void el_jugador_se_planta() {
        assertEquals("no", gameAsker.stringAsk());
    }

    @When("se valida el {int} de la casa")
    public void se_valida_el_puntaje_de_la_casa(int puntaje) {
        if(!(casa.sumarPuntos()<=21))
            estado = true;
    }

    @Then("se reparte una carta : {string}")
    @Then("se reparte una carta : true")
    @Then("se reparte una carta : false")
    public void se_reparte_una_carta() {
        if(estado)
            casa.addCarta(carta);
    }

    //-----------------------------------------------------------------------------------------------

    // prueba 3

    @When("se suman las cartas: {string}")
    public void se_suman_las_cartas(String mano) {
        Jugador jugador = veintiuno.getJugador("jugador");
        String[] valoresMano = mano.split(",");
        for (String valorCarta : valoresMano) {
            carta = new Carta(valorCarta, "Diamante");
            jugador.addCarta(carta);
        }
        puntaje = jugador.sumarPuntos();
    }

    @Then("el conteo es: {int}")
    public void el_conteo_es(int puntaje) {
        assertEquals(puntaje,this.puntaje);
    }

    // prueba 4

    @When("se sabe el {int} y el {int}")
    public void se_sabe_el_puntaje_jugador_y_el_puntaje_casa(int puntaje_jugador, int puntaje_casa) {
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


    @Then("se sabe el {string}")
    public void se_sabe_el_ganador(String ganador) {

        System.out.println(veintiuno.getJugador("jugador").getPuntaje());
        assertEquals(veintiuno.imprimirResultado(),ganador);
    }
}
