package Paquete;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comunicacion.ComunicacionMusicSP;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

//import comunicacion.ComunicacionMusicSP;
//import comunicacion.PantallaPrincipal;
//import comunicacion.gestorTareas;
//import comunicacion.wc3270;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField jpass;
	
	
	static wc3270 comunicacionWS = null;//wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = null; //ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = null; // gestorTareas.getInstancia(comunicacionWS);

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
	
	private static boolean logearse (String nombreUsuario, String nombrePasswd) throws Exception {
    	comunicacionSP.conectar();
    	Thread.sleep(100);
        //final String usuario = "grupo_09";
        //final String contraseña = "secreto6";
        System.out.println("El nombre del usuario es " +nombreUsuario);
        System.out.println("La contraseña del usuario es " +nombrePasswd);
        /*if(nombreUsuario.equals(usuario)) {
        	System.out.println("El nombre del usuario coincide");
        }
        else {
        	System.out.println("El nombre del usuario no coincide");
        }
        if(nombrePasswd.equals(contraseña)) {
        	System.out.println("La contraseña del usuario coincide");
        }
        else {
        	System.out.println("La contraseña del usuario no coincide");
        }*/
        System.out.println("intentando login");
		Boolean exito = comunicacionSP.login(nombreUsuario, nombrePasswd);
        System.out.println("El nombre de usuario es: " + nombreUsuario);
        System.out.println("login realizado");
        if (exito) {
            System.out.println("Exito");
            return true;
        } else {
            System.out.println("Fracaso");
            return false;
        }
    }

	/**
	 * Create the frame.
	 */
	public Inicio() {
		addWindowListener(new WindowAdapter(){
	           @Override
	           public void windowClosing(WindowEvent et) {
	        	   	dispose();
	           };
	     });
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO SESION");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(130, 11, 163, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario (grupo_09):");
		lblNewLabel_1.setBounds(10, 62, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contrase\u00F1a (secreto6):");
		lblNewLabel_1_1.setBounds(10, 95, 114, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(157, 62, 263, 25);
		contentPane.add(txtUsuario);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.setBackground(Color.lightGray);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] passwd = jpass.getPassword();
				String passwdfinal = new String(passwd);
				comunicacionWS = wc3270.getInstancia();
			    comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
			    appLegada = gestorTareas.getInstancia(comunicacionWS);
				
				try {
					//comunicacionWS.assertConnected();
		            if(logearse(txtUsuario.getText(), passwdfinal)){
		            	System.out.println("Se ha podido loguear");
		            	
		            	try {
							//PantallaPrincipal frame = new PantallaPrincipal();
							//frame.setVisible(true);
		            		dispose();
							JOptionPane.showMessageDialog(null,  "Correcto inicio de sesion", "Logueo", JOptionPane.INFORMATION_MESSAGE);
							MenuPrincipal p = new MenuPrincipal();
							p.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		            }
		            else {
		            	System.out.println("No se ha podido loguear");
		            	JOptionPane.showMessageDialog(null, "Nombre Usuario o Contrase�a incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtUsuario.setText("");
						jpass.setText("");
						txtUsuario.requestFocus();
		            }
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(92, 130, 119, 36);
		contentPane.add(btnNewButton);
		
		jpass = new JPasswordField();
		jpass.setBounds(157, 95, 263, 25);
		contentPane.add(jpass);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.lightGray);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(232, 130, 119, 36);
		contentPane.add(btnSalir);
		
		
	}
}
