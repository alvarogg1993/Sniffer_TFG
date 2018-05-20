package dominio.pcapDumper;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import net.sourceforge.jpcap.net.Packet;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import presentacion.visualizarCaptura.PacketHandlerPcapLib;
import presentacion.visualizarCaptura.VisualizarCaptura;
import dominio.FachadaDominio;
import dominio.export.xml_PcapLib.XmlPacketHandler;
import dominio.sincronizacion.FicheroSincronizacion;

/**
 * Clase Captura, interactua pcap para la captura de paquetes.
 * 
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */

public class Captura extends Thread {

	private static final int INFINITE = 0;
	protected static final PcapPacket packet = null;
	private static int PACKET_COUNT;
	protected String devs[];
	protected String filePathCapture;
	private boolean promiscuo;
	private boolean nfssync;
	private boolean xmlcircular;
	private SavePacketHandler SavePH;
	private static OfflineSavePacketHandler OfflineSPH;
	private XmlPacketHandler XmlPH;
	public PacketHandlerPcapLib PacketHPL;
	public SaveFileName sfn;
	private PcapBpfProgram filtro;
	private String FILTER;
	private Vector rawPacket;
	public static Pcap jpcap;
	static Pcap jpcap_write;

	int caplen;
	int flags;
	int timeout;

	static StringBuilder MsgError = new StringBuilder();
	String devices[];

	static boolean isLiveCapture;
	public boolean finSaveMeta;
	boolean typeOffline;
	public boolean finOneFile;
	CountPacketHandler RCountPacketHandler;
	public boolean endCapture;
	private Thread captureThread;

	PcapBpfProgram program = new PcapBpfProgram();
	private ArrayList<PcapIf> TodosDispositivos;
	private String strF;
	protected int i;
	private Thread captureThread2;
	public VisualizarCaptura vc;

	
	public Captura() {
	
		this.filtro = new PcapBpfProgram();
		this.PACKET_COUNT = 0;
		caplen = 64 * 1024; // Capture all packets, no trucation
		flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		timeout = 10 * 1000; // 10 seconds in millis
		setTypeOffline(false);
		this.endCapture = false;
	}

	 /** Metodo donde realiza la capturas de perifericos.
     * @param Sin valor de entrada
     * @return String[] 
      */
	public String[] capDispositivos() {

		TodosDispositivos = new ArrayList<PcapIf>(); // Will be filled with NICs
	
		int NumeroDispositivos = Pcap.findAllDevs(TodosDispositivos, MsgError);
		if (NumeroDispositivos == Pcap.NOT_OK || TodosDispositivos.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"No se encuentra la red.\n System error: errbuf",
					"Warning", 2);

		}
		System.out.println("Aqui estan todos los perifericos");

