package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;

public class DNodo<E> implements Position<E>{
    private E elemento;
    private DNodo<E> siguiente;
    private DNodo<E> anterior;

    public DNodo(E element){
        elemento=element;
        anterior=null;
        siguiente=null;
    }
    public DNodo(E element, DNodo<E> sig, DNodo<E> ant){
        elemento = element;
        siguiente = sig;
        anterior = ant;
    }
    public void setElemento(E element){
        elemento = element;
    }

    public E getElemento(){
        return elemento;
    }

    public DNodo<E> getSiguiente(){
        return siguiente;
    }

    public DNodo<E> getAnterior(){
        return anterior;
    }

    @Override
    public E element() {
        return elemento;
    }
    public void setSiguiente(DNodo<E> element){
        siguiente = element;
    }

    public void setAnterior(DNodo<E> element){
        anterior = element;
    }

}
