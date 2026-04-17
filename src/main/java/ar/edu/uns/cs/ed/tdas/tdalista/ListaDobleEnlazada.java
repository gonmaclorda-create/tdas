package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada.ElementoIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

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
        PositionList<Position<E>> l2 = new ListaDobleEnlazada<Position<E>>();
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
        boolean esta=false;
        for(Position<Character> p : l2.positions()){
            Character elem = p.element();
            //preguntar por casteo
            esta=false;
            for(Character elem1:l1){
                if(elem1.equals(elem))
                    esta=true;
            }
            if(esta){
                l2.remove(p);
                eliminados.addLast(elem);
            }
        }
        return eliminados;
    }

    //Ejercicio 6) a)
    public PositionList<E> intercalar(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> lr = new ListaDobleEnlazada<>();
        if(l1.isEmpty()&&l2.isEmpty()) return lr;
        Position<E> c1 = l1.first();
        Position<E> c2 = l2.first();
        boolean puedo = (c1!=null||c2!=null);
        while(puedo){
            if(c1!=null){
                lr.addLast(c1.element());
                c1=l1.next(c1);
            }
            if(c2!=null){
                lr.addLast(c2.element());
                c2=l2.next(c2);
            }
            if(c1==null&&c2==null)
                puedo=false;
        }
        return lr;
    }
    ///b)
    public PositionList<Integer> intercalarsinrep(PositionList<Integer> l1, PositionList<Integer> l2){
        PositionList<Integer> lr = new ListaDobleEnlazada<>();
        if(l1.isEmpty()&&l2.isEmpty()) return lr;
        Position<Integer> c1 = l1.first();
        Position<Integer> c2 = l2.first();
        boolean puedo = (c1!=null||c2!=null);
        boolean esta=false;
        while(puedo){
            esta=false;
            if(c1!=null){
                for(Integer elem1: lr){
                    if(elem1.equals(c1.element()))
                        esta=true;
                }
                if(!esta){
                lr.addLast(c1.element());
                c1=l1.next(c1);
                }
            }
            esta=false;
            if(c2!=null){
                for(Integer elem1: lr){
                    if(elem1.equals(c2.element()))
                        esta=true;
                }
                if(!esta){
                lr.addLast(c2.element());
                c2=l2.next(c2);
                }
            if(c1==null&&c2==null)
                puedo=false;
            }
        }
        return lr;
    }

    //Ejercicio 7)
    public PositionList<E> eliminadorinvertido(PositionList<E> l1, PositionList<E> l2){
        Stack<E> pila= new Stack<E>();
        boolean esta=false;
        for(Position<E> p : l1.positions()){
            E elem = p.element();
            esta=false;
            for(E elem1:l2){
                if(elem1.equals(elem)){
                    esta=true;
                    break;
                }
            }
            if(esta){
                l1.remove(p);
            }
        }
        for(E e:l2){
            pila.push(e);
        }
        while(!pila.isEmpty()){
            l1.addLast(pila.pop());
        }
        return l1;
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