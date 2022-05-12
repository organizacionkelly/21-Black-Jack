package com.ing_software.veintiuno;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class  Jugador { // Jugador que tiene las cartas y a su vez metodos para el calculo de su puntaje
    private final String nombreJugador;
    private int puntaje;
    private final List<Carta> jugadorCartas = new ArrayList<>();
    private final List<String> jugadorAses = new ArrayList<>();
    private boolean jugadorContinua = true;

    public Jugador(String nombreJugador) {
      this.nombreJugador = nombreJugador;
    }
    
    public int getPuntaje() {
      return puntaje;
    }
    
    public String getNombre() {
      return nombreJugador;
    }
    
    public List<Carta> getCartas(){
      return jugadorCartas;
    }

    public boolean isJugadorContinua() {
        return jugadorContinua;
    }

    public void setJugadorContinua(boolean jugadorContinua) {
        this.jugadorContinua = jugadorContinua;
    }

    public void addCarta(Carta carta){
      if (jugadorContinua)
        jugadorCartas.add(carta);
    }

    public String imprimirCartas(boolean ocultarPrimera) {
        if(ocultarPrimera){
          return  "[(X, X)], " + jugadorCartas.subList(1, jugadorCartas.size())
          .stream().map(Carta::cartaString)
                  .collect(Collectors.joining(", "));
        }
        else{
            return jugadorCartas
            .stream().map(Carta::cartaString)
                    .collect(Collectors.joining(", "));
        }        
    }
    
    public int sumarPuntos(){
      puntaje = jugadorCartas.stream().mapToInt(carta -> sumarPuntos(carta.valor)).sum();
      final int[] cantidadAses = {jugadorAses.size()};
      if (puntaje > 21)
          jugadorAses.forEach(a -> {
              cantidadAses[0] -= 1;
              if ( 21 <= (cantidadAses[0] + puntaje))
                  puntaje -= 10;
          });
      jugadorAses.clear();
      return puntaje;
    }

    public int sumarPuntos(String carta){ 
        if(Pattern.matches("[JQK]+", carta)){
            return 10;
        }
        if(carta.equals("As")){
            jugadorAses.add(carta);
            return 11;
        }
        return Integer.parseInt(carta);
    }
}
