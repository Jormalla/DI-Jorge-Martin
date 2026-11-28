package dam.di.gestores;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import dam.di.config.Config;
import dam.di.entidades.Cliente;
import dam.di.enums.Pago;
import dam.di.enums.Plan;
import dam.di.exceptions.ArchivoCorruptoException;

/**
 * 
 * GestorClientes
 * Clase que contiene los métodos para gestionar los clientes
 */
public class GestorClientes {

    public GestorClientes() {
    }

    /**
     * Método para comprobar si un dni es valido
     * 
     * @param dni dni que se quiere comprobar
     * @return devuelve true si es válido, false si no
     */
    public boolean validarDni(String dni) {
        if (dni == null) {
            return false;
        }

        dni = dni.trim().toUpperCase();

        // Comprueba que tenga 8 dígitos y una letra mayúscula de la A a la Z
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        // Coge los primeros 8 digitos del dni - coge la letra y comprueba si sería la
        // correcta
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        char letraQueCorresponde = Config.LETRAS_DNI.charAt(numero % 23);

        return letra == letraQueCorresponde;
    }

    /**
     * Método que comprueba si un valor de la bbdd de clientes es un booleano
     * 
     * @param valor string que queremos comprobar
     * @return true si es booleano, false si no
     */
    public boolean comprobarBooleanoClientes(String valor) {
        if ("true".equalsIgnoreCase(valor) || "false".equalsIgnoreCase(valor)) {
            return true;
        }
        return false;
    }

    /**
     * Método para comprobar si un IBAN es válido
     * 
     * @param iban iban que se quiere comprobar
     * @return devuelve true si es válido, false si no
     */
    public static boolean validarIban(String iban) {
        if (iban == null) {
            return false;
        }

        // Limpiar espacios y guiones, y pasar a mayúsculas
        iban = iban.replaceAll("[\\s-]", "").toUpperCase();

        // Comprueba longitud básica (mínimo 15 y máximo 34) y formato (2 letras + 2
        // números + resto alfanumérico)
        if (!iban.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}$")) {
            return false;
        }

        // Mueve los 4 primeros caracteres al final
        iban = iban.substring(4) + iban.substring(0, 4);

        // Convierte letras a números
        StringBuilder ibanNumerico = new StringBuilder();
        for (char caracter : iban.toCharArray()) {
            if (Character.isDigit(caracter)) {
                ibanNumerico.append(caracter);
            } else {
                // En ASCII "A" es 65. Restando 55 obtenemos A=10, B=11, ...
                ibanNumerico.append(caracter - 55);
            }
        }

