package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    // Atributos
    private int idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private ArrayList<ListaPersonalizada> susListas; 
    private ArrayList<Alquiler> susAlquileres; 
    private int eliminadoPor;
    private int aceptadoPor;


    // Constructora
    public Usuario(int idUsuario, String nombre, String email, String rol, ArrayList<ListaPersonalizada> susListas, ArrayList<Alquiler> susAlquileres, int eliminadoPor, int aceptadoPor) 
    {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.susListas = susListas;
        this.susAlquileres = susAlquileres;
        this.eliminadoPor = eliminadoPor;
        this.aceptadoPor = aceptadoPor;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public ArrayList<ListaPersonalizada> getSusListas() {
        return susListas;
    }

    public ArrayList<Alquiler> getSusAlquileres() {
        return susAlquileres;
    }

    public int getEliminadoPor() {
        return eliminadoPor;
    }


    public int getAceptadoPor() {
        return aceptadoPor;
    }


    // Método toString para imprimir la clase
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", susListas=" + susListas +
                ", susAlquileres=" + susAlquileres +
                ", eliminadoPor=" + eliminadoPor +
                ", aceptadoPor=" + aceptadoPor +
                '}';
    }
}
