package modelo;



public class JSON1 
{
    private String nombreP;
    private int anioP;
    private float puntuMedia; 
    private int idP;

    // Constructor
    public JSON1(String pnombreP, int panio, float pmedia, int pid) 
    {
        this.anioP=panio;
        this.nombreP=pnombreP;
        this.puntuMedia=pmedia;
        this.idP=pid;
    }
    
    public String getNombreP() {
        return nombreP;
    }

    public int getAnioP() {
        return anioP;
    }

    public float getPuntuMedia() {
        return puntuMedia;
    }

    public int getIdP() {
        return idP;
    }

    // Método toString
    public String toString() {
        return "JSON1{" +
               "nombreP='" + nombreP + '\'' +
               ", anioP=" + anioP +
               ", puntuMedia=" + puntuMedia +
               ", idP=" + idP +
               '}';
    }
    
}
