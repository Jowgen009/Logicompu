package Umag.Componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import Umag.Logica.ExpresionBooleana;

public class Switch extends Componente implements ExpresionBooleana {
    private boolean estado;
    private String nombre;

    public Switch(int x, int y, String nombre) {
        super(x, y, 0, 1);
        this.estado = false;
        this.nombre = nombre;
    }

    public Switch(int x, int y) {
        this(x, y, "SW");
    }

    public void toggle() {
        estado = !estado;
        salidas.get(0).cambiarEstado(estado);
    }

    @Override
    public void evaluar() {
        salidas.get(0).cambiarEstado(estado);
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(2));

        // Cuerpo redondo del switch
        g2d.setColor(estado ? Color.GREEN : Color.RED);
        g2d.fillOval(x, y, 30, 30); // círculo de 30x30

        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, 30, 30); // borde del círculo

        // Indicador interno de estado
        g2d.setColor(Color.BLACK);
        g2d.fillOval(
            x + (estado ? 6 : 18), // posición cambia si está activado
            y + 9,
            6,
            12
        );

        // Indicador numérico (1 o 0)
        g2d.setColor(Color.WHITE);
        g2d.drawString(estado ? "1" : "0", x + 12, y + 20);

        // Línea de conexión (pin de salida)
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x + 30, y + 15, x + 45, y + 15);

        // Pin (representado como un pequeño círculo azul)
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x + 42, y + 12, 6, 6);

        // Dibuja el nombre del switch
        g2d.setColor(Color.BLACK);
        g2d.drawString(nombre, x + 10, y + 45);

        g2d.dispose();
    }

    public boolean getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toBooleanExpression() {
        return getNombre();
    }
}