/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dam.di;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dam.di.config.Config;
import dam.di.disenios.Componentes;
import dam.di.entidades.Cliente;
import dam.di.enums.Plan;
import dam.di.exceptions.ArchivoCorruptoException;
import dam.di.gestores.GestorClientes;

/**
 *
 * @author DAM
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(VentanaPrincipal.class.getName());

    private List<Cliente> clientes = new ArrayList<>();

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        // DETECTA EL CLICK EN LA TABLA
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });

        setLocationRelativeTo(null);
        Componentes.aplicarTema(this);
        // CARGA LOS CLIENTES
        GestorClientes gestor = new GestorClientes();
        try {
            clientes = gestor.cargarClientes();
            completarTabla(clientes);
            // ERROR - LA APLICACIÓN NO TIENE PERMISOS
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, Config.MSG_ERROR_PERMISOS,
                    Config.MSG_TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
            // ERROR - EL ARCHIVO ESTÁ CORRUPTO
        } catch (ArchivoCorruptoException ex) {
            JOptionPane.showMessageDialog(this, Config.MSG_CLIENTES_CORRUPTO,
                    Config.MSG_TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }

    }

    /**
     * Método propio de la clase para cargar los clientes en la propia tabla
     * 
     * @param clientes lista de clientes
     */
    private void completarTabla(List<Cliente> clientes) {
        DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
        modelo.setRowCount(0); // quita la fila vacía que crea NetBeans
        for (Cliente cliente : clientes) {
            modelo.addRow(new Object[] {
                    cliente.getNombre(),
                    cliente.getApellidos(),
                    cliente.getDni(),
                    cliente.getPlan().name(),
                    cliente.isPendiente() ? "POR PAGAR" : "PAGADO",
                    cliente.isActivo() ? "ACTIVO" : "INACTIVO",
                    false, // botón choiceBox para ver
                    "🗑" // texto que hará de botón para eliminar
            });
        }
    }

    // ============ CLIC EN LA TABLA ============ //
    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {
        // Fila y columna donde se ha hecho clic
        int fila = tablaClientes.rowAtPoint(evt.getPoint());
        int columna = tablaClientes.columnAtPoint(evt.getPoint());

        // Compruebo que no se haya hecho click en una zona vacía (obtiene el valor -1)
        if (fila < 0) {
            return;
        }

        // Guarda los datos de la tabla
        DefaultTableModel datosTabla = (DefaultTableModel) tablaClientes.getModel();

        // ===== CLIC EN LA CASILLA - PARA EDITAR =====
        if (columna == Config.COL_EDITAR) {
            boolean yaMarcada = (boolean) datosTabla.getValueAt(fila, Config.COL_EDITAR);
            // Pone true solo en la fila pulsada y false en las demás, para que solo haya 1.
            // Si ya estaba marcada, la desmarca
            for (int i = 0; i < datosTabla.getRowCount(); i++) {
                datosTabla.setValueAt(i == fila && !yaMarcada, i, Config.COL_EDITAR);
            }

            // ===== CLIC EN LA PAPELERA - PARA ELIMINAR =====
        } else if (columna == Config.COL_BORRAR) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Deseas eliminar a " + clientes.get(fila).getNombre() + " " + clientes.get(fila).getApellidos()
                            + "?\nEste cambio es irreversible",
                    Config.MSG_TITULO_CONFIRMAR, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Si quiere eliminar - lo elimina
            if (respuesta == JOptionPane.YES_OPTION) {
                clientes.remove(fila); // quita al cliente de la lista
                completarTabla(clientes); // vuelve a pintar la tabla
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zonaTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        zonaNorte = new javax.swing.JPanel();
        tfbuscador = new javax.swing.JTextField();
        zonaBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        clienteMenu = new javax.swing.JMenu();
        nuevoSubMenu = new javax.swing.JMenuItem();
        editarClienteSubMenu = new javax.swing.JMenuItem();
        ingresosMenu = new javax.swing.JMenu();
        planesSubMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        ayudaMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        zonaTabla.setLayout(new java.awt.BorderLayout());

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null, null, null }
                },
                new String[] {
                        "Nombre", "Apellidos", "DNI", "Plan", "Estado del pago", "Activo", "Ver", ""
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        zonaTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(zonaTabla, java.awt.BorderLayout.CENTER);

        zonaNorte.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tfbuscador.setColumns(30);
        tfbuscador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfbuscador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfbuscador.setText("Buscar Cliente ...");
        tfbuscador.setMaximumSize(new java.awt.Dimension(70, 70));
        tfbuscador.setMinimumSize(new java.awt.Dimension(70, 70));
        tfbuscador.addActionListener(this::tfbuscadorActionPerformed);
        zonaNorte.add(tfbuscador);

        getContentPane().add(zonaNorte, java.awt.BorderLayout.PAGE_START);

        zonaBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        zonaBotones.add(btnSalir);

        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);
        zonaBotones.add(btnCerrarSesion);

        getContentPane().add(zonaBotones, java.awt.BorderLayout.PAGE_END);

        clienteMenu.setText("Cliente");

        nuevoSubMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        nuevoSubMenu.setText("Nuevo");
        nuevoSubMenu.addActionListener(this::nuevoSubMenuActionPerformed);
        clienteMenu.add(nuevoSubMenu);

        editarClienteSubMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        editarClienteSubMenu.setText("Editar");
        clienteMenu.add(editarClienteSubMenu);

        barraMenu.add(clienteMenu);

        ingresosMenu.setText("Ingresos");

        planesSubMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        planesSubMenu.setText("Planes");
        planesSubMenu.addActionListener(this::planesSubMenuActionPerformed);
        ingresosMenu.add(planesSubMenu);

        jMenuItem1.setText("Estadísticas");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        ingresosMenu.add(jMenuItem1);

        barraMenu.add(ingresosMenu);

        ayudaMenu.setText("Ayuda");
        barraMenu.add(ayudaMenu);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoSubMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nuevoSubMenuActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_nuevoSubMenuActionPerformed

    private void planesSubMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_planesSubMenuActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_planesSubMenuActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    private void tfbuscadorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tfbuscadorActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tfbuscadorActionPerformed

    // =============== SALIR ================= //
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalirActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de salir?",
                Config.MSG_TITULO_CONFIRMAR, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }// GEN-LAST:event_btnSalirActionPerformed

    // ============= CERRAR SESIÓN =============== //
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarSesionActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "Se cerrará la sesión\n¿Estás seguro?",
                Config.MSG_TITULO_CONFIRMAR, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            new VentanaInicioSesion().setVisible(true);
            dispose();
        }
    }// GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ayudaMenu;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenu clienteMenu;
    private javax.swing.JMenuItem editarClienteSubMenu;
    private javax.swing.JMenu ingresosMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem nuevoSubMenu;
    private javax.swing.JMenuItem planesSubMenu;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField tfbuscador;
    private javax.swing.JPanel zonaBotones;
    private javax.swing.JPanel zonaNorte;
    private javax.swing.JPanel zonaTabla;
    // End of variables declaration//GEN-END:variables
}
