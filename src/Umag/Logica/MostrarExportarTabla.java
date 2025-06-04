package Umag.Logica;

import java.awt.HeadlessException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MostrarExportarTabla {
    public static void mostrarTabla(JFrame parent, PuertaLogica circuit) {
        GeneradorTablaVerdad generator = new GeneradorTablaVerdad(circuit);
        List<List<String>> table = generator.getTablaVerdad();
        TablaDeVerdadDialogo dialog = new TablaDeVerdadDialogo(parent, table);
        dialog.setVisible(true);
    }

    public static void exportarTabla(JFrame parent, PuertaLogica circuit) {
        GeneradorTablaVerdad generator = new GeneradorTablaVerdad(circuit);
        List<List<String>> table = generator.getTablaVerdad();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ExportadorTablaDeVerdad.exportToCSV(table, file);
                JOptionPane.showMessageDialog(parent, "Tabla exportada a " + file.getAbsolutePath());
            } catch (HeadlessException | IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error al exportar: " + ex.getMessage());
            }
        }
    }
}