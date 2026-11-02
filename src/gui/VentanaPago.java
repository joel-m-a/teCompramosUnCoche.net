package gui;

import domain.Coche;

import javax.swing.*;
import java.awt.*;

public class VentanaPago extends JFrame {

    private static final long serialVersionUID = 1L;

    // Datos de la compra
    private Coche coche;
    private double importe;

    // Componentes del formulario
    private JComboBox<String> comboMetodo;
    private JTextField txtTitular;
    private JTextField txtNumeroTarjeta;
    private JTextField txtCaducidad;
    private JPasswordField txtCvv;

    // Botones
    private JButton btnPagar;
    private JButton btnCancelar;

    // Constructor sin datos (para pruebas)
    public VentanaPago() {
        this(null, 0);
    }

    public VentanaPago(Coche coche, double importe) {
        this.coche = coche;
        this.importe = importe;

        // Configuración de la ventana
        setTitle("Pago");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Superior (título)
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("PAGO DEL VEHÍCULO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // 2. Panel Central (resumen + formulario)
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));

        // Resumen de la compra
        JPanel panelResumen = new JPanel(new GridLayout(2, 1, 5, 5));
        panelResumen.setBorder(BorderFactory.createTitledBorder("Resumen de la compra"));
        String textoCoche = (coche != null)
                ? coche.marca + " - " + coche.cv + " CV - " + coche.color + " (" + coche.anio + ")"
                : "Ningún vehículo seleccionado";
        panelResumen.add(new JLabel("Vehículo: " + textoCoche));
        JLabel lblImporte = new JLabel(String.format("Importe total: %,.2f €", importe));
        lblImporte.setFont(new Font("Arial", Font.BOLD, 14));
        panelResumen.add(lblImporte);
        panelCentro.add(panelResumen, BorderLayout.NORTH);

        // Formulario de pago
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de pago"));

        String[] metodos = {"Tarjeta de crédito", "Tarjeta de débito", "Transferencia bancaria"};
        comboMetodo = new JComboBox<String>(metodos);
        txtTitular = new JTextField();
        txtNumeroTarjeta = new JTextField();
        txtCaducidad = new JTextField("MM/AA");
        txtCvv = new JPasswordField();

        panelFormulario.add(new JLabel("Método de pago:"));
        panelFormulario.add(comboMetodo);
        panelFormulario.add(new JLabel("Titular:"));
        panelFormulario.add(txtTitular);
        panelFormulario.add(new JLabel("Nº de tarjeta (16 dígitos):"));
        panelFormulario.add(txtNumeroTarjeta);
        panelFormulario.add(new JLabel("Caducidad:"));
        panelFormulario.add(txtCaducidad);
        panelFormulario.add(new JLabel("CVV (3 dígitos):"));
        panelFormulario.add(txtCvv);

        panelCentro.add(panelFormulario, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        // 3. Panel Inferior (botones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        btnPagar = new JButton("Pagar");
        btnCancelar = new JButton("Cancelar");

        btnPagar.setBackground(new Color(46, 204, 113));
        btnPagar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);

        panelBotones.add(btnPagar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // 4. Eventos
        comboMetodo.addActionListener(e -> actualizarCamposSegunMetodo());
        btnPagar.addActionListener(e -> realizarPago());
        btnCancelar.addActionListener(e -> dispose());
    }

    // Si es transferencia, los datos de tarjeta no hacen falta
    private void actualizarCamposSegunMetodo() {
        boolean esTarjeta = comboMetodo.getSelectedIndex() != 2;
        txtNumeroTarjeta.setEnabled(esTarjeta);
        txtCaducidad.setEnabled(esTarjeta);
        txtCvv.setEnabled(esTarjeta);
    }

    private void realizarPago() {
        String titular = txtTitular.getText().trim();
        if (titular.isEmpty()) {
            mostrarError("Introduce el nombre del titular.");
            return;
        }

        boolean esTarjeta = comboMetodo.getSelectedIndex() != 2;
        if (esTarjeta) {
            String numero = txtNumeroTarjeta.getText().replace(" ", "");
            if (!numero.matches("\\d{16}")) {
                mostrarError("El número de tarjeta debe tener 16 dígitos.");
                return;
            }
            if (!txtCaducidad.getText().matches("(0[1-9]|1[0-2])/\\d{2}")) {
                mostrarError("La caducidad debe tener el formato MM/AA (por ejemplo 08/27).");
                return;
            }
            String cvv = new String(txtCvv.getPassword());
            if (!cvv.matches("\\d{3}")) {
                mostrarError("El CVV debe tener 3 dígitos.");
                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                String.format("Pago de %,.2f € realizado correctamente.\n¡Gracias por tu compra, %s!", importe, titular),
                "Pago completado",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error en el pago", JOptionPane.ERROR_MESSAGE);
    }

    // main temporal
    public static void main(String[] args) {
        Coche cocheDePrueba = new Coche("Ferrari", 700, "Rojo", 2023);
        VentanaPago v = new VentanaPago(cocheDePrueba, 250000);
        v.setVisible(true);
    }
}