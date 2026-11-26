package dam.di.entidades;

import java.io.Serializable;

/**
 * Trabajador
 * Clase que representa aun trabajador del gimnasio. Con sus datos (usuario,
 * contrasenia y si es jefe o no) se ingresa en el programa
 */
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L; // Esto es por si hago cambios en la clase que no pete .dat
    private String usuario;
    private String contrasenia;
    private boolean jefe;

    // ========== CONSTRUCTOR =============
    public Trabajador(String usuario, String contrasenia, boolean jefe) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.jefe = jefe;
    }

    // ========= GETTERS & SETTERS ===========
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isJefe() {
        return jefe;
    }

    public void setJefe(boolean jefe) {
        this.jefe = jefe;
    }
}
