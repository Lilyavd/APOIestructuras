package uniandes.cupi2.apo2h.estructuras;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa el iterador sencillo (sólo avanza hacia adelante).
 * @param <E> Tipo de información que almacena el iterador.
 */
public class IteradorSencillo<E> implements Iterator<E>, Serializable 
{

	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 1852525L;
	
	/**
	 * El nodo donde se encuentra el iterador.
	 */
	private NodoListaSencilla<E> actual;

	
	public IteradorSencillo(NodoListaSencilla<E> primerNodo) 
	{
		actual = primerNodo;
	}
	
	/**
     * Indica si aún hay elementos por recorrer
     * @return true en caso de que  aún haya elemetos o false en caso contrario
     */
	public boolean hasNext() 
	{
		return actual != null;
	}

	/**
     * Devuelve el siguiente elemento a recorrer
     * <b>post:</b> se actualizado actual al siguiente del actual
     * @return objeto en actual
     * @throws NoSuchElementException Si se encuentra en el final de la lista y se pide el siguiente elemento.
     */
	public E next() 
	{
		if(actual == null)
			throw new NoSuchElementException("Se ha alcanzado el final de la lista");
		E valor = actual.darElemento();
		actual = actual.darSiguiente();
		return valor;
	}

}
