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
public class InterGestionarProveedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form InterGestionarProducto
     */
    public InterGestionarProveedores() {
        initComponents();
        this.setSize(400, 300);
        this.setTitle("Gestionar Proveedores");

        jButton_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    buscarProducto();
                }
            }
        });

        jButton_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposVacios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    eliminarProducto();
                }
            }
        });

        jButton_ver_productos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTodosLosProductos();
            }
        });
    }

    private boolean verificarCamposVacios() {
        return jTextField1.getText().trim().isEmpty();
    }

    private void buscarProducto() {
        String nombreBuscado = jTextField1.getText().trim();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/proveedores.txt"))) {
            String linea;
            StringBuilder producto = new StringBuilder();
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains("Proveedor: " + nombreBuscado)) {
                    encontrado = true;
                    producto.append(linea).append("\n");
                    for (int i = 0; i < 4; i++) { // Leer las siguientes líneas del producto
                        producto.append(br.readLine()).append("\n");
                    }
                    break;
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, producto.toString(), "Proveedor Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        String nombreBuscado = jTextField1.getText().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("resources/proveedores.txt")); BufferedWriter bw = new BufferedWriter(new FileWriter("resources/proveedores_temp.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Proveedor: " + nombreBuscado)) {
                    encontrado = true;
                    // Saltar las siguientes líneas del producto
                    for (int i = 0; i < 4; i++) {
                        br.readLine();
                    }
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al modificar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Actualizar archivo original solo si se encontró y eliminó el proveedor
        if (encontrado) {
            new java.io.File("resources/proveedores.txt").delete();
            new java.io.File("resources/proveedores_temp.txt").renameTo(new java.io.File("resources/proveedores.txt"));
        }
    }

    private void verTodosLosProductos() {
        try (BufferedReader br = new BufferedReader(new FileReader("resources/proveedores.txt"))) {
            String linea;
            StringBuilder productos = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                productos.append(linea).append("\n");
            }

            JOptionPane.showMessageDialog(this, productos.toString(), "Todos los Proveedores", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        jTextField1 = new javax.swing.JTextField();
        jButton_buscar = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_ver_productos = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestionar Proveedores");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 100, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 170, -1));

        jButton_buscar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_buscar.setText("Buscar");
        getContentPane().add(jButton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 90, 30));

        jButton_eliminar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 90, 30));

        jButton_ver_productos.setBackground(new java.awt.Color(0, 204, 204));
        jButton_ver_productos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_ver_productos.setText("Ver todos los proveedores");
        jButton_ver_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ver_productosActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_ver_productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 220, 30));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ver_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ver_productosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ver_productosActionPerformed

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_ver_productos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
