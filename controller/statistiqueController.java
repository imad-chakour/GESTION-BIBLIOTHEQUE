package controller;

import model.statistiques;

public class statistiqueController {

    private statistiques statistiques;

    public statistiqueController(statistiques statistiques) {
        this.statistiques = statistiques;
    }
    
    public int getTotalUtilisateurs() {
        return statistiques.getTotalUtilisateurs();
    }

    public int getTotalLivres() {
        return statistiques.getTotalLivres();
    }

    public int getTotalEmprunts() {
        return statistiques.getTotalEmprunts();
    }

    public double getPourcentageLivresEmpruntes() {
        return statistiques.getPourcentageLivresEmpruntes();
    }

    public String getLivreLePlusEmprunte() {
        return statistiques.getLivreLePlusEmprunte();
    }

    public String getUtilisateurLePlusActif() {
        return statistiques.getUtilisateurLePlusActif();
    }
}
