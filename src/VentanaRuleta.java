import javax.swing.*;
import java.awt.GridLayout;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Mesa de Ruleta - Casino Black Cat");

    // Componentes de entrada de datos
    private final JComboBox<String> cbTipoApuesta;
    private final JTextField txtMonto = new JTextField();
    private final JButton btnGirar = new JButton("¡Girar Ruleta!");
    private final JButton btnEstadisticas = new JButton("Ver Estadísticas");
    private final JButton btnRegistro = new JButton("Registrarse");

    public VentanaRuleta() {
        // Configuramos las opciones del menú desplegable
        String[] opciones = {"(R) Rojo", "(N) Negro", "(P) Par", "(I) Impar"};
        cbTipoApuesta = new JComboBox<>(opciones);

        // Configuramos el diseño visual (3 filas, 2 columnas)
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Fila 1: Selección de apuesta
        frame.add(new JLabel(" Seleccione su apuesta:"));
        frame.add(cbTipoApuesta);

        // Fila 2: Ingreso de dinero
        frame.add(new JLabel(" Monto a apostar ($):"));
        frame.add(txtMonto);

        // Fila 3: Botón de acción
        frame.add(new JLabel("")); // Espacio vacío por estética
        frame.add(btnGirar);

        // Fila 4: Fila para estadísticas
        frame.add(new JLabel("")); // Espacio vacío
        frame.add(btnEstadisticas);

        // Fila 5: Botón de registro
        frame.add(new JLabel("¿No tienes cuenta?"));
        frame.add(btnRegistro);

        btnGirar.addActionListener(e -> ejecutarRonda());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticasVisuales());
        btnRegistro.addActionListener(e -> abrirRegistro());

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void mostrarEstadisticasVisuales() {
        // Verificamos si hay jugadas registradas consultando la variable estática de Ruleta
        if (Ruleta.historialSize == 0) {
            JOptionPane.showMessageDialog(frame, "Aún no hay datos de jugadas en esta sesión.", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int totalApostado = Ruleta.calcularTotalApostado();
        int aciertos = Ruleta.calcularTotalAciertos();
        int gananciaNeta = Ruleta.calcularGananciaNeta();
        double porcentaje = (aciertos * 100.0) / Ruleta.historialSize;

        // Armamos un String multilinea con los resultados
        String reporte = "ESTADÍSTICAS DE LA SESIÓN\n\n"
                + "Rondas jugadas: " + Ruleta.historialSize + "\n"
                + "Monto total apostado: $" + totalApostado + "\n"
                + "Cantidad total de aciertos: " + aciertos + "\n"
                + "Porcentaje de aciertos: " + String.format("%.2f", porcentaje) + "%\n"
                + "Ganancia o pérdida neta: $" + gananciaNeta;

        JOptionPane.showMessageDialog(frame, reporte, "Reporte de Sesión", JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirRegistro() {
        // Cerramos la ventana de login
        frame.dispose();

        VentanaRegistro registro = new VentanaRegistro();
        registro.mostrarVentana();
    }

    private void ejecutarRonda() {
        try {
            // 1. Extraer y validar el monto apostado
            // Si el usuario escribe letras, Integer.parseInt lanzará un error que atraparemos abajo
            int monto = Integer.parseInt(txtMonto.getText());

            if (monto <= 0) {
                JOptionPane.showMessageDialog(frame, "El monto debe ser mayor a cero.", "Monto Inválido", JOptionPane.WARNING_MESSAGE);
                return; // Detenemos la ejecución aquí
            }

            // 2. Extraer el tipo de apuesta del JComboBox
            // Las opciones son "(R) Rojo", "(N) Negro", etc. El índice 1 de ese texto (la segunda letra) es la clave 'R', 'N', 'P', o 'I'
            String seleccion = (String) cbTipoApuesta.getSelectedItem();
            char tipoApuesta = seleccion.charAt(1);

            // 3. COMUNICACIÓN CON LA LÓGICA (Clase Ruleta)
            int numeroGanador = Ruleta.girarRuleta();
            boolean acierto = Ruleta.evaluarResultado(numeroGanador, tipoApuesta);

            // Guardamos el resultado en los arreglos históricos
            Ruleta.registrarResultado(numeroGanador, monto, acierto);

            // 4. Mostrar el resultado visualmente
            String mensaje = "El número ganador es: " + numeroGanador + "\n\n";
            if (acierto) {
                mensaje += "¡FELICIDADES! Ganaste $" + monto;
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensaje += "Lamentablemente perdiste $" + monto;
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.ERROR_MESSAGE);
            }

            // 5. Limpiar la caja de texto para la siguiente ronda
            txtMonto.setText("");

        } catch (NumberFormatException ex) {
            // Si el código llega aquí, significa que el usuario intentó apostar letras o símbolos
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un monto válido (solo números enteros).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
