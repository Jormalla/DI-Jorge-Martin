package dam.di.disenios;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * Componentes
 * Clase para crear los diseños de algunos componentes y acciones
 */
public class Componentes {

    /**
     * Metodo para crear un texto por defecto en un textField y se borra cuando se
     * escribe sobre él
     * 
     * @param campo      campo TextField al que aplicar el diseño
     * @param textoAyuda texto de ayuda que aparecerá
     */
    public static void ponerPlaceholder(JTextField campo, String textoAyuda) {
        campo.setText(textoAyuda);
        campo.setForeground(Color.decode(ColorDisenio.BLANCO));

        // Evento al entrar o salir del campo
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Si tiene el texto de ayuda, lo borra y cambia el color a negro
                if (campo.getText().equals(textoAyuda)) {
                    campo.setText("");
                    campo.setForeground(Color.decode(ColorDisenio.NEGRO));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el usuario sale y lo dejó vacío, vuelve a poner la ayuda
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.decode(ColorDisenio.BLANCO));
                    campo.setText(textoAyuda);
                }
            }
        });
    }

    /**
     * Metodo para crear un texto por defecto en un PasswordField y se borra cuando
     * se escribe sobre él
     * 
     * @param campo
     * @param textoAyuda
     */
    public static void ponerPlaceholder(JPasswordField campo, String textoAyuda) {
        campo.setText(textoAyuda);
        campo.setForeground(Color.decode(ColorDisenio.BLANCO));

        // Evento al entrar o salir del campo
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Si tiene el texto de ayuda, lo borra y cambia el color a negro
                if (new String(campo.getPassword()).equals(textoAyuda)) {
                    campo.setText("");
                    campo.setForeground(Color.decode(ColorDisenio.NEGRO));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el usuario sale y lo dejó vacío, vuelve a poner la ayuda
                if (new String(campo.getPassword()).isEmpty()) {
                    campo.setForeground(Color.decode(ColorDisenio.BLANCO));
                    campo.setText(textoAyuda);
                }
            }
        });
    }

    /**
     * Aplica a todos los componentes los colores del programa
     * <p>
     * {@link javax.swing.JOptionPane}.
     * </p>
     *
     * @param contenedor El contenedor raíz donde se aplicara el estilo
     */
    public static void aplicarTema(Container contenedor) {
        // Recorre todos los elementos directos dentro del contenedor actual
        for (Component componente : contenedor.getComponents()) {

            // Aplica el color de fondo base a los paneles
            if (componente instanceof JPanel) {
                componente.setBackground(Color.decode(ColorDisenio.GRIS_BASE));

                // Aplica el color de texto a los labels
            } else if (componente instanceof JLabel) {
                componente.setForeground(Color.decode(ColorDisenio.GRIS_LABELS));

                // Aplica fondo y letra a los botones
            } else if (componente instanceof JButton) {
                componente.setBackground(Color.decode(ColorDisenio.NEGRO));
                componente.setForeground(Color.decode(ColorDisenio.BLANCO));
                // Aplica el tema al textfield
            } else if (componente instanceof JTextField) {
                componente.setBackground(Color.decode(ColorDisenio.GRIS_TEXTFIELD));
                componente.setForeground(Color.decode(ColorDisenio.BLANCO));
            }

            // Si el componente actual tiene otros elementos dentro, se llama de nuevo al
            // método para decorar también sus componentes hijos
            if (componente instanceof Container) {
                aplicarTema((Container) componente);
            }
        }

    }
}
