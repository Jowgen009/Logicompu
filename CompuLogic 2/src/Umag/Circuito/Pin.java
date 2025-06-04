package Umag.Circuito;

import Umag.Componentes.Componente;
import java.io.Serializable;

public class Pin implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;  
    private boolean estado;
    private Componente componente;
    private transient Conector conector;

    public Pin(String tipo, Componente componente) {
        this.tipo = tipo;
        this.componente = componente;
        this.estado = false;
        this.conector = null;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public boolean obtenerEstado() {
        return estado;
    }

    public void cambiarEstado(boolean nuevoEstado) {
        this.estado = nuevoEstado;
        if (conector != null && "salida".equals(tipo)) {
            conector.propagarEstado();
        }
    }

    public void conectarA(Conector conector) {
        try {
            if (conector == null) {
                throw new Exception("El conector no puede ser nulo");
            }

            if ("entrada".equals(tipo) && this.conector != null) {
                throw new Exception("Pin de entrada ya conectado");
            }

            this.conector = conector;

            if ("entrada".equals(tipo) && conector.obtenerPinSalida() != null) {
                this.estado = conector.obtenerPinSalida().obtenerEstado();
                if (componente != null) {
                    componente.evaluar();
                }
            }
        } catch (Exception e) {
            if (componente != null && componente.getCircuito() != null) {
                componente.getCircuito().mostrarError(e.getMessage());
            }
        }
    }

    public void desconectar() {
        System.out.println("[DEBUG] Desconectando pin " + this);

        if ("entrada".equals(tipo)) {
            this.estado = false;
            System.out.println("[DEBUG]   Estado resetado a false");

            if (componente != null) {
                System.out.println("[DEBUG]   Evaluando componente padre");
                componente.evaluar();
            }
        }
        this.conector = null;
    }

    public String getTipo() {
        return tipo;
    }

    public Componente getComponente() {
        return componente;
    }

    public Conector getConector() {
        return conector;
    }

    public Componente getComponenteOrigen() {
        if ("entrada".equals(tipo) && conector != null && conector.obtenerPinSalida() != null) {
            return conector.obtenerPinSalida().getComponente();
        }
        return null;
    }

}