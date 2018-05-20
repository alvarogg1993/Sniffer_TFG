
package dominio.export.xml_PcapLib;

import org.jnetpcap.packet.PcapPacket;

import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import dominio.pcapDumper.analyzer.PacketAnalyzer;
//import jpcap.packet.Packet;

/** 
 * Clase Packetlayer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class Packetlayer
{
    /** Constructor se definen los paquetes para IP4 e IP6.
     * @param Sin valor de Entrada
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public Packetlayer()
    {
        packetAnalyzer = new PacketAnalyzer();
    }
    /** Constructor  donde se crea el paquete con un protocolo de tipo General.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void analizar(PcapPacket packet)
    {
        packetAnalyzer.analyze(packet);
        protocolName = packetAnalyzer.getProtocolName();
        protocolName = protocolName.replaceAll(" ", "_");
        valueNames = packetAnalyzer.getValueNames();
        xmlStr = (new StringBuilder("      <")).append(protocolName).append(">").append(System.getProperty("line.separator")).toString();
        if(valueNames != null)
        {
            for(int i = 0; i < valueNames.length; i++)
            {
                String strNameField = valueNames[i].toString();
                String strCont = String.valueOf(packetAnalyzer.getValue(strNameField));
                strNameField = strNameField.replaceAll(" ", "_");
                xmlStr = (new StringBuilder(String.valueOf(xmlStr))).append("        <").append(strNameField).append(">").append(strCont).append("</").append(strNameField).append(">").append(System.getProperty("line.separator")).toString();
            }

        }
        xmlStr = (new StringBuilder(String.valueOf(xmlStr))).append("      </").append(protocolName).append(">").toString();
    }

    public String getStr()
    {
        return xmlStr;
    }

    private String xmlStr;
    private JDPacketAnalyzer packetAnalyzer;
    private String protocolName;
    private String valueNames[];
}
