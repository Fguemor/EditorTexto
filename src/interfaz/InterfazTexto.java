package interfaz;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import actividad.dual.Editor;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.JTabbedPane;
/**
 * Interfaz principal
 * @author Fátima Guerrero
 *@version 1.0
 */

public class InterfazTexto extends JFrame implements ActionListener{
	private JPanel contentPane;
	private Acciones acciones;
	private Panel panel;
	private Editor editor;
	private JLabel lblNewLabel;
	private JLabel contadorPalabras;
	private JLabel contadorFilas;
	public  JTextArea textArea;
	public  JTextArea textArea1;
	public  JTextArea textArea2;
	private JLabel labelFuente;
	private JSpinner tamanioFuente;
	private JComboBox cuadro;
	private JCheckBox negrita;
	private JCheckBox cursiva;
	int seleccion;

	//Declaración de variables
	JMenuBar menuBar;
	JMenu menu;
	JMenu barraHerramientas;
	JMenuItem abrir;
	JMenuItem crear;
	JMenuItem color;
	JMenuItem comparar;
	JMenuItem guardar;
	JMenuItem salir;
	JMenuItem abiertoRecientemente;
	JFrame ventanaEmer = new JFrame();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazTexto frame = new InterfazTexto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public InterfazTexto() {
		crearMenu();
		//creo el objeto
		editor=new Editor();
		//titulo de editor
		setTitle("Editor Texto FGM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 5));
		acciones = new Acciones(this);
		contentPane.add(acciones, BorderLayout.NORTH);

		panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		lblNewLabel = new JLabel("Barra de estado:" );

		textArea = panel.textArea;
		textArea2=panel.textArea2;
		//variable seleccion, seleciona 0 si estamos en la pestaña, -1 si estamos en otra
		seleccion=panel.pestanias.getSelectedIndex();


		//JLabel para seleccionar la fuente
		//Lo añadimos en el panel
		labelFuente = new JLabel("Fuente: ");
		acciones.add(labelFuente);
		//JComboBox para seleccionar el tipo de la fuente
		//Lo añadimos en el panel
		String[]fuentes=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		cuadro = new JComboBox(fuentes);
		cuadro.setSelectedItem("Arial");
		cuadro.addActionListener(this);
		acciones.add(cuadro);
		//JSpinner para seleccionar el tamaño de la fuente
		//Lo añadimos en el panel
		tamanioFuente = new JSpinner();
		acciones.add(tamanioFuente);
		tamanioFuente.setPreferredSize(new Dimension(50,25));
		tamanioFuente.setValue(20);
		tamanioFuente.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) tamanioFuente.getValue()));
				textArea2.setFont(new Font(textArea2.getFont().getFamily(),Font.PLAIN,(int) tamanioFuente.getValue()));


			}
		}
				);
		//JCheckBox para marcar la negrita
		negrita = new JCheckBox("Negrita");
		negrita.addActionListener(this);
		acciones.add(negrita);
		//JCheckBox para marcar la cursiva
		cursiva = new JCheckBox("Cursiva");
		cursiva.addActionListener(this);
		acciones.add(cursiva);

		//Para el contador de palabras y filas del archivo 1
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				calcularPalabras();
				calcularFilas();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				calcularPalabras();
				calcularFilas();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				calcularPalabras();
				calcularFilas();
			}
		});
		//Para el contador de palabras y filas del archivo 2
		textArea2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				calcularPalabras2();
				calcularFilas2();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				calcularPalabras2();
				calcularFilas2();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				calcularPalabras2();
				calcularFilas2();
			}
		});

		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(contadorPalabras, BorderLayout.SOUTH);
		contadorFilas=new JLabel("Contador de Filas :");
		contentPane.add(contadorFilas, BorderLayout.SOUTH);
	}
	/**
	 * Método para crear el menú
	 */
	private void crearMenu() {
		//Asignación de variables
		menuBar =new JMenuBar();
		menu =new JMenu("Menu");
		barraHerramientas=new JMenu("Barra de herramientas");
		//Creación de submenus
		abrir= new JMenuItem("Abrir");
		crear= new JMenuItem("Crear");
		guardar= new JMenuItem("Guardar");
		abiertoRecientemente= new JMenuItem("Abierto Recientemente");
		salir=new JMenuItem("Salir");
		contadorPalabras=new JLabel("Contador de Palabras: ");
		//He introducido en el menú el color
		color=new JMenuItem("Color");
		comparar=new JMenuItem("comparar");


		//añadimos los anteriores submenus a la barra de herramientas y unos separadores entre ellos utilizando JSeparator
		barraHerramientas.add(abrir);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(abiertoRecientemente);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(color);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(crear);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(comparar);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(guardar);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(salir);
		menuBar.add(barraHerramientas);
		//añadimos el controlador de eventos
		salir.addActionListener(this);
		abrir.addActionListener(this);
		crear.addActionListener(this);
		color.addActionListener(this);
		guardar.addActionListener(this);
		comparar.addActionListener(this);
		abiertoRecientemente.addActionListener(this);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}
	/**
	 * método para abrir dos archivo, dónde nos muestra para elegir el archivo 1 
	 * y luego nos muestra una ventana emergente avisando que vamos a seleccionar la segunda 
	 * ruta del otro archivo, y elegiremos el segundo archivo y se abrirá
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void abrir()  {
		JFileChooser fileChooser=new JFileChooser();
		JFileChooser fileChooser2=new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser2.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter =new FileNameExtensionFilter("Archivo de texto","txt");
		FileNameExtensionFilter filter2 =new FileNameExtensionFilter("Archivo de texto","txt");
		fileChooser.setFileFilter(filter);
		fileChooser2.setFileFilter(filter2);
		int response=fileChooser.showOpenDialog(null);
		JOptionPane.showMessageDialog(ventanaEmer, "Selecciona la ruta del segundo archivo");
		int responses=fileChooser2.showOpenDialog(null);
		if (response==JFileChooser.APPROVE_OPTION &&responses==JFileChooser.APPROVE_OPTION) {
			try {
				File f=fileChooser.getSelectedFile();
				File g=fileChooser2.getSelectedFile();
				String contenido="";
				String contenido2="";
				contenido2 = editor.abrir(g.getAbsolutePath());
				contenido=editor.abrir(f.getAbsolutePath());
				panel.setContenido(contenido);
				panel.setContenido2(contenido2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(responses==JFileChooser.CANCEL_OPTION) {
			panel.setContenido2(" ");
		}




	}

	/**
	 * método para abrir recientemente, donde coge la primera línea del archivo donde almaceno la ruta y el nombre del último archivo abierto.
	 * @throws FileNotFoundException
	 */
	public void abrirRecientemente() throws FileNotFoundException {

		BufferedReader reader = new BufferedReader(new FileReader("/home/usuario/Desktop/Dual/EditorTexto/src/actividad/dual/AbiertoRecientemente.txt"));
		String ruta = null;
		String contenido="";
		try {
			ruta = reader.readLine();

			panel.setContenido(editor.abrirRecientemente(contenido));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editor.abrirRecientemente(ruta);


	}
	/**
	 * Método donde se compara el número de palabras, filas 
	 * entre los dos archivos
	 */
	public void comparar() {
		//ventana emergente
		JOptionPane.showMessageDialog(ventanaEmer, "Haz clic en la pestaña Comparador de archivo");
		//Recojo el número de palabras que tiene cada archivo con un String tokenizer
		String text=textArea.getText();
		//utilizo el stringtokenizer para contar las palabras
		StringTokenizer stringTokenizer1 = new StringTokenizer(text);
		String linea[]=text.split("\n");
		//recojo en un array los bytes del archivo1
		byte[] conseguirby = null;
		try {
			conseguirby=text.getBytes("UTF-8") ;
			int bytearch1=conseguirby.length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//inicialización de variable que recoge el número de filas arch1
		int contadorFilas1=linea.length;
		//inicialización de variable que recoge el número de palabras arch1
		int contadorPalabras1=stringTokenizer1.countTokens();
		String text2=textArea2.getText();
		int contadorBytes=text2.length();
		//recojo en un array los bytes del archivo2
		byte[] conseguirby2 = null;
		try {
			conseguirby2 = text2.getBytes("UTF-8");
			int bytearch2=conseguirby2.length;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringTokenizer stringTokenizer2 = new StringTokenizer(text2);
		String lineas[]=text2.split("\n");
		//inicialización de variable que recoge el número de filas arch2
		int contadorFilas2=lineas.length;
		//inicialización de variable que recoge el número de palabras arch2
		int contadorPalabras2=stringTokenizer2.countTokens();
		//comparación de tamaño de palabras
		if (contadorPalabras1>contadorPalabras2) {
			String texto="El archivo 1 tiene una diferencia de palabras con el archivo 2 de :"+ (contadorPalabras1-contadorPalabras2)+" palabras";
			panel.setContenidoComparador(texto);
		}else if (contadorPalabras1==contadorPalabras2) {
			panel.comparador.setText("El archivo 1 y 2 tienen las mismas palabras : "+contadorPalabras1+" palabras");
		} else {
			panel.comparador.setText("El archivo 2 tiene mayor palabras que el archivo 1, con una diferencia de :"+ (contadorPalabras2-contadorPalabras1)+" palabras");
		}
		//Comparación de tamaños de filas
		if (contadorFilas1>contadorFilas2) {
			panel.comparador.setText("El archivo 1 tiene una diferencia de filas con el archivo 2 de :"+ (contadorFilas1-contadorFilas2)+" filas"+"\n"+ panel.comparador.getText());
		}else if (contadorFilas2>contadorFilas1) {
			panel.comparador.setText("El archivo 2 tiene una diferencia de filas con el archivo 1 de :"+ (contadorFilas2-contadorFilas1)+" filas"+"\n"+ panel.comparador.getText());
		} else {
			panel.comparador.setText("El archivo 1 tiene las mismas filas que el archivo 2 :"+ contadorFilas2+" filas"+"\n"+ panel.comparador.getText());
		}	
		//Comparación de tamaños de archivos
		if (conseguirby.length>conseguirby2.length) {
			panel.comparador.setText("El archivo 1 es de mayor tamaño que el archivo 2, el archivo 1 es de :"+ conseguirby.length +"bytes. El archivo 2 es de: "+ conseguirby2.length+" bytes, la diferencia es de: "+(conseguirby.length-conseguirby2.length)+" bytes" +"\n"+panel.comparador.getText());
		}else if (conseguirby.length<conseguirby2.length) {
			panel.comparador.setText("El archivo 2 es de mayor tamaño que el archivo 1, el archivo 2 es de :"+ conseguirby2.length +"bytes. El archivo 1 es de: "+ conseguirby.length+" bytes, la diferencia es de: "+(conseguirby2.length-conseguirby.length)+" bytes"+"\n" +panel.comparador.getText());
		}else {
			panel.comparador.setText("El archivo 1 es de igual tamaño que el archivo 2, el tamaño es de :"+(conseguirby2.length)+" bytes" +"\n"+panel.comparador.getText());
		}

	}

	/**
	 * Método para crear un nuevo archivo, deja el archivo nulo
	 */
	public void crear() {
		editor.crear();
		panel.setContenido("");
		panel.setContenido2("");
		//Si creo un nuevo archivo desmarcar las casillas
		this.negrita.setSelected(false);
		this.cursiva.setSelected(false);

	}
	/**
	 * Método para salir del editor de texto
	 */
	public void salir() {
		System.exit(0);

	}
	/**
	 * Método para añadir el color al texto
	 */
	public void color() {
		JColorChooser colorElegido=new JColorChooser();
		Color color=JColorChooser.showDialog(null, "Elige un color: ", Color.black);
		if (seleccion==0) {
			panel.textArea.setForeground(color);
		}else {
			panel.textArea2.setForeground(color);

		}
	}

	/**
	 * Método para guardar el archivo, primero compruebo si el archivo existe o no existe con un if-else
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void guardar() {
		if (seleccion==0) {
			if (editor.archivoNuevo()) {
				JFileChooser fileChooser=new JFileChooser();
				if(fileChooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
					//recoge la ruta del archivo
					String ruta= fileChooser.getSelectedFile().getAbsolutePath();
					String contenido=panel.getContenido();
					try {
						editor.guardar(contenido, ruta);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					String contenidoNuevo=panel.getContenido();	
					try {
						editor.guardar(contenidoNuevo,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}else if(seleccion!=0){
			if (editor.archivoNuevo()) {
				JFileChooser fileChooser=new JFileChooser();
				if(fileChooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
					//recoge la ruta del archivo
					String ruta= fileChooser.getSelectedFile().getAbsolutePath();
					String contenido=panel.getContenido2();
					try {
						editor.guardar(contenido, ruta);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					String contenidoNuevo=panel.getContenido2();	
					try {
						editor.guardar(contenidoNuevo,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			JOptionPane.showMessageDialog(ventanaEmer, "Si deseas guardar o modificar otro archivo, pulsa crear archivo nuevo y guarda");
		}
	}






	/**
	 * Método para calcular palabras del archivo
	 */
	public void calcularPalabras() {
		String text=textArea.getText();
		String palabra[]=text.split("\\s");
		contadorPalabras.setText("Contador de palabras: "+palabra.length);
	}
	/**
	 * Método para calcular las filas del archivo
	 */
	public void calcularFilas() {
		String text=textArea.getText();
		String linea[]=text.split("\n");
		contadorFilas.setText("Líneas: "+linea.length);
	}
	/**
	 * Método para calcular palabras del archivo
	 */
	public void calcularPalabras2() {
		String text=textArea2.getText();
		String palabras[]=text.split("\\s");
		contadorPalabras.setText("Contador de palabras: "+palabras.length);
	}
	/**
	 * Método para calcular las filas del archivo
	 */
	public void calcularFilas2() {
		String text=textArea2.getText();
		String lineas[]=text.split("\n");
		contadorFilas.setText("Líneas: "+lineas.length);
	}
	/**
	 * Método para elegir el tipo de fuente
	 */
	public void cuadro() {

		if (seleccion==0) {
			textArea.setFont(new Font((String)cuadro.getSelectedItem(),Font.PLAIN, textArea.getFont().getSize()));
		}else {
			textArea2.setFont(new Font((String)cuadro.getSelectedItem(),Font.PLAIN, textArea2.getFont().getSize()));
		}
	}
	/**
	 * Método para poner el tipo de fuente en negrita
	 */
	public void negrita() {
		if (seleccion==0) {
			textArea.setFont(new Font((String)cuadro.getSelectedItem(),Font.BOLD, textArea.getFont().getSize()));
		}else {

			textArea2.setFont(new Font((String)cuadro.getSelectedItem(),Font.BOLD, textArea2.getFont().getSize()));
		}

	}
	/**
	 * Método para poner el tipo de fuente en cursiva
	 */
	public void cursiva() {
		if (seleccion==0) {
			textArea.setFont(new Font((String)cuadro.getSelectedItem(),Font.ITALIC, textArea.getFont().getSize()));
		}else {
			textArea2.setFont(new Font((String)cuadro.getSelectedItem(),Font.ITALIC, textArea2.getFont().getSize()));
		}
	}

	/**
	 * Método donde se elige la acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==comparar) {
			this.comparar();
		}else if (e.getSource()==negrita) {
			this.negrita();
		}else if (e.getSource()==cursiva) {
			this.cursiva();
		}else if (e.getSource()==cursiva && e.getSource()==negrita) {
			this.cursiva();
			this.negrita();
		}else if (e.getSource()==cuadro) {
			this.cuadro();
		}else if (e.getSource()==color) {
			this.color();
		}else if (e.getSource()==abrir) {
			this.abrir();
		}else if (e.getSource()==crear) {
			this.crear();
		}else if (e.getSource()==guardar) {
			this.guardar();
		}else if (e.getSource()==salir) {
			this.salir();
		}else if (e.getSource()==abiertoRecientemente) {
			try {
				this.abrirRecientemente();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}
}


