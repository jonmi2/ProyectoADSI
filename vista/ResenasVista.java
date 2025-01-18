package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;
import modelo.JSON2;
import modelo.JSON3;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class ResenasVista extends JFrame {

	private JPanel panelPPal;
	private static ResenasVista misResenasv = new ResenasVista();
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel labelResenastitulo;
	private JButton botonAtras;
	private JButton botonAlquilar;
	private Controler controler = null;
	private JLabel lblRes1;
	private JLabel lblRes2;
	private JLabel lblRes3;
	private JLabel lblRes4;
	private int idUsuario;
	private int idPeli;
	private String nomPeli;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResenasVista frame = new ResenasVista();
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
	private ResenasVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPPal = new JPanel();
		panelPPal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPPal);
		panelPPal.setLayout(new GridLayout(6, 1, 0, 0));
		panelPPal.add(getPanel_1());
		panelPPal.add(getPanel_2());
		panelPPal.add(getPanel_3());
		panelPPal.add(getPanel_4());
		panelPPal.add(getPanel_5());
		panelPPal.add(getPanel());
	}
	
	public static ResenasVista getResenasVista()
	{
		return misResenasv;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getBotonAtras());
			panel.add(getBotonAlquilar());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLabelResenastitulo());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getLblRes1());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getLblRes2());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.add(getLblRes3());
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getLblRes4());
		}
		return panel_5;
	}
	private JLabel getLabelResenastitulo() {
		if (labelResenastitulo == null) {
			labelResenastitulo = new JLabel("Resenas de la pelicula");
			labelResenastitulo.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 33));
		}
		return labelResenastitulo;
	}
	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atras");
			botonAtras.addActionListener(getControler());
		}
		return botonAtras;
	}
	private JButton getBotonAlquilar() {
		if (botonAlquilar == null) {
			botonAlquilar = new JButton("Alquilar Peli");
			botonAlquilar.addActionListener(getControler());
		}
		return botonAlquilar;
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
				if (e.getSource().equals(botonAtras))
				{
					System.out.println("Patras");
					ResultadoBusqueda rdoBusqueda = ResultadoBusqueda.getRdoBusqueda();					
					setVisible(false);
					rdoBusqueda.setVisible(true);
				}		
				
				if (e.getSource().equals(botonAlquilar));
				{
					System.out.println("Alquilar peli");
					AlquilarVista alquiVista = AlquilarVista.getAlquilarVista();		
					alquiVista.actualizarYalquilar(idUsuario,idPeli,nomPeli);
					setVisible(false);
					alquiVista.setVisible(true);
					//añade la película a lista de películas alquiladas del usuario
					//inicia el contador de 48 horas respectivo al tiempo que tiene el usuario para ver la película
					//se guardará el alquiler en el historial de alquileres (BD y java)
					//idUsuario, idPelicula y fecha
				}
			}

		}
		//-----------------------------Controler------------------------------------------------
	private JLabel getLblRes1() {
		if (lblRes1 == null) {
			lblRes1 = new JLabel("");
		}
		return lblRes1;
	}
	private JLabel getLblRes2() {
		if (lblRes2 == null) {
			lblRes2 = new JLabel("");
		}
		return lblRes2;
	}
	private JLabel getLblRes3() {
		if (lblRes3 == null) {
			lblRes3 = new JLabel("");
		}
		return lblRes3;
	}
	private JLabel getLblRes4() {
		if (lblRes4 == null) {
			lblRes4 = new JLabel("");
		}
		return lblRes4;
	}

	public void actualizar(int idPeli, String nomPeli, int idUsuario) 
	{
		this.idUsuario=idUsuario;
		this.idPeli=idPeli;
		this.nomPeli=nomPeli;
		
		this.labelResenastitulo.setText("Resenas de la peli "+nomPeli);
		GestorPpal gestorPpal = GestorPpal.getGestorPpal();
		//JSON3 es un array de JSON2
		
		JSON3 resenas = gestorPpal.getResenasPeli(idPeli);
		int contres = 0;
		
		ArrayList<JSON2> rdoRes = resenas.getResenas();
		Iterator<JSON2> iterator = rdoRes.iterator();
		
		while (iterator.hasNext() && contres<4)
		{
			JSON2 datosresena = iterator.next();
			if (contres==0)
			{
				lblRes1.setText("Comentario: "+datosresena.getComent()+" | Puntuacion: "+datosresena.getPuntuacion());
			}
			else if(contres==1)
			{
				lblRes2.setText("Comentario: "+datosresena.getComent()+" | Puntuacion: "+datosresena.getPuntuacion());
			}
			else if (contres==2)
			{
				lblRes3.setText("Comentario: "+datosresena.getComent()+" | Puntuacion: "+datosresena.getPuntuacion());
			}
			else if (contres==3)
			{
				lblRes4.setText("Comentario: "+datosresena.getComent()+" | Puntuacion: "+datosresena.getPuntuacion());
			}
			contres++;
		}
			
	}
}
