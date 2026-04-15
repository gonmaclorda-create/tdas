package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

import java.util.Iterator;
import jdk.dynalink.NoSuchDynamicMethodException;

public class ElementoIterator<E> implements Iterator<E>{

    private PositionList<E> lista;
    private  Position<E> cursor;

    public ElementoIterator(PositionList<E> l){
        lista = l;
        cursor = l.first();
    }

    @Override
    public boolean hasNext() {
        return cursor!=null;
    }

    @Override
    public E next() {
        if(cursor==null) throw new NoSuchElementException("posicion nula");

        E element= cursor.element();

        if(cursor!= last()) 
            cursor = lista.next(cursor);
        else 
            cursor = null;

        return element;

    }
    
}
