package model;

import java.util.Objects;

public class livre {
    private int id;                
    private String titre;          
    private String auteur;        
    private int anneePublication;  
    private String genre;          

    public livre() {
    	
    }

    public livre(int id, String titre, String auteur, int anneePublication, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        livre livre = (livre) o;
        return id == livre.id &&
                anneePublication == livre.anneePublication &&
                Objects.equals(titre, livre.titre) &&
                Objects.equals(auteur, livre.auteur) &&
                Objects.equals(genre, livre.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, auteur, anneePublication, genre);
    }
    
}
