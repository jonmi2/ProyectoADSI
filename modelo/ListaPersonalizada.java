package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonalizada {
    // Atributos
    private  String nombreLista;
    private  String estado;
    private  int idUsuario;
    private  ArrayList<Pelicula> pelis; // Asumimos que Pelicula es otra clase definida

    // Constructor
    public ListaPersonalizada(String nombreLista, String estado, int idUsuario, ArrayList<Pelicula> pelis) {
        this.nombreLista = nombreLista;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.pelis = pelis;
    }

    // Getters
    public String getNombreLista() {
        return nombreLista;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<Pelicula> getPelis() {
        return pelis;
    }

    // Método toString para imprimir la clase
    @Override
    public String toString() {
        return "ListaPersonalizada{" +
                "nombreLista='" + nombreLista + '\'' +
                ", estado='" + estado + '\'' +
                ", idUsuario=" + idUsuario +
                ", pelis=" + pelis +
                '}';
    }
}
