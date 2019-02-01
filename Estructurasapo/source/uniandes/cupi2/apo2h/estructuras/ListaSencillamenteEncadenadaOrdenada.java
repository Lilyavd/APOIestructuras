package uniandes.cupi2.apo2h.estructuras;

import java.util.Collection;
import java.util.Comparator;

/**
 * Estructura de datos lista ordenada por algun criterio de ordenamiento de E
 * @author Christian
 *
 * @param <E> Tipo de dato que se almacenar� en la lista, debe ser Unicamente identificado
 */
public class ListaSencillamenteEncadenadaOrdenada<E> extends ListaSencillamenteEncadenada<E>
{

	/**
	 * Constante de Serializaci�n.
	 */
	private static final long serialVersionUID = 1878975L;

	/**
	 * Comparador de E, permite comparar dos E para mantener el orden en la lista.
	 */
	private Comparator<E> comparador;

	/**
	 * Indica si la lista debe organizarse ascendente o descendentemente
	 */
	private boolean ascendente;

	/**
	 * Construye una lista vac�a.
	 * <b>post: </b> - Se ha inicializado el primer nodo en null. <br/>
	 * - Se ha inicializado el criterio de comparaci�n por el que se ordenar� el elemento.
	 * - Se ha inicializado si se quiere ordenar ascendente.
	 * @param comparador Criterio de comparaci�n por el que se ordenar�n los elementos en la lista.
	 */
	public ListaSencillamenteEncadenadaOrdenada(Comparator<E> comparador, boolean ascendente)
	{
		this.comparador = comparador;
		this.ascendente = ascendente;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�metro. Actualiza el n�mero de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @param comparador Criterio de comparaci�n por el que se ordenar�n los elementos en la lista.
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaSencillamenteEncadenadaOrdenada(E nPrimero, Comparator<E> comparador, boolean ascendente)
	{
		super(nPrimero);
		this.comparador = comparador;
		this.ascendente = ascendente;
	}


	/**
	 * Agrega un elemento a la lista manteniendo el orden de acuerdo al criterio de comparaci�n, actualiza el n�mero de elementos.
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
	 * @param elem el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public boolean add(E elemento) throws  NullPointerException
	{
		//TODO Completar seg�n la documentaci�n.
		if (elemento == null)
			throw new NullPointerException ();
		boolean res= false;
		if (primerNodo != null)
		{
			NodoListaSencilla<E> estoy = primerNodo;
			NodoListaSencilla<E> anterior =  null;

			while (estoy != null && !estoy.darElemento().equals(elemento))
			{

				if ( ( comparador.compare(estoy.darElemento(),elemento)<0) == ascendente )
				{
					anterior = estoy; 
				}
				estoy= estoy.darSiguiente();

			}

			if (estoy == null )
			{
				if (anterior != null )
				{
					NodoListaSencilla<E> nodo = new NodoListaSencilla<E> (elemento);
					nodo.cambiarSiguiente(anterior.darSiguiente());
					anterior.cambiarSiguiente(nodo);
				}else
				{
					NodoListaSencilla<E> nodo = new NodoListaSencilla<E> (elemento);
					nodo.cambiarSiguiente(primerNodo);
					primerNodo = nodo;
				}

				res= true;
			}

		}else
		{
			primerNodo = new NodoListaSencilla<E>(elemento);
			res= true;
		}
		if (res)
			cantidadElementos++;
		return res;


	}




	//------------------------------------------------------------------------------------------
	// M�todos no soportados por la lista (No los soporta porque no tienen sentido, la lista siempre debe estar organizada por el criterio de comparaci�n).
	//-----------------------------------------------------------------------------------------
	@Override
	@Deprecated
	public void add(int index, E elemento) 
	{
		throw new UnsupportedOperationException("No se puede hacer uso de esta operaci�n en este tipo de lista");
	}

	@Override
	@Deprecated
	public E set(int index, E element) throws IndexOutOfBoundsException 
	{
		throw new UnsupportedOperationException("No se puede hacer uso de esta operaci�n en este tipo de lista");
	}

	@Override
	@Deprecated
	public boolean addAll(int pos, Collection<? extends E> c) 
	{
		throw new UnsupportedOperationException("No se puede hacer uso de esta operaci�n en este tipo de lista");
	}

}
