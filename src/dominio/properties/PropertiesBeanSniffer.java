
package dominio.properties;

import java.io.Serializable;
/** 
 * Clase PropertiesBeanSniffer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PropertiesBeanSniffer
    implements Serializable
{

    public PropertiesBeanSniffer()
    {
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        setWinX("0");
        setWinY("0");
        setWinHeight("600");
        setWinWidth("800");
        setTableView("Num,Size,Time,MAC Src,MAC Dest,Frame,Protocol,IP Src,IP Dest,Port Src,Port Dest,SEQ,ACK,Length");
    }

    public String getWinX()
    {
        return WinX;
    }

    public void setWinX(String aux)
    {
        WinX = aux;
    }

    public String getWinY()
    {
        return WinY;
    }

    public void setWinY(String aux)
    {
        WinY = aux;
    }

    public String getWinHeight()
    {
        return WinHeight;
    }

    public void setWinHeight(String aux)
    {
        WinHeight = aux;
    }

    public String getWinWidth()
    {
        return WinWidth;
    }

    public void setTableView(String aux)
    {
        TableView = aux;
    }

    public String getTableView()
    {
        return TableView;
    }

    public void setWinWidth(String aux)
    {
        WinWidth = aux;
    }

    private String WinX;
    private String WinY;
    private String WinHeight;
    private String WinWidth;
    private String TableView;
}
