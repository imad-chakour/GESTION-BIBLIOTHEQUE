package view;

import controller.retourController;
import model.emprunt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class retourView extends JFrame {
    private retourController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public retourView(retourController controller) {
        this.controller = controller;
        setTitle("Gestion des Retours");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table for displaying returns
        tableModel = new DefaultTableModel(new Object[]{"ID", "Utilisateur ID", "Livre ID", "Date Emprunt", "Date Retour", "Retour Effectif", "Pénalité"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Search and Filter
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        JButton refreshButton = new JButton("Actualiser");

        searchPanel.add(new JLabel("Recherche:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton recordButton = new JButton("Enregistrer Retour");

        buttonPanel.add(recordButton);

        // Adding components
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event listeners
        searchButton.addActionListener(e -> rechercherRetours());
        refreshButton.addActionListener(e -> actualiserTable());
        recordButton.addActionListener(e -> enregistrerRetour());

        // Populate table
        actualiserTable();
    }

    private void enregistrerRetour() {
        try {
            int empruntId = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez l'ID de l'emprunt:"));
            int dateRetourEffective = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez la date de retour effective (yyyy-MM-dd):"));

            boolean success = controller.enregistrerRetour(empruntId, dateRetourEffective);
            if (success) {
                JOptionPane.showMessageDialog(this, "Retour enregistré avec succès.");
                actualiserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur: Emprunt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: ID invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rechercherRetours() {
        String termeRecherche = searchField.getText();
        List<emprunt> resultats = controller.rechercherRetours(termeRecherche);

        tableModel.setRowCount(0); // Clear table
        for (emprunt e : resultats) {
            tableModel.addRow(new Object[]{e.getId(), e.getUtilisateurId(), e.getLivreId(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetourEffective(), e.getPenalite()});
        }
    }

    private void actualiserTable() {
        tableModel.setRowCount(0); // Clear table
        List<emprunt> emprunts = controller.getTousLesRetours();
        for (emprunt e : emprunts) {
            tableModel.addRow(new Object[]{e.getId(), e.getUtilisateurId(), e.getLivreId(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetourEffective(), e.getPenalite()});
        }
    }

    public static void main(String[] args) {
        retourController controller = new retourController(new ArrayList<>());
        SwingUtilities.invokeLater(() -> {
            retourView view = new retourView(controller);
            view.setVisible(true);
        });
    }
}
