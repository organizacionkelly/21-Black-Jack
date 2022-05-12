package com.ing_software.veintiuno;

import java.util.Scanner;

public class GameAsker {

    private Scanner sc;

    public void setScanner(Scanner sc){
        this.sc = sc;
    }

    public String stringAsk(){
        return sc.nextLine();
    }

}
