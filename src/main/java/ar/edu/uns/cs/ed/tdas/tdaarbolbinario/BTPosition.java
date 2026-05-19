package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;

public interface BTPosition<E> extends Position<E> {
    public void setElement(E e);
    public void setLeft(BTPosition<E> n);
    public void setRight(BTPosition<E> n);
    public void setParent(BTPosition<E> n);
    public E element();
    public BTPosition<E> getParent();
    public BTPosition<E> getLeft();
    public BTPosition<E> getRight();
}
