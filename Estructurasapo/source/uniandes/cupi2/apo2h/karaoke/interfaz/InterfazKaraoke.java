/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_karaoke
 * Autor: Equipo Cupi2  2018-2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.apo2h.karaoke.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;


import uniandes.cupi2.apo2h.excepciones.PersistenciaException;
import uniandes.cupi2.apo2h.karaoke.mundo.Artista;
import uniandes.cupi2.apo2h.karaoke.mundo.Cancion;
import uniandes.cupi2.apo2h.karaoke.mundo.CriteriosComparacionCanciones;
import uniandes.cupi2.apo2h.karaoke.mundo.Karaoke;
import uniandes.cupi2.apo2h.karaoke.mundo.Karaoke.Categoria;
import uniandes.cupi2.apo2h.utils.Ordenador;
import uniandes.cupi2.apo2h.utils.Ordenamientos;
import uniandes.cupi2.apo2h.utils.Serializador;

/**
 * Esta es la ventana principal de la aplicación.
 */
@SuppressWarnings("serial")
public class InterfazKaraoke extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo con la configuración inicial del karaoke
     */
    private final static String RUTA_ARCHIVO = "./data/karaoke.properties";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Karaoke karaoke;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------


    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la información de una canción
     */
    private PanelCancion panelCancion;

    /**
     * Panel con la información de las categorías
     */
    private PanelCategorias panelCategorias;

    /**
     * Panel con las información de artistas
     */
    private PanelArtistas panelArtistas;

   
    /**
     * Barra de opciones de la aplicación.
     */
    private BarraOpciones barraOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post: </b> Los paneles son inicializados
     */
    public InterfazKaraoke( )
    {
        // Crea la clase principal
        karaoke = new Karaoke( );
        cargarKaraoke( );
        barraOpciones = new BarraOpciones(this);

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 600, 650 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( " CupiKaraoke " );
        setJMenuBar(barraOpciones);
        

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );


        panelCancion = new PanelCancion( this );
        add( panelCancion, BorderLayout.CENTER );

        JPanel aux2 = new JPanel( );
        aux2.setLayout( new BorderLayout( ) );

        panelCategorias = new PanelCategorias( this );
        aux2.add( panelCategorias, BorderLayout.NORTH );

        panelArtistas = new PanelArtistas( this );
        aux2.add( panelArtistas, BorderLayout.CENTER );

        add( aux2, BorderLayout.WEST );

        // Centrar la ventana
        setLocationRelativeTo( null );

        Categoria c = panelCategorias.darCategoriaSeleccionada( );
        actualizarArtistas( c );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Carga la información inicial del karaoke
     */
    private void cargarKaraoke( )
    {
        try
        {
            Properties datos = new Properties( );
            FileInputStream in = new FileInputStream( RUTA_ARCHIVO );
            datos.load( in );
            in.close( );

            int numArtistas = Integer.parseInt( datos.getProperty( "total.artistas" ) );
            for( int i = 1; i <= numArtistas; i++ )
            {
                String nombre = datos.getProperty( "artista" + i + ".nombre" );
                String imagen = datos.getProperty( "artista" + i + ".imagen" );
                String categoriaInput = datos.getProperty( "artista" + i + ".categoria" );
                Categoria categoria = Categoria.valueOf(categoriaInput.toUpperCase());
                
                Artista artista = new Artista(categoria, nombre, imagen);
                
                karaoke.agregarArtista( artista );

                int numCanciones = Integer.parseInt( datos.getProperty( "artista" + i + ".total.canciones" ) );
                for( int j = 1; j <= numCanciones; j++ )
                {
                    String cancion = datos.getProperty( "artista" + i + ".cancion" + j + ".nombre" );
                    int duracion = Integer.parseInt( datos.getProperty( "artista" + i + ".cancion" + j + ".duracion" ) );
                    String letra = datos.getProperty( "artista" + i + ".cancion" + j + ".letra" );
                    int dificultad = Integer.parseInt( datos.getProperty( "artista" + i + ".cancion" + j + ".dificultad" ) );
                    String ruta = datos.getProperty( "artista" + i + ".cancion" + j + ".ruta" );
                    artista.agregarCancion(new Cancion(cancion, duracion, letra, dificultad, ruta) );
                }
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No fue posible cargar la información inicial del karaoke " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Agrega un nuevo artista a una categoría del karaoke
     */
    public void agregarArtista( Artista artista )
    {
        try
        {
            karaoke.agregarArtista( artista );
            Categoria c = panelCategorias.darCategoriaSeleccionada( );
            actualizarArtistas( c );
            JOptionPane.showMessageDialog( this, "Artista agregado exitosamente", "Agregar Artista", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No fue posible agregar el artista: " + artista.darNombre(), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Agrega una nueva canción a un artista del karaoke
     */
    public void agregarCancion( Artista artista, Cancion cancion )
    {
        try
        {
            artista.agregarCancion(cancion);
            Categoria c = panelCategorias.darCategoriaSeleccionada( );
            actualizarArtistas( c );
            JOptionPane.showMessageDialog( this, "Canción agregada exitosamente", "Agregar Canción", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            e.printStackTrace( );
            JOptionPane.showMessageDialog( this, "No fue posible agregar la canción: " + cancion.darNombre(), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Actualiza la interfaz con la lista de artistas de una categoría
     * @param pCategoria Categoría del karaoke. pCategoria pertenece a Karaoke.CATEGORIAS
     */
    public void actualizarArtistas( Categoria pCategoria )
    {
        panelArtistas.actualizarArtistas( karaoke.darArtistasCategoria( pCategoria ) );
       
       
    }

    /**
     * Actualiza la interfaz con la información de una canción
     * @param pCancion Canción que se debe mostrar. pCancion != null
     */
    public void actualizarCancion( Cancion pCancion )
    {
        panelCancion.actualizar( pCancion );
    }

    /**
     * Elimina el artista actualmente seleccionado.
     */
    public void eliminarArtistaSeleccionado( )
    {
        Artista artista = panelArtistas.darArtistaSeleccionado( );
        if( artista != null )
        {

            int opc = JOptionPane.showConfirmDialog( this, "¿Deseas eliminar al artista '" + artista.darNombre( ) + "' del karaoke?", "Eliminar artista", JOptionPane.YES_NO_OPTION );
            if( opc == JOptionPane.YES_OPTION )
            {
                karaoke.eliminarArtista( artista );
                actualizarArtistas( artista.darCategoria( ) );
                JOptionPane.showMessageDialog( this, "Se ha eliminado al artista correctamente", "Eliminar artista", JOptionPane.INFORMATION_MESSAGE );
            }
        }

    }
    
    /**
     * Método que carga el Karaoke desde un archivo serializado.
     */
    public void cargar()
    {
    	//TODO Completar de acuerdo a la documentación.
    	JFileChooser jfc = new JFileChooser("data" );
		jfc.setDialogTitle("Seleccionar cargar el archivo serializado ....");
		jfc.setMultiSelectionEnabled(false);
		int seleccion = jfc.showSaveDialog(this);
		if(seleccion == JFileChooser.APPROVE_OPTION)
		{
			
			String rutaArchivo = jfc.getSelectedFile().getAbsolutePath();
			try 
			{
				 karaoke = Serializador.cargar(rutaArchivo, karaoke);
				
				 Categoria c = panelCategorias.darCategoriaSeleccionada( );
		         actualizarArtistas( c );
				
			} 
			catch (PersistenciaException e) 
			{
				JOptionPane.showMessageDialog(this, "Ocurrió un error cargando el archivo\n" + e.getMessage() + "\nRuta:" + e.darRuta(), "Error", JOptionPane.ERROR_MESSAGE);
			} 

		}
    }
    
    /**
     * Método que guarda el Karaoke en un archivo.
     */
    public void guardar()
    {
    	//TODO Completar de acuerdo a la documentación.
    	JFileChooser jfc = new JFileChooser("data" );
		jfc.setDialogTitle("Seleccionar guardar el archivo serializado ....");
		jfc.setMultiSelectionEnabled(false);
		int seleccion = jfc.showSaveDialog(this);
		if(seleccion == JFileChooser.APPROVE_OPTION)
		{
			
			String rutaArchivo = jfc.getSelectedFile().getAbsolutePath();
			
			try 
			{
				Serializador.guardar(rutaArchivo, karaoke);
				
			} 
			catch (PersistenciaException e) 
			{
				JOptionPane.showMessageDialog(this, "Ocurrió un error guardando el archivo\n" + e.getMessage() + "\nRuta:" + e.darRuta(), "Error", JOptionPane.ERROR_MESSAGE);
			} 

		}
    }

    /**
     * Elimina la canción actualmente seleccionada.
     */
    public void eliminarCancionSeleccionada( )
    {
        Cancion cancion = panelArtistas.darCancionSeleccionada( );
        Artista artista = panelArtistas.darArtistaSeleccionado( );
        if( cancion != null )
        {

            int opc = JOptionPane.showConfirmDialog( this, "¿Deseas eliminar la canción '" + cancion.darNombre( ) + "' del karaoke?", "Eliminar canción", JOptionPane.YES_NO_OPTION );
            if( opc == JOptionPane.YES_OPTION )
            {
                artista.eliminarCancion(cancion);
                cancion = artista.darCanciones( ).isEmpty( ) ? null : artista.darCanciones( ).get( 0 );
                panelArtistas.actualizarArtista( );
                actualizarCancion( panelArtistas.darCancionSeleccionada( ) );
                
                JOptionPane.showMessageDialog( this, "Se ha eliminado la canción correctamente", "Eliminar canción", JOptionPane.INFORMATION_MESSAGE );
            }
        }

    }

    /**
     * Muestra la canción con mayor dificultad.
     */
    public void mostrarCancionMasDificil( )
    {
        Cancion cancion = karaoke.darCancionMasDificil( );
        if( cancion != null )
        {
            JOptionPane.showMessageDialog( this, "La canción mas difícil es " + cancion.darNombre( ), "Más Difícil", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No existen canciones en el karaoke", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la canción con menor dificultad.
     */
    public void mostrarCancionMasFacil( )
    {
        Cancion cancion = karaoke.darCancionMasFacil( );
        if( cancion != null )
        {
            JOptionPane.showMessageDialog( this, "La canción mas fácil es " + cancion.darNombre( ), "Más Fácil", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No existen canciones en el karaoke", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la canción con mayor duración.
     */
    public void mostrarCancionMasLarga( )
    {
        Cancion cancion = karaoke.darCancionMasLarga( );
        if( cancion != null )
        {
            JOptionPane.showMessageDialog( this, "La canción mas larga es " + cancion.darNombre( ), "Más Larga", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No existen canciones en el karaoke", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la canción con menor duración.
     */
    public void mostrarCancionMasCorta( )
    {
        Cancion cancion = karaoke.darCancionMasCorta( );
        if( cancion != null )
        {
            JOptionPane.showMessageDialog( this, "La canción mas corta es " + cancion.darNombre( ), "Más Corta", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No existen canciones en el karaoke", "Error", JOptionPane.ERROR_MESSAGE );
        }

    }
    
    /**
     * 
     */
    public void actualizarEliminarArtista(boolean estado)
    {
    	barraOpciones.actualizarEstadoEliminarArtista(estado);
    }
    
    public void actualizarEliminarCancion(boolean estado)
    {
    	barraOpciones.actualizarEstadoEliminarCancion(estado);
    }
    
    /**
     * Ordena las canciones del artista actual por el criterio de comparación que llega como parámetro.
     * @param criterio
     */
    public void ordenarCanciones(CriteriosComparacionCanciones criterio)
    {
    	Artista artista = panelArtistas.darArtistaSeleccionado( );
    	if(artista != null)
    	{
    		//TODO Ordena las canciones del artista seleccionado actualmente de forma ascendente y por el criterio que llega como parámetro.
    		Ordenador<Cancion> or = new Ordenador<>();
    		or.ordenar(Ordenamientos.BURBUJA_BIDERECCIONAL, true, criterio.comparador, artista.darCanciones());
    		panelArtistas.actualizarCanciones();
    	}
    }

    /**
     * Muestra el artista con mayor número de canciones.
     */
    public void mostrarArtistaMasCanciones( )
    {
        Artista artista = karaoke.darArtistaMasCanciones( );
        if( artista != null )
        {
            JOptionPane.showMessageDialog( this, "El artista con más canciones es " + artista.darNombre( ), "Artista con más canciones", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No existen artistas en el karaoke", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra una lista con todas las canciones de una categoría del karaoke
     * @param pCategoria Nombre de la categoría. pCategoria pertenece a Karaoke.CATEGORIAS
     */
    public void mostrarCanciones( Categoria pCategoria )
    {
        List<Cancion> canciones = karaoke.darCancionesCategoria( pCategoria );
        DialogoCanciones dialogo = new DialogoCanciones( pCategoria, canciones );
        dialogo.setVisible( true );
    }

    /**
     * Retorna los artistas de una categoría dada.
     * @param pCategoria Categoría de la cual se quieren los artistas. pCategoria pertenece a Karaoke.CATEGORIAS
     * @return Lista con los artistas de la categoría.
     */
    public List<Artista> darArtistasCategoria( Categoria pCategoria )
    {
        return karaoke.darArtistasCategoria( pCategoria );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = karaoke.metodo1( );
        
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        panelArtistas.actualizarArtistas(karaoke.darArtistas());
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = karaoke.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        panelArtistas.actualizarArtistas(karaoke.darArtistas());
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------
    /**
     * Ejecuta la aplicación.
     * @param pArgs Parámetros de la ejecución. No son necesarios.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazKaraoke interfaz = new InterfazKaraoke( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

}