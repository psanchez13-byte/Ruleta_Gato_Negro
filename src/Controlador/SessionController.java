package Controlador;

import Modelo.GestorUsuariosCSV;
import Modelo.Usuario;

import java.io.IOException;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;

    public void registrarUsuario(String username, String password, String nombre) {
        if (username == null || username.isBlank() || password == null || password.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        // Evitar usernames repetidos leyendo el CSV
        List<Usuario> usuariosRegistrados = GestorUsuariosCSV.leerUsuarios();
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsername().equals(username)) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso. Elija otro.");
            }
        }

        Usuario nuevoUsuario = new Usuario(username, password, nombre);
        try {
            GestorUsuariosCSV.guardarUsuario(nuevoUsuario);
        } catch (IOException e) {
            throw new RuntimeException("Error fatal al guardar el archivo: " + e.getMessage());
        }
    }

    public boolean iniciarSesion(String username, String password) {
        List<Usuario> usuariosRegistrados = GestorUsuariosCSV.leerUsuarios();
        for (Usuario u : usuariosRegistrados) {
            if (u.validarCredenciales(username, password)) {
                this.usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}