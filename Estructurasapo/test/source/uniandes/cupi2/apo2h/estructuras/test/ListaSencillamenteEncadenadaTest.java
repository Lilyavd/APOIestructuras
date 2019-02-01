package uniandes.cupi2.apo2h.estructuras.test;

import uniandes.cupi2.apo2h.estructuras.ListaSencillamenteEncadenada;

public class ListaSencillamenteEncadenadaTest extends ListaAbstractaNoOrdenadaTest 
{

	@Override
	public void setupEscenario1() 
	{
		lista = new ListaSencillamenteEncadenada<>();		
	}

	@Override
	public void testListIterator() {
		//No se probará en esta prueba.
	}
}
