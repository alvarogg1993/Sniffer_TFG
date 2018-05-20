
package dominio.preferences;

import java.io.Serializable;
import java.util.ArrayList;
/** 
 * Clase preferencesBeanExportInsercion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanExportInsercion
    implements Serializable
{

    public preferencesBeanExportInsercion()
    {
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        ipOrigen = new ArrayList();
        ipDestino = new ArrayList();
        macOrigen = new ArrayList();
        macDestino = new ArrayList();
    }

    public void setIpOrigen(String text)
    {
        ipOrigen.add(text);
    }

    public void setIpDestino(String text)
    {
        ipDestino.add(text);
    }

    public void setMacOrigen(String text)
    {
        macOrigen.add(text);
    }

    public void setMacDestino(String text)
    {
        macDestino.add(text);
    }

    public ArrayList getIpOrigen()
    {
        return ipOrigen;
    }

    public ArrayList getIpDestino()
    {
        return ipDestino;
    }

    public ArrayList getMacOrigen()
    {
        return macOrigen;
    }

    public ArrayList getMacDestino()
    {
        return macDestino;
    }

    ArrayList ipOrigen;
    ArrayList ipDestino;
    ArrayList macOrigen;
    ArrayList macDestino;
}
