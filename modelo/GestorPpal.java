package modelo;

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
		
		GestorPeliculas misPelis = GestorPeliculas.getGestorPelis();
		misPelis.cargarDatos();
		
		GestorResenas misResenas = GestorResenas.getGestorResenas();
		misResenas.cargarDatos();
		
		//hay que hacerlo con todos los datos, no solo con los usuarios
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
	
}
