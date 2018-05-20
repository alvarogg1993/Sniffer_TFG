
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanExport;
import org.jdom.Element;
/** 
 * Clase PrefExport. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.capture:
//            ElemExpFile, ElemExpStatistics

public class PrefExport extends Element
{

    public PrefExport(preferencesBeanExport pBExport)
    {
        super("ExportPreferences");
        try
        {
            setPBExport(pBExport);
            setElemFile();
            setElemStatistics();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setElemFile()
    {
        addContent(new ElemExpFile(getPBExport()));
    }

    private void setElemStatistics()
    {
        addContent(new ElemExpStatistics(getPBExport()));
    }

    private void setPBExport(preferencesBeanExport aux)
    {
        pBExport = aux;
    }

    private preferencesBeanExport getPBExport()
    {
        return pBExport;
    }

    private preferencesBeanExport pBExport;
}
