package presentacion.visualizarCaptura;


import dominio.pcap.rules.*;
import dominio.pcapDumper.Captura;
import java.awt.*;
import java.io.File;
import java.util.Collection;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/** 
 * Clase Sniffer, Clase Principal. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
*/


public class VisualizarCaptura extends JPanel implements Runnable {

	private Thread captureThread2;
	private boolean finOneFile;
	protected boolean isLiveCapture;
	protected int i;
	private boolean finSaveMeta;

	public VisualizarCaptura(Mediador med) {
		contadorarbol = 0;
		hilo = null;
		mediador = med;
		mediador.setPanelEstado("Cargado datos desde fichero");
		VectorConexiones = new Vector();
		history = new Vector();
		VentanaCaptura();
	}

	public void VentanaCaptura() {
		Label = new JLabel();
		Label.setIcon(new ImageIcon((new StringBuilder(String
				.valueOf(FachadaFicheroDirectorios
						.getdirectorioData("DIR_IMAGES")))).append(
				System.getProperty("file.separator")).append("tcp.png")
				.toString()));
		Label.setText("Conexiones TCP");
		TablaPaquetes = new TablePane(this);
		TablaConexiones = new TableConexions(TablaPaquetes);
		Paneaux = new JPanel();
		Paneaux2 = new JPanel();
		Paneauxtree1 = new JPanel();
		Paneauxtree2 = new JPanel();
		Paneauxtree3 = new JPanel();
		Paneauxtree = new JPanel();
		Paneconexiones = new JPanel();
		Paneaux.setLayout(new GridLayout(0, 1));
		Paneaux2.setLayout(new GridLayout(0, 1));
		Paneauxtree.setLayout(new GridLayout(1, 3));
		Paneauxtree1.setLayout(new BorderLayout());
		Paneauxtree2.setLayout(new BorderLayout());
		Paneauxtree3.setLayout(new BorderLayout());
		Paneauxtree.add(Paneauxtree1);
		Paneauxtree.add(Paneauxtree2);
		Paneauxtree.add(Paneauxtree3);
		Paneconexiones.setBorder(new BevelBorder(0));
		Paneconexiones.setLayout(new BorderLayout());
		output = new JTextArea(37, 45);
		output2 = new JTextArea(12, 60);
		output2.setBackground(new Color(0, 0, 0));
		output2.setForeground(new Color(51, 255, 0));
		output.setEditable(false);
		output2.setEditable(false);
		scrollPane = new JScrollPane(TablaPaquetes.Tabla());
		scrollPane2 = new JScrollPane(TablaConexiones.Tabla());
		Paneconexiones.add(Label, "North");
		Paneconexiones.add(scrollPane2, "Center");
		JSplitPane splitPane = new JSplitPane(0);
		splitPane.setTopComponent(scrollPane);
		splitPane.setBottomComponent(Paneconexiones);
		splitPane.setDividerLocation(200);
		Paneaux.add(splitPane);
		JSplitPane splitPane2 = new JSplitPane(0);
		splitPane2.setTopComponent(Paneaux);
		splitPane2.setBottomComponent(Paneauxtree);
		splitPane2.setDividerLocation(300);
		Paneaux2.add(splitPane2);
		Paneaux2.setPreferredSize(new Dimension(1023, 760));

	}

	public JPanel getPanel() {
		return Paneaux2;
	}

	public void run() {
		resetGraficos();

		AbrirFile(getFile());
		
	}

	public void setFile(String aux) {
		fichero = aux;
	}

	public String getFile() {
		return fichero;
	}

	public void resetGraficos() {
		VectorConexiones.clear();
		history.clear();
		TablaPaquetes.clearTable();
		TablaConexiones.clearTable();
		Paneauxtree1.removeAll();
		Paneauxtree2.removeAll();
		Paneauxtree3.removeAll();
		contadorarbol = 0;
	}

