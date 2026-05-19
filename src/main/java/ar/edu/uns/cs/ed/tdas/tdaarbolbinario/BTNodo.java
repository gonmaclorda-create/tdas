package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;
import ar.edu.uns.cs.ed.tdas.Position;

public class BTNodo<E> implements Position<E>{
    private E element;
    private BTNodo<E> left, right, parent;

    public BTNodo( E element, BTNodo<E> left, BTNodo<E> right, BTNodo<E> parent) {
        this.element = element; 
        this.left = left;
        this.right = right; 
        this.parent = parent;
    }
    
    public void setElement(E e){
        element =e;
    }
    public void setLeft(BTNodo<E> n){
        left = n;
    }
    public void setRight(BTNodo<E> n){
        right = n;
    }
    public void setParent(BTNodo<E> n){
        parent = n;
    }
    public E element(){
        return element;
    }
    public BTNodo<E> getParent(){
        return parent;
    }
    public BTNodo<E> getLeft(){
        return left;
    }
    public BTNodo<E> getRight(){
        return right;
    }
}
