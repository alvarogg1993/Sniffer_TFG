

package dominio.export.xml;

import net.sourceforge.jpcap.net.IPPacket;
import net.sourceforge.jpcap.net.IPProtocol;
import org.jdom.Element;

/** 
 * Clase IPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class IPlayer extends Element
{

    public IPlayer(IPPacket ipPacket)
    {
        super("IP_Protocol");
        sourceAddress = ipPacket.getSourceAddress();
        destinationAddress = ipPacket.getDestinationAddress();
        try
        {
            addContent((new Element("Source_IP_Address")).setText(sourceAddress));
            addContent((new Element("Destination_IP_Address")).setText(destinationAddress));
            addContent((new Element("Headler_Length")).setText(String.valueOf(ipPacket.getHeaderLength())));
            addContent((new Element("Version")).setText(String.valueOf(ipPacket.getVersion())));
            addContent((new Element("Type")).setText(String.valueOf(ipPacket.getTypeOfService())));
            addContent((new Element("TTL")).setText(String.valueOf(ipPacket.getTimeToLive())));
            addContent((new Element("Protocol")).setText(String.valueOf(ipPacket.getIPProtocol())));
            addContent((new Element("Protocol_Description")).setText(IPProtocol.getDescription(ipPacket.getIPProtocol())));
            addContent((new Element("ID")).setText(String.valueOf(ipPacket.getId())));
            addContent((new Element("Length")).setText(String.valueOf(ipPacket.getLength())));
            addContent((new Element("Flags")).setText(String.valueOf(ipPacket.getFragmentFlags())));
            addContent((new Element("Offset")).setText(String.valueOf(ipPacket.getFragmentOffset())));
            addContent((new Element("Check_Sum")).setText(String.valueOf(ipPacket.getChecksum())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected String sourceAddress;
    protected String destinationAddress;
}
