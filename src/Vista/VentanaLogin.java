package Vista;

import Controlador.SessionController;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.GridLayout;

public class VentanaLogin {

    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistro = new JButton("Registrarse");
    private final SessionController session;

    /**
     * Constructor que recibe el controlador
     */
    public VentanaLogin(SessionController session) {
        this.session = session;

        // Agregar los usuarios iniciales
        try {
            session.registrarUsuario("admin", "1234", "Don Donnie");
            session.registrarUsuario("jugador1", "gato", "Pedro");
        } catch (IllegalArgumentException e) {
            // Ignoramos si ya existen
        }

        //layout
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(lblUsuario);   // Fila 1, Columna 1
        frame.add(txtUsuario);   // Fila 1, Columna 2

        frame.add(lblClave);     // Fila 2, Columna 1
        frame.add(txtClave);     // Fila 2, Columna 2

        frame.add(new JLabel("")); // Fila 3, Columna 1 (Espacio vacío por estética)
        frame.add(btnIngresar);    // Fila 3, Columna 2

        frame.add(new JLabel("¿No tienes cuenta?"));
        frame.add(btnRegistro);

        btnIngresar.addActionListener(e -> login());
        btnRegistro.addActionListener(e -> abrirRegistro());

        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void login() {

        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());

        // Evaluamos delegando al CONTROLADOR
        boolean ingreso = session.iniciarSesion(u, p);

        if (ingreso) {
            String nombreUsuario = session.getNombreUsuario();
            JOptionPane.showMessageDialog(frame, "¡Acceso concedido! Bienvenido, " + nombreUsuario, "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();


            VentanaMenu menu = new VentanaMenu(session, null);
            menu.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos. Intente nuevamente.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        frame.dispose();
        ;

        JOptionPane.showMessageDialog(frame, "Ventana de registro en construcción para la Iteración 04.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}
