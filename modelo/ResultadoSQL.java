package modelo;
import java.util.*;

public class ResultadoSQL 
{
	private List<Map<String, Object>> filas; // Lista de filas (cada fila es un mapa de columna a valor)
    private int indiceActual; // �ndice de la fila actual
	
	public ResultadoSQL(List<Map<String, Object>> filas)
	{
		this.filas = filas;
        this.indiceActual = -1; // Comienza antes de la primera fila
	}
	
	public boolean next()
	{
		//selecciona la siguiente tupla del resultado, devuelve false si no hay mas
		if (indiceActual < filas.size() - 1) 
		{
            indiceActual++;
            return true;
        }
        return false;
	}
	
	
	//un get para cada tipo dato de la BD, FALTAN MAS POR HACER!!!
	//por ejemplo: getString(�Nombre�) devuelve Juan. Juan seria el valor de la columna nombre en la fila apuntada por el next.
	public String getString(String tipoAtributo)
	{
		if (indiceActual >= 0 && indiceActual < filas.size()) 
		{
	        Object valor = filas.get(indiceActual).get(tipoAtributo);
	        if (valor != null) 
	        {
	            return valor.toString(); // Devuelve la representaci�n en cadena si no es null
	        } 
	        else 
	        {
	            return null; // Devuelve null si el valor es null
	        }
	    }
	    throw new IllegalStateException("Cursor fuera de rango. Aseg�rate de llamar a next() antes.");
	}
	
	public Integer getInt(String tipoAtributo)
	{
		if (indiceActual >= 0 && indiceActual < filas.size()) 
		{
	        Object valor = filas.get(indiceActual).get(tipoAtributo);
	        if (valor != null) 
	        {
	            return Integer.parseInt(valor.toString()); // Devuelve la representaci�n en cadena si no es null
	        } 
	        else 
	        {
	            return null; // Devuelve null si el valor es null
	        }
	    }
	    throw new IllegalStateException("Cursor fuera de rango. Aseg�rate de llamar a next() antes.");
	}

	public float getFloat(String tipoAtributo) {
	    if (indiceActual >= 0 && indiceActual < filas.size()) {
	        Object valor = filas.get(indiceActual).get(tipoAtributo);
	        if (valor != null) {
	            if (valor instanceof Double) {
	                return ((Double) valor).floatValue();
	            } else if (valor instanceof Integer) {
	                return ((Integer) valor).floatValue();
	            } else {
	                throw new ClassCastException("El valor no es un tipo num�rico compatible.");
	            }
	        } else {
	            return 0; // Devuelve 0 si el valor es null
	        }
	    }
	    throw new IllegalStateException("Cursor fuera de rango. Aseg�rate de llamar a next() antes.");
	}

}
