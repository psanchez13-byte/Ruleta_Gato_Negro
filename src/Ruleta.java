import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    /**
     *simula el giro de la ruleta lanzando un numero aleatorio del 0 al 36
     */
    public static int girarRuleta() {
        final int CANTIDAD_NUMEROS = 37;

        System.out.println("Giro Ruleta");
        int resultado = rng.nextInt(CANTIDAD_NUMEROS);

        return resultado;
    }

    /**
     *evalua si la apuesta del usuario fue acertada con la logica de casino
     */
    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) {
            return false;
        }

        switch (tipo) {
            case 'R':
                return esRojo(numero);
            case 'N':
                return !esRojo(numero);
            case 'P':
                return (numero % 2 == 0);
            case 'I':
                return (numero % 2 != 0);
            default:
                return false;
        }

    }

    /**
     *determina si un numero corresponde a color rojo a travez de un arreglo static
     */
    public static boolean esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i ++) {
            if (numerosRojos[i] == n) {
                return true;
            }
        }
        return false;
    }

    /**
     *Registra los resultados de la ronda en los arreglos en historial
     */
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {

            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;

            historialSize++;


        } else {
            System.out.println("El historial esta lleno . No se guardara esta ronda");
        }
    }

    /**
     *calcula la sumatoria de las apuestas realizadas recorriendo el historial activo
     */
    public static int calcularTotalApostado() {
        int total = 0;
        for (int i = 0; i < historialSize; i++) {
            total += historialApuestas[i];
        }
        return total;
    }

    /**
     *cuenta la cantidad de rondas donde el usuario gano
     */
    public static int calcularTotalAciertos() {
        int aciertos = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                aciertos++;
            }
        }
        return aciertos;
    }

    /**
     *calcula el balance economico  final del usuario sumando la apuestas ganadas y restando las perdidas
     */
    public static int calcularGananciaNeta() {
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