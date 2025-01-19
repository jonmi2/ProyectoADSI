package modelo;

import java.util.ArrayList;

public class JSON4 
{
	private int anio;
	private ArrayList<String> reparto;
	
	public JSON4(ArrayList<String> preparto, int panio)
	{
		this.anio=panio;
		this.reparto=preparto;
	}
	
	public int getAnio()
	{
		return this.anio;
	}
	
	
	public ArrayList<String> getReparto()
	{
		return this.reparto;
	}
	

}
