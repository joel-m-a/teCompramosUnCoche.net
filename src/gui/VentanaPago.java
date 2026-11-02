package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaPago extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboMetodo;
	private JTextField txtTitular;
	private JTextField txtTarjeta;
	private JTextField txtCvv;
	private JButton btnPagar;
	private JButton btnCancelar;

	public VentanaPago() {
		setTitle("Pago");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(5, 2, 10, 10));

		// metodo de pago
		add(new JLabel("Método de pago:"));
		String metodos[] = { "Tarjeta", "Transferencia" };
		comboMetodo = new JComboBox<String>(metodos);
		add(comboMetodo);

		// titular
		add(new JLabel("Titular:"));
		txtTitular = new JTextField();
		add(txtTitular);

		// numero de tarjeta
		add(new JLabel("Nº de tarjeta:"));
		txtTarjeta = new JTextField();
		add(txtTarjeta);

		// cvv
		add(new JLabel("CVV:"));
		txtCvv = new JTextField();
		add(txtCvv);

		// botones
		btnCancelar = new JButton("Cancelar");
		btnPagar = new JButton("Pagar");
		add(btnCancelar);
		add(btnPagar);

		// eventos
		btnCancelar.addActionListener(e -> dispose());

		btnPagar.addActionListener(e -> {
			if (txtTitular.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Escribe el titular");
			} else if (comboMetodo.getSelectedItem().equals("Tarjeta") && txtTarjeta.getText().length() != 16) {
				JOptionPane.showMessageDialog(this, "La tarjeta tiene que tener 16 números");
			} else if (comboMetodo.getSelectedItem().equals("Tarjeta") && txtCvv.getText().length() != 3) {
				JOptionPane.showMessageDialog(this, "El CVV tiene que tener 3 números");
			} else {
				JOptionPane.showMessageDialog(this, "Pago realizado");
				dispose();
			}
		});
	}

	// main temporal
	public static void main(String[] args) {
		VentanaPago v = new VentanaPago();
		v.setVisible(true);
	}

}