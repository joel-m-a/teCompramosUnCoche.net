package gui;

import domain.Coche;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes para la tabla
    private JTable tablaCoches;
    private DefaultTableModel modeloTabla;

    // Componentes del formulario
    private JTextField txtMarca;
    private JTextField txtCv;
    private JTextField txtColor;
    private JTextField txtAnio;

    // Botones de acción
    private JButton btnAnadir;
    private JButton btnEliminar;
    private JButton btnVolver;

    // Lista de coches
    private List<Coche> listaCoches;

    public VentanaAdmin(List<Coche> listaCoches) {
        this.listaCoches = (listaCoches != null) ? listaCoches : new ArrayList<>();

        // Configuración de la ventana
        setTitle("Panel de Administración");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Superior
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("PANEL DE ADMINISTRACIÓN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // 2. Panel Central (Tabla)
        String[] columnas = {"Marca", "CV", "Color", "Año"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaCoches = new JTable(modeloTabla);
        tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaCoches);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Vehículos"));
        add(scrollTabla, BorderLayout.CENTER);

        // Cargar datos en los campos al seleccionar una fila
        tablaCoches.getSelectionModel().addListSelectionListener(e -> cargarDatosDeTabla());

        // 3. Panel Izquierdo (Formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Coche"));

        txtMarca = new JTextField();
        txtCv = new JTextField();
        txtColor = new JTextField();
        txtAnio = new JTextField();

        panelFormulario.add(new JLabel("Marca:"));
        panelFormulario.add(txtMarca);
        panelFormulario.add(new JLabel("Potencia (CV):"));
        panelFormulario.add(txtCv);
        panelFormulario.add(new JLabel("Color:"));
        panelFormulario.add(txtColor);
        panelFormulario.add(new JLabel("Año:"));
        panelFormulario.add(txtAnio);

        add(panelFormulario, BorderLayout.WEST);

        // 4. Panel Inferior (Botones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        btnAnadir = new JButton("Añadir Coche");
        btnEliminar = new JButton("Eliminar Seleccionado");
        btnVolver = new JButton("Volver");

        btnAnadir.setBackground(new Color(46, 204, 113));
        btnAnadir.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);

        panelBotones.add(btnAnadir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // 5. Cargar datos iniciales
        actualizarTabla();

        // 6. Eventos
        btnAnadir.addActionListener(e -> anadirCoche());
        btnEliminar.addActionListener(e -> eliminarCoche());
        btnVolver.addActionListener(e -> dispose());
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Coche c : listaCoches) {
            Object[] fila = {
                c.marca,
                c.cv,
                c.color,
                c.anio
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosDeTabla() {
        int fila = tablaCoches.getSelectedRow();
        if (fila != -1) {
            txtMarca.setText(String.valueOf(modeloTabla.getValueAt(fila, 0)));
            txtCv.setText(String.valueOf(modeloTabla.getValueAt(fila, 1)));
            txtColor.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
            txtAnio.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
        }
    }

    private void anadirCoche() {
        String marca = txtMarca.getText();
        int cv = Integer.parseInt(txtCv.getText());
        String color = txtColor.getText();
        int anio = Integer.parseInt(txtAnio.getText());

        Coche nuevoCoche = new Coche(marca, cv, color, anio);
        listaCoches.add(nuevoCoche);

        actualizarTabla();
    }

    private void eliminarCoche() {
        int fila = tablaCoches.getSelectedRow();
        if (fila >= 0) {
            listaCoches.remove(fila);
            actualizarTabla();
        }
    }
}
