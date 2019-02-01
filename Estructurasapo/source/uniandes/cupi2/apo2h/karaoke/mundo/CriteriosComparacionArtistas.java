package uniandes.cupi2.apo2h.karaoke.mundo;

import java.io.Serializable;

import uniandes.cupi2.apo2h.utils.SerializableComparator;
/**
 * Enumeraci�n con los criterios de comparaci�n de los artistas.
 */
public enum CriteriosComparacionArtistas implements Serializable
{
	NOMBRE("Nombre Artista", new SerializableComparator<Artista>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4355032789115854307L;

		/**
		 * Compara los artistas por su nombre.
		 */
		@Override
		public int compare(Artista a1, Artista a2) {
			int comparacion = a1.darNombre().compareToIgnoreCase(a2.darNombre());
			if(comparacion > 0)
				return 1;
			else if(comparacion < 0)
				return -1;
			else
				return 0;
		}
		
	}),
	CANTIDAD_CANCIONES("Cantidad canciones", new SerializableComparator<Artista>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -202465295306899934L;

		/**
		 * Compara los artistas por su cantidad de canciones.
		 */
		@Override
		public int compare(Artista a1, Artista a2) {
			int comparacion = a1.darCanciones().size() - a2.darCanciones().size();
			if(comparacion > 0)
				return 1;
			else if(comparacion < 0)
				return -1;
			else
				return 0;
		}
		
	});
	
	/**
	 * Nombre para presentarle al usuario del criterio.
	 */
	public String nombre;
	
	/**
	 * Comparador de artistas del elemento de la enumeraci�n.
	 */
	public SerializableComparator<Artista> comparador;
	
	/**
	 * Constructor de la enumeraci�n. Asigna el nombre para mostrarle al usuario y el comparador asociado.
	 */
	private CriteriosComparacionArtistas(String nombre, SerializableComparator<Artista> comparador) 
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
