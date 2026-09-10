import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class VentanaLogin {
    // --- Lista dinámica de usuarios ---
    public static final List<Usuario> USUARIOS = new ArrayList<>();
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin() {
        // Le añadimos un "Escuchador de Acciones" al botón.
        // Usamos una función lambda (e ->) para decirle que ejecute login() al hacer clic.
        btnIngresar.addActionListener(e -> login());
// TODO: Agregar los usuarios iniciales a la lista
// TODO: Inicializar y configurar la ventana
    }
    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {
// TODO: Centrar y mostrar la ventana
    }
    /**
     * Gestiona el inicio de sesión al presionar el botón.
     * Debe validar las credenciales ingresadas y abrir la siguiente
     * ventana o mostrar un mensaje de error.
     */
    private void login() {
        // 1. Capturamos lo que el usuario escribió en las cajas de texto
        String u = txtUsuario.getText();
        // Para JPasswordField, la buena práctica es obtener un arreglo de chars por seguridad y luego pasarlo a String
        String p = new String(txtClave.getPassword());

        // 2. Evaluamos usando el método que creamos en el paso anterior
        String nombreUsuario = validarCredenciales(u, p);

        // 3. Tomamos una decisión basada en el resultado
        if (!nombreUsuario.isEmpty()) {
            // Login exitoso: Mostramos mensaje y cerramos esta ventana
            JOptionPane.showMessageDialog(frame, "¡Acceso concedido! Bienvenido, " + nombreUsuario, "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);

            // Cierra la ventana de Login para liberar recursos de memoria
            frame.dispose();

            // TODO: Aquí abriremos la VentanaSaludo en el próximo paso
        } else {
            // Login fallido: Mostramos mensaje de error
            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos. Intente nuevamente.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
// TODO: Implementar la lógica de inicio de sesión

    /**
     * Valida las credenciales ingresadas utilizando la lista de usuarios.
     *
     * @param u nombre de usuario ingresado3
     * @param p contraseña ingresada
     * @return el nombre del usuario si las credenciales son válidas o una cadena vacía
    si no existe una coincidencia
     */
    private String validarCredenciales(String u, String p) {
        // Recorremos la lista de usuarios registrados
        for (Usuario usuario : USUARIOS) {
            // Delegamos la validación al propio objeto Usuario
            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre(); // Si coincide, retornamos el nombre real
            }
        }
        return ""; // Si termina el bucle y no hay coincidencias, retornamos un String vacío
    }
// TODO: Recorrer la lista y validar las credenciales
    /**
     * Abre la ventana de registro para crear un nuevo usuario.
     * Debe cerrar la ventana actual e invocar a VentanaRegistro.
     */
    private void abrirRegistro() {
// TODO: Cerrar la ventana actual y abrir la ventana de registro
    }

}
