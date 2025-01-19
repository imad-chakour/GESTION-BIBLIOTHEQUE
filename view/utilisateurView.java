package view;

import controller.utilisateurController;
import model.utilisateur;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class utilisateurView extends JFrame {

    private utilisateurController controller;
    private DefaultTableModel tableModel;

    public utilisateurView() {
        controller = new utilisateurController();
        setTitle("Gestion des Utilisateurs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Title
        JLabel titleLabel = new JLabel("Gestion des Utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Rôle"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Button Panel
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
        add(buttonPanel, BorderLayout.SOUTH);

        // Back Button ActionListener
        backButton.addActionListener(e -> {
            dispose();
            List<model.emprunt> empruntList = fetchEmprunts(); // Get the list of emprunts
            SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true)); // Pass to tableBordView
        });

        // Add Button Action
        addButton.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog("Nom:");
            String prenom = JOptionPane.showInputDialog("Prénom:");
            String email = JOptionPane.showInputDialog("Email:");
            String motDePasse = JOptionPane.showInputDialog("Mot de passe:");
            String role = JOptionPane.showInputDialog("Rôle:");

            if (nom != null && prenom != null && email != null && motDePasse != null && role != null) {
                int id = tableModel.getRowCount() + 1; // Auto-generate ID
                utilisateur user = new utilisateur(id, nom, prenom, email, motDePasse, role);
                controller.ajouterUtilisateur(user);
                refreshTable();
            }
        });

        // Edit Button Action
        editButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à modifier.");
                return;
            }

            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String nom = JOptionPane.showInputDialog("Nom:", tableModel.getValueAt(selectedRow, 1));
            String prenom = JOptionPane.showInputDialog("Prénom:", tableModel.getValueAt(selectedRow, 2));
            String email = JOptionPane.showInputDialog("Email:", tableModel.getValueAt(selectedRow, 3));
            String role = JOptionPane.showInputDialog("Rôle:", tableModel.getValueAt(selectedRow, 4));

            if (nom != null && prenom != null && email != null && role != null) {
                controller.modifierUtilisateur(id, nom, prenom, email, "motDePasseNonModifiable", role);
                refreshTable();
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à supprimer.");
                return;
            }

            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            controller.supprimerUtilisateur(id);
            refreshTable();
        });

        // Search Button Action
        searchButton.addActionListener(e -> {
            String searchTerm = JOptionPane.showInputDialog("Entrez un nom ou un email à rechercher:");
            if (searchTerm != null) {
                List<utilisateur> results = controller.rechercherUtilisateur(searchTerm);
                refreshTable(results);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        refreshTable(controller.getTousLesUtilisateurs());
    }

    private void refreshTable(List<utilisateur> utilisateurs) {
        tableModel.setRowCount(0); // Clear the table
        for (utilisateur user : utilisateurs) {
            tableModel.addRow(new Object[]{user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), user.getRole()});
        }
    }

    // Fetch emprunt list
    private List<model.emprunt> fetchEmprunts() {
        // Example fetching logic: replace with actual data-fetching code
        return new ArrayList<>(); // Placeholder (replace with actual data fetching logic)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new utilisateurView().setVisible(true));
    }
}
