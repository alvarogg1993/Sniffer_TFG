
package presentacion.visualizarCaptura;

import java.util.Vector;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.Timeval;
/** 
 * Clase RawPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.visualizarCaptura:
//            TablePane

public class RawPacketHandler
    implements RawPacketListener
{

    public RawPacketHandler(TablePane RTablePane)
    {
        this.RTablePane = RTablePane;
        i = 0;
        VRawPackets = new Vector();
    }

    public void rawPacketArrived(RawPacket rawPacket)
    {
        RTablePane.DatosPk();
        RTablePane.DatosRawPaquete(String.valueOf(i), rawPacket.getTimeval().toString(), String.valueOf(rawPacket.getData().length));
        VRawPackets.addElement(rawPacket);
        i++;
    }

    public static Vector getVRawPackets()
    {
        return VRawPackets;
    }

    public int i;
    private static Vector VRawPackets;
    private TablePane RTablePane;
}
