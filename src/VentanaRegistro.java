import javax.swing.*;
import java.awt.GridLayout;

public class VentanaRegistro {

    private final JFrame frame = new JFrame("Registro de Nuevo Jugador");
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnGuardar = new JButton("Registrar y Volver");

    public VentanaRegistro() {
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel(" Nombre Real:"));
        frame.add(txtNombre);

        frame.add(new JLabel(" Nombre de Usuario (Login):"));
        frame.add(txtUsuario);

        frame.add(new JLabel(" Contraseña:"));
        frame.add(txtClave);

        frame.add(new JLabel("")); // Espacio
        frame.add(btnGuardar);

        btnGuardar.addActionListener(e -> registrarUsuario());

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void registrarUsuario() {
        String n = txtNombre.getText().trim();
        String u = txtUsuario.getText().trim();
        String p = new String(txtClave.getPassword()).trim();

        // 1. Verificar que los campos no estén vacíos (Regla de negocio)
        if (n.isEmpty() || u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return; // Detenemos la ejecución
        }

        // 2. Agregar el nuevo usuario a la lista estática del Login
        // Usamos la clase VentanaLogin para acceder a la lista compartida
        VentanaLogin.USUARIOS.add(new Usuario(u, p, n));

        // 3. Mensaje de éxito
        JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente. Ahora puede iniciar sesión.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        // 4. Cerrar esta ventana y volver al login
        frame.dispose();
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana();
    }
}
