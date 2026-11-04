package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable tablaCoches;
	private DefaultTableModel modeloTabla;
	private JButton btnFiltros;
	private JButton btnComprar;
	private JButton btnAdmin;
	private JButton btnCerrarSesion;

	public VentanaPrincipal() {
		setTitle("Catálogo Principal");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		String[] columnas = { "Marca", "Potencia (CV)", "Color", "Año" };
		modeloTabla = new DefaultTableModel(columnas, 0);

		modeloTabla.addRow(new Object[] { "Ferrari", 670, "Rojo", 2021 });
		modeloTabla.addRow(new Object[] { "Lamborghini", 740, "Amarillo", 2022 });
		modeloTabla.addRow(new Object[] { "Porsche", 520, "Negro", 2020 });
		modeloTabla.addRow(new Object[] { "Aston Martin", 510, "Verde", 2023 });

		tablaCoches = new JTable(modeloTabla);
		JScrollPane scroll = new JScrollPane(tablaCoches);
		add(scroll, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel(new FlowLayout());

		btnFiltros = new JButton("Filtrar");
		btnComprar = new JButton("Comprar Coche");
		btnAdmin = new JButton("Panel Admin");
		btnCerrarSesion = new JButton("Cerrar Sesión");

		panelBotones.add(btnFiltros);
		panelBotones.add(btnComprar);
		panelBotones.add(btnAdmin);
		panelBotones.add(btnCerrarSesion);

		add(panelBotones, BorderLayout.SOUTH);

		btnFiltros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaFiltros vf = new VentanaFiltros();
				vf.setVisible(true);
			}
		});

		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPago vp = new VentanaPago();
				vp.setVisible(true);
			}
		});

		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin va = new VentanaAdmin(null);
				va.setVisible(true);
			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
				dispose();
			}
		});
	}
}