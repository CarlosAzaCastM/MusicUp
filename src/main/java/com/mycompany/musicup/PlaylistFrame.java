/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.musicup;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author carlc
 */
public class PlaylistFrame extends javax.swing.JFrame {

    ArrayList<Playlist> playlists = new ArrayList<>();
    public PlaylistFrame() {
        
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


            // --- Creamos la Etiqueta con el Nombre ---
            javax.swing.JLabel lblNombre = new javax.swing.JLabel();
            lblNombre.setFont(new java.awt.Font("Arial", 1, 14));
            lblNombre.setForeground(new java.awt.Color(242, 242, 242));
            lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNombre.setText(playlist.getNombre()); // <-- AQUÍ VA EL NOMBRE DINÁMICO

            // --- Creamos el Botón Borrar ---
            javax.swing.JButton btnPlaySong = new javax.swing.JButton();
            btnPlaySong.setIcon(new javax.swing.ImageIcon("C:\\Users\\carlc\\Documents\\MusicUp\\boton-de-play.png"));
            btnPlaySong.setOpaque(false);
            btnPlaySong.setContentAreaFilled(false);
            btnPlaySong.setBorderPainted(false);

            // --- 4. Añadimos Listeners (acciones) a los botones ---

            // Guardamos el ID de esta playlist en una variable local
            int playlistId = playlist.getId(); 
            String playlistNombre = playlist.getNombre();

            btnPlaySong.addActionListener(e -> {
                System.out.println("Clic en Borrar playlist: " + playlistNombre + " (ID: " + playlistId + ")");
                // Aquí llamaremos a la función de borrado
                openMusicPlayer(playlistId);
            });

            // --- 5. Organizamos los componentes dentro del Panel Fila (GroupLayout) ---
            // (Este es el código que NetBeans genera, adaptado a nuestras variables)
            javax.swing.GroupLayout panelFilaLayout = new javax.swing.GroupLayout(panelFila);
            panelFila.setLayout(panelFilaLayout);
            panelFilaLayout.setHorizontalGroup(
                panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFilaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGap(18, 18, 18)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPlaySong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            panelFilaLayout.setVerticalGroup(
                panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFilaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlaySong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            // 6. Añadimos el panel de esta fila al contenedor principal
            panelContenedorPlaylists.add(panelFila);
        }

        // 7. Refrescamos el contenedor para que muestre los cambios
        panelContenedorPlaylists.revalidate();
        panelContenedorPlaylists.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelContenedorPlaylists = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(797, 496));
        setMinimumSize(new java.awt.Dimension(797, 496));
        setResizable(false);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/homeIconChico.png"))); // NOI18N
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/logoMusicUpChico.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tus Playlists");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelContenedorPlaylists.setBackground(new java.awt.Color(43, 35, 59));
        panelContenedorPlaylists.setLayout(new javax.swing.BoxLayout(panelContenedorPlaylists, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelContenedorPlaylists);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        backMenu();
    }//GEN-LAST:event_btnHomeMouseClicked

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
            java.util.logging.Logger.getLogger(PlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlaylistFrame().setVisible(true);
            }
        });
    }
    
    private void openMusicPlayer(int idPlaylist) {
        MusicPlayerFrame musicPlayerFrame = new MusicPlayerFrame(idPlaylist);
        musicPlayerFrame.setVisible(true); 
        this.dispose();
    }
    
    private void backMenu() {
        Menu menu = new Menu();
        menu.setVisible(true); 
        this.dispose();
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelContenedorPlaylists;
    // End of variables declaration//GEN-END:variables
}
