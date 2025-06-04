package Umag.Logica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaDeVerdadDialogo extends JDialog {
    public TablaDeVerdadDialogo(Frame owner, List<List<String>> truthTable) {
        super(owner, "Tabla de Verdad", true);

        // Convertimos la tabla a formato de JTable
        String[] columns = truthTable.get(0).toArray(new String[0]);
        String[][] data = new String[truthTable.size() - 1][columns.length];
        for (int i = 1; i < truthTable.size(); i++) {
            data[i - 1] = truthTable.get(i).toArray(new String[0]);
        }

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setLocationRelativeTo(owner);
    }
}