package dam.di.gestores;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import dam.di.config.Config;
import dam.di.entidades.Cliente;
import dam.di.entidades.Trabajador;
import dam.di.exceptions.ArchivoCorruptoException;

/**
 * 
 * GestorClientes
 * Clase que contiene los métodos para gestionar los clientes
 */
public class GestorClientes {
    
    public GestorClientes(){
    }

    /**
     * Método para comprobar si un dni es valido
     * @param dni dni que se quiere comprobar
     * @return devuelve true si es válido, false si no
     */
    public static boolean validarDni(String dni) {
        if (dni == null) {
            return false;
        }

        dni = dni.trim().toUpperCase();

        // Comprueba que tenga 8 dígitos y una letra mayúscula de la A a la Z
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        // Coge los primeros 8 digitos del dni - coge la letra y comprueba si sería la correcta
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        char letraQueCorresponde = Config.LETRAS_DNI.charAt(numero % 23);

        return letra == letraQueCorresponde;
    }

    
    public List<Cliente> cargarClientes() throws IOException, ArchivoCorruptoException{
        List<Cliente> clientes = new ArrayList<>();
        Path archivo = Config.RUTA_BBDD_CLIENTES;

        // Comprueba si existe la ruta - la crea si no - devuelve lista vacía
        if (!Files.exists(archivo) || !Files.isRegularFile(archivo)) {
            Files.createDirectories(archivo.getParent());
            Files.createFile(archivo);
            return clientes;
        }
        // Abre el CSV y lo lee
        List<String> texto = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        String[] CABECERA = texto.get(0).split(";", -1);
        // ERROR - No tiene la cabecera esperada
        if (CABECERA.length != Config.NUM_COLUMNAS_CLIENTES) {
            throw new ArchivoCorruptoException("Error al cargar clientes.csv: No tiene la cabecera esperada");
        }
        String[] columnas;
        // Recorre el resto del csv
        for (int i = 1; i < texto.size(); i++) {
            columnas = texto.get(i).split(";", -1);
            int filaReal = i + 1;

            // Comprueba que tenga el número de columnas esperado
            if (columnas.length != Config.NUM_COLUMNAS_CLIENTES) {
                throw new ArchivoCorruptoException("Error al cargar clientes.csv: La fila " + filaReal + " no contiene las columnas esperadas");
            }

            // Recoge los datos - crea el cliente - lo añade a la lista
            try {
                String nombre = columnas[0];
                String apellidos = columnas[1];
                String dni = columnas[2];

                // Comprueba que el dni sea válido
                if (!validarDni(dni)) {
                    throw new ArchivoCorruptoException("Error al cargar clientes.csv: La fila " + filaReal + " no contiene un dni posible");
                }

                String domicilio = columnas[3];
                int estatura = Integer.parseInt(columnas[4]);
                String foto = columnas[5];
                boolean activo = Boolean.parseBoolean(columnas[6]);
                
            } catch (NumberFormatException e) {
                throw new ArchivoCorruptoException("Error al cargar clientes.csv: La fila " + filaReal + " no contiene un numero en estatura");
            }
        }
    }
}
