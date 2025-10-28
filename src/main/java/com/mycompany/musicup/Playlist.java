package com.mycompany.musicup;

import java.util.LinkedList;

public class Playlist {
    
    // --- NUEVO ---
    private int id; // El ID que viene de la base de datos
    
    private String nombre;
    private LinkedList<Cancion> playlist = new LinkedList<>(); // Esto está perfecto

    // Constructor para cuando creas una playlist nueva (Java)
    public Playlist(String nombre) {
        this.nombre = nombre;
    }

    // --- NUEVO ---
    // Constructor para cuando cargas la playlist DESDE LA BASE DE DATOS
    // (Solo cargamos el id y el nombre, la lista de canciones se carga después)
    public Playlist(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- NUEVO ---
    public int getId() {
        return id;
    }

    // --- NUEVO ---
    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Cancion> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(LinkedList<Cancion> playlist) {
        this.playlist = playlist;
    }
    
    public void addSong(Cancion cancion){
        this.playlist.addLast(cancion);
    }
}