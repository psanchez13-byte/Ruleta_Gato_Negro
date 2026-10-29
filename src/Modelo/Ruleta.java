package Modelo;

import java.util.Random;

public class Ruleta {
    private static final int MAX_HISTORIAL = 100;
    private static final int[] NUMEROS_ROJOS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    // Atributos de instancia
    private int saldo;
    private Resultado[] historialResultados;
    private int historialSize;
    private Random rng;

    /**
     * Constructor con parámetro de saldo inicial
     */
    public Ruleta(int saldoInicial) {
        this.saldo = Math.max(0, saldoInicial); // Asegura que no inicie con saldo negativo
        this.historialResultados = new Resultado[MAX_HISTORIAL];
        this.historialSize = 0;
        this.rng = new Random();
    }


    public Ruleta() {
        this(0); // Delega al constructor principal
    }

    // --- GETTERS Y GESTIÓN DE SALDO ---

    public int getSaldo() {
        return saldo;
    }

    public int getHistorialSize() {
        return historialSize;
    }


    public void depositar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        this.saldo += monto;
    }

    public void descontarSaldo(int monto) {
        if (monto > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente. Tu saldo actual es $" + this.saldo);
        }
        this.saldo -= monto;
    }

    // --- LÓGICA DEL JUEGO

    public int girarRuleta() {
        final int CANTIDAD_NUMEROS = 37;
        System.out.println("Giro Modelo.Ruleta");
        return rng.nextInt(CANTIDAD_NUMEROS);
    }

    /**
     * Evalúa si la apuesta fue acertada usando el Enum TipoApuesta
     */
    public String determinarColor(int numero) {
        if (numero == 0) {
            return "Verde";
        }
        if (esRojo(numero)) {
            return "Rojo";
        }
        return "Negro";
    }

    private boolean esRojo(int n) {
        for (int i = 0; i < NUMEROS_ROJOS.length; i++) {
            if (NUMEROS_ROJOS[i] == n) {
                return true;
            }
        }
        return false;
    }

    // --- HISTORIAL ---

    public void registrarResultadoEnRuleta(Resultado resultado) {
        if (historialSize < MAX_HISTORIAL) {
            historialResultados[historialSize] = resultado;
            historialSize++;
        } else {
            System.out.println("El historial está lleno. No se guardará esta ronda.");
        }
    }

    public int calcularTotalApostado() {
        int total = 0;
        for (int i = 0; i < historialSize; i++) {
            total += historialResultados[i].getMontoApostado();
        }
        return total;
    }

    public int calcularTotalAciertos() {
        int aciertos = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialResultados[i].isAcierto()) {
                aciertos++;
            }
        }
        return aciertos;
    }

    public int calcularGananciaNeta() {
        int ganancia = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialResultados[i].isAcierto()) {
                ganancia += historialResultados[i].getMontoApostado();
            } else {
                ganancia -= historialResultados[i].getMontoApostado();
            }
        }
        return ganancia;
    }

    public Resultado[] getHistorialResultados() {
        return historialResultados;
    }
}