package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class TDcola<E> implements Queue<E>{

    private int cant;
    private E [] datos;
    private int ult;
    private int prim;

    public TDcola(){
        cant=0;
        datos= (E[]) new Object[100];
        ult=0;
        prim=0;
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
    public E front() {
        if(cant==0){
            throw new EmptyQueueException("la cola esta vacia");
        }
        return datos[prim];
    }

    @Override
    public void enqueue(E element) {
        if(cant==datos.length){
            E [] nuevo = (E[]) new Object[datos.length*2];
            for(int i=0;i<cant;i++){
                nuevo[i]= datos[(prim+i)%datos.length];
            }
            ult=cant;
            prim=0;
            datos = nuevo;
        }
        datos[ult]= element;
        ult= (ult+1)%datos.length;
        cant++;
    }

    @Override
    public E dequeue() {
        if(cant==0)
            throw new EmptyQueueException("La cola esta vacia");
        E elemen;
        elemen = datos[prim];
        datos[prim]=null;
        prim= (prim +1)%datos.length;
        cant--;
        return elemen;
    }
    
}
