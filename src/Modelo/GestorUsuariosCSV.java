package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuariosCSV {
    private static final String ARCHIVO = "usuarios.csv";

    public static void guardarUsuario(Usuario u) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(u.getUsername() + "," + u.getPassword() + "," + u.getNombre() + ",0");
            bw.newLine();
        }
    }

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
                if (datos.length >= 3) {
                    // Crea el usuario con: username, password, nombre
                    Usuario u = new Usuario(datos[0], datos[1], datos[2]);

                    if (datos.length == 4) {
                        u.setSaldoGuardado(Integer.parseInt(datos[3]));
                    }
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return lista;
    }

    public static void actualizarSaldoUsuario(String username, int nuevoSaldo) {
        List<String> lineas = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(username) && datos.length >= 3) {
                    lineas.add(datos[0] + "," + datos[1] + "," + datos[2] + "," + nuevoSaldo);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo para actualizar: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }
}