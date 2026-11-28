package dam.di.config;

import java.nio.file.Path;

/**
 * 
 * Config
 * Esta clase tiene las configuraciones globales del programa
 */
public class Config {
    // =========== CONTRASEÑA ========= //
    public static final int MAX_INTENTOS_CONTRASENIA = 3;
    public static final String CONTRASENIA_DEFAULT = "1234";
    public static final String PLACEHOLDER_CONTRASENIA = "Place holder contraseña";

    // =========== USUARIO =========== //
    public static final String USUARIO_DEFAULT = "Admin";
    public static final String PLACEHOLDER_USUARIO = "Usuario ...";
    public static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    // ============ RUTAS ============ //
    public static final Path RUTA_BBDD_TRABAJADORES = Path.of("datos", "trabajadores.dat");
    public static final Path RUTA_BBDD_CLIENTES = Path.of("datos", "clientes.csv");
    public static final Path RUTA_LOGS_REGISTROS = Path.of("logs", "registros.log");
    public static final Path RUTA_LOGS_ERRORES = Path.of("logs", "error.log");

    // ========== CLIENTES =========== //
    public static final String CABECERA_CLIENTES = "NOMBRE;APELLIDOS;DNI;DOMICILIO;ESTATURA;FOTO;ACTIVO;PENDIENTE;PLAN;PAGO;IBAN";
    public static final int NUM_COLUMNAS_CLIENTES = CABECERA_CLIENTES.trim().split(";").length;

    // ============ MENSAJES ALERTAS =========== //
    public static final String MSG_TITULO_ERROR = "ERROR";
    public static final String MSG_TITULO_FALTAN_DATOS = "FALTAN DATOS";
    public static final String MSG_TITULO_CONFIRMAR = "CONFIRMAR";
    public static final String MSG_TITULO_EXITO = "OPERACIÓN EXITOSA";
    public static final String MSG_DATOS_INCOMPLETOS = "Datos incompletos";
    public static final String MSG_FALTA_USUARIO_CONTRASENIA = "Escribe el usuario y la contraseña";
    public static final String MSG_USUARIO_EXISTENTE = "Ya existe un trabajador con ese usuario";
    public static final String MSG_CONTRASENIA_REPETIDA_DIFERENTE = "Los campos de las contraseñas no coinciden";
    public static final String MSG_EXITO_USUARIO_CREADO = "El trabajador se creó con éxito";
    public static final String MSG_TRABAJADORES_CORRUPTO = "Error al cargar trabajadores.dat. El archivo está corrupto o no sigue los parámetros";
    public static final String MSG_ERROR_PERMISOS = "Hubo un error en los permisos de la aplicación. Revise y modifique los permisos";
    public static final String MSG_CLIENTES_CORRUPTO = "Error al cargar clientes.csv. El archivo está corrupto o no sigue los parámetros";

    // ============ PAGOS ========== //
    public static final String EFECTIVO = "EFECTIVO";
    public static final String TARJETA = "TARJETA";
    public static final String CUENTA = "CUENTA";

    // ============ PLANES =========== //
    public static final String DIARIO = "DIARIO";
    public static final String SEMANAL = "SEMANAL";
    public static final String MENSUAL = "MENSUAL";
}
