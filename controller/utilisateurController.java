package controller;

import model.livre;
import model.utilisateur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class utilisateurController {

    private List<utilisateur> utilisateurs;
    private static final String CSV_FILE = "utilisateurs.csv";

    public utilisateurController() {
        this.utilisateurs = new ArrayList<>();
        chargerUtilisateursDepuisCSV();
    }

    public void ajouterUtilisateur(utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
        sauvegarderUtilisateursDansCSV();
    }

    public boolean modifierUtilisateur(int id, String nom, String prenom, String email, String motDePasse, String role) {
        for (utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getId() == id) {
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setEmail(email);
                utilisateur.setMotDePasse(motDePasse);
                utilisateur.setRole(role);
                sauvegarderUtilisateursDansCSV();
                return true;
            }
        }
        return false;
    }
    
    public boolean supprimerUtilisateur(int id) {
        boolean removed = utilisateurs.removeIf(utilisateur -> utilisateur.getId() == id);
        if (removed) {
            sauvegarderUtilisateursDansCSV();
        }
        return removed;
    }

    public List<utilisateur> rechercherUtilisateur(String termeRecherche) {
        List<utilisateur> resultats = new ArrayList<>();
        for (utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().toLowerCase().contains(termeRecherche.toLowerCase()) ||
                utilisateur.getEmail().toLowerCase().contains(termeRecherche.toLowerCase())) {
                resultats.add(utilisateur);
            }
        }
        return resultats;
    }

    public List<utilisateur> getTousLesUtilisateurs() {
        return utilisateurs;
    }
    
    private void sauvegarderUtilisateursDansCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (utilisateur utilisateur : utilisateurs) {
                writer.write(utilisateur.getId() + "," +
                        utilisateur.getNom() + "," +
                        utilisateur.getPrenom() + "," +
                        utilisateur.getEmail() + "," +
                        utilisateur.getMotDePasse() + "," +
                        utilisateur.getRole());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void chargerUtilisateursDepuisCSV() {
        utilisateurs.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Ensure there are 6 parts in the CSV file
                    int id = Integer.parseInt(parts[0]);
                    String nom = parts[1];
                    String prenom = parts[2];
                    String email = parts[3];
                    String motDePasse = parts[4];
                    String role = parts[5];

                    utilisateurs.add(new utilisateur(id, nom, prenom, email, motDePasse, role));
                }
            }
        } catch (IOException ex) {
            // File may not exist yet; no action needed
        }
    }

}
