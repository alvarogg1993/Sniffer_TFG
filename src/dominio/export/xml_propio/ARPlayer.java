
package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.ARPPacket;
/** 
 * Clase ARPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ARPlayer
{
  protected String sourceAddress;
  protected String destinationAddress;
  private String xmlStr;
  
  public ARPlayer(ARPPacket arpPacket)
  {
    this.xmlStr = ("      <Address_Resolution_Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Operation_ARP>" + (arpPacket.getOperation() == 1 ? "Request" : "Reply") + "</Operation_ARP>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Source_Proto_Address>" + arpPacket.getSourceProtoAddress() + "</Source_Proto_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Hardware_source_address>" + arpPacket.getSourceHwAddress() + "</Hardware_source_address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Destination_Proto_Address>" + arpPacket.getDestinationProtoAddress() + "</Destination_Proto_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Hardware_destination_address>" + arpPacket.getDestinationHwAddress() + "</Hardware_destination_address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Operation_code>" + String.valueOf(arpPacket.getOperation()) + "</Operation_code>" + System.getProperty("line.separator"));
    this.xmlStr += "      </Address_Resolution_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}