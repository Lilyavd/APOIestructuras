package uniandes.cupi2.apo2h.utils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import uniandes.cupi2.apo2h.karaoke.mundo.CriteriosComparacionCanciones;

public  class Buscador<V> implements Serializable
{
	/**
	 * Constante serializadora
	 */
	private static final long serialVersionUID = 4211817212685355336L;

	/**
	 * Buscar en lista no ordenada
	 * param elementos la lista de elementos donde se desea buscar. elementos != null
	 * @param comparador el comparador asociado al criterio de busqueda. comparador != null
	 * @param elementoABuscar Elemento del que se quiere saber la posici�n, el elemento puede s�lo tener el el atributo por el cual se va a comparar.
	 * @return True si lo encontro, false si no.
	 */
	public V buscarNoOrdenada (List<V> elementos, Comparator<V> comparador, V elementoABuscar) 
	{
	
		V buscado = null;
		for ( int j = 0; j < elementos.size() && buscado == null; j++)
		{
			if ( comparador.compare(elementos.get(j), elementoABuscar) == 0 )
			{
				buscado = elementos.get(j);
			}
				
		}
		return buscado;
	}
	/**
	 * Busca el elemento mayor dentro de una lista de acuerdo a un criterio
	 * @param elementos la lista de elementos donde se desea buscar. elementos != null
	 * @param comparador el comparador asociado al criterio de busqueda. comparador != null
	 * @return el elemento mayor o null en caso que la lista no tenga elementos
	 */
	public V buscarMayor(List<V> elementos, Comparator<V> comparador) 
	{
		// TODO Complete seg�n la documentaci�n
		V buscado = null;
		for ( V j: elementos)
		{
			if ( buscado == null || comparador.compare(j, buscado) == 1 )
				buscado = j;
		}
		return buscado;
	}
	
	
	/**
	 * Busca el elemento menor dentro de una lista de acuerdo a un criterio
	 * @param elementos la lista de elementos donde se desea buscar. elementos != null
	 * @param comparador el comparador asociado al criterio de busqueda. comparador != null
	 * @return el elemento menor o null en caso que la lista no tenga elementos
	 */
	public V buscarMenor( List<V> elementos, Comparator<V> comparador)
	{
		//TODO Complete seg�n la documentaci�n
		V buscado = null;
		for ( V j: elementos)
		{
			
			if ( buscado == null || comparador.compare(j, buscado) == -1 )
				buscado = j;
		}
		return buscado;
	}
	
	/**
	 * Busca el �ndice del elemento que llega como par�metro, busc�ndolo por el criterio del comparador. Lo realiza por medio de b�squeda binaria.
	 * @param elementos la lista de elementos donde se desea buscar. elementos != null
	 * @param comparador el comparador asociado al criterio de busqueda. comparador != null
	 * @param elementoABuscar Elemento del que se quiere saber la posici�n, el elemento puede s�lo tener el el atributo por el cual se va a comparar.
	 * @return Retorna la posici�n del elemento dado. Si el elemento no existe se retorna -1.
	 */
	public int buscarIndiceElementoBinario(List<V> elementos, Comparator<V> comparador, V elementoABuscar)
	{
		//TODO Complete seg�n la documentaci�n
		int inicio=0, hasta = elementos.size()-1;
		int res=-1;
		while (inicio <= hasta && res == -1 )
		{ 
			int mit = (inicio+hasta)/2;
			int cmp = comparador.compare(elementoABuscar, elementos.get(mit));
			if (cmp == 0 )
				res = mit;
			else if ( cmp == 1 )
				inicio = mit+1;
			else
				hasta = mit-1;
		}
		return res;
	}
	
	
}