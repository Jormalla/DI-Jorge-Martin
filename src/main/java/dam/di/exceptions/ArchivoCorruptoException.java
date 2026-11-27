package dam.di.exceptions;

/**
 * 
 * ArchivoCorruptoException
 * Clase exception para crear exceptions en caso de que algún archivo esté dañado o no siga el formato
 */
public class ArchivoCorruptoException extends Exception {
    public ArchivoCorruptoException(String mensaje) {
        super(mensaje);
    }
}
