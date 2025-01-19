package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
	
	public Pelicula buscarPeliPorNombre (String nombre)
	{
		for (Pelicula pelicula : peliculas.values()) {
	        if (pelicula.getTitulo().equalsIgnoreCase(nombre)) {
	            return pelicula; // Devuelve la película si el título coincide
	        }
	    }
	    return null; // Devuelve null si no se encuentra la película
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
	
	public Pelicula insertarPelicula(int idUsuario, String nombrePelicula, String anioPelicula, ListaPersonalizada lp) {
		GestorPeliculas gp = GestorPeliculas.getGestorPelis();
        
        int idPelicula = gp.getPeliculas().keySet().stream().max(Integer::compare).orElse(0) + 1;
        ArrayList<String> reparto = new ArrayList<>();
        reparto.add("ActorFamoso");
        
        ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>();
        perteneceA.add(lp);
        
        ArrayList<Resena> lresenas = new ArrayList<>();
        Resena resena1 = new Resena(idUsuario, idPelicula, 5.0f, "Excelente pelÃ­cula"); // Ejemplo
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
	
	public void anadirPeliSolicitada(String titulo) {
		int idPelicula = peliculas.keySet().stream().max(Integer::compare).orElse(0) + 1;
		ArrayList<String> reparto = new ArrayList<>();
		ArrayList<Resena> lresenas = new ArrayList<>();
		Pelicula nuevaPeli = new Pelicula (idPelicula,titulo,reparto,0,0,lresenas,0);
		peliculas.put(idPelicula, nuevaPeli);
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
//	            stmt.setInt(1, idPelicula); // Asignamos el valor para el primer parÃ¡metro (idPelicula)
	            stmt.setString(1, titulo); // Asignamos el valor para el segundo parÃ¡metro (titulo)
	            stmt.setString(2, String.join(", ", reparto)); // Convertimos el ArrayList en una cadena y lo asignamos
	            stmt.setInt(3, anio); // Asignamos el valor para el cuarto parÃ¡metro (anio)
	            stmt.setFloat(4, puntuacion); // Asignamos el valor para el quinto parÃ¡metro (puntuacion)
	            stmt.setInt(5, quienLaHaAceptado); // Asignamos el valor para el sexto parÃ¡metro (quienLaHaAceptado)
	
	            stmt.executeUpdate(); // Ejecutamos la actualizaciÃ³n de la base de datos
	        } catch (SQLException e) {
	            e.printStackTrace(); // En caso de error, imprimimos la traza del error
	        }
		}
	
	public void cargarDatos() {
	    GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Pelicula"; // Consulta para obtener todas las pelï¿½culas

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
	            
	            // Crear listas vacï¿½as para , perteneceA y susResenas
	           
	            ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>();
	            ArrayList<Resena> susResenas = new ArrayList<>();
	            
	            float puntuacionMedia = GestorResenas.getGestorResenas().obtenerPuntuacion(idPelicula);
	            // Crear el objeto Pelicula
	            Pelicula pelicula = new Pelicula(
	                idPelicula, titulo, reparto, anio, puntuacionMedia, susResenas, quienLaHaAceptado
	            );

	            
	            // Aï¿½adir al HashMap (clave: idPelicula, valor: Pelicula)
	            peliculas.put(idPelicula, pelicula);
	        }

	        System.out.println("Pelï¿½culas cargadas exitosamente.");

	        // Imprimir pelï¿½culas para comprobar (esto puede eliminarse mï¿½s adelante)
	        for (Entry<Integer, Pelicula> entry : peliculas.entrySet()) {
	        	Integer idPelicula = entry.getKey();
	            Pelicula pelicula = entry.getValue();
	            System.out.println("ID Pelï¿½cula: " + idPelicula);
	            System.out.println(pelicula); // Asume que la clase Pelicula tiene un mï¿½todo toString()
	        }
	        System.out.println("---------------------------------------------");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando las pelï¿½culas desde la base de datos.", e);
	    }
	}

	public Pelicula buscarPeliculaPorId(int idPelicula) 
	{
		if (peliculas.containsKey(idPelicula)) 
		{
	        return peliculas.get(idPelicula); // Retorna la pelï¿½cula si existe en el mapa
	    } 
		else 
		{
	        System.out.println("Pelï¿½cula con ID " + idPelicula + " no encontrada.");
	        return null; // Devuelve null si no se encuentra la pelï¿½cula
	    }
	}
	
	public ArrayList<String> getPelisAValidar() {
		ArrayList<String> lista = new ArrayList<String>();
		for (Pelicula pelicula : peliculas.values()) 
	    {
			if(pelicula.getQuienLaHaAceptado() == 0) {
				lista.add(pelicula.getTitulo());
			}
	    }
		return lista;
	}
	
	public void validarPeli(String titulo, int idUsuario) 
	{			
		JSON4 json = this.getInfoPeliAPI(titulo);
		ArrayList<String> reparto = json.getReparto();
		int anio = json.getAnio();

		for (Pelicula pelicula : peliculas.values()) {
			if(pelicula.getTitulo().equals(titulo)) 
			{
				pelicula.setReparto(reparto);
				pelicula.setAceptador(idUsuario);
				pelicula.setAnio(anio);
				String repartoString = reparto.toString();
				
				GestorBD bd = GestorBD.getGestorBD();
				String sentencia = "INSERT INTO Pelicula (titulo, reparto, anio, puntuacion, idAceptador) " +
	                       "VALUES ('" + titulo + "', '" + repartoString + "', " + anio + ", " + 0 + ", " + idUsuario + ")";
				bd.execSQL(sentencia);
			}
		}
		
		
	}
	
	public void rechazarPeli(String titulo) {
		for (Pelicula pelicula : peliculas.values()) {
			if(pelicula.getTitulo().equals(titulo)) {
				peliculas.remove(pelicula.getIdPelicula());
			}
		}
	}
	
	public JSON4 getInfoPeliAPI(String titulo) {
	    ArrayList<String> actoresList = new ArrayList<>();
	    int anio = 0;  // Variable para almacenar el año
	    try (Scanner scanner = new Scanner(System.in)) {
	        while (!titulo.equalsIgnoreCase("exit")) {
	            String jsonResponse = fetchMovieData(titulo);

	            if (jsonResponse != null) {
	                // Verificar si la respuesta indica que no se encontró la película
	                if (jsonResponse.contains("\"Response\":\"False\"")) {
	                    System.out.println("Película no encontrada.");
	                } else {
	                    // Buscar el campo "Actors" en la respuesta
	                    String actoresTag = "\"Actors\":\"";
	                    int startIdx = jsonResponse.indexOf(actoresTag);

	                    if (startIdx != -1) {
	                        startIdx += actoresTag.length();
	                        int endIdx = jsonResponse.indexOf("\"", startIdx);

	                        if (endIdx != -1) {
	                            String actores = jsonResponse.substring(startIdx, endIdx);

	                            // Separar los actores por comas y agregar a la lista
	                            String[] actoresArray = actores.split(",\\s*");
	                            for (String actor : actoresArray) {
	                                actoresList.add(actor);
	                            }
	                        }
	                    }

	                    // Buscar el campo "Year" para obtener el año
	                    String yearTag = "\"Year\":\"";
	                    int yearStartIdx = jsonResponse.indexOf(yearTag);

	                    if (yearStartIdx != -1) {
	                        yearStartIdx += yearTag.length();
	                        int yearEndIdx = jsonResponse.indexOf("\"", yearStartIdx);

	                        if (yearEndIdx != -1) {
	                            String yearString = jsonResponse.substring(yearStartIdx, yearEndIdx);
	                            anio = Integer.parseInt(yearString);  // Convertir el año a entero
	                        }
	                    }

	                    // Crear y devolver el objeto JSON4 con los actores y el año
	                    return new JSON4(actoresList, anio);
	                }
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Se produjo un error: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return new JSON4(new ArrayList<>(), 0); // En caso de error, devolver una lista vacía y año 0
	}

	private static String fetchMovieData(String titulo) {
	    try {
	        String API_KEY = "3c64c9aa";
	        String urlString = "http://www.omdbapi.com/?apikey=" + API_KEY + "&t=" + titulo.replace(" ", "%20");
	        URL url = new URL(urlString);

	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder respuesta = new StringBuilder();
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) {
	            respuesta.append(inputLine);
	        }
	        in.close();

	        return respuesta.toString();
	    } catch (Exception e) {
	        System.out.println("Error al realizar la consulta: " + e.getMessage());
	        return null;
	    }
	}
		 
}