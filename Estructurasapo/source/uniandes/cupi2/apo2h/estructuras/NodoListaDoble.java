package uniandes.cupi2.apo2h.estructuras;

public class NodoListaDoble<E> extends NodoListaSencilla<E>
{
	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 14546221L;
	
	/**
	 * Nodo anterior.
	 */
	//TODO Declarar el nodo anterior.
	private NodoListaDoble<E> anterior;
	
	/**
	 * Método constructor del nodo doblemente encadenado
	 * @param elemento elemento que se almacenará en el nodo.
	 */
	public NodoListaDoble(E elemento) 
	{
		super(elemento);
		anterior = null;
	}
	
	/**
	 * Método que retorna el nodo anterior.
	 * @return Nodo anterior.
	 */
	public NodoListaDoble<E> darAnterior()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return anterior;
	}
	
	/**
	 * Método que cambia el nodo anterior por el que llega como parámetro.
	 * @param anterior Nuevo nodo anterior.
	 */
	public void cambiarAnterior(NodoListaDoble<E> pAnterior)
	{
		//TODO Completar el método de acuerdo a la documentación.
		anterior = pAnterior;
	}
}
