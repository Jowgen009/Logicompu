package Umag.Compuertas;

import Umag.Circuito.Pin;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Umag.Logica.ExpresionBooleana;

public class CompuertaOR extends Compuerta implements ExpresionBooleana {
    public CompuertaOR(int x, int y, String nombre) {
        super(x, y, 2, nombre);
    }

    public CompuertaOR(int x, int y) {
        super(x, y, 2);
    }

    @Override
    public void evaluar() {
        if (entradas.size() >= 2) {
            boolean resultado = entradas.get(0).obtenerEstado() ||
                                entradas.get(1).obtenerEstado();
            salidas.get(0).cambiarEstado(resultado);
        } else if (entradas.size() == 1) {
            salidas.get(0).cambiarEstado(entradas.get(0).obtenerEstado());
        } else {
            salidas.get(0).cambiarEstado(false);
        }
    }

    @Override
    public void dibujarCompuerta(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(3));

        // Dibujar las partes curvas de la compuerta OR
        g2d.fillArc(x - 30, y, 83, 40, 270, 180);
        g2d.fillArc(x - 15, y, 70, 40, 270, 180);
        g2d.fillArc(x, y, 55, 40, 270, 90);
        g2d.fillArc(x, y, 55, 50, 0, 90);

        // Dibujar el contorno de la compuerta OR
        g2d.drawArc(x - 11, y, 20, 40, 270, 180);
        g2d.drawArc(x, y, 55, 40, 270, 90);
        g2d.drawArc(x, y, 55, 50, 0, 90);

        // Dibujar las líneas de entrada y salida
        g2d.drawLine(x, y, x + 30, y);  // Entrada superior
        g2d.drawLine(x, y + 40, x + 30, y + 40);  // Entrada inferior
        g2d.drawLine(x + 55, y + 22, x + 75, y + 22);  // Salida

        // Dibujar las líneas de conexión de las entradas
        g2d.drawLine(x - 20, y + 10, x + 6, y + 10);  // Entrada superior
        g2d.drawLine(x - 20, y + 30, x + 6, y + 30);  // Entrada inferior

        // Dibujar el valor de las entradas
        if (!entradas.isEmpty()) {
            g2d.drawString(entradas.get(0).obtenerEstado() ? "1" : "0", x - 25, y + 13);
            if (entradas.size() > 1) {
                g2d.drawString(entradas.get(1).obtenerEstado() ? "1" : "0", x - 25, y + 33);
            }
        }

        // Dibujar el valor de la salida
        if (!salidas.isEmpty()) {
            g2d.drawString(salidas.get(0).obtenerEstado() ? "1" : "0", x + 80, y + 25);
        }

        // Dibuja el nombre de la compuerta
        if (nombre != null) {
            g2d.drawString(nombre, x + 20, y - 5);
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
            return "(" + left + " OR " + right + ")";
        }
        return "?";
    }
}