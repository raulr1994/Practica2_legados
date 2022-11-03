package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Seleccion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seleccion frame = new Seleccion();
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
	public Seleccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TIPO DE TAREA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(157, 11, 128, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnEspecfica = new JButton("Espec\u00EDfica");
		btnEspecfica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TareaEspecifica te = new TareaEspecifica();
				te.setVisible(true);
			}
		});
		btnEspecfica.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEspecfica.setBounds(245, 61, 137, 76);
		contentPane.add(btnEspecfica);
		
		JButton btnGeneral = new JButton("General");
		btnGeneral.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TareaGeneral tg = new TareaGeneral();
				tg.setVisible(true);
			}
		});
		btnGeneral.setBounds(46, 61, 137, 76);
		contentPane.add(btnGeneral);
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaPrincipal p = new PantallaPrincipal();
				p.setVisible(true);
			}
		});
		btnNewButton.setBounds(22, 148, 76, 23);
		contentPane.add(btnNewButton);
	}

}
