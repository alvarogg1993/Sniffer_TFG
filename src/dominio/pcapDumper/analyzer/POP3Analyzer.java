
package dominio.pcapDumper.analyzer;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;
/** 
 * Clase POP3Analyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class POP3Analyzer extends JDPacketAnalyzer
{
	Tcp tcppacket = new Tcp();
    public POP3Analyzer()
    {
        layer = APPLICATION_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ARP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
    	
    	return (p.hasHeader(tcppacket) && (tcppacket.source() == 110 || tcppacket.destination() == 110));
    }    			
    public String getProtocolName()
    {
        return "POP3";
    }

    public String[] getValueNames()
    {
        return null;
    }
    /** Metodo 
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
    public void analyze(PcapPacket packet)
    {
    }

    public Object getValue(String s)
    {
        return null;
    }

    public Object getValueAt(int i)
    {
        return null;
    }

    public Object[] getValues()
    {
        return null;
    }
}
