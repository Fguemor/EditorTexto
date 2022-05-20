package actividad.dual;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * Clase editor de texto 
 * @author Fátima Guerrero
 *@version 1.0
 */
public class Editor {
	//Este atributo representa el archivo que está cargado en el editor
	private Archivo archivo;

	/**
	 * Constructor de la clase Editor
	 */
	public Editor() {
		archivo=null;
	}
	/**
	 * Método para abrir un archivo de texto existente
	 * @param nombreArchivo contiene la ruta completa del archivo
	 * @return String que contiene el contenido del archivo
	 * @throws Exception Arroja un excepción cuando ocurre un problema abriendo el archivo
	 */
	public String abrir(String nombreArchivo) throws Exception {
		String contenido="";
		//creamos el archivo que voy abrir
		archivo=new Archivo(nombreArchivo);
		//llamo al método dar contenido de la clase Archivo
		try {
			contenido=archivo.contenido();
		} catch (IOException e) {
			throw new Exception("Error al abrir el archivo", e);
		}
		return contenido;

	}
	/**
	 * Método para abrir recientemente, y se abra el último archivo que hemos abierto
	 * @param ruta pasamos la ruta guardada en un archivo de texto plano
	 * @return devuelve la ruta
	 */
	public static String  abrirRecientemente(String ruta) {
		Desktop fichero=Desktop.getDesktop();
		try {
			fichero.open(new File(ruta));
		} catch (Exception e) {
		}
		return ruta;
	}
	/**
	 * Crea un archivo de texto nuevo, dejando el archivo en blanco
	 */
	public void crear() {
		archivo=null;

	}
	/**
	 * Guarda el contenido del archivo,sea nuevo o existente
	 * @param contenido String que contiene el contenido del archivo
	 * @param ruta ruta del archivo si es un nuevo archivo
	 * @throws Exception arroja una excepcion al guardar un archivo si se produce un error
	 */
	public void guardar(String contenido,String ruta) throws Exception {
		//Primero veo si el archivo es nuevo o no, utilizando un if
		if (archivo==null) {
			//si es null creo un nuevo archivo
			archivo=new Archivo(ruta);
		}
		try {
			archivo.guardar(contenido);
		} catch (IOException e) {
			throw new Exception("Error al guardar el archivo",e);
		}
	}
	/**
	 * Indica si el archivo del editor es nuevo o no
	 * @return true si es nuevo, false en caso contrario
	 */
	public boolean archivoNuevo() {
		return archivo==null;
	}
}
