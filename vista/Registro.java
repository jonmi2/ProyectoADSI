package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registro extends JFrame {

	private JPanel contentPane;
	private Controler controler = null;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JButton btnRegistrar;
	private static Registro miRegis = new Registro();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	public static Registro getRegis()
	{
		return miRegis;
	}
	
	//-----------------------------Controler------------------------------------------------
		private class Controler implements ActionListener 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource().equals(btnRegistrar))
				{
					System.out.println("------------------------------------");
					System.out.println("Pulsado boton registrar");
					System.out.println("------------------------------------");
					String nombre = txtNombre.getText();
					String email = txtEmail.getText();
					GestorPpal gestor = GestorPpal.getGestorPpal();
					gestor.registrar(nombre,email);
				}
				else if (e.getSource().equals(btnNewButton))
				{
					System.out.println("Pulsado boton astras");
					setVisible(false);
					Inicio ini = Inicio.getMenu();
					ini.setVisible(true);
				}
			}

		}
		//-----------------------------Controler------------------------------------------------

	/**
	 * Create the frame.
	 */
	private Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblNewLabel());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(2, 1, 0, 0));
			panel_1.add(getPanel_2());
			panel_1.add(getPanel_3());
		}
		return panel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("REGISTRATE!!!!!!");
			lblNewLabel.setFont(new Font("Verdana Pro Cond Black", Font.PLAIN, 30));
		}
		return lblNewLabel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(2, 1, 0, 0));
			panel_2.add(getTxtNombre());
			panel_2.add(getTxtEmail());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(0, 2, 0, 0));
			panel_3.add(getBtnRegistrar());
			panel_3.add(getBtnNewButton());
		}
		return panel_3;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setText("Introduce tu email");
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setText("Introduce tu nombre");
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JButton getBtnRegistrar() {
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrarme");
			btnRegistrar.addActionListener(getControler());
		}
		return btnRegistrar;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Atras");
			btnNewButton.addActionListener(getControler());
		}
		return btnNewButton;
	}
}
