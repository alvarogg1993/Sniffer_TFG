

package dominio.export.xml;

import net.sourceforge.jpcap.net.IGMPMessage;
import net.sourceforge.jpcap.net.IGMPPacket;
import org.jdom.Element;

/** 
 * Clase IGMPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class IGMPlayer extends Element
{

    public IGMPlayer(IGMPPacket igmpPacket)
    {
        super("IGMP_Protocol");
        try
        {
            addContent((new Element("Type")).setText(IGMPMessage.getDescription(igmpPacket.getMessageType())));
            addContent((new Element("Group_address")).setText(igmpPacket.getGroupAddress()));
            addContent((new Element("max_response_time")).setText(String.valueOf(igmpPacket.getMaxResponseTime())));
            addContent((new Element("Checksum")).setText(String.valueOf(igmpPacket.getChecksum())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected String sourceAddress;
    protected String destinationAddress;
}
