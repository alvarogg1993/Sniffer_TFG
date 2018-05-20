// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlPacketHandler.java

package dominio.export.xml_propio;


//
//import jpcap.PacketReceiver;
//import jpcap.packet.*;
//import jpcap.packet.EthernetPacket;
//import jpcap.packet.Packet;

//import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.*;

/** 
 * Clase XMLPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class XmlPacketHandler implements PacketListener 
{

    public XmlPacketHandler(CrearXMLOffline RCrearXML)
    {
        this.RCrearXML = RCrearXML;
    }
  
   
    
    public void packetArrived(Packet packet)
    {

        try
        {
        	
            RCrearXML.xmlContadorHijo();
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

    private CrearXMLOffline RCrearXML;

	


}
