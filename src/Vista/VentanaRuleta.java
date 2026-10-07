package Vista;

import Controlador.RuletaController;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.GridLayout;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Mesa de Ruleta - Casino Black Cat");

    // Componentes de entrada de datos
    private final JComboBox<TipoApuesta> cbTipoApuesta;
    private final JTextField txtMonto = new JTextField();
    private final JButton btnGirar = new JButton("Girar Ruleta");
    private final JButton btnEstadisticas = new JButton("Ver Estadísticas");

    private final RuletaController controlador;

    // Constructor que recibe el controlador
    public VentanaRuleta(RuletaController controlador) {
        this.controlador = controlador;

        cbTipoApuesta = new JComboBox<>(TipoApuesta.values());

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel(" Seleccione su apuesta:"));
        frame.add(cbTipoApuesta);

        frame.add(new JLabel(" Monto a apostar ($):"));
        frame.add(txtMonto);

        frame.add(new JLabel(""));
        frame.add(btnGirar);

        frame.add(new JLabel(""));
        frame.add(btnEstadisticas);

        btnGirar.addActionListener(e -> ejecutarRonda());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticasVisuales());

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void mostrarEstadisticasVisuales() {
        // Obtenemos la instancia real de la ruleta
        Ruleta miRuleta = controlador.getModelo();

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

            TipoApuesta tipoApuesta = (TipoApuesta) cbTipoApuesta.getSelectedItem();

            int numeroGanador = controlador.girar();
            boolean acierto = controlador.evaluarYRegistrar(numeroGanador, tipoApuesta, monto);

            // Mostrar resultado visualmente
            String mensaje = "El número ganador es: " + numeroGanador + "\n\n";
            if (acierto) {
                mensaje += "¡FELICIDADES! Ganaste $" + monto;
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensaje += "Lamentablemente perdiste $" + monto;
                JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Ronda", JOptionPane.ERROR_MESSAGE);
            }

            txtMonto.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un monto válido (solo números enteros).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
