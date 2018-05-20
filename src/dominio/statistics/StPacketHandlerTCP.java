
package dominio.statistics;

import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;
/** 
 * Clase StPacketHandlerTCP. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.statistics:
//            StAplicationLayer

class StPacketHandlerTCP
{

    public StPacketHandlerTCP(Packet packet)
    {
        try
        {
            if(packet instanceof TCPPacket)
            {
                TCPPacket tcpPacket = (TCPPacket)packet;
                dominio.statistics.StAplicationLayer.TCPPacket(tcpPacket);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
