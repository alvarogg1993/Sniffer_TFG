
package dominio.preferences;

import java.io.Serializable;
/** 
 * Clase preferencesBeanExport. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanExport
    implements Serializable
{

    public preferencesBeanExport()
    {
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        expType = "";
        expSource = "";
        expDestination = "";
        setExpMultifile(true);
        expStatistic = false;
    }

    public String getExpType()
    {
        return expType;
    }

    public void setExpType(String aux)
    {
        expType = aux;
    }

    public String getExpSource()
    {
        return expSource;
    }

    public void setExpSource(String aux)
    {
        expSource = aux;
    }

    public String getExpDestination()
    {
        return expDestination;
    }

    public void setExpDestination(String aux)
    {
        expDestination = aux;
    }

    public boolean getExpMultifile()
    {
        return expMultifile;
    }

    public void setExpMultifile(boolean aux)
    {
        expMultifile = aux;
    }

    public boolean getExpStatistics()
    {
        return expStatistic;
    }

    public void setExpStatistics(boolean aux)
    {
        expStatistic = aux;
    }

    private String expType;
    private String expSource;
    private String expDestination;
    private boolean expMultifile;
    private boolean expStatistic;
}