        // Calcula el módulo 97. Si el resto es 1, el IBAN es válido
        BigInteger numero = new BigInteger(ibanNumerico.toString());
        return numero.mod(BigInteger.valueOf(97)).intValue() == 1;
    }

    /**
     * Método para cargar los clientes de un csv
     * Si no existe crea la ruta del csv. Utilizado como BBDD
     * 
     * @return devuelve la lista de clientes (vacía si el archivo no existe o no
     *         contiene datos)
     * @throws IOException              En caso de que no tenga permisos de lectura,
     *                                  escritua o ejecución
     * @throws ArchivoCorruptoException En caso de que el csv no contenga los
     *                                  parámetros pedidos
     */
    public List<Cliente> cargarClientes() throws IOException, ArchivoCorruptoException {
        List<Cliente> clientes = new ArrayList<>();
        Path archivo = Config.RUTA_BBDD_CLIENTES;

        // Comprueba si existe la ruta - la crea si no - devuelve lista vacía
        if (!Files.exists(archivo) || !Files.isRegularFile(archivo)) {
            Files.createDirectories(archivo.getParent());
            Files.createFile(archivo);
            // Escribe la cabecera en el csv
            try (BufferedWriter salida = Files.newBufferedWriter(
                    archivo, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                salida.write(Config.CABECERA_CLIENTES);
                salida.newLine();
            }
            return clientes;
        }
        // Abre el CSV y lo lee
        List<String> texto = Files.readAllLines(archivo, StandardCharsets.UTF_8);

        // ERROR - El archivo existe pero está completamente vacío (no tiene ni
        // cabecera)
        if (texto.isEmpty()) {
            throw new ArchivoCorruptoException("Error al cargar clientes.csv: El archivo está vacío");
        }

        String[] CABECERA = texto.get(0).split(";", -1);
        // ERROR - No tiene la cabecera esperada
        if (CABECERA.length != Config.NUM_COLUMNAS_CLIENTES) {
            throw new ArchivoCorruptoException("Error al cargar clientes.csv: No tiene la cabecera esperada");
        }
        String[] columnas;
        // Recorre el resto del csv
        for (int i = 1; i < texto.size(); i++) {
            columnas = texto.get(i).split(";", -1);
            int filaReal = i;

            // Comprueba que tenga el número de columnas esperado
            if (columnas.length != Config.NUM_COLUMNAS_CLIENTES) {
                throw new ArchivoCorruptoException(
                        "Error al cargar clientes.csv: La fila " + filaReal + " no contiene las columnas esperadas");
            }

            // Recoge los datos - crea el cliente - lo añade a la lista
            try {
                String nombre = columnas[0];
                String apellidos = columnas[1];
                String dni = columnas[2];

                // Comprueba que el dni sea válido
                if (!validarDni(dni)) {
                    throw new ArchivoCorruptoException(
                            "Error al cargar clientes.csv: La fila " + filaReal + " no contiene un dni posible");
                }

                String domicilio = columnas[3];
                int estatura = Integer.parseInt(columnas[4]);
                String foto = columnas[5];
                String valorActivo = columnas[6].trim();
                String valorPendiente = columnas[7].trim();

                // Comprueba si son booleanos
                if (!comprobarBooleanoClientes(valorActivo) || !comprobarBooleanoClientes(valorPendiente)) {
                    throw new ArchivoCorruptoException(
                            "Error al cargar clientes.csv: Se esperaba/n booleanos en la fila " + filaReal);
                }

                boolean activo = Boolean.parseBoolean(valorActivo);
                boolean pendiente = Boolean.parseBoolean(valorPendiente);
                String plan = columnas[8].trim().toUpperCase();

                // No tiene un plan posible
                if (!plan.toUpperCase().equals(Config.MENSUAL) && !plan.toUpperCase().equals(Config.DIARIO)
                        && !plan.toUpperCase().equals(Config.SEMANAL)) {
                    throw new ArchivoCorruptoException(
                            "Error al cargar clientes.csv: El cliente no tiene un plan posible. Fila: " + filaReal);
                }
                String pago = columnas[9].trim().toUpperCase();
                // No tiene un pago posible
                if (!pago.toUpperCase().equals(Config.EFECTIVO) && !pago.toUpperCase().equals(Config.TARJETA)
                        && !pago.toUpperCase().equals(Config.CUENTA)) {
                    throw new ArchivoCorruptoException(
                            "Error al cargar clientes.csv: El cliente no tiene una opción de pago posible. Fila: "
                                    + filaReal);
                }
                String iban = columnas[10];

                // No tiene un iban válido
                if (!validarIban(iban) && pago.equals(Config.CUENTA)) {
                    throw new ArchivoCorruptoException(
                            "Error al cargar clientes.csv: El cliente no tiene un IBAN válido. Fila: " + filaReal);
                }

                // Transforma el string a ruta
                Path rutaFoto;
                if (foto.isEmpty() || "null".equalsIgnoreCase(foto)) {
                    rutaFoto = null;
                } else {
                    rutaFoto = Path.of(foto);
                }
                // Transforma el plan y el pago
                Plan tipoPlan = Plan.valueOf(plan);
                Pago tipoPago = Pago.valueOf(pago);

                // CREA AL CLIENTE Y LO AÑADE A LA LISTA
                Cliente clienteCargado = new Cliente(nombre, apellidos, dni, rutaFoto, activo, pendiente, tipoPlan,
                        tipoPago);
                if (tipoPago == Pago.CUENTA) {
                    clienteCargado.setIban(iban);
                }

                if (estatura > -1) {
                    clienteCargado.setEstatura(estatura);
                }

                if (!domicilio.isEmpty() && !"null".equalsIgnoreCase(domicilio)) {
                    clienteCargado.setDomicilio(domicilio);
                }

                // Carga en la lista al cliente
                clientes.add(clienteCargado);

            } catch (NumberFormatException | InvalidPathException e) {
                throw new ArchivoCorruptoException(
                        "Error al cargar clientes.csv: Dato numérico o ruta de foto inválida en la fila " + filaReal);
            }
        }
        return clientes;
    }
}
