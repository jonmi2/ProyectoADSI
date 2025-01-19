package modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;

public class GestorPpal 
{
	private static GestorPpal miPpal = new GestorPpal();
	
	private GestorPpal()
	{
		
	}
	
	public static GestorPpal getGestorPpal()
	{
		return miPpal;
	}

	public void cargarDatos() {
		// metodo para cargar los datos de la base de datos
		GestorUsuarios misUsuarios = GestorUsuarios.getGestorUsuarios();
		misUsuarios.cargarDatos();
		
		GestorResenas misResenas = GestorResenas.getGestorResenas();
		misResenas.cargarDatos();
		
		GestorPeliculas misPelis = GestorPeliculas.getGestorPelis();
		misPelis.cargarDatos();		
		
		GestorAlquileres misAlquileres = GestorAlquileres.getGestorAlquileres();
		misAlquileres.cargarDatos();
		
		GestorListaPersonalizada misListas = GestorListaPersonalizada.getGestorListaPersonalizada();
		misListas.cargarDatos();
	}

	public boolean puedeIniciarSesion(int pidUsuario) 
	{
		GestorUsuarios misUsuarios = GestorUsuarios.getGestorUsuarios();
		return misUsuarios.puedeIniciarsSesion(pidUsuario);
	}
	
	public Usuario getCurrentUser(int pidUsuario) {
		GestorUsuarios misUsuarios = GestorUsuarios.getGestorUsuarios();
		return misUsuarios.getUsuarios().get(pidUsuario);
	}

	public String buscarNombreUsuario(int idUsuario) {
		GestorUsuarios misUsuarios = GestorUsuarios.getGestorUsuarios();
		return misUsuarios.buscarNombreUsuario(idUsuario);
	}

	public String getRolUsuario(int pid) 
	{
		return GestorUsuarios.getGestorUsuarios().getRolUsuario(pid);
	}

	public JSON1 buscarInfoPeli(String peliAbuscar) 
	{
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		if(miGestorPelis.buscarInfoPeli(peliAbuscar)==null)
		{
			System.out.println("Gestor pelis nos devuelve un JSON1 null");
			return null;
		}
		else
		{
			return miGestorPelis.buscarInfoPeli(peliAbuscar);
		}
	}
	
	public void anadirPeliSolicitada (String titulo) {
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		miGestorPelis.anadirPeliSolicitada(titulo);
	}
	
	public void anadirPelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
                    float puntuacionMedia, ArrayList<ListaPersonalizada> perteneceA, ArrayList<Resena> lresenas, int quienLaHaAceptado) 
	{
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		miGestorPelis.anadirPelicula(idPelicula, titulo, reparto, anio, puntuacionMedia, perteneceA, lresenas, quienLaHaAceptado);
	}

	public JSON3 getResenasPeli(int idPeli) 
	{
		GestorResenas gestorRes = GestorResenas.getGestorResenas();		
		return gestorRes.getResenasPeli(idPeli);
	}

	public void anadirAlquiler(int idUsuario, int idPeli, LocalDateTime ahora) 
	{
		//añadir objeto java al gestor de alquileres
		GestorAlquileres gestorAlquileres = GestorAlquileres.getGestorAlquileres();
		gestorAlquileres.crearYadd(idUsuario, idPeli, ahora);
		
		//añadir alquiler a la BD
		// Convertir LocalDateTime a Date en formato 'yyyy-MM-dd'
	    Date fecha = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
	    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String fechaFormateada = dateFormat.format(fecha);

	    // Generar la sentencia SQL
	    String sentencia = "INSERT INTO Alquiler (idUsuario, idPelicula, fecha) " +
	                       "VALUES (" + idUsuario + ", " + idPeli + ", '" + fechaFormateada + "')";

	    // Ejecutar la sentencia usando execSQL
	    GestorBD gestorbd = GestorBD.getGestorBD();
	    gestorbd.execSQL(sentencia);
		
	}
	public void anadirResena(String peliAbuscar,int idUsuario, int idPelicula, float puntuacion, String comentario) {
        GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
        miGestorPelis.anadirResenaAPeli(peliAbuscar, idUsuario, idPelicula, puntuacion, comentario);
    }
	
	public ArrayList<String> getPelisAValidar() {
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		return miGestorPelis.getPelisAValidar();
	}
	
	public void validarPeli(String titulo, int idUsuario) {
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		miGestorPelis.validarPeli(titulo, idUsuario);
	}
	
	public void rechazarPeli(String titulo) {
		GestorPeliculas miGestorPelis = GestorPeliculas.getGestorPelis();
		miGestorPelis.rechazarPeli(titulo);
	}

	public void registrar(String nombre, String email) {
		GestorUsuarios usus = GestorUsuarios.getGestorUsuarios();
		usus.registrar(nombre,email);
				
		
	}
	
}
