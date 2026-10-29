package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;

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

    public boolean evaluarYRegistrar(int numero, ApuestaBase apuesta) {
        String colorGanador = modelo.determinarColor(numero);
        boolean acierto = apuesta.acierta(numero, colorGanador);

        Resultado nuevoResultado = new Resultado(numero, apuesta.getMonto(), acierto, apuesta);

        modelo.registrarResultadoEnRuleta(nuevoResultado);

        if (session.hayUsuario()) {
            session.getUsuarioActual().agregarResultado(nuevoResultado);
        }

        return acierto;
    }

    public Ruleta getModelo() { return modelo; }
}