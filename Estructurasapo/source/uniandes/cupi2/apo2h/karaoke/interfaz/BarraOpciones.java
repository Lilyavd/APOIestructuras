package uniandes.cupi2.apo2h.karaoke.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import uniandes.cupi2.apo2h.karaoke.mundo.CriteriosComparacionCanciones;
import uniandes.cupi2.apo2h.karaoke.mundo.Karaoke.Categoria;

@SuppressWarnings("serial")
public class BarraOpciones extends JMenuBar implements ActionListener
{
	
	/**
	 * Enum con los comandos de los ítems
	 *
	 */
	private enum Comando{
		CARGAR, GUARDAR, AGREGAR_ARTISTA, ELIMINAR_ARTISTA, AGREGAR_CANCION, ELIMINAR_CANCION, ORDENAR_CANCIONES, BUSCAR, LISTA_DE_CANCIONES, OPCION_1, OPCION_2
	}
	
	/**
	 * Ruta con los íconos de la barra opciones.
	 */
	public static final String RUTA_ICONOS = "data/iconos/";
	
	/**
	 * Menú para el manejo de archivos
	 */
	private JMenu menuArchivo;
	
	/**
	 * Ítem para cargar un archivo de karaoke.
	 */
	private JMenuItem itemCargar;
	
	/**
	 * Ítem para guardar un archivo de karaoke
	 */
	private JMenuItem itemGuardar;
	
	/**
	 * Menú para manejar los artistas.
	 */
	private JMenu menuArtistas;
	
	/**
	 * Ítem para agregar un nuevo artista
	 */
	private JMenuItem itemAgregarArtista;
	
	/**
	 * Ítem para eliminar un artista.
	 */
	private JMenuItem itemEliminarArtista;
	
	/**
	 * Menú para manejar las canciones
	 */
	private JMenu menuCanciones;
	
	/**
	 * Ítem para agregar una canción
	 */
	private JMenuItem itemAgregarCancion;
	
	/**
	 * Ítem para eliminar una canción.
	 */
	private JMenuItem itemEliminarCancion;
	
	/**
	 * Ítem para ordenar las canciones
	 */
	private JMenuItem itemOrdenarCanciones;
	
	/**
	 * Ítem para buscar.
	 */
	private JMenuItem itemBuscar;
	
	/**
	 * Ítem para ver la lista de canciones.
	 */
	private JMenuItem itemListaDeCanciones;
	
	/**
	 * Menú de opciones.
	 */
	private JMenu menuOpciones;
	
	/**
	 * Ítem de opción 1
	 */
	private JMenuItem itemOpcion1;
	
	/**
	 * Ítem de opción 2
	 */
	private JMenuItem itemOpcion2;
	
	/**
	 * Clase principal de la principal
	 */
	private InterfazKaraoke principal;
	
