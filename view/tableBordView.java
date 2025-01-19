package view;

import javax.swing.*;
import controller.empruntController;
import controller.retourController;
import controller.statistiqueController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class tableBordView extends JFrame {

    private List<model.emprunt> empruntList;  // List of emprunt objects

    public tableBordView(List<model.emprunt> emprunts) {
        this.empruntList = emprunts;  // Initialize the emprunt list
        setTitle("Tableau de Bord - Bibliothèque");
        setSize(700, 500); // Adjusted size to accommodate additional buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Title
        JLabel titleLabel = new JLabel("Application - Gestion de Bibliothèque", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20)); // Adjusted to fit 6 buttons
        JButton manageBooksButton = new JButton("Gérer les Livres");
        JButton manageUsersButton = new JButton("Gérer les Utilisateurs");
        JButton manageLoansButton = new JButton("Gérer les Emprunts");
        JButton retourButton = new JButton("Retour");
        JButton statisticsButton = new JButton("Statistiques");
        JButton logoutButton = new JButton("Déconnexion");

        // Add buttons to panel
        buttonPanel.add(manageBooksButton);
        buttonPanel.add(manageUsersButton);
        buttonPanel.add(manageLoansButton);
        buttonPanel.add(retourButton);
        buttonPanel.add(statisticsButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners
        manageBooksButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new livreView().setVisible(true));
        });

        manageUsersButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new utilisateurView().setVisible(true));
        });

        manageLoansButton.addActionListener(e -> {
            dispose();
            empruntController controller = new empruntController();
            SwingUtilities.invokeLater(() -> {
                empruntView view = new empruntView(controller);
                view.setVisible(true);
            });
        });

        retourButton.addActionListener(e -> {
            dispose();
            retourController controller = new retourController(empruntList); // Pass the empruntList to retourController
            SwingUtilities.invokeLater(() -> new retourView(controller).setVisible(true)); // Pass controller to retourView
        });

        statisticsButton.addActionListener(e -> {
            dispose();
            statistiqueController controller = new statistiqueController(new model.statistiques()); // Initialize statistiqueController with a new statistiques object
            SwingUtilities.invokeLater(() -> new statistiqueView(controller).setVisible(true)); // Pass controller to statistiqueView
        });

        logoutButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new loginView().setVisible(true));
        });
    }

    public static void main(String[] args) {
        // Assuming you have a method to fetch empruntList (e.g., from a database or a static list)
        List<model.emprunt> empruntList = fetchEmprunts(); // Replace with your actual method to fetch emprunts
        SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true));
    }

    private static List<model.emprunt> fetchEmprunts() {
        return new ArrayList<>();
    }
}