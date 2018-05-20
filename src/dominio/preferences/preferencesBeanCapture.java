
package dominio.preferences;

import java.io.Serializable;
import java.util.List;

import org.jnetpcap.PcapIf;
/** 
 * Clase preferencesBeanCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanCapture
    implements Serializable
{

    public preferencesBeanCapture()
    {
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        setCapDispositive(null);
        setCapPromiscuousMode(true);
        setCapFilter(false);
        setCapAdvanceId(false);
        setCapAdvance("");
        setCapNormal(false);
        setCapHost("");
        setCapProtocol("");
        setCapPort("");
        setFilLocate("");
        setFilMultipleFileId(false);
        setFilSpaceId(false);
        setFilSpaceType("");
        setFilSpace("");
        setFilTimeId(false);
        setFilTimeType("");
        setFilTime("");
        setFilRingBufferId(false);
        setFilRingBufferXML(false);
        setNFSsync(false);
        setFilRingBufferType("");
        setFilRingBuffer("");
        setFilStopAfterId(false);
        setFilStopAfterType("");
        setFilStopAfter("");
        setstpAfterPacketsId(false);
        setstpAfterPackets("");
        setstpAfterSpaceId(false);
        setstpAfterSpaceType("");
        setstpAfterSpace("");
        setstpAfterTimeId(false);
        setstpAfterTimeType("");
        setstpAfterTime("");
    }

    public String getCapDispositive()
    {
        return capDispositive;
    }

    public void setCapDispositive(String string)
    {
        capDispositive = string;
    }

    public boolean getCapPromiscuousMode()
    {
        return capPromiscuousMode;
    }

    public void setCapPromiscuousMode(boolean aux)
    {
        capPromiscuousMode = aux;
    }

    public boolean getCapFilter()
    {
        return capFilter;
    }

    public void setCapFilter(boolean aux)
    {
        capFilter = aux;
    }

    public boolean getCapAdvanceId()
    {
        return capAdvanceId;
    }

    public void setCapAdvanceId(boolean aux)
    {
        capAdvanceId = aux;
    }

    public String getCapAdvance()
    {
        return capAdvance;
    }

    public void setCapAdvance(String aux)
    {
        capAdvance = aux;
    }

    public boolean getCapNormal()
    {
        return capNormal;
    }

    public void setCapNormal(boolean aux)
    {
        capNormal = aux;
    }

    public String getCapHost()
    {
        return capHost;
    }

    public void setCapHost(String aux)
    {
        capHost = aux;
    }

    public String getCapProtocol()
    {
        return capProtocol;
    }

    public void setCapProtocol(String aux)
    {
        capProtocol = aux;
    }

    public String getCapPort()
    {
        return capPort;
    }

    public void setCapPort(String aux)
    {
        capPort = aux;
    }

    public String getFilLocate()
    {
        return filLocate;
    }

    public void setFilLocate(String aux)
    {
        filLocate = aux;
    }

    public boolean getFilMultipleFileId()
    {
        return filMultipleFileId;
    }

    public void setFilMultipleFileId(boolean aux)
    {
        filMultipleFileId = aux;
    }

    public boolean getFilSpaceId()
    {
        return filSpaceId;
    }

    public void setFilSpaceId(boolean aux)
    {
        filSpaceId = aux;
    }

    public String getFilSpaceType()
    {
        return filSpaceType;
    }

    public void setFilSpaceType(String aux)
    {
        filSpaceType = aux;
    }

    public String getFilSpace()
    {
        return filSpace;
    }

    public void setFilSpace(String aux)
    {
        filSpace = aux;
    }

    public boolean getFilTimeId()
    {
        return filTimeId;
    }

    public void setFilTimeId(boolean aux)
    {
        filTimeId = aux;
    }

    public String getFilTimeType()
    {
        return filTimeType;
    }

    public void setFilTimeType(String aux)
    {
        filTimeType = aux;
    }

    public String getFilTime()
    {
        return filtime;
    }

    public void setFilTime(String aux)
    {
        filtime = aux;
    }

    public boolean getFilRingBufferId()
    {
        return filRingBufferId;
    }

    public void setFilRingBufferId(boolean aux)
    {
        filRingBufferId = aux;
    }

    public String getFilRingBufferType()
    {
        return filRingBufferType;
    }

    public void setFilRingBufferType(String aux)
    {
        filRingBufferType = aux;
    }

    public String getFilRingBuffer()
    {
        return filRingBuffer;
    }

    public void setFilRingBuffer(String aux)
    {
        filRingBuffer = aux;
    }
    
    public boolean getFilRingBufferXML()
    {
        return filRingBufferXML;
    }

    public void setFilRingBufferXML(boolean aux)
    {
        filRingBufferXML = aux;
    }
    
    public boolean getNFSsync()
    {
        return nfsSync;
    }

    public void setNFSsync(boolean aux)
    {
    	nfsSync = aux;
    }

    public boolean getFilStopAfterId()
    {
        return filStopAfterId;
    }

    public void setFilStopAfterId(boolean aux)
    {
        filStopAfterId = aux;
    }

    public String getFilStopAfterType()
    {
        return filStopAfterType;
    }

    public void setFilStopAfterType(String aux)
    {
        filStopAfterType = aux;
    }

    public String getFilStopAfter()
    {
        return filStopAfter;
    }

    public void setFilStopAfter(String aux)
    {
        filStopAfter = aux;
    }

    public boolean getstpAfterPacketsId()
    {
        return stpAfterPacketsId;
    }

    public void setstpAfterPacketsId(boolean aux)
    {
        stpAfterPacketsId = aux;
    }

    public String getstpAfterPackets()
    {
        return stpAfterPackets;
    }

    public void setstpAfterPackets(String aux)
    {
        stpAfterPackets = aux;
    }

    public boolean getstpAfterSpaceId()
    {
        return stpAfterSpaceId;
    }

    public void setstpAfterSpaceId(boolean aux)
    {
        stpAfterSpaceId = aux;
    }

    public String getstpAfterSpaceType()
    {
        return stpAfterSpaceType;
    }

    public void setstpAfterSpaceType(String aux)
    {
        stpAfterSpaceType = aux;
    }

    public String getstpAfterSpace()
    {
        return stpAfterSpace;
    }

    public void setstpAfterSpace(String aux)
    {
        stpAfterSpace = aux;
    }

    public boolean getstpAfterTimeId()
    {
        return stpAfterTimeId;
    }

    public void setstpAfterTimeId(boolean aux)
    {
        stpAfterTimeId = aux;
    }

    public String getstpAfterTimeType()
    {
        return stpAfterTimeType;
    }

    public void setstpAfterTimeType(String aux)
    {
        stpAfterTimeType = aux;
    }

    public String getstpAfterTime()
    {
        return stpAfterTime;
    }

    public void setstpAfterTime(String aux)
    {
        stpAfterTime = aux;
    }

    private String capDispositive;
    private boolean capPromiscuousMode;
    private boolean capFilter;
    private boolean capAdvanceId;
    private String capAdvance;
    private boolean capNormal;
    private String capHost;
    private String capProtocol;
    private String capPort;
    private String filLocate;
    private boolean filMultipleFileId;
    private boolean filSpaceId;
    private String filSpaceType;
    private String filSpace;
    private boolean filTimeId;
    private String filTimeType;
    private String filtime;
    private boolean filRingBufferId;
    private String filRingBufferType;
    private boolean filRingBufferXML;
    private boolean nfsSync;
    private String filRingBuffer;
    private boolean filStopAfterId;
    private String filStopAfterType;
    private String filStopAfter;
    private boolean stpAfterPacketsId;
    private String stpAfterPackets;
    private boolean stpAfterSpaceId;
    private String stpAfterSpaceType;
    private String stpAfterSpace;
    private boolean stpAfterTimeId;
    private String stpAfterTimeType;
    private String stpAfterTime;
}
