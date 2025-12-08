package mx.uv.listi.Restaurante.DTOs;

import java.util.List;

public class PedidoRequest {
    public Long usuarioId; // ID del cliente que hace el pedido
    public List<DetalleRequest> productos; // Lista de platos y cantidades

    // Clase interna estática para los detalles
    public static class DetalleRequest {
        public Long platoId;
        public Integer cantidad;
    }
}