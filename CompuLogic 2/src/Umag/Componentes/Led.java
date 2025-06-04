package Umag.Componentes;

import Umag.Circuito.Pin;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import Umag.Logica.ExpresionBooleana;

public class Led extends Componente implements ExpresionBooleana {
    private String nombre;

    public Led(int x, int y, String nombre) {
        super(x, y, 1, 0);
        this.nombre = nombre;
    }

    public Led(int x, int y) {
        this(x, y, "LED");
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void evaluar() {
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

        // Dibuja el nombre del LED debajo
        g2d.setColor(Color.BLUE);
        g2d.drawString(nombre, cuerpoX + 5, cuerpoY + cuerpoAlto + 18);

        g2d.dispose();
    }

    @Override
   public String toBooleanExpression() {
       if (!getEntradas().isEmpty() && getEntradas().get(0).getConector() != null) {
           Pin pinSalida = getEntradas().get(0).getConector().obtenerPinSalida();
           if (pinSalida != null && pinSalida.getComponente() != null) {
               return pinSalida.getComponente().toBooleanExpression();
           }
       }
       return "?";
   }
}