package modelo;

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
	
}
