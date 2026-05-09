package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

public class DiccionarioConLista<K,V> implements Dictionary<K,V>{
    protected ListaDobleEnlazada<Entrada<K,V>> D = new ListaDobleEnlazada<Entrada<K,V>>();
    protected int n;

    public DiccionarioConLista(){
       D = new ListaDobleEnlazada<Entrada<K,V>>(); 
    }
    /**
	 * Consulta el número de entradas del diccionario.
	 * @return Número de entradas del diccionario.
	 */
    @Override
    public int size() {
        return n;
    }
    /**
	 * Consulta si el diccionario está vacío.
	 * @return Verdadero si el diccionario está vacío, falso en caso contrario.
	 */
    @Override
    public boolean isEmpty() {
        return n==0;
    }
	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
    @Override
    public Entry<K, V> find(K key) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida");
        for(Entrada<K,V> e: D){
            if(e.getKey().equals(key))
                return e;
        }
        return null;
    }
	/**
	 * Retorna una colección iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colección iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida");
        ListaDobleEnlazada<Entry<K,V>> rta = new ListaDobleEnlazada<>();
        for(Entrada<K,V> e : D){
            if(e.getKey().equals(key))
                rta.addLast(e);
        }
        return rta;

    }
    /**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
    @Override
    public Entrada<K, V> insert(K key, V value) {
        if(key==null)
            throw new InvalidKeyException("La clave no es valida"); 
        Entrada<K,V> e = new Entrada<K,V>(key, value);
        D.addLast(e);
        n++;
        return e;
    }
    /**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no está en el diccionario o es inválida.
	 */
    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        if(e==null)
            throw new InvalidEntryException("La entrada no es valida");
        for(Position<Entrada<K,V>> p : D.positions()){
            if(p.element().equals(e)){
                D.remove(p);
                n--;
                return e;
            }
        }
        throw new InvalidEntryException("La Entrada no es valida");
    }
    /**
	 * Retorna una colección iterable con todas las entradas en el diccionario.
	 * @return Colección iterable de todas las entradas.
	 */
    @Override
    public Iterable<Entry<K, V>> entries() {
        ListaDobleEnlazada<Entry<K,V>> rta = new ListaDobleEnlazada<>();
        for(Entrada<K,V> e : D){
            rta.addLast(e);
        }
        return rta;
    }
    
}
