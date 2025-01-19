package controller;

import model.emprunt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class retourController {
    private List<emprunt> emprunts;

    public retourController(List<emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    // Method to register the return of a loan
    public boolean enregistrerRetour(int empruntId, int dateRetourEffective) {
        for (emprunt e : emprunts) {
            if (e.getId() == empruntId) {
                e.setDateRetourEffective(dateRetourEffective);

                // Calculate penalties
                long penalty = calculerPenalites(e);
                e.setPenalite((int) penalty); // Set penalty after calculation

                return true;
            }
        }
        return false;
    }

    // Method to calculate penalties
    public long calculerPenalites(emprunt e) {
        if (e.getDateRetourEffective() != 0 && e.getDateRetourPrevue() != 0) {
            // Convert integer date to LocalDate
            LocalDate dueDate = convertirIntEnDate(e.getDateRetourPrevue());
            LocalDate actualReturnDate = convertirIntEnDate(e.getDateRetourEffective());

            if (actualReturnDate.isAfter(dueDate)) {
                long daysLate = ChronoUnit.DAYS.between(dueDate, actualReturnDate);
                return daysLate * 5; // Assume penalty is 5 units per day
            }
        }
        return 0;
    }

    // Convert integer (YYYYMMDD) to LocalDate
    private LocalDate convertirIntEnDate(int dateInt) {
        String dateStr = String.valueOf(dateInt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateStr, formatter);
    }

    // Search method for returns
    public List<emprunt> rechercherRetours(String termeRecherche) {
        List<emprunt> resultats = new ArrayList<>();
        for (emprunt e : emprunts) {
            if (String.valueOf(e.getId()).contains(termeRecherche) ||
                String.valueOf(e.getDateEmprunt()).contains(termeRecherche) ||
                (e.getDateRetourEffective() != 0 && String.valueOf(e.getDateRetourEffective()).contains(termeRecherche))) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    // Get all returns
    public List<emprunt> getTousLesRetours() {
        return emprunts;
    }
}
