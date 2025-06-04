package Umag.Logica;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class PuertaAND implements PuertaLogica {
    private final PuertaLogica left;
    private final PuertaLogica right;

    public PuertaAND(PuertaLogica left, PuertaLogica right) {
        this.left = left;
        this.right = right;
    }

    public PuertaLogica getLeft() {
        return left;
    }

    public PuertaLogica getRight() {
        return right;
    }

    @Override
    public boolean evaluate(Map<String, Boolean> inputs) {
        return left.evaluate(inputs) && right.evaluate(inputs);
    }

    @Override
    public Set<String> getInputVariables() {
        Set<String> vars = new HashSet<>(left.getInputVariables());
        vars.addAll(right.getInputVariables());
        return vars;
    }
}