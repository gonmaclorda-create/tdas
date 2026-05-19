package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;


public class ArbolBinario<E> implements BinaryTree<E>{
    
    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }

    @Override
    public E replace(Position<E> v, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public Position<E> root() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'root'");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parent'");
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'children'");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInternal'");
    }

    @Override
    public boolean isExternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExternal'");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRoot'");
    }

    @Override
    public void createRoot(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRoot'");
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirstChild'");
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLastChild'");
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeExternalNode'");
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInternalNode'");
    }

    @Override
    public void removeNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
    }

    @Override
    public Position<E> left(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'left'");
    }

    @Override
    public Position<E> right(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'right'");
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasLeft'");
    }

    @Override
    public boolean hasRight(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasRight'");
    }

    @Override
    public Position<E> addLeft(Position<E> v, E r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLeft'");
    }

    @Override
    public Position<E> addRight(Position<E> v, E r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRight'");
    }

    @Override
    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attach'");
    }

}