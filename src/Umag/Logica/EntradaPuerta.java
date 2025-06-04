package Umag.Logica;

import java.util.Map;
import java.util.Set;
import java.util.Collections;

public class EntradaPuerta implements PuertaLogica {
    private final String variableName;

    public EntradaPuerta(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public boolean evaluate(Map<String, Boolean> inputs) {
        Boolean value = inputs.get(variableName);
        if (value == null) {
            throw new IllegalArgumentException("Variable '" + variableName + "' not found in inputs.");
        }
        return value;
    }

    @Override
    public Set<String> getInputVariables() {
        return Collections.singleton(variableName);
    }
}