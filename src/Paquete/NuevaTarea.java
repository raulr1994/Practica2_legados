package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevaTarea extends JFrame {

	private JPanel contentPane;
	private JTextField textTipo;
	private JTextField textDescripcion;
	private JTextField textFecha;
	private JTextField textNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaTarea frame = new NuevaTarea();
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
	public NuevaTarea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR NUEVA TAREA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(124, 0, 166, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Tarea (General/Espec\u00EDfica):");
		lblNewLabel_1.setBounds(10, 54, 181, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre (15 car\u00E1cteres m\u00E1x):");
		lblNewLabel_2.setBounds(10, 95, 149, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Descripci\u00F3n (25 car\u00E1cteres max): ");
		lblNewLabel_3.setBounds(10, 135, 166, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha (ddmm):");
		lblNewLabel_4.setBounds(10, 175, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		textTipo = new JTextField();
		textTipo.setBounds(226, 54, 181, 20);
		contentPane.add(textTipo);
		textTipo.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(226, 132, 181, 20);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(226, 172, 181, 20);
		contentPane.add(textFecha);
		textFecha.setColumns(10);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((textTipo.getText().equals("Específica") || textTipo.getText().equals("Especifica") 
						|| textTipo.getText().equals("especifica") || textTipo.getText().equals("General")
						|| textTipo.getText().equals("general")) && (!textNombre.getText().equals("")) 
						&& (!textDescripcion.getText().equals("")) && (!textFecha.getText().equals(""))) {
					dispose();
					JOptionPane.showMessageDialog(null,  "Nueva Tarea a añadir", "NewTask", JOptionPane.INFORMATION_MESSAGE);
					
					PantallaPrincipal p = new PantallaPrincipal();
					p.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Alguno de los campos introducidos incorrectamente", "ERROR", JOptionPane.ERROR_MESSAGE);
					textTipo.setText("");
					textNombre.setText("");
					textDescripcion.setText("");
					textFecha.setText("");
					textTipo.requestFocus();
				}
			}
		});
		btnSiguiente.setBounds(318, 227, 89, 23);
		contentPane.add(btnSiguiente);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(226, 92, 181, 20);
		contentPane.add(textNombre);
	}
}
