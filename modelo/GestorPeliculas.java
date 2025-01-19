package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class GestorPeliculas 
{
	private static GestorPeliculas miGestorPelis = new GestorPeliculas();
	private HashMap<Integer, Pelicula> peliculas; //int=key y Pelicula=Value
	
	public HashMap<Integer, Pelicula> getPeliculas() {
		return this.peliculas;
	}
	
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
	
	public Pelicula insertarPelicula(int idUsuario, String nombrePelicula, String anioPelicula, ListaPersonalizada lp) {
		GestorPeliculas gp = GestorPeliculas.getGestorPelis();
        
        int idPelicula = gp.getPeliculas().keySet().stream().max(Integer::compare).orElse(0) + 1;
        ArrayList<String> reparto = new ArrayList<>();
        reparto.add("ActorFamoso");
        
        ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>();
        perteneceA.add(lp);
        
        ArrayList<Resena> lresenas = new ArrayList<>();
        Resena resena1 = new Resena(idUsuario, idPelicula, 5.0f, "Excelente película"); // Ejemplo
        lresenas.add(resena1);
        
        gp.anadirPelicula(
        		idPelicula, 
        		nombrePelicula, 
        		reparto, 
        		Integer.parseInt(anioPelicula), 
        		0.0f, 
        		perteneceA, 
        		lresenas, 
        		idUsuario);
        
        return new Pelicula(idPelicula, nombrePelicula, reparto, Integer.parseInt(anioPelicula), 0.0f, lresenas, idUsuario);
        

	}
	
		public void anadirPelicula(
				int idPelicula, 
				String titulo, 
				ArrayList<String> reparto, 
				int anio,
	            float puntuacion, 
	            ArrayList<ListaPersonalizada> perteneceA, 
	            ArrayList<Resena> lresenas, 
	            int quienLaHaAceptado) 
		{
			Pelicula unaPeli = new Pelicula(idPelicula, titulo, reparto, anio, puntuacion, lresenas, quienLaHaAceptado);
			
			peliculas.put(idPelicula, unaPeli);
			GestorBD migestor = GestorBD.getGestorBD();
		        
	        // Creamos la sentencia SQL
        String sentencia = "INSERT INTO Pelicula (titulo, reparto, anio, puntuacion	, idAceptador) VALUES (?, ?, ?, ?, ?)";
	
	        try (PreparedStatement stmt = migestor.prepareStatement(sentencia)) {
//	            stmt.setInt(1, idPelicula); // Asignamos el valor para el primer parámetro (idPelicula)
	            stmt.setString(1, titulo); // Asignamos el valor para el segundo parámetro (titulo)
	            stmt.setString(2, String.join(", ", reparto)); // Convertimos el ArrayList en una cadena y lo asignamos
	            stmt.setInt(3, anio); // Asignamos el valor para el cuarto parámetro (anio)
	            stmt.setFloat(4, puntuacion); // Asignamos el valor para el quinto parámetro (puntuacion)
	            stmt.setInt(5, quienLaHaAceptado); // Asignamos el valor para el sexto parámetro (quienLaHaAceptado)
	
	            stmt.executeUpdate(); // Ejecutamos la actualización de la base de datos
	        } catch (SQLException e) {
	            e.printStackTrace(); // En caso de error, imprimimos la traza del error
	        }
		}
	
	public void cargarDatos() {
	    GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Pelicula"; // Consulta para obtener todas las pel�culas

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
	            
	            // Crear listas vac�as para , perteneceA y susResenas
	           
	            ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>();
	            ArrayList<Resena> susResenas = new ArrayList<>();
	            
	            float puntuacionMedia = GestorResenas.getGestorResenas().obtenerPuntuacion(idPelicula);
	            // Crear el objeto Pelicula
	            Pelicula pelicula = new Pelicula(
	                idPelicula, titulo, reparto, anio, puntuacionMedia, susResenas, quienLaHaAceptado
	            );

	            
	            // A�adir al HashMap (clave: idPelicula, valor: Pelicula)
	            peliculas.put(idPelicula, pelicula);
	        }

	        System.out.println("Pel�culas cargadas exitosamente.");

	        // Imprimir pel�culas para comprobar (esto puede eliminarse m�s adelante)
	        for (Entry<Integer, Pelicula> entry : peliculas.entrySet()) {
	        	Integer idPelicula = entry.getKey();
	            Pelicula pelicula = entry.getValue();
	            System.out.println("ID Pel�cula: " + idPelicula);
	            System.out.println(pelicula); // Asume que la clase Pelicula tiene un m�todo toString()
	        }
	        System.out.println("---------------------------------------------");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando las pel�culas desde la base de datos.", e);
	    }
	}

	public Pelicula buscarPeliculaPorId(int idPelicula) 
	{
		if (peliculas.containsKey(idPelicula)) 
		{
	        return peliculas.get(idPelicula); // Retorna la pel�cula si existe en el mapa
	    } 
		else 
		{
	        System.out.println("Pel�cula con ID " + idPelicula + " no encontrada.");
	        return null; // Devuelve null si no se encuentra la pel�cula
	    }
	}
}