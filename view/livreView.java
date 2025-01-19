package view;

import controller.empruntController;
import controller.livreController;
import model.livre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class livreView extends JFrame {

    private livreController controller;
    private DefaultTableModel tableModel;
    private JTable bookTable;

    public livreView() {
    	controller = new livreController();

        setTitle("Gestion des Livres");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Books Summary", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Titre", "Auteur", "Année", "Genre"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        add(tableScrollPane, BorderLayout.CENTER);

        loadBooksIntoTable();

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

        addButton.addActionListener(new AddBookAction());
        editButton.addActionListener(new EditBookAction());
        deleteButton.addActionListener(new DeleteBookAction());
        searchButton.addActionListener(new SearchBookAction());
        backButton.addActionListener(e -> {
            dispose();
            // Fetch empruntList (replace with actual method to get the list)
            List<model.emprunt> empruntList = fetchEmprunts(); // You need to fetch emprunt list here
            SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true)); // Pass empruntList to tableBordView
        });

    }
    private List<model.emprunt> fetchEmprunts() {
        // Example fetching logic: replace with actual data-fetching code
        // For instance, fetching from a database, or returning a static list.
        return new ArrayList<>(); // Placeholder
    }

    private void loadBooksIntoTable() {
        tableModel.setRowCount(0); // Clear the table
        for (livre book : controller.getTousLesLivres()) {
            tableModel.addRow(new Object[]{
                book.getId(),
                book.getTitre(),
                book.getAuteur(),
                book.getAnneePublication(),
                book.getGenre()
            });
        }
    }

    private class AddBookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID du livre:"));
                String title = JOptionPane.showInputDialog("Entrez le titre du livre:");
                String author = JOptionPane.showInputDialog("Entrez l'auteur du livre:");
                int year = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'année de publication:"));
                String genre = JOptionPane.showInputDialog("Entrez le genre du livre:");

                controller.ajouterLivre(new livre(id, title, author, year, genre));
                loadBooksIntoTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides.");
            }
        }
    }

    private class EditBookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    String title = JOptionPane.showInputDialog("Modifiez le titre:", tableModel.getValueAt(selectedRow, 1));
                    String author = JOptionPane.showInputDialog("Modifiez l'auteur:", tableModel.getValueAt(selectedRow, 2));
                    int year = Integer.parseInt(JOptionPane.showInputDialog("Modifiez l'année:", tableModel.getValueAt(selectedRow, 3)));
                    String genre = JOptionPane.showInputDialog("Modifiez le genre:", tableModel.getValueAt(selectedRow, 4));

                    if (controller.modifierLivre(id, title, author, year, genre)) {
                        loadBooksIntoTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Livre introuvable.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un livre à modifier.");
            }
        }
    }

    private class DeleteBookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                if (controller.supprimerLivre(id)) {
                    loadBooksIntoTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Livre introuvable.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un livre à supprimer.");
            }
        }
    }

    private class SearchBookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = JOptionPane.showInputDialog("Entrez le titre ou l'auteur à rechercher:");
            if (searchTerm != null && !searchTerm.isEmpty()) {
                List<livre> results = controller.rechercherLivre(searchTerm);
                if (!results.isEmpty()) {
                    tableModel.setRowCount(0); // Clear the table
                    for (livre book : results) {
                        tableModel.addRow(new Object[]{
                            book.getId(),
                            book.getTitre(),
                            book.getAuteur(),
                            book.getAnneePublication(),
                            book.getGenre()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun livre trouvé correspondant à la recherche.");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new livreView().setVisible(true));
    }
}
