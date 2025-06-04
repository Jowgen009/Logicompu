package Umag.Logica;

import java.util.Map;
import java.util.Set;

public class PuertaNOT implements PuertaLogica {
    private final PuertaLogica input;

    public PuertaNOT(PuertaLogica input) {
        this.input = input;
    }

    public PuertaLogica getInput() {
        return input;
    }

    @Override
    public boolean evaluate(Map<String, Boolean> inputs) {
        return !input.evaluate(inputs);
    }

    @Override
    public Set<String> getInputVariables() {
        return input.getInputVariables();
    }
}