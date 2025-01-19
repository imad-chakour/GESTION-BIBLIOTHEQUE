import view.tableBordView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    	List<model.emprunt> empruntList = fetchEmprunts();
        SwingUtilities.invokeLater(() -> new tableBordView(empruntList).setVisible(true));
    }
    
    private static List<model.emprunt> fetchEmprunts() {
        return new ArrayList<>();
    }
}
