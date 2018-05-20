
package dominio.pcapDumper.analyzer;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;
/** 
 * Clase SSHAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class SSHAnalyzer extends JDPacketAnalyzer
{
	Tcp tcppacket = new Tcp();
    public SSHAnalyzer()
    {
        layer = APPLICATION_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo SSH.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
    	return (p.hasHeader(tcppacket) && (tcppacket.source() == 22 || tcppacket.destination() == 22));
    		   }

    public String getProtocolName()
    {
        return "SSH";
    }

    public String[] getValueNames()
    {
        return null;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo ARP.
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
