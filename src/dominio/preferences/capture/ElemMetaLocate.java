
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanMeta;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemMetaLocate. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemMetaLocate extends Element
{

    public ElemMetaLocate(preferencesBeanMeta pBMeta)
    {
        super("Locate");
        try
        {
            setPBMeta(pBMeta);
            pOperation = new preferencesOperation();
            setRelative();
            setAbsolute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setRelative()
    {
        Element eAux = new Element("Relative");
        eAux.setAttribute("Id", pOperation.validate(getPBMeta().getMetLocRelativeId()));
        eAux.setText(getPBMeta().getMetLocRelative());
        addContent(eAux);
    }

    private void setAbsolute()
    {
        Element eAbsolute = new Element("Absolute");
        eAbsolute.setAttribute("Id", pOperation.validate(getPBMeta().getMetLocAbsotuteId()));
        Element eAux = new Element("Path");
        eAux.setText(getPBMeta().getMetLocAbsotutePath());
        eAbsolute.addContent(eAux);
        eAux = new Element("Name");
        eAux.setText(getPBMeta().getMetLocAbsotuteName());
        eAbsolute.addContent(eAux);
        addContent(eAbsolute);
    }

    private void setPBMeta(preferencesBeanMeta aux)
    {
        pBMeta = aux;
    }

    private preferencesBeanMeta getPBMeta()
    {
        return pBMeta;
    }

    private preferencesBeanMeta pBMeta;
    private preferencesOperation pOperation;
}
