
package dominio.export.xml_propio;

import java.io.*;
import net.sourceforge.jpcap.net.*;
import servicioAccesoDatos.*;

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
        contador = 0L;
        try
        {
            output = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fabrica = new FabricaAccesoDatos();
        f = fabrica.creaFachadaFichero("exportacion", fichero);
        writeToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        writeToFile("<Captura>");
    }

    public void xmlContadorHijo()
    {
        if(contador > 0L)
            writeToFile("  </Packet>");
        writeToFile((new StringBuilder("  <Packet Numero=\"")).append(contador).append("\">").toString());
        contador++;
    }

    public void Etherlayer(EthernetPacket ethernetPacket)
    {
        Etherlayer ethernetlayer = new Etherlayer(ethernetPacket);
        writeToFile(ethernetlayer.getStr());
    }

    public void ARPlayer(ARPPacket arpPacket)
    {
        ARPlayer arplayer = new ARPlayer(arpPacket);
        writeToFile(arplayer.getStr());
    }

    public void IPlayer(IPPacket ipPacket)
    {
        IPlayer iplayer = new IPlayer(ipPacket);
        writeToFile(iplayer.getStr());
    }

    public void ICMPlayer(ICMPPacket icmpPacket)
    {
        ICMPlayer icmplayer = new ICMPlayer(icmpPacket);
        writeToFile(icmplayer.getStr());
    }

    public void IGMPlayer(IGMPPacket igmpPacket)
    {
        IGMPlayer igmplayer = new IGMPlayer(igmpPacket);
        writeToFile(igmplayer.getStr());
    }

    public void TCPlayer(TCPPacket tcpPacket)
    {
        TCPlayer tcplayer = new TCPlayer(tcpPacket);
        writeToFile(tcplayer.getStr());
    }

    public void UDPlayer(UDPPacket udpPacket)
    {
        UDPlayer udplayer = new UDPlayer(udpPacket);
        writeToFile(udplayer.getStr());
    }

    public void endFile()
    {
        try
        {
            writeToFile("  </Packet>");
            writeToFile("</Captura>");
            f.cerrar();
            output.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void writeToFile(String aux)
    {
        try
        {
            f.escribir(aux);
        }
        catch(Exception exception) { }
    }

    private String fichero;
    private static long contador;
    public FileWriter writer;
    public BufferedWriter buffer;
    public PrintWriter output;
    private FabricaAccesoDatosIF fabrica;
    private FachadaFichero f;
}
