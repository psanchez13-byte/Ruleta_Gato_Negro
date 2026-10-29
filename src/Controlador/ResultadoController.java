package Controlador;

import Modelo.Resultado;
import Modelo.Usuario;
import java.util.List;

public class ResultadoController {

    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public String obtenerHistorialUsuario() {
        if (!session.hayUsuario()) {
            return "No hay ninguna sesión activa.";
        }

        Usuario usuario = session.getUsuarioActual();
        List<Resultado> historial = usuario.getHistorial();

        if (historial.isEmpty()) {
            return "Aún no hay jugadas registradas para el jugador: " + usuario.getUsername();
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("--- HISTORIAL DE JUGADAS: ").append(usuario.getUsername().toUpperCase()).append(" ---\n\n");

        for (int i = 0; i < historial.size(); i++) {
            Resultado r = historial.get(i);
            reporte.append("Ronda ").append(i + 1).append(":\n");
            reporte.append(" - Tipo de apuesta: ").append(r.getApuesta().getEtiqueta()).append("\n");
            reporte.append(" - Número ganador: ").append(r.getNumeroObtenido()).append("\n");
            reporte.append(" - Monto apostado: $").append(r.getMontoApostado()).append("\n");
            reporte.append(" - Resultado: ").append(r.isAcierto() ? "¡GANÓ!" : "Perdió").append("\n");
            reporte.append("---------------------------\n");
        }

        return reporte.toString();
    }
}
