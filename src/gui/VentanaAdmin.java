package gui;

import domain.Coche;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;

   private JTable tablaCoches;
   private DefaultTableModel modeloTabla;
   private JTextField marca;
   private JTextField cv;
   private JTextField color;
   private JTextField anio;
   private JButton anadir;
   private JButton eliminar;
   private JButton modificar;
   private JButton volver;

   
    private List<Coche> listaCoches;

    public VentanaAdmin(List<Coche> listaCoches) {
    	if (listaCoches != null) {
            this.listaCoches = listaCoches;
        } else {
            this.listaCoches = new ArrayList<Coche>();
        }

        setTitle("Panel de Administracion");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

     
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2));

        marca = new JTextField();
        cv = new JTextField();
        color = new JTextField();
        anio = new JTextField();

        panelFormulario.add(new JLabel("Marca:"));
        panelFormulario.add(marca);
        panelFormulario.add(new JLabel("CV:"));
        panelFormulario.add(cv);
        panelFormulario.add(new JLabel("Color:"));
        panelFormulario.add(color);
        panelFormulario.add(new JLabel("Año:"));
        panelFormulario.add(anio);

        add(panelFormulario, BorderLayout.WEST);

        
        String[] columnas = {"Marca", "CV", "Color", "Año"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCoches = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaCoches);
        add(scrollTabla, BorderLayout.CENTER);

        
        tablaCoches.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = tablaCoches.getSelectedRow();
                if (fila != -1) {
                    marca.setText(modeloTabla.getValueAt(fila, 0).toString());
                    cv.setText(modeloTabla.getValueAt(fila, 1).toString());
                    color.setText(modeloTabla.getValueAt(fila, 2).toString());
                    anio.setText(modeloTabla.getValueAt(fila, 3).toString());
                }
            }
        });

    
        JPanel panelBotones = new JPanel();

        anadir = new JButton("Añadir");
        modificar = new JButton("Modificar");
        eliminar = new JButton("Eliminar");
        volver = new JButton("Volver");

        panelBotones.add(anadir);
        panelBotones.add(modificar);
        panelBotones.add(eliminar);
        panelBotones.add(volver);

        add(panelBotones, BorderLayout.SOUTH);

       actualizarTabla();
       
       anadir.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String m = marca.getText();
               int c = Integer.parseInt(cv.getText());
               String co = color.getText();
               int a = Integer.parseInt(anio.getText());

               Coche nuevoCoche = new Coche(m, c, co, a);
               listaCoches.add(nuevoCoche);

               actualizarTabla();
           }
       });
       
       eliminar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int fila = tablaCoches.getSelectedRow();
               if (fila >= 0) {
                   listaCoches.remove(fila);
                   actualizarTabla();
               }
           }
       });
       
       volver.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });
    
    }
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < listaCoches.size(); i++) {
            Coche c = listaCoches.get(i);
            Object[] fila = new Object[4];
            fila[0] = c.marca;
            fila[1] = c.cv;
            fila[2] = c.color;
            fila[3] = c.anio;
            modeloTabla.addRow(fila);
        }
    }
    
}
