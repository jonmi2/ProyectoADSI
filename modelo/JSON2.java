package modelo;

public class JSON2 
{
	private float puntuacion;
	private String comentario;
	
	public JSON2(float pPun,String pCom)
	{
		this.comentario=pCom;
		this.puntuacion=pPun;
	}
	
	public float getPuntuacion()
	{
		return this.puntuacion;
	}
	
	public String getComent()
	{
		return this.comentario;
	}
	// Método toString
    public String toString() {
        return "JSON2{" +
               "puntuacion='" + puntuacion + '\'' +
               ", comentario=" + comentario +             
               '}';
    }
}
