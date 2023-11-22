package Jugador;

public class Jugador implements Comparable<Jugador> {
    private Posicio posicio;
    private int puntuacio;

    public Jugador(Posicio posicio, int puntuacio) {
        this.posicio = posicio;
        this.puntuacio = puntuacio;
    }

    @Override
    public int compareTo(Jugador otroJugador) {
        int comparacionPosicio = this.posicio.compareTo(otroJugador.posicio);
        if (comparacionPosicio == 0) {
            return Integer.compare(this.puntuacio, otroJugador.puntuacio);
        }
        return comparacionPosicio;
    }

    @Override
    public String toString() {
        return posicio + " " + puntuacio;
    }
}
