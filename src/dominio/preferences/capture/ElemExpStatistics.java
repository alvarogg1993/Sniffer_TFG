
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemExpStatistics. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemExpStatistics extends Element
{

    public ElemExpStatistics(preferencesBeanExport pBExport)
    {
        super("Statistics");
        try
        {
            pOperation = new preferencesOperation();
            setPBExport(pBExport);
            setAttribute("Id", pOperation.validate(getPBExport().getExpStatistics()));
            setRead();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setRead()
    {
        addContent((new Element("Read")).setText(pOperation.validate(getPBExport().getExpStatistics())));
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
    private preferencesOperation pOperation;
}
