/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_karaoke
 * Autor: Equipo Cupi2  2018-2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.apo2h.karaoke.mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.DASTORE;

import uniandes.cupi2.apo2h.estructuras.ListaSencillamenteEncadenada;
import uniandes.cupi2.apo2h.estructuras.ListaSencillamenteEncadenadaOrdenada;
import uniandes.cupi2.apo2h.utils.Buscador;

/**
 * Representa un Karaoke
 */
@SuppressWarnings("serial")
public class Karaoke implements Serializable
{
	/**
	 * Constante de serializaci�n.
	 */
	private static final long serialVersionUID = 58757259279521L;
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Arreglo de constantes necesarios para el manejo de las categor�as del karaoke.
	 */
	public enum Categoria 
	{
		ROCK("Rock"), 
		POP("Pop"), 
		REGGAE("Reggae"), 
		TROPICAL("Tropical"), 
		ELECTRONICA("Electronica");

		private String nombre;

		Categoria(String nombre)
		{
			this.nombre = nombre;
		}

		@Override
		public String toString() {
			return nombre;
		}
	}

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Lista de artistas.
	 */
	//TODO Declare la lista de artistas como artistas
	private List<Artista> artistas;
	/**
	 * Buscador de canciones
	 */
	private Buscador<Cancion> buscadorCanciones;

