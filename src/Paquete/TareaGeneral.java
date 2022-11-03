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

public class TareaGeneral extends JFrame {

	private JPanel contentPane;
	private JTextField textDescripcionGen;
	private JTextField textFechaGen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TareaGeneral frame = new TareaGeneral();
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
	public TareaGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR TAREA GENERAL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 11, 193, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Descripci\u00F3n (25 car\u00E1cteres max): ");
		lblNewLabel_3.setBounds(10, 79, 166, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha (ddmm):");
		lblNewLabel_4.setBounds(10, 119, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		textDescripcionGen = new JTextField();
		textDescripcionGen.setBounds(226, 77, 181, 20);
		contentPane.add(textDescripcionGen);
		textDescripcionGen.setColumns(10);
		
		textFechaGen = new JTextField();
		textFechaGen.setBounds(226, 116, 181, 20);
		contentPane.add(textFechaGen);
		textFechaGen.setColumns(10);
		
		JButton btnSiguienteGen = new JButton("Siguiente");
		btnSiguienteGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripGeneral = textDescripcionGen.getText();
				if(descripGeneral.length()<=25) {
					
				}else {
					JOptionPane.showMessageDialog(null, "Error en alguno de los campos introduccidos", "ERROR", JOptionPane.ERROR_MESSAGE);
					textDescripcionGen.setText("");
					textFechaGen.setText("");
					textDescripcionGen.requestFocus();
				}
				
			}
		});
		btnSiguienteGen.setBounds(311, 147, 96, 39);
		contentPane.add(btnSiguienteGen);
		
		JButton btnNewButton = new JButton("Atr\u00E1s");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaPrincipal p = new PantallaPrincipal();
				p.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 166, 69, 23);
		contentPane.add(btnNewButton);
	}
}
