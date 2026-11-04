package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnCancelar;

	public VentanaLogin() {
		setTitle("Iniciar Sesión");
		setSize(380, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 2, 10, 10));

		add(new JLabel("  Usuario:"));
		txtUsuario = new JTextField();
		add(txtUsuario);

		add(new JLabel("  Contraseña:"));
		txtPassword = new JPasswordField();
		add(txtPassword);

		btnCancelar = new JButton("Cancelar");
		btnLogin = new JButton("Iniciar Sesión");
		add(btnCancelar);
		add(btnLogin);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		VentanaLogin v = new VentanaLogin();
		v.setVisible(true);
	}
}