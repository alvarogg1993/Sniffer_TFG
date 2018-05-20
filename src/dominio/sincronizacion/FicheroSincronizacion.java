package dominio.sincronizacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


//import org.jdom2.output.EscapeStrategy;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.channels.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Clase que implementa el fichero de sincronización en el servidor entre las dos aplicaciones.
 * Se utiliza el acceso a ficheros DOM
 * 
 * @author: Nuria González Mata
 * @version: 15/02/2015
 */
public class FicheroSincronizacion {

	//Campos de la clase
	private Element root;
	private final String ruta;
	private File fichero;
	private final boolean esPila;
	private final int tamPila;
	private File fXmlFile;

	private FileLock lock;
	private Document doc = null;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private NodeList itemList;
	private Transformer transformer;
	private FileChannel channel;
	private Element newElement;
	private int prevNumber;
	private TransformerFactory transformerFactory;
	private DOMSource source;
	private StreamResult result;
	private NonClosingInputStream ncis = null;

	/**
	 * Constructor de la clase.
	 * @param s ruta del fichero que queremos generar.
	 * @param circular indica si la pila del fichero va a ser circular
	 * @param tamPila tamaño de la pila de elementos dentro del fichero
	 * @author: Nuria González Mata
	 */
	
	public FicheroSincronizacion(String s, boolean circular, int tamPila) {
		this.ruta = s;
		this.tamPila = tamPila;
		this.esPila = circular;
	}

	/**
	 * Constructor de la clase.
	 * @param f fichero que queremos generar.
	 * @param circular indica si la pila del fichero va a ser circular
	 * @param tamPila tamaño de la pila de elementos dentro del fichero
	 * @author: Nuria González Mata
	 */
	public FicheroSincronizacion(File f,  boolean circular, int tamPila) {
		this.fichero = f;
		this.tamPila = tamPila;
		this.esPila = circular;
		this.ruta = fichero.getAbsoluteFile().toString() + ".xml";
	}
	
	/**
	 * Método que genera la estructura dentro del fichero.
	 * @author: Nuria González Mata
	 */
	public void Generar() throws ParserConfigurationException, SAXException,
			IOException {
		if (esPila) {
			GenerarPila();
		} else {
			GenerarSinPila();
		}

	}

