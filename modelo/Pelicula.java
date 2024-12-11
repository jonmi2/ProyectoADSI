package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    // Atributos
    private int idPelicula;
    private String titulo;
    private ArrayList<String> reparto; 
    private int anio;
    private float puntuacionMedia;
    private ArrayList<ListaPersonalizada> perteneceA; 
    private int quienLaHaAceptado;

    // Constructor
    public Pelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
                    float puntuacionMedia, ArrayList<ListaPersonalizada> perteneceA, int quienLaHaAceptado) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.reparto = reparto;
        this.anio = anio;
        this.puntuacionMedia = puntuacionMedia;
        this.perteneceA = perteneceA;
        this.quienLaHaAceptado = quienLaHaAceptado;
    }

    // Getters
    public int getIdPelicula() {
        return idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<String> getReparto() {
        return reparto;
    }

    public int getAnio() {
        return anio;
    }

    public float getPuntuacionMedia() {
        return puntuacionMedia;
    }

    public ArrayList<ListaPersonalizada> getPerteneceA() {
        return perteneceA;
    }

    public int getQuienLaHaAceptado() {
        return quienLaHaAceptado;
    }

    // Método toString para imprimir la clase
    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", reparto=" + String.join(", ", reparto) +
                ", anio=" + anio +
                ", puntuacionMedia=" + puntuacionMedia +
                ", perteneceA=" + perteneceA +
                ", quienLaHaAceptado=" + quienLaHaAceptado +
                '}';
    }
}

