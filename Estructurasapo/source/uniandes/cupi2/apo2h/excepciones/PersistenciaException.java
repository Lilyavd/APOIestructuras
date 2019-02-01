package uniandes.cupi2.apo2h.excepciones;

import java.util.Date;

/**
 * Excepci�n de persistencia error.
 * @author lilyd
 *
 */
public class PersistenciaException extends ExcepcionGeneral 
{
	/**
	 * Ruta la cual gener� la excepci�n.
	 */
	private String ruta;
	
	/**
	 * Constante de serializaci�n.
	 */
	private final static long serialVersionUID = 4910630810751335803L;

	public PersistenciaException (String pMensaje, String pRuta)
	{
		super (pMensaje);
		ruta = pRuta;
		Date fecha = new Date ();
		escribirLog(fecha.toString()+" "+darNombreError()+" - "+pMensaje);
	}

	protected String darNombreError ()
	{
		return "Persistencia";
	}

	public String darRuta ()
	{
		return ruta;
	}
}
