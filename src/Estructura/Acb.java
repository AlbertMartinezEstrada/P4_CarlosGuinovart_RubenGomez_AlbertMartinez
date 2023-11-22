package Estructura;

public interface Acb<E extends Comparable<E>> {
    E arrel() throws ArbreException;
    Acb<E> fillEsquerre();
    Acb<E> fillDret();
    boolean abBuit();
    void buidar();
    void inserir(E e) throws ArbreException;
    void esborrar(E e) throws ArbreException;
    boolean membre(E e);
}
