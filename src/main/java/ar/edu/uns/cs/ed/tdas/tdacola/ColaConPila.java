package ar.edu.uns.cs.ed.tdas.tdacola;
import java.util.Stack;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaConPila<E> implements Queue<E>{
    //mecanismo, voy a utilizar dos pilas en las cuales cuando encolo siempre agrego a la primera pero cuando quiero desencolar me fijo en la segunda
    
    private Stack<E> datos;

    public ColaConPila(){
        datos = new Stack<>();
    }

    @Override
    public int size() {
        return datos.size();
    }

    @Override
    public boolean isEmpty() {
        return ((datos.isEmpty()));
    }

    @Override
    public E front() {
        if(isEmpty()){
            throw new EmptyQueueException("La cola esta vacia");
        }
        if(pila2.isEmpty()){
            while(!pila1.isEmpty()){
                pila2.push(pila1.pop());
            }
        }
        return pila2.peek();
    }

    @Override
    public void enqueue(E element) {
        pila1.push(element);
    }

    @Override
    public E dequeue() {
        if(pila2.isEmpty()){
            while(!pila1.isEmpty()){
                pila2.push(pila1.pop());
            }
        }
        if(isEmpty()){
            throw new EmptyQueueException("Cola vacia");
        }
        return pila2.pop();
    }
    
}
