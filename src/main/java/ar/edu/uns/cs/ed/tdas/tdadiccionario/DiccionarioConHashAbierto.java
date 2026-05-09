package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.DiccionarioConLista;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V>{
    protected DiccionarioConLista<K,V> []A;
    protected int n;
    protected int N;

    public DiccionarioConHashAbierto(){
        A = new DiccionarioConLista[11];
        n=0;
        N=11;
    }
    public DiccionarioConHashAbierto(int t){
        A = new DiccionarioConLista[t];
        n=0;
        N=t;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public Entry<K, V> find(K key) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida");
        for(int i=0;i<N;i++){
            for(Entry<K,V> e: A[i].entries()){
                if(e.getKey().equals(key))
                    return e;
            }
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida");
        ListaDobleEnlazada<Entry<K,V>> rta = new ListaDobleEnlazada<>();
        for(int i=0;i<N;i++){
            for(Entry<K,V> e: A[i].entries()){
                if(e.getKey().equals(key))
                    rta.addLast(e);
            }
        }

        return rta;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida"); 
        Entry<K,V> ent = A[h(key)].insert(key,value);
        if(ent==null) n++;
        return ent;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        Entry<K,V> ent= A[h(e.getKey())].remove(e);
        if(ent==null)n--;
        return ent;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        ListaDobleEnlazada<Entry<K,V>> rta = new ListaDobleEnlazada<>();
        for(int i=0;i<N;i++){
            for(Entry<K,V> e: A[i].entries()){
                rta.addLast(e);
            }
        }
        return rta;
    }
    
    // ejercicio 5
    public Iterable<Entry<K,V>> eliminarTodas(K c,V v){
        if(c==null)
            throw new InvalidKeyException("La clave no es valida");
        ListaDobleEnlazada<Entry<K,V>> rta = new ListaDobleEnlazada<>();
        for(int i=0;i<N;i++){
            for(Entry<K,V> e: A[i].entries()){
                if(e.getKey().equals(c)&&e.getValue().equals(v)){
                    remove(e);
                    rta.addLast(e);
                }
            }
        }
        return rta;
    }



    private int h(K key) {
        return Math.abs(key.hashCode()) % N;
    }

}
