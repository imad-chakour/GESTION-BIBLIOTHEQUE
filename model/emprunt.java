package model;

import java.util.Objects;

public class emprunt {
    private int id;                 // Loan ID
    private int utilisateurId;      // User ID
    private int livreId;            // Book ID
    private int dateEmprunt;        // Loan date
    private int dateRetourPrevue;   // Expected return date
    private int dateRetourEffective; // Actual return date
    private int penalite;           // Penalty for late return

    public emprunt() {
    }
    
    public emprunt(int id, int utilisateurId, int livreId, int dateEmprunt, int dateRetourPrevue, int dateRetourEffective, int penalite) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.penalite = penalite;
    }
    
    public emprunt(int id, int utilisateurId, int livreId, int dateEmprunt, int dateRetourPrevue) {
        this(id, utilisateurId, livreId, dateEmprunt, dateRetourPrevue, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public int getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(int dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public int getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(int dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public int getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(int dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public int getPenalite() {
        return penalite;
    }

    public void setPenalite(int penalite) {
        this.penalite = penalite;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", utilisateurId=" + utilisateurId +
                ", livreId=" + livreId +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourEffective=" + dateRetourEffective +
                ", penalite=" + penalite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        emprunt emprunt = (emprunt) o;
        return id == emprunt.id &&
                utilisateurId == emprunt.utilisateurId &&
                livreId == emprunt.livreId &&
                dateEmprunt == emprunt.dateEmprunt &&
                dateRetourPrevue == emprunt.dateRetourPrevue &&
                dateRetourEffective == emprunt.dateRetourEffective &&
                penalite == emprunt.penalite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateurId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective, penalite);
    }
}
