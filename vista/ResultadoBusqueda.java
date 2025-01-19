package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;
import modelo.JSON1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class ResultadoBusqueda extends JFrame {

	private JPanel panelPpal;
	private static ResultadoBusqueda miRdoBusqueda = new ResultadoBusqueda();
	private JPanel panelVerResenas;
	private JPanel panelArriba;
	private JPanel panelInfo;
	private JLabel lblResultadoTitulo;
	private JLabel lblInfo;
	private JButton botonResenas;
	private Controler controler = null;
	private JButton botonAtras;
	private int idPeli;
	private String nomPeli;
	private int idUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultadoBusqueda frame = new ResultadoBusqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static ResultadoBusqueda getRdoBusqueda()
	{
		return miRdoBusqueda;	
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	//-----------------------------Controler------------------------------------------------
		private class Controler implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource().equals(botonResenas))
				{
					if (getBotonResenas().getText().equals("Atras"))
					{
						System.out.println("volvemos patras");
						AlquilerPeliculas vistaAlquiP = AlquilerPeliculas.getAlquilerPeliculas();
						setVisible(false);
						vistaAlquiP.setVisible(true);
					}
					else
					{
						System.out.println("vamos a ver resenas!!!!");
						ResenasVista misResenas = ResenasVista.getResenasVista();
						misResenas.actualizar(idPeli,nomPeli,idUsuario);
						setVisible(false);
						misResenas.setVisible(true);
					}							
				}
				
				if (e.getSource().equals(botonAtras))
				{
					System.out.println("volvemos patras");
					AlquilerPeliculas vistaAlquiP = AlquilerPeliculas.getAlquilerPeliculas();
					setVisible(false);
					vistaAlquiP.setVisible(true);
				}
			}

		}
		//-----------------------------Controler------------------------------------------------
	
	private ResultadoBusqueda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPpal = new JPanel();
		panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPpal);
		panelPpal.setLayout(new GridLayout(3, 1, 0, 0));
		panelPpal.add(getPanelArriba());
		panelPpal.add(getPanelInfo());
		panelPpal.add(getPanelVerResenas());
	}

	private JPanel getPanelVerResenas() {
		if (panelVerResenas == null) {
			panelVerResenas = new JPanel();
			panelVerResenas.setLayout(new GridLayout(1, 2, 0, 0));
			panelVerResenas.add(getBotonResenas());
			panelVerResenas.add(getBotonAtras());
		}
		return panelVerResenas;
	}
	private JPanel getPanelArriba() {
		if (panelArriba == null) {
			panelArriba = new JPanel();
			panelArriba.add(getLblResultadoTitulo());
		}
		return panelArriba;
	}
	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.add(getLblInfo());
		}
		return panelInfo;
	}
	private JLabel getLblResultadoTitulo() {
		if (lblResultadoTitulo == null) {
			lblResultadoTitulo = new JLabel("Resultado de la busqueda");
			lblResultadoTitulo.setForeground(new Color(0, 128, 128));
			lblResultadoTitulo.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 32));
		}
		return lblResultadoTitulo;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("Informacion de la peli");
			lblInfo.setFont(new Font("Arial", Font.PLAIN, 17));
		}
		return lblInfo;
	}
	private JButton getBotonResenas() {
		if (botonResenas == null) {
			botonResenas = new JButton("Quieres ver las resenas de la peli?");
			botonResenas.addActionListener(getControler());
		}
		return botonResenas;
	}

	public void actualizar(int idUsuario, String peliAbuscar) 
	{
		this.idUsuario=idUsuario;
		
		//llamo al ppal para q me devuelva un JSON con la info de la peli
		GestorPpal miPpal = GestorPpal.getGestorPpal();
		JSON1 peliculaJson = miPpal.buscarInfoPeli(peliAbuscar);	
		if (peliculaJson==null)
		{
			this.lblInfo.setText("No se encontro la peli");
			this.botonResenas.setText("Atras");
		}
		else
		{
			this.botonResenas.setText("Quieres ver las resenas de la peli?");
			int anioP = peliculaJson.getAnioP();
			int idP = peliculaJson.getIdP();
			String nomP = peliculaJson.getNombreP();
			this.idPeli=idP;
			this.nomPeli=nomP;
			float mediaP = peliculaJson.getPuntuMedia();
			this.lblInfo.setText("id película = " + idP + "\n" +
                    "Nombre = " + nomP + "\n" +
                    "Año = " + anioP + "\n" +
                    "Puntuación media = " + mediaP);
		}
	}


	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atras");
			botonAtras.addActionListener(getControler());
		}
		return botonAtras;
	}
}
