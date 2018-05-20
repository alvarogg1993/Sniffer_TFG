
package dominio.export.xml;

import net.sourceforge.jpcap.net.IPPort;
import net.sourceforge.jpcap.net.TCPPacket;
import org.jdom.Element;

/** 
 * Clase TCPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class TCPlayer extends Element
{

    public TCPlayer(TCPPacket tcpPacket)
    {
        super("TCP_Protocol");
        TCPflags tcpflags = new TCPflags(tcpPacket);
        try
        {
            addContent((new Element("Port_source")).setText(String.valueOf(tcpPacket.getDestinationPort())));
            addContent((new Element("Destination_port")).setText(IPPort.getName(tcpPacket.getDestinationPort())));
            addContent((new Element("Sequence_Number")).setText(String.valueOf(tcpPacket.getAcknowledgmentNumber())));
            addContent((new Element("Acknowledgment_Number")).setText(String.valueOf(tcpPacket.getAcknowledgmentNumber())));
            addContent((new Element("Header_Length")).setText(String.valueOf(tcpPacket.getTCPHeaderLength())));
            addContent(tcpflags);
            addContent((new Element("Window_Size")).setText(String.valueOf(tcpPacket.getWindowSize())));
            addContent((new Element("Checksum")).setText(String.valueOf(tcpPacket.getChecksum())));
            addContent((new Element("UrgentPointer")).setText(String.valueOf(tcpPacket.getUrgentPointer())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
