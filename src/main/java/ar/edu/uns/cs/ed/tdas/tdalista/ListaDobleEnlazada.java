package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if(isEmpty())
            throw new EmptyListException("Lista vacia");
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
        DNodo<E> nuevo = new DNodo(element, ag.getSiguiente(), ag);
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
        DNodo<E> actual= head.getSiguiente();
        while(actual != tail){
            l2.addLast(actual);
            actual= actual.getSiguiente(); 
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
    //Ejercicio 2
    public void modificar(E e1, E e2){
        DNodo<E> n1 = new DNodo<E>(e1);
        DNodo<E> n2 = new DNodo<E>(e2);
        if(size()>1){
            DNodo<E> primero = head.getSiguiente();
            DNodo<E> segundo = primero.getSiguiente();
            DNodo<E> ultimo = tail.getAnterior();
            DNodo<E> anteultimo = ultimo.getAnterior();

            primero.setSiguiente(n1);
            n1.setAnterior(primero);
            n1.setSiguiente(segundo);
            segundo.setAnterior(n1);

            ultimo.setAnterior(n2);
            n2.setSiguiente(ultimo);
            n2.setAnterior(anteultimo);
            anteultimo.setSiguiente(n2);
        }
        if(isEmpty()){
            head.setSiguiente(n1);
            n1.setAnterior(head);
            n1.setSiguiente(n2);
            n2.setAnterior(n1);
            n2.setSiguiente(tail);
            tail.setAnterior(n2);
        }
        if(size()==1){
            DNodo<E> unico = head.getSiguiente();
            unico.setSiguiente(n1);
            n1.setAnterior(unico);
            n1.setSiguiente(n2);
            n2.setAnterior(n1);
            n2.setSiguiente(tail);
            tail.setSiguiente(n2);
        }
    }

    //Ejercicio 3
    //a)
    public boolean contiene(E e1){
        Iterator<E> it = this.iterator();
        while(it.hasNext()){
            E elemento = it.next();
            if(elemento.equals(e1))
                return true;
        }
        return false;
    }
    //b)
    public int cantidadDe(E e1){
        int cant=0;
        Iterator<E> it = this.iterator();
        while(it.hasNext()){
            E elemento = it.next();
            if(elemento.equals(e1))
                cant++;    
        }
        return cant;
    }
    //c)
    public boolean nVeces(E x, int n){
        int cant=0;
        Iterator<E> it = this.iterator();
        while(it.hasNext()){
            E elemento = it.next();
            if(elemento==x)
                cant++;
            if(cant==n);
                return true;
        }
        return false;
    }
    //Ejercicio 4
    public ListaDobleEnlazada<E> repetir(PositionList<E> l){
        ListaDobleEnlazada<E> lista = new ListaDobleEnlazada<>();
        for(E elemento : l){
            lista.addLast(elemento);
            lista.addLast(elemento);
        }
        return lista;
    }
    //Ejercicio 5
    public Iterable<Character> sinInterseccion(PositionList<Character> l1, PositionList<Character> l2){
        PositionList<Character> eliminados = new ListaDobleEnlazada<>();
        PositionList<Position<Character>> posiciones = new ListaDobleEnlazada<>();
        for(Position<Character> p: l2.positions()){
            Character pelem = p.element();
            if(l1.esta(pelem)){}
        }
    }

    private boolean esta(Character c){
        for(E actual : this){
            Character a = (Character)actual;
            if(c==a)
                return true;
        }
        return false;
    }


public class ElementoIterator implements Iterator<E>{

        @SuppressWarnings("FieldMayBeFinal")
        private PositionList<E> lista;
        private  Position<E> cursor;

        public ElementoIterator(PositionList<E> l){
            lista = l;
            if(l.isEmpty())
                cursor = null;
            else
                cursor = l.first();
        }

        @Override
        public boolean hasNext() {
            return cursor!=null;
        }

        @Override
        public E next() {
            if(cursor==null) throw new NoSuchElementException("no hay mas elementos");

            E element= cursor.element();

            if(cursor!= last()) 
                cursor = lista.next(cursor);
            else 
                cursor = null;

            return element;

        }
        
    }
}