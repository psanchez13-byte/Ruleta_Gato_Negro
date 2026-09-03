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
     * Método principal: inicia el programa llamando al menú.
     */
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner recibir = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerOpcion(recibir);
            ejecutarOpcion(opcion, recibir);

        } while (!(opcion == 3));
    }
// TODO: Repetir el menú hasta que el usuario elija salir.


    public static void mostrarMenu() {
        System.out.println("Casino Black Cat / Ruleta");
        System.out.println("1.Partida Ronda");
        System.out.println("2.Ver Estadisticas");
        System.out.println("3.Salir");
        System.out.println("Elija Una Opcion:");
    }
// TODO: Mostrar las opciones disponibles para el usuario.


    public static int leerOpcion(Scanner in) {
        int opcion = in.nextInt();
        in.nextLine();
        return opcion;
    }
// TODO: Leer y retornar la opción ingresada.


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
// TODO: Ejecutar la acción asociada a la opción.


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
// TODO: Implementar el flujo completo de una ronda.


    public static char leerTipoApuesta(Scanner in) {
        char tipo = ' ';
        boolean valido = false;

        do {
            System.out.println("Tipos de apuesta: (R) Rojo | (N) Negro | (P) Par | (I) Impar");
            System.out.println("Ingrese su eleccion: ");

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
// TODO: Leer y validar el tipo de apuesta.


    public static int girarRuleta() {
        final int CANTIDAD_NUMEROS = 37;

        System.out.println("Giro Ruleta");
        int resultado = rng.nextInt(CANTIDAD_NUMEROS);

        return resultado;
    }
// TODO: Generar y retornar un número entre 0 y 36.


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
// TODO: Evaluar el resultado según el tipo de apuesta.


    public static boolean esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i ++) {
            if (numerosRojos[i] == n) {
                return true;
            }
        }
        return false;
    }


// TODO: Buscar el número en el arreglo numerosRojos.

    /**
     * Registra los resultados de la ronda en los arreglos
     * de historial.
     *
     * @param numero número obtenido en la ruleta.
     * @param apuesta monto apostado.
     * @param acierto si el jugador acertó o no.
     */
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
// TODO: Guardar los datos sin superar MAX_HISTORIAL.
    }
    /**
     * Muestra en consola el resultado de la ronda.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
// TODO: Mostrar los datos y el resultado de la ronda.
    }
    /**
     * Muestra estadísticas generales de todas las
     * rondas jugadas.
     */
    public static void mostrarEstadisticas() {
// TODO: Calcular y mostrar las estadísticas acumuladas.
    }
}