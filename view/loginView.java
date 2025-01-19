package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class loginView extends JFrame {

    public loginView() {
        setTitle("Connexion à la Bibliothèque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Title
        JLabel titleLabel = new JLabel("Connexion", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

       
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
        JLabel userLabel = new JLabel("Nom d'utilisateur :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(userField, gbc);

        // Password Label and Field
        JLabel passLabel = new JLabel("Mot de passe :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(passField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Se connecter");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Connexion réussie !");
                dispose(); // Close login window
                List<model.emprunt> empruntList = fetchEmprunts(); // You need to fetch emprunt list here
                SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true));
            } else {
                JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> System.exit(0));
    }
    
    private List<model.emprunt> fetchEmprunts() {
        // Example fetching logic: replace with actual data-fetching code
        // For instance, fetching from a database, or returning a static list.
        return new ArrayList<>(); // Placeholder
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginView().setVisible(true));
    }
}
