
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemExpFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemExpFile extends Element
{

    public ElemExpFile(preferencesBeanExport pBExport)
    {
        super("File");
        try
        {
            pOperation = new preferencesOperation();
            setPBExport(pBExport);
            setType();
            setSource();
            setDestination();
            setMultipleFiles();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setType()
    {
        addContent((new Element("Type")).setText(getPBExport().getExpType()));
    }

    private void setSource()
    {
        Element eAux = new Element("Source");
        eAux.setText(getPBExport().getExpSource());
        addContent(eAux);
    }

    private void setDestination()
    {
        addContent((new Element("Destination")).setText(getPBExport().getExpDestination()));
    }

    private void setMultipleFiles()
    {
        addContent((new Element("Multiple_Files")).setText(pOperation.validate(getPBExport().getExpMultifile())));
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
