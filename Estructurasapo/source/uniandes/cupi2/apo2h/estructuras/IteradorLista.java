package uniandes.cupi2.apo2h.estructuras;

import java.io.Serializable;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa el iterador de lista (avanza hacia adelante y hacia atrás)
 * @param <E> Tipo del objeto que almacena el iterador de la lista
 */
public class IteradorLista<E> implements ListIterator<E>, Serializable 
{

	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 123L;
	
	/**
	 * Nodo anterior al que se encuentra el iterador.
	 */
	private NodoListaDoble<E> anterior;

	/**
	 * Nodo en el que se encuentra el iterador.
	 */
	private NodoListaDoble<E> actual;
	
	/**
     * Crea un nuevo iterador de lista iniciando en el nodo que llega por parámetro
     * @param primerNodo el nodo en el que inicia el iterador. nActual != null
     */
	public IteradorLista(NodoListaDoble<E> primerNodo)
	{
		actual = primerNodo;
		anterior = null;
	}
	
    /**
     * Indica si hay nodo siguiente
     * true en caso que haya nodo siguiente o false en caso contrario
     */
	public boolean hasNext() 
	{
		// TODO Completar según la documentación
		return actual != null;
	}

    /**
     * Indica si hay nodo anterior
     * true en caso que haya nodo anterior o false en caso contrario
     */
	public boolean hasPrevious() 
	{
		// TODO Completar según la documentación
		return anterior != null ;
	}

    /**
     * Devuelve el elemento siguiente de la iteración y avanza.
     * @return elemento siguiente de la iteración
     * @throws NoSuchElementException Se lanza en caso de que se pida el siguiente y ya se encuentre al final de la lista
     */
	public E next() throws NoSuchElementException
	{
		// TODO Completar según la documentación
		if (actual == null)
			throw new  NoSuchElementException ("Se encuentra al final de la lista");
		E elemento = actual.darElemento();
		anterior = actual;
		actual = (NodoListaDoble<E>) actual.darSiguiente();
		return elemento;
	}

    /**
     * Devuelve el elemento anterior de la iteración y retrocede.
     * @return elemento anterior de la iteración.
     * @throws NoSuchElementException Se lanza en caso de que se pida el anterior y ya se encuentra al final de la lista.
     */
	public E previous() throws NoSuchElementException
	{
		// TODO Completar según la documentación
		if (anterior == null)
			throw new  NoSuchElementException ("Se encuentra al principio de la lista");
		E elemento = anterior.darElemento();
		actual = anterior;
		anterior = anterior.darAnterior();
		return elemento;
	}
	
	
	//=======================================================
	// Métodos que no se implementarán
	//=======================================================
	
	public int nextIndex() 
	{
		throw new UnsupportedOperationException();
	}

	public int previousIndex() 
	{
		throw new UnsupportedOperationException();
	}

	public void remove() 
	{
		throw new UnsupportedOperationException();
	}

	public void set(E e) 
	{
		throw new UnsupportedOperationException();
	}
	
	public void add(E e) 
	{
		throw new UnsupportedOperationException();		
	}

}
