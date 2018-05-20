
package dominio.export.xml_PcapLib;

import org.jnetpcap.packet.PcapPacket;

import dominio.pcapDumper.analyzer.*;
//import jpcap.packet.Packet;

/** 
 * Clase Iplayer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class IPlayer
{
    /** Constructor se definen los paquetes para IP4 e IP6.
     * @param Sin valor de Entrada
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public IPlayer()
    {
        packetAnalyzerV4 = new IPv4Analyzer();
        packetAnalyzerV6 = new IPv6Analyzer();
    }
    /** Constructor  donde se crea el paquete con un protocolo de tipo IP4 o IP6.
     * @param PcapPacket packet
     * @return Sin valor de retorno 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public void analizar(PcapPacket packet)
    {
        if(packetAnalyzerV4.isAnalyzable(packet))
        {
            packetAnalyzer = packetAnalyzerV4;
            packetAnalyzer.analyze(packet);
        } else
        {
            packetAnalyzer = packetAnalyzerV6;
            packetAnalyzer.analyze(packet);
        }
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
                strNameField = strNameField.replaceAll(":", "");
                strNameField = strNameField.replaceAll("'", "");
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
    private JDPacketAnalyzer packetAnalyzerV4;
    private JDPacketAnalyzer packetAnalyzerV6;
}
