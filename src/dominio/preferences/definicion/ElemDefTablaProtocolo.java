
package dominio.preferences.definicion;

import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemDefTablaProtocolo. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemDefTablaProtocolo extends Element
{

    public ElemDefTablaProtocolo(preferencesBeanDefinicion pBDefinicion, int campo)
    {
        super((new StringBuilder("Campo")).append(String.valueOf(campo)).toString());
        try
        {
            pOperation = new preferencesOperation();
            setPBDefinicion(pBDefinicion);
            setValores(campo);
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

    private void setValores(int val)
    {
        val--;
        if(getPBDefinicion().getObjetoTabla(val, 0) == null)
            addContent((new Element("IDCampo")).setText("null"));
        else
            addContent((new Element("IDCampo")).setText(getPBDefinicion().getObjetoTabla(val, 0).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 1) == null)
            addContent((new Element("NombreCampo")).setText("null"));
        else
            addContent((new Element("NombreCampo")).setText(getPBDefinicion().getObjetoTabla(val, 1).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 2) == null)
            addContent((new Element("Tama\361oCampo")).setText("null"));
        else
            addContent((new Element("Tama\361oCampo")).setText(getPBDefinicion().getObjetoTabla(val, 2).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 3) == null)
            addContent((new Element("ValorDefectoCampo")).setText("null"));
        else
            addContent((new Element("ValorDefectoCampo")).setText(getPBDefinicion().getObjetoTabla(val, 3).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 4) == null)
            addContent((new Element("DescripcionCampo")).setText("null"));
        else
            addContent((new Element("DescripcionCampo")).setText(getPBDefinicion().getObjetoTabla(val, 4).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 5) == null)
            addContent((new Element("TipoDato")).setText("null"));
        else
            addContent((new Element("TipoDato")).setText(getPBDefinicion().getObjetoTabla(val, 5).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 6) == null)
            addContent((new Element("Opcional")).setText("No"));
        else
            addContent((new Element("Opcional")).setText(getPBDefinicion().getObjetoTabla(val, 6).toString()));
        if(getPBDefinicion().getObjetoTabla(val, 7) == null)
            addContent((new Element("CampoReferenciado")).setText("null"));
        else
            addContent((new Element("CampoReferenciado")).setText(getPBDefinicion().getObjetoTabla(val, 7).toString()));
    }

    private String convertirBinario(String cadena, String tipo)
    {
        if(tipo.equals("Booleano"))
            return convertirBooleano(cadena);
        if(tipo.equals("Numerico"))
            return convertirNumerico(cadena);
        if(tipo.equals("Alfanumerico"))
            return convertirAlfanumerico(cadena);
        else
            return null;
    }

    private String convertirAlfanumerico(String cadena)
    {
        String binario = "";
        char ac[];
        int j = (ac = cadena.toCharArray()).length;
        for(int i = 0; i < j; i++)
        {
            char letra = ac[i];
            binario = (new StringBuilder(String.valueOf(binario))).append(String.format(Integer.toBinaryString(letra), new Object[0])).toString();
        }

        return binario;
    }

    private String convertirNumerico(String cadena)
    {
        return String.format("%16s", new Object[] {
            Integer.toBinaryString(Integer.valueOf(cadena).intValue())
        });
    }

    private String convertirBooleano(String cadena)
    {
        if(cadena.equals("verdadero") || cadena.equals("true"))
            return "1";
        else
            return "0";
    }

    private preferencesBeanDefinicion pBDefinicion;
    private preferencesOperation pOperation;
}
