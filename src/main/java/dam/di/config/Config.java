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

    // =========== USUARIO =========== //
    public static final String USUARIO_DEFAULT = "Admin";

    // ============ RUTAS ============ //
    public static final Path RUTA_BBDD_TRABAJADORES = Path.of("DI-Jorge-Martin", "src", "main", "java", "dam", "di",
            "datos", "trabajadores.dat");
    public static final Path RUTA_BBDD_CLIENTES = Path.of("DI-Jorge-Martin", "src", "main", "java", "dam", "di",
            "datos", "clientes.csv");
    public static final Path RUTA_LOGS_REGISTROS = Path.of("DI-Jorge-Martin", "src", "main", "java", "dam", "di",
            "logs",
            "registros.log");
    public static final Path RUTA_LOGS_ERRORES = Path.of("DI-Jorge-Martin", "src", "main", "java", "dam", "di", "logs",
            "error.log");
}
