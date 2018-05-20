
package dominio.pcapDumper;

import dominio.export.xml_PcapLib.CrearXMLOffline;
import dominio.export.xml_PcapLib.XmlPacketHandler;
import dominio.pcap.SaveFileName;
/** 
 * Clase StateHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcapDumper:
//            StateCaptura

public class StateHandler extends Thread
{

    public StateHandler(SaveFileName SFN, StateCaptura ficheroEstate)
    {
        SFName = SFN;
        this.ficheroEstate = ficheroEstate;
    }

    public void run()
    {
        boolean primeraVez = true;
        do
            try
            {
                sleep(500L);
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    CrearXMLOffline CXMLOffline;
    XmlPacketHandler XMLPHandler;
    SaveFileName SFName;
    StateCaptura ficheroEstate;
}