	public int getLinkLayer() {
		int Linklayer = -1;
		return Linklayer;
	}
    /** Metodo  se abre el fichero pcap con capturas para su visualización.
     * @param String nombre 
     * @return sin valor de retorno
     */
	public void AbrirFile(String nombre) {
		String fichero = nombre;
		System.out.println((new StringBuilder("1............")).append(fichero)
				.toString());
		PacketHandlerPcapLib PacketHPL = new PacketHandlerPcapLib(this,
				TablaPaquetes);
		try {
				abrirFichero();
			System.out.println("5............");
			AddConexionesTabla();
			System.err
					.println((new StringBuilder("Establecio fichero leido => "))
							.append(fichero).toString());
			mediador.setFileReaded(fichero);
			mediador.getVentanaMenuSniffer().dispose();
			Captura.eCOFwithoutSaveMeta(pcap);
			System.out.println("...paso");
			if (Reglas != null)
				log.CrearLog();
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
	}

	private void abrirFichero() {
		isLiveCapture = true;
		StringBuilder errbuf = new StringBuilder();
		pcap = Pcap.openOffline(fichero, errbuf);

		do {
			i = 0;
			PcapPacketHandler<String> handler = new PcapPacketHandler<String>() {

				public void nextPacket(PcapPacket packet, String user) {
					PacketHandlerPcapLib.nextPacket(packet);
					i = 1;
				}
			};
			pcap.loop(1, handler, "jNetPcap rocks!");

		} while (i == 1 && (VisualizarCaptura.this.isLiveCapture));
		
	}

	public void CrearArbol(int numero) {
		contadorarbol++;
		PcapPacket paquete = (PcapPacket) history.get(numero);
		Border emptyBorder = BorderFactory.createLoweredBevelBorder();
		Border selectBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.red);
		Arbol = new TreePacket(numero, paquete);
		scrollPane3 = new JScrollPane(Arbol.Arbol());
		scrollPane3.setBorder(selectBorder);
		if (contadorarbol == 1) {
			Paneauxtree1.removeAll();
			Paneauxtree1.add(scrollPane3, "Center");
			if (Paneauxtree3.getComponentCount() > 0) {
				JScrollPane auxJSPanel = (JScrollPane) Paneauxtree3
						.getComponent(0);
				auxJSPanel.setBorder(emptyBorder);
			}
		} else if (contadorarbol == 2) {
			Paneauxtree2.removeAll();
			Paneauxtree2.add(scrollPane3, "Center");
			if (Paneauxtree2.getComponentCount() > 0) {
				JScrollPane auxJSPanel = (JScrollPane) Paneauxtree1
						.getComponent(0);
				auxJSPanel.setBorder(emptyBorder);
			}
		} else if (contadorarbol == 3) {
			Paneauxtree3.removeAll();
			Paneauxtree3.add(scrollPane3, "Center");
			contadorarbol = 0;
			if (Paneauxtree2.getComponentCount() > 0) {
				JScrollPane auxJSPanel = (JScrollPane) Paneauxtree2
						.getComponent(0);
				auxJSPanel.setBorder(emptyBorder);
			}
		}
		mediador.repaintVentana(mediador.getVentanaMenuSniffer());
	}

	public void dumpHistory() {
	}

	public void refreshCapture() {
		Paneaux.repaint();
		Paneaux2.repaint();
		repaint();
		System.out.println("refres...paso");
	}

	public void LoadFileRules(File nombre) {
		try {
			String nombreficheroreglas = (new StringBuilder()).append(
					nombre.getAbsoluteFile()).toString();
			if (nombreficheroreglas.indexOf(".rules") == -1)
				throw new Exception("Fichero no de reglas");
			if (Reglas == null) {
				log = new XMLlog(1);
				TablaAlertas = new TableAlerts();
				FAlertsLoad = new FAlertsLoad();
				Reglas = new Rules(TablaAlertas, log);
			}
			Reglas.LoadRules(nombre);
			FAlertsLoad.DatosTablaAlertsLoad(nombre.getAbsolutePath());
			Reglas.OrdenarReglas();
			JOptionPane.showMessageDialog(frameinf, (new StringBuilder(
					"Fichero de reglas ")).append(nombreficheroreglas).append(
					" añadido").toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frameinf,
					"Formato incorrecto de fichero de reglas");
		}
	}

