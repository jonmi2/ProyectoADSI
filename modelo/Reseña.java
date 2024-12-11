package modelo;

public class Reseña
{
	private int idUsuario;
	private int idPelicula;
	private float puntuacion;
	private String comentario;
	
	public Reseña(int idUsuario, int idPelicula, float puntuacion, String comentario) {
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    // Método toString
    @Override
    public String toString() {
        return "Reseña{" +
                "idUsuario=" + idUsuario +
                ", idPelicula=" + idPelicula +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
