package modelo;

/**
 * Representa un pedido registrado en el sistema SpeedFast.
 */
public class Pedido {

    // Atributos
    private int id;
    private String direccionEntrega;
    private String tipo;
    private EstadoPedido estado;
    private String repartidor;

    /**
     * Construye un pedido con sus datos principales.
     *
     * @param id identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param tipo tipo de pedido
     */
    public Pedido(int id, String direccionEntrega, String tipo) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE;
        this.repartidor = "Sin asignar";
    }

    // Representación de datos
    @Override
    public String toString() {
        return "Pedido #" + id
                + " - Dirección: " + direccionEntrega
                + " - Tipo: " + tipo
                + " - Estado: " + estado
                + " - Repartidor: " + repartidor;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }
}