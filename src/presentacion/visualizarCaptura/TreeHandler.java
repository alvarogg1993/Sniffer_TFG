
package presentacion.visualizarCaptura;

import dominio.pcapDumper.analyzer.*;
import dominio.preferences.identificacion.PrefIdentificacion;
import java.io.PrintStream;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

//import sun.org.mozilla.javascript.internal.ast.NewExpression;


/** 
 * Clase TreeHanler. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class TreeHandler
{
	Tcp tcpPacket = new Tcp();
	Icmp icmpPacket = new Icmp();
	Udp udpPacket = new Udp();
	Arp arpPacket = new Arp();
	Ip4 ipPacket = new Ip4();
	Ethernet ethernetPacket = new Ethernet();
    public TreeHandler(PcapPacket paquete)
    {
        PrefIdentificacion pref = new PrefIdentificacion();
        try
        {         	
            if(paquete instanceof PcapPacket)
                TreePacket.treepk(paquete);
            if(paquete.hasHeader( ethernetPacket))
                TreePacket.treepkether(paquete);
            if(paquete.hasHeader(arpPacket))
                TreePacket.treepkARP(paquete);
            if(paquete.hasHeader(ipPacket))
                TreePacket.treepkIP(paquete);
            if(paquete.hasHeader(icmpPacket))
                TreePacket.treepkICMP(paquete);
            if(paquete.hasHeader(tcpPacket))
                TreePacket.treepkTCP(paquete);
            if(paquete.hasHeader(udpPacket))
                TreePacket.treepkUDP(paquete);
        	
            JDPacketAnalyzer packetAnalyzer = new FTPAnalyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
            {
                TreePacket.treepkFTP(paquete);              
            }
            packetAnalyzer = new HTTPAnalyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkHTTP(paquete);
            packetAnalyzer = new POP3Analyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkPOP3(paquete);
            packetAnalyzer = new SMTPAnalyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkSMTP(paquete);
            packetAnalyzer = new SSHAnalyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkSSH(paquete);
            packetAnalyzer = new TelnetAnalyzer();
            if(packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkTelnet(paquete);
            OtroAnalyzer otro = new OtroAnalyzer();
            packetAnalyzer = new FTPAnalyzer();
            if(otro.isAnalyzable(paquete) && !packetAnalyzer.isAnalyzable(paquete))
                TreePacket.treepkOtro(paquete, pref);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
