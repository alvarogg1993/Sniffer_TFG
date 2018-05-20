
package dominio.export.xml;

import net.sourceforge.jpcap.net.TCPPacket;
import org.jdom.Element;

/** 
 * Clase TCPflags. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class TCPflags extends Element
{

    public TCPflags(TCPPacket tcpPacket)
    {
        super("TCPflags");
        if(tcpPacket.isUrg())
            addContent((new Element("URG")).setText(Integer.toHexString(tcpPacket.getUrgentPointer())));
        if(tcpPacket.isAck())
            addContent((new Element("ACK")).setText(Long.toHexString(tcpPacket.getAcknowledgmentNumber())));
        if(tcpPacket.isPsh())
            addContent(new Element("PSH"));
        if(tcpPacket.isRst())
            addContent(new Element("RST"));
        if(tcpPacket.isSyn())
            addContent((new Element("SYN")).setText(Long.toHexString(tcpPacket.getSequenceNumber())));
        if(tcpPacket.isFin())
            addContent(new Element("FIN"));
    }
}