	/**
	 * Buscador de artistas
	 */
	private Buscador<Artista> buscadorArtistas;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del karaoke. <br>
	 */
	public Karaoke( )
	{
		//TODO Inicializar la lista de canciones
		artistas = new ListaSencillamenteEncadenada<Artista>();
		buscadorCanciones = new Buscador<>();
		buscadorArtistas = new Buscador<>();
		//artistas = new ArrayList <Artista>();

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Retorna los artistas.
	 * @return Retorna la lista de los artistas
	 */
	public List<Artista> darArtistas ()
	{
		return artistas;
	}
	/**
	 * Agrega un artista a la categor�a del karaoke.<br>
	 * El artista se agrega en la posici�n que le corresponda de tal manera que la lista quede ordenada por nombre de manera ascendente.<br>
	 * <b> post: </b> Se ha agregado un nuevo artista del karaoke
	 * @param artista Artista a agregar al karaoke
	 * @throws Exception Si ya existe un artista con el mismo nombre.
	 */
	public void agregarArtista( Artista artista ) throws Exception
	{
		//TODO Completar de acuerdo a la documentaci�n. No es necesario que tenga en cuenta la restricci�n del orden.
		artistas.add(artista);
	}

	/**
	 * Busca un artista con el nombre.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @return El artista con el nombre dado. Si no existe un artista con ese nombre se retorna null
	 */
	public Artista buscarArtista( String pNombre )
	{
		//TODO Completar de acuerdo a la documentaci�n 

		return buscadorArtistas.buscarNoOrdenada(artistas, CriteriosComparacionArtistas.NOMBRE.comparador, new Artista(null, pNombre, null));
	}

	/**
	 * Elimina al artista con nombre dado del karaoke.<br>
	 * <b>pre:</b>El artista existe.<br>
	 * <b>post</b>Se elimin� el artista del karaoke.
	 * @param artista El artista que se quiere eliminar.
	 */
	public void eliminarArtista( Artista artista )
	{
		//TODO Completar de acuerdo a la documentaci�n
		artistas.remove(artista);
	}

	/**
	 * Retorna una lista con los artistas que pertenecen a una categor�a.
	 * @param pCategoria Categor�a de la cual se quieren los artistas. pCategoria pertenece a Karaoke.CATEGORIAS.
	 * @return Lista con los artistas de la categor�a dada.
	 */
	public List<Artista> darArtistasCategoria( Categoria pCategoria )
	{
		//TODO Completar de acuerdo a la documentaci�n
		List<Artista> res = new ArrayList <> ();
		for (Artista j: artistas)
		{
			if (j.darCategoria() == pCategoria)
			{
				res.add(j);
			}
				
		}
		return res;
	}

	/**
	 * Busca la canci�n con mayor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasDificil( )
	{
		//TODO Completar de acuerdo a la documentaci�n. Usar el buscador de canciones para realizar este m�todo.
		Cancion res = null;
		for (Artista j: artistas)
		{
			Cancion esta = buscadorCanciones.buscarMayor(j.darCanciones(), CriteriosComparacionCanciones.DIFICULTAD.comparador);
			if (res == null ||(esta != null && CriteriosComparacionCanciones.DIFICULTAD.comparador.compare(esta, res) == 1 ) )
			{
				res= esta;
			}
		}
		return res;
	}

	/**
	 * Busca la canci�n con menor dificultad. Si existen varias canciones con la misma dificultad retorna la primera canci�n encontrada.
	 * @return La canci�n con menor dificultad. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasFacil( )
	{
		//TODO Completar de acuerdo a la documentaci�n. Usar el buscador de canciones para realizar este m�todo.
		Cancion res = null;
		for (Artista j: artistas)
		{
			Cancion esta = buscadorCanciones.buscarMenor(j.darCanciones(), CriteriosComparacionCanciones.DIFICULTAD.comparador);
			
			if (res == null || (esta != null && CriteriosComparacionCanciones.DIFICULTAD.comparador.compare(esta, res) == -1 ) )
			{
				
				res= esta;
			}
		}
		return res;
	}

	/**
	 * Busca la canci�n con mayor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con mayor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasLarga( )
	{
		//TODO Completar de acuerdo a la documentaci�n. Usar el buscador de canciones para realizar este m�todo.
		Cancion res = null;
		for (Artista j: artistas)
		{
			Cancion esta = buscadorCanciones.buscarMayor(j.darCanciones(), CriteriosComparacionCanciones.DURACION.comparador);
			if (res == null || (esta != null && CriteriosComparacionCanciones.DURACION.comparador.compare(esta, res) == 1 ) )
				res= esta;
		}
		return res;
	}

	/**
	 * Busca la canci�n con menor duraci�n. Si existen varias canciones con la misma duraci�n retorna la primera canci�n encontrada.
	 * @return La canci�n con menor duraci�n. Si ning�n artista tiene canciones se retorna null
	 */
	public Cancion darCancionMasCorta( )
	{
		//TODO Completar de acuerdo a la documentaci�n. Usar el buscador de canciones para realizar este m�todo.
		Cancion res = null;
		for (Artista j: artistas)
		{
			Cancion esta = buscadorCanciones.buscarMenor(j.darCanciones(), CriteriosComparacionCanciones.DURACION.comparador);
			if (res == null || (esta != null && CriteriosComparacionCanciones.DURACION.comparador.compare(esta, res) == -1 ) )
				res= esta;
		}
		return res;
	}

	/**
	 * Busca el artista con mayor n�mero de canciones. Si existen varios artistas con el mismo n�mero de canciones retorna el primer artista encontrado.
	 * @return El artista de alguna categor�a con mayor n�mero de canciones. Si no hay artistas se retorna null
	 */
	public Artista darArtistaMasCanciones( )
	{
		//TODO Completar de acuerdo a la documentaci�n. Usar el buscador de artistas para realizar este m�todo.

		return buscadorArtistas.buscarMayor(artistas, CriteriosComparacionArtistas.CANTIDAD_CANCIONES.comparador);
	}

	/**
	 * Retorna una lista con todas las canciones de la categor�a con el nombre dado.<br>
	 * <b> pre: </b> La categor�a existe.
	 * @param categoria Categoria de las canciones buscadas
	 * @return Lista con todas las canciones de una categor�a
	 */
	public List<Cancion> darCancionesCategoria( Categoria categoria )
	{
		// TODO Completar de acuerdo a la documentaci�n
		List<Cancion> res = new ArrayList <> ();
		List <Artista> art = darArtistasCategoria(categoria);
		for (Artista k: art)
		{
			for (Cancion j: k.darCanciones())
			{
				res.add(j);
			}
		}

		return res;

	}

	/**
	 * M�todo que retorna una lista con todas las canciones del karaoke (de todos los artistas).
	 * @return Lista completa de canciones del karaoke.
	 */
	public List<Cancion> darCancionesKaraoke()
	{
		//TODO Completar de acuerdo a la documentaci�n
		List<Cancion> res = new ArrayList <> ();
		for (Artista k: artistas)
		{
			for (Cancion j: k.darCanciones())
			{
				res.add(j);
			}
		}

		return res;


	}




	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1
	 * @return respuesta1
	 */
	public String metodo1( )
	{
		((ListaSencillamenteEncadenada<Artista>) artistas).invertir();
		return "Respuesta 1";
	}

	/**
	 * M�todo para la extensi�n2
	 * @return respuesta2
	 */
	public String metodo2( )
	{
		((ListaSencillamenteEncadenada<Artista>) artistas).ordenarPorBurbuja(CriteriosComparacionArtistas.CANTIDAD_CANCIONES.comparador);
		
		return "Respuesta 2";
	}

}