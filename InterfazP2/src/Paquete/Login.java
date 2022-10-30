package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField jpassPasswd;
	private JButton btnInicioSesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Usuario (grupo_09):");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(32, 57, 202, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a (secreto6):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 93, 170, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("INICIO SESI\u00D3N");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(132, 11, 177, 35);
		contentPane.add(lblNewLabel_2);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(263, 61, 113, 21);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		btnInicioSesion = new JButton("Iniciar sesión");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwd = jpassPasswd.getPassword();
				String passwdfinal = new String(passwd);
				if(textUsuario.getText().equals("grupo_09")&& (passwdfinal.equals("secreto6"))) {
					dispose();
					JOptionPane.showMessageDialog(null,  "Correcto inicio de sesion", "Logueo", JOptionPane.INFORMATION_MESSAGE);
					PantallaPrincipal p = new PantallaPrincipal();
					p.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Nombre Usuario o Contraseña incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
					textUsuario.setText("");
					jpassPasswd.setText("");
					textUsuario.requestFocus();
				}
					
			}
		});

		
		btnInicioSesion.setBounds(153, 129, 137, 43);
		contentPane.add(btnInicioSesion);
		
		jpassPasswd = new JPasswordField();
		jpassPasswd.setBounds(263, 93, 113, 20);
		contentPane.add(jpassPasswd);
	}

	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
