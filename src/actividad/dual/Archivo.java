package actividad.dual;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Clase Archivo de Texto
 * @author Fátima
 *@version 1.0
 */
public class Archivo {
	//Objeto de la clase File que representa un archivo
	private File  archivo;

	/**
	 * Constructor de la clase archivo
	 * @param nombreArchivo ruta del archivo que se va a crear
	 */
	public Archivo(String nombreArchivo) {
		//creamos el archivo
		archivo=new File(nombreArchivo);
		//Creo un archivo de texto plano en la ruta especificada donde guardo la ruta y el nombre del archivo que estoy utilizando
		try {
			PrintWriter writer = new PrintWriter("/home/usuario/Desktop/Dual/EditorTexto/src/actividad/dual/AbiertoRecientemente.txt", "UTF-8");
			writer.println(archivo.getAbsolutePath());
			writer.println(archivo.getName());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Método para dar el contenido del archivo 
	 * @return contenido del archivo
	 * @throws IOException cuando hay un error leyendo o abriendo archivo, arroja excepción
	 */
	public String contenido() throws IOException {
		//variable para almacenar contenido
		String contenido="";
		//Utilizo la clase FileReader para leer el archivo
		FileReader leer=new FileReader(archivo);
		BufferedReader lector=new BufferedReader(leer);
		//readLine lee linea por linea
		String linea=lector.readLine();
		while(linea!=null) {
			contenido+=linea +"\n";
			linea=lector.readLine();
		}
		//cierro flujo
		lector.close();
		leer.close();

		return contenido;

	}
	/**
	 * Método para guardar el archivo
	 * @param contenido
	 * @throws IOException cuando hay un error guardando archivo, arroja excepcion
	 */
	public void guardar(String contenido) throws IOException{
		//creamos un objeto de la clase PrintWriter
		PrintWriter escribir=new PrintWriter(archivo);
		escribir.write(contenido);
		//Cierro flujo
		escribir.close();

	}	      
}


