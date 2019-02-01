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
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import uniandes.cupi2.apo2h.karaoke.mundo.Cancion;
import uniandes.cupi2.apo2h.karaoke.mundo.Karaoke.Categoria;

/**
 * Dialogo con la lista de canciones de una categoría del karaoke
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DialogoCanciones extends JDialog
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo con la lista de canciones
     * @param pCategoria Nombre de la categoría. pertenece a Karaoke.CATEGORIAS
     * @param pCanciones Lista de canciones. canciones != null
     */
	public DialogoCanciones( Categoria pCategoria, List<Cancion> pCanciones )
    {
        setLayout( new BorderLayout( ) );
        setSize( 350, 200 );
        setTitle( " Canciones - " + pCategoria );
        setModal( true );
        setLocationRelativeTo( null );

        JScrollPane scrollDesplazamiento = new JScrollPane( );
        JList lista = new JList( );
        lista.setEnabled( false );
        lista.setListData( pCanciones.toArray( ) );

        scrollDesplazamiento.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollDesplazamiento.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollDesplazamiento.setViewportView( lista );
        add( scrollDesplazamiento, BorderLayout.CENTER );
    }
}