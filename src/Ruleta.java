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
     * Metodo principal: inicia el programa llamando al menu.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Controla el flujo principal del programa mostrando
     * un menú en consola hasta que el usuario decida salir.
     */
    public static void menu() {
        Scanner recibir = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerOpcion(recibir);
            ejecutarOpcion(opcion, recibir);

        } while (!(opcion == 3));
        recibir.close();// Libera Recursos de la memoria
    }

    /**
     * Muestra en consola las opciones disponibles del menu.
     */
    public static void mostrarMenu() {
        System.out.println("Casino Black Cat / Ruleta");
        System.out.println("1.Partida Ronda");
        System.out.println("2.Ver Estadisticas");
        System.out.println("3.Salir");
        System.out.println("Elija Una Opcion:");
    }

    /**
     *Lee la opcion elegida por el usuario desde el teclado
     */
    public static int leerOpcion(Scanner in) {
        int opcion = in.nextInt();
        in.nextLine();
        return opcion;
    }

    /**
     *Ejecuta la accion correspondiente a la opcion del menu
     */
    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("Saliendo, Nos vemos");
                break;
            default:
                System.out.println("Opcion invalida, seleccione 1,2 o 3");
                break;
        }
    }

    /**
     *Inicia la ronda de la ruleta leyendo la apuesta ,girando,evaluando y mostrando el resultado final
     */
    public static void iniciarRonda(Scanner in) {
        char tipoApuesta = leerTipoApuesta(in);

        System.out.println("Ingrese monto a apostar");
        int monto = in.nextInt();
        in.nextLine();

        int numeroRuleta = girarRuleta();
        boolean acierto = evaluarResultado(numeroRuleta,tipoApuesta);

        registrarResultado(numeroRuleta,monto,acierto);
        mostrarResultado(numeroRuleta,tipoApuesta,monto,acierto);
    }

    /**
     *Permite al usuario seleccionar y evaluar el tipo de apuesta mediante un ciclo de verificacion
     */
    public static char leerTipoApuesta(Scanner in) {
        char tipo = ' ';
        boolean valido = false;

        do {
            System.out.println("Tipos de apuesta: (R) Rojo | (N) Negro | (P) Par | (I) Impar");
            System.out.println("Ingrese su eleccion: ");
            // Normaliza la entrada de datos quitando espacios y forzando las mayusculas
            String entrada = in.nextLine().trim().toUpperCase();

            if (entrada.length() > 0) {
                tipo = entrada.charAt(0);

                if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                    valido = true;
                } else {
                    System.out.println("Opcion invalida. Intente de nuevo.");
                }
            } else {
                System.out.println("No ingreso ningun dato");
            }
        } while (!valido);

        return tipo;
    }

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
     * Muestra el resultado final de la ronda del usuario
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
        System.out.println(" Resultado De La Ronda ");
        System.out.println("El numero ganador es: " + numero);
        System.out.println("Su apuesta fue: " + tipo + " por $" + monto);

        if (acierto) {
            int ganancia = monto;
            System.out.println("¡FELICIDADES! Gano $" + ganancia);
        } else {
            System.out.println("Lamentablemente perdio $" + monto);
        }
    }

    /**
     * muestra las estadisticas generales de las rondas jugadas hasta el momento
     */
    public static void mostrarEstadisticas() {
        if ( historialSize == 0) {
            System.out.println(" No hay datos");
            return;
        }
        int totalApostado = calcularTotalApostado();
        int aciertos = calcularTotalAciertos();
        int gananciaNeta = calcularGananciaNeta();
        double porcentaje = (aciertos * 100.0) / historialSize;

        System.out.println(" ESTADÍSTICAS DE LA SESIÓN ");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Monto total apostado: $" + totalApostado);
        System.out.println("Cantidad total de aciertos: " + aciertos);
        System.out.println("Porcentaje de aciertos: " + String.format("%.2f", porcentaje) + "%");
        System.out.println("Ganancia o pérdida neta: $" + gananciaNeta);

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