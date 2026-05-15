package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

//import java.util.NoSuchElementException;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.MapeoConLista;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDobleEnlazada.ElementoIterator;

public class Arbol<E> implements Tree<E>{
    protected TNodo<E> root;
    protected int n;

    public Arbol(){
        root=null;
        n=0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }
    /**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
    @Override
    public Iterator<E> iterator() {
        PositionList<E> ld = new ListaDobleEnlazada<>();
		for(Position<E> p : positions())
			ld.addLast(p.element());
		return ld.iterator();
    }
    /**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> lista = new ListaDobleEnlazada<>();
		if(!isEmpty()) preorden(root,lista);
		return lista;
    }
    /**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
    @Override
    public E replace(Position<E> v, E e) {
        TNodo<E> b = checkPosition(v);
		E elemento = b.element();
		b.setElemento(e);
		return elemento;
    }
    /**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
    @Override
    public Position<E> root() {
		if(root==null)
			throw new EmptyTreeException("El arbol esta vacio");
		return root;

    }

    /**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
    @Override
	public Position<E> parent(Position<E> v){
		TNodo<E> ve = checkPosition(v);
		if(ve==root)
			throw new BoundaryViolationException("La posicion pasada por parametro es la raiz del arbol");
		return ve.getPadre();
	}
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
    @Override
	public Iterable<Position<E>> children(Position<E> v){
		TNodo<E> ve = checkPosition(v);
		ListaDobleEnlazada<Position<E>> lista = new ListaDobleEnlazada<Position<E>>();
		for(TNodo<E> n : ve.getHijos())
			lista.addLast(n);
		return lista; 
	}
	
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
    @Override
	public boolean isInternal(Position<E> v){
		TNodo<E> ve = checkPosition(v);
		if(ve.getHijos()!=null)
			return true;
		return false;

	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
    @Override
	public boolean isExternal(Position<E> v){
		TNodo<E> ve =  checkPosition(v);
		if(ve.getHijos()==null)
			return true;
		return false;
	}
	
	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
    @Override
	public boolean isRoot(Position<E> v){
		TNodo<E> ve = checkPosition(v);
		return ve==root;
	}
	
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
    @Override
	public void createRoot(E e){
		if(root!=null)
			throw new InvalidOperationException("El arbol ya tiene una raiz");
		TNodo<E> v = new TNodo<>(e);
		root=v;
		n++;
	}
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param padre Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
    @Override
	public Position<E> addFirstChild(Position<E> p, E e){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> v = checkPosition(p);
		TNodo<E> h = new TNodo<E>(e);
		v.getHijos().addFirst(h);
		h.setPadre(v);
		n++;
		return h;
	}
	
	/**
	 * Agrega un nodo con rótulo e como último hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
    @Override
	public Position<E> addLastChild(Position<E> p, E e){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> v = checkPosition(p);
		TNodo<E> h = new TNodo(e);
		v.getHijos().addLast(h);
		h.setPadre(v);
		n++;
		return h;
	}
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
    @Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> padre = checkPosition(p);
        TNodo<E> antes = checkPosition(rb);
        TNodo<E> insertar = new TNodo<>(e,padre);
        n++;
        for(Position<TNodo<E>> nuevo : padre.getHijos().positions()){
            if(nuevo.element()==antes){
                padre.getHijos().addBefore(nuevo, insertar);
            }
        }
        return insertar;
	}

	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
    @Override
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> padre = checkPosition(p);
        TNodo<E> antes = checkPosition(lb);
        TNodo<E> insertar = new TNodo<>(e,padre);
        n++;
        for(Position<TNodo<E>> nuevo : padre.getHijos().positions()){
            if(nuevo.element()==antes){
                padre.getHijos().addAfter(nuevo, insertar);
            }
        }
        return insertar;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
    @Override
	public void removeExternalNode (Position<E> p){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> pe = checkPosition(p);
		if(isInternal(p)) throw new InvalidPositionException("Es interno");
		if(pe==root){
			root=null;
			n--;
			return;
		}
		if(pe.getHijos().isEmpty()){
			for(Position<TNodo<E>> nodo : pe.getPadre().getHijos().positions())
				if(nodo.element()==pe){
					pe.getPadre().getHijos().remove(nodo);
					n--;
				}
		}
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
    @Override
	public void removeInternalNode (Position<E> p){
		if(root==null) throw new InvalidPositionException("El arbol no tiene raiz");
		TNodo<E> pe = checkPosition(p);
		if(pe==root&&pe.getHijos().size()>1) throw new InvalidPositionException("La raiz tiene mas de un hijo");
		if(pe==root&&pe.getHijos().size()==1){
			root = pe.getHijos().first().element();
			root.setPadre(null);
			n--;
			return;
		}
		if(pe!=root){
			PositionList<TNodo<E>> hijoss = pe.getPadre().getHijos();
			for(Position<TNodo<E>> buscar: hijoss.positions()){
				if(buscar.element()==pe){
					for(TNodo<E> h : pe.getHijos()){
						hijoss.addBefore(buscar,h);
						h.setPadre(pe.getPadre());
					}
					n--;
					hijoss.remove(buscar);
					break;
				}
			}
		}
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
    @Override
	public void removeNode (Position<E> p){
		if(isExternal(p)) removeExternalNode(p);
		else removeInternalNode(p);
	}


	private TNodo<E> checkPosition(Position<E> p){
		if (p==null) throw new InvalidPositionException("p nulo");
		try{
			return (TNodo<E>) p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("Posicion invalida");
		}
	}
	private void preorden(TNodo<E> nodo,PositionList<Position<E>> lista){
		lista.addLast(nodo);
		for(TNodo<E> p : nodo.getHijos())
			preorden(p,lista);
	}

	//ejercicio 2
	public void eliminarUltimoHijo(Position<E> p){
		TNodo<E> v =  checkPosition(p);
		if(root==v) throw new InvalidPositionException("La raiz no es ultimo hijo");
		ListaDobleEnlazada<TNodo<E>> hijoss = v.getPadre().getHijos();
		if(hijoss.last().element()==v){
			hijoss.remove(hijoss.last());
			n--;
		}else throw new InvalidPositionException("p no es ultimo hijo");
	}

	//ejercicio 3
	public Map<Character,Integer> cantidadRepeticiones(Tree<Character> t){
		MapeoConLista<Character,Integer> mapa = new MapeoConLista<>();
		if(t.isEmpty()||t.root()==null) return mapa;
		for(Position<Character> nodo : t.positions()){
			Character car = nodo.element();
			Integer cant = mapa.get(car);
			if(cant==null){
				mapa.put(car,1);
			}else{mapa.put(car,cant++);}
		}
		return mapa;
	}

	//ejercicio 4
	public Iterable<Position<String>> repeticionDePalabras(Tree<String> a, String s){
		ListaDobleEnlazada<Position<String>> lista = new ListaDobleEnlazada<>();
		if(a.isEmpty()||a.root()==null) return lista;
		for(Position<String> nodo : a.positions()){
			String pal = nodo.element();
			if(pal==s)
				lista.addLast(nodo);
		}
		return lista;
	}

	//ejercicio 5
	public int eliminar(Tree<E> a, E e){
		int cont = 0; //tiempo c1
		if(a.isEmpty()||a.root()==null) return cont; // tiempo c2+c3
		for(Position<E> nodo : a.positions()){/// tiempo n
			E elem = nodo.element(); // tiempo c4
			if(elem.equals(e)){      // tiempo c5
				a.removeNode(nodo);  // tiempo c6
				cont++;				 // tiempo c7
			}
		}
		return cont;				// tiempo c8
	} // T(n)= c1+max((c2+c3),(n(c4,c5,c6,c7)))+c8=O(n)

	//ejercicio 6
	public boolean pertenece(Tree<Integer> a, int n){
		if(a.isEmpty()||a.root()==null) return false;   //tiempo c1+c2+c3
		Iterator<Integer> iterador = a.iterator();		//tiempo c4+c5
		while(iterador.hasNext()){						//tiempo n iteraciones que depende de la cantidad de nodos del arbol
			if(iterador.next()==n)						//tiempo c6+c7
				return true;							//tiempo c8
		}												// n(c6+c7+c8)
		return false;									//tiempo c9
	}													//T(n)=c1+c2+max((c3),(c4+c5+n(c6+c7+c8)+c9?))=O(n)
}