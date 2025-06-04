/**
 * POR JOSÉ ROMERO PARA PROGRAMACION ORIENTADA A OBJETOS DEL PROFE NAYIB
 */
package Umag.Logica;

import java.util.Map;
import java.util.Set;

/**
 * Interfaz lógica general para compuertas y adaptadores de circuitos.
 * Permite evaluar la salida de la compuerta/circuito y obtener las variables de entrada.
 * El método getNombre() puede ser sobrescrito por compuertas/componentes para mostrar sus nombres en tablas y expresiones.
 */
public interface PuertaLogica {
    /**
     * Evalúa la compuerta/circuito con un conjunto de valores de entrada.
     * @param inputs Mapa de nombre de variable a valor booleano de entrada.
     * @return Valor de la salida principal.
     */
    boolean evaluate(Map<String, Boolean> inputs);

    /**
     * Devuelve el conjunto de nombres de variables de entrada.
     * @return 
     */
    Set<String> getInputVariables();

    /**
     * Devuelve el nombre de la compuerta/circuito (útil para mostrar en tablas de verdad o expresiones).
     * Por defecto retorna null.
     * @return 
     */
    default String getNombre() {
        return null;
    }
}