package vista;

import modelo.EstadoPedido;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana principal del sistema SpeedFast.
 */
public class VentanaPrincipal extends JFrame {

    // Lista compartida de pedidos
    private ArrayList<Pedido> pedidos;

    // Botones
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnIniciarEntregas;

    // Índice para asignar repartidores por turno
    private int indiceRepartidor;

    /**
     * Construye la ventana principal.
     */
    public VentanaPrincipal() {

        pedidos = new ArrayList<>();
        indiceRepartidor = 0;

        setTitle("SpeedFast - Gestión de Pedidos");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel(
                "Sistema SpeedFast",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        // Botones
        btnRegistrar =
                new JButton("Registrar pedido");

        btnListar =
                new JButton("Listar pedidos");

        btnIniciarEntregas =
                new JButton(
                        "Asignar repartidor / Iniciar entrega"
                );

        // Panel de botones
        JPanel panelBotones = new JPanel(
                new GridLayout(
                        3,
                        1,
                        10,
                        10
                )
        );

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnIniciarEntregas);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        // Botón registrar pedido
        btnRegistrar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        VentanaRegistroPedido ventana =
                                new VentanaRegistroPedido(pedidos);

                        ventana.setVisible(true);
                    }
                }
        );

        // Botón listar pedidos
        btnListar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        VentanaListaPedidos ventana =
                                new VentanaListaPedidos(pedidos);

                        ventana.setVisible(true);
                    }
                }
        );

        // Botón iniciar entregas
        btnIniciarEntregas.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iniciarEntregas();
                    }
                }
        );
    }

    /**
     * Asigna repartidores e inicia los pedidos pendientes.
     */
    private void iniciarEntregas() {

        if (pedidos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen pedidos registrados.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        String[] repartidores = {
                "Juan",
                "Camila",
                "Pedro"
        };

        int pedidosIniciados = 0;

        for (Pedido pedido : pedidos) {

            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {

                pedido.setRepartidor(
                        repartidores[indiceRepartidor]
                );

                pedido.setEstado(
                        EstadoPedido.EN_REPARTO
                );

                pedidosIniciados++;

                // Pasar al siguiente repartidor
                indiceRepartidor++;

                // Volver a Juan después de Pedro
                if (indiceRepartidor == repartidores.length) {
                    indiceRepartidor = 0;
                }
            }
        }

        if (pedidosIniciados > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidores asignados.\n"
                            + "Entregas iniciadas correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay pedidos pendientes."
            );
        }
    }
}