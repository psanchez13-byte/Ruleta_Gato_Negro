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
    private int[] historialNumeros;
    private int[] historialApuestas;
    private boolean[] historialAciertos;
    private int historialSize;
    private Random rng;

    /**
     * Constructor con parámetro de saldo inicial
     */
    public Ruleta(int saldoInicial) {
        this.saldo = Math.max(0, saldoInicial); // Asegura que no inicie con saldo negativo
        this.historialNumeros = new int[MAX_HISTORIAL];
        this.historialApuestas = new int[MAX_HISTORIAL];
        this.historialAciertos = new boolean[MAX_HISTORIAL];
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
            throw new IllegalArgumentException("Saldo insuficiente.");
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
    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) {
            return false;
        }

        switch (tipo) {
            case ROJO:
                return esRojo(numero);
            case NEGRO:
                return !esRojo(numero);
            case PAR:
                return (numero % 2 == 0);
            case IMPAR:
                return (numero % 2 != 0);
            default:
                return false;
        }
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

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        } else {
            System.out.println("El historial está lleno. No se guardará esta ronda.");
        }
    }

    public int calcularTotalApostado() {
        int total = 0;
        for (int i = 0; i < historialSize; i++) {
            total += historialApuestas[i];
        }
        return total;
    }

    public int calcularTotalAciertos() {
        int aciertos = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                aciertos++;
            }
        }
        return aciertos;
    }

    public int calcularGananciaNeta() {
        int ganancia = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
            }
        }
        return ganancia;
    }
}