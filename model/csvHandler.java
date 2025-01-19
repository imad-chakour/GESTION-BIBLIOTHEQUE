package model;

import java.io.*;

public class csvHandler {

    private String filePath;
    private String delimiter;

    public csvHandler(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public String[][] readCsv(int maxRows, int maxColumns) {
        String[][] data = new String[maxRows][maxColumns];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < maxRows) {
                String[] values = line.split(delimiter);
                for (int col = 0; col < values.length && col < maxColumns; col++) {
                    data[row][col] = values[col];
                }
                row++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
        return data;
    }

    public void writeCsv(String[][] data, int rows, int columns) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < rows; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < columns; j++) {
                    if (data[i][j] != null) {
                        line.append(data[i][j]);
                    }
                    if (j < columns - 1) {
                        line.append(delimiter);
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        }
    }

    public void updateRow(int targetRow, String[] newRow, int maxColumns) {
        String[][] data = readCsv(1000, maxColumns); 
        if (targetRow >= 0 && targetRow < data.length) {
            for (int col = 0; col < maxColumns; col++) {
                if (col < newRow.length) {
                    data[targetRow][col] = newRow[col];
                }
            }
            writeCsv(data, data.length, maxColumns);
        } else {
            System.err.println("Ligne cible hors des limites.");
        }
    }
    
}
