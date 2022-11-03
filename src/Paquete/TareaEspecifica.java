package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TareaEspecifica extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreEsp;
	private JTextField textDescripcionEsp;
	private JTextField textFechaEsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TareaEspecifica frame = new TareaEspecifica();
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
	public TareaEspecifica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR TAREA ESPEC\u00CDFICA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 11, 202, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre (15 car\u00E1cteres max):");
		lblNewLabel_1.setBounds(10, 66, 170, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripci\u00F3n (25 car\u00E1cteres max):");
		lblNewLabel_1_1.setBounds(10, 108, 170, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha (ddmm):");
		lblNewLabel_1_1_1.setBounds(10, 150, 170, 27);
		contentPane.add(lblNewLabel_1_1_1);
		
		textNombreEsp = new JTextField();
		textNombreEsp.setBounds(222, 69, 186, 20);
		contentPane.add(textNombreEsp);
		textNombreEsp.setColumns(10);
		
		textDescripcionEsp = new JTextField();
		textDescripcionEsp.setColumns(10);
		textDescripcionEsp.setBounds(222, 111, 186, 20);
		contentPane.add(textDescripcionEsp);
		
		textFechaEsp = new JTextField();
		textFechaEsp.setColumns(10);
		textFechaEsp.setBounds(222, 153, 186, 20);
		contentPane.add(textFechaEsp);
		
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripEspecifica = textDescripcionEsp.getText();
				String nombreEspecifica = textNombreEsp.getText();
				if(descripEspecifica.length()<=25 || nombreEspecifica.length()<=15) {
					/*dispose();
					PantallaPrincipalEsp pe = new PantallaPrincipalEsp();
					int numCols = table.getModel().getColumnCount();
					
					Object [] fila = new Object[numCols];
					
					fila[0] = "unal";
					fila[1] = "420";
					fila[2] = "mundo";
					pe.setVisible(true);*/
					
				}else {
					JOptionPane.showMessageDialog(null, "Error en alguno de los campos introduccidos", "ERROR", JOptionPane.ERROR_MESSAGE);
					textDescripcionEsp.setText("");
					textNombreEsp.setText("");
					textFechaEsp.setText("");
					textNombreEsp.requestFocus();
				}
			}
		});
		btnNewButton.setBounds(313, 184, 96, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Atr\u00E1s");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaPrincipal p = new PantallaPrincipal();
				p.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 200, 70, 22);
		contentPane.add(btnNewButton_1);


		
	}
}
