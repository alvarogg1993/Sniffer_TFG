
package dominio.export.xml;

import net.sourceforge.jpcap.net.EthernetPacket;
import net.sourceforge.jpcap.util.Timeval;
import org.jdom.Element;

/** 
 * Clase Etherlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Etherlayer extends Element
{

    public Etherlayer(EthernetPacket ethernetPacket)
    {
        super("Ethernet_Layer");
        sourceAddress = ethernetPacket.getSourceHwAddress();
        destinationAddress = ethernetPacket.getDestinationHwAddress();
        addContent((new Element("Source_Hardware_Address")).setText(sourceAddress));
        addContent((new Element("Destination_Hardware_Address")).setText(destinationAddress));
        addContent((new Element("Type")).setText(String.valueOf(ethernetPacket.getEthernetProtocol())));
        addContent((new Element("Time_arrived")).setText(ethernetPacket.getTimeval().toString()));
    }

    protected String sourceAddress;
    protected String destinationAddress;
}
