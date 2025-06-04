package Umag.Componentes;

import Umag.Circuito.Circuito;
import Umag.Circuito.Conector;
import Umag.Circuito.Pin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Umag.Logica.ExpresionBooleana;

public abstract class Componente implements Serializable, ExpresionBooleana {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected int x, y;
    protected transient List<Pin> entradas;
    protected transient List<Pin> salidas;
    protected transient Circuito circuito; // Referencia al circuito padre
    protected String nombre; // <--- añade este campo para compatibilidad

    public Componente(int x, int y, int numEntradas, int numSalidas) {
        this.x = x;
        this.y = y;
        this.id = java.util.UUID.randomUUID().toString();
        inicializarPines(numEntradas, numSalidas);
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
        if (entradas != null) {
            for (Pin pin : entradas) {
                if (pin != null) pin.setComponente(this);
            }
        }
        if (salidas != null) {
            for (Pin pin : salidas) {
                if (pin != null) pin.setComponente(this);
            }
        }
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void inicializarPines(int numEntradas, int numSalidas) {
        this.entradas = new ArrayList<>();
        for (int i = 0; i < numEntradas; i++) {
            Pin pin = new Pin("entrada", this);
            entradas.add(pin);
        }

        this.salidas = new ArrayList<>();
        for (int i = 0; i < numSalidas; i++) {
            Pin pin = new Pin("salida", this);
            salidas.add(pin);
        }
        setCircuito(this.circuito);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public List<Boolean> obtenerSalidas() {
        List<Boolean> estados = new ArrayList<>();
        if (salidas != null) {
            for (Pin pin : salidas) {
                if (pin != null) estados.add(pin.obtenerEstado());
            }
        }
        return estados;
    }

    public abstract void evaluar();

    public void conectarEntrada(int numEntrada, Conector conector) {
        try {
            if (entradas == null || numEntrada < 0 || numEntrada >= entradas.size()) {
                throw new IndexOutOfBoundsException("Número de entrada inválido");
            }
            if (conector == null) {
                throw new IllegalArgumentException("El conector no puede ser nulo");
            }
            Pin pin = entradas.get(numEntrada);
            if (pin.getConector() != null && pin.getConector() != conector) {
                throw new IllegalStateException("La entrada " + numEntrada + " ya está conectada.");
            }
            conector.agregarConexion(pin);
        } catch (Exception e) {
            if (circuito != null) {
                circuito.mostrarError(e.getMessage());
            }
        }
    }

    public void desconectarCable(Conector conector) {
        if (conector == null || entradas == null) return;
        for (Pin pin : entradas) {
            if (pin != null && conector.equals(pin.getConector())) {
                conector.removerConexion(pin);
            }
        }
    }

    public void mover(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void dibujar(java.awt.Graphics g);

    public String getId() {
        return id;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public List<Pin> getEntradas() {
        return entradas == null ? new ArrayList<>() : new ArrayList<>(entradas);
    }

    public List<Pin> getSalidas() {
        return salidas == null ? new ArrayList<>() : new ArrayList<>(salidas);
    }

    // MÉTODO NECESARIO PARA CIRCUITO Y COMPATIBILIDAD
    public String getNombre() {
        return (nombre != null) ? nombre : id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public abstract String toBooleanExpression();
}