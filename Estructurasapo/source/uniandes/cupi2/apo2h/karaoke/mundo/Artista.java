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

import uniandes.cupi2.apo2h.estructuras.ListaDoblementeEncadenada;
import uniandes.cupi2.apo2h.karaoke.mundo.Karaoke.Categoria;
import uniandes.cupi2.apo2h.utils.Buscador;

/**
 * Artista int�rprete de las canciones del karaoke
 */
@SuppressWarnings("serial")
public class Artista implements Serializable
{
	/**
	 * Constante de serializaci�n.
	 */
	private static final long serialVersionUID = 4548452151515L;
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Nombre del artista
	 */
	private String nombre;

	/**
	 * Categor�a del artista.
	 */
	private Categoria categoria;

	/**
	 * Ruta del archivo con la imagen del artista
	 */
	private String imagen;

	/**
	 * Lista de canciones del artista.
	 */
	//TODO Declare la lista de canciones como canciones
	private List <Cancion> canciones;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea un Artista.<br>
	 * <b>post:</b> Inicializa sus atributos con los par�metro recibidos.
	 * @param pNombre Nombre del artista. pNombre != null y pNombre != ""
	 * @param pCategoria Categor�a del artista. pCategoria != null && pCategoria pertenece a Karaoke.CATEGORIAS
	 * @param pImagen Ruta del archivo con la imagen del artista. pImagen != null y pImagen != ""
	 */
	public Artista( Categoria pCategoria, String pNombre, String pImagen )
	{
		nombre = pNombre;
		imagen = pImagen;
		categoria = pCategoria;
		//TODO Inicializar la lista de canciones.
		//canciones = new ArrayList <Cancion> ();
		canciones = new ListaDoblementeEncadenada<Cancion>();

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre del artista
	 * @return Nombre del artista
	 */
	public String darNombre( )
	{
		return nombre;
	}

	/**
	 * Retorna la categor�a del artista
	 * @return Categor�a del artista
	 */
	public Categoria darCategoria( )
	{
		return categoria;
	}

	/**
	 * Retorna la ruta del archivo con la imagen del artista
	 * @return Ruta de la imagen
	 */
	public String darImagen( )
	{
		return imagen;
	}

	/**
	 * Agrega una nueva canci�n a la lista de canciones del artista<br>
	 * <b> post: </b> Se agreg� una nueva canci�n a la lista de canciones.
	 * @param cancion Canci�n a agregar.
	 * @throws Exception Si ya existe una canci�n con el mismo nombre
	 */
	public void agregarCancion( Cancion cancion ) throws Exception
	{
		// TODO Completar el m�todo seg�n la documentaci�n.
		
		if ( !canciones.add(cancion))
			throw new Exception ("Ya existe una canci�n con el mismo nombre.");
	

	}

	/**
	 * Elimina la canci�n con el nombre dado.<br>
	 * <b>pre:</b> La canci�n existe.<br>
	 * <b>post:</b>Se elimin� la canci�n de la lista de canciones del artista.
	 * @param pNombre Nombre de la canci�n a eliminar. pNombre!=null && pNombre != "".
	 */
	public void eliminarCancion( Cancion cancion )
	{
		// TODO Completar el m�todo seg�n la documentaci�n.
		canciones.remove(cancion);
	}

	/**
	 * Retorna una lista con las canciones del artista
	 * @return Lista de canciones
	 */
	public List<Cancion> darCanciones( )
	{
		return canciones;
	}

	/**
	 * Compara los artistas por nombre.
	 */
	public boolean equals(Object obj) 
	{
		if(obj instanceof Artista)
		{
			Artista otro = (Artista) obj;
			return this.nombre.equalsIgnoreCase(otro.nombre);
		}
		return false;
	}

	/**
	 * Devuelve la representaci�n en String del artista
	 * @return La representaci�n en String del artista: nombre.
	 */
	public String toString( )
	{
		return nombre;
	}
}