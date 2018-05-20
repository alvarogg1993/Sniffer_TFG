
package presentacion.visualizarCaptura;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnetpcap.packet.PcapPacket;


import dominio.pcapDumper.Captura;
import dominio.pcapDumper.analyzer.EthernetAnalyzer;
import dominio.pcapDumper.analyzer.IPv4Analyzer;
import dominio.pcapDumper.analyzer.IPv6Analyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import dominio.pcapDumper.analyzer.TCPAnalyzer;
import dominio.pcapDumper.analyzer.UDPAnalyzer;

/** 
 * Clase PacketHandlerPcapLib. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class PacketHandlerPcapLib
    
{
	
	 static String macsource = "----";
     static String macdest = "----";
     static String frame = "----";
     static String protocol = "---- ";
     static String desc_protocol_short = "11";
     static String ipsrc = "NO IP Address";
     static String ipdest = "NO IP Address";
     static String portsrc = "----";
     static String portdest = "----";
     static String seq = "----";
     static String ack = "----";
     static String length = "----";
     static String itype = "";
     static String icode = "";
     static String dsize = "";
     static String id = "";
     static String ttl = "";
     static String flags = "";

    public PacketHandlerPcapLib(VisualizarCaptura visualizarCaptura, TablePane RTablePane)
    {
        i = 0;
        this.venpadre = visualizarCaptura;
        this.RTablePane = RTablePane;
    }
    /** Metodo  se procesa para su analisis posterior los paquetes capturados, una vez
     * procesado se pasa a la siguente captura..
     * @param PcapPacket packet 
     * @return sin valor de retorno
     */
    public static void nextPacket(PcapPacket packet)
    {

        try
        {
             JDPacketAnalyzer packetAnalyzer = new EthernetAnalyzer();
            if(packetAnalyzer.isAnalyzable(packet))
            {
                packetAnalyzer.analyze(packet);
                frame = String.valueOf(packetAnalyzer.getValue("Frame Type"));
                macsource = String.valueOf(packetAnalyzer.getValue("Source MAC"));
                macdest = String.valueOf(packetAnalyzer.getValue("Destination MAC"));
                length = "";
                protocol = packetAnalyzer.getProtocolName();
            }
            packetAnalyzer = new IPv4Analyzer();
            if(packetAnalyzer.isAnalyzable(packet))
            {
                packetAnalyzer.analyze(packet);
                desc_protocol_short = new String("ip");
                ipsrc = String.valueOf(packetAnalyzer.getValue("Source IP"));
                ipdest = String.valueOf(packetAnalyzer.getValue("Destination IP"));
                protocol = packetAnalyzer.getProtocolName();
            }
            packetAnalyzer = new IPv6Analyzer();
            if(packetAnalyzer.isAnalyzable(packet))
            {
                packetAnalyzer.analyze(packet);
                desc_protocol_short = new String("ip");
                ipsrc = String.valueOf(packetAnalyzer.getValue("Source IP"));
                ipdest = String.valueOf(packetAnalyzer.getValue("Destination IP"));
                protocol = packetAnalyzer.getProtocolName();
            }
            packetAnalyzer = new TCPAnalyzer();
            if(packetAnalyzer.isAnalyzable(packet))
            {
                packetAnalyzer.analyze(packet);
                PuertoOrigen = ((Integer)packetAnalyzer.getValue("Source Port")).intValue();
                PuertoDestino = ((Integer)packetAnalyzer.getValue("Destination Port")).intValue();
                portsrc = String.valueOf(packetAnalyzer.getValue("Source Port"));
                portdest = String.valueOf(packetAnalyzer.getValue("Destination Port"));
                ack = String.valueOf(packetAnalyzer.getValue("Ack Number"));
                seq = String.valueOf(packetAnalyzer.getValue("Sequence Number"));
                protocol = packetAnalyzer.getProtocolName();
                venpadre.DatosConexion(ipsrc, ipdest, PuertoOrigen, PuertoDestino, i + 1);
            }
            packetAnalyzer = new UDPAnalyzer();
            if(packetAnalyzer.isAnalyzable(packet))
            {
                packetAnalyzer.analyze(packet);
                portsrc = String.valueOf(packetAnalyzer.getValue("Source Port"));
                portdest = String.valueOf(packetAnalyzer.getValue("Destination Port"));
                protocol = packetAnalyzer.getProtocolName();
            }
            RTablePane.DatosPk();
            
     
            RTablePane.DatosRawPaquete(String.valueOf(i), String.valueOf(new Date(packet.getCaptureHeader().hdr_sec()*1000+ packet.getCaptureHeader().hdr_usec()/1000).toString()), String.valueOf(packet.getCaptureHeader().caplen()));
            RTablePane.DatosPaquete(macsource, macdest, frame, protocol, ipsrc, ipdest, portsrc, portdest, seq, ack, length);
                   
          //  System.out.println(""+macsource+","+ macdest+","+ frame+","+ protocol+","+ ipsrc.toString()+","+ ipdest.toString()+","+ portsrc +","+ portdest+","+ seq+","+ ack+","+ length);
            VisualizarCaptura.addpackethistory(packet);
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
    private static int PuertoOrigen;
    private static int PuertoDestino;
    private String datos;
    private byte data[];
    public static int i;
    public static  VisualizarCaptura venpadre;
    public static TablePane RTablePane;
}
