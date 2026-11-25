package dam.di.disenios;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
        campo.setForeground(Color.decode(ColorDisenio.GRIS));

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
                    campo.setForeground(Color.decode(ColorDisenio.GRIS));
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
        campo.setForeground(Color.decode(ColorDisenio.GRIS));

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
                    campo.setForeground(Color.decode(ColorDisenio.GRIS));
                    campo.setText(textoAyuda);
                }
            }
        });
    }
}
