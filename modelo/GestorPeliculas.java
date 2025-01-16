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
	    // Si no se encuentra la pel�cula, devolver null
	    System.out.println("No encontrada ninguna peli con ese nombre, se devuelve un JSON1 null");
	    return null;
	}

	public void cargarDatos() 
	{
		GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Pelicula"; // Consulta para obtener todos los usuarios

	    try {
	        ResultadoSQL resultado = gestorBD.consultaSQL(query); // Ejecutar la consulta SQL

	        // Recorrer los resultados
	        while (resultado.next()) {
	            // Leer los datos de la fila actual
	            int idPelicula = Integer.parseInt(resultado.getString("idPelicula"));
	            String titulo = resultado.getString("titulo");
	            String repartoStr = resultado.getString("reparto");
	            int anio = Integer.parseInt(resultado.getString("a�o"));
	            float puntuacionMedia = Float.parseFloat(resultado.getString("puntuacion"));
	            int quienLaHaAceptado = Integer.parseInt(resultado.getString("idAceptador"));
	            
	            //Procesar el string de reparto
	            ArrayList<String> reparto = new ArrayList<>();
	            if (repartoStr != null && !repartoStr.isEmpty()) 
	            {
	                //los actores est�n separados por comas en la base de datos
	                String[] repartoArray = repartoStr.split(",");
	                for (String actor : repartoArray)
	                {
	                    reparto.add(actor.trim()); // A�adir cada actor a la lista
	                }
	            }

	            // Crear listas vac�as para perteneceA (listas personalizadas) si es necesario
	            ArrayList<ListaPersonalizada> perteneceA = new ArrayList<>(); // Puede que necesites llenar esta lista seg�n tu l�gica

	            // Crear el objeto Pelicula
	            Pelicula pelicula = new Pelicula(
	                idPelicula, titulo, reparto, anio, puntuacionMedia, perteneceA, quienLaHaAceptado
	            );

	            // A�adir al HashMap (clave: idPelicula, valor: Pelicula)
	            peliculas.put(idPelicula, pelicula);
	        }

	        System.out.println("Pel�culas cargadas exitosamente.");

	        // Imprimir pel�culas para comprobar (esto puede eliminarse m�s adelante)
	        for (Entry<Integer, Pelicula> entry : peliculas.entrySet()) 
	        {
	            Integer idPelicula = entry.getKey();
	            Pelicula pelicula = entry.getValue();
	            System.out.println("ID Pel�cula: " + idPelicula);
	            System.out.println(pelicula); // Asume que la clase Pelicula tiene un m�todo toString()
	        }

	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando las pel�culas desde la base de datos.", e);
	    }
		
	}
}
