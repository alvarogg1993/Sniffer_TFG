package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.IPPacket;
/** 
 * Clase IPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class IPlayer
{
  protected String sourceAddress;
  protected String destinationAddress;
  private String xmlStr;
  
  public IPlayer(IPPacket ipPacket)
  {
    this.sourceAddress = ipPacket.getSourceAddress();
    this.destinationAddress = ipPacket.getDestinationAddress();
    this.xmlStr = ("      <IP_Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Source_IP_Address>" + this.sourceAddress + "</Source_IP_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Destination_IP_Address>" + this.destinationAddress + "</Destination_IP_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Headler_Length>" + String.valueOf(ipPacket.getHeaderLength()) + "</Headler_Length>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Version>" + String.valueOf(ipPacket.getVersion()) + "</Version>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Type>" + String.valueOf(ipPacket.getTypeOfService()) + "</Type>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <TTL>" + String.valueOf(ipPacket.getTimeToLive()) + "</TTL>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Protocol>" + String.valueOf(ipPacket.getIPProtocol()) + "</Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Protocol_Description>" + String.valueOf(ipPacket.getIPProtocol()) + "</Protocol_Description>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <ID>" + String.valueOf(ipPacket.getId()) + "</ID>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Length>" + String.valueOf(ipPacket.getLength()) + "</Length>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Flags>" + String.valueOf(ipPacket.getFragmentFlags()) + "</Flags>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Offset>" + String.valueOf(ipPacket.getFragmentOffset()) + "</Offset>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Check_Sum>" + String.valueOf(ipPacket.getChecksum()) + "</Check_Sum>" + System.getProperty("line.separator"));
    this.xmlStr += "      </IP_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}

