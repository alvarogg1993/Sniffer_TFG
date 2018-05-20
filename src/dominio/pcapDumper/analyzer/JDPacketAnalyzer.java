
package dominio.pcapDumper.analyzer;

//import jpcap.packet.Packet;


import java.net.UnknownHostException;

import org.jnetpcap.packet.PcapPacket;

//import jpcap.packet.Packet;
/** 
 * Clase JDPacketAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
public abstract class JDPacketAnalyzer
{

    public JDPacketAnalyzer()
    {
        layer = DATALINK_LAYER;
    }

    public abstract boolean isAnalyzable(PcapPacket packet);

    public abstract void analyze(PcapPacket packet);

    public abstract String getProtocolName();

    public abstract String[] getValueNames();

    public abstract Object getValue(String s);

    abstract Object getValueAt(int i);

    public abstract Object[] getValues();

    public int layer;
    public static int DATALINK_LAYER = 0;
    public static int NETWORK_LAYER = 1;
    public static int TRANSPORT_LAYER = 2;
    public static int APPLICATION_LAYER = 3;

}
