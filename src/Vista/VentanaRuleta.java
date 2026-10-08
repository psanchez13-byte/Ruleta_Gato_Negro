package Vista;

import Controlador.RuletaController;
import Controlador.SessionController; // NUEVO IMPORT
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.GridLayout;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Mesa de Ruleta - Casino Black Cat");

    // Componentes de entrada de datos
    private final JComboBox<TipoApuesta> cbTipoApuesta;
    private final JTextField txtMonto = new JTextField();
    private final JButton btnGirar = new JButton("¡Girar Ruleta!");
    private final JButton btnEstadisticas = new JButton("Ver Estadísticas");
    private final JButton btnVolver = new JButton("Volver al Menú"); // NUEVO BOTÓN

    // Controladores
    private final SessionController session; // para recordar quién es el usuario
    private final RuletaController controlador;

    // Constructor actualizado
    public VentanaRuleta(SessionController session, RuletaController controlador) {
        this.session = session;
        this.controlador = controlador;

        cbTipoApuesta = new JComboBox<>(TipoApuesta.values());

        // Aumentamos a 5 filas para que quepa el nuevo botón
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(new JLabel(" Seleccione su apuesta:"));
        frame.add(cbTipoApuesta);

        frame.add(new JLabel(" Monto a apostar ($):"));
        frame.add(txtMonto);

        frame.add(new JLabel(""));
        frame.add(btnGirar);

        frame.add(new JLabel(""));
        frame.add(btnEstadisticas);

        // Agregamos la fila del botón volver
        frame.add(btnVolver);
        frame.add(new JLabel(""));

        btnGirar.addActionListener(e -> ejecutarRonda());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticasVisuales());
        btnVolver.addActionListener(e -> volverAlMenu()); // Acción del botón

        frame.setSize(400, 250); // Un poco más alto para el botón extra
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    // Cierra la ruleta y vuelve a abrir el menú
    private void volverAlMenu() {
        frame.dispose();
        VentanaMenu menu = new VentanaMenu(session, controlador);
        menu.mostrarVentana();
    }

    private void mostrarEstadisticasVisuales() {
        Modelo.Ruleta miRuleta = controlador.getModelo();

        if (miRuleta.getHistorialSize() == 0) {
            JOptionPane.showMessageDialog(frame, "Aún no hay datos de jugadas en esta sesión.", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int totalApostado = miRuleta.calcularTotalApostado();
        int aciertos = miRuleta.calcularTotalAciertos();
        int gananciaNeta = miRuleta.calcularGananciaNeta();
        double porcentaje = (aciertos * 100.0) / miRuleta.getHistorialSize();

        String reporte = "ESTADÍSTICAS DE LA SESIÓN\n\n"
                + "Rondas jugadas: " + miRuleta.getHistorialSize() + "\n"
                + "Monto total apostado: $" + totalApostado + "\n"
                + "Cantidad total de aciertos: " + aciertos + "\n"
                + "Porcentaje de aciertos: " + String.format("%.2f", porcentaje) + "%\n"
                + "Ganancia o pérdida neta: $" + gananciaNeta;

        JOptionPane.showMessageDialog(frame, reporte, "Reporte de Sesión", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ejecutarRonda() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());

            if (monto <= 0) {
                JOptionPane.showMessageDialog(frame, "El monto debe ser mayor a cero.", "Monto Inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                controlador.getModelo().descontarSaldo(monto);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Saldo Insuficiente", JOptionPane.WARNING_MESSAGE);
                return;
            }

            TipoApuesta tipoApuesta = (TipoApuesta) cbTipoApuesta.getSelectedItem();
            int numeroGanador = controlador.girar();
            boolean acierto = controlador.evaluarYRegistrar(numeroGanador, tipoApuesta, monto);

            String mensaje = "El número ganador es: " + numeroGanador + "\n\n";
            if (acierto) {
                int premio = monto * 2;
                controlador.getModelo().depositar(premio);
                mensaje += "¡FELICIDADES! Ganaste $" + premio + "\n(Tu saldo ahora es $" + controlador.getModelo().getSaldo() + ")";
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensaje += "Lamentablemente perdiste $" + monto + "\n(Tu saldo ahora es $" + controlador.getModelo().getSaldo() + ")";
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.ERROR_MESSAGE);
            }
            txtMonto.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un monto válido (solo números enteros).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}