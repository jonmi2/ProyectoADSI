package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorBD;
import modelo.GestorPpal;
import modelo.ResultadoSQL;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class Inicio extends JFrame {

	private JPanel panelPpal;

	private static Inicio miMenu = new Inicio();
	private Controler controler = null;
	private JPanel panelArriba;
	private JPanel panelAbajo;
	private JLabel labelVideoclub;
	private JLabel labelPpal;
	private JPanel panelAbajoIzq;
	private JPanel panelAbajoDer;
	private JButton botonRegistrarse;
	private JButton botonIniciarSesion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Inicio frame = new Inicio();
					frame.setVisible(true);
					
					GestorBD gestorBD = GestorBD.getGestorBD();
			        gestorBD.inicializarBaseDeDatos(); // Inicializa la base de datos con unos datos la primera vez
			        
					//metodo para cargar los datos de la base de datos en objetos de java
					GestorPpal miPpal = GestorPpal.getGestorPpal();
					miPpal.cargarDatos();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			
		});
	}

	/**
	 * Create the frame.
	 */
	private Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Panel principal");
		
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(0, 128, 192));
		panelPpal.setForeground(new Color(0, 128, 192));
		panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPpal);
		panelPpal.setLayout(new GridLayout(2, 1, 0, 0));
		panelPpal.add(getPanel());
		panelPpal.add(getPanelAbajo());
	}
	
	public static Inicio getMenu() {
		return miMenu;
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
			if (e.getSource().equals(getBotonIniciarSesion()))
			{
				System.out.println("------------------------------------");
				System.out.println("Pulsado boton inicio sesion");
				System.out.println("------------------------------------");
				setVisible(false);
				IniciarSesion miInicioSesion = IniciarSesion.getIniciarSesion();
				miInicioSesion.setVisible(true);
			}
		}

	}
	//-----------------------------Controler------------------------------------------------
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			//lo que tenga q hacer
		}
	}
	private JPanel getPanel() {
		if (panelArriba == null) {
			panelArriba = new JPanel();
			panelArriba.setBackground(new Color(0, 64, 128));
			panelArriba.setLayout(new BorderLayout(0, 0));
			panelArriba.add(getLabelVideoclub(), BorderLayout.NORTH);
			panelArriba.add(getLabelPpal(), BorderLayout.SOUTH);
		}
		return panelArriba;
	}
	private JPanel getPanelAbajo() {
		if (panelAbajo == null) {
			panelAbajo = new JPanel();
			panelAbajo.setBackground(new Color(0, 128, 192));
			panelAbajo.setLayout(new GridLayout(1, 2, 0, 0));
			panelAbajo.add(getPanelAbajoIzq());
			panelAbajo.add(getPanelAbajoDer());
		}
		return panelAbajo;
	}
	private JLabel getLabelVideoclub() {
		if (labelVideoclub == null) {
			labelVideoclub = new JLabel("VIDEOCLUB");
			labelVideoclub.setHorizontalAlignment(SwingConstants.CENTER);
			labelVideoclub.setVerticalAlignment(SwingConstants.BOTTOM);
			labelVideoclub.setFont(new Font("Snap ITC", Font.PLAIN, 36));
			labelVideoclub.setForeground(new Color(192, 192, 192));
		}
		return labelVideoclub;
	}
	private JLabel getLabelPpal() {
		if (labelPpal == null) {
			labelPpal = new JLabel("PANTALLA PRINCIPAL");
			labelPpal.setFont(new Font("Tahoma", Font.ITALIC, 33));
			labelPpal.setHorizontalAlignment(SwingConstants.CENTER);
			labelPpal.setForeground(new Color(192, 192, 192));
		}
		return labelPpal;
	}
	private JPanel getPanelAbajoIzq() {
		if (panelAbajoIzq == null) {
			panelAbajoIzq = new JPanel();
			panelAbajoIzq.setBackground(new Color(0, 128, 192));
			panelAbajoIzq.add(getBotonRegistrarse());
		}
		return panelAbajoIzq;
	}
	private JPanel getPanelAbajoDer() {
		if (panelAbajoDer == null) {
			panelAbajoDer = new JPanel();
			panelAbajoDer.setBackground(new Color(0, 128, 192));
			panelAbajoDer.add(getBotonIniciarSesion());
		}
		return panelAbajoDer;
	}
	private JButton getBotonRegistrarse() {
		if (botonRegistrarse == null) {
			botonRegistrarse = new JButton("REGISTRARSE");
		}
		return botonRegistrarse;
	}
	private JButton getBotonIniciarSesion() {
		if (botonIniciarSesion == null) {
			botonIniciarSesion = new JButton("INICIAR SESION");
			botonIniciarSesion.addActionListener(getControler());
		}
		return botonIniciarSesion;
	}
}
