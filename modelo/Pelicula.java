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
    private ArrayList<Resena> susResenas;
    private int quienLaHaAceptado;

    // Constructor
    public Pelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
                    float puntuacionMedia, ArrayList<ListaPersonalizada> perteneceA, ArrayList<Resena> lresenas, int quienLaHaAceptado) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.reparto = reparto;
        this.anio = anio;
        this.puntuacionMedia = puntuacionMedia;
        this.perteneceA = perteneceA;
        this.quienLaHaAceptado = quienLaHaAceptado;
        this.susResenas = lresenas;
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
    
    public ArrayList<Resena> getSusResenas() {
        return susResenas;
    }
    
    public void eliminarResena(Resena resena) {
        susResenas.remove(resena);
    }
    
    public ArrayList<ListaPersonalizada> getPerteneceA() {
        return perteneceA;
    }

    public int getQuienLaHaAceptado() {
        return quienLaHaAceptado;
    }
    public void agregarResena(Resena resena) {
        if (resena != null) {
        	
        	susResenas.add(resena);
        }
    }
    public boolean tieneResena(int idUsuario){
    	for (Resena resena : susResenas) {
            if (resena.getIdUsuario() == idUsuario) { 
            	return true;
            }
    	}
    	return false;
    }

    // M�todo toString para imprimir la clase
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
                ", susReseñas=" + susResenas +
                '}';
    }
}

