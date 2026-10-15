package Vista;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial de Jugadas - Casino");
    private final JTextArea txtHistorial = new JTextArea();
    private final JButton btnVolver = new JButton("Volver al Menú");

    private final SessionController session;
    private final RuletaController controladorRuleta;

    public VentanaHistorial(SessionController session, RuletaController controladorRuleta) {
        this.session = session;
        this.controladorRuleta = controladorRuleta;

        ResultadoController resController = new ResultadoController(session);

        txtHistorial.setEditable(false);
        txtHistorial.setText(resController.obtenerHistorialUsuario());
        txtHistorial.setMargin(new Insets(10, 10, 10, 10));

        //un scroll para historiales muy largos
        JScrollPane scrollPane = new JScrollPane(txtHistorial);

        frame.setLayout(new BorderLayout(10, 10));
        frame.add(new JLabel("Historial de Jugadas", SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnVolver);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> volverAlMenu());

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void volverAlMenu() {
        frame.dispose();
        VentanaMenu menu = new VentanaMenu(session, controladorRuleta);
        menu.mostrarVentana();
    }
}
