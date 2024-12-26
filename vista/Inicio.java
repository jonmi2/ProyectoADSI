package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class Inicio extends JFrame {

	private JPanel contentPane;

	private static Inicio miMenu = new Inicio();
	private Controler controler = null;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField txtPantallaPrincipal;
	private JTextField txtVideoclub;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	private Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setForeground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getTxtPantallaPrincipal());
		contentPane.add(getTxtVideoclub());
		contentPane.add(getBtnNewButton());
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
			
		}

	}
	//-----------------------------Controler------------------------------------------------
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			//lo que tenga q hacer
		}
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Iniciar Sesi\u00F3n");
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Registrarse");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btnNewButton_1;
	}
	private JTextField getTxtPantallaPrincipal() {
		if (txtPantallaPrincipal == null) {
			txtPantallaPrincipal = new JTextField();
			txtPantallaPrincipal.setText("PANTALLA PRINCIPAL");
			txtPantallaPrincipal.setColumns(10);
		}
		return txtPantallaPrincipal;
	}
	private JTextField getTxtVideoclub() {
		if (txtVideoclub == null) {
			txtVideoclub = new JTextField();
			txtVideoclub.setText("VIDEOCLUB");
			txtVideoclub.setColumns(10);
		}
		return txtVideoclub;
	}
}
