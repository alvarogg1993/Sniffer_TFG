
package dominio.export.xml_PcapLib;



import dominio.pcapDumper.analyzer.*;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


/** 
 * Clase de exportación de paquetes a XML 
 * Gestiona los tipos de protocolos capturados en los paquetes 
 * @author Jose Manuel Saiz Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/        

public class XmlPacketHandler 
{

	Tcp tcpPacket = new Tcp();
	Icmp icmpPacket = new Icmp();
	Udp udpPacket = new Udp();
	Arp arpPacket = new Arp();
	Ip4 ipPacket = new Ip4();
	Ethernet ethernetPacket = new Ethernet();
	
	
    public XmlPacketHandler(CrearXMLOffline RCrearXML)
    {
        this.RCrearXML = RCrearXML;
        establecerLayers();
    }

    private void establecerLayers()
    {
        FTPAnalyzer = new FTPAnalyzer();
        HTTPAnalyzer = new HTTPAnalyzer();
        POP3Analyzer = new POP3Analyzer();
        SMTPAnalyzer = new SMTPAnalyzer();
        SSHAnalyzer = new SSHAnalyzer();
        TelnetAnalyzer = new TelnetAnalyzer();
    }

    public void finEntrada() {
    	RCrearXML.endFile();
	}
    /** Metodo donde se recibe el paquete se identifica que tipo de protocolo es al 
     * que pertenece y se manda para crear su objeto.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void receivePacket(PcapPacket packet)
    {
        try
        {
        	 
        	
            RCrearXML.xmlContadorHijo();
         
            if(packet instanceof PcapPacket)
                RCrearXML.PacketInformation(packet);
            if(packet.hasHeader(ethernetPacket))
                RCrearXML.Etherlayer(packet);
            if(packet.hasHeader(arpPacket))
                RCrearXML.ARPlayer(packet);
            if(packet.hasHeader(ipPacket))
                RCrearXML.IPlayer(packet);
            if(packet.hasHeader(icmpPacket))
                RCrearXML.ICMPlayer(packet);
            if(packet.hasHeader(tcpPacket))
                RCrearXML.TCPlayer(packet);
            if(packet.hasHeader(udpPacket))
                RCrearXML.UDPlayer(packet);
            if(FTPAnalyzer.isAnalyzable(packet))
                RCrearXML.FTPlayer(packet);
            if(HTTPAnalyzer.isAnalyzable(packet))
                RCrearXML.HTTPlayer(packet);
            if(POP3Analyzer.isAnalyzable(packet))
                RCrearXML.POP3layer(packet);
            if(SMTPAnalyzer.isAnalyzable(packet))
                RCrearXML.SMTPlayer(packet);
            if(SSHAnalyzer.isAnalyzable(packet))
                RCrearXML.SSHlayer(packet);
            if(TelnetAnalyzer.isAnalyzable(packet))
                RCrearXML.Telnetlayer(packet);
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("En el paquete: ")).append(RCrearXML.getContador()).toString());
            e.printStackTrace();
        }
    }

    private CrearXMLOffline RCrearXML;
    private FTPAnalyzer FTPAnalyzer;
    private HTTPAnalyzer HTTPAnalyzer;
    private POP3Analyzer POP3Analyzer;
    private SMTPAnalyzer SMTPAnalyzer;
    private SSHAnalyzer SSHAnalyzer;
    private TelnetAnalyzer TelnetAnalyzer;




	
	
}
