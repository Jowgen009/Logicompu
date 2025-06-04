package Umag.Circuito;

import Umag.Compuertas.Compuerta;
import Umag.Componentes.Componente;

public class Excepciones extends Exception {
    public Excepciones(String message) {
        super(message);
    }

    public void validarCircuito(Iterable<Componente> componentes) throws Excepciones {
        for (Componente c : componentes) {
            if (c instanceof Compuerta) {
                for (Pin entrada : c.getEntradas()) {
                    if (entrada.getConector() == null) {
                        throw new Excepciones("La compuerta " + c.getId() + 
                                            " Tiene las entradas desconectadas");
                    }
                }
            }
        }
    }
}