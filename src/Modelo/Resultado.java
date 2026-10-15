package Modelo;

public class Resultado {
    private int numeroObtenido;
    private int montoApostado;
    private boolean acierto;
    private TipoApuesta tipoApuesta;

    public Resultado(int numeroObtenido, int montoApostado, boolean acierto, TipoApuesta tipoApuesta) {
        this.numeroObtenido = numeroObtenido;
        this.montoApostado = montoApostado;
        this.acierto = acierto;
        this.tipoApuesta = tipoApuesta;
    }

    public int getNumeroObtenido() { return numeroObtenido; }
    public int getMontoApostado() { return montoApostado; }
    public boolean isAcierto() { return acierto; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
}