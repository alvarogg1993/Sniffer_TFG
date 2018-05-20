
package dominio.preferences.definicion;

import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemDefProtocolo. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemDefProtocolo extends Element
{

    public ElemDefProtocolo(preferencesBeanDefinicion pBDefinicion)
    {
        super("CamposProtocolo");
        try
        {
            pOperation = new preferencesOperation();
            setPBDefinicion(pBDefinicion);
            setNombreProtocolo();
            setNivelProtocolo();
            setCamposProtocolo();
            setCamposClaveProtocolo();
            setRFCProtocolo();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setPBDefinicion(preferencesBeanDefinicion aux)
    {
        pBDefinicion = aux;
    }

    private preferencesBeanDefinicion getPBDefinicion()
    {
        return pBDefinicion;
    }

    private void setTabla()
    {
        for(int i = 0; i < getPBDefinicion().getNumCampos(); i++)
        {
            Element eAux = new Element("Campo1");
            setValores(i);
        }

    }

    private void setNombreProtocolo()
    {
        addContent((new Element("Protocolo")).setText(getPBDefinicion().getNomProtocolo()));
    }

    private void setCamposProtocolo()
    {
        addContent((new Element("NumeroCampos")).setText(String.valueOf(getPBDefinicion().getNumCampos())));
    }

    private void setCamposClaveProtocolo()
    {
        addContent((new Element("CamposClave")).setText(getPBDefinicion().getCamposClave()));
    }

    private void setRFCProtocolo()
    {
        addContent((new Element("RFC")).setText(getPBDefinicion().getRFCProtocolo()));
    }

    private void setNivelProtocolo()
    {
        addContent((new Element("Nivel")).setText(String.valueOf(getPBDefinicion().getNivelProtocolo())));
    }

    private void setValores(int val)
    {
        addContent((new Element("IDCampo")).setText(getPBDefinicion().getObjetoTabla(val, 0).toString()));
        addContent((new Element("NombreCampo")).setText(getPBDefinicion().getObjetoTabla(val, 1).toString()));
        addContent((new Element("Tama\361oCampo")).setText(getPBDefinicion().getObjetoTabla(val, 2).toString()));
        addContent((new Element("ValorDefectoCampo")).setText(getPBDefinicion().getObjetoTabla(val, 3).toString()));
        addContent((new Element("DescripcionCampo")).setText(getPBDefinicion().getObjetoTabla(val, 4).toString()));
        addContent((new Element("TipoDato")).setText(getPBDefinicion().getObjetoTabla(val, 5).toString()));
        addContent((new Element("Opcional")).setText(getPBDefinicion().getObjetoTabla(val, 6).toString()));
        addContent((new Element("Otro")).setText(getPBDefinicion().getObjetoTabla(val, 7).toString()));
    }

    private preferencesBeanDefinicion pBDefinicion;
    private preferencesOperation pOperation;
}
