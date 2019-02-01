package uniandes.cupi2.apo2h.estructuras;

import java.io.Serializable;

public class NodoListaSencilla<E> implements Serializable
{

	/**
	 * Constante de Serialización
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
	 * @param elemento El elemento que se almacenará en el nodo. elemento != null
	 */
	public NodoListaSencilla(E pElemento)
	{
		//TODO Completar el método de acuerdo a la documentación.
		elemento = pElemento;
		siguiente = null;
	}
	
	/**
	 * Método que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(NodoListaSencilla<E> pSiguiente)
	{
		//TODO Completar el método de acuerdo a la documentación.
		siguiente = pSiguiente;
	}
	
	/**
	 * Método que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public E darElemento()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenará en el nodo.
	 */
	public void cambiarElemento(E pElemento)
	{
		//TODO Completar el método de acuerdo a la documentación.
		elemento = pElemento;
	}
	
	
	/**
	 * Método que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public NodoListaSencilla<E> darSiguiente()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return siguiente;
	}

}
