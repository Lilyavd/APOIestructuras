package uniandes.cupi2.apo2h.estructuras.test;

import uniandes.cupi2.apo2h.estructuras.ListaSencillamenteEncadenadaOrdenada;

public class ListaSencillamenteEncadenadaOrdenadaTest extends ListaAbstractaOrdenadaTest 
{

	@Override
	public void setupEscenario1() {
		super.setupEscenario1();
		lista = new ListaSencillamenteEncadenadaOrdenada<>(comparador, true);
	}
}
