package dam.di.entidades;

/**
 * Trabajador
 * Clase que representa aun trabajador del gimnasio. Con sus datos (usuario,
 * contrasenia) se ingresa en el programa
 */
public class Trabajador {
    private String usuario;
    private String contrasenia;

    // ========== CONSTRUCTOR =============
    public Trabajador(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
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
}
