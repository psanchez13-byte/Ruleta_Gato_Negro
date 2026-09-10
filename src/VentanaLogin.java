import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.GridLayout;

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
    private final JButton btnRegistro = new JButton("Registrarse");
    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin() {
        // 1. Agregar los usuarios iniciales a la lista temporal
        USUARIOS.add(new Usuario("admin", "1234", "Don Donnie"));
        USUARIOS.add(new Usuario("jugador1", "gato", "Pedro"));

        // 2. Configurar el administrador de diseño (Layout)
        // GridLayout(filas, columnas, espacioHorizontal, espacioVertical)
        frame.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        // 3. Añadir los componentes al "frame" (El orden importa)
        frame.add(lblUsuario);   // Fila 1, Columna 1
        frame.add(txtUsuario);   // Fila 1, Columna 2

        frame.add(lblClave);     // Fila 2, Columna 1
        frame.add(txtClave);     // Fila 2, Columna 2

        frame.add(new JLabel("")); // Fila 3, Columna 1 (Espacio vacío por estética)
        frame.add(btnIngresar);    // Fila 3, Columna 2

        // FILA 4: El botón de registro
        frame.add(new JLabel("¿No tienes cuenta?"));
        frame.add(btnRegistro);

        // 4. Conectar el botón a la función que valida el login
        btnIngresar.addActionListener(e -> login());
        btnRegistro.addActionListener(e -> abrirRegistro());

        // 5. Configuraciones de tamaño y cierre
        frame.setSize(350, 200); // Un tamaño apropiado (evita que se vea gigante como en tu foto)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Que el programa se detenga al cerrar la 'X'
    }
// TODO: Agregar los usuarios iniciales a la lista
// TODO: Inicializar y configurar la ventana

    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {
        // Esto centra la ventana en la pantalla (opcional, pero buena práctica)
        frame.setLocationRelativeTo(null);

        // ESTA ES LA LÍNEA CLAVE: Dibuja la ventana y mantiene el programa ejecutándose
        frame.setVisible(true);
    }
// TODO: Centrar y mostrar la ventana

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

        // 2. Evaluamos
        String nombreUsuario = validarCredenciales(u, p);

        // 3. Tomamos una decisión basada en el resultado
        if (!nombreUsuario.isEmpty()) {
            // Login exitoso: Mostramos mensaje y cerramos esta ventana
            JOptionPane.showMessageDialog(frame, "¡Acceso concedido! Bienvenido, " + nombreUsuario, "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);

            // Cierra la ventana de Login para liberar recursos de memoria
            frame.dispose();

            // Instancia la nueva ventana pasándole el nombre del usuario y la muestra
            VentanaSaludo saludo = new VentanaSaludo(nombreUsuario);
            saludo.mostrarVentana();
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
        // Cierra la ventana actual de login
        frame.dispose();

        // Instancia y abre la ventana de registro que creamos anteriormente
        VentanaRegistro registro = new VentanaRegistro();
        registro.mostrarVentana();
    }
// TODO: Cerrar la ventana actual y abrir la ventana de registro


}
