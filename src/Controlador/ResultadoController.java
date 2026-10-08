package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;

public class ResultadoController {

    /**
     * Toma el modelo de la ruleta y transforma el arreglo de objetos
     * en un texto formateado y listo para mostrar en pantalla.
     */
    public String obtenerHistorialFormateado(Ruleta modelo) {
        if (modelo.getHistorialSize() == 0) {
            return "Aún no hay jugadas registradas en esta sesión.";
        }

        // Usamos StringBuilder porque es más eficiente para concatenar mucho texto
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- DETALLE DE JUGADAS ---\n\n");

        Resultado[] resultados = modelo.getHistorialResultados();

        for (int i = 0; i < modelo.getHistorialSize(); i++) {
            Resultado r = resultados[i];
            reporte.append("Ronda ").append(i + 1).append(":\n");
            reporte.append(" - Número ganador: ").append(r.getNumeroObtenido()).append("\n");
            reporte.append(" - Monto apostado: $").append(r.getMontoApostado()).append("\n");
            reporte.append(" - Resultado: ").append(r.isAcierto() ? "¡GANÓ!" : "Perdió").append("\n");
            reporte.append("---------------------------\n");
        }

        return reporte.toString();
    }
}
