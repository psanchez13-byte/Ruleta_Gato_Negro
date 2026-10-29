package Modelo;

public class ApuestaImpar extends ApuestaBase {
    public ApuestaImpar(int monto) {
        super(monto, "Impar");
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && (numero % 2 != 0);
    }
}