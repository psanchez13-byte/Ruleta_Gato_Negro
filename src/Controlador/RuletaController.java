package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {
    private Ruleta modelo;
    private final SessionController session;

    public RuletaController(SessionController session) {
        this.modelo = new Ruleta(0);
        this.session = session;
    }

    public int girar() {
        return modelo.girarRuleta();
    }

    public boolean evaluarYRegistrar(int numero, TipoApuesta tipo, int monto) {
        boolean acierto = modelo.evaluarResultado(numero, tipo);

        Resultado nuevoResultado = new Resultado(numero, monto, acierto, tipo);

        modelo.registrarResultadoEnRuleta(nuevoResultado);
        if (session.hayUsuario()) {
            session.getUsuarioActual().agregarResultado(nuevoResultado);
        }

        return acierto;
    }

    public Ruleta getModelo() { return modelo; }
}