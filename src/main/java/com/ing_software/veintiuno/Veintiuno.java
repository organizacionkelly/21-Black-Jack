package com.ing_software.veintiuno;

import java.util.*;

public class Veintiuno { // Clase principal que dirige el juego dependiendo de las decisiones del jugador
    public static final String[] pintas = {"Corazones", "Picas", "Diamante", "Trébol"};
    public static final String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private final List<Carta> mazo;
    private List<Jugador> jugadores = new ArrayList<>();
    private boolean finalizo = false;
    private final GameAsker gameAsker;


    public Veintiuno(GameAsker gameAsker) {
        mazo = generarMazo();
        this.gameAsker = gameAsker;
    }

    public String empezarJuego() { // Metodo principal que dependiendo de la continuación o juego del jugador direcciona la partida
        do {
            for (Jugador jugador : jugadores) {
                if (jugador.isJugadorContinua())
                    if (!jugador.getNombre().equals("casa")) {
                        jugador.setJugadorContinua(jugar(jugador.getNombre()));
                        if (jugador.isJugadorContinua()) {
                            System.out.println(imprimirCartas());
                            finalizo = jugadorNoRecibeOtraCarta();
                            if (finalizo){
                                jugador.setJugadorContinua(false);
                                break;
                            }
                        } else
                            finalizo = true;
                    } else if (!(jugador.sumarPuntos() == 21))
                        jugador.setJugadorContinua(jugar(jugador.getNombre()));
            }
        } while (!finalizo);
        System.out.println(imprimirCartas());
        return imprimirResultado();
    }

    private List<Carta> generarMazo() { // Genera el mazo a partir de las cartas que se contiene en el array y pintas
        List<Carta> mazo = new ArrayList<>();
        for (String pinta : pintas) {
            for (String valor : valores) {
                Carta carta = new Carta(valor, pinta);
                mazo.add(carta);
            }
        }
        return mazo;
    }

    public boolean jugar(String nombreJugador) {
        Jugador jugador = getJugador(nombreJugador);
        if (jugador.getCartas().isEmpty()) {// Le da la mano al jugador
            jugador.addCarta(sacarCartaMazo());
            jugador.addCarta(sacarCartaMazo());
        } else {
            jugador.addCarta(sacarCartaMazo());
        }
        return jugador.sumarPuntos() <= 21; // Si alguno de los jugadores paso los 21 termina el juego
    }

    public String imprimirCartas() { //Impresiones por pantalla
        String mensaje = "";
        if (!finalizo) {
            mensaje += "\nCartas casa: \n " + getJugador("casa").imprimirCartas(true);
        } else {
            mensaje += "\n*** Termino el juego ***";
            mensaje += "\nCartas casa: \n" + getJugador("casa").imprimirCartas(false);
            mensaje += "\nPuntaje casa: " + getJugador("casa").getPuntaje();
        }
        mensaje += "\n\nCartas jugador: \n" + getJugador("jugador").imprimirCartas(false);
        mensaje += "\nPuntaje jugador: " + getJugador("jugador").getPuntaje();
        return mensaje;
    }

    private boolean jugadorNoRecibeOtraCarta() { //Confirma si quiere mas cartas, sino solo juega la casa
        System.out.println("\nJugador, quiere mas cartas? (Escriba 'No' para plantar su juego): ");
        return "no".equalsIgnoreCase(gameAsker.stringAsk());
    }

    public String imprimirResultado() { //RESULTADOS: Analiza el puntaje de cada jugador e imprime el resultado
        int puntajeCasa = getJugador("casa").getPuntaje();
        int puntajeJugador = getJugador("jugador").getPuntaje();
        if ((puntajeCasa > 21) && (puntajeJugador > 21))
            return "Nadie gano";
        if ((puntajeCasa <= 21) &&
                (puntajeCasa == puntajeJugador ||
                        puntajeCasa > puntajeJugador ||
                        puntajeJugador > 21))
            return "Gano la casa";
        else
            return "Gano el jugador";
    }

    /**
     * Genera una nueva carta según un numero aleatorio basado en el tamaño del mazo
     *
     * @return retorna la carta eliminada del mazo o una carta de error en caso de no
     * encontrar una carta dentro del mazo
     */
    private Carta sacarCartaMazo() {
        return mazo.remove((int) (Math.random() * mazo.size()));
    }

    /**
     * Obtiene un jugador presente en el listado jugadores, en caso contrario crea
     * uno nuevo utilizando el nombre recibido.
     *
     * @param nombreJugador string con el nombre del jugador
     * @return jugador encontrado o jugador creado y añadido al listado de jugadores
     */
    public Jugador getJugador(String nombreJugador) {
        Optional<Jugador> optional = jugadores.stream().filter(
                        jugador -> nombreJugador.equals(jugador.getNombre()))
                .findAny();
        if (optional.isPresent())
            return optional.get();
        else {
            jugadores.add(new Jugador(nombreJugador));
            return getJugador(nombreJugador);
        }
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}