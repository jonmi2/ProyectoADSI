package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


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
	    // Si no se encuentra la pelï¿½cula, devolver null
	    System.out.println("No encontrada ninguna peli con ese nombre, se devuelve un JSON1 null");
	    return null;
	}


	public void anadirResenaAPeli(String peliAbuscar,int idUsuario, int idPelicula, float puntuacion, String comentario) 
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
					
				    String sentencia = "INSERT INTO reseÃ±as (idUsuario, idPelicula, puntuacion, comentario) VALUES ("
	                        + idUsuario + ", '"
	                        + idPelicula + ", '"
	                        + puntuacion + ", '"
	                        + comentario + ")";

					
					migestor.execSQL(sentencia);
				}
				else {
					System.out.println("El usuario ya tiene registrada una reseÃ±a");
				}
            // cuando encontremos la peli crear un JSON1 y rellenarlo con los datos de la peli 
            
			}
		}
	}
	
	public void anadirPelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
                    float puntuacionMedia, ArrayList<ListaPersonalizada> perteneceA, ArrayList<Resena> lresenas, int quienLaHaAceptado) 
	{
		Pelicula unaPeli = new Pelicula(idPelicula, titulo, reparto, anio, puntuacionMedia, lresenas, quienLaHaAceptado);
		peliculas.put(idPelicula, unaPeli);
		GestorBD migestor = GestorBD.getGestorBD();
		String sentencia = "INSERT INTO Pelicula (idPelicula, titulo, reparto, anio, puntuacion, idAceptador) VALUES ("
                + idPelicula + ", '"
                + titulo + ", '"
                + reparto + ", '"
                + anio + ", '"
                + puntuacionMedia + ", '"
                + quienLaHaAceptado + ")";
		migestor.execSQL(sentencia);
	}
	
	public void cargarDatos() {
	    GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Pelicula"; // Consulta para obtener todas las películas

	    try {
	        ResultadoSQL resultado = gestorBD.consultaSQL(query); // Ejecutar la consulta SQL

	        // Recorrer los resultados
	        while (resultado.next()) {
	            // Leer los datos de la fila actual
	            int idPelicula = resultado.getInt("idPelicula");
	            String titulo = resultado.getString("titulo");
	            int anio = resultado.getInt("anio");
	            

	            int quienLaHaAceptado;
	            String quienLaHaAceptadoStr = resultado.getString("quienLaHaAceptado");
	            if (quienLaHaAceptadoStr == null || quienLaHaAceptadoStr.isEmpty()) {
	                // Caso en el que "quienLaHaAceptado" sea null en la BD
	                quienLaHaAceptado = 0;
	            } else {
	                quienLaHaAceptado = Integer.parseInt(quienLaHaAceptadoStr);
	            }

	            // Procesar la columna reparto
	            String repartoStr = resultado.getString("reparto");
	            ArrayList<String> reparto = new ArrayList<>();
	            if (repartoStr != null && !repartoStr.isEmpty()) 
	            {
	                String[] actores = repartoStr.split(",");
	                for (String actor : actores) 
	                {
	                    reparto.add(actor.trim()); // Agregar cada actor a la lista, eliminando espacios innecesarios
	                }
	            }
	            
	            // Crear listas vacías para , perteneceA y susResenas
	           
	            ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>();
	            ArrayList<Resena> susResenas = new ArrayList<>();
	            
	            float puntuacionMedia = GestorResenas.getGestorResenas().obtenerPuntuacion(idPelicula);
	            // Crear el objeto Pelicula
	            Pelicula pelicula = new Pelicula(
	                idPelicula, titulo, reparto, anio, puntuacionMedia, susResenas, quienLaHaAceptado
	            );

	            
	            // Añadir al HashMap (clave: idPelicula, valor: Pelicula)
	            peliculas.put(idPelicula, pelicula);
	        }

	        System.out.println("Películas cargadas exitosamente.");

	        // Imprimir películas para comprobar (esto puede eliminarse más adelante)
	        for (Entry<Integer, Pelicula> entry : peliculas.entrySet()) {
	        	Integer idPelicula = entry.getKey();
	            Pelicula pelicula = entry.getValue();
	            System.out.println("ID Película: " + idPelicula);
	            System.out.println(pelicula); // Asume que la clase Pelicula tiene un método toString()
	        }
	        System.out.println("---------------------------------------------");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando las películas desde la base de datos.", e);
	    }
	}

	public Pelicula buscarPeliculaPorId(int idPelicula) 
	{
		if (peliculas.containsKey(idPelicula)) 
		{
	        return peliculas.get(idPelicula); // Retorna la película si existe en el mapa
	    } 
		else 
		{
	        System.out.println("Película con ID " + idPelicula + " no encontrada.");
	        return null; // Devuelve null si no se encuentra la película
	    }
	}
}