
package dominio.preferences;

import java.io.Serializable;
/** 
 * Clase preferencesBeanFromFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanFromFile
    implements Serializable
{

    public preferencesBeanFromFile()
    {
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        setffCapSource("./files/capturas/capture.pcap");
        setffCapFilterId(true);
        setffCapAdvanceId(true);
        setffCapAdvance("");
        setffCapNormal(false);
        setffCapHost("");
        setffCapProtocol("");
        setffCapPort("");
        setffFilLocate("./files/capturas/capture_from_file.pcap");
        setffFilMultipleFileId(false);
        setffFilSpaceId(false);
        setffFilSpaceType("megabyte(s)");
        setffFilSpace("1");
        setffFilRingBufferId(false);
        setffFilRingBufferType("segundo(s)");
        setffFilRingBuffer("10");
        setffFilStopAfterId(false);
        setffFilStopAfterType("Files");
        setffFilStopAfter("3");
        setffStpAfterPacketsId(false);
        setffStpAfterPackets("0");
    }

    public String getffCapSource()
    {
        return ffCapSource;
    }

    public void setffCapSource(String aux)
    {
        ffCapSource = aux;
    }

    public boolean getffCapFilterId()
    {
        return ffCapFilterId;
    }

    public void setffCapFilterId(boolean aux)
    {
        ffCapFilterId = aux;
    }

    public boolean getffCapAdvanceId()
    {
        return ffCapAdvanceId;
    }

    public void setffCapAdvanceId(boolean aux)
    {
        ffCapAdvanceId = aux;
    }

    public String getffCapAdvance()
    {
        return ffCapAdvance;
    }

    public void setffCapAdvance(String aux)
    {
        ffCapAdvance = aux;
    }

    public boolean getffCapNormal()
    {
        return ffCapNormal;
    }

    public void setffCapNormal(boolean aux)
    {
        ffCapNormal = aux;
    }

    public String getffCapHost()
    {
        return ffCapHost;
    }

    public void setffCapHost(String aux)
    {
        ffCapHost = aux;
    }

    public String getffCapProtocol()
    {
        return ffCapProtocol;
    }

    public void setffCapProtocol(String aux)
    {
        ffCapProtocol = aux;
    }

    public String getffCapPort()
    {
        return ffCapPort;
    }

    public void setffCapPort(String aux)
    {
        ffCapPort = aux;
    }

    public String getffFilLocate()
    {
        return ffFilLocate;
    }

    public void setffFilLocate(String aux)
    {
        ffFilLocate = aux;
    }

    public boolean getffFilMultipleFileId()
    {
        return ffFilMultipleFileId;
    }

    public void setffFilMultipleFileId(boolean aux)
    {
        ffFilMultipleFileId = aux;
    }

    public boolean getffFilSpaceId()
    {
        return ffFilSpaceId;
    }

    public void setffFilSpaceId(boolean aux)
    {
        ffFilSpaceId = aux;
    }

    public String getffFilSpaceType()
    {
        return ffFilSpaceType;
    }

    public void setffFilSpaceType(String aux)
    {
        ffFilSpaceType = aux;
    }

    public String getffFilSpace()
    {
        return ffFilSpace;
    }

    public void setffFilSpace(String aux)
    {
        ffFilSpace = aux;
    }

    public boolean getffFilRingBufferId()
    {
        return ffFilRingBufferId;
    }

    public void setffFilRingBufferId(boolean aux)
    {
        ffFilRingBufferId = aux;
    }

    public String getffFilRingBufferType()
    {
        return ffFilRingBufferType;
    }

    public void setffFilRingBufferType(String aux)
    {
        ffFilRingBufferType = aux;
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
    
    public String getffFilRingBuffer()
    {
        return ffFilRingBuffer;
    }

    public void setffFilRingBuffer(String aux)
    {
        ffFilRingBuffer = aux;
    }

    public boolean getffFilStopAfterId()
    {
        return ffFilStopAfterId;
    }

    public void setffFilStopAfterId(boolean aux)
    {
        ffFilStopAfterId = aux;
    }

    public String getffFilStopAfterType()
    {
        return ffFilStopAfterType;
    }

    public void setffFilStopAfterType(String aux)
    {
        ffFilStopAfterType = aux;
    }

    public String getffFilStopAfter()
    {
        return ffFilStopAfter;
    }

    public void setffFilStopAfter(String aux)
    {
        ffFilStopAfter = aux;
    }

    public boolean getffStpAfterPacketsId()
    {
        return ffStpAfterPacketsId;
    }

    public void setffStpAfterPacketsId(boolean aux)
    {
        ffStpAfterPacketsId = aux;
    }

    public String getffStpAfterPackets()
    {
        return ffStpAfterPackets;
    }

    public void setffStpAfterPackets(String aux)
    {
        ffStpAfterPackets = aux;
    }

    private String ffCapSource;
    private boolean ffCapFilterId;
    private String ffCapFilter;
    private boolean ffCapAdvanceId;
    private String ffCapAdvance;
    private boolean ffCapNormal;
    private String ffCapHost;
    private String ffCapProtocol;
    private String ffCapPort;
    private String ffFilLocate;
    private boolean ffFilMultipleFileId;
    private boolean ffFilSpaceId;
    private String ffFilSpaceType;
    private String ffFilSpace;
    private boolean ffFilRingBufferId;
    private String ffFilRingBufferType;
    private boolean filRingBufferXML;
    private boolean nfsSync;
    private String ffFilRingBuffer;
    private boolean ffFilStopAfterId;
    private String ffFilStopAfterType;
    private String ffFilStopAfter;
    private boolean ffStpAfterPacketsId;
    private String ffStpAfterPackets;
}
