
package dominio.pcapDumper;

import java.io.File;
import java.io.PrintStream;
/** 
 * Clase SaveSpace. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcapDumper:
//            SavePacketHandler, SaveFileName

public class SaveSpace extends Thread
{

    public SaveSpace(SavePacketHandler venpadre, long space, SaveFileName SFN)
    {
        time = 500L;
        setVenPadre(venpadre);
        setSpace(space);
        setSFName(SFN);
    }

    public void run()
    {
        try
        {
            if(getVenPadre().getspaceLen() >= getSpace())
            {
                SFName.setNext();
                if(SFName.getNext() == -1)
                {
                    getVenPadre().stopCaptura();
                } else
                {
                    getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                    SFName.saveStateMulti(true);
                    System.out.println((new StringBuilder("\n----> ")).append(getSFName().getNameTime()).toString());
                }
            }
        }
        catch(Exception exception) { }
    }

    private void setVenPadre(SavePacketHandler padre)
    {
        venpadre = padre;
    }

    private SavePacketHandler getVenPadre()
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

    public SavePacketHandler venpadre;
    private SaveFileName SFName;
    public long spaceBytes;
    private long time;
    private File f;
}
