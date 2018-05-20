
package dominio.preferences.definicion;

import dominio.preferences.preferencesBeanDefinicion;
import org.jdom.Element;
/** 
 * Clase PrefDef. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.definicion:
//            ElemDefProtocolo, ElemDefClavesProtocolo, ElemDefTablaProtocolo

public class PrefDef extends Element
{

    public PrefDef(preferencesBeanDefinicion pBDefinicion)
    {
        super("DefinicionProtocolo");
        try
        {
            setPBDefinicion(pBDefinicion);
            setElemDefClaves();
            setElemDef();
            setTablaDef();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setElemDef()
    {
        addContent(new ElemDefProtocolo(getPBDefinicion()));
    }

    private void setElemDefClaves()
    {
        String claves = pBDefinicion.getCamposClave();
        String tabla[] = claves.split("-");
        for(int i = 0; i < tabla.length; i++)
            addContent(new ElemDefClavesProtocolo(getPBDefinicion(), Integer.valueOf(tabla[i]).intValue() - 1, i));

    }

    private void setTablaDef()
    {
        for(int i = 0; i < pBDefinicion.getNumCampos(); i++)
        {
            int val = i + 1;
            addContent(new ElemDefTablaProtocolo(getPBDefinicion(), val));
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

    private preferencesBeanDefinicion pBDefinicion;
}
