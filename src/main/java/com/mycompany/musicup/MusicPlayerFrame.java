/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.musicup;

import java.awt.Color;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList; // Importante
import java.util.Random;
import javax.swing.JOptionPane;

public class MusicPlayerFrame extends javax.swing.JFrame {

    
    private int idPlaylist; // El ID de la playlist que estamos reproduciendo
    private LinkedList<Cancion> cancionesPlaylist = new java.util.LinkedList<>();
    private int cancionActualIndex = 0; // Para saber cuál canción toca (siguiente/anterior)
    ArrayList<Color> coloresPortada = new ArrayList<>();
    Random random = new java.util.Random();
    boolean isMusicPlay = false;
    javax.swing.ImageIcon iconoNormal = new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\boton-de-playGrande.png");
    javax.swing.ImageIcon iconoPlay = new javax.swing.ImageIcon("C:\\Users\\carlc\\Desktop\\todo\\ApacheProjects\\MusicUp\\src\\main\\java\\com\\mycompany\\musicup\\icons8-pausa-60.png");
    // ----------------------------
    
    public MusicPlayerFrame(int idPlaylist) {
        initComponents();
        this.getContentPane().setBackground(new Color(43, 35, 59));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 3. Guarda el ID
        this.idPlaylist = idPlaylist;
        
        // 4. Carga las canciones de esa playlist
        cargarCancionesDePlaylist();
        
        // 5. Muestra la primera canción (índice 0)
        mostrarCancionActual();
        
        inicializarColores();
        
    }
    
    private void inicializarColores() {
        coloresPortada.add(new Color(231, 76, 60));  // Rojo
        coloresPortada.add(new Color(52, 152, 219)); // Azul
        coloresPortada.add(new Color(46, 204, 113)); // Verde
        coloresPortada.add(new Color(155, 89, 182)); // Morado
        coloresPortada.add(new Color(243, 156, 18)); // Naranja
        coloresPortada.add(new Color(26, 188, 156)); // Turquesa
        // ... añade los demás
    }

    private void cargarCancionesDePlaylist() {
        cancionesPlaylist.clear(); // Limpiamos la lista

        String sql = "SELECT s.idsong, s.nameSong, s.artistSong, s.duration " +
                     "FROM song s " +
                     "JOIN playlist_songs ps ON s.idsong = ps.idsong " +
                     "WHERE ps.idplaylist = ? " + 
                     "ORDER BY s.nameSong";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.idPlaylist); // Usamos el ID de la playlist actual

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idsong");
                    String nombre = rs.getString("nameSong");
                    String artista = rs.getString("artistSong");
                    String duracion = rs.getString("duration");

