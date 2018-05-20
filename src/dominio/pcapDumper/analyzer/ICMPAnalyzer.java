
package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
//import org.jnetpcap.protocol.lan.Ethernet.EthernetType;
//import org.jnetpcap.protocol.network.Icmp.DestinationUnreachable;
import org.jnetpcap.protocol.network.Icmp.IcmpType;

/** 
 * Clase ICMAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/


public class ICMPAnalyzer extends JDPacketAnalyzer
{
	Icmp icmppacket = new Icmp();
	Icmp.EchoReply echo = new Icmp.EchoReply();
	Icmp.Redirect redirip = new Icmp.Redirect();
	
	Ip4 ip4 =new Ip4();
	private Icmp icmp;
    public ICMPAnalyzer()
    {
        values = new Hashtable();
        layer = TRANSPORT_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ICMP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
        return p.hasHeader(icmppacket);
    }

    public String getProtocolName()
    {
        return "ICMP";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo ICMP.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     * */
    public void analyze(PcapPacket p)
    {
        if(!isAnalyzable(p))
            return;
        values.clear();
        
        icmp = icmppacket;
        if(icmp.type() >= typeNames.length)
            values.put(valueNames[0], String.valueOf(icmp.type()));
        else
            values.put(valueNames[0], typeNames[icmp.type()]);
        values.put(valueNames[1], new Integer(icmp.code()));
        if(icmp.type() == 0 || icmp.type() == 8 || icmp.type() >= 13 && icmp.type() <= 18)
        {
            values.put(valueNames[2], new Integer(icmp.getId()));
            values.put(valueNames[3], new Integer(echo.sequence()));
        }
        if(icmp.type() == 5)
            values.put(valueNames[4], redirip.gateway());
        if(icmp.type() == 17 || icmp.type() == 18)
        	 values.put(valueNames[5], (new StringBuilder(String.valueOf(0x00)).append(".").append(0xff).append(".").append(0xff).append(".").append(0xff).append(".").toString()));
             
        	
        if(icmp.type() == 13 || icmp.type() == 14)
        {

            values.put(valueNames[6], new Long(IcmpType.TIMESTAMP_REQUEST_ID));
            values.put(valueNames[7], new Long(IcmpType.TIMESTAMP_RESPONSE_ID));
            values.put(valueNames[8], new Long(IcmpType.TIME_EXCEEDED_ID));
        }
    }

    public Object getValue(String valueName)
    {
        return values.get(valueName);
    }

    public Object getValueAt(int index)
    {
        if(index < 0 || index >= valueNames.length)
            return null;
        else
            return values.get(valueNames[index]);
    }

    public Object[] getValues()
    {
        Object v[] = new Object[valueNames.length];
        for(int i = 0; i < valueNames.length; i++)
            v[i] = values.get(valueNames[i]);

        return v;
    }

    private static final String valueNames[] = {
        "Type", "Code", "ID", "Sequence", "Redirect Address", "Address Mask", "Original Timestamp", "Receive Timestamp", "Transmission Timestamp"
    };
    private static final String typeNames[] = {
        "Echo Reply(0)", "Unknown(1)", "Unknown(2)", "Destination Unreachable(3)", "Source Quench(4)", "Redirect(5)", "Unknown(6)", "Unknown(7)", "Echo(8)", "Unknown(9)", 
        "Unknown(10)", "Time Exceeded(11)", "Parameter Problem(12)", "Timestamp(13)", "Timestamp Reply(14)", "Unknown(15)", "Unknown(16)", "Address Mask Request(17)", "Address Mask Reply(18)"
    };
    private Hashtable values;

}
