package modelo;

public class Resena
{
	private int idUsuario;
	private int idPelicula;
	private float puntuacion;
	private String comentario;
	
	public Resena(int idUsuario, int idPelicula, float puntuacion, String comentario) {
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

    // M�todo toString
    @Override
    public String toString() {
        return "Rese�a{" +
                "idUsuario=" + idUsuario +
                ", idPelicula=" + idPelicula +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
