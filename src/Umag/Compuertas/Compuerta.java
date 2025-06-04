package Umag.Compuertas;

import Umag.Componentes.Componente;
import java.awt.Graphics;

public abstract class Compuerta extends Componente {
    public final int ancho = 20;
    public final int alto = 40;
    protected String nombre;

    public Compuerta(int x, int y, int numEntradas, String nombre) {
        super(x, y, numEntradas, 1);
        this.nombre = nombre;
    }

    public Compuerta(int x, int y, int numEntradas) {
        this(x, y, numEntradas, null);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public abstract void dibujarCompuerta(Graphics g);

    @Override
    public void dibujar(Graphics g) {
        dibujarCompuerta(g);
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
}