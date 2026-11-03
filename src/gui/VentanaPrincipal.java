package gui;

import java.awt.*;
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
		setSize(750, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(new Color(45, 52, 54));

		JLabel lblTitulo = new JLabel("  CATÁLOGO DE VEHÍCULOS");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setForeground(Color.WHITE);

		panelSuperior.add(lblTitulo, BorderLayout.WEST);
		add(panelSuperior, BorderLayout.NORTH);

		String[] columnas = { "Marca", "Potencia (CV)", "Color", "Año" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modeloTabla.addRow(new Object[] { "Ferrari", 670, "Rojo", 2021 });
		modeloTabla.addRow(new Object[] { "Lamborghini", 740, "Amarillo", 2022 });
		modeloTabla.addRow(new Object[] { "Porsche", 520, "Negro", 2020 });
		modeloTabla.addRow(new Object[] { "Aston Martin", 510, "Verde", 2023 });

		tablaCoches = new JTable(modeloTabla);
		tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(tablaCoches);
		scroll.setBorder(BorderFactory.createTitledBorder("Coches Disponibles"));
		add(scroll, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		btnFiltros = new JButton("Filtrar");
		btnComprar = new JButton("Comprar Coche");
		btnAdmin = new JButton("Panel Admin");
		btnCerrarSesion = new JButton("Cerrar Sesión");

		btnAdmin.setBackground(new Color(45, 52, 54));
		btnAdmin.setForeground(Color.WHITE);
		btnComprar.setBackground(new Color(46, 204, 113));
		btnComprar.setForeground(Color.WHITE);

		panelBotones.add(btnFiltros);
		panelBotones.add(btnComprar);
		panelBotones.add(btnAdmin);
		panelBotones.add(btnCerrarSesion);

		add(panelBotones, BorderLayout.SOUTH);

		btnFiltros.addActionListener(e -> {
			VentanaFiltros vf = new VentanaFiltros();
			vf.setVisible(true);
		});

		btnComprar.addActionListener(e -> {
			VentanaPago vp = new VentanaPago();
			vp.setVisible(true);
		});

		btnAdmin.addActionListener(e -> {
			VentanaAdmin va = new VentanaAdmin(null);
			va.setVisible(true);
		});

		btnCerrarSesion.addActionListener(e -> {
			new VentanaLogin().setVisible(true);
			dispose();
		});
	}
}