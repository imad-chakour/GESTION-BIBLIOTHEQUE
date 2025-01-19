package view;

import controller.empruntController;
import model.emprunt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class empruntView extends JFrame {
    private empruntController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    // Form fields
    private JTextField idField, utilisateurIdField, livreIdField, dateEmpruntField, dateRetourField;

    public empruntView(empruntController controller) {
        this.controller = controller;
        setTitle("Gestion des Emprunts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table for displaying loans
        tableModel = new DefaultTableModel(new Object[]{"ID", "Utilisateur ID", "Livre ID", "Date Emprunt", "Date Retour"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Ajouter");
        JButton editButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        JButton searchButton = new JButton("Rechercher");
        JButton backButton = new JButton("Retour");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        // Form panel for adding/editing loans
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Détails de l'emprunt"));

        idField = new JTextField();
        utilisateurIdField = new JTextField();
        livreIdField = new JTextField();
        dateEmpruntField = new JTextField();
        dateRetourField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Utilisateur ID:"));
        formPanel.add(utilisateurIdField);
        formPanel.add(new JLabel("Livre ID:"));
        formPanel.add(livreIdField);
        formPanel.add(new JLabel("Date Emprunt (yyyy-MM-dd):"));
        formPanel.add(dateEmpruntField);
        formPanel.add(new JLabel("Date Retour (yyyy-MM-dd):"));
        formPanel.add(dateRetourField);

        // Adding components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.NORTH);

        // Event listeners
        addButton.addActionListener(e -> ajouterEmprunt());
        editButton.addActionListener(e -> modifierEmprunt());
        deleteButton.addActionListener(e -> supprimerEmprunt());
        backButton.addActionListener(e -> {
            dispose();
            List<emprunt> empruntList = fetchEmprunts(); // Fetch emprunt list (replace with actual method)
            SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true)); // Navigate to the dashboard
        });

        // Initial table population
        actualiserTable();
    }
    
    private List<model.emprunt> fetchEmprunts() {
        // Example fetching logic: replace with actual data-fetching code
        // For instance, fetching from a database, or returning a static list.
        return new ArrayList<>(); // Placeholder
    }
    
    private void ajouterEmprunt() {
        try {
            int id = Integer.parseInt(idField.getText());
            int utilisateurId = Integer.parseInt(utilisateurIdField.getText());
            int livreId = Integer.parseInt(livreIdField.getText());
            int dateEmprunt = Integer.parseInt(dateEmpruntField.getText());
            int dateRetour = Integer.parseInt(dateRetourField.getText());

            emprunt newEmprunt = new emprunt(id, utilisateurId, livreId, dateEmprunt, dateRetour);
            controller.ajouterEmprunt(newEmprunt);

            JOptionPane.showMessageDialog(this, "Emprunt ajouté avec succès.");
            actualiserTable();
            viderFormulaire();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifierEmprunt() {
        try {
            int id = Integer.parseInt(idField.getText());
            int utilisateurId = Integer.parseInt(utilisateurIdField.getText());
            int livreId = Integer.parseInt(livreIdField.getText());
            int dateEmprunt = Integer.parseInt(dateEmpruntField.getText());
            int dateRetour = Integer.parseInt(dateRetourField.getText());

            boolean success = controller.modifierEmprunt(id, utilisateurId, livreId, dateEmprunt, dateRetour);
            if (success) {
                JOptionPane.showMessageDialog(this, "Emprunt modifié avec succès.");
                actualiserTable();
                viderFormulaire();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur: Emprunt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void supprimerEmprunt() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez l'ID de l'emprunt à supprimer:"));
            boolean success = controller.supprimerEmprunt(id);

            if (success) {
                JOptionPane.showMessageDialog(this, "Emprunt supprimé avec succès.");
                actualiserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur: Emprunt introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualiserTable() {
        tableModel.setRowCount(0); // Clear table
        List<emprunt> emprunts = controller.getTousLesEmprunts();
        for (emprunt e : emprunts) {
            tableModel.addRow(new Object[]{e.getId(), e.getUtilisateurId(), e.getLivreId(), e.getDateEmprunt(), e.getDateRetourPrevue()});
        }
    }

    private void viderFormulaire() {
        idField.setText("");
        utilisateurIdField.setText("");
        livreIdField.setText("");
        dateEmpruntField.setText("");
        dateRetourField.setText("");
    }

    public static void main(String[] args) {
        empruntController controller = new empruntController();
        SwingUtilities.invokeLater(() -> {
            empruntView view = new empruntView(controller);
            view.setVisible(true);
        });
    }
}
