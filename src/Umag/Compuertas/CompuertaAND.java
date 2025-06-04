package Umag.Compuertas;

import Umag.Circuito.Pin;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Umag.Logica.ExpresionBooleana;

public class CompuertaAND extends Compuerta implements ExpresionBooleana {
    public CompuertaAND(int x, int y, String nombre) {
        super(x, y, 2, nombre);
    }

    public CompuertaAND(int x, int y) {
        super(x, y, 2);
    }

    @Override
    public void evaluar() {
        if (entradas.size() >= 2) {
            boolean resultado = entradas.get(0).obtenerEstado() &&
                               entradas.get(1).obtenerEstado();
            salidas.get(0).cambiarEstado(resultado);
        } else {
            salidas.get(0).cambiarEstado(false);
        }
    }

    @Override
    public void dibujarCompuerta(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(3));

        g2d.fillArc(x, y, 2 * ancho, alto, 90, -180);
        g2d.fillRect(x, y, ancho, alto);

        g2d.drawLine(x, y, x, y + alto);
        g2d.drawLine(x, y, x + ancho, y);
        g2d.drawLine(x, y + alto, x + ancho, y + alto);
        g2d.drawArc(x, y, 2 * ancho, alto, 90, -180);

        g2d.drawLine(x, y + 12, x - 20, y + 12);
        g2d.drawLine(x, y + 28, x - 20, y + 28);
        g2d.drawLine(x + 2 * ancho, y + alto / 2, x + 2 * ancho + 20, y + alto / 2);

        if (!entradas.isEmpty()) {
            g2d.drawString(entradas.get(0).obtenerEstado() ? "1" : "0", x - 25, y + 15);
            if (entradas.size() > 1) {
                g2d.drawString(entradas.get(1).obtenerEstado() ? "1" : "0", x - 25, y + 35);
            }
        }
        if (!salidas.isEmpty()) {
            g2d.drawString(salidas.get(0).obtenerEstado() ? "1" : "0", x + 2 * ancho + 25, y + 25);
        }

        if (nombre != null) {
            g2d.drawString(nombre, x + ancho, y - 5);
        }

        g2d.dispose();
    }

    @Override
    public String toBooleanExpression() {
        if (getEntradas().size() >= 2) {
            String left = "?";
            String right = "?";
            if (getEntradas().get(0).getConector() != null) {
                Pin pin0 = getEntradas().get(0).getConector().obtenerPinSalida();
                if (pin0 != null && pin0.getComponente() != null) {
                    left = pin0.getComponente().toBooleanExpression();
                }
            }
            if (getEntradas().get(1).getConector() != null) {
                Pin pin1 = getEntradas().get(1).getConector().obtenerPinSalida();
                if (pin1 != null && pin1.getComponente() != null) {
                    right = pin1.getComponente().toBooleanExpression();
                }
            }
            return "(" + left + " AND " + right + ")";
        }
        return "?";
    }
}