package uniandes.cupi2.apo2h.estructuras;

import java.io.Serializable;

public class NodoListaSencilla<E> implements Serializable
{

	/**
	 * Constante de Serializaci�n
	 */
	private static final long serialVersionUID = 1123135L;
	
	/**
	 * Elemento almacenado en el nodo.
	 */
	//TODO Declara el elemento almacenado en el nodo.
	private E elemento;
	/**
	 * Siguiente nodo.
	 */
	//TODO Declara el siguiente nodo.
	private NodoListaSencilla<E> siguiente;
	/**
	 * Constructor del nodo.
	 * @param elemento El elemento que se almacenar� en el nodo. elemento != null
	 */
	public NodoListaSencilla(E pElemento)
	{
		//TODO Completar el m�todo de acuerdo a la documentaci�n.
		elemento = pElemento;
		siguiente = null;
	}
	
	/**
	 * M�todo que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(NodoListaSencilla<E> pSiguiente)
	{
		//TODO Completar el m�todo de acuerdo a la documentaci�n.
		siguiente = pSiguiente;
	}
	
	/**
	 * M�todo que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public E darElemento()
	{
		//TODO Completar el m�todo de acuerdo a la documentaci�n.
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenar� en el nodo.
	 */
	public void cambiarElemento(E pElemento)
	{
		//TODO Completar el m�todo de acuerdo a la documentaci�n.
		elemento = pElemento;
	}
	
	
	/**
	 * M�todo que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public NodoListaSencilla<E> darSiguiente()
	{
		//TODO Completar el m�todo de acuerdo a la documentaci�n.
		return siguiente;
	}

}
