package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlquilerPeliculas extends JFrame {

	private static AlquilerPeliculas miAlquilerPeliculas = new AlquilerPeliculas();
	private JPanel panelPpal;
	private JPanel panelArr;
	private JPanel panelAbaj;
	private JLabel labelAlquilerPelis;
	private JLabel labelBuscaTuPeli;
	private JTextField fieldNombrePeli;
	private JButton botonBuscar;
	private Controler controler = null;
	private int idUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlquilerPeliculas frame = new AlquilerPeliculas();
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
	private AlquilerPeliculas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPpal = new JPanel();
		panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPpal);
		panelPpal.setLayout(new GridLayout(2, 1, 0, 0));
		panelPpal.add(getPanelArr());
		panelPpal.add(getPanelAbaj());
	}
	
	public static AlquilerPeliculas getAlquilerPeliculas()
	{
		return miAlquilerPeliculas;
	}
	private JPanel getPanelArr() {
		if (panelArr == null) {
			panelArr = new JPanel();
			panelArr.setLayout(new GridLayout(2, 1, 0, 0));
			panelArr.add(getLabelAlquilerPelis());
			panelArr.add(getLabelBuscaTuPeli());
		}
		return panelArr;
	}
	private JPanel getPanelAbaj() {
		if (panelAbaj == null) {
			panelAbaj = new JPanel();
			panelAbaj.setLayout(new GridLayout(2, 1, 0, 0));
			panelAbaj.add(getFieldNombrePeli());
			panelAbaj.add(getBotonBuscar());
		}
		return panelAbaj;
	}
	private JLabel getLabelAlquilerPelis() {
		if (labelAlquilerPelis == null) {
			labelAlquilerPelis = new JLabel("ALQUILER DE PELICULAS");
			labelAlquilerPelis.setHorizontalAlignment(SwingConstants.CENTER);
			labelAlquilerPelis.setForeground(new Color(0, 0, 0));
			labelAlquilerPelis.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
			labelAlquilerPelis.setBackground(new Color(192, 192, 192));
		}
		return labelAlquilerPelis;
	}
	private JLabel getLabelBuscaTuPeli() {
		if (labelBuscaTuPeli == null) {
			labelBuscaTuPeli = new JLabel("BUSCA TU PELICULA FAVORITA!");
			labelBuscaTuPeli.setHorizontalAlignment(SwingConstants.CENTER);
			labelBuscaTuPeli.setForeground(new Color(128, 128, 255));
			labelBuscaTuPeli.setFont(new Font("Stencil", Font.PLAIN, 18));
		}
		return labelBuscaTuPeli;
	}
	private JTextField getFieldNombrePeli() {
		if (fieldNombrePeli == null) {
			fieldNombrePeli = new JTextField();
			fieldNombrePeli.setHorizontalAlignment(SwingConstants.CENTER);
			fieldNombrePeli.setText("Introduce el titulo de la peli que quieras buscar");
			fieldNombrePeli.setColumns(10);
		}
		return fieldNombrePeli;
	}
	private JButton getBotonBuscar() {
		if (botonBuscar == null) {
			botonBuscar = new JButton("Buscar Pelis");
			botonBuscar.addActionListener(getControler());
		}
		return botonBuscar;
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
			if (e.getSource().equals(getBotonBuscar()))
			{
				System.out.println("------------------------------------");
				System.out.println("Pulsado boton de buscar peli");
				System.out.println("------------------------------------");
				String peliAbuscar= fieldNombrePeli.getText();
				System.out.println("Peli a buscar= "+peliAbuscar);
				ResultadoBusqueda rdoBusqueda = ResultadoBusqueda.getRdoBusqueda();
				rdoBusqueda.actualizar(idUsuario, peliAbuscar);	
				setVisible(false);
				rdoBusqueda.setVisible(true);
				
			}
		}

	}
	//-----------------------------Controler------------------------------------------------

	public void actualizar(int idUsuario) {
		this.idUsuario=idUsuario;
	}
}
