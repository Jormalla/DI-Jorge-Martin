package dam.di.gestores;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dam.di.config.Config;
import dam.di.entidades.Trabajador;

/**
 * 
 * GestorTrabajadores
 * Clase para gestionar la carga de datos de los trabajadores con sus
 * contraseñas y su creación
 */
public class GestorTrabajadores {

    /**
     * CONSTRUCTOR
     */
    public GestorTrabajadores() {
    }

    /**
     * MÉTODO que carga todos los trabajadores del centro deportivo
     * 
     * @return devuelve una lista de trabajadores
     * @throws IOException No dispone de los permisos para crear las carpetas o
     *                     archivos
     */
    public List<Trabajador> cargarTrabajadores() throws IOException {
        List<Trabajador> trabajadores = new ArrayList<>();
        Path archivo = Config.RUTA_BBDD_TRABAJADORES;
        // NO EXISTE LA RUTA DE LA BBDD - LA CREA
        if (!Files.exists(archivo) || !Files.isRegularFile(archivo)) {
            Files.createDirectories(archivo.getParent());
            Files.createFile(archivo);
            // Guarda el archivo con el usuario y contraseña por defecto
            trabajadores.add(new Trabajador(Config.USUARIO_DEFAULT, Config.CONTRASENIA_DEFAULT, true));
            guardarTrabajadores(trabajadores);
            return trabajadores;
            // EXISTE EL ARCHIVO - CARGA LOS TRABAJADORES
        } else {
            HashSet<String> nombresUsuario = new HashSet<>();

            try (ObjectInputStream entrada = new ObjectInputStream(
                    Files.newInputStream(archivo))) {
                Object dato = entrada.readObject();
                if (!(dato instanceof List<?> lista)) {
                    throw new IOException("El archivo no contiene una lista.");
                }
                for (Object elemento : lista) {
                    if (!(elemento instanceof Trabajador trabajador)) {
                        throw new IOException("Se ha encontrado un dato inesperado.");
                    }
                    // Comprueba si hay al menos 1 nombre de usuario duplicado está duplicado
                    if (!nombresUsuario.add(trabajador.getUsuario())) {
                        throw new IOException("Se ha encontrado al menos un usuario duplicado.");
                    }
                    trabajadores.add(trabajador);
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("No se pudo recuperar: " + e.getMessage());
            }
            return trabajadores;
        }
    }

    /**
     * MÉTODO que permite actualizar el archivo donde se guardan los usuarios y
     * contraseñas de los trabajadores
     * 
     * @param lista lista de trabajadores
     * @throws IOException lanza excepción si no tiene permisos el usuario.
     */
    public void guardarTrabajadores(List<Trabajador> lista) throws IOException {
        Files.createDirectories(Config.RUTA_BBDD_TRABAJADORES.getParent());
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(Config.RUTA_BBDD_TRABAJADORES))) {
            salida.writeObject(lista);
        }
    }

    /**
     * MÉTODO para buscar un trabajador por nombre de usuario
     * 
     * @return devuelve el trabajador o en su defecto null
     * @throws IOException En caso de que no se tengan permisos o el archivo esté
     *                     corrupto
     */
    public Trabajador buscarTrabajador(String nombreUsuario) throws IOException {
        List<Trabajador> trabajadores = cargarTrabajadores();
        // Recorre todos los trabajadores y si lo encuentra lo devuelve
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getUsuario().equals(nombreUsuario)) {
                return trabajador;
            }
        }
        return null;
    }

    /**
     * MÉTODO que permite comprobar si las credenciales introducidas son correctas
     * 
     * @return devuelve true si usuario y contraseña son correctos, de lo contrario
     *         true
     * @throws IOException salta excepcion por error de permisos o archivo corrupto
     */
    public boolean credencialesCorrectas(String usuario, String contrasenia) throws IOException {
        Trabajador trabajador = buscarTrabajador(usuario);
        if (trabajador.getUsuario().equals(usuario) && trabajador.getContrasenia().equals(contrasenia)) {
            return true;
        }
        return false;
    }
}
