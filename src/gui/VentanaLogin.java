package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnCancelar;

	public VentanaLogin() {
		setTitle("Iniciar Sesión");
		setSize(450, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(45, 52, 54));
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setForeground(Color.WHITE);
		panelTitulo.add(lblTitulo);
		add(panelTitulo, BorderLayout.NORTH);

		JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		panelFormulario.add(new JLabel("Usuario:"));
		txtUsuario = new JTextField();
		panelFormulario.add(txtUsuario);

		panelFormulario.add(new JLabel("Contraseña:"));
		txtPassword = new JPasswordField();
		panelFormulario.add(txtPassword);

		add(panelFormulario, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

		btnCancelar = new JButton("Cancelar");
		btnLogin = new JButton("Iniciar Sesión");

		btnLogin.setBackground(new Color(46, 204, 113));
		btnLogin.setForeground(Color.WHITE);

		panelBotones.add(btnCancelar);
		panelBotones.add(btnLogin);

		add(panelBotones, BorderLayout.SOUTH);

		btnCancelar.addActionListener(e -> dispose());

		btnLogin.addActionListener(e -> {
			VentanaPrincipal principal = new VentanaPrincipal();
			principal.setVisible(true);
			dispose();
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			VentanaLogin v = new VentanaLogin();
			v.setVisible(true);
		});
	}
}