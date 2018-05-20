package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.IPPort;
import net.sourceforge.jpcap.net.UDPPacket;
/** 
 * Clase UDPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class UDPlayer
{
  private String xmlStr;
  
  public UDPlayer(UDPPacket udpPacket)
  {
    this.xmlStr = ("      <UDP_Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <UDP_Port_source>" + String.valueOf(udpPacket.getSourcePort()) + "</UDP_Port_source>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <UDP_Port_source_name>" + IPPort.getName(udpPacket.getSourcePort()) + "</UDP_Port_source_name>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <UDP_Destination_port>" + String.valueOf(udpPacket.getDestinationPort()) + "</UDP_Destination_port>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <UDP_Destination_port_name>" + IPPort.getName(udpPacket.getDestinationPort()) + "</UDP_Destination_port_name>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Length>" + String.valueOf(udpPacket.getLength()) + "</Length>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Checksum>" + String.valueOf(udpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator"));
    this.xmlStr += "      </UDP_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}