	/**
	 * Constructor de la barra de opciones
	 */
	public BarraOpciones(InterfazKaraoke interfaz) 
	{
		this.principal = interfaz;
		menuArchivo = new JMenu("Archivos");
		menuArchivo.setIcon(new ImageIcon(RUTA_ICONOS + "archivos.png"));
		add(menuArchivo);
		
		itemCargar = new JMenuItem("Cargar");
		asignarCaracteristicasItem(itemCargar, Comando.CARGAR, RUTA_ICONOS + "abrir.png");
		menuArchivo.add(itemCargar);
		
		itemGuardar = new JMenuItem("Guardar");
		asignarCaracteristicasItem(itemGuardar, Comando.GUARDAR, RUTA_ICONOS + "guardar.png");
		menuArchivo.add(itemGuardar);
		
		menuArtistas = new JMenu("Artistas");
		menuArtistas.setIcon(new ImageIcon(RUTA_ICONOS + "artista.png"));
		add(menuArtistas);
		
		itemAgregarArtista = new JMenuItem("Agregar artista");
		asignarCaracteristicasItem(itemAgregarArtista, Comando.AGREGAR_ARTISTA, RUTA_ICONOS + "nuevo-artista.png");
		menuArtistas.add(itemAgregarArtista);
		
		itemEliminarArtista = new JMenuItem("Eliminar artista");
		asignarCaracteristicasItem(itemEliminarArtista, Comando.ELIMINAR_ARTISTA, RUTA_ICONOS + "eliminar-artista.png");
		menuArtistas.add(itemEliminarArtista);
		
		menuCanciones = new JMenu("Canciones");
		menuCanciones.setIcon(new ImageIcon(RUTA_ICONOS + "disco-32.png"));
		add(menuCanciones);
		
		itemAgregarCancion = new JMenuItem("Agregar canción");
		asignarCaracteristicasItem(itemAgregarCancion, Comando.AGREGAR_CANCION, RUTA_ICONOS + "agregar.png");
		menuCanciones.add(itemAgregarCancion);
		
		itemEliminarCancion = new JMenuItem("Eliminar canción");
		asignarCaracteristicasItem(itemEliminarCancion, Comando.ELIMINAR_CANCION, RUTA_ICONOS + "eliminar.png");
		menuCanciones.add(itemEliminarCancion);
		
		menuCanciones.addSeparator();
		
		itemOrdenarCanciones = new JMenuItem("Ordenar canciones");
		asignarCaracteristicasItem(itemOrdenarCanciones, Comando.ORDENAR_CANCIONES, RUTA_ICONOS + "ordenar.png");
		menuCanciones.add(itemOrdenarCanciones);
		
		menuOpciones = new JMenu("Opciones");
		menuOpciones.setIcon(new ImageIcon(RUTA_ICONOS + "opciones.png"));
		add(menuOpciones);
		
		itemBuscar = new JMenuItem("Buscar");
		asignarCaracteristicasItem(itemBuscar, Comando.BUSCAR, RUTA_ICONOS + "buscar.png");
		menuOpciones.add(itemBuscar);
		
		itemListaDeCanciones = new JMenuItem("Lista de canciones");
		asignarCaracteristicasItem(itemListaDeCanciones, Comando.LISTA_DE_CANCIONES, RUTA_ICONOS + "disco-24.png");
		menuOpciones.add(itemListaDeCanciones);
		
		menuOpciones.addSeparator();
		
		itemOpcion1 = new JMenuItem("Opción 1");
		asignarCaracteristicasItem(itemOpcion1, Comando.OPCION_1, RUTA_ICONOS + "uno.png");
		menuOpciones.add(itemOpcion1);
		
		itemOpcion2 = new JMenuItem("Opción 2");
		asignarCaracteristicasItem(itemOpcion2, Comando.OPCION_2, RUTA_ICONOS + "dos.png");
		menuOpciones.add(itemOpcion2);
	}
	
	private void asignarCaracteristicasItem(JMenuItem item, Comando comando, String rutaImagen)
	{
		item.setActionCommand(comando.name());
		item.addActionListener(this);
		item.setIcon(new ImageIcon(rutaImagen));
	}
	
	public void actualizarEstadoEliminarArtista(boolean estado)
	{
		itemEliminarArtista.setEnabled(estado);
	}

	public void actualizarEstadoEliminarCancion(boolean estado)
	{
		itemEliminarCancion.setEnabled(estado);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Comando comando = Comando.valueOf(e.getActionCommand());
		
		switch (comando) {
		case CARGAR:
			principal.cargar();
			break;
			
		case GUARDAR:
			principal.guardar();
			break;
			
		case AGREGAR_ARTISTA:
			DialogoAgregarArtista dialogoAgregarArtista = new DialogoAgregarArtista(principal);
        	dialogoAgregarArtista.setVisible(true);
        	break;
		case ELIMINAR_ARTISTA:
			principal.eliminarArtistaSeleccionado( );
			break;
        	
		case AGREGAR_CANCION:
			DialogoAgregarCancion dialogoAgregarCancion = new DialogoAgregarCancion(principal);
        	dialogoAgregarCancion.setVisible(true);
        	break;
        	
		case ELIMINAR_CANCION:
			principal.eliminarCancionSeleccionada( );
			break;
			
		case ORDENAR_CANCIONES:
			CriteriosComparacionCanciones criterio = (CriteriosComparacionCanciones) JOptionPane.showInputDialog(null, "Criterio ordenamiento: ", "Ordenar canciones", JOptionPane.QUESTION_MESSAGE, null, CriteriosComparacionCanciones.values(), CriteriosComparacionCanciones.values()[0]);
			principal.ordenarCanciones(criterio);
			break;
        	
		case BUSCAR:
			new DialogoBuscar(principal).setVisible(true);
			break;
			
		case LISTA_DE_CANCIONES:
			Categoria c = (Categoria)JOptionPane.showInputDialog(null, "Categoría: ", "Lista de canciones", JOptionPane.QUESTION_MESSAGE, null, Categoria.values(), Categoria.values()[0]);
            if(c != null)
            {	
            	principal.mostrarCanciones(c);            	
            }
            break;
		case OPCION_1:
			principal.reqFuncOpcion1();
			break;
		case OPCION_2:
			principal.reqFuncOpcion2();
			break;
		default:
			break;
		}
		
	}

}
