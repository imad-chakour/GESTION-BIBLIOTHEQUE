package model;

public class statistiques {
    private int totalUtilisateurs;
    private int totalLivres;
    private int totalEmprunts;
    private double pourcentageLivresEmpruntes;
    private String livreLePlusEmprunte;
    private String utilisateurLePlusActif;

    // No-argument constructor
    public statistiques() {
        this.totalUtilisateurs = 0;
        this.totalLivres = 0;
        this.totalEmprunts = 0;
        this.pourcentageLivresEmpruntes = 0.0;
        this.livreLePlusEmprunte = "";
        this.utilisateurLePlusActif = "";
    }

    // Parameterized constructor
    public statistiques(int totalUtilisateurs, int totalLivres, int totalEmprunts, double pourcentageLivresEmpruntes,
                        String livreLePlusEmprunte, String utilisateurLePlusActif) {
        this.totalUtilisateurs = totalUtilisateurs;
        this.totalLivres = totalLivres;
        this.totalEmprunts = totalEmprunts;
        this.pourcentageLivresEmpruntes = pourcentageLivresEmpruntes;
        this.livreLePlusEmprunte = livreLePlusEmprunte;
        this.utilisateurLePlusActif = utilisateurLePlusActif;
    }

    // Getters and setters
    public int getTotalUtilisateurs() {
        return totalUtilisateurs;
    }

    public void setTotalUtilisateurs(int totalUtilisateurs) {
        this.totalUtilisateurs = totalUtilisateurs;
    }

    public int getTotalLivres() {
        return totalLivres;
    }

    public void setTotalLivres(int totalLivres) {
        this.totalLivres = totalLivres;
    }

    public int getTotalEmprunts() {
        return totalEmprunts;
    }

    public void setTotalEmprunts(int totalEmprunts) {
        this.totalEmprunts = totalEmprunts;
    }

    public double getPourcentageLivresEmpruntes() {
        return pourcentageLivresEmpruntes;
    }

    public void setPourcentageLivresEmpruntes(double pourcentageLivresEmpruntes) {
        this.pourcentageLivresEmpruntes = pourcentageLivresEmpruntes;
    }

    public String getLivreLePlusEmprunte() {
        return livreLePlusEmprunte;
    }

    public void setLivreLePlusEmprunte(String livreLePlusEmprunte) {
        this.livreLePlusEmprunte = livreLePlusEmprunte;
    }

    public String getUtilisateurLePlusActif() {
        return utilisateurLePlusActif;
    }

    public void setUtilisateurLePlusActif(String utilisateurLePlusActif) {
        this.utilisateurLePlusActif = utilisateurLePlusActif;
    }
}
