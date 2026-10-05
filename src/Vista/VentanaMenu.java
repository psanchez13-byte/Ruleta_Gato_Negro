package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");

    // Declaración de los botones del menú
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel lblMensaje;

    public VentanaMenu(String nombreUsuario) {
        // Usamos etiquetas HTML básicas dentro del JLabel para poder hacer saltos de línea
        lblMensaje = new JLabel("<html><b>Bienvenido/a al menú principal, " + nombreUsuario + ".</b><br><br>" +
                "A la izquierda tienes:<br>" +
                "- Jugar: abre la ventana de juego.<br>" +
                "- Historial: abre la ventana de estadísticas.<br>" +
                "- Salir: cierra sesión y vuelve al login.</html>");
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Margen estético

        // Usaremos BorderLayout para separar el panel lateral del contenido principal
        frame.setLayout(new BorderLayout());

        // Creamos un sub-panel a la izquierda para apilar los botones verticalmente
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        // Añadimos las piezas al plano principal
        frame.add(panelBotones, BorderLayout.WEST); // Botones a la izquierda
        frame.add(lblMensaje, BorderLayout.CENTER); // Texto al centro

        // Conectamos los botones a sus acciones
        btnJugar.addActionListener(e -> abrirRuleta());
        btnSalir.addActionListener(e -> cerrarSesion());
        btnHistorial.addActionListener(e -> mostrarHistorial());

        // Configuramos la ventana
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void abrirRuleta() {
        frame.dispose(); // Cierra el menú
        VentanaRuleta ruleta = new VentanaRuleta();
        ruleta.mostrarVentana();
    }

    private void cerrarSesion() {
        frame.dispose(); // Cierra el menú
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana(); // Vuelve al inicio
    }
    //Crear Ventana para hsitorial Por hacer
    private void mostrarHistorial() {
        JOptionPane.showMessageDialog(frame, "La ventana de historial.", "En construcción", JOptionPane.INFORMATION_MESSAGE);
    }
}
