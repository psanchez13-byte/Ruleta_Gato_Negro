package Controlador;

import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {
    private Ruleta modelo;

    public RuletaController() {
        this.modelo = new Ruleta(); // Instanciamos el objeto real
    }

    public int girar() {
        return modelo.girarRuleta();
    }

    public boolean evaluarYRegistrar(int numero, TipoApuesta tipo, int monto) {
        boolean acierto = modelo.evaluarResultado(numero, tipo);
        modelo.registrarResultado(numero, monto, acierto);
        return acierto;
    }

    public Ruleta getModelo() {
        return modelo;
    }
}