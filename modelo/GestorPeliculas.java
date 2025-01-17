package modelo;

import java.util.HashMap;

public class GestorPeliculas 
{
	private static GestorPeliculas miGestorPelis = new GestorPeliculas();
	private HashMap<Integer, Pelicula> peliculas; //int=key y Pelicula=Value
	
	private GestorPeliculas()
	{
		this.peliculas = new HashMap<Integer, Pelicula>();
	}
	
	public static GestorPeliculas getGestorPelis()
	{
		return miGestorPelis;
	}

	public JSON1 buscarInfoPeli(String peliAbuscar) 
	{
		// Recorremos el HashMap de nuestras pelis
	    for (Pelicula pelicula : peliculas.values()) 
	    {
	        // Comparar el titulo de la peli con el nombre buscado
	        if (pelicula.getTitulo().equalsIgnoreCase(peliAbuscar)) {
	            // cuando encontremos la peli crear un JSON1 y rellenarlo con los datos de la peli 
	            JSON1 json = new JSON1(pelicula.getTitulo(),pelicula.getAnio(),pelicula.getPuntuacionMedia(),pelicula.getIdPelicula());
	            return json; 
	        }
	    }
	    // Si no se encuentra la pel�cula, devolver null
	    System.out.println("No encontrada ninguna peli con ese nombre, se devuelve un JSON1 null");
	    return null;
	}


	public void añadirResenaAPeli(String peliAbuscar,int idUsuario, int idPelicula, float puntuacion, String comentario) 
	{
		// Recorremos el HashMap de nuestras pelis
		for (Pelicula pelicula : peliculas.values()) 
		{
        // Comparar el titulo de la peli con el nombre buscado
			if (pelicula.getTitulo().equalsIgnoreCase(peliAbuscar)) {
				if (!pelicula.tieneResena(idUsuario)) {
					Resena nuevaResena = new Resena(idUsuario, idPelicula, puntuacion, comentario);
					pelicula.agregarResena(nuevaResena);
					GestorBD migestor = GestorBD.getGestorBD();
					
				    String sentencia = "INSERT INTO reseñas (idUsuario, idPelicula, puntuacion, comentario) VALUES ("
	                        + idUsuario + ", '"
	                        + idPelicula + ", '"
	                        + puntuacion + ", '"
	                        + comentario + ")";

					
					migestor.execSQL(sentencia);
				}
				else {
					System.out.println("El usuario ya tiene registrada una reseña");
				}
            // cuando encontremos la peli crear un JSON1 y rellenarlo con los datos de la peli 
            
			}
		}
	}
}