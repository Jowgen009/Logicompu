/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.Compuertas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CompuertaOR extends Compuerta {
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

        g2d.dispose();
    }

}