	/**
	 * Crea un fichero de sincronización vacío para el control de la captura sin
	 * elementos. Está pensado para ir añadiendo al final del fichero los nuevos
	 * nodos.
	 * @author: Nuria González Mata
	 */
	public void GenerarSinPila() throws ParserConfigurationException,
			SAXException, IOException {
		boolean fin = false;
		int part = 1;
		int i = 0;

		// Creo una factoria a la que pedir objetos builder
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// Obtengo una instancia
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// Creo el arbol vacio de nodos, pero ya esta la estructura
		doc = dBuilder.newDocument();

		String nombrefichero = ruta;
		// Creamos la raíz del documento
		Element root = doc.createElement("FicheroSincronizacion");
		root.setAttribute("tamPila", "" + tamPila);
		root.setAttribute("topePila", "-1");
		doc.appendChild(root);

		try {
			guardarFichero(false);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Crea un fichero de sincronización vacío para el control de la captura con
	 * tantos elementos fichero_XML vacíos como tamaño de la pila se haya
	 * indicado al instanciar
	 * @author: Nuria González Mata
	 */
	public void GenerarPila() throws ParserConfigurationException,
			SAXException, IOException {
		boolean fin = false;
		int part = 1;
		int i = 0;

		// Creo una factoria a la que pedir objetos builder
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// Obtengo una instancia
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// Creo el arbol vacio de nodos, pero ya esta la estructura
		doc = dBuilder.newDocument();

		String nombrefichero = ruta;
		// Creamos la raíz del documento
		Element root = doc.createElement("FicheroSincronizacion");
		root.setAttribute("tamPila", "" + tamPila);
		root.setAttribute("topePila", "-1");
		doc.appendChild(root);

		for (i = 1; i < tamPila + 1; i++) {
			// Creamos un elemento "fichero_XML"
			Element elemFichero = doc.createElement("fichero_XML");
			elemFichero.setAttribute("Estado", "2");
			elemFichero.setAttribute("Numero", "" + i);

			Element elemNombre = doc.createElement("nombre");
			elemFichero.appendChild(elemNombre);

			Element elemFecha = doc.createElement("fecha_creacion");
			elemFichero.appendChild(elemFecha);

			Element elemIdC = doc.createElement("id_consumidor");
			elemFichero.appendChild(elemIdC);

			root.appendChild(elemFichero);
		}

		try {
			guardarFichero(false);

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}



	/**
	 * Método que abre el fichero XML instanciado para su lectura
	 * 
	 * @trhows ParserConfigurationException
	 * @trhows SAXException
	 * @trhows IOException
	 * @author Nuria González Mata
	 */
	public void abrir2() throws ParserConfigurationException, SAXException,
			IOException {
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			ncis = new NonClosingInputStream(Channels.newInputStream(channel));
			doc = dBuilder.parse(ncis);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que bloquea el fichero para su uso exclusivo por la aplicación.
	 * @return true si se ha bloqueado OK, false si se ha bloqueado NOK
	 */
	public boolean bloquear2() {
		boolean ok = false;
		try{
		channel = new RandomAccessFile(ruta, "rws").getChannel();
		System.out.println("Esperando el desbloqueo");
		FileLock lock = channel.lock(0L, Long.MAX_VALUE, false);
		System.out.println("bloqueado!");
		ok = true;
		}catch (Exception e){
			System.err.println("Error al bloquear el canal");
		}
		return ok;
	}

	/**
	 * Método que desbloquea el fichero para le uso por parte de otras aplicaciones.
	 * @throws IOException
	 */
	public void desbloquear2() throws IOException {
		// CERRAMOS EL CANAL Y LIBERAMOS EL RECURSO
		try {
			ncis.reallyClose();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que guarda el fichero XML instanciado para su lectura
	 * 
	 * @trhows TransformerException
	 * @author Nuria González Mata
	 */
	public void guardarFichero(boolean b) throws TransformerException,
			IOException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		DOMSource source = new DOMSource(doc);
		StreamResult result = null;
		System.out.println("b es:" + b);
		if (!b)
			result = new StreamResult(new File(ruta));
		else {
			result = new StreamResult();
			result.setOutputStream(Channels.newOutputStream(channel));
		}
		transformer.transform(source, result);

	}

	/**
	 * Método que guarda el fichero XML instanciado para su lectura
	 * 
	 * @trhows TransformerException
	 * @author Nuria González Mata
	 */
	public void guardarFichero2(boolean b) throws TransformerException,
			IOException {

		// GUARDAMOS LOS DATOS EN EL FICHERO
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);

		channel.truncate(0);
		result = new StreamResult(Channels.newOutputStream(channel));

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.transform(source, result);
		channel.close();

	}

	/**
	 * Método que abre un fichero XML para su lectura
	 * 
	 * @trhows ParserConfigurationException
	 * @trhows SAXException
	 * @trhows IOException
	 * @return Posición del elemento cuyo atributo "Estado"=1(procesado). -1 en
	 * caso de estar todos a la espera de consumidor.
	 * @author Nuria González Mata
	 */
	public int esPilaLlena() throws ParserConfigurationException, SAXException,
			IOException {
		int i;
		root = doc.getDocumentElement();
		// guardamos en una lista los elementos fichero_XML
		NodeList nLista = root.getElementsByTagName("fichero_XML");

		for (i = 0; i < nLista.getLength(); i++) {
			Element elemento = (Element) nLista.item(i);
			if (Integer.parseInt(elemento.getAttribute("Estado")) == 1) {
				// tenemos uno libre
				return i;
			}
		}

		return -1;
	}
	
	/**
	 * Método que indica si el fichero en una posición concreta ha sido analizado.
	 * @param nodo posición del nodo
	 * @return true si el fichero ha sido consumido, false si aún no
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public boolean esNodoConsumido(int nodo) throws ParserConfigurationException,
			SAXException, IOException {

		root = doc.getDocumentElement(); 

		// guardamos en una lista los elementos fichero_XML
		NodeList nLista = root.getElementsByTagName("fichero_XML");
			Element elemento = (Element) nLista.item(nodo - 1);
			if (Integer.parseInt(elemento.getAttribute("Estado")) == 2) {
				// tenemos uno libre
				return true;
			}
		return false;
	}
	
	
	/**
	 * Método que inserta los datos de un fichero en nuestro fichero de sincronización.
	 * @param i posición [sólo se usa en caso de ser circular]
	 * @param f fichero del cual queremos introducir los datos 
	 * @throws TransformerException
	 * @throws IOException
	 */
	public void insertarNodo(int i, File f) throws TransformerException,
			IOException {
		if (esPila) {
			insertarNodoCircular(i, f);
		} else {
			insertarNodoFinal(f);
		}

	}

	/**
	 * Método que inserta los datos de un fichero en una posición concreta
	 * de nuestro fichero de sincronización.
	 * @param i posición en la que vamos a insertar los datos
	 * @param f fichero del cual queremos introducir los datos.
	 * @throws TransformerException
	 * @throws IOException
	 */
	private void insertarNodoCircular(int i, File f)
			throws TransformerException, IOException {

		// Crear nodo nuevo
		Element raiz = doc.getDocumentElement();
		String tope = raiz.getAttribute("topePila");
		int topeInt = Integer.parseInt(tope);


		Element nuevo = doc.createElement("fichero_XML");
		nuevo.setAttribute("Estado", "0");
		nuevo.setAttribute("Numero", "" + i);

		Element nNombre = doc.createElement("nombre");
		nNombre.setTextContent(f.getPath());

		Date lastModified = new Date(f.lastModified());

		Element nFecha = doc.createElement("fecha_creacion");

		java.util.Date myDate = new java.util.Date(f.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		nFecha.setTextContent(myDate.toLocaleString());
		nFecha.setTextContent(sdf.format(lastModified));

		Element nIdC = doc.createElement("id_consumidor");
		// nIdC.setTextContent(c);

		nuevo.appendChild(nNombre);
		nuevo.appendChild(nFecha);
		nuevo.appendChild(nIdC);

		NodeList lista = doc.getElementsByTagName("fichero_XML");
		Element nodo = (Element) lista.item(i - 1);

		//Comprobamos si el nodo existe
		if (nodo != null){
			raiz.replaceChild(nuevo, nodo);	
		}else{
			raiz.appendChild(nuevo);		
		}
		
		String maximo = raiz.getAttribute("tamPila");
		int maximoInt = Integer.parseInt(maximo);
		topeInt = (topeInt + 1) % maximoInt;
		raiz.setAttribute("topePila", topeInt + "");

		// this.guardarFichero();
	}

	/**
	 * Método que inserta un nodo con los datos del fichero f dentro del 
	 * fichero de sincronización al final del todo.
	 * @param f fichero a introducir
	 * @throws TransformerException
	 * @throws IOException
	 */
	private void insertarNodoFinal(File f) throws TransformerException,
			IOException {
		// Crear nodo nuevo
		Element raiz = doc.getDocumentElement();
		String tope = raiz.getAttribute("topePila");
		int topeInt = Integer.parseInt(tope);
		topeInt = topeInt + 1;

		Element nuevo = doc.createElement("fichero_XML");
		nuevo.setAttribute("Estado", "0");
		nuevo.setAttribute("Numero", "" + topeInt);

		Element nNombre = doc.createElement("nombre");
		nNombre.setTextContent(f.getPath());

		Date lastModified = new Date(f.lastModified());

		Element nFecha = doc.createElement("fecha_creacion");

		java.util.Date myDate = new java.util.Date(f.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		nFecha.setTextContent(myDate.toLocaleString());
		nFecha.setTextContent(sdf.format(lastModified));

		Element nIdC = doc.createElement("id_consumidor");
		// nIdC.setTextContent(c);

		nuevo.appendChild(nNombre);
		nuevo.appendChild(nFecha);
		nuevo.appendChild(nIdC);

		NodeList lista = doc.getElementsByTagName("fichero_XML");
		raiz.appendChild(nuevo);

		raiz.setAttribute("topePila", topeInt + "");

		// this.guardarFichero();

	}

	

	class NonClosingInputStream extends FilterInputStream {

		public NonClosingInputStream(InputStream it) {
			super(it);
		}

		@Override
		public void close() throws IOException {
			// Do nothing.
		}

		public void reallyClose() throws IOException {
			// Actually close.
			in.close();
		}
	}
}
