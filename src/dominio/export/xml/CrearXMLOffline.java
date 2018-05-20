
package dominio.export.xml;

import java.io.FileOutputStream;
import net.sourceforge.jpcap.net.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/** 
 * Clase CrearXMLOffline. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class CrearXMLOffline
{

    public CrearXMLOffline(String fichero)
    {
        this.fichero = fichero;
        root = new Element("Captura");
        contador = 0;
    }

    public Element getRoot()
    {
        if(root == null)
            root = new Element("Captura");
        return root;
    }

    public void Generar()
    {
        String nombrefichero = fichero;
        Document doc = new Document(root);
        try
        {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(nombrefichero);
            out.output(doc, file);
            file.flush();
            file.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void xmlContadorHijo()
    {
        packet = (new Element("Packet")).setAttribute("Numero", String.valueOf(contador));
        contador++;
    }

    public void addPacket()
    {
        root.addContent(packet);
    }

    public void Etherlayer(EthernetPacket ethernetPacket)
    {
        Etherlayer ethernetlayer = new Etherlayer(ethernetPacket);
        packet.addContent(ethernetlayer);
    }

    public void ARPlayer(ARPPacket arpPacket)
    {
        ARPlayer arplayer = new ARPlayer(arpPacket);
        packet.addContent(arplayer);
    }

    public void IPlayer(IPPacket ipPacket)
    {
        IPlayer iplayer = new IPlayer(ipPacket);
        packet.addContent(iplayer);
    }

    public void ICMPlayer(ICMPPacket icmpPacket)
    {
        ICMPlayer icmplayer = new ICMPlayer(icmpPacket);
        packet.addContent(icmplayer);
    }

    public void IGMPlayer(IGMPPacket igmpPacket)
    {
        IGMPlayer igmplayer = new IGMPlayer(igmpPacket);
        packet.addContent(igmplayer);
    }

    public void TCPlayer(TCPPacket tcpPacket)
    {
        TCPlayer tcplayer = new TCPlayer(tcpPacket);
        packet.addContent(tcplayer);
    }

    public void UDPlayer(UDPPacket udpPacket)
    {
        UDPlayer udplayer = new UDPlayer(udpPacket);
        packet.addContent(udplayer);
    }

    protected Packet paquete;
    protected Element root;
    protected Element packet;
    private String fichero;
    private static long contador;
	public void enfile() {
		// TODO Auto-generated method stub
		
	}

}
