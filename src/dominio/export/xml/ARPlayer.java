
package dominio.export.xml;

import net.sourceforge.jpcap.net.ARPPacket;
import org.jdom.Element;
/** 
 * Clase ARPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ARPlayer extends Element
{

    public ARPlayer(ARPPacket arpPacket)
    {
        super("Address_Resolution_Protocol");
        addContent((new Element("Operation_ARP")).setText(arpPacket.getOperation() != 1 ? "Reply" : "Request"));
        addContent((new Element("Source_Proto_Address")).setText(arpPacket.getSourceProtoAddress()));
        addContent((new Element("Hardware_source_address")).setText(arpPacket.getSourceHwAddress()));
        addContent((new Element("Destination_Proto_Address")).setText(arpPacket.getDestinationProtoAddress()));
        addContent((new Element("Hardware_destination_address")).setText(arpPacket.getDestinationHwAddress()));
        addContent((new Element("Operation_code")).setText(String.valueOf(arpPacket.getOperation())));
    }

    protected String sourceAddress;
    protected String destinationAddress;
}
