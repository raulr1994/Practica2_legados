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
import java.util.ArrayList;
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

import comunicacion.ComunicacionMusicSP;
import comunicacion.Sincronizador;
import comunicacion.Tarea;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = gestorTareas.getInstancia(comunicacionWS);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					comunicacionWS.assertConnected();
		            logearse();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarTareasEspecificas() {
		ArrayList<Tarea> nuevoListadoTareas = appLegada.obtenerListadoTareasEspecificas();
		for(int i = 0; i < nuevoListadoTareas.size(); i++) {
            System.out.println(nuevoListadoTareas.get(i).getNumeroTarea() + " " + nuevoListadoTareas.get(i).getNombre() + " " + 
            		nuevoListadoTareas.get(i).getFecha() + " " + nuevoListadoTareas.get(i).getDescripcion());
        }
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
		
		JButton btnEspecifica = new JButton("Añadir Especifica");
		btnEspecifica.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					appLegada.crearTareaEspecifica("11","12","Tarea1","Prueba1$");
					Sincronizador.waitSyncro(9500);
					//Sincronizador.waitSyncro(1000);
					comunicacionWS.limpiarEntrada();
					Sincronizador.waitSyncro(9500);
					//Sincronizador.waitSyncro(1000);
					//appLegada.crearTareaEspecifica("12","13","Tarea2","Prueba de tarea 2");
					//comunicacionWS.limpiarEntrada();
					System.out.println("Tarea creada");
				} catch (Exception e1) {
		            e1.printStackTrace();
		        }
			}
		});
		
		JButton btnGeneral = new JButton("Ver especificas");
		btnGeneral.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					appLegada.cargarListadoTareasEspecificas();
					Sincronizador.waitSyncro(7500);
					comunicacionWS.limpiarEntrada();
					Sincronizador.waitSyncro(7500);
					mostrarTareasEspecificas();
				} catch (Exception e1) {
		            e1.printStackTrace();
		        }
			}
		});
		
		JButton btnNuevaTarea = new JButton("Nueva Tarea");
		btnNuevaTarea.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevaTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
				dispose();
				NuevaTarea n = new NuevaTarea();
				n.setVisible(true);
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				comunicacionSP.finalizarPrograma();
				JOptionPane.showMessageDialog(null, "Sesi�n Cerrada", "Exit", JOptionPane.ERROR_MESSAGE);
				Login l = new Login();
				l.setVisible(true);
			}
		});
		
		JList list = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEspecifica)
							.addGap(9)
							.addComponent(btnGeneral, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(btnNuevaTarea, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(btnEspecifica, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(btnGeneral, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNuevaTarea, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
					.addGap(16))
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
	
	private static void logearse() throws Exception {
    	comunicacionSP.conectar();
    	Thread.sleep(100);
        final String usuario = "grupo_09";
        final String contraseña = "secreto6";
        System.out.println("intentando login");
        Boolean exito = comunicacionSP.login(usuario, contraseña);
        System.out.println("login realizado");
        if (exito) {
            System.out.println("Exito");
        } else {
            System.out.println("Fracaso");
        }
    }
}
