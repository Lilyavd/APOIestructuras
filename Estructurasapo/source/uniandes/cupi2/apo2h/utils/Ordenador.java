package uniandes.cupi2.apo2h.utils;

import java.util.Comparator;
import java.util.List;


public class Ordenador<V> {
	/**
	 * Ordena los elementos de la lista que llega por parámetro
	 * 
	 * @param algoritmo
	 *            el algoritmo que se desea usar para ordenar. algoritmo != null
	 * @param ascendente
	 *            la dirección del ordenamiento. true si es ascedente o false si
	 *            es descendente
	 * @param comparador
	 *            el criterio de comparación que se usará. comaprador != null
	 * @param elementos
	 *            la lista de elementos que se desea ordenar. elementos != null
	 */
	public void ordenar(Ordenamientos algoritmo, boolean ascendente, Comparator<V> comparador, List<V> elementos) {
		switch (algoritmo)
		{
		case BURBUJA:
			ordenarBurbuja(elementos, ascendente, comparador);
			break;
		case SELECCION:
			ordenarSeleccion(elementos, ascendente, comparador);
			break;
		case INSERCION:
			ordenarInsercion(elementos, ascendente, comparador);
			break;
		case BURBUJA_BIDERECCIONAL:
			ordenarShaker(elementos, ascendente, comparador);
			break;
		case GNOME:
			ordenarGnome(elementos, ascendente, comparador);
			break;
		}

	}

	/**
	 * Ordena la lista usando el algoritmo de inscerción post: la lista se
	 * encuentra ordenada
	 * 
	 * @param lista
	 *            la lista que se desea ordenar. lsita != null
	 * @param ascendnte
	 *            indica si se debe ordenar de mamenra ascendente, de lo
	 *            contrario se ordenará de manera descendente
	 * @param comparador
	 *            comparador de elementos tipo T que se usará para ordenar la
	 *            lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarInsercion(List<V> lista, boolean ascendnte, Comparator<V> comparador) {
		
		int cmp = 1;
		if ( ascendnte)
			cmp = -1;
		for (int i=1; i<lista.size() ; i++)
		{
			for (int j=i-1; j>=0 && comparador.compare (lista.get(j+1),lista.get(j)) == cmp; j--)
			{
				V k = lista.get(j+1);
				lista.set(j+1,lista.get(j)) ;
				lista.set(j, k);
			}

		}

	}

	/**
	 * Ordena la lista usando el algoritmo de selección 
	 * <b> post: </b> la lista se encuentra ordenada
	 * 
	 * @param lista
	 *            la lista que se desea ordenar. lista != null
	 * @param ascendnte
	 *            indica si se debe ordenar de mamenra ascendente, de lo
	 *            contrario se ordenará de manera descendente
	 * @param comparador
	 *            comparador de elementos tipo T que se usará para ordenar la
	 *            lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarSeleccion(List<V> lista, boolean ascendnte, Comparator<V> comparador) {
	
		int cmp = -1;
		if ( ascendnte)
			cmp = 1;
		for (int i=0; i<lista.size() ; i++)
		{ 
			int pos=i; 
			for (int j=i+1; j<lista.size() ; j++)
			{
				if (comparador.compare (lista.get(pos),lista.get(j)) == cmp )
				{ 
					pos=j;
				}
			}
			if (i!=pos)
			{
				V j = lista.get(pos);
				lista.set(pos,lista.get(i)) ;
				lista.set(i, j);
			}
		}

	}

	/**
	 * Ordena la lista usando el algoritmo de burbuja post: la lista se
	 * encuentra ordenada
	 * 
	 * @param lista
	 *            la lista que se desea ordenar. lsita != null
	 * @param ascendnte
	 *            indica si se debe ordenar de mamenra ascendente, de lo
	 *            contrario se ordenará de manera descendente
	 * @param comparador
	 *            comparador de elementos tipo T que se usará para ordenar la
	 *            lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarBurbuja(List<V> lista, boolean ascendnte, Comparator<V> comparador) {
		
		int cmp = -1;
		if ( ascendnte)
			cmp = 1;
		for (int i=lista.size()-1; i>=0 ; i--)
		{
			boolean ya = true;
			for (int j=0; j<i ; j++)
			{
				if (comparador.compare (lista.get(j),lista.get(j+1)) == cmp)
				{
					V k = lista.get(j+1);
					lista.set(j+1,lista.get(j)) ;
					lista.set(j, k);
					ya= false;
				}

			}
			if (ya)
				break;
		}
	}

	/**
	 * Ordena la lista usando el algoritmo de shake (burbuja bidireccional)
	 * post: la lista se encuentra ordenada
	 * 
	 * @param lista
	 *            la lista que se desea ordenar. lsita != null
	 * @param ascendnte
	 *            indica si se debe ordenar de mamenra ascendente, de lo
	 *            contrario se ordenará de manera descendente
	 * @param comparador
	 *            comparador de elementos tipo T que se usará para ordenar la
	 *            lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarShaker(List<V> lista, boolean ascendnte, Comparator<V> comparador) {
		
		int cmp = -1;
		if ( ascendnte)
			cmp = 1;
		int inicio = 0, fin = lista.size()-1;
		boolean izqder=true;
		int i=0;
		while ( fin>inicio )
		{
			if (izqder)
			{
				if (i<fin)
				{
					if (comparador.compare (lista.get(i),lista.get(i+1)) == cmp)
					{
						V k = lista.get(i+1);
						lista.set(i+1,lista.get(i)) ;
						lista.set(i, k);
					}
					i++;
				}else
				{
					izqder = false;
					fin--;
					i = fin;
				}
			}else
			{
				if (i > inicio)
				{
					if (comparador.compare (lista.get(i),lista.get(i-1)) == cmp*-1)
					{
						V k = lista.get(i-1);
						lista.set(i-1,lista.get(i)) ;
						lista.set(i, k);
					}
					i--;
				}else
				{
					izqder= true;
					inicio++;
					i= inicio;
				}
			}

		}
	}

	/**
	 * Ordena la lista usando el algoritmo de Gnome 
	 * post: la lista se encuentra ordenada
	 * 
	 * @param lista
	 *            la lista que se desea ordenar. lsita != null
	 * @param ascendnte
	 *            indica si se debe ordenar de mamenra ascendente, de lo
	 *            contrario se ordenará de manera descendente
	 * @param comparador
	 *            comparador de elementos tipo T que se usará para ordenar la
	 *            lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarGnome(List<V> lista, boolean ascendnte, Comparator<V> comparador) {
		
		int cmp = -1;
		if ( ascendnte)
			cmp = 1;
		int i=1;
		while ( i<lista.size() )
		{
			if (comparador.compare (lista.get(i),lista.get(i-1)) == cmp)
			{
				i++;
			}else 
			{
				V k = lista.get(i-1);
				lista.set(i-1,lista.get(i)) ;
				lista.set(i, k);
				i -= (i>1) ? 1 : 0;
			}

		}

	}
}