package modelo;
import java.util.ArrayList;

public class JSON3 
{
		//private float puntuacion;
		//private String comentario;
		
		private int idPeli;
		private ArrayList<JSON2> resenas;
		
		public JSON3(int idP)
		{
			this.idPeli=idP;
			this.resenas = new ArrayList<JSON2>();
		}
		
		public int getId()
		{
			return this.idPeli;
		}
		
		public void anadirDatosResena(float puntu, String coment)
		{
			JSON2 json = new JSON2(puntu,coment);
			this.resenas.add(json);
		}
		
		public ArrayList<JSON2> getResenas()
		{
			return this.resenas;
		}
		
		// Método toString
	    public String toString() {
	        return "JSON3{" +
	               "idPeli='" + idPeli + '\'' +
	               ", resenas=" + resenas.toString() +             
	               '}';
	    }
	
}
