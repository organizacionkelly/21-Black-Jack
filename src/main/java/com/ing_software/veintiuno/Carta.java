package com.ing_software.veintiuno;

public class  Carta { // Clase que representa una carta con valor y pinta
    String valor;
    String pinta;

    public Carta(String valor, String pinta) {
        this.valor = valor;
        this.pinta = pinta;
    }
    public String cartaString() {
        return "[("+valor+", "+pinta+")]";
    }
}