
package dominio.export.xml_PcapLib;

import java.io.*;

import org.jnetpcap.packet.PcapPacket;

//import jpcap.packet.Packet;
import servicioAccesoDatos.*;

/** 
 * Clase CrearXMLOffline, guarda en disco duro los archivos exportados a XML. 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
*/
/* Referenced classes of package dominio.export.xml_PcapLib:
*            Packetlayer, Etherlayer, ARPlayer, IPlayer, 
*            ICMPlayer, TCPlayer, UDPlayer, FTPlayer, 
*            HTTPlayer, POP3layer, SMTPlayer, SSHlayer, 
*            Telnetlayer
*/ 
public class CrearXMLOffline
{

    public CrearXMLOffline(String fichero)
    {
        setFichero(fichero);
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
        establecerLayers();
    }

    public void xmlContadorHijo()
    {
        if(contador > 0)
        writeToFile("  </Packet>");
        writeToFile((new StringBuilder("  <Packet Numero=\"")).append(contador).append("\">").toString());
        contador++;
    }

    private void establecerLayers()
    {
        packetlayer = new Packetlayer();
        ethernetlayer = new Etherlayer();
        arplayer = new ARPlayer();
        iplayer = new IPlayer();
        icmplayer = new ICMPlayer();
        tcplayer = new TCPlayer();
        udplayer = new UDPlayer();
        ftplayer = new FTPlayer();
        httplayer = new HTTPlayer();
        pop3layer = new POP3layer();
        smtplayer = new SMTPlayer();
        sshlayer = new SSHlayer();
        telnetlayer = new Telnetlayer();
    }

    /** Metodo donde  se analiza para sacar la información y tipo de paquetes.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void PacketInformation(PcapPacket packet)
    {
        packetlayer.analizar(packet);
        writeToFile(packetlayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete EthertNet.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void Etherlayer(PcapPacket packet)
    {
        ethernetlayer.analizar(packet);
        writeToFile(ethernetlayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete ARP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void ARPlayer(PcapPacket packet)
    {
        arplayer.analizar(packet);
        writeToFile(arplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete IP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void IPlayer(PcapPacket packet)
    {
        iplayer.analizar(packet);
        writeToFile(iplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete ICMP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void ICMPlayer(PcapPacket packet)
    {
        icmplayer.analizar(packet);
        writeToFile(icmplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete TCP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void TCPlayer(PcapPacket packet)
    {
        tcplayer.analizar(packet);
        writeToFile(tcplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete UDP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void UDPlayer(PcapPacket packet)
    {
        udplayer.analizar(packet);
        writeToFile(udplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete FTP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void FTPlayer(PcapPacket packet)
    {
        ftplayer.analizar(packet);
        writeToFile(ftplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete HTTP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void HTTPlayer(PcapPacket packet)
    {
        httplayer.analizar(packet);
        writeToFile(httplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete POP3.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void POP3layer(PcapPacket packet)
    {
        pop3layer.analizar(packet);
        writeToFile(pop3layer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete SMTP.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void SMTPlayer(PcapPacket packet)
    {
        smtplayer.analizar(packet);
        writeToFile(smtplayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete SSHL.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void SSHlayer(PcapPacket packet)
    {
        sshlayer.analizar(packet);
        writeToFile(sshlayer.getStr());
    }
    /** Metodo donde  se analiza para sacar la información del paquete TelNet.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void Telnetlayer(PcapPacket packet)
    {
        telnetlayer.analizar(packet);
        writeToFile(telnetlayer.getStr());
    }

    public void endFile()
    {
        try
        {
            writeToFile("  </Packet>");
            writeToFile("</Captura>");
            System.out.println("\n - Cierre de archivo XML");
            f.cerrar();
            output.close();
          
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
          
        }
    }

    private void writeToFile(String aux)
    {
        try
        {
            f.escribir(aux);
        }
        catch(Exception exception) { }
    }

    public long getContador()
    {
        return contador;
    }

    public void setFichero(String fichero)
    {
        this.fichero = fichero;
    }

    public String getFichero()
    {
        return fichero;
    }

    private String fichero;
    private static long contador;
    public FileWriter writer;
    public BufferedWriter buffer;
    public PrintWriter output;
    private FabricaAccesoDatosIF fabrica;
    private FachadaFichero f;
    private Packetlayer packetlayer;
    private Etherlayer ethernetlayer;
    private ARPlayer arplayer;
    private IPlayer iplayer;
    private ICMPlayer icmplayer;
    private TCPlayer tcplayer;
    private UDPlayer udplayer;
    private FTPlayer ftplayer;
    private HTTPlayer httplayer;
    private POP3layer pop3layer;
    private SMTPlayer smtplayer;
    private SSHlayer sshlayer;
    private Telnetlayer telnetlayer;
}
