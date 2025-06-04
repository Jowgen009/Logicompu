package Umag.Circuito;

import Umag.Componentes.Componente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conector implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Pin pinSalida;
    private transient Pin pinEntrada; 

    public Conector() {
        this.pinSalida = null;
        this.pinEntrada = null;
    }

    /**
     * Conecta un pin de salida a uno de entrada.
     * Solo permite una entrada y una salida por conector.
     */
    public boolean conectar(Pin pinOrigen, Pin pinDestino) {
        try {
            if (pinOrigen == null || pinDestino == null) {
                throw new Exception("Uno o ambos pines son nulos");
            }

            Pin salida = null;
            Pin entrada = null;

            if (pinOrigen.getTipo().equals("salida") && pinDestino.getTipo().equals("entrada")) {
                salida = pinOrigen;
                entrada = pinDestino;
            } 
            else if (pinOrigen.getTipo().equals("entrada") && pinDestino.getTipo().equals("salida")) {
                salida = pinDestino;
                entrada = pinOrigen;
            } else {
                throw new Exception("¡¡ERROR!! Solo puede conectar un pin de salida a uno de entrada");
            }

            if (entrada.getConector() != null) {
                throw new Exception("¡¡ERROR!! Pin de entrada ya conectado, intente con otro");
            }

            this.pinSalida = salida;
            this.pinEntrada = entrada;

            entrada.conectarA(this);
            salida.conectarA(this);

            return true;

        } catch (Exception e) {
            if (pinEntrada != null && pinEntrada.getComponente() != null &&
                pinEntrada.getComponente().getCircuito() != null) {
                pinEntrada.getComponente().getCircuito().mostrarError(e.getMessage());
            }
            return false;
        }
    }

    /**
     * Desconecta ambos pines del conector.
     */
    public void desconectar() {
        if (pinSalida != null) {
            pinSalida.desconectar();
            pinSalida = null;
        }
        if (pinEntrada != null) {
            pinEntrada.desconectar();
            pinEntrada = null;
        }
    }

    /**
     * Propaga el estado de la salida al pin de entrada.
     */
    public void propagarEstado() {
        if (pinSalida == null || pinEntrada == null) {
            return;
        }
        boolean estado = pinSalida.obtenerEstado();
        if (pinEntrada.obtenerEstado() != estado) {
            pinEntrada.cambiarEstado(estado);
            if (pinEntrada.getComponente() != null) {
                pinEntrada.getComponente().evaluar();
            }
        }
    }

    public Pin obtenerPinEntrada() {
        return pinEntrada;
    }

    public Pin obtenerPinSalida() {
        return pinSalida;
    }

    public List<Pin> obtenerPinesEntrada() {
        List<Pin> pines = new ArrayList<>();
        if (pinEntrada != null) {
            pines.add(pinEntrada);
        }
        return pines;
    }

    /**
     * Conecta este conector a un pin de entrada (solo se admite una entrada por conector).
     */
    public boolean agregarConexion(Pin pinEntrada) {
        if (pinEntrada == null || !"entrada".equals(pinEntrada.getTipo())) {
            return false;
        }
        if (pinEntrada.getConector() != null) {
            return false;
        }

        if (this.pinEntrada != null) {
            this.pinEntrada.desconectar();
        }

        this.pinEntrada = pinEntrada;
        pinEntrada.conectarA(this);
        return true;
    }

    public void removerConexion(Pin pinEntrada) {
        if (pinEntrada != null && pinEntrada.equals(this.pinEntrada)) {
            this.pinEntrada.desconectar();
            this.pinEntrada = null;
        }
    }

    /**
     * Reconstruye las referencias a los pines tras la deserialización.
     */
    public void reconstruir(Map<String, Componente> mapaComponentes, 
                            String idSalida, int indexSalida,
                            String idEntrada, int indexEntrada) {

        if (idSalida != null && idEntrada != null) {
            Componente compSalida = mapaComponentes.get(idSalida);
            Componente compEntrada = mapaComponentes.get(idEntrada);

            if (compSalida != null && compEntrada != null && 
                indexSalida < compSalida.getSalidas().size() && 
                indexEntrada < compEntrada.getEntradas().size()) {

                this.pinSalida = compSalida.getSalidas().get(indexSalida);
                this.pinEntrada = compEntrada.getEntradas().get(indexEntrada);

                if (this.pinSalida != null && this.pinEntrada != null) {
                    this.pinSalida.conectarA(this);
                    this.pinEntrada.conectarA(this);
                }
            }
        }
    }

    public static class ConnectionData implements Serializable {
        private static final long serialVersionUID = 1L;
        String idComponente;
        int indexPin;

        public ConnectionData(String idComponente, int indexPin) {
            this.idComponente = idComponente;
            this.indexPin = indexPin;
        }
    }

    public ConnectionData getSalidaData() {
        if (pinSalida == null || pinSalida.getComponente() == null) {
            return null;
        }
        return new ConnectionData(
            pinSalida.getComponente().getId(),
            pinSalida.getComponente().getSalidas().indexOf(pinSalida)
        );
    }

    public ConnectionData getEntradaData() {
        if (pinEntrada == null || pinEntrada.getComponente() == null) {
            return null;
        }
        return new ConnectionData(
            pinEntrada.getComponente().getId(),
            pinEntrada.getComponente().getEntradas().indexOf(pinEntrada)
        );
    }

    public boolean contienePin(Pin pin) {
        return pin != null && (pin.equals(pinSalida) || pin.equals(pinEntrada));
    }
}