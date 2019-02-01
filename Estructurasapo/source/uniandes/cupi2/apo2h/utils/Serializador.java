package uniandes.cupi2.apo2h.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import uniandes.cupi2.apo2h.excepciones.PersistenciaException;



public class Serializador 
{
	/**
	 * Guarda un objeto serializable en un archivo binario
	 * @param ruta ruta donde se crear el archivo con el objeto serializado. ruta != null. Si no existe el archivo con la ruta se crea
	 * @param objeto el objeto que se desea serializar
	 * @throws PersistenciaException Se lanza en caso que no se pueda escribir el archivo
	 */
	public static void guardar(String ruta, Serializable objeto)throws PersistenciaException
	{
		//TODO Complete el método según la documentación
		//	En caso de que no exista el archivo lo debe crear
		// 	En caso que no se pueda escribir el archivo debe generar PersistenciaException
		
		try 
		{
			File file = new File (ruta);
			
			if(!file.exists())
				file.createNewFile();
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(objeto);
			obj.close();


		}catch (Exception e) {
			
			throw new  PersistenciaException ("Hubo un error al guerdar el objeto.",ruta);
		}
	}

	/**
	 * Carga un objeto desde un archivo serializado
	 * pre: El archivo solo tiene un objeto
	 * @param ruta la ruta donde se encuentra el archivo que se desea cargar
	 * @param objeto el objeto donde se guadará la información cargada. objeto != null
	 * @return en caso que se pueda cargar la información se guarda en el objeto y se devuelve, de lo contrario solo se devuelve el objeto
	 * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo o se genere algún tipo de error al leerlo
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cargar(String ruta, T objeto)throws PersistenciaException
	{
		try {
			File archivo = new File(ruta);
			if(archivo.exists())
			{
				//TODO Complete el método de acuerdo a la documentación
				//	IMPORTANTE El objeto cargado se debe guardar en el ojeto que llega por parámetro
				ObjectInputStream obj = new ObjectInputStream( new FileInputStream(archivo));
				objeto = (T) obj.readObject();
				obj.close();

			}

		} catch (Exception e) {

			//TODO Complete el método de acuerdo a la documentación
			
			throw new  PersistenciaException ("Hubo un error a leer el archivo.",ruta);
		}
		return objeto;

	}
}
