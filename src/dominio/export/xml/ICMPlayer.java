
package dominio.export.xml;

import net.sourceforge.jpcap.net.ICMPMessage;
import net.sourceforge.jpcap.net.ICMPPacket;
import org.jdom.Element;

/** 
 * Clase ICMPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ICMPlayer extends Element
{

    public ICMPlayer(ICMPPacket icmpPacket)
    {
        super("ICMP_Protocol");
        sourceAddress = icmpPacket.getSourceAddress();
        destinationAddress = icmpPacket.getDestinationAddress();
        try
        {
            addContent((new Element("Type")).setText(ICMPMessage.getDescription(icmpPacket.getMessageCode())));
            addContent((new Element("Code")).setText(String.valueOf(icmpPacket.getMessageCode())));
            addContent((new Element("Checksum")).setText(String.valueOf(icmpPacket.getChecksum())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected String sourceAddress;
    protected String destinationAddress;
    protected String dataPacket;
}
