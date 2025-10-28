/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.musicup;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carlc
 */
public class CreatePlaylistFrame extends javax.swing.JFrame {

    ArrayList<Playlist> playlists = new ArrayList<>();
    
    public CreatePlaylistFrame() {
        initComponents();
        
        this.getContentPane().setBackground(new Color(43, 35, 59));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Carga las playlists de la BD al ArrayList
        cargarPlaylistsDesdeDB(); 
        // Dibuja la UI usando el ArrayList
        mostrarPlaylistsEnUI();
        
    }
    
    private void cargarPlaylistsDesdeDB() {
        // 1. Limpiamos la lista en memoria para no duplicar datos en cada carga
        playlists.clear(); 

        String sql = "SELECT idplaylist, namePlaylist FROM playlist ORDER BY namePlaylist";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // 2. Recorremos los resultados de la base de datos
            while (rs.next()) {
                int id = rs.getInt("idplaylist");
                String nombre = rs.getString("namePlaylist");

                // 3. Creamos un objeto Playlist (usando el nuevo constructor)
                //    y lo añadimos al ArrayList
                playlists.add(new Playlist(id, nombre));
            }

            System.out.println("Playlists cargadas desde la BD: " + playlists.size());

        } catch (SQLException e) {
            System.out.println("Error al cargar las playlists: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar las playlists.");
        }
    }
    
    private void mostrarPlaylistsEnUI() {
        // 1. Limpiamos el panel contenedor de cualquier cosa que tuviera antes
        panelContenedorPlaylists.removeAll();

        // 2. Recorremos el ArrayList de playlists
        for (Playlist playlist : playlists) {

            // 3. Por cada playlist, creamos un panel idéntico al que diseñaste

            // --- Creamos el Panel Fila ---
            javax.swing.JPanel panelFila = new javax.swing.JPanel();
            panelFila.setBackground(new java.awt.Color(126, 105, 168));
            // Definimos un tamaño máximo para que no se estire verticalmente
            panelFila.setMaximumSize(new java.awt.Dimension(32767, 45)); 
            panelFila.setMinimumSize(new java.awt.Dimension(337, 45));

            // --- Creamos el Botón Editar ---
            javax.swing.JButton btnEditar = new javax.swing.JButton();
            btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\editar.png"));
            btnEditar.setOpaque(false);
            btnEditar.setContentAreaFilled(false);
            btnEditar.setBorderPainted(false);

            // --- Creamos la Etiqueta con el Nombre ---
            javax.swing.JLabel lblNombre = new javax.swing.JLabel();
            lblNombre.setFont(new java.awt.Font("Arial", 1, 14));
            lblNombre.setForeground(new java.awt.Color(242, 242, 242));
            lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNombre.setText(playlist.getNombre()); // <-- AQUÍ VA EL NOMBRE DINÁMICO

            // --- Creamos el Botón Borrar ---
            javax.swing.JButton btnBorrar = new javax.swing.JButton();
            btnBorrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\Logobin.png"));
            btnBorrar.setOpaque(false);
            btnBorrar.setContentAreaFilled(false);
            btnBorrar.setBorderPainted(false);

            // --- 4. Añadimos Listeners (acciones) a los botones ---

            // Guardamos el ID de esta playlist en una variable local
            int playlistId = playlist.getId(); 
            String playlistNombre = playlist.getNombre();

            btnEditar.addActionListener(e -> {
                System.out.println("Clic en Editar playlist: " + playlistNombre + " (ID: " + playlistId + ")");
                // Aquí llamarías a tu frame "AgregarCancionesFrame"
                // (Deberás modificarlo para que acepte el ID de la playlist)
                openAgregarCanciones(playlistNombre, playlistId); 
            });

            btnBorrar.addActionListener(e -> {
                System.out.println("Clic en Borrar playlist: " + playlistNombre + " (ID: " + playlistId + ")");
                // Aquí llamaremos a la función de borrado
                borrarPlaylist(playlistId, playlistNombre);
            });

            // --- 5. Organizamos los componentes dentro del Panel Fila (GroupLayout) ---
            // (Este es el código que NetBeans genera, adaptado a nuestras variables)
            javax.swing.GroupLayout panelFilaLayout = new javax.swing.GroupLayout(panelFila);
            panelFila.setLayout(panelFilaLayout);
            panelFilaLayout.setHorizontalGroup(
                panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFilaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            panelFilaLayout.setVerticalGroup(
                panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFilaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            // 6. Añadimos el panel de esta fila al contenedor principal
            panelContenedorPlaylists.add(panelFila);
        }

        // 7. Refrescamos el contenedor para que muestre los cambios
        panelContenedorPlaylists.revalidate();
        panelContenedorPlaylists.repaint();
    }

    private void borrarPlaylist(int playlistId, String playlistNombre) {
        // 1. Pedir confirmación al usuario
        int confirmacion = JOptionPane.showConfirmDialog(
            this, // El frame actual
            "¿Estás seguro de que quieres borrar la playlist '" + playlistNombre + "'?", 
            "Confirmar Borrado", // Título de la ventana
            JOptionPane.YES_NO_OPTION, // Tipo de botones
            JOptionPane.WARNING_MESSAGE // Ícono de advertencia
        );

        // 2. Si el usuario NO presiona "YES", salimos del método
        if (confirmacion != JOptionPane.YES_OPTION) {
            return; 
        }

        // 3. Si el usuario dijo "Sí", procedemos con el borrado
        String sql = "DELETE FROM playlist WHERE idplaylist = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignamos el ID al '?' de la consulta
            pstmt.setInt(1, playlistId);

            // Ejecutamos el borrado
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Playlist '" + playlistNombre + "' borrada.");

                // 4. (¡MUY IMPORTANTE!) Recargamos la lista desde la BD
                // y la volvemos a dibujar en la UI
                cargarPlaylistsDesdeDB();
                mostrarPlaylistsEnUI();
            }

        } catch (SQLException e) {
            System.out.println("Error al borrar la playlist: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al borrar la playlist.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        namePlaylistField = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelContenedorPlaylists = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(797, 496));
        setMinimumSize(new java.awt.Dimension(797, 496));
        setPreferredSize(new java.awt.Dimension(797, 496));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\logoMusicUpChico.png")); // NOI18N

        btnHome.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\homeIconChico.png")); // NOI18N
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Create Playlist");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        namePlaylistField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        namePlaylistField.setText("name");
        namePlaylistField.setToolTipText("name");

        btnCreate.setBackground(new java.awt.Color(105, 168, 146));
        btnCreate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(242, 242, 242));
        btnCreate.setText("create");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Playlist");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelContenedorPlaylists.setLayout(new javax.swing.BoxLayout(panelContenedorPlaylists, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelContenedorPlaylists);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(namePlaylistField, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCreate))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namePlaylistField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseClicked
        crearPlaylistClick();
    }//GEN-LAST:event_btnCreateMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        backMenu();
    }//GEN-LAST:event_btnHomeMouseClicked

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
            java.util.logging.Logger.getLogger(CreatePlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePlaylistFrame().setVisible(true);
            }
        });
    }
    
    private void crearPlaylistClick() {
        String sql = "INSERT INTO playlist (namePlaylist) VALUES (?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { 

                pstmt.setString(1, namePlaylistField.getText()); 
                int filasAfectadas = pstmt.executeUpdate(); 

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "¡Playlist '" + namePlaylistField.getText() + "' creada!");

                    // --- ¡AQUÍ ESTÁ EL CAMBIO! ---
                    // Una vez creada, recargamos todo para mostrarla
                    cargarPlaylistsDesdeDB();
                    mostrarPlaylistsEnUI();

                    // Limpiamos el campo de texto
                    namePlaylistField.setText(""); 

                }
            } catch (SQLException e) {
                System.out.println("error");
            }
        
    }
    
    private void openAgregarCanciones(String name, int id) {
        AgregarCancionesFrame agregarCancionesFrame = new AgregarCancionesFrame(name, id);
        agregarCancionesFrame.setVisible(true); 
        this.dispose();
    }
    
    private void backMenu() {
        Menu menu = new Menu();
        menu.setVisible(true); 
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namePlaylistField;
    private javax.swing.JPanel panelContenedorPlaylists;
    // End of variables declaration//GEN-END:variables
}
