package Umag.Logica;

import Umag.Circuito.Circuito;
import Umag.Componentes.Switch;
import Umag.Componentes.Led;
import Umag.Compuertas.CompuertaAND;
import Umag.Compuertas.CompuertaOR;
import Umag.Compuertas.CompuertaNOT;
import Umag.Componentes.Componente;

import java.util.HashMap;
import java.util.Map;

public class AnalizadorExpresionBooleana {
    private final String expr;
    private int pos;
    private final Circuito circuito;
    private final Map<String, Switch> switches = new HashMap<>();
    private int compuertaCounter = 1;  

    public AnalizadorExpresionBooleana(String expr, Circuito circuito) {
        this.expr = expr;
        this.pos = 0;
        this.circuito = circuito;
    }

    public Led analizador(String nombreLed) {
        Componente salida = analizadorOr();
        skipWhitespace();
        if (pos != expr.length())
            throw new RuntimeException("Expresión mal formada cerca de: '" + expr.substring(pos) + "'");
        Led led = new Led(200, 200, nombreLed);
        circuito.agregarComponente(led);
        circuito.conectar(salida, 0, led, 0);
        return led;
    }

    public Led analizador() {
        return analizador("OUT");
    }

    private Componente analizadorOr() {
        Componente left = analizadorAnd();
        skipWhitespace();
        while (matchOp("OR")) {
            Componente right = analizadorAnd();
            String nombre = "G" + (compuertaCounter++);
            CompuertaOR or = new CompuertaOR(100, 100, nombre);
            circuito.agregarComponente(or);
            circuito.conectar(left, 0, or, 0);
            circuito.conectar(right, 0, or, 1);
            left = or;
            skipWhitespace();
        }
        return left;
    }

    private Componente analizadorAnd() {
        Componente left = analizadorNot();
        skipWhitespace();
        while (matchOp("AND")) {
            Componente right = analizadorNot();
            String nombre = "G" + (compuertaCounter++);
            CompuertaAND and = new CompuertaAND(100, 100, nombre);
            circuito.agregarComponente(and);
            circuito.conectar(left, 0, and, 0);
            circuito.conectar(right, 0, and, 1);
            left = and;
            skipWhitespace();
        }
        return left;
    }

    private Componente analizadorNot() {
        skipWhitespace();
        if (matchOp("NOT")) {
            Componente input = analizadorAtom();
            String nombre = "G" + (compuertaCounter++);
            CompuertaNOT not = new CompuertaNOT(100, 100, nombre);
            circuito.agregarComponente(not);
            circuito.conectar(input, 0, not, 0);
            return not;
        }
        return analizadorAtom();
    }

    private Componente analizadorAtom() {
        skipWhitespace();
        if (match("(")) {
            Componente puerta = analizadorOr();
            expect(")");
            return puerta;
        }
        String var = analizadorVariable();
        if (!switches.containsKey(var)) {
            Switch sw = new Switch(50, 50, var); 
            circuito.agregarComponente(sw);
            switches.put(var, sw);
        }
        return switches.get(var);
    }

    private String analizadorVariable() {
        skipWhitespace();
        StringBuilder sb = new StringBuilder();
        if (pos < expr.length() && Character.isLetter(expr.charAt(pos))) {
            sb.append(expr.charAt(pos++));
            while (pos < expr.length() && (Character.isLetterOrDigit(expr.charAt(pos)))) {
                sb.append(expr.charAt(pos++));
            }
        }
        if (sb.length() == 0) throw new RuntimeException("Esperada variable en la posición " + pos);
        return sb.toString();
    }

    private boolean matchOp(String op) {
        skipWhitespace();
        int len = op.length();
        if (pos + len <= expr.length() && expr.regionMatches(true, pos, op, 0, len)) {
            int end = pos + len;
            if ((end == expr.length()) ||
                !Character.isLetter(expr.charAt(end))) {
                pos += len;
                skipWhitespace();
                return true;
            }
        }
        return false;
    }

    private boolean match(String symbol) {
        skipWhitespace();
        if (expr.startsWith(symbol, pos)) {
            pos += symbol.length();
            skipWhitespace();
            return true;
        }
        return false;
    }

    private void expect(String symbol) {
        if (!match(symbol)) throw new RuntimeException("Se esperaba '" + symbol + "' en la posición " + pos);
    }

    private void skipWhitespace() {
        while (pos < expr.length() && Character.isWhitespace(expr.charAt(pos))) {
            pos++;
        }
    }
}