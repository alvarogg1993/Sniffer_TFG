

package dominio.export.xml;

import java.io.*;
import net.sourceforge.jpcap.client.CaptureHistory;
import net.sourceforge.jpcap.net.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
 
/** 
 * Clase CrearXML. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class CrearXML
{

    public CrearXML(CaptureHistory history, File fichero, int pk_xml_file)
    {
        this.history = history;
        this.fichero = fichero;
        this.pk_xml_file = pk_xml_file;
    }

    public void Generar()
    {
        boolean fin = false;
        int part = 1;
        int i = 0;
        if(pk_xml_file == -1)
        {
            String nombrefichero = (new StringBuilder()).append(fichero.getAbsoluteFile()).append(".xml").toString();
            Element root = new Element("Captura");
            XmlHandler GestorPaquetes = new XmlHandler(this);
            System.out.println((new StringBuilder("tamanho:")).append(String.valueOf(history.size())).toString());
            for(i = 0; i < history.size(); i++)
            {
                packet = (new Element("Packet")).setAttribute("Numero", String.valueOf(i));
                paquete = history.get(i);
                GestorPaquetes.packetarrived(paquete);
                root.addContent(packet);
            }

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
        } else
        {
            while(!fin) 
            {
                String nombrefichero = (new StringBuilder()).append(fichero.getAbsoluteFile()).append(String.valueOf(part)).append(".xml").toString();
                Element root = new Element("Captura");
                XmlHandler GestorPaquetes = new XmlHandler(this);
                System.out.println((new StringBuilder("tamanho:")).append(String.valueOf(history.size())).toString());
                for(int cont_pk = 0; cont_pk <= pk_xml_file && i < history.size(); i++)
                {
                    packet = (new Element("Packet")).setAttribute("Numero", String.valueOf(i));
                    paquete = history.get(i);
                    GestorPaquetes.packetarrived(paquete);
                    root.addContent(packet);
                    cont_pk++;
                }

                if(i == history.size())
                    fin = true;
                else
                    fin = false;
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
                part++;
            }
        }
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
    protected static Element root;
    protected static Element packet;
    private CaptureHistory history;
    private File fichero;
    private int pk_xml_file;
}
