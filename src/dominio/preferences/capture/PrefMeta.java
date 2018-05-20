
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanMeta;
import org.jdom.Element;
/** 
 * Clase PrefMeta. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.capture:
//            ElemMetaLocate, ElemMetaMultipleFile

public class PrefMeta extends Element
{

    public PrefMeta(preferencesBeanMeta pBMeta)
    {
        super("MetaCapture");
        try
        {
            setPBMeta(pBMeta);
            setElemLocate();
            setElemMultipleFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setElemLocate()
    {
        addContent(new ElemMetaLocate(getPBMeta()));
    }

    private void setElemMultipleFile()
    {
        addContent(new ElemMetaMultipleFile(getPBMeta()));
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
}
