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
    private ArrayList<Resena> susResenas;
    private int quienLaHaAceptado;
    
    //Sobrecargado
//    public Pelicula(int idPelicula, String titulo, int anio, String thumbnail) {
//    	
//    	this.idPelicula = idPelicula;
//    	this.titulo = titulo;
//    	this.anio = anio;
//    	
//    }

    // Constructor
    public Pelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
                    float puntuacionMedia, ArrayList<Resena> lresenas, int quienLaHaAceptado) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.reparto = reparto;
        this.anio = anio;
        this.puntuacionMedia = puntuacionMedia;
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
    
    public void setReparto(ArrayList<String> reparto) {
    	this.reparto = reparto;
    }
    
    public void setAnio(int anio) {
    	this.anio = anio;
    }
    
    public void setAceptador(int idUsuario) {
    	this.quienLaHaAceptado = idUsuario;
    }
    
    public void eliminarResena(Resena resena) {
        susResenas.remove(resena);
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
                ", quienLaHaAceptado=" + quienLaHaAceptado +
                ", susReseñas=" + susResenas +
                '}';
    }
}

