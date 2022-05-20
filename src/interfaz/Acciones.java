package interfaz;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
/**
 * Interfaz creada para los botones del editor, es decir las acciones
 * @author Fátima Guerrero
 *@version 1.0
 */

public class Acciones extends JPanel implements ActionListener{
	//creacion de constantes para los botones 
	public static final String CREAR="Crear";
	public static final String ABRIR="Abrir";
	public static final String ABIERTO_RECIENTEMENTE="Abierto Recientemente";
	public static final String GUARDAR="Guardar";
	public static final String SALIR="Salir";
	public static final String COLOR="Color";
	public static final String COMPARAR="comparar";

	//botones que he creado para la barra de herramientas
	private JButton btnAbrir;
	private JButton btnCrear;
	private JButton btnGuardar;
	private InterfazTexto principal;
	private JButton btnSalir;
	private JButton btnAbiertoRecientemente;
	private JButton btnComparar;
	//añado el botón del color
	private JButton btnColor;
	private JCheckBox negrita;
	private JCheckBox cursiva;



	/**
	 * Constructor de la clase
	 * @param principal pasamos la Interfaztexto
	 */
	public Acciones(InterfazTexto principal) {
		this();
		this.principal=principal;

	}
	/**
	 * Create the panel.
	 */
	public Acciones() {
		setLayout(new GridLayout(1, 0, 7, 0));	
		//Botón para seleccionar el color
		//añadimos el botón al evento de acción
		btnColor = new JButton("Elige un color");
		btnColor.setActionCommand(COLOR);
		btnColor.addActionListener(this);
		add(btnColor);
		

		

		//Botón para abrir archivo
		//añadimos el botón al evento de acción
		btnAbrir = new JButton("Abrir archivo");
		btnAbrir.setActionCommand(ABRIR);
		btnAbrir.addActionListener(this);
		add(btnAbrir);
		//Botón para abierto recientemente
		//añadimos el botón al evento de acción
		btnAbiertoRecientemente = new JButton("Abierto recientemente");
		btnAbiertoRecientemente.setActionCommand(ABIERTO_RECIENTEMENTE);
		btnAbiertoRecientemente.addActionListener(this);
		add(btnAbiertoRecientemente);
		//Botón para crear archivo
		//añadimos el botón al evento de acción
		btnCrear = new JButton("Crear archivo");
		btnCrear.setActionCommand(CREAR);
		btnCrear.addActionListener(this);
		add(btnCrear);
		//Botón para guardar archivo
		//añadimos el botón al evento de acción
		btnGuardar = new JButton("Guardar archivo");
		btnGuardar.setActionCommand(GUARDAR);
		btnGuardar.addActionListener(this);
		add(btnGuardar);
		//Botón para comparar archivos
		//añadimos el botón al evento de acción
		btnComparar = new JButton("Comparar");
		btnComparar.setActionCommand(COMPARAR);
		btnComparar.addActionListener(this);
		add(btnComparar);
		//Botón para salir
		//añadimos el botón al evento de acción
		btnSalir = new JButton("Salir");
		btnSalir.setActionCommand(SALIR);
		btnSalir.addActionListener(this);
		add(btnSalir);

	}

	/**
	 * Método donde se elige la acción del botón
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(COMPARAR)) {
			principal.comparar();
		}else if (e.getActionCommand().equals(COLOR)) {
			principal.color();
		}else if (e.getActionCommand().equals(ABRIR)) {
			principal.abrir();
		}else if (e.getActionCommand().equals(ABIERTO_RECIENTEMENTE)) {
			try {
				principal.abrirRecientemente();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}else if (e.getActionCommand().equals(CREAR)) {
			principal.crear();
		}else if (e.getActionCommand().equals(GUARDAR)) {
			principal.guardar();
		}else if (e.getActionCommand().equals(SALIR)) {
			principal.salir();
		}
	}


}

