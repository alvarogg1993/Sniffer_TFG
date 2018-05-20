
package dominio.pcap;

import java.io.PrintStream;

/** 
 * Clase SaveTime. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class SaveTime extends Thread
{

    public SaveTime(SaveRawPacketHandler venpadre, long time, SaveFileName SFN)
    {
        setVenPadre(venpadre);
        setTime(time);
        setSFName(SFN);
    }

    public void run()
    {
        do
            try
            {
                SFName.setNext();
                if(SFName.getNext() == -1)
                    getVenPadre().stopCaptura();
                getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                System.out.println((new StringBuilder("getNameTime=> ")).append(getSFName().getNameTime()).toString());
                sleep(getTime());
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    private void setVenPadre(SaveRawPacketHandler padre)
    {
        venpadre = padre;
    }

    private SaveRawPacketHandler getVenPadre()
    {
        return venpadre;
    }

    private void setTime(long t)
    {
        timeSleep = t;
    }

    private long getTime()
    {
        return timeSleep;
    }

    private void setSFName(SaveFileName SFN)
    {
        SFName = SFN;
    }

    private SaveFileName getSFName()
    {
        return SFName;
    }

    public SaveRawPacketHandler venpadre;
    private SaveFileName SFName;
    public long timeSleep;
    private int i;
}
