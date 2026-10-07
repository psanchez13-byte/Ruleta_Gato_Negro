package Vista;

import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {

    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Nuevo Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Contraseña:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JLabel lblNombre = new JLabel("Nombre Completo:");
    private final JTextField txtNombre = new JTextField();

    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver");

    // Controlador de sesión
    private final SessionController session;

    public VentanaRegistro(SessionController session) {
        this.session = session;

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(lblNombre);
        frame.add(txtNombre);

        frame.add(lblUsuario);
        frame.add(txtUsuario);

        frame.add(lblClave);
        frame.add(txtClave);

        frame.add(btnVolver);
        frame.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarNuevoJugador());
        btnVolver.addActionListener(e -> volverAlLogin());

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void registrarNuevoJugador() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String n = txtNombre.getText();

        try {
            // Delegamos el registro al controlador
            session.registrarUsuario(u, p, n);
            JOptionPane.showMessageDialog(frame, "Registro exitoso. Ahora puede iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverAlLogin();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlLogin() {
        frame.dispose();
        // Pasamos la sesión de vuelta al login
        VentanaLogin login = new VentanaLogin(session);
        login.mostrarVentana();
    }
}
