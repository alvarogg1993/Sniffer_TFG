
package dominio.pcapDumper.analyzer;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Udp;

//import jpcap.packet.Packet;
//import jpcap.packet.UDPPacket;
/** 
 * Clase UDPAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class UDPAnalyzer extends JDPacketAnalyzer
{
Udp udpacket = new Udp();
private Udp udp;

    public UDPAnalyzer()
    {
    	
        layer = TRANSPORT_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ARP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
        return p.hasHeader(udpacket);
    }

    public String getProtocolName()
    {
        return "UDP";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo UDP.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
    public void analyze(PcapPacket p)
    {
        if(!isAnalyzable(p))
        {
            return;
        } else
        {
            udp = udpacket;
            return;
        }
    }

    public Object getValue(String valueName)
    {
        for(int i = 0; i < valueNames.length; i++)
            if(valueNames[i].equals(valueName))
                return getValueAt(i);

        return null;
    }

    public Object getValueAt(int index)
    {
        switch(index)
        {
        case 0: // '\0'

            return new Integer(udp.source());


        case 1: // '\001'
            return new Integer(udp.destination());


        case 2: // '\002'
            return new Integer(udp.getLength());

                }
        return null;
    }

    public Object[] getValues()
    {
        Object v[] = new Object[3];
        for(int i = 0; i < 3; i++)
            v[i] = getValueAt(i);

        return v;
    }

    private static final String valueNames[] = {
        "Source Port", "Destination Port", "Packet Length"
    };
   

}
