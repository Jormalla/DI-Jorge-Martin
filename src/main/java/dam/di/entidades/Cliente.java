package dam.di.entidades;

import java.nio.file.Path;

import dam.di.enums.Pago;
import dam.di.enums.Plan;

/**
 * 
 * Cliente
 * Entidad que representa un cliente del gimnasio
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private String dni;
    private String domicilio;
    private int estatura;
    private Path rutaFoto;
    private boolean activo;
    private boolean pendiente;
    private Plan plan;
    private Pago pago;
    private String iban = null;

    // ========= CONSTRUCTOR ============ //
    public Cliente(String nombre, String apellidos, String dni, String domicilio, int estatura, Path rutaFoto,
            boolean activo, boolean pendiente, Plan plan, Pago pago) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.domicilio = domicilio;
        this.estatura = estatura;
        this.rutaFoto = rutaFoto;
        this.activo = activo;
        this.pendiente = pendiente;
        this.plan = plan;
        this.pago = pago;
    }

    // ============ GETTERS & SETTERS ============= //
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public Path getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(Path rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    
}
