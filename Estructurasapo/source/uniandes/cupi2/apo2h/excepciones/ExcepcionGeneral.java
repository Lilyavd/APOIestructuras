package uniandes.cupi2.apo2h.excepciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

/**
 * Excepción general.
 *
 */
public class ExcepcionGeneral extends Exception implements Serializable
{
	/**
	 * Archivo para guardar los errores.
	 */
	protected final static String ARCHIVO_LOG_ERRORES = "data/log/error.log";
	
	/**
	 * Mensaje de error.
	 */
	private String mensaje;
	
	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 8742874318766998064L;
	
	public ExcepcionGeneral (String pMensaje)
	{
		super (pMensaje);
		mensaje = pMensaje;
	}
	
	/**
	 * Da el mensaje de la excepción.
	 * @return String que contiene el mensaje de la excepción.
	 */
	public String darMensaje ()
	{
		return mensaje;
	}
	
	/**
	 * Da el nombre de la excepción.
	 * @return String que contiene el nombre de la excepción.
	 */
	protected String darNombreError ()
	{
		return "General";
	}
	
	/**
	 * Escribe el mesaje del error. <br>
	 * <b> post: <\b> Modifica el archivo log de errores.
	 * @param pMensaje String con el mensaje del error. pMensaje != null.
	 */
	protected void escribirLog (String pMensaje)
	{
		File file = new File (ARCHIVO_LOG_ERRORES);
		if (!file.exists())
		{
			try 
			{
				file.createNewFile();
			} catch (IOException e) 
			{
				
			}
		}
		try 
		{
			PrintWriter texto = new PrintWriter( new BufferedWriter (new FileWriter(file,true)));
			
			texto.println(pMensaje);
			texto.close();
		} catch (IOException e) 
		{
		
		} 
		
	}
}
