# Ficha de mi aplicación · Hito 0

- **Alumno/a:** Jorge Martín Llaneli
- **Temática:** Gestión de un gimnasio desde la perspectiva de un trabajador
- **Qué gestiona:** Gestiona los clientes de un gimnasio

## Campos

Al menos 7. Como mínimo: 2 de texto · 1 número entero · 1 número decimal · 1 opción de una lista · 1 opción entre 2 o 3 · 1 sí/no.

| # | Campo | Tipo de dato | Componente | Ejemplo de valor / opciones |
|---|-------|--------------|------------|-----------------------------|
| 1 | Trabajador | String | TextField | Trabajador1 |
| 2 | Contraseña | String | PasswordField | eswC%RZMNZXoRrn96789&g |
| 3 | Buscar Cliente | String | TextField | Jorge |
| 4 | Nombre | String | TextField | Jorge |
| 5 | Apellidos | String | TextField | Martín |
| 6 | DNI | String | TextField | 12345678-Z |
| 7 | Domicilio | String | TextField | Calle alcorcón 124412 |
| 8 | Estatura | Int | TextField | 177 |
| 9 | Activo | Boolean | checkBox | Inactivo |
| 10 | Pendiente | Boolean | checkBox | Pagado |
| 11 | Plan | Lista | Lista Desplegable | diario/semanal/mensual |
| 12 | Precio | double | TextField | 39.90 |
| 13 | Pago | boton | choiceBox | Efectivo/Tarjeta/Cuenta |
| 14 | IBAN | String | TextField | ES8300829956950656623405 |

Tipos de dato: `texto`, `entero`, `decimal`, `lista`, `opción`, `sí/no`.
Componentes: `campo de texto`, `selector numérico`, `lista desplegable`, `botones de opción`, `casilla`.

## Pantallas

Las fotos de tus bocetos, en la carpeta `bocetos/` con estos nombres (`.jpg` o `.png`):

| Pantalla | Foto |
|----------|------|
| Inicio de sesión | `bocetos/1_inicio_sesion.jpg` |
| Ventana principal | `bocetos/2_ventana_principal.jpg` |
| Formulario de alta | `bocetos/3_formulario.jpg` |

### Ventana principal

- **Menús y sus opciones:**
  - Cliente: Nuevo, Buscar
  - Ingresos
  - Ayuda
- **Columnas de la tabla:** Nombre, Apellidos, DNI, Plan, Estado del pago, Activo, Ver

### Qué hace cada botón

- Inicio de sesión › **Ingresar**: comprueba el trabajador y la contraseña y abre la ventana principal. **Crear**: abre un formulario para crear un trabajador.
- Ventana principal > **ChoiceBox**: Selecciona al cliente. **Papelera**: Elimina al cliente.
- Formulario Cliente > **Crear**: comprueba los datos, pregunta al usuario y crea el cliente y cierra el formulario. **Cancelar**: Pregunta al usuario y cierra el formulario. **Eliminar**: pregunta al usuario, elimina al cliente y cierra el formulario de edición.


### Mensajes

- Si el usuario o la contraseña están mal: «¡ERROR! Datos incorrectos. Revise el nombre del trabajador y la contraseña»
- Si falta un campo obligatorio en el formulario: «¡ERROR! Parece que 1 o más campos obligatorios están incompletos»
- Si no encuentra el cliente: «0 clientes encontrados con el nombre: nombreCliente»
- Si crea un nuevo cliente: «Se añadió a nombreCliente a la lista de clientes»
- Si elimina un cliente: «¿Estás seguro de querer eliminar a nombreCliente? Esta acción es irreversible»
- Si elimina un cliente confirma: «cliente nombreCliente eliminado»
- Si cancela la creación de un cliente: «Aun no se ha creado el cliente, ¿Quiéres cancelar esta acción?»
- Si algun dato del formulario no es correcto: «1 o más campos tienen información incorrecta. Revisa el formulario»
