package uniandes.cupi2.apo2h.estructuras.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public abstract class ListaAbstractaNoOrdenadaTest extends ListaAbstractaTest
{

	@Override
	public void testIterator() 
	{
		super.testIterator();
		//Prueba que los elementos del iterador estén en el orden correcto.
		Iterator<Integer> iterador = lista.iterator();
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertEquals("El elemento en la posición " + indiceActual + " del iterador no coincide con el que debería", new Integer(ARREGLO_ESCENARIO_2[indiceActual]), actual);
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
		for(int i = 0; i < arreglo.length; i++)
		{
			assertEquals("El elemento en la posición "+ i + " no es el esperado", ARREGLO_ESCENARIO_2[i], arreglo[i]);
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
			assertEquals("El elemento en la posición "+ i + " no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), arreglo[i]);
		}
	}
	
	@Override
	public void testAdd() {
		super.testAdd();
		
		Object[] arreglo = lista.toArray();
		
		for(int i = 0; i < lista.size(); i ++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ESCENARIO_2[i], arreglo[i]);
			assertTrue("La lista no contiene " + ARREGLO_ESCENARIO_2[i], lista.contains(ARREGLO_ESCENARIO_2[i]));
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
		}
	}
	
	@Override
	public void testRemoveObject() {
		super.testRemoveObject();
		Object[] arreglo = lista.toArray();
		
		assertEquals("El primer elemento no es el esperado", new Integer(383), arreglo[0]);
		assertEquals("El elemento no es el esperado", new Integer(266), arreglo[4]);
		assertEquals("El elemento no es el esperado", new Integer(221), arreglo[9]);
		assertEquals("El elemento no es el esperado", new Integer(352), arreglo[13]);
		assertEquals("El elemento no es el esperado", new Integer(98), arreglo[14]);
		assertEquals("El elemento no es el esperado", new Integer(97), arreglo[15]);
		
		
	}
	
	@Override
	public void testAddAll() {
		super.testAddAll();
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);
		
		
		Object[] arreglo = lista.toArray();
		int i = 0;
		for(; i < ARREGLO_ESCENARIO_2.length; i ++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", ARREGLO_ESCENARIO_2[i], arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
		}
		
		for(int j = 0; j < elementos.size(); j++)
		{
			assertEquals("El elemento en la posición " + i + " no es el esperado", elementos.get(j), arreglo[i]);
			assertEquals("El elemento en la posición " + i + " no es el esperado", new Integer(elementos.get(j)), lista.get(i));
			i++;
		}		
	}
	
	@Override
	public void testGet() {
		super.testGet();
		
		for(int i = 0; i < lista.size(); i ++)
		{
			assertEquals("El elemento en la posición " + i  + " no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
		}
	}
	
	@Override
	public void testIndexOf() {
		super.testIndexOf();
		
		for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i ++)
		{
			assertEquals("El índice no es el esperado", i, lista.indexOf( ARREGLO_ESCENARIO_2[i]));
		}
	}
	
	@Override
	public void testLastIndexOf() {
		super.testLastIndexOf();
		
		for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i ++)
		{
			assertEquals("El índice no es el esperado", i, lista.indexOf( ARREGLO_ESCENARIO_2[i]));
		}
	}
	
	@Override
	public void testSubList() {
		super.testSubList();
		
		try
		{
			List<Integer> sublista = lista.subList(0, 20);
			assertEquals("La sublista debería tener 20 elementos", 20, sublista.size());
			for(int i = 0; i < sublista.size(); i++)
			{
				assertEquals("El elemento de la sublista no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
			}
			
			sublista = lista.subList(0, 10);
			assertEquals("La sublista debería tener 10 elementos", 10, sublista.size());
			for(int i = 0; i < 10; i++)
			{
				assertEquals("El elemento de la sublista no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
			}
			
			sublista = lista.subList(10, 20);
			assertEquals("La sublista debería tener 10 elementos", 10, sublista.size());
			for(int i = 10; i < 10; i++)
			{
				assertEquals("El elemento de la sublista no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i + 10]), lista.get(i));
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("Los índices se encuentran dentro de los índices permitidos, no debería fallar.");
		}
	}
	
	/**
	 * Prueba el método add all con índice.
	 */
	@Test
	public void testAddAllIndice()
	{
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);
		
		//Prueba la lista vacía.
		try
		{
			lista.addAll(-1, elementos);
			fail("Debería lanzar la excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Funciona
		}
		
		try
		{
			lista.addAll(1, elementos);
			fail("Debería lanzar la excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Funciona
		}
		
		try
		{
			lista.addAll(0, elementos);
			assertEquals("El número de elementos no es correcto", 4, lista.size());
			for(int i = 0; i < elementos.size(); i ++)
			{
				assertEquals("El elemento no es el esperado", elementos.get(i), lista.get(i));
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar excepción, debería agregar al inicio.");
		}
		
		lista.clear();
		
		//Prueba con 20 elementos.
		setupEscenario2();
		
		try
		{
			lista.addAll(-1, elementos);
			fail("Debería lanzar la excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Funciona
		}
		
		try
		{
			lista.addAll(21, elementos);
			fail("Debería lanzar la excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Funciona
		}
		
		try
		{
			lista.addAll(0, elementos);
			assertEquals("El número de elementos no es correcto", 24, lista.size());
			for(int i = 0; i < elementos.size(); i ++)
			{
				assertEquals("El elemento no es el esperado", elementos.get(i), lista.get(i));
			}
			
			for(int i = 4; i < lista.size(); i ++)
			{
				assertEquals("El elemento no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i - 4]), lista.get(i));
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar excepción, los índices son correctos.");
		}
		
		lista.clear();
		setupEscenario2();
		
		try
		{
			lista.addAll(20, elementos);
			assertEquals("El número de elementos no es correcto", 24, lista.size());
			
			for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i ++)
			{
				assertEquals("El elemento no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
			}
			
			for(int i = 0; i < elementos.size(); i ++)
			{
				assertEquals("El elemento no es el esperado", elementos.get(i), lista.get(i + 20));
			}
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar excepción, los índices son correctos.");
		}
		
		lista.clear();
		setupEscenario2();
		
		try
		{
			lista.addAll(10, elementos);
			assertEquals("El número de elementos no es correcto", 24, lista.size());
			
			for(int i = 0; i < 10; i ++)
			{
				assertEquals("El elemento no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
			}
			
			for(int i = 0; i < elementos.size(); i ++)
			{
				assertEquals("El elemento no es el esperado", elementos.get(i), lista.get(i + 10));
			}
			
			for(int i = 10; i < ARREGLO_ESCENARIO_2.length; i ++)
			{
				assertEquals("El elemento no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i + 4));
			}
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
			fail("No debería lanzar excepción, los índices son correctos.");
		}
	}
	
	/**
	 * Prueba el método set con un índice.
	 */
	@Test
	public void testSetIndex()
	{
		//Prueba con la lista vacía
		try
		{
			lista.set(0, 5);
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		try
		{
			lista.set(20, 5);
			fail("Debería lanzar excepción, está por fuera del arreglo");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.set(-1, 5);
			fail("Debería lanzar excepción, está por fuera del arreglo");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.set(0, 5);
			assertEquals("El tamaño de la lista no debió cambiar", 20, lista.size());
			assertEquals("El elemento no fue modificado", new Integer(5), lista.get(0));
			
			lista.set(19, 200);
			assertEquals("El tamaño de la lista no debió cambiar", 20, lista.size());
			assertEquals("El elemento no fue modificado", new Integer(200), lista.get(19));
			
			lista.set(10, 320);
			assertEquals("El tamaño de la lista no debió cambiar", 20, lista.size());
			assertEquals("El elemento no fue modificado", new Integer(320), lista.get(10));
			
			lista.set(16, 518);
			assertEquals("El tamaño de la lista no debió cambiar", 20, lista.size());
			assertEquals("El elemento no fue modificado", new Integer(518), lista.get(16));
			
			lista.set(4, 1250);
			assertEquals("El tamaño de la lista no debió cambiar", 20, lista.size());
			assertEquals("El elemento no fue modificado", new Integer(1250), lista.get(4));
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería fallar, los índices se encuentra dentro de la lista.");
		}
	}
	
	/**
	 * Prueba el método add en un índice.
	 */
	@Test
	public void addIndice()
	{
		//Prueba con la lista vacía
		try
		{
			lista.add(1, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(0, 5);
			assertEquals("El tamaño de la lista no es correcto", 1, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(5), lista.get(0));
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar la excepción, el índice se encuentra dentro de la lista.");
		}
		
		//Prueba con 20 elementos
		lista.clear();
		setupEscenario2();
		
		try
		{
			lista.add(-1, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(21, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(0, 5);
			assertEquals("El tamaño de la lista no es correcto", 21, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(5), lista.get(0));
			assertEquals("El elemento no es el esperado", new Integer(350), lista.get(1));
			
			lista.add(21, 290);
			assertEquals("El tamaño de la lista no es correcto", 22, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(290), lista.get(21));
			assertEquals("El elemento no es el esperado", new Integer(396), lista.get(20));
			
			lista.add(10, 580);
			assertEquals("El tamaño de la lista no es correcto", 23, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(580), lista.get(10));
			assertEquals("El elemento no es el esperado", new Integer(80), lista.get(9));
			assertEquals("El elemento no es el esperado", new Integer(360), lista.get(11));
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar la excepción, el índice se encuentra dentro de la lista.");
		}
	}
	
	/**
	 * Prueba el método listIterator
	 */
	@Test
	public void testListIterator()
	{
		//Prueba con la lista vacía.
		ListIterator<Integer> iterator = lista.listIterator();
		assertFalse("No debería poder avanzar", iterator.hasNext());
		assertFalse("No debería poder retroceder", iterator.hasPrevious());
		
		try
		{
			iterator.next();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			//
		}
		
		try
		{
			iterator.previous();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			//
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		iterator = lista.listIterator();
		assertFalse("No debería poder retroceder", iterator.hasPrevious());
		try
		{
			iterator.previous();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			//
		}
		
		int i = 0;
		for(; i < 10; i++)
		{
			assertTrue("Debería poder avanzar", iterator.hasNext());
			assertEquals("El elemento siguiente no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), iterator.next());
		}
		
		i--;
		
		for(; i >= 0; i--)
		{
			assertTrue("Debería poder retroceder", iterator.hasPrevious());
			assertEquals("El elemento siguiente no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), iterator.previous());
		}
		
		assertFalse("No debería poder retroceder", iterator.hasPrevious());
		try
		{
			iterator.previous();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			//
		}
		
		i++;
		
		for(; i < ARREGLO_ESCENARIO_2.length; i++)
		{
			assertTrue("Debería poder avanzar", iterator.hasNext());
			assertEquals("El elemento siguiente no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), iterator.next());
		}
		
		assertFalse("No debería poder retroceder", iterator.hasNext());
		try
		{
			iterator.next();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			
		}
		
		i--;
		
		for(; i >= 0; i--)
		{
			assertTrue("Debería poder retroceder", iterator.hasPrevious());
			assertEquals("El elemento siguiente no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), iterator.previous());
		}
		
		assertFalse("No debería poder retroceder", iterator.hasPrevious());
		try
		{
			iterator.previous();
			fail("Debería lanzar la excepción");
		}
		catch(NoSuchElementException e)
		{
			//
		}
	}
}