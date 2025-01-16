package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
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
					setVisible(false);
					alquiVista.setVisible(true);
				}
			}

		}
		//-----------------------------Controler------------------------------------------------
}
