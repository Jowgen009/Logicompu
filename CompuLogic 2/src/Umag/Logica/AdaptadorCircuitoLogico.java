package Umag.Logica;

import Umag.Circuito.Circuito;
import Umag.Componentes.Switch;
import Umag.Componentes.Led;
import Umag.Componentes.Componente;

import java.util.*;

public class AdaptadorCircuitoLogico implements PuertaLogica {
    private final Circuito circuito;
    private Led ledPrincipal; 

    public AdaptadorCircuitoLogico(Circuito circuito) {
        this.circuito = circuito;
        for (Componente comp : circuito.getComponentes()) {
            if (comp instanceof Led led) {
                this.ledPrincipal = led;
                break;
            }
        }
    }

    @Override
    public boolean evaluate(Map<String, Boolean> inputs) {
        for (Componente comp : circuito.getComponentes()) {
            if (comp instanceof Switch sw) {
                String varName = sw.getNombre();  
                if (inputs.containsKey(varName)) {
                    if (sw.getEstado() != inputs.get(varName)) {
                        sw.toggle();
                    }
                }
            }
        }
        circuito.evaluar();

        for (Componente comp : circuito.getComponentes()) {
            if (comp instanceof Led led) {
                if (!led.getEntradas().isEmpty()) {
                    return led.getEntradas().get(0).obtenerEstado();
                }
            }
        }
        return false;
    }

    @Override
    public Set<String> getInputVariables() {
        Set<String> vars = new LinkedHashSet<>();
        for (Componente comp : circuito.getComponentes()) {
            if (comp instanceof Switch sw) {
                vars.add(sw.getNombre());  
            }
        }
        return vars;
    }

    @Override
    public String getNombre() {
        return (ledPrincipal != null) ? ledPrincipal.getNombre() : "Output";
    }
}