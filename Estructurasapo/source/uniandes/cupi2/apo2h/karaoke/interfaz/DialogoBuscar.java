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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.RadioButton;

/**
 * Clase que representar el dialogo para las busquedas.
 */
@SuppressWarnings("serial")
public class DialogoBuscar extends JDialog implements ActionListener{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
     * Comando Buscar
     */
    private static final String BUSCAR = "Buscar";
    
    
    //TODO Declare y documente las constantes necesarias para el manejo de los radios.
    /**
     * Comando Buscar canción más fácil
     */
    private static final String CANCION_MAS_FACIL = "Canción más fácil";
    
    /**
     * Comando Buscar canción más difícil
     */
    private static final String CANCION_MAS_DIFICIL = "Canción más difícil";
    
    /**
     * Comando Buscar canción más corta
     */
    private static final String CANCION_MAS_CORTA = "Canción más corta";
    
    /**
     * Comando Buscar canción más larga
     */
    private static final String CANCION_MAS_LARGA = "Canción más larga";
    
    /**
     * Comando Buscar artista con más canciones
     */
    private static final String ARTISTA_MAS_CANCIONES = "Artista más canciones";
    
    
	// -----------------------------------------------------------------
	// Atributos de Interfaz
	// -----------------------------------------------------------------
	
	/**
     * Botón Buscar
     */
    private JButton btnBuscar;
    
    // TODO Declare y documente los atributos que representan los radios.
	/**
	 * Arreglo de botones de radio.
	 */
    private JRadioButton[] btnRadios; 
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazKaraoke principal;
	
	/**
	 * Opción seleccionada.
	 */
	private String opcSeleccionada;
	
	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel de facturas
     * @param pPrincipal Instancia del panel contenedor. pPrincipal != null.
     */
	public DialogoBuscar(InterfazKaraoke pPrincipal) {
		super(pPrincipal, true);
		
		principal= pPrincipal;
		setTitle( "Buscar" );
        setSize( 220, 250 );
        setLocationRelativeTo( principal );
		opcSeleccionada = null;
		
		JPanel contenedor = new JPanel(new GridLayout(6, 1));
		contenedor.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		// TODO Cree el grupo de botones para los radios.
		ButtonGroup btnGroup = new ButtonGroup();
		
		// TODO Inicialice los radios
		btnRadios = new JRadioButton[5];
		btnRadios [0] = new JRadioButton (CANCION_MAS_FACIL);
		btnRadios [1] = new JRadioButton (CANCION_MAS_DIFICIL);
		btnRadios [2] = new JRadioButton (CANCION_MAS_CORTA);
		btnRadios [3] = new JRadioButton (CANCION_MAS_LARGA);
		btnRadios [4] = new JRadioButton (ARTISTA_MAS_CANCIONES);
		
		for (JRadioButton j: btnRadios)
		{
			j.setActionCommand(j.getText());
			j.addActionListener(this);
		}
		
		// TODO Agregue los radios al grupo de botones
		for (JRadioButton j: btnRadios)
			btnGroup.add(j);
		
		// TODO Agregue los radios al panel contenedor
		for (JRadioButton j: btnRadios)
			contenedor.add(j);
		
		// TODO Marque el primer radio como seleccionado 
		btnRadios [0].setSelected(true);
		
		// TODO Inicialice el atributo opcSeleccionada con el comando del radio seleccionado.
		opcSeleccionada = btnRadios [0].getText();
		
		btnBuscar = new JButton(BUSCAR);
		btnBuscar.setActionCommand(BUSCAR);
		btnBuscar.addActionListener(this);
		contenedor.add(btnBuscar);
		
		add(contenedor);
	}
	
	 //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
    	// TODO Implementar el manejo de los eventos de los botones
    	if (pEvento.getActionCommand().equals(BUSCAR))
    	{
    		switch (opcSeleccionada) {
			case CANCION_MAS_FACIL:
				principal.mostrarCancionMasFacil();
				break;
			case CANCION_MAS_DIFICIL:
				principal.mostrarCancionMasDificil();
				break;
			case CANCION_MAS_CORTA:
				principal.mostrarCancionMasCorta();
				break;
			case CANCION_MAS_LARGA:
				principal.mostrarCancionMasLarga();
				break;
			case ARTISTA_MAS_CANCIONES:
				principal.mostrarArtistaMasCanciones();
				break;
			default:
				break;
			}
    	}else
    	{
    		opcSeleccionada = pEvento.getActionCommand();
    	}
    }

}
