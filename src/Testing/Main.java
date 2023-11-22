package Testing;

import Estructura.AcbEnll;
import Estructura.ArbreException;
import Jugador.Posicio;
import Jugador.Jugador;
import Keyboard.Keyboard;


public class Main {
    public static void main(String[] args) {
        AcbEnll<Jugador> arbre = new AcbEnll<>();
        AcbEnll<Jugador> arbreClonat = null;


        int opcio;
        do {
            System.out.println("Opcions:");
            System.out.println("1. Ingresar jugador");
            System.out.println("2. Eliminar jugador");
            System.out.println("3. Visualitzar un dels dos arbres");
            System.out.println("4. Clonar arbre");
            System.out.println("5. Cardinalitat d'un dels dos arbres");
            System.out.println("6. Sortir");
            System.out.print("Ingresa la opció: ");
            opcio = Keyboard.readInt();
            System.out.println();

            switch (opcio) {
                case 1:
                    try {
                        Posicio posicio = obtenirPosicioPerNumero();

                        System.out.println("Indica la puntuació del jugador: [0,1000] ");
                        int puntuacio = Keyboard.readInt();
                        System.out.println();
                        while (puntuacio<0 || puntuacio>1000){
                            System.out.println("Error en la puntuació ");
                            System.out.println("Indica la puntuació del jugador: [0,1000] ");
                            puntuacio = Keyboard.readInt();
                            System.out.println();
                        }

                        try{
                            Jugador nouJugador = new Jugador(posicio, puntuacio);
                            arbre.inserir(nouJugador);
                        }catch (IllegalArgumentException e) {
                            System.out.println("Error al afegir el jugador: " + e.getMessage());
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Opció de posició no válida. Si us plau, escull una posición válida.");

                    }


                    break;

                case 2:
                    if(arbre.abBuit()){
                        System.out.println("Error arbre sense jugadors.");

                    }
                    else{
                        Posicio posicioEliminar = obtenirPosicioPerNumero();
                        System.out.print("Indica la puntuació del jugador a eliminar: ");
                        int puntuacioEliminar = Keyboard.readInt();

                        Jugador jugadorEliminar = new Jugador(posicioEliminar, puntuacioEliminar);

                        try {
                            arbre.esborrar(jugadorEliminar);
                            System.out.println( posicioEliminar + " " + puntuacioEliminar +  " eliminat amb éxit.");
                        } catch (ArbreException e) {
                            System.out.println("Error al eliminar el jugador: " + e.getMessage());
                        }
                    }

                    break;

                case 3:
                    System.out.println("Indica quin arbre vols mostrar: ");
                    System.out.println("1.- Actual ");
                    System.out.println("2.- Clonat ");
                    System.out.println("Tria l'arbre [1,2]");
                    int opcio2 = Keyboard.readInt();
                    System.out.println();
                    switch (opcio2){
                        case 1:
                            if(arbre.abBuit()){
                                System.out.println("Error arbre sense jugadors.");
                            }
                            else{
                                System.out.println("Arbre actual:");
                                visualitzarArbre(arbre);
                                break;
                            }
                        case 2:
                            if (arbreClonat != null) {
                                System.out.println("Arbre Clonat:");
                                visualitzarArbre(arbreClonat);
                            } else {
                                System.out.println("El arbre clonat encara no ha estat creat.");
                            }
                            break;
                    }
                    break;

                case 4:
                    if(arbre.abBuit()){
                        System.out.println("Error arbre sense jugadors.");
                    }
                    else{
                        arbreClonat = (AcbEnll<Jugador>) arbre.clone();
                        System.out.println("Arbre clonat amb éxit.");
                    }

                    break;

                case 5:
                    System.out.println("Indica quin arbre vols saber la cardinalitat: ");
                    System.out.println("1.- Actual ");
                    System.out.println("2.- Clonat ");
                    System.out.println("Tria l'arbre [1,2]");
                    int opcio3 = Keyboard.readInt();
                    System.out.println();
                    switch (opcio3) {
                        case 1:
                            if(arbre.abBuit()){
                                System.out.println("Error arbre sense jugadors.");
                            }else{
                                int cardinalitatOriginal = arbre.calcularCardinalitat();
                                System.out.println("Cardinalitat del arbre original: " + cardinalitatOriginal);
                            }
                            break;

                        case 2:
                            if(arbreClonat==null){
                                System.out.println("Error arbre clonat no creat encara.");
                            }
                            else{
                                int cardinalidatClonat = arbreClonat.calcularCardinalitat();
                                System.out.println("Cardinalitat del arbre clonat: " + cardinalidatClonat);
                            }
                            break;

                    }
                    break;

                case 6:
                    System.out.println("Sortin del programa.");
                    break;

                default:
                    System.out.println("Opció no válida. Intenta-ho de nou.");
            }
            System.out.println("\n----------------\n");

        } while (opcio != 7);
    }
    private static Posicio obtenirPosicioPerNumero() {
        System.out.println("Indica la posició del jugador: ");
        System.out.println("1.- Base ");
        System.out.println("2.- Escorta ");
        System.out.println("3.- Aler ");
        System.out.println("4.- AlerPivot ");
        System.out.println("5.- Pivot ");
        System.out.println("Tria la posició [1,5]");
        int numPosicio = Keyboard.readInt();
        switch (numPosicio) {
            case 1:
                return Posicio.Base;
            case 2:
                return Posicio.Escorta;
            case 3:
                return Posicio.Aler;
            case 4:
                return Posicio.AlerPivot;
            case 5:
                return Posicio.Pivot;
            default:
                throw new IllegalArgumentException("Número de posició no vàlida");
        }
    }
    private static void visualitzarArbre(AcbEnll<Jugador> arbre) {
        System.out.println("Indica en quin ordre vols mostrar els jugadors");
        System.out.println("1.Ascendent");
        System.out.println("2.Descendent");
        int ordre = Keyboard.readInt();
        System.out.println();

        if (ordre== 1){
            arbre.iniRecorregut(true);
        }else{
            arbre.iniRecorregut(false);
        }
        System.out.println("Arbre:");
        try {
            while (!arbre.finalRecorregut()) {
                Jugador jugador = arbre.segRecorregut();
                System.out.println(jugador);
            }
        } catch (ArbreException e) {
            System.out.println("Error al recorrer l'arbre: " + e.getMessage());
        }
    }
}
