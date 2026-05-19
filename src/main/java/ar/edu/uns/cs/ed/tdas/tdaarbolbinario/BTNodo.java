package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

public class BTNodo<E> implements BTPosition<E>{
    private E element;
    private BTPosition<E> left, right, parent;

    public BTNodo( E element, BTPosition<E> left, BTPosition<E> right, BTPosition<E> parent) {
        this.element = element; 
        this.left = left;
        this.right = right; 
        this.parent = parent;
    }
    
    public void setElement(E e){
        element =e;
    }
    public void setLeft(BTPosition<E> n){
        left = n;
    }
    public void setRight(BTPosition<E> n){
        right = n;
    }
    public void setParent(BTPosition<E> n){
        parent = n;
    }
    public E element(){
        return element;
    }
    public BTPosition<E> getParent(){
        return parent;
    }
    public BTPosition<E> getLeft(){
        return left;
    }
    public BTPosition<E> getRight(){
        return right;
    }
}
