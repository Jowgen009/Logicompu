package Umag.Logica;

public class ConvertidorExpresionCircuito {
    public static String toExpression(PuertaLogica gate) {
        if (gate instanceof PuertaAND g) {
            return "(" + toExpression(g.getLeft()) + " AND " + toExpression(g.getRight()) + ")";
        } else if (gate instanceof PuertaOR g) {
            return "(" + toExpression(g.getLeft()) + " OR " + toExpression(g.getRight()) + ")";
        } else if (gate instanceof PuertaNOT g) {
            return "(NOT " + toExpression(g.getInput()) + ")";
        } else if (gate instanceof EntradaPuerta) {
            return ((EntradaPuerta) gate).getVariableName();
        } else if (gate != null && gate.getNombre() != null) {
            return gate.getNombre();
        } else {
            return "?";
        }
    }
}