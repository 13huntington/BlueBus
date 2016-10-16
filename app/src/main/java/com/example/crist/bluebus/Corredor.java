package com.example.crist.bluebus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crist on 08/10/2016.
 */
public class Corredor {
    private final int numero;
    private ArrayList<String> lineas;

    public Corredor(int numero, ArrayList<String> lineas){
        this.lineas = lineas;
        this.numero = numero;
    }

    public Corredor(int numero) {
        this.numero = numero;
    }

    public int getnombre() {
        return numero;
    }

    public  ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas( ArrayList<String> lineas) {
        this.lineas = lineas;
    }
    
    public void addLinea(String linea){
        lineas.add(linea);
    }

    public String toString(){
        return "Corredor "+numero;
    }
}
