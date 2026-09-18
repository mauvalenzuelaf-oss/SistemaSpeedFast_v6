package vista;

import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana que permite visualizar los pedidos registrados.
 */
public class VentanaListaPedidos extends JFrame {

    // Componentes
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    // Lista compartida
    private ArrayList<Pedido> pedidos;

    /**
     * Construye la ventana de listado.
     *
     * @param pedidos lista compartida de pedidos
     */
    public VentanaListaPedidos(ArrayList<Pedido> pedidos) {

        this.pedidos = pedidos;

        setTitle("SpeedFast - Lista de Pedidos");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Columnas de la tabla
        String[] columnas = {
                "ID",
                "Dirección",
                "Tipo",
                "Estado",
                "Repartidor"
        };

        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                );

        tablaPedidos =
                new JTable(modeloTabla);

        JScrollPane scrollPane =
                new JScrollPane(tablaPedidos);

        btnActualizar =
                new JButton("Actualizar");

        JPanel panelBoton =
                new JPanel();

        panelBoton.add(btnActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Cargar datos iniciales
        cargarPedidos();

        // Evento actualizar
        btnActualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cargarPedidos();
                    }
                }
        );
    }

    /**
     * Actualiza la tabla con los pedidos registrados.
     */
    private void cargarPedidos() {

        // Limpiar tabla
        modeloTabla.setRowCount(0);

        for (Pedido pedido : pedidos) {

            Object[] fila = {
                    pedido.getId(),
                    pedido.getDireccionEntrega(),
                    pedido.getTipo(),
                    pedido.getEstado(),
                    pedido.getRepartidor()
            };

            modeloTabla.addRow(fila);
        }
    }
}
