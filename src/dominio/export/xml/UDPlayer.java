
package dominio.export.xml;

import net.sourceforge.jpcap.net.IPPort;
import net.sourceforge.jpcap.net.UDPPacket;
import org.jdom.Element;

/** 
 * Clase UDPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class UDPlayer extends Element
{

    public UDPlayer(UDPPacket udpPacket)
    {
        super("UDP_Protocol");
        try
        {
            addContent((new Element("UDP_Port_source")).setText(String.valueOf(udpPacket.getSourcePort())));
            addContent((new Element("UDP_Port_source_name")).setText(IPPort.getName(udpPacket.getSourcePort())));
            addContent((new Element("UDP_Destination_port")).setText(String.valueOf(udpPacket.getDestinationPort())));
            addContent((new Element("UDP_Destination_port_name")).setText(IPPort.getName(udpPacket.getDestinationPort())));
            addContent((new Element("Length")).setText(String.valueOf(udpPacket.getLength())));
            addContent((new Element("Checksum")).setText(String.valueOf(udpPacket.getChecksum())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