                    // Creamos y añadimos la canción a la LinkedList
                    cancionesPlaylist.add(new Cancion(id, nombre, artista, duracion));
                }
            }
            System.out.println("Canciones cargadas para el reproductor: " + cancionesPlaylist.size());

        } catch (SQLException e) {
            System.out.println("Error al cargar las canciones: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar las canciones.");
        }
    }
    
    
    private void mostrarCancionActual() {
        // 1. Validar que la lista no esté vacía
        if (cancionesPlaylist.isEmpty()) {
            txtSongName.setText("Playlist Vacía");
            txtSongArtist.setText("-");
            txtSongDuration.setText("0:00");
            // Aquí también podrías deshabilitar los botones
            btnPlaySong.setEnabled(false);
            btnNextSong.setEnabled(false);
            btnBackSong.setEnabled(false);
            return;
        }

        // 2. Habilitar botones (en caso de que estuvieran deshabilitados)
        btnPlaySong.setEnabled(true);
        btnNextSong.setEnabled(true);
        btnBackSong.setEnabled(true);

        // 3. Obtener la canción actual
        
        Cancion cancion = cancionesPlaylist.get(cancionActualIndex);

        // 4. Actualizar las etiquetas en la interfaz
        txtSongName.setText(cancion.getNameSong());
        txtSongArtist.setText(cancion.getArtistaSong());
        txtSongDuration.setText(cancion.getDuracionSong());

        if (coloresPortada.isEmpty()) {
            inicializarColores(); // Asegúrate de que la lista esté llena
        }

        // Elige un índice aleatorio
        int indiceColor = random.nextInt(coloresPortada.size()); 
        // Aplica el color al panel
        panelAlbum.setBackground(coloresPortada.get(indiceColor));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBackPlaylists = new javax.swing.JButton();
        txtSongName = new javax.swing.JLabel();
        panelAlbum = new javax.swing.JPanel();
        txtSongDuration = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPlaySong = new javax.swing.JButton();
        btnNextSong = new javax.swing.JButton();
        btnBackSong = new javax.swing.JButton();
        txtSongArtist = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(797, 496));
        setMinimumSize(new java.awt.Dimension(797, 496));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/logoMusicUpChico.png"))); // NOI18N

        btnBackPlaylists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/flecha-izquierda.png"))); // NOI18N
        btnBackPlaylists.setOpaque(false);
        btnBackPlaylists.setContentAreaFilled(false);
        btnBackPlaylists.setBorderPainted(false);
        btnBackPlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackPlaylistsMouseClicked(evt);
            }
        });

        txtSongName.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtSongName.setForeground(new java.awt.Color(242, 242, 242));
        txtSongName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSongName.setText("La Planta");
        txtSongName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelAlbum.setBackground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout panelAlbumLayout = new javax.swing.GroupLayout(panelAlbum);
        panelAlbum.setLayout(panelAlbumLayout);
        panelAlbumLayout.setHorizontalGroup(
            panelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );
        panelAlbumLayout.setVerticalGroup(
            panelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        txtSongDuration.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtSongDuration.setForeground(new java.awt.Color(255, 255, 255));
        txtSongDuration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSongDuration.setText("3:10");

        jPanel1.setMaximumSize(new java.awt.Dimension(263, 5));
        jPanel1.setMinimumSize(new java.awt.Dimension(263, 5));
        jPanel1.setPreferredSize(new java.awt.Dimension(263, 5));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        btnPlaySong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/boton-de-playGrande.png"))); // NOI18N
        btnPlaySong.setBorder(null);
        btnPlaySong.setOpaque(false);
        btnPlaySong.setContentAreaFilled(false);
        btnPlaySong.setBorderPainted(false);
        btnPlaySong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlaySongMouseClicked(evt);
            }
        });

        btnNextSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/icon-fin-32.png"))); // NOI18N
        btnNextSong.setBorder(null);
        btnNextSong.setOpaque(false);
        btnNextSong.setContentAreaFilled(false);
        btnNextSong.setBorderPainted(false);
        btnNextSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextSongMouseClicked(evt);
            }
        });

        btnBackSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/musicup/icon-flip-fin.png"))); // NOI18N
        btnBackSong.setAlignmentY(-1.0F);
        btnBackSong.setBorder(null);
        btnBackSong.setOpaque(false);
        btnBackSong.setContentAreaFilled(false);
        btnBackSong.setBorderPainted(false);
        btnBackSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackSongMouseClicked(evt);
            }
        });

        txtSongArtist.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtSongArtist.setForeground(new java.awt.Color(242, 242, 242));
        txtSongArtist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSongArtist.setText("Artista");
        txtSongArtist.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSongDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBackPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196)
                        .addComponent(panelAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(btnBackSong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPlaySong)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextSong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSongArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(panelAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(31, 31, 31)
                .addComponent(txtSongName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSongArtist)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(txtSongDuration)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnPlaySong, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnNextSong)
                            .addGap(9, 9, 9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnBackSong)
                        .addGap(8, 8, 8)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackPlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackPlaylistsMouseClicked
        backPlaylists();
    }//GEN-LAST:event_btnBackPlaylistsMouseClicked

    private void btnNextSongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextSongMouseClicked
        cancionActualIndex++;
        if (cancionActualIndex <= cancionesPlaylist.size()-1) {
          mostrarCancionActual();  
        }     
        else{
            cancionActualIndex = 0;
            mostrarCancionActual();
        }
    }//GEN-LAST:event_btnNextSongMouseClicked

    private void btnBackSongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackSongMouseClicked
        cancionActualIndex--;
        if (cancionActualIndex >= 0) {
          mostrarCancionActual();  
        }     
        else{
            cancionActualIndex = cancionesPlaylist.size()-1;
            mostrarCancionActual();
        }
    }//GEN-LAST:event_btnBackSongMouseClicked

    private void btnPlaySongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlaySongMouseClicked
        if (isMusicPlay) {
            isMusicPlay = false;
            btnPlaySong.setIcon(iconoNormal);
        }
        else{
            btnPlaySong.setIcon(iconoPlay);
            isMusicPlay = true;
        }
    }//GEN-LAST:event_btnPlaySongMouseClicked

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
            java.util.logging.Logger.getLogger(MusicPlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusicPlayerFrame(1).setVisible(true);
            }
        });
    }
    
    private void backPlaylists() {
        PlaylistFrame playlistFrame = new PlaylistFrame();
        playlistFrame.setVisible(true); 
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackPlaylists;
    private javax.swing.JButton btnBackSong;
    private javax.swing.JButton btnNextSong;
    private javax.swing.JButton btnPlaySong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelAlbum;
    private javax.swing.JLabel txtSongArtist;
    private javax.swing.JLabel txtSongDuration;
    private javax.swing.JLabel txtSongName;
    // End of variables declaration//GEN-END:variables
}
