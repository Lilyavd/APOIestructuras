package uniandes.cupi2.apo2h.estructuras.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase de prueba con los métodos necesarios para probar cualquier tipo de lista.
 * Esta clase de pruebas no tiene en cuenta las particularidades de cada tipo de lista.
 *
 */
public abstract class ListaAbstractaTest 
{
	/**
	 * Lista sobre la que se realizarán las pruebas.
	 */
	protected List<Integer> lista;
	
	/**
	 * Arreglo con los elementos del escenario 2 (sirve para realizar pruebas exhaustivas).
	 */
	protected static final int[] ARREGLO_ESCENARIO_2 = {350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396};
	
	/**
	 * Crea el escenario 1. Un escenario vacío.
	 */
	@Before
	public abstract void setupEscenario1();
	
	/**
	 * Crea el escenario 2 (sin números repetidos) agregando los siguientes números (en este orden):
	 * 
	 * 350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396
	 */
	public void setupEscenario2()
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			lista.add(actual);
		}
	}
	
	/**
	 * Prueba que revisa el método size.
	 */
	@Test
	public void testSize()
	{
		//Prueba la lista vacía.
		assertEquals("El tamaño de la lista vacía no es correcto", 0, lista.size());
		
		//Prueba la lista con dos elementos
		
		lista.add(5);
		lista.add(30);
		
		assertEquals("El tamaño de la lista con dos elementos no es correcto", 2, lista.size());
		
		//Prueba vaciando la lista
		lista.clear();
		assertEquals("El tamaño de la lista vacía no es correcto", 0, lista.size());
		
		//Prueba la lista con 20 elementos
		setupEscenario2();
		
		assertEquals("El tamaño de la lista con 20 elementos no es correcto", ARREGLO_ESCENARIO_2.length, lista.size());
		
		//Agrega dos elementos más y prueba
		
		lista.add(5);
		lista.add(30);
		
		assertEquals("El tamaño de la lista con 22 elementos no es correcto", ARREGLO_ESCENARIO_2.length + 2, lista.size());
	}
	
	/**
	 * Prueba que revisa el método isEmpty
	 */
	@Test
	public void testIsEmpty()
	{
		//Prueba la lista vacía.
		assertTrue("Al inicio la lista debería estar vacía", lista.isEmpty());
		
		//Prueba la lista con dos elementos.
		lista.add(5);
		lista.add(30);
		
		assertFalse("Al agregar elementos la lista no debería estar vacía", lista.isEmpty());
		
		//Prueba la lista después de vaciarla.
		lista.clear();
		
		assertTrue("Al remover todos los elementos de la lista debería estar vacía", lista.isEmpty());
		
		//Prueba la lista con 20 elementos.
		setupEscenario2();
		
		assertFalse("Al agregar 20 elementos, la lista no debería estar vacía", lista.isEmpty());
	}
	
	/**
	 * Prueba que revisa el método contains
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testContains()
	{
		//Prueba con la lista vacía.
		assertFalse("Al inicio no debería encontrar ningún elemento", lista.contains(80));
		assertFalse("Al inicio no debería encontrar ningún elemento", lista.contains(new Double(80)));
		
		//Prueba con dos elementos
		lista.add(5);
		lista.add(30);
		
		assertTrue("Debería contener el elemento", lista.contains(5));
		assertTrue("Debería contener el elemento", lista.contains(30));
		
		//Vacía la lista y prueba.
		lista.clear();
		
		assertFalse("No debería encontrar ningún elemento", lista.contains(30));
		assertFalse("No debería encontrar ningún elemento", lista.contains(new Double(5)));
		
		// Prueba con 20 elementos.
		setupEscenario2();
		
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			assertTrue("El elemento "+ actual +" debería encontrarse en la lista", lista.contains(actual));
		}
		
	}
	
	/**
	 * Prueba que verifica el iterador que recibe.
	 */
	@Test
	public void testIterator()
	{
		//Prueba el iterador de la lista vacía.
		Iterator<Integer> iterador = lista.iterator();
		assertNotNull("Con la lista vacía retorna un iterador vacío", iterador);
		assertFalse("Con la lista vacía dice que puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			iterador.next();
			fail("Si no tiene elementos, no debería avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanzó porque no hay elementos.
		}
		
		//Prueba el iterador con dos elementos.
		lista.add(5);
		lista.add(30);
		
		iterador = lista.iterator();
		assertNotNull("No debería retornar un iterador vacío", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			Integer siguiente = iterador.next();
			assertTrue("Estando en el primer elemento de la lista, dice que no puede avanzar al segundo", iterador.hasNext());
			assertNotNull(siguiente);
			siguiente = iterador.next();
		}
		catch(NoSuchElementException e)
		{
			fail("No avanzó el iterador, dice que no hay más elementos.");
		}
		
		assertFalse("Estando en el último elemento de la lista dice que no puede avanzar.", iterador.hasNext());
		
		try
		{
			iterador.next();
			fail("Si está en el último elemento, no debería avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanzó proque no hay más elementos.
		}
		
		//Prueba el iterador con 20 elementos
		
		lista.clear();
		
		setupEscenario2();
		
		iterador = lista.iterator();
		assertNotNull("No debería retornar un iterador vacío", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertNotNull(actual);
				indiceActual++;
			}
			catch(NoSuchElementException e)
			{
				fail("Indica que puede avanzar, pero lanza excepción al avanzar.");
			}
		}
		
		assertEquals("El iterador no recorrió toda la lista", indiceActual, lista.size());
		
	}
	
	/**
	 * Método que prueba el primer toarray
	 */
	@Test
	public void testToArray1()
	{
		//Prueba con la lista vacía.
		Object[] arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacía no está vacío", 0, arreglo.length);
		
		//Prueba la lista con dos elementos.
		lista.add(5);
		lista.add(30);
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacía no está vacío", 2, arreglo.length);
		
		//Prueba la lista con 20 elementos.
		lista.clear();
		setupEscenario2();
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El tamaño del arreglo no es el esperado", ARREGLO_ESCENARIO_2.length, arreglo.length);	
	}
	
	/**
	 * Prueba el segundo método toArray (que tiene un parámetro).
	 */
	@Test
	public void testToArray2()
	{
		//Prueba con la lista vacía.
		Integer[] arreglo = new Integer[lista.size()];
		arreglo = lista.toArray(arreglo);
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacía no está vacío", 0, arreglo.length);
		
		//Prueba la lista con dos elementos.
		lista.add(5);
		lista.add(30);
		arreglo = new Integer[lista.size()];
		arreglo = lista.toArray(arreglo);
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacía no está vacío", 2, arreglo.length);
		
		//Prueba la lista con 20 elementos.
		lista.clear();
		setupEscenario2();
		arreglo = new Integer[lista.size()];
		arreglo = lista.toArray(arreglo);
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El tamaño del arreglo no es el esperado", ARREGLO_ESCENARIO_2.length, arreglo.length);
	}
	
	/**
	 * Prueba que revisa que se agreguen los elementos sin tener en cuenta repetidos, ni el orden.
	 */
	@Test
	public void testAdd()
	{
		//Prueba la lista vacía.
		assertTrue("Al principio la lista está vacía", lista.isEmpty());
		assertEquals("El tamaño de la lista al principio no es 0", 0, lista.size());

		//Agrega dos elementos.
		assertTrue("No agrega el elemento", lista.add(5));
		assertTrue("No agrega el elemento", lista.add(30));
		assertFalse("La lista no debería estar vacía", lista.isEmpty());
		assertEquals("La lista debería tener 2 elementos", 2, lista.size());

		assertTrue("La lista no contiene 5", lista.contains(5));
		assertTrue("La lista no contiene 30", lista.contains(30));
		
		//Agrega 20 elementos.
		lista.clear();
		
		setupEscenario2();
		assertFalse("La lista no debería estar vacía", lista.isEmpty());
		assertEquals("La lista debería tener 20 elementos", ARREGLO_ESCENARIO_2.length, lista.size());
	}
	
	/**
	 * Prueba el método remove que recibe como parámetro un objeto.
	 */
	@Test
	public void testRemoveObject()
	{
		//Prueba con una lista vacía.
		assertFalse("No debería eliminar elementos porque la lista está vacía", lista.remove(new Integer(40)));
		
		//Prueba con una lista de 3 elementos.
		lista.add(3);
		lista.add(40);
		lista.add(30);
		assertEquals("El tamaño del arreglo no es el esperado", 3, lista.size());
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(3)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(3)));
		assertEquals("El tamaño de la lista no es el esperado", 2, lista.size());
		
		assertFalse("No debería poder eliminar un elemento que no está en la lista", lista.remove(new Integer(323)));
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(30)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(30)));
		assertEquals("El tamaño de la lista no es el esperado", 1, lista.size());
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(40)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(3)));
		assertEquals("El tamaño de la lista no es el esperado", 0, lista.size());
		
		//Prueba con una lista de 20 elementos.
		setupEscenario2();

		
		assertFalse("No debería poder eliminar un elemento que no está en la lista", lista.remove(new Integer(40)));
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(350)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(350)));
		assertEquals("El tamaño de la lista no es el esperado", 19, lista.size());
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(396)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(396)));
		assertEquals("El tamaño de la lista no es el esperado", 18, lista.size());
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(18)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(18)));
		assertEquals("El tamaño de la lista no es el esperado", 17, lista.size());
		
		assertTrue("Debería poder eliminar el elemento de la lista", lista.remove(new Integer(446)));
		assertFalse("No se eliminó el elemento", lista.contains(new Integer(446)));
		assertEquals("El tamaño de la lista no es el esperado", 16, lista.size());
		
		assertFalse("La lista no debería tener elementos repetidos", hayRepetidos());
	}
	
	/**
	 * Prueba que revisa el método contains all.
	 */
	@Test
	public void testContainsAll()
	{
		//Prueba la lista vacía.
		ArrayList<Integer> arreglo = new ArrayList<>();
		assertTrue("La lista vacía no contiene ningún elemento, por lo tanto debería ser correcto", lista.containsAll(arreglo));
		
		arreglo.add(5);
		arreglo.add(8);
		
		assertFalse("La lista vacía no contiene el 5 y el 8", lista.containsAll(arreglo));
		
		//Prueba con un elemento.
		lista.add(8);
		
		assertFalse("La lista con el 8 no contiene el 5 y el 8", lista.containsAll(arreglo));
		
		lista.add(5);
		
		assertTrue("La lista contiene los mismos elementos del arreglo", lista.containsAll(arreglo));
		
		arreglo.add(49);
		assertFalse("El arreglo contiene un elemento que no está en la lista", lista.containsAll(arreglo));
		
		//Prueba con 20 elementos.
		lista.clear();
		setupEscenario2();
		
		assertFalse("El arreglo contiene elementos que no se encuentran en la lista", lista.containsAll(arreglo));
		
		arreglo.clear();
		
		assertTrue("La lista contiene todos los elementos de la lista, porque no hay ninguno", lista.containsAll(arreglo));
		
		arreglo.add(lista.get(0));
		arreglo.add(lista.get(lista.size() - 1));
		arreglo.add(lista.get(3));
		arreglo.add(lista.get(18));
		arreglo.add(lista.get(10));
		
		assertTrue("La lista dice no contiene los elementos que fueron sacados de la propia lista", lista.containsAll(arreglo));
		
		arreglo.add(8);
		
		assertFalse("El arreglo contiene un elemento que no está en la lista", lista.containsAll(arreglo));
	}
	
	/**
	 * Prueba el método addAll
	 */
	@Test
	public void testAddAll()
	{
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);
		
		//Prueba con la lista vacía
		lista.addAll(elementos);
		assertFalse("La lista ya no está vacía", lista.isEmpty());
		assertEquals("No se agregaron todos los elementos", elementos.size(), lista.size());
		
		//Se prueba la lista con 20 elementos.
		lista.clear();
		setupEscenario2();
		
		lista.addAll(elementos);
		assertEquals("No se agregaron todos los elementos", 20 + elementos.size(), lista.size() );
		
		assertFalse("La lista no debería tener elementos repetidos", hayRepetidos());
	}
	
	/**
	 * Prueba el método removeAll
	 */
	@Test
	public void testRemoveAll()
	{
		//Prueba con la lista vacía
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);
		
		assertFalse("No debería eliminar ningún elemento porque la lista está vacía", lista.removeAll(elementos));
		
		//Prueba con 20 elementos.
		setupEscenario2();
		assertFalse("No debería eliminar ningún elemento porque la lista no contiene ningún elemento", lista.removeAll(elementos));
		assertEquals("No debería cambiar la cantidad de elementos", 20, lista.size());
		
		elementos.add(233);
		
		assertTrue("Debió eliminar el 233", lista.removeAll(elementos));
		assertFalse("Debió eliminar el elemento", lista.contains(233));
		assertEquals("Debería cambiar la cantidad de elementos", 19, lista.size());
		
		elementos.add(lista.get(0));
		elementos.add(lista.get(lista.size() - 1));
		elementos.add(lista.get(3));
		elementos.add(lista.get(18));
		elementos.add(lista.get(10));
		
		assertTrue("Debió eliminar los elementos", lista.removeAll(elementos));
		assertEquals("Debería cambiar la cantidad de elementos", 15, lista.size());
		
		for(Integer actual : elementos)
		{
			assertFalse("La lista no debería contener ninguno de los elementos eliminados", lista.contains(actual));
		}		
		
		assertFalse("La lista no debería tener elementos repetidos", hayRepetidos());
	}
	
	/**
	 * Prueba el método retainAll
	 */
	@Test
	public void testRetainAll()
	{
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		elementos.add(50);
		elementos.add(400);
		elementos.add(145);
		elementos.add(259);
		
		//Prueba con la lista vacía.
		assertFalse("Si no hay elementos, no debería realizar ningún cambio", lista.retainAll(elementos));
		
		//Prueba con 20 elementos
		
		//Si no hay ningún elemento en elementos que esté en la lista.
		setupEscenario2();
		assertTrue("Debía eliminar todos los elementos", lista.retainAll(elementos));
		assertEquals("No debería quedar ningún elemento en la lista", 0, lista.size());
		
		//Si hay un elemento en elementos que esté en la lista
		setupEscenario2();
		elementos.add(233);
		assertTrue("Debía eliminar todos los elementos excepto uno", lista.retainAll(elementos));
		assertEquals("Debería quedar sólo un elemento en la lista", 1, lista.size());
		
		//Si todos los elementos de la lista están en elementos.
		lista.clear();
		setupEscenario2();
		elementos.addAll(lista);
		assertFalse("No debía eliminar ningún elemento, porque todos están en elementos", lista.retainAll(elementos));
		assertEquals("La lista debería quedar intacta", 20, lista.size());
		
		// Si sólo algunos elementos de la lista están en elementos.
		elementos.clear();
		elementos.add(lista.get(16));
		elementos.add(lista.get(3));
		elementos.add(lista.get(18));
		elementos.add(lista.get(10));
		elementos.add(lista.get(16));
		elementos.add(lista.get(8));
		
		assertTrue("Debería eliminar elementos", lista.retainAll(elementos));
		assertEquals("La lista debería tener 5 elementos", 5, lista.size());
		
		assertFalse("La lista no debería tener elementos repetidos", hayRepetidos());
	}
	
	/**
	 * Prueba que revisa el método clear.
	 */
	@Test
	public void testClear()
	{
		//Prueba la lista vacía.
		lista.clear();
		assertEquals("La lista sigue vacía", 0, lista.size());
		
		//Prueba con 20 elementos
		setupEscenario2();
		assertEquals("La lista no tiene 20 elementos, hay que revisar el método add", 20, lista.size());
		lista.clear();
		assertEquals("La lista no quedó vacía", 0, lista.size());
		try
		{
			lista.get(0);
			fail("No debería poder recuperar el primer elemento de la lista");
		}
		catch (IndexOutOfBoundsException e) 
		{
			//Debería lanzar la excepción.
		}
	}
	
	/**
	 * Prueba que revisa el método get.
	 */
	@Test
	public void testGet()
	{
		//Revisa la lista vacía.
		try
		{
			lista.get(0);
			fail("Debería lanzar excepción porque la lista está vacía.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzar la excepción
		}
		
		try
		{
			lista.get(-1);
			fail("Debería lanzar excepción porque el índice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzar la excepción
		}
		
		try
		{
			lista.get(-50);
			fail("Debería lanzar excepción porque el índice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzar la excepción
		}
		
		//Revisa la lista con 20 elementos.
		setupEscenario2();
		
		try
		{
			lista.get(-1);
			fail("Debería lanzar excepción porque el índice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzar la excepción
		}
		
		try
		{
			lista.get(-50);
			fail("Debería lanzar excepción porque el índice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzarla
		}
		
		try
		{
			lista.get(500);
			fail("Debería lanzar excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzarla
		}
		
		try
		{
			lista.get(lista.size());
			fail("Debería lanzar excepción porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Debería lanzarla
		}
		
		for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i++)
		{
			try
			{
				Integer elemento = lista.get(i);
				assertNotNull("Los elementos recuperados no pueden ser nulos", elemento);
			}
			catch(IndexOutOfBoundsException e)
			{
				fail("El elemento se encuentra dentro del rango, no debería lanzar excepción");
			}
		}
	}
	
	/**
	 * Prueba del método remove
	 */
	@Test
	public void testRemoveIndex()
	{
		//Prueba con la lista vacía.
		try
		{
			lista.remove(0);
			fail("No debería dejar eliminar elementos porque la lista está vacía");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(-1);
			fail("No debería eliminar porque el índice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		try
		{
			lista.remove(-1);
			fail("No debería dejar eliminar porque el índice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(lista.size());
			fail("No debería dejar eliminar porque el índice está por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		
		try
		{
			Integer paraEliminar = lista.get(0);
			assertEquals("No eliminó el elemento esperado", paraEliminar, lista.remove(0));
			assertEquals("La lista no tiene el tamaño esperado", 19, lista.size());
			assertFalse("No eliminó el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(lista.size() - 1);
			assertEquals("No eliminó el elemento esperado", paraEliminar, lista.remove(lista.size() - 1));
			assertEquals("La lista no tiene el tamaño esperado", 18, lista.size());
			assertFalse("No eliminó el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(3);
			assertEquals("No eliminó el elemento esperado", paraEliminar, lista.remove(3));
			assertEquals("La lista no tiene el tamaño esperado", 17, lista.size());
			assertFalse("No eliminó el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(15);
			assertEquals("No eliminó el elemento esperado", paraEliminar, lista.remove(15));
			assertEquals("La lista no tiene el tamaño esperado", 16, lista.size());
			assertFalse("No eliminó el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(10);
			assertEquals("No eliminó el elemento esperado", paraEliminar, lista.remove(10));
			assertEquals("La lista no tiene el tamaño esperado", 15, lista.size());
			assertFalse("No eliminó el elemento", lista.contains(paraEliminar));
			
			assertFalse("La lista no debería tener elementos repetidos", hayRepetidos());
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("Debería dejar realizar la operación porque el índice se encuentra en el rango");
		}
	}
	
	/**
	 * Prueba del método indexOf.
	 */
	@Test
	public void testIndexOf()
	{
		//Prueba con la lista vacía
		assertEquals("Como no hay elementos en la lista, el resultado debería ser -1", -1, lista.indexOf(50));
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(10));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(248));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(448));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(366));
	}
	
	/**
	 * Prueba del método lastIndexOf
	 */
	@Test
	public void testLastIndexOf()
	{
		//Prueba con la lista vacía
		assertEquals("Como no hay elementos en la lista, el resultado debería ser -1", -1, lista.lastIndexOf(50));
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(10));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(248));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(448));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(366));
	}
	
	/**
	 * Prueba del método subList
	 */
	@Test
	public void testSubList()
	{
		//Prueba con la lista vacía.
		try
		{
			lista.subList(0, 1);
			fail("La lista está vacía, no debería dejar crear sublistas.");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		try
		{
			lista.subList(15, 21);
			fail("El índice se encuentra fuera de la lista, debería fallar.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Correcto
		}
		
		try
		{
			lista.subList(-1, 15);
			fail("El índice se encuentra fuera de la lista, debería fallar.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Correcto
		}
		
		try
		{
			lista.subList(0, 20);
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("El índice se encuentra dentro de la lista, no debería fallar.");
			
		}
	}

	/**
	 * Revisa si hay números repetidos en la lista.
	 * @return True si está repetido, false en caso contrario.
	 */
	protected boolean hayRepetidos()
	{
		for(int i = 0; i < lista.size(); i++)
		{
			for(int j = i + 1; j < lista.size(); j++)
			{
				if(lista.get(i).intValue() == lista.get(j).intValue())
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un número está en el arreglo del escenario.
	 * @param numero Número que se quiere buscar
	 * @return True si se encuentra, false de lo contrario.
	 */
	protected boolean estaEnArreglo(int numero)
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			if(actual == numero)
				return true;
		}
		return false;
	}
}