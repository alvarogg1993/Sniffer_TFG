
package dominio.pcapDumper.analyzer;


import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;

import org.jnetpcap.protocol.network.Ip6;

//import jpcap.packet.IPPacket;
//import jpcap.packet.Packet;
/** 
 * Clase IPv6Analyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class IPv6Analyzer extends JDPacketAnalyzer
{
	Ip6 ipPacket = new Ip6();
    public IPv6Analyzer()
    {
        values = new Hashtable();
        layer = NETWORK_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo IP6.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
    	return p.hasHeader(ipPacket) && ipPacket.version()==6;
    }

    public String getProtocolName()
    {
        return "IP v6";
    }
    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo IP6.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
    public void analyze(PcapPacket packet)
    {
        values.clear();
        if(!isAnalyzable(packet))
        {
            return;
        } else
        {
        	Ip6 ip = ipPacket;
            
        	
        	values.put(valueNames[0], new Integer(ip.version()));
            values.put(valueNames[1], new Integer(ip.trafficClass()));
            values.put(valueNames[2], new Integer(ip.flowLabel()));
            values.put(valueNames[3], new Integer(ip.length()));
            values.put(valueNames[4], new String(ip.getName()));
            values.put(valueNames[5], new Integer(ip.hopLimit()));
            values.put(valueNames[6], org.jnetpcap.packet.format.FormatUtils.ip(ip.source()));
            values.put(valueNames[7], org.jnetpcap.packet.format.FormatUtils.ip(ip.destination()));
//            values.put(valueNames[8], ip.src_ip.getHostName());
//            values.put(valueNames[9], ip.dst_ip.getHostName());
           // values.put(valueNames[1], new Integer(ip.priority));
//          values.put(valueNames[2], new Integer(ip.flow_label));
//          values.put(valueNames[3], new Integer(ip.length));
//          values.put(valueNames[4], new Integer(ip.protocol));
//          values.put(valueNames[5], new Integer(ip.hop_limit));
//          values.put(valueNames[6], ip.src_ip.getHostAddress());
//          values.put(valueNames[7], ip.dst_ip.getHostAddress());
//          values.put(valueNames[8], ip.src_ip.getHostName());
//          values.put(valueNames[9], ip.dst_ip.getHostName());
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
            return values.get(valueNames[index]);
    }

    public Object[] getValues()
    {
        Object v[] = new Object[valueNames.length];
        for(int i = 0; i < valueNames.length; i++)
            v[i] = values.get(valueNames[i]);

        return v;
    }

    private static final String valueNames[] = {
        "Version", "Traffic Class", "Flow Label", "Length", "Protocol", "Hop Limit", "Source IP", "Destination IP"
    };
    Hashtable values;

}
