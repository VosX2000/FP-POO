package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author Axel
 */
public class VentanaUsuarios extends javax.swing.JFrame {
    private JTextField txtId, txtNombre, txtCargo;
    private JButton btnAgregar, btnEditar, btnEliminar, btnListar;
    private JTextArea textArea;
    private final String archivoEmpleados = "empleados.txt";
    /**
     * Creates new form VentanaUsuarios
     */
    public VentanaUsuarios() {
        initComponents();
        setTitle("Administrar Empleados");
        setSize(600, 600);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        // Fila 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("ID Empleado:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtId = new JTextField(15);
        add(txtId, gbc);

        // Fila 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombre = new JTextField(15);
        add(txtNombre, gbc);

        // Fila 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Cargo:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCargo = new JTextField(15);
        add(txtCargo, gbc);

        // Fila 4 - Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotones, gbc);

        // Fila 5 - Área de texto
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        textArea = new JTextArea(10, 40);
        add(new JScrollPane(textArea), gbc);

        // Acción de los botones
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarEmpleado();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarEmpleados();
            }
        });

        setVisible(true);
    }
    
    private void agregarEmpleado() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String cargo = txtCargo.getText();

        Usuario usuario = new Usuario(id, nombre, cargo);
        List<String> empleados = ManejadorArchivos.leerArchivo(archivoEmpleados);
        empleados.add(empleado.toString());
        ManejadorArchivos.escribirArchivo(archivoEmpleados, empleados);

        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarEmpleado() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String cargo = txtCargo.getText();

        List<String> empleados = ManejadorArchivos.leerArchivo(archivoEmpleados);
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            String[] datos = empleados.get(i).split(",");
            if (datos[0].equals(id)) {
                empleados.set(i, new Empleado(id, nombre, cargo).toString());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            ManejadorArchivos.escribirArchivo(archivoEmpleados, empleados);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Empleado editado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpleado() {
        String id = txtId.getText();

        List<String> empleados = ManejadorArchivos.leerArchivo(archivoEmpleados);
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            String[] datos = empleados.get(i).split(",");
            if (datos[0].equals(id)) {
                empleados.remove(i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            ManejadorArchivos.escribirArchivo(archivoEmpleados, empleados);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarEmpleados() {
        List<String> empleados = ManejadorArchivos.leerArchivo(archivoEmpleados);
        textArea.setText("");
        for (String empleado : empleados) {
            textArea.append(empleado + "\n");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCargo.setText("");
    }

    public static void main(String[] args) {
        new VentanaEmpleados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
