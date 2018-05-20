
package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.EthernetPacket;
import net.sourceforge.jpcap.util.Timeval;

/** 
 * Clase Etherlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class Etherlayer
{
  protected String sourceAddress;
  protected String destinationAddress;
  private String xmlStr;
  
  public Etherlayer(EthernetPacket ethernetPacket)
  {
    this.sourceAddress = ethernetPacket.getSourceHwAddress();
    this.destinationAddress = ethernetPacket.getDestinationHwAddress();
    this.xmlStr = ("      <Ethernet_Layer>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Source_Hardware_Address>" + this.sourceAddress + "</Source_Hardware_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Destination_Hardware_Address>" + this.destinationAddress + "</Destination_Hardware_Address>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Type>" + String.valueOf(ethernetPacket.getEthernetProtocol()) + "</Type>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Time_arrived>" + ethernetPacket.getTimeval().toString() + "</Time_arrived>" + System.getProperty("line.separator"));
    this.xmlStr += "      </Ethernet_Layer>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}
