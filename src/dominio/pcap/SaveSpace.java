
package dominio.pcap;

import java.io.File;
import java.io.PrintStream;

/** 
 * Clase SaveSpace. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class SaveSpace extends Thread
{

    public SaveSpace(SaveRawPacketHandler venpadre, long time, SaveFileName SFN)
    {
        this.time = 500L;
        setVenPadre(venpadre);
        setSpace(time);
        setSFName(SFN);
    }

    public void run()
    {
        long fileSpace = -1L;
        do
            try
            {
                if(fileSpace > getSpace() || fileSpace == -1L)
                {
                    SFName.setNext();
                    if(SFName.getNext() == -1)
                        getVenPadre().stopCaptura();
                    getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                    System.out.println((new StringBuilder("getNameTime=> ")).append(getSFName().getNameTime()).toString());
                    System.out.println("SaveSpace    =>    ENTRA");
                }
                f = new File(SFName.getFullNameTime());
                boolean exists = f.exists();
                if(exists)
                {
                    fileSpace = f.length();
                    System.out.println((new StringBuilder("SaveSpace    =>    ")).append(fileSpace).toString());
                }
                sleep(time);
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

    private void setSpace(long t)
    {
        spaceBytes = t;
    }

    private long getSpace()
    {
        return spaceBytes;
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
    public long spaceBytes;
    private long time;
    private File f;
}
