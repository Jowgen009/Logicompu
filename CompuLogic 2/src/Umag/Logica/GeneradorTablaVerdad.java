package Umag.Logica;

import java.util.*;
import Umag.Circuito.Circuito;
import Umag.Componentes.Componente;
import Umag.Componentes.Led;

public class GeneradorTablaVerdad {
    private final PuertaLogica circuit;

    private String outputName = "Output";

    public GeneradorTablaVerdad(PuertaLogica circuit) {
        this.circuit = circuit;
        if (circuit instanceof AdaptadorCircuitoLogico adapter) {
            Circuito realCircuito = getCircuitoAdaptador(adapter);
            if (realCircuito != null) {
                for (Componente c : realCircuito.getComponentes()) {
                    if (c instanceof Led led) {
                        outputName = led.getNombre();
                        break;
                    }
                }
            }
        }
    }

    private Circuito getCircuitoAdaptador(AdaptadorCircuitoLogico adapter) {
        try {
            java.lang.reflect.Field field = AdaptadorCircuitoLogico.class.getDeclaredField("circuito");
            field.setAccessible(true);
            return (Circuito) field.get(adapter);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Boolean>> generadordeCombinaciones() {
        List<Map<String, Boolean>> combinaciones = new ArrayList<>();
        List<String> inputs = new ArrayList<>(circuit.getInputVariables());
        int n = inputs.size();
        int rows = 1 << n; 

        for (int i = 0; i < rows; i++) {
            Map<String, Boolean> combination = new HashMap<>();
            for (int j = 0; j < n; j++) {
                combination.put(inputs.get(j), ((i >> (n - j - 1)) & 1) == 1);
            }
            combinaciones.add(combination);
        }
        return combinaciones;
    }

    public List<List<String>> getTablaVerdad() {
        List<Map<String, Boolean>> combinations = generadordeCombinaciones();
        List<String> inputVars = new ArrayList<>(circuit.getInputVariables());
        List<List<String>> table = new ArrayList<>();
        // Header
        List<String> header = new ArrayList<>(inputVars);
        header.add(outputName);
        table.add(header);

        for (Map<String, Boolean> input : combinations) {
            List<String> row = new ArrayList<>();
            for (String var : inputVars) {
                row.add(input.get(var) ? "1" : "0");
            }
            row.add(circuit.evaluate(input) ? "1" : "0");
            table.add(row);
        }
        return table;
    }
}