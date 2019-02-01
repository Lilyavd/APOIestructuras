package uniandes.cupi2.apo2h.excepciones;

/**
 * Excepci�n asociada a un error de base de datos.
 *
 */
public class BaseDeDatosException extends ExcepcionGeneral 
{

	/**
	 * Constante de serializaci�n.
	 */
	private static final long serialVersionUID = -7066942225999317992L;

	public BaseDeDatosException(String mensaje) 
	{
		super(mensaje);
	}
	
	@Override
	protected String darNombreError() 
	{
		return "Base de datos";
	}

}
