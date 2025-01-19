package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;
import modelo.JSON2;
import modelo.JSON3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JButton;

public class validarPeli extends JFrame {

	private static validarPeli validarP = new validarPeli();
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private Controler controler = null;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private int idUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					validarPeli frame = new validarPeli();
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
	private validarPeli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
		contentPane.add(getPanel_2());
		contentPane.add(getPanel_3());
		contentPane.add(getPanel_4());
		contentPane.add(getBtnNewButton());
	}
	public validarPeli getValidarPeli() {
		return validarP;
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
			panel_1.setLayout(new GridLayout(1, 1, 0, 0));
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getBtnNewButton_1());
			panel_1.add(getBtnNewButton_2());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(1, 1, 0, 0));
			panel_2.add(getLblNewLabel_2());
			panel_2.add(getBtnNewButton_3());
			panel_2.add(getBtnNewButton_4());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(1, 1, 0, 0));
			panel_3.add(getLblNewLabel_3());
			panel_3.add(getBtnNewButton_5());
			panel_3.add(getBtnNewButton_6());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(1, 1, 0, 0));
			panel_4.add(getLblNewLabel_4());
			panel_4.add(getBtnNewButton_7());
			panel_4.add(getBtnNewButton_8());
		}
		return panel_4;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("PEL\u00CDCULAS SOLICITADAS");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Atr\u00E1s");
			btnNewButton.addActionListener(getControler());
		}
		return btnNewButton;
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
					if (e.getSource().equals(btnNewButton))
					{
						System.out.println("Patras");
						PanelUsuario panelU = PanelUsuario.getPanelUsuario();					
						setVisible(false);
						panelU.setVisible(true);
					}		
					
					if (e.getSource().equals(btnNewButton_1));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.validarPeli(lblNewLabel_1.getText(),idUsuario);
					}
					if (e.getSource().equals(btnNewButton_3));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.validarPeli(lblNewLabel_2.getText(),idUsuario);
					}
					if (e.getSource().equals(btnNewButton_5));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.validarPeli(lblNewLabel_3.getText(),idUsuario);
					}
					if (e.getSource().equals(btnNewButton_7));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.validarPeli(lblNewLabel_4.getText(),idUsuario);
					}
					if (e.getSource().equals(btnNewButton_2));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.rechazarPeli(lblNewLabel_1.getText());
					}
					if (e.getSource().equals(btnNewButton_4));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.rechazarPeli(lblNewLabel_2.getText());
					}
					if (e.getSource().equals(btnNewButton_6));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.rechazarPeli(lblNewLabel_3.getText());
					}
					if (e.getSource().equals(btnNewButton_8));
					{
						GestorPpal gestorPpal = GestorPpal.getGestorPpal();
						gestorPpal.rechazarPeli(lblNewLabel_4.getText());
					}
				}

			}
			//-----------------------------Controler------------------------------------------------
	
			public void actualizar(int idPeli, String nomPeli, int idUsuario) 
			{
				this.idUsuario=idUsuario;
				GestorPpal gestorPpal = GestorPpal.getGestorPpal();
				//JSON3 es un array de JSON2
				
				ArrayList<String> lista = gestorPpal.getPelisAValidar();
				int contpel = 0;
				Iterator<String> iterator = lista.iterator();
				
				while (iterator.hasNext() && contpel<4)
				{
					String titulo = iterator.next();
					if (contpel==0)
					{
						lblNewLabel_1.setText(titulo);
					}
					else if(contpel==1)
					{
						lblNewLabel_2.setText(titulo);
					}
					else if (contpel==2)
					{
						lblNewLabel_3.setText(titulo);
					}
					else if (contpel==3)
					{
						lblNewLabel_4.setText(titulo);
					}
					contpel++;
				}
			}	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Validar");
			btnNewButton_1.addActionListener(getControler());
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Rechazar");
			btnNewButton_2.addActionListener(getControler());
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("Validar");
			btnNewButton_3.addActionListener(getControler());
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("Rechazar");
			btnNewButton_4.addActionListener(getControler());
		}
		return btnNewButton_4;
	}
	private JButton getBtnNewButton_5() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("Validar");
			btnNewButton_5.addActionListener(getControler());
		}
		return btnNewButton_5;
	}
	private JButton getBtnNewButton_6() {
		if (btnNewButton_6 == null) {
			btnNewButton_6 = new JButton("Rechazar");
			btnNewButton_6.addActionListener(getControler());
		}
		return btnNewButton_6;
	}
	private JButton getBtnNewButton_7() {
		if (btnNewButton_7 == null) {
			btnNewButton_7 = new JButton("Validar");
			btnNewButton_7.addActionListener(getControler());
		}
		return btnNewButton_7;
	}
	private JButton getBtnNewButton_8() {
		if (btnNewButton_8 == null) {
			btnNewButton_8 = new JButton("Rechazar");
			btnNewButton_8.addActionListener(getControler());
		}
		return btnNewButton_8;
	}
}

