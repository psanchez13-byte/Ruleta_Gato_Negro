package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuariosCSV {
    private static final String ARCHIVO = "usuarios.csv";

    public static void guardarUsuario(Usuario u) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(u.getUsername() + "," + u.getPassword() + "," + u.getNombre());
            bw.newLine();
        }
    }

    // Lee el archivo completo y devuelve una lista
    public static List<Usuario> leerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        File file = new File(ARCHIVO);

        if (!file.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    // Crea el usuario con: username, password, nombre
                    lista.add(new Usuario(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return lista;
    }
}