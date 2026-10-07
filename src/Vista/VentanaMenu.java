package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel lblMensaje;

    private final JLabel lblPerfil = new JLabel();
    private final JButton btnCambiarNombre = new JButton("Cambiar Nombre");
    private final JButton btnRecargar = new JButton("Recargar Saldo");

    // Controladores
    private final SessionController session;
    private final RuletaController controladorRuleta;

    public VentanaMenu(SessionController session, RuletaController controladorRuleta) {
        this.session = session;
        // Si no existe un juego activo, lo creamos
        this.controladorRuleta = (controladorRuleta != null) ? controladorRuleta : new RuletaController();

        lblMensaje = new JLabel();
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        frame.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        // ---  Sub-panel para el Perfil y Saldo ---
        JPanel panelPerfil = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPerfil.setBorder(BorderFactory.createTitledBorder("Perfil"));
        panelPerfil.add(lblPerfil);
        panelPerfil.add(btnCambiarNombre);
        panelPerfil.add(btnRecargar);

        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(lblMensaje, BorderLayout.CENTER);
        frame.add(panelPerfil, BorderLayout.SOUTH);

        btnJugar.addActionListener(e -> abrirRuleta());
        btnSalir.addActionListener(e -> cerrarSesion());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnCambiarNombre.addActionListener(e -> cambiarNombre());
        btnRecargar.addActionListener(e -> recargarSaldo());

        // Cargamos el nombre y el saldo inicial
        actualizarTextos();

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void abrirRuleta() {
        frame.dispose(); // Cierra el menú
        VentanaRuleta ruleta = new VentanaRuleta(controladorRuleta);
        ruleta.mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();

        //Volver a abrir VentanaLogin pasándole la variable 'session'
        JOptionPane.showMessageDialog(null, "Sesión cerrada. Cierre la ventana por ahora.", "Adiós", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarHistorial() {
        JOptionPane.showMessageDialog(frame, "La ventana de historial.", "En construcción", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarTextos() {
        String nombreUsuario = session.getNombreUsuario();
        int saldo = controladorRuleta.getModelo().getSaldo(); // Uso del getter[cite: 27]

        lblMensaje.setText("<html><b>Bienvenido/a al menú principal, " + nombreUsuario + ".</b><br><br>" +
                "A la izquierda tienes:<br>" +
                "- Jugar: abre la ventana de juego.<br>" +
                "- Historial: abre la ventana de estadísticas.<br>" +
                "- Salir: cierra sesión y vuelve al login.</html>");

        lblPerfil.setText("Saldo: $" + saldo + "   ");
    }

    private void cambiarNombre() {
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Ingrese su nuevo nombre:");
        if (nuevoNombre != null) {
            try {
                session.getUsuarioActual().setNombre(nuevoNombre); // Valida que no esté vacío
                actualizarTextos();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void recargarSaldo() {
        try {
            String input = JOptionPane.showInputDialog(frame, "Monto a depositar ($):");
            if (input != null) {
                int monto = Integer.parseInt(input);
                controladorRuleta.getModelo().depositar(monto); // Deposita usando lógica segura
                actualizarTextos();
            }
        } catch (NumberFormatException ex) {
            // Entra aquí si el usuario escribe letras en vez de números
            JOptionPane.showMessageDialog(frame, "Debe ingresar un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Entra aquí si el modelo rechaza el monto
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Monto Inválido", JOptionPane.ERROR_MESSAGE);
        }
    }
}
