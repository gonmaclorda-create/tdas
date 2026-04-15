package ar.edu.uns.cs.ed.tdas.tdapila;

public class PilaArreglo<E> implements Stack<E>{
    private int cant;
    private E [] datos;

    public PilaArreglo(){
        cant=0;
        datos = (E[]) new Object[100];
    }

    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return (cant==0);
    }

    @Override
    public E top() {
        if(isEmpty())
            throw new ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException("Pila vacia no hay tope");
        return datos[cant-1];
    }

    @Override
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public void push(E element) {
        if(cant == datos.length){
            E[] nuevo = (E[]) new Object[datos.length * 2];
            for(int i = 0; i < cant; i++){
                nuevo[i] = datos[i];
            }
            datos = nuevo;
        }
        datos[cant++] = element;
    }

    @Override
    public E pop() {
        if(isEmpty())
            throw new ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException("Ya esta vacia");
        E elemento=datos[cant-1];
        datos[(cant-1)]=null;
        cant--;
        return  elemento;
    }
}
