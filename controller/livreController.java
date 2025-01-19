package controller;

import model.livre;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class livreController {

    private List<livre> livres;
    private static final String CSV_FILE = "livres.csv";

    public livreController() {
        this.livres = new ArrayList<>();
        chargerLivresDepuisCSV();
    }

    public void ajouterLivre(livre livre) {
        livres.add(livre);
        sauvegarderLivresDansCSV();
    }

    public boolean modifierLivre(int id, String titre, String auteur, int annee, String genre) {
        for (livre livre : livres) {
            if (livre.getId() == id) {
                livre.setTitre(titre);
                livre.setAuteur(auteur);
                livre.setAnneePublication(annee);
                livre.setGenre(genre);
                sauvegarderLivresDansCSV();
                return true;
            }
        }
        return false;
    }

    public boolean supprimerLivre(int id) {
        boolean removed = livres.removeIf(livre -> livre.getId() == id);
        if (removed) {
            sauvegarderLivresDansCSV();
        }
        return removed;
    }

    public List<livre> rechercherLivre(String termeRecherche) {
        List<livre> resultats = new ArrayList<>();
        for (livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(termeRecherche.toLowerCase()) ||
                livre.getAuteur().toLowerCase().contains(termeRecherche.toLowerCase())) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public List<livre> getTousLesLivres() {
        return livres;
    }

    private void sauvegarderLivresDansCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (livre livre : livres) {
                writer.write(livre.getId() + "," +
                        livre.getTitre() + "," +
                        livre.getAuteur() + "," +
                        livre.getAnneePublication() + "," +
                        livre.getGenre());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void chargerLivresDepuisCSV() {
        livres.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String titre = parts[1];
                    String auteur = parts[2];
                    int annee = Integer.parseInt(parts[3]);
                    String genre = parts[4];

                    livres.add(new livre(id, titre, auteur, annee, genre));
                }
            }
        } catch (IOException ex) {
            // File may not exist yet; no action needed
        }
    }
}
