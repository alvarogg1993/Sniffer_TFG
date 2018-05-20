
package presentacion.visualizarCaptura;

import dominio.FachadaDominio;
import dominio.pcap.Captura;
import dominio.pcap.rules.Rules;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.jnetpcap.packet.PcapPacket;
import net.sourceforge.jpcap.net.*;
import net.sourceforge.jpcap.net.LinkLayer;
import net.sourceforge.jpcap.capture.PacketListener;
/** 
 * Clase FPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.visualizarCaptura:
//            VisualizarCaptura, TablePane

class PacketHandler implements PacketListener
{

    public PacketHandler(VisualizarCaptura venpadre, TablePane RTablePane, Rules Reglas)
    {
        i = 0;
        this.venpadre = venpadre;
        this.RTablePane = RTablePane;
        this.Reglas = Reglas;
    }

	/*@Override
	public void packetArrived(Packet packet) {
		// TODO Auto-generated method stub
		
	}*/
    
    public void packetArrived(Packet packet)
    {
        String macsource = "----";
        String macdest = "----";
        String frame = "----";
        String protocol = "---- ";
        String desc_protocol_short = "";
        String ipsrc = "NO IP Address";
        String ipdest = "NO IP Address";
        String portsrc = "----";
        String portdest = "----";
        String seq = "----";
        String ack = "----";
        String length = "----";
        String itype = "";
        String icode = "";
        String dsize = "";
        String id = "";
        String ttl = "";
        String flags = "";
        try
        {
            FachadaDominio.getPcap().addpackethistory(packet);
        	
            if(packet instanceof EthernetPacket) 
            {
                EthernetPacket ethernetPacket = (EthernetPacket)packet;
                frame = String.valueOf(LinkLayer.getDescription(FachadaDominio.getPcap().getLinkLayer()));
                macsource = ethernetPacket.getSourceHwAddress();
                macdest = ethernetPacket.getDestinationHwAddress();
                length = String.valueOf(ethernetPacket.getHeaderLength());
                desc_protocol_short = String.valueOf(ethernetPacket.getProtocol());
            }
            if(packet instanceof ARPPacket)
            {
                desc_protocol_short = new String("arp");
                ARPPacket arppacket = (ARPPacket)packet;
            }
            if(packet instanceof IPPacket)
            {
                desc_protocol_short = new String("ip");
                IPPacket ipPacket = (IPPacket)packet;
                data = ipPacket.getData();
                dsize = String.valueOf(ipPacket.getLength());
                id = String.valueOf(ipPacket.getId());
                ttl = String.valueOf(ipPacket.getTimeToLive());
                ipsrc = ipPacket.getSourceAddress();
                ipdest = ipPacket.getDestinationAddress();
                protocol = IPProtocol.getDescription(ipPacket.getIPProtocol());
            }
            if(packet instanceof ICMPPacket)
            {
                desc_protocol_short = new String("icmp");
                ICMPPacket icmpPacket = (ICMPPacket)packet;
                itype = String.valueOf(icmpPacket.getMessageType());
                icode = String.valueOf(icmpPacket.getMessageCode());
            }
            if(packet instanceof IGMPPacket)
            {
                desc_protocol_short = new String("igmp");
                IGMPPacket igmppacket = (IGMPPacket)packet;
            }
            if(packet instanceof TCPPacket)
            {
                desc_protocol_short = new String("tcp");
                TCPPacket tcpPacket = (TCPPacket)packet;
                PuertoOrigen = tcpPacket.getSourcePort();
                PuertoDestino = tcpPacket.getDestinationPort();
                portsrc = String.valueOf(tcpPacket.getSourcePort());
                portdest = String.valueOf(tcpPacket.getDestinationPort());
                ack = (new StringBuilder("0x")).append(Long.toHexString(tcpPacket.getAcknowledgmentNumber())).toString();
                seq = (new StringBuilder("0x")).append(Long.toHexString(tcpPacket.getSequenceNumber())).toString();
                if(tcpPacket.isUrg())
                    flags = (new StringBuilder(String.valueOf(flags))).append("U").toString();
                if(tcpPacket.isAck())
                    flags = (new StringBuilder(String.valueOf(flags))).append("A").toString();
                if(tcpPacket.isPsh())
                    flags = (new StringBuilder(String.valueOf(flags))).append("P").toString();
                if(tcpPacket.isRst())
                    flags = (new StringBuilder(String.valueOf(flags))).append("R").toString();
                if(tcpPacket.isSyn())
                    flags = (new StringBuilder(String.valueOf(flags))).append("S").toString();
                if(tcpPacket.isFin())
                    flags = (new StringBuilder(String.valueOf(flags))).append("F").toString();
                venpadre.DatosConexion(ipsrc, ipdest, PuertoOrigen, PuertoDestino, i);
            }
            if(packet instanceof UDPPacket)
            {
                desc_protocol_short = new String("udp");
                UDPPacket udpPacket = (UDPPacket)packet;
                portsrc = String.valueOf(udpPacket.getSourcePort());
                portdest = String.valueOf(udpPacket.getDestinationPort());
            }
            if(Reglas != null && (packet instanceof IPPacket))
                Reglas.DatosRules(ipsrc, ipdest, portsrc, portdest, desc_protocol_short, data, itype, icode, dsize, id, ttl, flags, ack, seq, packet, i);
            RTablePane.DatosPaquete(macsource, macdest, frame, protocol, ipsrc, ipdest, portsrc, portdest, seq, ack, length);
            i++;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String GetTime()
    {
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:ms");
        String horaActual = new String(sdf.format(hoy));
        return horaActual;
    }

    private String IpOrigen;
    private String IpDestino;
    private int PuertoOrigen;
    private int PuertoDestino;
    private String datos;
    private byte data[];
    public int i;
    public VisualizarCaptura venpadre;
    public TablePane RTablePane;
    public Rules Reglas;
	
}
