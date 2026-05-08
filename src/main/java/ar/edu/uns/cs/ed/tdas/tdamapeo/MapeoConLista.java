package ar.edu.uns.cs.ed.tdas.tdamapeo;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

public class MapeoConLista<K,V> implements Map<K,V>{
    protected ListaDobleEnlazada<Entry<K,V>> S;
    
    public MapeoConLista(){
        S = new ListaDobleEnlazada<Entry<K,V>>();
    }
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V get(K key) {
		if(key==null)
			throw new InvalidKeyException("La clave no es válida");	
		// Para cada posición p de la lista S hacer:
		for( Position<Entry<K,V>> p : S.positions() )
		// Si la clave de la entrada actual es key:
		if( p.element().getKey().equals( key ) )
			// Retornar el valor de la entrada actual:
			return p.element().getValue();
		// Si salí del for-each, quiere decir que no encontré ninguna entrada con clave key.
		return null;
	}
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V put(K key, V value) {
		if(key==null)
			throw new InvalidKeyException("La clave no es válida");
		// Para cada posición p de la lista S hacer:
		for( Position<Entry<K,V>> p : S.positions() )
		// Si la clave de la entrada en la posición p es key:
		if( p.element().getKey().equals( key ) ) {
			// Salvar el valor de la entrada en aux
			V aux = p.element().getValue();
			// Setear el nuevo valor de la entrada a value
			p.element().setValue( value );
			// Retornar el viejo valor
			return aux;
		}
		// Si salí del for-each entonces no encontré una entrada con clave key
		S.addLast(new Entry<K,V>(key, value) ); // Inserto una nueva entrada (key,value)
		return null; // Retorno null para indicar que inserté una nueva entrada
	}
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V remove(K key) {
		if(key==null)
			throw new InvalidKeyException("La clave no es válida");
		// Para cada posición p de S hacer:
		for( Position<Entry<K,V>> p : S.positions() )
			// Si la entrada de la posición p tiene clave key:
			if( p.element().getKey().equals( key ) ) {
				// Salvar el valor de la entrada corriente en value:
				V value = p.element().getValue();
				// Eliminar la posición p de la lista S:
				S.remove( p );
				// Retornar el valor de entrada removida del mapeo:
				return value;
			}
		// Si salí del for-each, quiere decir que no encontré ninguna entrada con clave key
		return null;
	}
	/**
	 * Consulta si el mapeo está vacío.
	 * @return Verdadero si el mapeo está vacío, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		if(S.isEmpty())
			return true;
		else
			return false;
	}
    /**
	 * Consulta el número de entradas del mapeo.
	 * @return Número de entradas del mapeo.
	 */
	@Override
	public int size() {
		return S.size();
	}
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	@Override
	public Coleccion<K> keys() {
		Coleccion<K>
	}

	@Override
	public Coleccion<V> values() {
		return null;
	}

	@Override
	public Coleccion<Entry<K, V>> entries() {
		return null;
	}

}