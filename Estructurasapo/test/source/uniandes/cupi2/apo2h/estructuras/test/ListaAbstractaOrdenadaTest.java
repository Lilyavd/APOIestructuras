package uniandes.cupi2.apo2h.estructuras.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ListaAbstractaOrdenadaTest extends ListaAbstractaTest
{
	/**
	 * Arreglo del escenario 2 ordenado.
	 */
	protected static final int[] ARREGLO_ORDENADO_ESCENARIO_2 = {18, 80, 97, 98, 105, 130, 140, 221, 233, 236, 241, 244, 266, 350, 352, 356, 360, 383, 396, 446};
	
	/**
	 * Comparador de enteros a usar con la lista.
	 */
	protected Comparator<Integer> comparador;
	
	@Override
	public void setupEscenario1() 
	{
		comparador = new ComparadorEnteros();
	}
	
	@Override
	public void testIterator() 
	{
		super.testIterator();
		//Prueba que los elementos en el iterador estén en el orden correcto.
		Iterator<Integer> iterador = lista.iterator();
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertEquals("El elemento en la posición " + indiceActual + " del iterador no coincide con el que debería", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[indiceActual]), actual);
				indiceActual++;
			}
			catch(NoSuchElementException e)
			{
				fail("Indica que puede avanzar, pero lanza excepción al avanzar.");
			}
		}
	}
	
	@Override
	public void testToArray1() 
	{
		super.testToArray1();
		//Prueba que los elementos en el arreglo estén en el orden correcto.
		Object[] arreglo = lista.toArray();
		for(int i = 0; i <arreglo.length ; i++)
		{
			
			assertEquals("El elemento en la posición "+ i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[i], arreglo[i]);
		}
	}
	
	@Override
	public void testToArray2() 
	{
		super.testToArray2();
		//Prueba que los elementos en el arreglo estén en el orden correcto
		Integer[] arreglo = new Integer[lista.size()];
		arreglo = lista.toArray(arreglo);
		
		for(int i = 0; i < arreglo.length; i++)
		{
			assertEquals("El elemento en la posición "+ i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[i]), arreglo[i]);
		}
	}
	
	@Override
	public void testAdd() {
		super.testAdd();
		
		Object[] arreglo = lista.toArray();
		
		for(int i = 0; i < lista.size(); i ++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[i], arreglo[i]);
			assertTrue("La lista no contiene " + ARREGLO_ORDENADO_ESCENARIO_2[i], lista.contains(ARREGLO_ORDENADO_ESCENARIO_2[i]));
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[i]), lista.get(i));
		}
		
		assertTrue("La lista no se encuentra ordenada", seEncuentraOrdenada());
	}
	
	@Override
	public void testRemoveObject() {
		super.testRemoveObject();

		Object[] arreglo = lista.toArray();
		
		assertEquals("El primer elemento no es el esperado", new Integer(80), arreglo[0]);
		assertEquals("El elemento no es el esperado", new Integer(130), arreglo[4]);
		assertEquals("El elemento no es el esperado", new Integer(241), arreglo[9]);
		assertEquals("El elemento no es el esperado", new Integer(356), arreglo[13]);
		assertEquals("El elemento no es el esperado", new Integer(360), arreglo[14]);
		assertEquals("El elemento no es el esperado", new Integer(383), arreglo[15]);
		
		for(int i = 0; i < arreglo.length - 1; i ++)
		{
			int actual = ((Integer)arreglo[i]).intValue();
			int siguiente = ((Integer) arreglo[i + 1]).intValue();
			
			assertTrue("Los elementos no se encuentran en el orden correcto", actual - siguiente < 0);
			
			boolean contieneActual = false;
			boolean contieneSiguiente = false;
			for(Object objetoActual: arreglo)
			{
				int numActual = (Integer) objetoActual;
				if(numActual == actual)
					contieneActual = true;
				else if(numActual == siguiente)
					contieneSiguiente = true;
				
				if(contieneActual && contieneSiguiente)
					break;					
			}
			
			boolean actualNoEliminado = actual != 350 && actual != 396 && actual != 18 && actual != 446;
			boolean siguienteNoEliminado = siguiente != 350 && siguiente != 396 && siguiente != 18 && siguiente != 446;
			
			assertTrue("El número actual fue eliminiado y sigue en la lista", actualNoEliminado);
			assertTrue("El número siguiente fue eliminiado y sigue en la lista", siguienteNoEliminado);
			
			assertTrue("El número actual aparece en la lista, pero no debería", contieneActual);
			assertTrue("El número siguiente aparece en la lista, pero no debería", contieneSiguiente);
		}
		
		assertTrue("La lista no se encuentra ordenada", seEncuentraOrdenada());
	}
	
	@Override
	public void testAddAll() {
		super.testAddAll();
		//18, 80, 97, 98, 105, 130, 140, 221, 233, 236, 241, 244, 266, 350, 352, 356, 360, 383, 396, 446
		//18, 50, 80, 97, 98, 105, 130, 140, 145, 221, 233, 236, 241, 244, 259, 266, 350, 352, 356, 360, 383, 396, 400, 446
		/*elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);*/
		Object[] arreglo = lista.toArray();
		
		int i = 0;
		int j = 0;
		
		assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[j], arreglo[i]);
		assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[j]), lista.get(i));
		
		i++;
		j++;
		
		assertEquals("El elemento en la posición " + i + " no es el esperado", 50, arreglo[i]);
		assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(50), lista.get(i));
		i++;
		
		for(; j < 7; j ++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[j], arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[j]), lista.get(i));
			i++;
		}
		
		assertEquals("El elemento en la posición " + i + " no es el esperado", 145, arreglo[i]);
		assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(145), lista.get(i));
		i++;
		
		for(; j < 12; j++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[j], arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[j]), lista.get(i));
			i++;
		}
		
		assertEquals("El elemento en la posición " + i + " no es el esperado", 259, arreglo[i]);
		assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(259), lista.get(i));
		i++;
		
		for(; j < 19; j++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[j], arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[j]), lista.get(i));
			i++;
		}
		
		assertEquals("El elemento en la posición " + i + " no es el esperado", 400, arreglo[i]);
		assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(400), lista.get(i));
		
		i++;
		
		for(; j < 20; j++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ORDENADO_ESCENARIO_2[j], arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[j]), lista.get(i));
			i++;
		}
		
		assertTrue("La lista no se encuentra ordenada", seEncuentraOrdenada());
		
	}
	
	@Override
	public void testGet() {
		super.testGet();
		
		for(int i = 0; i < lista.size(); i ++)
		{
			assertEquals("El elemento en la posición " + i  + " no es el esperado", new Integer(ARREGLO_ORDENADO_ESCENARIO_2[i]), lista.get(i));
		}
	}
	
	@Override
	public void testIndexOf() {
		super.testIndexOf();
		
		for(int i = 0; i < ARREGLO_ORDENADO_ESCENARIO_2.length; i ++)
		{
			assertEquals("El índice no es el esperado", i, lista.indexOf( ARREGLO_ORDENADO_ESCENARIO_2[i]));
		}
	}
	
	@Override
	public void testLastIndexOf() {
		super.testLastIndexOf();
		for(int i = 0; i < ARREGLO_ORDENADO_ESCENARIO_2.length; i ++)
		{
			assertEquals("El índice no es el esperado", i, lista.indexOf( ARREGLO_ORDENADO_ESCENARIO_2[i]));
		}
	}
	
	/**
	 * Método que revisa si la lista se encuentra ordenada.
	 * @return True si la lista se encuentra ordenada
	 */
	protected boolean seEncuentraOrdenada()
	{
		ComparadorEnteros comp = new ComparadorEnteros();
		for(int i = 0; i < lista.size() - 1; i ++)
		{
			if(comp.compare(lista.get(i), lista.get(i + 1)) > 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Comparador de enteros.
	 */
	protected class ComparadorEnteros implements Comparator<Integer>
	{
		@Override
		public int compare(Integer i1, Integer i2) {
			int comparacion = i1.intValue() - i2.intValue();
			return comparacion > 0? 1: (comparacion < 0? -1: 0);
		}
		
	}
}
