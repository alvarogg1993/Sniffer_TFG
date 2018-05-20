
package dominio.export.xml_PcapLib;

import net.sourceforge.jpcap.net.ICMPMessage;
import net.sourceforge.jpcap.net.IGMPPacket;
/** 
 * Clase IGMPlayer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class IGMPlayer
{
  protected String sourceAddress;
  protected String destinationAddress;
  private String xmlStr;
  
  public IGMPlayer(IGMPPacket igmpPacket)
  {
    this.xmlStr = ("      <IGMP_Protocol>" + System.getProperty("ICMP_Protocol.separator"));
    this.xmlStr = (this.xmlStr + "        <Type>" + ICMPMessage.getDescription(igmpPacket.getMessageType()) + "</Type>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Group_address>" + igmpPacket.getGroupAddress() + "</Group_address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <max_response_time>" + String.valueOf(igmpPacket.getMaxResponseTime()) + "</max_response_time>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Checksum>" + String.valueOf(igmpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator"));
    this.xmlStr += "      </IGMP_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}
