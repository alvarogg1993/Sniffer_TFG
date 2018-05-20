
package dominio.pcapDumper.analyzer;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;

//import jpcap.packet.IPPacket;
//import jpcap.packet.Packet;
/** 
 * Clase IPv4Analyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class IPv4Analyzer extends JDPacketAnalyzer 
{
	Ip4 ipPacket = new Ip4();
	InetAddress iaAddress;
	SocketAddress saddress;
	/** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo IP4.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
public IPv4Analyzer()
    {
        values = new Hashtable();
        layer = NETWORK_LAYER;
    }

    public boolean isAnalyzable(PcapPacket p)
    {
    	    	
    	return (p.hasHeader(ipPacket)) &&  (ipPacket.version()==4);

    }

    public String getProtocolName()
    {
        return "IP v4";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }

    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo IP4.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
	@SuppressWarnings("unchecked")
	public void analyze(PcapPacket packet) 
    {
        values.clear();
        if(!isAnalyzable(packet))
        {
            return;
        } else
        {
        	
      
            Ip4 ip = ipPacket;
      
         
          /*
            System.out.println("ipoffset: "+ip.hlen());
            System.out.println("ipoffset: "+ip.hlenDescription());
            */
            
            values.put(valueNames[0], new String(ip.getDescription()));
          values.put(valueNames[1],	new String(ip.tos_CodepointDescription()));
            values.put(valueNames[2], new String(ip.tos_ECEDescription()));
            values.put(valueNames[3], new String(ip.tos_ECNDescription()));
            values.put(valueNames[4], new Integer(ip.getLength()));
            values.put(valueNames[5], new Integer(ip.getId()));
            values.put(valueNames[6], new String(ip.flags_DFDescription()));
            values.put(valueNames[7], new String(ip.flags_MFDescription()));
            values.put(valueNames[8], new Integer(ip.offset()));
            values.put(valueNames[9], new Integer(ip.ttl()));
           values.put(valueNames[10], new String(ip.getName()));
            values.put(valueNames[11], org.jnetpcap.packet.format.FormatUtils.ip(ip.source()));
           values.put(valueNames[12], org.jnetpcap.packet.format.FormatUtils.ip(ip.destination()));
        
            return;
        }
    }

    public Object getValue(String valueName)
    {
        return values.get(valueName);
    }

    Object getValueAt(int index)
    {
        if(index < 0 || index >= valueNames.length)
            return null;
        else
            return getValue(valueNames[index]);
    }

    public Object[] getValues()
    {
        Object v[] = new Object[valueNames.length];
        for(int i = 0; i < valueNames.length; i++)
            v[i] = getValueAt(i);

        return v;
    }

    private static final String valueNames[] = {
        "Version", "TOS: CodePoint ", "TOS: ECN", "TOS: ECE", "Length", "Identification", "Fragment: Don't Fragment", "Fragment: More Fragment", "Fragment Offset", "Time To Live", 
        "Protocol", "Source IP", "Destination IP"
    };
    private Hashtable values;

}
