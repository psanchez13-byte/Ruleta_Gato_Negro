package Modelo;

public class Resultado {
    private int numeroObtenido;
    private int montoApostado;
    private boolean acierto;
    private ApuestaBase apuesta;

    public Resultado(int numeroObtenido, int montoApostado, boolean acierto, ApuestaBase apuesta) {
        this.numeroObtenido = numeroObtenido;
        this.montoApostado = montoApostado;
        this.acierto = acierto;
        this.apuesta = apuesta;
    }

    public int getNumeroObtenido() { return numeroObtenido; }
    public int getMontoApostado() { return montoApostado; }
    public boolean isAcierto() { return acierto; }
    public ApuestaBase getApuesta() { return apuesta; }
}