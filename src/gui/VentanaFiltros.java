package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaFiltros extends JFrame{
	private JComboBox<String> comboMarca;
	private JTextField txtPrecioMax;
	private JTextField txtPotenciaMin;
	private JTextField txtAnioMin;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	
	public VentanaFiltros() {
		setTitle("Filtros de Búsqueda");
		setSize(400, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(6,2,10,10));
		
		//filtro marca
		add(new JLabel("Marca:"));
		String marcas[]= {"Todas", "Ferrari", "Lamborghini", "Porsche", "Aston Martin"};
		comboMarca= new JComboBox<String>(marcas);
		add(comboMarca);
		
		//filtro precio
		add(new JLabel("Precio Máxima:"));
		txtPrecioMax= new JTextField("500000");
		add(txtPrecioMax);
		
		//filtro potencia
		add(new JLabel("Potencia Minima:"));
		txtPotenciaMin= new JTextField("0");
		add(txtPotenciaMin);
		
		//filtro año
		add(new JLabel("Año desde:"));
		txtAnioMin= new JTextField("2020");
		add(txtAnioMin);
		
		//botones
		btnLimpiar= new JButton("Limpiar");
		btnBuscar= new JButton("Buscar");
		add(btnLimpiar);
		add(btnBuscar);
		
		//eventos
		
		
		//main temporal
		
	}
	
	public static void main(String[] args) {
		VentanaFiltros v= new VentanaFiltros();
		v.setVisible(true);
	}

}
