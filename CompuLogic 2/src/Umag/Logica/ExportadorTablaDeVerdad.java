package Umag.Logica;

import java.io.*;
import java.util.List;

public class ExportadorTablaDeVerdad {
    public static void exportToCSV(List<List<String>> truthTable, File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (List<String> row : truthTable) {
                writer.println(String.join(",", row));
            }
        }
    }
}