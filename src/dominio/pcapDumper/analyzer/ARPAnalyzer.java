
package dominio.pcapDumper.analyzer;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Arp;


/** 
 * Clase ARPAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/


public class ARPAnalyzer extends JDPacketAnalyzer
{
	Arp arpacket = new Arp();
    public ARPAnalyzer()
    {
    
        layer = NETWORK_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ARP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
        return p.hasHeader(arpacket);
    }

    public String getProtocolName()
    {
        return "ARP RARP";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo ARP.
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
            arp = arpacket;
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

    Object getValueAt(int index)
    {
        switch(index)
        {
        case 0: // '\0'
            switch(arp.hardwareType())
            {
            case 1: // '\001'
                return (new StringBuilder("Ethernet (")).append(arp.hardwareType()).append(")").toString();

            case 6: // '\006'
                return (new StringBuilder("Token ring (")).append(arp.hardwareType()).append(")").toString();

            case 15: // '\017'
                return (new StringBuilder("Frame relay (")).append(arp.hardwareType()).append(")").toString();
            }
            return new Integer(arp.hardwareType());

        case 1: // '\001'
            switch(arp.protocolType())
            {
            case 2048: 
                return (new StringBuilder("IP (")).append(arp.protocolType()).append(")").toString();
            }
            return new Integer(arp.protocolType());

        case 2: // '\002'
            return new Integer(arp.hlen());

        case 3: // '\003'
            return new Integer(arp.plen());

        case 4: // '\004'
            switch(arp.operation())
            {
            case 1: // '\001'
                return "ARP Request";

            case 2: // '\002'
                return "ARP Reply";

            case 3: // '\003'
                return "Reverse ARP Request";

            case 4: // '\004'
                return "Reverse ARP Reply";

            case 8: // '\b'
                return "Identify peer Request";

            case 9: // '\t'
                return "Identify peer Reply";

            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                return new Integer(arp.operation());
            }

        case 5: // '\005'
            return arp.sha();

        case 6: // '\006'
            return arp.spa();

        case 7: // '\007'
            return arp.tha();

        case 8: // '\b'
            return arp.tpa();
        }
        return null;
    }

    public Object[] getValues()
    {
        Object v[] = new Object[valueNames.length];
        for(int i = 0; i < valueNames.length; i++)
            v[i] = getValueAt(i);

        return v;
    }

    private static final String valueNames[] = {
        "Hardware Type", "Protocol Type", "Hardware Address Length", "Protocol Address Length", "Operation", "Sender Hardware Address", "Sender Protocol Address", "Target Hardware Address", "Target Protocol Address"
    };
    private Arp arp;

}
