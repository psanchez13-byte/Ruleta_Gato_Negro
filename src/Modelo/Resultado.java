package Modelo;

public class Resultado {
    private int numeroObtenido;
    private int montoApostado;
    private boolean acierto;

    // Constructor
    public Resultado(int numeroObtenido, int montoApostado, boolean acierto) {
        this.numeroObtenido = numeroObtenido;
        this.montoApostado = montoApostado;
        this.acierto = acierto;
    }

    public int getNumeroObtenido() {
        return numeroObtenido;
    }

    public int getMontoApostado() {
        return montoApostado;
    }

    public boolean isAcierto() {
        return acierto;
    }
}
