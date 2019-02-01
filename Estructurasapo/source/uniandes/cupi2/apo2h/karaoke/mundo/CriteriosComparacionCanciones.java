package uniandes.cupi2.apo2h.karaoke.mundo;

import java.io.Serializable;

import uniandes.cupi2.apo2h.utils.SerializableComparator;

/**
 * Enum que lista los criterios de comparación de las canciones.
 * @author Christian
 *
 */
public enum CriteriosComparacionCanciones implements Serializable
{
	DIFICULTAD("Dificultad", new SerializableComparator<Cancion>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3885074921914315043L;

		/**
		 * Compara las dos canciones por su dificultad
		 */
		//TODO Cree y complete el método compare, de acuerdo a la documentación.
		@Override
		public int compare(Cancion c1, Cancion c2) {
			
			int comparacion = c1.darDificultad()-c2.darDificultad();
			if(comparacion > 0)
				return 1;
			else if(comparacion < 0)
				return -1;
			else
				return 0;
		}
		
	}),
	DURACION("Duración", new SerializableComparator<Cancion>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 682711222726552768L;

		/**
		 * Compara las canciones por su duracion
		 */
		//TODO Complete el método compare, de acuerdo a la documentación
		@Override
		public int compare(Cancion c1, Cancion c2) {
			
			int comparacion = c1.darDuracion()-c2.darDuracion();
			if(comparacion > 0)
				return 1;
			else if(comparacion < 0)
				return -1;
			else
				return 0;
		}
		
	}),
	NOMBRE("Nombre", new SerializableComparator<Cancion>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8418137039540888272L;

		/**
		 * Compara las canciones por su nombre.
		 */
		//TODO Complete el método compare, de acuerdo a la documentación
		@Override
		public int compare(Cancion c1, Cancion c2) {
			int comparacion = c1.darNombre().compareToIgnoreCase(c2.darNombre());
			if(comparacion > 0)
				return 1;
			else if(comparacion < 0)
				return -1;
			else
				return 0;
		}
		
	});
	
	/**
	 * Nombre para mostrarle al usuario del nombre del criterio de comparación.
	 */
	public String nombre;
	
	/**
	 * Criterio de comparación del elemento de la enumeración.
	 */
	public SerializableComparator<Cancion> comparador;
	
	/**
	 * Constructor del enum. Asigna el nombre y el comparador.
	 * @param nombre Nombre para mostrarle al usuario.
	 * @param comparador Comparador del elemento del enum.
	 */
	private CriteriosComparacionCanciones(String nombre, SerializableComparator<Cancion> comparador) 
	{
		this.nombre = nombre;
		this.comparador = comparador;
	}
	
	@Override
	public String toString() 
	{
		return nombre;
	}
}