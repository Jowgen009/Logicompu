package Umag.Circuito;

import Umag.Componentes.Componente;
import Umag.Componentes.Switch;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;
import Umag.GUI.MiPanel;

public class Circuito implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private List<Componente> componentes = new ArrayList<>();
    private List<Conector> conexiones = new ArrayList<>();
    private boolean modificado = false;
    private transient String rutaArchivo;
    private transient MiPanel panelReferencia;

    public Circuito(String nombre) {
        this.nombre = nombre;
    }

    public void setPanelReferencia(MiPanel panel) {
        this.panelReferencia = panel;
    }

    public void mostrarError(String mensaje) {
        if (panelReferencia != null) {
            panelReferencia.mostrarMensajeError(mensaje);
        } else {
            JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        List<Conector> conexionesValidas = new ArrayList<>();
        for (Conector conector : conexiones) {
            if (conector != null &&
                conector.obtenerPinSalida() != null &&
                conector.obtenerPinEntrada() != null &&
                conector.obtenerPinSalida().getComponente() != null &&
                conector.obtenerPinEntrada().getComponente() != null) {
                conexionesValidas.add(conector);
            }
        }

        out.writeInt(conexionesValidas.size());
        for (Conector conector : conexionesValidas) {
            out.writeObject(conector.obtenerPinSalida().getComponente().getId());
            out.writeInt(conector.obtenerPinSalida().getComponente().getSalidas().indexOf(conector.obtenerPinSalida()));
            out.writeObject(conector.obtenerPinEntrada().getComponente().getId());
            out.writeInt(conector.obtenerPinEntrada().getComponente().getEntradas().indexOf(conector.obtenerPinEntrada()));
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        if (componentes == null) componentes = new ArrayList<>();
        conexiones = new ArrayList<>();

        for (Componente comp : componentes) {
            if (comp != null) {
                int numEntradas = comp instanceof Umag.Compuertas.CompuertaAND ? 2 :
                                  comp instanceof Umag.Compuertas.CompuertaOR ? 2 :
                                  comp instanceof Umag.Compuertas.CompuertaNOT ? 1 :
                                  comp instanceof Umag.Componentes.Switch ? 0 :
                                  comp instanceof Umag.Componentes.Led ? 1 : 0;
                int numSalidas = comp instanceof Umag.Componentes.Led ? 0 : 1;

                comp.inicializarPines(numEntradas, numSalidas);

                for (Pin pin : comp.getEntradas()) {
                    if (pin != null) pin.setComponente(comp);
                }
                for (Pin pin : comp.getSalidas()) {
                    if (pin != null) pin.setComponente(comp);
                }
            }
        }

        Map<String, Componente> mapaComponentes = new HashMap<>();
        for (Componente c : componentes) {
            if (c != null) mapaComponentes.put(c.getId(), c);
        }

        int numConectores = in.readInt();
        for (int i = 0; i < numConectores; i++) {
            try {
                String idSalida = (String) in.readObject();
                int indexSalida = in.readInt();
                String idEntrada = (String) in.readObject();
                int indexEntrada = in.readInt();

                Componente compSalida = mapaComponentes.get(idSalida);
                Componente compEntrada = mapaComponentes.get(idEntrada);

                if (compSalida != null && compEntrada != null &&
                    indexSalida < compSalida.getSalidas().size() &&
                    indexEntrada < compEntrada.getEntradas().size()) {

                    Pin pinSalida = compSalida.getSalidas().get(indexSalida);
                    Pin pinEntrada = compEntrada.getEntradas().get(indexEntrada);

                    Conector nuevoConector = new Conector();
                    if (nuevoConector.conectar(pinSalida, pinEntrada)) {
                        conexiones.add(nuevoConector);
                    }
                }
            } catch (Exception e) {
                if (panelReferencia != null)
                    panelReferencia.mostrarMensajeError("Error reconstruyendo conector #" + i + ": " + e.getMessage());
            }
        }

        evaluar();
    }

    public void toggleSwitch(Switch sw) {
        try {
            if (sw == null) {
                throw new Exception("El switch no puede ser nulo");
            }
            if (!componentes.contains(sw)) {
                throw new Exception("El switch no existe en este circuito");
            }

            sw.toggle();
            this.modificado = true;
            this.evaluar();
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    public void agregarComponente(Componente componente) {
        try {
            if (componente == null) {
                throw new Exception("No se puede agregar un componente nulo");
            }
            componentes.add(componente);
            modificado = true;
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    public void conectar(Componente origen, int salidaIdx, Componente destino, int entradaIdx) {
        try {
            if (origen == null || destino == null) {
                throw new Exception("Los componentes no pueden ser nulos");
            }
            List<Pin> salidas = origen.getSalidas();
            List<Pin> entradas = destino.getEntradas();
            if (salidaIdx < 0 || salidaIdx >= salidas.size()) {
                throw new Exception("Índice de salida inválido para " + origen.getNombre());
            }
            if (entradaIdx < 0 || entradaIdx >= entradas.size()) {
                throw new Exception("Índice de entrada inválido para " + destino.getNombre());
            }
            Pin pinSalida = salidas.get(salidaIdx);
            Pin pinEntrada = entradas.get(entradaIdx);
            Conector nuevoConector = new Conector();
            if (!nuevoConector.conectar(pinSalida, pinEntrada)) {
                throw new Exception("No se pudo crear la conexión");
            }
            conexiones.add(nuevoConector);
            modificado = true;
            destino.evaluar();
        } catch (Exception e) {
            mostrarError("Error de conexión: " + e.getMessage());
        }
    }

    public void evaluar() {
        for (Componente componente : componentes) {
            if (componente != null) {
                componente.evaluar();
            }
        }
        for (Conector conector : conexiones) {
            if (conector != null) {
                conector.propagarEstado();
            }
        }
    }

    public void eliminarComponente(Componente componente) {
        if (componente == null) return;

        List<Conector> conexionesAEliminar = new ArrayList<>();

        for (Conector conector : conexiones) {
            if (conector != null) {
                if ((conector.obtenerPinSalida() != null &&
                     conector.obtenerPinSalida().getComponente() == componente) ||
                    (conector.obtenerPinEntrada() != null &&
                     conector.obtenerPinEntrada().getComponente() == componente)) {
                    conexionesAEliminar.add(conector);
                }
            }
        }

        for (Conector conector : conexionesAEliminar) {
            eliminarConector(conector);
        }

        componentes.remove(componente);
        modificado = true;
        evaluar();
    }

    public void eliminarConector(Conector conector) {
        if (conector == null) return;

        conector.desconectar();
        conexiones.remove(conector);
        modificado = true;
        evaluar();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; this.modificado = true; }
    public List<Componente> getComponentes() { return new ArrayList<>(componentes); }
    public List<Conector> getConexiones() { return new ArrayList<>(conexiones); }
    public boolean isModificado() { return modificado; }
    public void setModificado(boolean modificado) { this.modificado = modificado; }
    public String getRutaArchivo() { return rutaArchivo; }
    public void setRutaArchivo(String ruta) { this.rutaArchivo = ruta; }

    public Componente buscarComponentePorId(String id) {
        for (Componente c : componentes) {
            if (c != null && c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Conector> buscarConectoresDeComponente(Componente componente) {
        List<Conector> resultado = new ArrayList<>();
        if (componente == null) return resultado;

        for (Conector c : conexiones) {
            if (c != null &&
                ((c.obtenerPinSalida() != null && c.obtenerPinSalida().getComponente() == componente) ||
                 (c.obtenerPinEntrada() != null && c.obtenerPinEntrada().getComponente() == componente))) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void limpiarConexionesInvalidas() {
        conexiones.removeIf(conector ->
            conector == null ||
            conector.obtenerPinSalida() == null ||
            conector.obtenerPinEntrada() == null ||
            conector.obtenerPinSalida().getComponente() == null ||
            conector.obtenerPinEntrada().getComponente() == null
        );
    }
}