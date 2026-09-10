import javax.swing.*;
import java.awt.GridLayout;

public class VentanaSaludo {

    private final JFrame frame = new JFrame("Bienvenido al Casino");
    private final JLabel lblMensaje;
    private final JButton btnJugar = new JButton("Ir a la Ruleta");

    /**
     * El constructor recibe el nombre del jugador para personalizar el mensaje.
     */
    public VentanaSaludo(String nombreJugador) {
        // Inicializamos el texto con el nombre recibido
        lblMensaje = new JLabel("¡Bienvenido a la mesa, " + nombreJugador + "!", SwingConstants.CENTER);

        frame.setLayout(new GridLayout(2, 1, 10, 10));
        frame.add(lblMensaje);
        frame.add(btnJugar);
        // Conectamos el boton
        btnJugar.addActionListener(e -> irALaRuleta());

        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void irALaRuleta() {
        // 1. Cerramos la ventana de saludo actual
        frame.dispose();

        // 2. Instanciamos la nueva ventana del juego y la mostramos
        VentanaRuleta ruleta = new VentanaRuleta();
        ruleta.mostrarVentana();
    }
}