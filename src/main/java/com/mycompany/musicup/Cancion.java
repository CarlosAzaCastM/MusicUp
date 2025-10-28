
package com.mycompany.musicup;


public class Cancion {
    private int idSong;
    private String nameSong;
    private String artistaSong;
    private String duracionSong;

    public Cancion(int idSong, String nameSong, String artistaSong, String duracionSong) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.artistaSong = artistaSong;
        this.duracionSong = duracionSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getArtistaSong() {
        return artistaSong;
    }

    public void setArtistaSong(String artistaSong) {
        this.artistaSong = artistaSong;
    }

    public String getDuracionSong() {
        return duracionSong;
    }

    public void setDuracionSong(String duracionSong) {
        this.duracionSong = duracionSong;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }
    
    
    
    
}
