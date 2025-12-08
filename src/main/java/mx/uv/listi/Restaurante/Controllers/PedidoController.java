package mx.uv.listi.Restaurante.Controllers;

import mx.uv.listi.Restaurante.DTOs.PedidoRequest;
import mx.uv.listi.Restaurante.Models.Pedido;
import mx.uv.listi.Restaurante.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequest request) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(request);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/usuario/{id}")
    public List<Pedido> listarPedidosUsuario(@PathVariable Long id) {
        return pedidoService.obtenerPedidosPorUsuario(id);
    }
}