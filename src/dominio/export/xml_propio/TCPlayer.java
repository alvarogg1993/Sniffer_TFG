package dominio.export.xml_propio;

import net.sourceforge.jpcap.net.IPPort;
import net.sourceforge.jpcap.net.TCPPacket;

/** 
 * Clase TCPlayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class TCPlayer
{
  private String xmlStr;
  
  public TCPlayer(TCPPacket tcpPacket)
  {
    this.xmlStr = ("      <TCP_Protocol>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Port_source>" + String.valueOf(tcpPacket.getDestinationPort()) + "</Port_source>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Destination_port>" + IPPort.getName(tcpPacket.getDestinationPort()) + "</Destination_port>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Sequence_Number>" + String.valueOf(tcpPacket.getAcknowledgmentNumber()) + "</Sequence_Number>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Acknowledgment_Number>" + String.valueOf(tcpPacket.getAcknowledgmentNumber()) + "</Acknowledgment_Number>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Header_Length>" + String.valueOf(tcpPacket.getTCPHeaderLength()) + "</Header_Length>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <TCPflags>" + System.getProperty("line.separator"));
    if (tcpPacket.isUrg()) {
      this.xmlStr = (this.xmlStr + "          <URG>" + Integer.toHexString(tcpPacket.getUrgentPointer()) + "</URG>" + System.getProperty("line.separator"));
    }
    if (tcpPacket.isAck()) {
      this.xmlStr = (this.xmlStr + "          <ACK>" + Long.toHexString(tcpPacket.getAcknowledgmentNumber()) + "</ACK>" + System.getProperty("line.separator"));
    }
    if (tcpPacket.isPsh()) {
      this.xmlStr = (this.xmlStr + "          <PSH>" + "</PSH>" + System.getProperty("line.separator"));
    }
    if (tcpPacket.isRst()) {
      this.xmlStr = (this.xmlStr + "          <RST>" + "</RST>" + System.getProperty("line.separator"));
    }
    if (tcpPacket.isSyn()) {
      this.xmlStr = (this.xmlStr + "          <SYN>" + Long.toHexString(tcpPacket.getSequenceNumber()) + "</SYN>" + System.getProperty("line.separator"));
    }
    if (tcpPacket.isFin()) {
      this.xmlStr = (this.xmlStr + "          <FIN>" + "</FIN>" + System.getProperty("line.separator"));
    }
    this.xmlStr = (this.xmlStr + "        </TCPflags>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Window_Size>" + tcpPacket.getWindowSize() + "</Window_Size>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <Checksum>" + String.valueOf(tcpPacket.getChecksum()) + "</Checksum>" + System.getProperty("line.separator"));
    this.xmlStr = (this.xmlStr + "        <UrgentPointer>" + tcpPacket.getUrgentPointer() + "</UrgentPointer>" + System.getProperty("line.separator"));
    this.xmlStr += "      </TCP_Protocol>";
  }
  
  public String getStr()
  {
    return this.xmlStr;
  }
}
