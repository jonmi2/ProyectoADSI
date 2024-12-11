package modelo;

import java.util.Date;

public class Alquiler 
{
	private int idUsuario;
	private int idPelicula;
	private Date fecha;
	
	public Alquiler(int idUsuario, int idPelicula, Date fecha) {
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.fecha = fecha;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public Date getFecha() {
        return fecha;
    }

    // Método toString
    @Override
    public String toString() {
        return "Alquiler{" +
                "idUsuario=" + idUsuario +
                ", idPelicula=" + idPelicula +
                ", fecha=" + fecha +
                '}';
    }
	
}
