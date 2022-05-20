package interfaz;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Interfaz para el panel del editor donde recojo las pestañas del editor
 * @author Fátima Guerrero 
 * @version 1.0
 */

public class Panel extends JPanel {
	//archivo 1
	public JTextArea textArea;
	//archivo 2
	public JTextArea textArea2;
	private JScrollPane scrollPane;
	//Pestañas
	public JTabbedPane pestanias;
	// comparación de archivos 
	public JTextArea comparador;


	/**
	 * Create the panel.
	 */
	public Panel() {
		setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		pestanias= new JTabbedPane(JTabbedPane.TOP);
		add(pestanias, BorderLayout.NORTH);


		textArea = new JTextArea();
		pestanias.addTab("Archivo 1", null, textArea, null);
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);

		textArea2 = new JTextArea();
		pestanias.addTab("Archivo 2", null, textArea2, null);
		this.textArea2.setLineWrap(true);
		this.textArea2.setWrapStyleWord(true);
		
		comparador = new JTextArea();
		pestanias.addTab("Comparador de archivo", null, comparador, null);
		//Para que sea solo de lectura
		comparador.setEnabled(false);
		comparador.setBackground(Color.BLACK);
	
		



	}
	//Método Getter y Setter archivo 1
	public String getContenido() {
		return textArea.getText();
	}
	public void setContenido(String contenido) {
		textArea.setText(contenido);
	}
	//Método Getter y Setter archivo 2
	public String getContenido2() {
		return textArea2.getText();
	}
	public void setContenido2(String contenido2) {
		textArea2.setText(contenido2);
	}
	//Método Getter y Setter comparador
	public String getContenidoComparador() {
		return comparador.getText();
	}
	public void setContenidoComparador(String contenido) {
		comparador.setText(contenido);
	}
}
