package mx.uv.listi.Restaurante.DTOs;

public class UsuarioResponse {
    public Long id;
    public String nombre;
    public String email;

    public UsuarioResponse(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}
