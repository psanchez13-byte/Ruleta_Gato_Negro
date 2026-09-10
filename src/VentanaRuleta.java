import javax.swing.*;
import java.awt.GridLayout;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Mesa de Ruleta - Casino Black Cat");

    // Componentes de entrada de datos
    private final JComboBox<String> cbTipoApuesta;
    private final JTextField txtMonto = new JTextField();
    private final JButton btnGirar = new JButton("¡Girar Ruleta!");

    public VentanaRuleta() {
        // Configuramos las opciones del menú desplegable
        String[] opciones = {"(R) Rojo", "(N) Negro", "(P) Par", "(I) Impar"};
        cbTipoApuesta = new JComboBox<>(opciones);

        // Configuramos el diseño visual (3 filas, 2 columnas)
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        // Fila 1: Selección de apuesta
        frame.add(new JLabel(" Seleccione su apuesta:"));
        frame.add(cbTipoApuesta);

        // Fila 2: Ingreso de dinero
        frame.add(new JLabel(" Monto a apostar ($):"));
        frame.add(txtMonto);

        // Fila 3: Botón de acción
        frame.add(new JLabel("")); // Espacio vacío por estética
        frame.add(btnGirar);

        // Conectamos el botón a un método que construiremos luego
        btnGirar.addActionListener(e -> ejecutarRonda());

        frame.setSize(400, 180);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void ejecutarRonda() {
        // TODO: Aquí extraeremos los datos y llamaremos a los métodos de Ruleta.java
        System.out.println("Botón presionado. Preparando el giro...");
    }
}
