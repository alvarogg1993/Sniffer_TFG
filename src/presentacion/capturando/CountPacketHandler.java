
package presentacion.capturando;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.*;
/** 
 * Clase CountPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.capturando:
//            Fcaptura

class CountPacketHandler
    implements PacketListener
{

    public CountPacketHandler(Fcaptura RFcaptura)
    {
        ethernet = 0;
        ip = 0;
        arp = 0;
        icmp = 0;
        igmp = 0;
        tcp = 0;
        udp = 0;
        pktotal = 0;
        i = 0;
        contpk = 0;
        this.RFcaptura = RFcaptura;
        pktotal = 0;
        contpk = 0;
    }

    public void packetArrived(Packet packet)
    {
        try
        {
            strNumero = (new StringBuilder("(")).append(Integer.toString(pktotal)).append(") ").toString();
            if((packet instanceof ICMPPacket) || (packet instanceof IGMPPacket) || (packet instanceof TCPPacket) || (packet instanceof UDPPacket))
            {
                if(packet instanceof ICMPPacket)
                {
                    ICMPPacket icmpPacket = (ICMPPacket)packet;
                    icmp++;
                    RFcaptura.pkether((new StringBuilder(String.valueOf(strNumero))).append(icmpPacket.toString()).toString());
                }
                if(packet instanceof IGMPPacket)
                {
                    IGMPPacket igmpPacket = (IGMPPacket)packet;
                    igmp++;
                    RFcaptura.pkIGMP((new StringBuilder(String.valueOf(strNumero))).append(igmpPacket.toString()).toString());
                }
                if(packet instanceof TCPPacket)
                {
                    TCPPacket tcpPacket = (TCPPacket)packet;
                    tcp++;
                    RFcaptura.pkTCP((new StringBuilder(String.valueOf(strNumero))).append(tcpPacket.toString()).toString());
                }
                if(packet instanceof UDPPacket)
                {
                    UDPPacket udpPacket = (UDPPacket)packet;
                    udp++;
                    RFcaptura.pkUDP((new StringBuilder(String.valueOf(strNumero))).append(udpPacket.toString()).toString());
                }
            } else
            if((packet instanceof ARPPacket) || (packet instanceof IPPacket))
            {
                if(packet instanceof ARPPacket)
                {
                    ARPPacket arpPacket = (ARPPacket)packet;
                    arp++;
                    RFcaptura.pkARP((new StringBuilder(String.valueOf(strNumero))).append(arpPacket.toString()).toString());
                }
                if(packet instanceof IPPacket)
                {
                    IPPacket ipPacket = (IPPacket)packet;
                    ip++;
                    RFcaptura.pkIP((new StringBuilder(String.valueOf(strNumero))).append(ipPacket.toString()).toString());
                }
            } else
            if(packet instanceof EthernetPacket)
            {
                EthernetPacket ethernetPacket = (EthernetPacket)packet;
                ethernet++;
                RFcaptura.pkether((new StringBuilder(String.valueOf(strNumero))).append(ethernetPacket.toString()).toString());
            }
            RFcaptura.pkrecibidos(pktotal);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        i++;
        pktotal++;
        contpk++;
    }

    public void erasedates()
    {
        ethernet = 0;
        ip = 0;
        arp = 0;
        icmp = 0;
        igmp = 0;
        tcp = 0;
        udp = 0;
        i = 0;
    }

    public void eraseContParcial()
    {
        contpk = 0;
        RFcaptura.borrarTextArea();
    }

    public int ethernet;
    public int ip;
    public int arp;
    public int icmp;
    public int igmp;
    public int tcp;
    public int udp;
    public int pktotal;
    public int i;
    public int contpk;
    public String strNumero;
    private Fcaptura RFcaptura;
}
