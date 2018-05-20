

package dominio.export.xml;

import net.sourceforge.jpcap.net.*;

/** 
 * Clase XmlHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

class XmlHandler
{

    public XmlHandler(CrearXML RCrearXML)
    {
        this.RCrearXML = RCrearXML;
    }

    public void packetarrived(Packet packet)
    {
        try
        {
            if(packet instanceof EthernetPacket)
            {
                EthernetPacket ethernetPacket = (EthernetPacket)packet;
                RCrearXML.Etherlayer(ethernetPacket);
            }
            if(packet instanceof ARPPacket)
            {
                ARPPacket arpPacket = (ARPPacket)packet;
                RCrearXML.ARPlayer(arpPacket);
            }
            if(packet instanceof IPPacket)
            {
                IPPacket ipPacket = (IPPacket)packet;
                RCrearXML.IPlayer(ipPacket);
            }
            if(packet instanceof ICMPPacket)
            {
                ICMPPacket icmpPacket = (ICMPPacket)packet;
                RCrearXML.ICMPlayer(icmpPacket);
            }
            if(packet instanceof IGMPPacket)
            {
                IGMPPacket igmpPacket = (IGMPPacket)packet;
                RCrearXML.IGMPlayer(igmpPacket);
            }
            if(packet instanceof TCPPacket)
            {
                TCPPacket tcpPacket = (TCPPacket)packet;
                RCrearXML.TCPlayer(tcpPacket);
            }
            if(packet instanceof UDPPacket)
            {
                UDPPacket udpPacket = (UDPPacket)packet;
                RCrearXML.UDPlayer(udpPacket);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private CrearXML RCrearXML;
}
