package controller;

import model.emprunt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class empruntController {

    private List<emprunt> emprunts;
    private static final String CSV_FILE = "emprunts.csv";
    private static final int PENALTY_PER_DAY = 10; // Example penalty amount per day of delay

    public empruntController() {
        this.emprunts = new ArrayList<>();
        chargerEmpruntsDepuisCSV();
    }

    public void ajouterEmprunt(emprunt emprunt) {
        emprunts.add(emprunt);
        sauvegarderEmpruntsDansCSV();
    }

    public boolean modifierEmprunt(int id, int utilisateurId, int livreId, int dateEmprunt, int dateRetour) {
        for (emprunt e : emprunts) {
            if (e.getId() == id) {
                e.setUtilisateurId(utilisateurId);
                e.setLivreId(livreId);
                e.setDateEmprunt(dateEmprunt);
                e.setDateRetourPrevue(dateRetour);
                sauvegarderEmpruntsDansCSV();
                return true;
            }
        }
        return false;
    }

    public boolean enregistrerRetour(int id, int dateRetourEffective) {
        for (emprunt e : emprunts) {
            if (e.getId() == id) {
                e.setDateRetourEffective(dateRetourEffective);
                int delay = dateRetourEffective - e.getDateRetourPrevue();
                if (delay > 0) {
                    e.setPenalite(delay * PENALTY_PER_DAY);
                } else {
                    e.setPenalite(0);
                }
                sauvegarderEmpruntsDansCSV();
                return true;
            }
        }
        return false;
    }

    public boolean supprimerEmprunt(int id) {
        boolean removed = emprunts.removeIf(e -> e.getId() == id);
        if (removed) {
            sauvegarderEmpruntsDansCSV();
        }
        return removed;
    }

    public List<emprunt> rechercherEmpruntParUtilisateur(int utilisateurId) {
        List<emprunt> resultats = new ArrayList<>();
        for (emprunt e : emprunts) {
            if (e.getUtilisateurId() == utilisateurId) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    public List<emprunt> rechercherRetoursAvecPenalites() {
        List<emprunt> resultats = new ArrayList<>();
        for (emprunt e : emprunts) {
            if (e.getPenalite() > 0) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    public List<emprunt> getTousLesEmprunts() {
        return emprunts;
    }

    private void sauvegarderEmpruntsDansCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (emprunt e : emprunts) {
                writer.write(e.getId() + "," +
                        e.getUtilisateurId() + "," +
                        e.getLivreId() + "," +
                        e.getDateEmprunt() + "," +
                        e.getDateRetourPrevue() + "," +
                        e.getDateRetourEffective() + "," +
                        e.getPenalite());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void chargerEmpruntsDepuisCSV() {
        emprunts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    int utilisateurId = Integer.parseInt(parts[1]);
                    int livreId = Integer.parseInt(parts[2]);
                    int dateEmprunt = Integer.parseInt(parts[3]);
                    int dateRetourPrevue = Integer.parseInt(parts[4]);
                    int dateRetourEffective = Integer.parseInt(parts[5]);
                    int penalite = Integer.parseInt(parts[6]);

                    emprunts.add(new emprunt(id, utilisateurId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective,penalite));
                }
            }
        } catch (IOException ex) {
            // File may not exist yet; no action needed
        }
    }
    
    
}
