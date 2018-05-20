
package dominio.statistics;

import net.sourceforge.jpcap.net.*;
/** 
 * Clase StPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.statistics:
//            Estadisticas

class StPacketHandler
{

    public StPacketHandler(Packet packet)
    {
        i = 0;
        i++;
        try
        {
            if((packet instanceof ICMPPacket) || (packet instanceof IGMPPacket) || (packet instanceof TCPPacket) || (packet instanceof UDPPacket))
            {
                if(packet instanceof ICMPPacket)
                {
                    ICMPPacket icmpPacket = (ICMPPacket)packet;
                    dominio.statistics.Estadisticas.ICMPPacket(icmpPacket);
                }
                if(packet instanceof IGMPPacket)
                {
                    IGMPPacket igmpPacket = (IGMPPacket)packet;
                    dominio.statistics.Estadisticas.IGMPPacket(igmpPacket);
                }
                if(packet instanceof TCPPacket)
                {
                    TCPPacket tcpPacket = (TCPPacket)packet;
                    dominio.statistics.Estadisticas.TCPPacket(tcpPacket);
                }
                if(packet instanceof UDPPacket)
                {
                    UDPPacket udpPacket = (UDPPacket)packet;
                    dominio.statistics.Estadisticas.UDPPacket(udpPacket);
                }
            } else
            if((packet instanceof ARPPacket) || (packet instanceof IPPacket))
            {
                if(packet instanceof ARPPacket)
                {
                    ARPPacket arpPacket = (ARPPacket)packet;
                    dominio.statistics.Estadisticas.ARPPacket(arpPacket);
                }
                if(packet instanceof IPPacket)
                {
                    IPPacket ipPacket = (IPPacket)packet;
                    dominio.statistics.Estadisticas.IPPacket(ipPacket);
                }
            } else
            if(packet instanceof EthernetPacket)
            {
                EthernetPacket ethernetPacket = (EthernetPacket)packet;
                Estadisticas.EtherPacket(ethernetPacket);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int i;
}