	public Rules getReglas() {
		return Reglas;
	}

	public void clearHistory() {

		history.clear();
	}

	public static void addpackethistory(PcapPacket packet) {

		Paneaux2.repaint();

		history.add(packet);
	}

	public Vector getConexionesTCP() {
		return VectorConexiones;
	}

	public void DatosConexion(String IpOrigen, String IpDestino,
			int PuertoOrigen, int PuertoDestino, int numeropaquete) {
		int i = 0;
		boolean existente = false;
		NuevaConexion = new Conexion(IpOrigen, IpDestino, PuertoOrigen,
				PuertoDestino);
		if (VectorConexiones.size() != 0) {
			for (; i < VectorConexiones.size() && !existente; i++) {
				Conexion objetoConexion = (Conexion) VectorConexiones
						.elementAt(i);
				if ((objetoConexion.getIpSrc().compareTo(IpOrigen) == 0
						&& objetoConexion.getIpDest().compareTo(IpDestino) == 0 || objetoConexion
						.getIpSrc().compareTo(IpDestino) == 0
						&& objetoConexion.getIpDest().compareTo(IpOrigen) == 0)
						&& (objetoConexion.getPuertoSrc() == PuertoOrigen
								&& objetoConexion.getPuertoDst() == PuertoDestino || objetoConexion
								.getPuertoSrc() == PuertoDestino
								&& objetoConexion.getPuertoDst() == PuertoOrigen)) {
					objetoConexion.addpaquete(numeropaquete);
					existente = true;
				}
			}

			if (i == VectorConexiones.size() && !existente) {
				NuevaConexion.addpaquete(numeropaquete);
				VectorConexiones.add(NuevaConexion);
			}
		} else {
			NuevaConexion.addpaquete(numeropaquete);
			VectorConexiones.add(NuevaConexion);
		}
	}

	public void AddConexionesTabla() {
		int i = 0;
		for (i = 0; i < VectorConexiones.size(); i++) {
			Conexion objetoConexion = (Conexion) VectorConexiones.elementAt(i);
			TablaConexiones.DatosTablaConexion(String.valueOf(i),
					objetoConexion.getTimeEstablecimiento(), objetoConexion
							.getIpSrc(), objetoConexion.getIpDest(), String
							.valueOf(objetoConexion.getPuertoSrc()), String
							.valueOf(objetoConexion.getPuertoDst()), String
							.valueOf(objetoConexion.getnumeropaquetes()));
		}

	}

	public String leerPTV() {
		return mediador.leerPropertiesTableView();
	}

	public String getColumnPosition() {
		return TablaPaquetes.obtenerOrdenColumnas();
	}

	private Vector VectorConexiones;
	private Rules Reglas;
	private XMLlog log;
	private Vector VectorPacket;
	protected Conexion NuevaConexion;
	private int contadorarbol;
	public static Vector history;
	protected JFrame frameinf;
	protected Pcap paquete;
	public boolean visualframe;
	protected Pcap pcap;
	protected Pcap pcapLectura;
	private String fichero;
	protected Thread hilo;
	protected Collection Historial;
	public static TablePane TablaPaquetes;
	private TableConexions TablaConexiones;
	public TableAlerts TablaAlertas;
	public FAlertsLoad FAlertsLoad;
	public Mediador mediador;
	private TreePacket Arbol;
	public Vector x;
	public JTabbedPane jTabbedPane1;
	public static JPanel Paneaux;
	public static JPanel Paneaux2;
	public JPanel Paneauxtree;
	public JPanel Paneauxtree1;
	public JPanel Paneauxtree2;
	public JPanel Paneauxtree3;
	public JPanel Paneconexiones;
	public JTextArea output;
	public JTextArea output2;
	public JScrollPane scroll;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane2;
	public JScrollPane scrollPane3;
	public JSplitPane splitPane;
	public JSplitPane splitPane2;
	public JToolBar toolBar;
	public JSeparator jSeparator;
	public JLabel Label;
	// public static SavePacketHandler ficheroxmlenconstruccion;
}
