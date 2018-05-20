
package dominio.pcapDumper;

import java.util.Vector;
import java.lang.Object;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;



/** 
 * Clase CountPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class CountPacketHandler

{
	Tcp tcpPacket = new Tcp();
	Icmp icmpPacket = new Icmp();
	Udp udpPacket = new Udp();
	Arp arpPacket = new Arp();
	Ip4 ipPacket = new Ip4();
	Ethernet ethernetPacket = new Ethernet();

	public CountPacketHandler()
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
        packets = new Vector();
        pktotal = 0;
        contpk = 0;
    }

	/** Metodo se carga el paquete capturado para su analisis.
     * @param PcapPacket packet.
     * @return Sin valor de retorno
      */
	public void nextPacket (PcapPacket packet)
       {
        try
        {
            packets.addElement(packet);
//            
            if((packet.hasHeader(icmpPacket) || (packet.hasHeader(tcpPacket)) || (packet.hasHeader(udpPacket))))
            {
            	if(packet.hasHeader(icmpPacket)) {
            		icmp++;
            	}
            	
            	if(packet.hasHeader(tcpPacket)) {
            		
            		tcp++;
            	}
            	
            	if(packet.hasHeader(udpPacket)) {
            		udp++;
            		
            	}
       } else 
    	   	
    	   if(packet.hasHeader(arpPacket) || (packet.hasHeader(ipPacket)))
    			   {
    		   
    		   if(packet.hasHeader(arpPacket)){
    			   arp++;
    		   }
    		   
    		   if(packet.hasHeader(ipPacket)){
    			   ip++;
    		   }
    	   
      } else
    	  
    	  if(packet.hasHeader(ethernetPacket))
                ethernet++;
              
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
    }

    public Vector getPackets()
    {
        return packets;
    }

    public void delPackets()
    {
        packets.clear();
        packets.removeAllElements();
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
    private Vector packets;

	
}
