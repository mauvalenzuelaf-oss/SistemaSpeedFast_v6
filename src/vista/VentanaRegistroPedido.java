package vista;

import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana que permite registrar nuevos pedidos.
 */
public class VentanaRegistroPedido extends JFrame {

    // Componentes
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JButton btnGuardar;

    // Lista compartida
    private ArrayList<Pedido> pedidos;

    /**
     * Construye la ventana de registro.
     *
     * @param pedidos lista compartida de pedidos
     */
    public VentanaRegistroPedido(ArrayList<Pedido> pedidos) {

        this.pedidos = pedidos;

        setTitle("SpeedFast - Registrar Pedido");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel del formulario
        JPanel panelFormulario = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // Componentes
        JLabel lblId = new JLabel("ID:");
        JLabel lblDireccion = new JLabel("Dirección:");
        JLabel lblTipo = new JLabel("Tipo:");

        txtId = new JTextField();
        txtDireccion = new JTextField();

        String[] tipos = {
                "Comida",
                "Encomienda",
                "Express"
        };

        cmbTipo = new JComboBox<>(tipos);

        btnGuardar = new JButton("Guardar");

        // Agregar componentes
        panelFormulario.add(lblId);
        panelFormulario.add(txtId);

        panelFormulario.add(lblDireccion);
        panelFormulario.add(txtDireccion);

        panelFormulario.add(lblTipo);
        panelFormulario.add(cmbTipo);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnGuardar);

        add(panelFormulario);

        // Evento Guardar
        btnGuardar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        guardarPedido();
                    }
                }
        );
    }

    /**
     * Valida los datos y guarda un nuevo pedido.
     */
    private void guardarPedido() {

        String idTexto = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (idTexto.isEmpty() || direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int id;

        try {

            id = Integer.parseInt(idTexto);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (id <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Verificar que el ID no esté repetido
        for (Pedido pedido : pedidos) {

            if (pedido.getId() == id) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un pedido con ese ID.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        String tipo =
                cmbTipo.getSelectedItem().toString();

        Pedido nuevoPedido =
                new Pedido(
                        id,
                        direccion,
                        tipo
                );

        pedidos.add(nuevoPedido);

        JOptionPane.showMessageDialog(
                this,
                "Pedido registrado correctamente."
        );

        // Limpiar formulario
        txtId.setText("");
        txtDireccion.setText("");
        cmbTipo.setSelectedIndex(0);
    }
}
