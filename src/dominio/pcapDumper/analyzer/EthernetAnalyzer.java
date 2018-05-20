package dominio.pcapDumper.analyzer;

import javax.xml.transform.Source;

import org.jnetpcap.packet.PcapPacket;

//import jpcap.packet.EthernetPacket;
//import jpcap.packet.Packet;
import org.jnetpcap.packet.JHeader;
import org.jnetpcap.packet.annotate.Protocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.lan.Ethernet.EthernetType;

/**
 * Clase EthernetAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */


public class EthernetAnalyzer extends JDPacketAnalyzer {

	Ethernet ethernetPacket = new Ethernet();

	public EthernetAnalyzer() {
		layer = DATALINK_LAYER;
	}
	/** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo Ethernet.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
	public boolean isAnalyzable(PcapPacket packet) {
		return !packet.equals(null) && packet.hasHeader(ethernetPacket);
	}

	public String getProtocolName() {
		return "Ethernet Frame";
	}

	public String[] getValueNames() {
		return valueNames;
	}
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo Ethernet.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
	public void analyze(PcapPacket packet) {
		if (!isAnalyzable(packet)) {
			return;
		} else {
			eth = ethernetPacket;
			return;

		}
	}

	public Object getValue(String valueName) {
		for (int i = 0; i < valueNames.length; i++)
			if (valueNames[i].equals(valueName))
				return getValueAt(i);

		return null;
	}

	Object getValueAt(int index) {
		switch (index) {
		case 0: // '\0'
//		
			return new Integer(eth.type());
		case 1: // '\001'
			
			 return org.jnetpcap.packet.format.FormatUtils.mac(eth.source());
			 
			
		case 2: // '\002'
			
			return org.jnetpcap.packet.format.FormatUtils.mac(eth.destination());
		}
		return null;
	}

	public Object[] getValues() {
		Object v[] = new Object[3];
		for (int i = 0; i < 3; i++)
			v[i] = getValueAt(i);

		return v;
	}

	private static final String valueNames[] = { "Frame Type", "Source MAC",
			"Destination MAC" };
	
	private Ethernet eth;

}
