package Paquete;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.List;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Tareas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnEspecifica = new JButton("Especifica");
		btnEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaPrincipalEsp pe = new PantallaPrincipalEsp();
				pe.setVisible(true);
			}
		});
		btnEspecifica.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnGeneral = new JButton("General");
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaPrincipalGen pg = new PantallaPrincipalGen();
				pg.setVisible(true);
			}
		});
		btnGeneral.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnNuevaTarea = new JButton("Nueva Tarea");
		btnNuevaTarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNuevaTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
				dispose();
				Seleccion s = new Seleccion();
				s.setVisible(true);
			}
		});
		
		JList list = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login l = new Login();
				l.setVisible(true);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEspecifica)
									.addGap(9)
									.addComponent(btnGeneral, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(137)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNuevaTarea, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNuevaTarea, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEspecifica, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGeneral, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(25)
									.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Descripci\u00F3n", "Fecha"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
