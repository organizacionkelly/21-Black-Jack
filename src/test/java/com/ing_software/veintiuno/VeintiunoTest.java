package com.ing_software.veintiuno;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VeintiunoTest {

    private Veintiuno veintiuno;
    private final List<Jugador> jugadores = new ArrayList<>();
    private Jugador jugador;
    private Jugador casa;
    private GameAsker gameAsker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gameAsker = Mockito.mock(GameAsker.class);
        veintiuno = new Veintiuno(gameAsker);
        veintiuno.setJugadores(jugadores);
        jugador = Mockito.mock(Jugador.class);
        casa = Mockito.mock(Jugador.class);
        jugadores.add(jugador);
        jugadores.add(casa);
        when(jugador.getNombre()).thenReturn("jugador");
        when(casa.getNombre()).thenReturn("casa");
        when(jugador.isJugadorContinua()).thenReturn(true);
        when(gameAsker.stringAsk()).thenReturn("no");
    }

    @Test
    public void empezarJuego() {
        when(jugador.getPuntaje()).thenReturn(0);
        when(casa.getPuntaje()).thenReturn(0);
        assertEquals("Gano la casa", veintiuno.empezarJuego());
    }

    @Test
    public void imprimirResultado() {
        when(jugador.getPuntaje()).thenReturn(10);
        when(casa.getPuntaje()).thenReturn(10);
        assertEquals("Gano la casa", veintiuno.imprimirResultado());
        when(jugador.getPuntaje()).thenReturn(10);
        when(casa.getPuntaje()).thenReturn(21);
        assertEquals("Gano la casa", veintiuno.imprimirResultado());
        when(jugador.getPuntaje()).thenReturn(23);
        when(casa.getPuntaje()).thenReturn(22);
        assertEquals("Nadie gano", veintiuno.imprimirResultado());
        when(jugador.getPuntaje()).thenReturn(21);
        when(casa.getPuntaje()).thenReturn(2);
        assertEquals("Gano el jugador", veintiuno.imprimirResultado());
    }

    @Test
    public void getJugador(){
        assertNotNull(veintiuno.getJugador("player"));
    }

    @Test
    public void jugarUnaCarta(){
        Jugador player = veintiuno.getJugador("player");
        veintiuno.jugar("player");
        veintiuno.jugar("player");
        assertEquals(3,player.getCartas().size());
    }

    @Test
    public void puntajeAses() {
        jugador = new Jugador("jugador");
        jugador.addCarta(new Carta("As", "Picas"));
        jugador.addCarta(new Carta("As", "Corazones"));
        assertEquals(12,jugador.sumarPuntos());
    }

    @Test
    public void jugadorNoRecibeCarta(){
        List<Jugador> jugadores = new ArrayList<>();
        Veintiuno veintiuno = new Veintiuno(gameAsker);
        jugadores.add(veintiuno.getJugador("casa"));
        jugadores.add(veintiuno.getJugador("jugador"));
        veintiuno.setJugadores(jugadores);
        veintiuno.empezarJuego();
        jugador = veintiuno.getJugador("jugador");
        assertEquals(2,jugador.getCartas().size());
    }
}
