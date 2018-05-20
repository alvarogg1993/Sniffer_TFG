
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanMeta;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemMetaMultipleFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemMetaMultipleFile extends Element
{

    public ElemMetaMultipleFile(preferencesBeanMeta pBMeta)
    {
        super("Multiple_Files");
        try
        {
            setPBMeta(pBMeta);
            pOperation = new preferencesOperation();
            setMultiple();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setMultiple()
    {
        boolean auxMultipleFile = getPBMeta().getMetMultipleFileId();
        setAttribute("Id", pOperation.validate(auxMultipleFile));
        Element eAux = new Element("Ring_Buffer");
        eAux.setAttribute("Id", pOperation.validate(getPBMeta().getMetMFRingBufferId()));
        if(auxMultipleFile)
        {
            eAux.setText(getPBMeta().getMetMFRingBuffer());
            addContent(eAux);
            eAux = new Element("Start");
            eAux.setText(getPBMeta().getMetMFStar());
            addContent(eAux);
            eAux = new Element("End");
            eAux.setText(getPBMeta().getMetMFEnd());
            addContent(eAux);
        } else
        {
            eAux.setText("0");
            addContent(eAux);
            eAux = new Element("Start");
            eAux.setText("0");
            addContent(eAux);
            eAux = new Element("End");
            eAux.setText("0");
            addContent(eAux);
        }
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
