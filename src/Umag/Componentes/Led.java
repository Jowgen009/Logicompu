/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.Componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Led extends Componente {
    public Led(int x, int y) {
        super(x, y, 1, 0);
    }
    
    @Override
    public void evaluar() {
        // LEDs no tienen salidas, solo muestran el estado de la entrada
    }
    
    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(2));

        boolean encendido = !entradas.isEmpty() && entradas.get(0).obtenerEstado();

        int cuerpoX = x;
        int cuerpoY = y + 8;
        int cuerpoAncho = 30;
        int cuerpoAlto = 14;

        // Base negra del LED
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(cuerpoX, cuerpoY, cuerpoAncho, cuerpoAlto);

        // Cúpula redondeada al frente
        g2d.setColor(encendido ? Color.YELLOW : Color.LIGHT_GRAY);
        g2d.fillOval(cuerpoX + cuerpoAncho - 8, cuerpoY - 2, 16, cuerpoAlto + 4); // cúpula sobresaliente

        // Borde del LED
        g2d.setColor(Color.BLACK);
        g2d.drawRect(cuerpoX, cuerpoY, cuerpoAncho, cuerpoAlto);
        g2d.drawOval(cuerpoX + cuerpoAncho - 8, cuerpoY - 2, 16, cuerpoAlto + 4);

        // Terminal de entrada
        g2d.drawLine(cuerpoX - 18, cuerpoY + cuerpoAlto / 2, cuerpoX, cuerpoY + cuerpoAlto / 2);

        // Brillo si está encendido
        if (encendido) {
            g2d.setColor(new Color(255, 255, 100, 120));
            g2d.fillOval(cuerpoX - 10, cuerpoY - 10, 60, 35);
        }

        // Indicador de estado "1" o "0"
        g2d.setColor(Color.BLACK);
        g2d.drawString(encendido ? "1" : "0", cuerpoX + cuerpoAncho + 20, cuerpoY + 10);

        g2d.dispose();
    }


}