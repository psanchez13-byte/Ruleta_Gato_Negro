package Modelo;

public abstract class ApuestaBase {
    protected int monto; // Atributo protegido para que las subclases accedan
    protected String etiqueta; // Atributo protegido

    public ApuestaBase(int monto, String etiqueta) {
        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public int getMonto() {
        return monto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public abstract boolean acierta(int numero, String color);
}