package Umag.Compuertas;

import Umag.Circuito.Pin;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Umag.Logica.ExpresionBooleana;

public class CompuertaNOT extends Compuerta implements ExpresionBooleana {
    public CompuertaNOT(int x, int y, String nombre) {
        super(x, y, 1, nombre);
    }

    public CompuertaNOT(int x, int y) {
        super(x, y, 1);
    }

    @Override
    public void evaluar() {
        if (!entradas.isEmpty()) {
            boolean resultado = !entradas.get(0).obtenerEstado();
            salidas.get(0).cambiarEstado(resultado);
        } else {
            salidas.get(0).cambiarEstado(true);
        }
    }

    @Override
    public void dibujarCompuerta(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(3));

        int[] xPoints = {x, x, x + 40};
        int[] yPoints = {y, y + 40, y + 20};

        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.drawPolygon(xPoints, yPoints, 3);

        g2d.fillOval(x + 40, y + 15, 10, 10);
        g2d.drawOval(x + 40, y + 15, 10, 10);

        g2d.drawLine(x - 20, y + 20, x, y + 20);
        g2d.drawLine(x + 50, y + 20, x + 70, y + 20);

        if (!entradas.isEmpty()) {
            g2d.drawString(entradas.get(0).obtenerEstado() ? "1" : "0", x - 25, y + 25);
        }
        if (!salidas.isEmpty()) {
            g2d.drawString(salidas.get(0).obtenerEstado() ? "1" : "0", x + 75, y + 25);
        }

        // Dibuja el nombre de la compuerta
        if (nombre != null) {
            g2d.drawString(nombre, x + 15, y - 5);
        }

        g2d.dispose();
    }

    @Override
    public String toBooleanExpression() {
        if (getEntradas().size() >= 1) {
            String expr = "?";
            if (getEntradas().get(0).getConector() != null) {
                Pin pin0 = getEntradas().get(0).getConector().obtenerPinSalida();
                if (pin0 != null && pin0.getComponente() != null) {
                    expr = pin0.getComponente().toBooleanExpression();
                }
            }
            return "(NOT " + expr + ")";
        }
        return "?";
    }
}