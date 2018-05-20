package dominio.pcapDumper.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;

/**
 * Clase HTTPAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
public class HTTPAnalyzer extends JDPacketAnalyzer {
	private static final String[] valueNames = { "Method", "Header" };
	String method;
	Vector headers = new Vector();
	Tcp tcppacket = new Tcp();
	public HTTPAnalyzer() {
		this.layer = APPLICATION_LAYER;
	}
	/** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ARP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
	public boolean isAnalyzable(PcapPacket p) {
		if (p.hasHeader(tcppacket) && (tcppacket.source() == 80 || tcppacket.destination() == 80))
		{
			return true;
		}
		return false;
	}

	public String getProtocolName() {
		return "HTTP";
	}

	public String[] getValueNames() {
		return valueNames;
	}
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo HTTP.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
	public void analyze(PcapPacket p) {
		this.method = "";
		this.headers.removeAllElements();
		if (!isAnalyzable(p)) {
			return;
		}
		try {
			BufferedReader in = new BufferedReader(new StringReader(new String(p.toString())));

			this.method = in.readLine();
			if ((this.method == null) || (this.method.indexOf("HTTP") == -1)) {
				this.method = "Not HTTP Header";
				return;
			}
			String l;
			while ((l = in.readLine()).length() > 0) {
				
				this.headers.addElement(l);
			}
		} catch (IOException localIOException) {
		} catch (Exception localException) {
		}
	}

	public Object getValue(String valueName) {
		if (valueNames[0].equals(valueName)) {
			return this.method;
		}
		if (valueNames[1].equals(valueName)) {
			return this.headers;
		}
		return null;
	}

	Object getValueAt(int index) {
		if (index == 0) {
			return this.method;
		}
		if (index == 1) {
			return this.headers;
		}
		return null;
	}

	public Object[] getValues() {
		Object[] values = new Object[2];
		values[0] = this.method;
		values[1] = this.headers;

		return values;
	}
}
