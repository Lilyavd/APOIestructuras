package uniandes.cupi2.apo2h.excepciones;

import java.util.Date;

/**
 * Excepción de persistencia error.
 * @author lilyd
 *
 */
public class PersistenciaException extends ExcepcionGeneral 
{
	/**
	 * Ruta la cual generó la excepción.
	 */
	private String ruta;
	
	/**
	 * Constante de serialización.
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
