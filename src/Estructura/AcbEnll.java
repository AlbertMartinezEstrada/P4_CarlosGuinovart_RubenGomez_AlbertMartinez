package Estructura;
import Jugador.Posicio;
import Jugador.Jugador;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E>, Cloneable {
    private static class NodeA<T extends Comparable<T>> {
        T contingut;
        NodeA<T> left, right;

        public NodeA(T contingut) {
            this.contingut = contingut;
            this.left = this.right = null;
        }

        public Object clone() {
            NodeA<T> nouNode = new NodeA<>(contingut);
            if (left != null) {
                nouNode.left = (NodeA<T>) left.clone();
            }
            if (right != null) {
                nouNode.right = (NodeA<T>) right.clone();
            }
            return nouNode;
        }
    }

    private NodeA<E> arrel;
    private Queue<E> cuaRecurregut;

    public AcbEnll() {
        this.arrel = null;
        this.cuaRecurregut = new LinkedList<>();
    }

    @Override
    public E arrel() throws ArbreException {
       if (arrel==null){
           throw new ArbreException("L'arbre es buit");
       }
       return arrel.contingut;
    }

    @Override
    public Acb<E> fillEsquerre() {
        AcbEnll<E> subArbreEsquerra = new AcbEnll<>();
        if (arrel !=null && arrel.left != null){
            subArbreEsquerra.arrel = arrel.left;
        }

        return subArbreEsquerra;
    }

    @Override
    public Acb<E> fillDret() {
        AcbEnll<E> subArbreDret = new AcbEnll<>();
        if (arrel !=null && arrel.right != null){
            subArbreDret.arrel = arrel.right;
        }
        return subArbreDret;
    }

    @Override
    public boolean abBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        arrel=null;
    }

    @Override
    public void inserir(E e) {
        arrel = inserirRecursiu(arrel, e);
    }

    private NodeA<E> inserirRecursiu(NodeA<E> node, E e) {
        if (node == null) {
            return new NodeA<>(e);
        }

        if (e.compareTo(node.contingut) != 0) {
            if (e.compareTo(node.contingut) < 0) {
                node.left = inserirRecursiu(node.left, e);
            } else if (e.compareTo(node.contingut) > 0) {
                node.right = inserirRecursiu(node.right, e);
            }
        }else{
            throw new IllegalArgumentException("Aquest Jugador ja existeix!");
        }


        return node;
    }

    @Override
    public void esborrar(E e) throws ArbreException {
        if (!membre(e)) {
            throw new ArbreException("Aquest jugador no existeix.");
        }
        arrel = esborrarRecursiu(arrel, e);
    }

    private NodeA<E> esborrarRecursiu(NodeA<E> node, E e) {
        if (node == null) {
            return null;
        }

        // Comparar por posició i despres per punts
        int comparacion = ((Jugador) e).compareTo((Jugador) node.contingut);

        if (comparacion < 0) {
            node.left = esborrarRecursiu(node.left, e);
        } else if (comparacion > 0) {
            node.right = esborrarRecursiu(node.right, e);
        } else {
            // Jugador trobat, realitzem la eliminació
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // El node té dos fills, trobem el successor inordre
            node.contingut = trobarMinim(node.right);
            node.right = esborrarRecursiu(node.right, node.contingut);
        }

        return node;
    }
    private E trobarMinim(NodeA<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.contingut;
    }

    @Override
    public boolean membre(E e) {
        return membreRecursiu(arrel, e);
    }

    private boolean membreRecursiu(NodeA<E> node, E e) {
        if (node == null) {
            return false;
        }

        int comparacio = e.compareTo(node.contingut);

        if (comparacio == 0) {
            return true;
        } else if (comparacio < 0) {
            return membreRecursiu(node.left, e);
        } else {
            return membreRecursiu(node.right, e);
        }
    }

    @Override
    public Object clone() {
        AcbEnll<E> nouArbre = new AcbEnll<>();
        if (arrel != null) {
            nouArbre.arrel = (NodeA<E>) arrel.clone();
        }
        return nouArbre;
    }
    public void iniRecorregut(boolean sentitAscendent) {
        cuaRecurregut = new LinkedList<>();
        if (sentitAscendent) {
            iniRecorregutAscendent(arrel);
        } else {
            iniRecorregutDescendent(arrel);
        }
    }

    private void iniRecorregutAscendent(NodeA<E> node) {
        if (node != null) {
            iniRecorregutAscendent(node.left);
            cuaRecurregut.add(node.contingut);
            iniRecorregutAscendent(node.right);
        }
    }

    private void iniRecorregutDescendent(NodeA<E> node) {
        if (node != null) {
            iniRecorregutDescendent(node.right);
            cuaRecurregut.add(node.contingut);
            iniRecorregutDescendent(node.left);
        }
    }

    public boolean finalRecorregut() {
        return cuaRecurregut == null || cuaRecurregut.isEmpty();
    }

    public E segRecorregut() throws ArbreException {
        if (cuaRecurregut == null || cuaRecurregut.isEmpty()) {
            throw new ArbreException("No hi ha més elements al recorregut.");
        }
        return cuaRecurregut.poll();
    }

    public int calcularCardinalitat() {
        return calcularCardinalitatRecursiu(arrel);
    }

    private int calcularCardinalitatRecursiu(NodeA<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + calcularCardinalitatRecursiu(node.left) + calcularCardinalitatRecursiu(node.right);
    }

}

