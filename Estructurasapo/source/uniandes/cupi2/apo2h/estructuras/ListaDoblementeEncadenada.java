package uniandes.cupi2.apo2h.estructuras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Clase que representa la lista doblemente encadenada
 * @param <E> Tipo de los objetos que almacenará la lista.
 */
public class ListaDoblementeEncadenada<E> extends ListaEncadenadaAbstracta<E>
{

	/**
	 *  Constante de serialización
	 */
	private static final long serialVersionUID = 145645L;

	/**
	 * Construye una lista vacia
	 * <b>post:< /b> se ha inicializado el primer nodo en null
	 */
	public ListaDoblementeEncadenada() 
	{
		primerNodo = null;
		cantidadElementos = 0;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámentro
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaDoblementeEncadenada(E nPrimero)
	{
		if(primerNodo == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		primerNodo = new NodoListaDoble<E>(nPrimero);
		cantidadElementos = 1;
	}

	/**
	 * Agrega un elemento al final de la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
	 * Se actualiza la cantidad de elementos.
	 * @param e el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public boolean add(E e) 
	{
		// TODO Completar según la documentación

		if (e == null)
			throw new NullPointerException ();
		boolean res = false;
		if (primerNodo != null)
		{
			NodoListaDoble<E> estoy = (NodoListaDoble<E>) primerNodo;
			while (estoy.darSiguiente() != null && !estoy.darElemento().equals(e))
			{
				estoy= (NodoListaDoble<E>) estoy.darSiguiente();
			}
			if (estoy.darSiguiente() == null && !estoy.darElemento().equals(e))
			{
				NodoListaDoble<E> nodo = new NodoListaDoble<E>(e);
				estoy.cambiarSiguiente(nodo);
				nodo.cambiarAnterior(estoy);
				res=true;
			}

		}else
		{
			primerNodo = new NodoListaDoble<E>(e);
			res= true;
		}
		if (res)
			cantidadElementos++;
		return res;
	}

	/**
	 * Agrega un elemento al final de la lista. Actualiza la cantidad de elementos.
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param elemento el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public void add(int index, E elemento) 
	{
		// TODO Completar según la documentación	
		indexCorrect(index,false);

		if (elemento == null)
			throw new NullPointerException ("El elemento que se quiere agregar es null");

		if (index != 0)
		{
			NodoListaDoble<E> anterior = (NodoListaDoble<E>) primerNodo;
			NodoListaDoble<E> res = (NodoListaDoble<E>) primerNodo.darSiguiente();
			boolean encontre = false;
			index--;
			NodoListaDoble<E>estoy = (NodoListaDoble<E>) primerNodo;
			for (int i=0; i<cantidadElementos && !encontre;i++, estoy = (NodoListaDoble<E>) estoy.darSiguiente())
			{
				if (index==0)
					encontre = estoy.darElemento().equals(elemento)? true: false;
				if (index!=0 )
				{
					anterior = res;
					res= (NodoListaDoble<E>) res.darSiguiente();
					index--;
				}

			}

			if (!encontre)
			{
				NodoListaDoble<E> nodo = new NodoListaDoble<E> (elemento);
				nodo.cambiarSiguiente(res);
				if (res != null)
					res.cambiarAnterior(nodo);
				nodo.cambiarAnterior(anterior);
				anterior.cambiarSiguiente(nodo);
				cantidadElementos++;
			}

		}else
		{
			boolean encontre = false;
			NodoListaDoble<E>estoy = (NodoListaDoble<E>) primerNodo;
			for (int i=0; i<cantidadElementos && !encontre;i++, estoy = (NodoListaDoble<E>) estoy.darSiguiente())
			{
				encontre = estoy.darElemento().equals(elemento)? true: false;
			}
			if (!encontre)
			{
				NodoListaDoble<E> nodo = new NodoListaDoble<E> (elemento);
				nodo.cambiarSiguiente(primerNodo);
				if (primerNodo != null)
					((NodoListaDoble<E>) primerNodo).cambiarAnterior(nodo);
				primerNodo= nodo;
				cantidadElementos++;
			}


		}


	}

	/**
	 * Método que retorna el iterador de la lista.
	 * @return el iterador de la lista.
	 */
	public ListIterator<E> listIterator() 
	{
		return new IteradorLista<E>((NodoListaDoble<E>) primerNodo);
	}

	/**
	 * Método que retorna el iterador de la lista desde donde se indica.
	 * @param index Índice desde se quiere comenzar a iterar.
	 * @return el iterador de la lista.
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public ListIterator<E> listIterator(int index) 
	{
		if(index< 0 || index >= size())
			throw new IndexOutOfBoundsException("El índice buscado está por fuera de la lista.");
		return new IteradorLista<E>((NodoListaDoble<E>) darNodo(index));
	}

	/**
	 * Elimina el nodo que contiene al objeto que llega por parámetro.
	 * Actualiza la cantidad de elementos.
	 * @param objeto el objeto que se desea eliminar. objeto != null
	 * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o) 
	{
		// TODO Completar según la documentación
		boolean respuesta= false;

		while (primerNodo != null && primerNodo.darElemento().equals(o))
		{
			primerNodo = primerNodo.darSiguiente();
			respuesta = true;
			cantidadElementos--;
		}
		if (primerNodo != null)
		{			
			NodoListaDoble<E> actual = (NodoListaDoble<E>) primerNodo.darSiguiente();
			NodoListaDoble<E> anterior = (NodoListaDoble<E>) primerNodo;

			while  (actual !=null )
			{
				if (actual.darElemento().equals(o) )
				{
					anterior.cambiarSiguiente(actual.darSiguiente());
					respuesta = true; 
					cantidadElementos--;
					actual= (NodoListaDoble<E>) anterior.darSiguiente();
				}else
				{
					anterior=actual;
					actual= (NodoListaDoble<E>) actual.darSiguiente();
				}

			}
		}
		return respuesta;
	}

	/**
	 * Elimina el nodo en la posición por parámetro.
	 * Actualiza la cantidad de elementos.
	 * @param pos la posición que se desea eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public E remove(int index) 
	{
		// TODO Completar según la documentación
		indexCorrect(index ,true);

		NodoListaDoble<E>res = (NodoListaDoble<E>) primerNodo;
		cantidadElementos--;
		if (index ==0)
		{
			primerNodo = primerNodo.darSiguiente();
			((NodoListaDoble<E>) primerNodo).cambiarAnterior(null);
		}else
		{
			NodoListaDoble<E> anterior = res;
			res = (NodoListaDoble<E>) res.darSiguiente();
			index --;
			while  ( index !=0 )
			{
				anterior=res;
				res= (NodoListaDoble<E>) res.darSiguiente();
				index --;
			}
			anterior.cambiarSiguiente(res.darSiguiente());
			if (res.darSiguiente() != null)
				((NodoListaDoble<E>) res.darSiguiente()).cambiarAnterior(null);


		}

		return res.darElemento();
	}

	/**
	 * Deja en la lista sólo los elementos que están en la colección que llega por parámetro.
	 * Actualiza la cantidad de elementos
	 * @param coleccion la colección de elementos a mantener. coleccion != null
	 * @return true en caso que se modifique (eliminación) la lista o false en caso contrario
	 */
	public boolean retainAll(Collection<?> c) 
	{
		// TODO Completar según la documentación
		NodoListaDoble<E>actual = (NodoListaDoble<E>) primerNodo;
		NodoListaDoble<E>anterior = (NodoListaDoble<E>) primerNodo;
		boolean si= false;
		boolean res= false;
		while (actual!= null && !si)
		{
			for (Object objeto: c)
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
				actual = (NodoListaDoble<E>) actual.darSiguiente();

			}

		}
		primerNodo = actual;
		if (actual != null )
		{
			actual.cambiarAnterior(null);
			anterior = actual;
			actual = (NodoListaDoble<E>) actual.darSiguiente();
			while (actual!= null )
			{
				si= false;
				for (Object objeto: c)
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
					actual = (NodoListaDoble<E>) anterior.darSiguiente();
					if (actual!=null)
					{
						actual.cambiarAnterior(anterior);
					}
					cantidadElementos--;

				}else
				{
					anterior = actual;
					actual = (NodoListaDoble<E>) anterior.darSiguiente();
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
		// TODO Completar según la documentación
		indexCorrect(inicio,false);
		indexCorrect(fin,false);
		indexCorrect(fin-inicio,false);

		List<E> respuesta = new ArrayList<E>();
		NodoListaDoble<E>actual = (NodoListaDoble<E>) primerNodo;
		for (int i=0; i<inicio; i++,actual = (NodoListaDoble<E>) primerNodo.darSiguiente())
		{

		}
		for (int i=inicio; i<fin; i++, actual = (NodoListaDoble<E>) primerNodo.darSiguiente())
		{
			respuesta.add(actual.darElemento());
		}
		return respuesta;

	}

}
