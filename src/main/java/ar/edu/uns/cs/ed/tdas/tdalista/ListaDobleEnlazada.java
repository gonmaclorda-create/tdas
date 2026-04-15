package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import java.util.Iterator;

public class ListaDobleEnlazada<E> implements PositionList<E> {

    @SuppressWarnings("FieldMayBeFinal")
    private DNodo<E> head;
    @SuppressWarnings("FieldMayBeFinal")
    private DNodo<E> tail;
    private int cant;

    public ListaDobleEnlazada(){
        cant=0;
        head = new DNodo<>(null);
        tail = new DNodo<>(null, null, head);
        head.setSiguiente(tail);
    }

   @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant==0;
    }

    @Override
    public Position<E> first() {
        if(isEmpty())
            throw new EmptyListException("Lista vacia");
        return head.getSiguiente();
    }

    @Override
    public Position<E> last() {
        return tail.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) {
        DNodo<E> nodo = checkPosition(p);
        if(nodo.getSiguiente()== tail)
            throw new BoundaryViolationException("Estas pidiendo el siguiente al ultimo");
        return nodo.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) {
        DNodo<E> nodo = checkPosition(p);
        if(nodo.getAnterior()==head)
            throw new BoundaryViolationException("Estas pidiendo el anterior al primero");
        return nodo.getAnterior();
    }

    @Override
    public void addFirst(E element) {
        DNodo<E> pviejo = head.getSiguiente();
        DNodo<E> nuevo = new DNodo(element,pviejo,head);
        pviejo.setAnterior(nuevo);
        head.setSiguiente(nuevo);
        cant++;
    }

    @Override
    public void addLast(E element) {
        DNodo<E> pviejo = tail.getAnterior();
        DNodo<E> nuevo = new DNodo(element,tail,pviejo);
        pviejo.setSiguiente(nuevo);
        tail.setAnterior(nuevo);
        cant++;
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        DNodo<E> ag = checkPosition(p);
        DNodo<E> nuevo = new DNodo(element, ag, ag.getSiguiente());
        ag.getSiguiente().setAnterior(nuevo);
        ag.setSiguiente(nuevo);
        cant++;
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        DNodo ag = checkPosition(p);
        DNodo nuevo = new DNodo(element,ag,ag.getAnterior());
        ag.getAnterior().setSiguiente(nuevo);
        ag.setAnterior(nuevo);
        cant++;
    }

    @Override
    public E remove(Position<E> p) {
        DNodo<E> nodo = checkPosition(p);
        nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(nodo.getAnterior());
        E elemento = nodo.getElemento();
        nodo.setElemento(null);
        cant--;
        return  elemento;
    }

    @Override
    public E set(Position<E> p, E element) {
        DNodo<E> nodo = checkPosition(p);
        E elementoviejo = nodo.getElemento();
        nodo.setElemento(element);
        return elementoviejo;
    }

    @Override
    public Iterator<E> iterator() {
       return new ElementoIterator(this);
    }

    @Override
    public Iterable<Position<E>> positions() {
        ListaDobleEnlazada<Position<E>> l2 = new ListaDobleEnlazada();
        DNodo<E> actual= head;
        while(actual.getSiguiente() != tail){
            actual= actual.getSiguiente(); 
            l2.addLast(actual);
        }
        return l2;
    }

    private DNodo<E> checkPosition(Position<E> p) {
    try {
        if (p == null)
            throw new InvalidPositionException("posicion nula");
        if (p.element() == null)
            throw new InvalidPositionException("posicion eliminada previamente");
        return (DNodo<E>) p;
    } catch (ClassCastException e) {
        throw new InvalidPositionException("p no es un nodo de lista");
    }
}

}