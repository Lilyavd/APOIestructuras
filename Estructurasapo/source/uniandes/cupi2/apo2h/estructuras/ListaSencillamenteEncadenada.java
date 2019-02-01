package uniandes.cupi2.apo2h.estructuras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListaSencillamenteEncadenada<E> extends ListaEncadenadaAbstracta<E>
{

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 13565L;

	/**
	 * Construye la lista vacía.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public ListaSencillamenteEncadenada() 
	{
		//TODO Completar el método de acuerdo a la documentación.
		super ();
		primerNodo = null;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámetro. Actualiza el número de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaSencillamenteEncadenada(E nPrimero)
	{
		//TODO Completar el método de acuerdo a la documentación.
		super ();
		primerNodo = new NodoListaSencilla<E>(nPrimero);
	}

	/**
	 * Agrega un elemento al final de la lista, actualiza el número de elementos.
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param elem el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public boolean add(E elemento) 
	{
		// TODO Completar según la documentación
		boolean res = false;
		if (elemento == null )
			throw new NullPointerException("El elemento que se quiere agregar es null");
		if (primerNodo != null)
		{
			NodoListaSencilla<E> estoy = primerNodo;
			while (estoy.darSiguiente() != null && !estoy.darElemento().equals(elemento))
			{
				estoy= estoy.darSiguiente();
			}
			if (estoy.darSiguiente() == null && !estoy.darElemento().equals(elemento))
			{
				estoy.cambiarSiguiente(new NodoListaSencilla<E>(elemento));
				res=true;
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

	/**
	 * Agrega un elemento en la posición dada de la lista. Todos los elementos siguientes se desplazan.
	 * Actualiza la cantidad de elementos.
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param pos la posición donde se desea agregar. Si pos es igual al tamañoo de la lista se agrega al final
	 * @param elem el elemento que se desea agregar
	 * @throws IndexOutOfBoundsException si el inidice es < 0 o > size()
	 * @throws NullPointerException Si el elemento que se quiere agregar es null.
	 */
	public void add(int index, E elemento) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO Completar según la documentación
		indexCorrect(index,false);

		if (elemento == null)
			throw new NullPointerException ("El elemento que se quiere agregar es null");

		if (index != 0)
		{
			NodoListaSencilla<E> anterior =  primerNodo;
			NodoListaSencilla<E> res =  primerNodo.darSiguiente();
			boolean encontre = false;
			index--;
			NodoListaSencilla<E>estoy = primerNodo;
			for (int i=0; i<cantidadElementos && !encontre;i++, estoy = (NodoListaSencilla<E>) estoy.darSiguiente())
			{
				if (index==0)
					encontre = estoy.darElemento().equals(elemento)? true: false;
				if (index!=0 )
				{
					anterior = res;
					res= (NodoListaSencilla<E>) res.darSiguiente();
					index--;
				}

			}

			if (!encontre)
			{
				NodoListaSencilla<E> nodo = new NodoListaSencilla<E> (elemento);
				nodo.cambiarSiguiente(res);
				anterior.cambiarSiguiente(nodo);
				cantidadElementos++;
			}

		}else
		{
			boolean encontre = false;
			NodoListaSencilla<E>estoy =  primerNodo;
			for (int i=0; i<cantidadElementos && !encontre;i++, estoy =  estoy.darSiguiente())
			{
				encontre = estoy.darElemento().equals(elemento)? true: false;
			}
			if (!encontre)
			{
				NodoListaSencilla<E> nodo = new NodoListaSencilla<E> (elemento);
				nodo.cambiarSiguiente(primerNodo);

				primerNodo= nodo;
				cantidadElementos++;
			}


		}

	}

	@Deprecated
	public ListIterator<E> listIterator() 
	{
		throw new UnsupportedOperationException ();
	}

	@Deprecated
	public ListIterator<E> listIterator(int index) 
	{
		throw new UnsupportedOperationException ();
	}


	/**
	 * Elimina el nodo que contiene al objeto que llega por parámetro. Actualiza el número de elementos.
	 * @param objeto el objeto que se desea eliminar. objeto != null
	 * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object objeto) 
	{
		//TODO Completar según la documentación
		boolean respuesta= false;

		while (primerNodo != null && primerNodo.darElemento().equals(objeto))
		{
			primerNodo = primerNodo.darSiguiente();
			respuesta = true;
			cantidadElementos--;
		}
		if (primerNodo != null)
		{			
			NodoListaSencilla<E> actual = primerNodo.darSiguiente();
			NodoListaSencilla<E> anterior = primerNodo;

			while  (actual !=null )
			{
				if (actual.darElemento().equals(objeto) )
				{
					anterior.cambiarSiguiente(actual.darSiguiente());
					respuesta = true; 
					cantidadElementos--;
					actual= anterior.darSiguiente();
				}else
				{
					anterior=actual;
					actual= actual.darSiguiente();
				}

			}
		}
		return respuesta;
	}

	/**
	 * Elimina el nodo en la posición por parámetro. Actualiza la cantidad de elementos.
	 * @param pos la posición que se desea eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
	 */
	public E remove(int pos) 
	{
		//TODO Completar según la documentación
		indexCorrect(pos,true);

		NodoListaSencilla<E>res = primerNodo;
		cantidadElementos--;
		if (pos==0)
		{
			primerNodo = primerNodo.darSiguiente();
		}else
		{
			NodoListaSencilla<E> anterior = res;
			res = res.darSiguiente();
			pos--;
			while  ( pos!=0 )
			{
				anterior=res;
				res= res.darSiguiente();
				pos--;
			}
			anterior.cambiarSiguiente(res.darSiguiente()); 

		}

		return res.darElemento();
	}

	/**
	 * Deja en la lista solo los elementos que están en la colección que llega por parámetro. La cantidad de elementos se actualiza.
	 * @param coleccion la colección de elementos a mantener. coleccion != null
	 * @return true en caso que se modifique (eliminación) la lista o false en caso contrario
	 */
	public boolean retainAll(Collection<?> coleccion) 
	{
		//TODO Completar según la documentación
		NodoListaSencilla<E>actual = primerNodo;
		NodoListaSencilla<E>anterior = primerNodo;
		boolean si= false;
		boolean res= false;
		while (actual!= null && !si)
		{
			for (Object objeto: coleccion)
			{
				if (objeto.equals(actual.darElemento()))
				{
					si=true;
					break;
				}
			}
			if (!si)
			{
				res=true;
				cantidadElementos--;
				actual = actual.darSiguiente();

			}

		}
		primerNodo = actual;
		if (actual != null )
		{
			anterior = actual;
			actual = actual.darSiguiente();
			while (actual!= null )
			{
				si= false;
				for (Object objeto: coleccion)
				{
					if (objeto.equals(actual.darElemento()))
					{
						si=true;
						break;
					}
				}
				if (!si)
				{
					anterior.cambiarSiguiente(actual.darSiguiente());
					actual = anterior.darSiguiente();
					cantidadElementos--;

				}else
				{
					anterior = actual;
					actual = anterior.darSiguiente();
				}
			}
		}

		return res;
	}

	/**
	 * Crea una lista con los elementos de la lista entre las posiciones dadas
	 * @param inicio la posición del primer elemento de la sublista. Se incluye en la sublista
	 * @param fin la posición del útlimo elemento de la sublista. Se excluye en la sublista
	 * @return una lista con los elementos entre las posiciones dadas
	 * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
	 */
	public List<E> subList(int inicio, int fin) 
	{
		//TODO Completar según la documentación
		indexCorrect(inicio,false);
		indexCorrect(fin,false);
		indexCorrect(fin-inicio,false);

		List<E> respuesta = new ArrayList<E>();
		NodoListaSencilla<E>actual = primerNodo;
		for (int i=0; i<inicio; i++,actual = primerNodo.darSiguiente())
		{

		}
		for (int i=inicio; i<fin; i++, actual = primerNodo.darSiguiente())
		{
			respuesta.add(actual.darElemento());
		}
		return respuesta;
	}

	public void invertir ()
	{

		if (primerNodo != null)
		{

			NodoListaSencilla<E> ant = primerNodo;
			NodoListaSencilla<E> estoy = primerNodo.darSiguiente();

			while (estoy != null)
			{
				NodoListaSencilla<E> estoy2 = estoy.darSiguiente();
				estoy.cambiarSiguiente(ant);
				ant = estoy;
				estoy = estoy2;

			}
			primerNodo.cambiarSiguiente(null);
			primerNodo = ant;

		}
	}

	public void ordenarPorBurbuja (Comparator <E> comparador)
	{
		if (primerNodo != null)
		{
			NodoListaSencilla<E> ant2 = null;
			NodoListaSencilla<E> ant = primerNodo;
			NodoListaSencilla<E> estoy = primerNodo.darSiguiente();
			int tam = 1;
			boolean first=true;
			while (estoy != null)
			{
				tam++;
				if (comparador.compare (ant.darElemento(),estoy.darElemento()) == 1)
				{
					if (first)
					{
						primerNodo = estoy;
						ant.cambiarSiguiente(estoy.darSiguiente());
						//System.out.println("FIRST " + ant+ " "+estoy);
						primerNodo.cambiarSiguiente(ant);
						
						NodoListaSencilla<E> m = ant;
						ant = estoy;
						estoy = m;
						//System.out.println("FIRST2 " + ant.darSiguiente()+ " "+estoy);
					}else
					{
						ant2.cambiarSiguiente(estoy);
						ant.cambiarSiguiente(estoy.darSiguiente());
						estoy.cambiarSiguiente(ant);
						NodoListaSencilla<E> m = ant;
						ant = estoy;
						estoy = m;
					}
				}
				ant2= ant;
				ant = estoy;
				estoy = estoy.darSiguiente();
				first=false;

			}
			
			for (int i=tam-2; i>=0 ; i--)
			{
				ant = primerNodo;
				estoy = primerNodo.darSiguiente();
				ant2 = null;
				first=true;
				for (int j=0; j<i ; j++)
				{
					
					if (comparador.compare (ant.darElemento(),estoy.darElemento()) == 1)
					{
						if (first)
						{
							primerNodo = estoy;
							ant.cambiarSiguiente(estoy.darSiguiente());
							primerNodo.cambiarSiguiente(ant);
							NodoListaSencilla<E> m = ant;
							ant = estoy;
							estoy = m;
						}else
						{
							ant2.cambiarSiguiente(estoy);
							ant.cambiarSiguiente(estoy.darSiguiente());
							estoy.cambiarSiguiente(ant);
							NodoListaSencilla<E> m = ant;
							ant = estoy;
							estoy = m;
						}
					}
					ant2= ant;
					ant = estoy;
					estoy = estoy.darSiguiente();
					first=false;
				}
			}

		}
	}
}
