package view;

import controller.retourController;
import controller.statistiqueController;
import model.statistiques;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class statistiqueView extends JFrame {

    private statistiqueController controle;

    public statistiqueView(statistiqueController controle) {
        this.controle = controle;
        initialize();
    }

    private void initialize() {
        setTitle("Statistiques de la Bibliothèque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Statistiques", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAfficherStats = new JButton("Afficher les Statistiques Générales");
        JButton btnAfficherRetards = new JButton("Afficher les Emprunts en Retard");
        JButton btnQuitter = new JButton("Quitter");

        buttonPanel.add(btnAfficherStats);
        buttonPanel.add(btnAfficherRetards);
        buttonPanel.add(btnQuitter);

        add(buttonPanel, BorderLayout.CENTER);

        // Add Action Listeners
        btnAfficherStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherStatistiques();
            }
        });

        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void afficherStatistiques() {
        String stats = "===== Statistiques =====\n" +
                "Total utilisateurs : " + controle.getTotalUtilisateurs() + "\n" +
                "Total livres : " + controle.getTotalLivres() + "\n" +
                "Total emprunts : " + controle.getTotalEmprunts() + "\n" +
                String.format("Pourcentage de livres empruntés : %.2f%%\n", controle.getPourcentageLivresEmpruntes()) +
                "Livre le plus emprunté : " + controle.getLivreLePlusEmprunte() + "\n" +
                "Utilisateur le plus actif : " + controle.getUtilisateurLePlusActif();
        JOptionPane.showMessageDialog(this, stats, "Statistiques Générales", JOptionPane.INFORMATION_MESSAGE);
    }



    public static void main(String[] args) {
        // Simulate a StatistiqueController
        // Replace this with actual implementation
        statistiques stats = new statistiques(); // Initialize your statistiques object
        statistiqueController controle = new statistiqueController(stats); // Pass it to the controller

        // Launch the application
        SwingUtilities.invokeLater(() -> {
            statistiqueView view = new statistiqueView(controle); // Pass the controller to the view
            view.setVisible(true);
        });
    }

}
