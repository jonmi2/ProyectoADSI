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
}
