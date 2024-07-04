package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author Axel
 */
public class InterGestionarClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form InterGestionarUsuarios
     */
    public InterGestionarClientes() {
        initComponents();
        this.setSize(400, 300);
        this.setTitle("Gestionar Clientes");
        
        jButton_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete al menos uno de los campos (Nombre o E-mail).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    buscarCliente();
                }
            }
        });

        jButton_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete al menos uno de los campos (Nombre o E-mail).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    eliminarCliente();
                }
            }
        });

        jButton_ver_productos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTodosLosClientes();
            }
        });

        jButton_editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete al menos uno de los campos (Nombre o E-mail).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    editarCliente();
                }
            }
        });
    }

    private boolean verificarCamposVacios() {
        return txt_nombre.getText().trim().isEmpty() && txt_email.getText().trim().isEmpty();
    }

    private void buscarCliente() {
        String nombreBuscado = txt_nombre.getText().trim();
        String emailBuscado = txt_email.getText().trim();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/clientes.txt"))) {
            String linea;
            StringBuilder cliente = new StringBuilder();
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains("Nombre: " + nombreBuscado) || linea.contains("E-mail: " + emailBuscado)) {
                    encontrado = true;
                    cliente.append(linea).append("\n");
                    for (int i = 0; i < 4; i++) { // Leer las siguientes líneas del cliente
                        cliente.append(br.readLine()).append("\n");
                    }
                    break;
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, cliente.toString(), "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        String nombreBuscado = txt_nombre.getText().trim();
        String emailBuscado = txt_email.getText().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("resources/clientes.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("resources/clientes_temp.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Nombre: " + nombreBuscado) || linea.contains("E-mail: " + emailBuscado)) {
                    encontrado = true;
                    for (int i = 0; i < 5; i++) { // Saltar las siguientes líneas del cliente
                        br.readLine();
                    }
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al modificar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Actualizar archivo original
        new java.io.File("resources/clientes.txt").delete();
        new java.io.File("resources/clientes_temp.txt").renameTo(new java.io.File("resources/clientes.txt"));
    }

    private void verTodosLosClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("resources/clientes.txt"))) {
            String linea;
            StringBuilder clientes = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                clientes.append(linea).append("\n");
            }

            JOptionPane.showMessageDialog(this, clientes.toString(), "Todos los Clientes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarCliente() {
        String nombreBuscado = txt_nombre.getText().trim();
        String emailBuscado = txt_email.getText().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("resources/clientes.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("resources/clientes_temp.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Nombre: " + nombreBuscado) || linea.contains("E-mail: " + emailBuscado)) {
                    encontrado = true;
                    String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo Nombre:", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    String nuevaDireccion = JOptionPane.showInputDialog(this, "Nueva Dirección:", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoCP = JOptionPane.showInputDialog(this, "Nuevo Código Postal:", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoTelefono = JOptionPane.showInputDialog(this, "Nuevo Teléfono:", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoEmail = JOptionPane.showInputDialog(this, "Nuevo E-mail:", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);

                    bw.write("Nombre: " + nuevoNombre);
                    bw.newLine();
                    bw.write("Dirección: " + nuevaDireccion);
                    bw.newLine();
                    bw.write("Código Postal: " + nuevoCP);
                    bw.newLine();
                    bw.write("Teléfono: " + nuevoTelefono);
                    bw.newLine();
                    bw.write("E-mail: " + nuevoEmail);
                    bw.newLine();
                    bw.write("------------------------------");
                    bw.newLine();

                    for (int i = 0; i < 5; i++) { // Saltar las siguientes líneas del cliente antiguo
                        br.readLine();
                    }
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, "Cliente editado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al modificar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Actualizar archivo original
        new java.io.File("resources/clientes.txt").delete();
        new java.io.File("resources/clientes_temp.txt").renameTo(new java.io.File("resources/clientes.txt"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jButton_buscar = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_ver_productos = new javax.swing.JButton();
        jButton_editar = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestionar Clientes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 150, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("E-mail:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 170, -1));

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 170, -1));

        jButton_buscar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_buscar.setText("Buscar");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 95, 30));

        jButton_eliminar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_eliminar.setText("Eliminar");
        getContentPane().add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 95, 30));

        jButton_ver_productos.setBackground(new java.awt.Color(0, 204, 204));
        jButton_ver_productos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_ver_productos.setText("Ver todos los clientes");
        jButton_ver_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ver_productosActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_ver_productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 200, 30));

        jButton_editar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_editar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_editar.setText("Editar información");
        jButton_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 200, 30));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ver_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ver_productosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ver_productosActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void jButton_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_editarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JButton jButton_editar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_ver_productos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