		devices = new String[TodosDispositivos.size()];
		for (int i = 0; i < TodosDispositivos.size(); i++) {
//			devices[i] = TodosDispositivos.get(i).getDescription() != null ? TodosDispositivos
//					.get(i).getDescription()
//					: TodosDispositivos.get(i).getName();
			
			devices[i] = TodosDispositivos.get(i).getName();
		}
			return devices;
	}

	 /** Metodo donde realiza la apertura de el dispositivo para la captura de paquetes .
     * @param PcapIf devices Devuelve
     * @return Sin valor de retorno
      */
	public void openCaptura(PcapIf devices) throws Exception {
		try {
		

			jpcap = Pcap.openLive(FachadaDominio.dispoName, caplen, flags,
					timeout, MsgError);

			jpcap_write = Pcap.openLive(FachadaDominio.dispoName, caplen,
					flags, timeout, MsgError);

		} catch (Exception e) {
			System.err.println(e.toString());
			throw e;
		}
	}
	/** Metodo donde realiza la apertura de el dispositivo para visualizaci�n
	 * de un archivo pcap .
     * @param String ruta, ruta del archivo a cargar.
     * @return Sin valor de retorno
      */
	public static void openOffline(String ruta) {
		try {
			jpcap = Pcap.openOffline(ruta, MsgError);
			jpcap_write = Pcap.openOffline(ruta, MsgError);
		} catch (Exception nfe) {
			nfe.printStackTrace();
			System.out.println(nfe.getMessage());
		}
	}
	/** Metodo donde realiza la apertura de del dispositivo para la lectura
	 * de un archivo pcap que contiene paquetes .
     * @param String ruta, ruta del archivo a cargar.
     * @return Sin valor de retorno
      */
	public void openOffline_read(String ruta) {
		try {
			
			jpcap = Pcap.openOffline(ruta, MsgError);
		} catch (Exception nfe) {
			nfe.printStackTrace();
		}
	}
	/** Metodo donde realiza la apertura de del dispositivo para la escritura
	 * de un archivo pcap que contiene paquetes .
     * @param String ruta, ruta del archivo a cargar.
     * @return Sin valor de retorno
      */
	public void openOffline_writer(String ruta) {
		try {
		
			jpcap_write = Pcap.openOffline(ruta, MsgError);
		} catch (Exception nfe) {
			nfe.printStackTrace();
		}
	}

	public void run() {
		try {
			
			//System.out.println("El modo de NFS esta: " + nfssync);
			
			SavePacketHandler.setXMLCircular(xmlcircular);
			SavePacketHandler.setNFS(nfssync);
			
			PcapBpfProgram programa = new PcapBpfProgram();
			if (!getTypeOffline()) {
				

				jpcap.setFilter(this.filtro);
			
				this.isLiveCapture = true;
				this.finSaveMeta = false;
				//this.SavePH.setControlPacket(true);//OJOOOO
				
				this.SavePH.runHilos();
				// output file

			
				while (this.isLiveCapture) {
					SavePacketHandler.receivePacket(jpcap /** ,dumper */
					);
					
					if ((SavePacketHandler.aux == 0) && (!this.isLiveCapture)) {
						stopCaptureThread();
					}
					Thread.yield();

				}
				endCapture(true);
			} else {
				
				jpcap.setFilter(this.filtro);
				this.finOneFile = false;
				this.isLiveCapture = true;
				this.finSaveMeta = false;

				
				while (this.isLiveCapture) {
					OfflineSavePacketHandler.receivePacket(jpcap);
					
					if ((OfflineSavePacketHandler.aux == 0) && (!this.isLiveCapture)) {
						stopCaptureThread();
					}
					Thread.yield();
					
				}
				jpcap_write.close();
				this.endCapture = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** Metodo Offfffff()
     * @param String ruta, ruta del archivo a cargar.
     * @return Sin valor de retorno
      */
	public void offfffff() {
		this.finOneFile = false;
		this.isLiveCapture = true;
		this.finSaveMeta = false;

		jpcap.setFilter(this.filtro);
		this.captureThread = new Thread(new Runnable() {
			int aux;

			public void run() {
				while (Captura.this.captureThread != null) {
					if ((Captura.this.OfflineSPH == null)
							&& (Captura.this.isLiveCapture)) {

						Captura.this.stopCaptureThread();
						Captura.this.finOneFile = true;
					}
					Thread.yield();
				}
			}
		});
		this.captureThread.setPriority(1);
		this.captureThread.start();
		while (!this.finOneFile) {
		}
		this.endCapture = true;
	}
	/** Metodo donde realiza la apertura de un archivo de tipo XML para su lectura
     * @param XmlPacketHandler xmlPH.
     * @return Sin valor de retorno
      */
	public void offline_xml(XmlPacketHandler xmlPH) {
		this.XmlPH=xmlPH;
		

		this.finSaveMeta = false;

		setXmlHalder(XmlPH);
				do {
					i = 0;
					
					PcapPacketHandler<String> handler = new PcapPacketHandler<String>() {

						public void nextPacket(PcapPacket packet, String user) {
							XmlPH.receivePacket(packet);
							i = 1;
						}
					};
				jpcap.loop(1, handler, "jNetPcap rocks!");

				} while (i == 1);

	}


	public void setXmlHalder(XmlPacketHandler auxXmlPH) {
		this.XmlPH = auxXmlPH;

	}

	public XmlPacketHandler getXmlHalder() {
		return this.XmlPH;
	}

	public void runHilosCapture() {
		this.OfflineSPH.runHilos(this);
	}

	public void stopCaptureThread() {
		
		captureThread = null;
		isLiveCapture = false;
		
	
	}

	public void endCaptureOffline(boolean tipo) {
		this.endCapture = true;
		this.isLiveCapture = false;
		this.finSaveMeta=true;
		if ((tipo) && (jpcap != null)) {
			jpcap.close();
			jpcap = null;
			jpcap_write.close();
			jpcap_write = null;
			FachadaDominio.saveMetaCapturaOffline();
			this.finSaveMeta = true;
			if (isAlive()) {
				stop();
			}
		}
	}

	public void eCOF() {
		this.endCapture = true;
		if (this.OfflineSPH != null) {
			this.OfflineSPH.stopCaptura();
		}
		jpcap.close();
		jpcap = null;
		jpcap_write.close();
		jpcap_write = null;
		FachadaDominio.saveMetaCapturaOffline();
	}

	public void both_ECOF() {
		this.endCapture = true;
		jpcap.close();
		jpcap_write.close();
		System.out.println("cerradondo fichero");
		jpcap = null;
	}

	public static void eCOFwithoutSaveMeta(Pcap pcap) {

		jpcap = pcap;
		if (OfflineSPH != null) {
			OfflineSPH.stopCaptura();
		}

		if (jpcap != null) {
			jpcap.close();
			jpcap = null;
		}
	}

	public void endCapture(boolean tipo) {
		endCapture = true;
		isLiveCapture=false;
		
		
		
		SavePH.stopCaptura();
		if (SavePH != null) {
			SavePH.stopHilos();
		}
		if (tipo){
			jpcap.close();
			jpcap_write.close();
			FachadaDominio.saveMetaCaptura();
			finSaveMeta = true;
			if (this.isAlive()) {
				this.stop();
			}
			
			
		}
		endCapture = true;
	}
	public void endCaptureCommand() {
		this.endCapture = true;
		this.SavePH.stopCaptura();
		if (this.SavePH != null) {
			this.SavePH.stopHilos();
		}
		jpcap.close();
		jpcap_write.close();
		if (isAlive()) {
			stop();
		}
	}

	public void setListener() {
		String aux = "";
		aux = getFilePathCapture().trim();
		setSFName();
		this.RCountPacketHandler = new CountPacketHandler();
		if (aux.equals("")) {
			this.SavePH = new SavePacketHandler(this, getSFname(),
					this.RCountPacketHandler, jpcap);
		} else {
			this.SavePH = new SavePacketHandler(this, getSFname(),
					this.RCountPacketHandler, jpcap, aux);
		}
		this.SavePH.setWriter(jpcap_write);
		this.SavePH.setNumPacket(getNumPaquetes());
	}

	public void setControlPacket(boolean aux) {
		this.SavePH.setControlPacket(aux);
	}

	public void setListenerOffline() {
		String aux = "";

		aux = FachadaDominio.getPrefBeanFromFile().getffFilLocate();
		setSFName();
		if (aux.equals("")) {
			this.OfflineSPH = new OfflineSavePacketHandler(this, getSFname(),
					jpcap);
		} else {
			this.OfflineSPH = new OfflineSavePacketHandler(this, getSFname(),
					jpcap, aux);
		}
		this.OfflineSPH.setNumPacket(getNumPaquetes());
		}

	public CountPacketHandler getCountPacketHandler() {
		return this.RCountPacketHandler;
	}

	public void setSFName() {
		if (this.sfn == null) {
			this.sfn = new SaveFileName();
		}
	}

	public SaveFileName getSFname() {
		return this.sfn;
	}

	public void setFilePathCapture(String aux) {
		this.filePathCapture = aux;
	}

	public String getFilePathCapture() {
		return this.filePathCapture;
	}

	public void setMFilSpace(String aux) {
		try {
			long lSpace = Long.parseLong(aux);
			this.SavePH.setSpace(lSpace);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public long getMFilSpace() {
		return this.SavePH.getSpace();
	}

	public void setMFilSpaceOffline(String aux) {
		try {
			long lSpace = Long.parseLong(aux);
			this.OfflineSPH.setSpace(lSpace);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public void setMFilTime(String aux) {
		try {
			long lTime = Long.parseLong(aux);
			this.SavePH.setTime(lTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public long getMFilTime() {
		return this.SavePH.getTime();
	}

	public void setMFilPila(String aux) {
		try {
			int iTime = Integer.parseInt(aux);
			this.SavePH.setPila(iTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public void setMFilPilaOffline(String aux) {
		try {
			int iTime = Integer.parseInt(aux);
			this.OfflineSPH.setPila(iTime);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public int getMFilPila() {
		return this.SavePH.getPila();
	}

	public void setMFilStop(String aux) {
		try {
			int iMax = Integer.parseInt(aux);
			this.SavePH.setMaximo(iMax);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public void setMFilStopOffline(String aux) {
		try {
			int iMax = Integer.parseInt(aux);
			this.OfflineSPH.setMaximo(iMax);
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
	}

	public int getMFilStop() {
		return this.SavePH.getMaximo();
	}

	public void setFiltro(PcapBpfProgram filtro2) {
		this.filtro = filtro2;
	}

	public void setPromiscuo(boolean aux) {
		this.promiscuo = aux;
	}

	public boolean getPromiscuo() {
		return this.promiscuo;
	}
	
	public boolean getNFSSync() {
		return this.nfssync;
	}
	
	public void setNFSSync(boolean b) {
		this.nfssync = b;
	}
	
	public void setXMLCircular(boolean b) {
		this.xmlcircular = b;
	}
	
	public boolean getXMLCircular() {
		return this.xmlcircular;
	}
	
	public void setNumPaquetes(String aux) {
		if (aux != "") {
			this.PACKET_COUNT = Integer.parseInt(aux);
		} else {
			this.PACKET_COUNT = 0;
		}
	}

	public static long getNumPaquetes() {
		return PACKET_COUNT;
	}

	public void setPcapV(Vector RawP) {
		this.rawPacket = RawP;
	}

	public Vector getPcapV() {
		return this.rawPacket;
	}

	public void setTypeOffline(boolean aux) {
		this.typeOffline = aux;
	}

	public boolean getTypeOffline() {
		return this.typeOffline;
	}

	public Pcap getPcap() {
		return jpcap;
	}

	public Pcap getPcap_writer() {
		return jpcap_write;
	}

	public boolean getEndCapture() {
		return this.endCapture;
	}
	
}
