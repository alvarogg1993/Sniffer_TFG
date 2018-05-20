
package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.ICMPMessage;
import net.sourceforge.jpcap.net.ICMPPacket;

/** 
 * Clase ICMPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ICMPlayer
{
  protected String sourceAddress;
  protected String destinationAddress;
  protected String dataPacket;
  private String xmlStr;
  
  public ICMPlayer(ICMPPacket icmpPacket)
  {
    this.sourceAddress = icmpPacket.getSourceAddress();
    this.destinationAddress = icmpPacket.getDestinationAddress();
    this.xmlStr = ("      <ICMP_Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Source_ICMP_Addres>" + this.sourceAddress + "</Source_ICMP_Addres>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Destination_ICMP_Addres>" + this.destinationAddress + "</Destination_ICMP_Addres>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Type>" + ICMPMessage.getDescription(icmpPacket.getMessageCode()) + "</Type>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Code>" + String.valueOf(icmpPacket.getMessageCode()) + "</Code>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Checksum>" + String.valueOf(icmpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator"));
    this.xmlStr += "      </ICMP_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}
