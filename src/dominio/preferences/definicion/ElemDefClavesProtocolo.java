
package dominio.preferences.definicion;

import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemDefClaveProtocolo. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemDefClavesProtocolo extends Element
{

    public ElemDefClavesProtocolo(preferencesBeanDefinicion pBDefinicion, int clave, int id)
    {
        super((new StringBuilder("Clave")).append(String.valueOf(id)).toString());
        try
        {
            pOperation = new preferencesOperation();
            setPBDefinicion(pBDefinicion);
            setCamposClave(clave);
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

    private void setNumeroCampoClave(String campo)
    {
        addContent((new Element("NumeroCampo")).setText(campo));
    }

    private void setValorCampoClave(String valor, String tipo, String tamaF1o)
    {
        addContent((new Element("Valor")).setText(valor));
        addContent((new Element("Tipo")).setText(tipo));
    }

    private void setPosicionInicioCampoClave(String valor)
    {
        addContent((new Element("PosicionInicio")).setText(valor));
    }

    private void setTamaF1oCampoClave(String valor)
    {
        addContent((new Element("Tama\361o")).setText(String.valueOf(Integer.valueOf(valor).intValue() / 8)));
    }

    private void setCamposClave(int i)
    {
        setValorCampoClave(valorCampoClave(i), String.valueOf(pBDefinicion.getObjetoTabla(i, 5)), String.valueOf(pBDefinicion.getObjetoTabla(i, 2)));
        setPosicionInicioCampoClave(valorComenzarLeer(i));
        setTamaF1oCampoClave(String.valueOf(pBDefinicion.getObjetoTabla(i, 2)));
    }

    private String[] valoresCampoClave()
    {
        String str[] = pBDefinicion.getCamposClave().split("-");
        return str;
    }

    private String valorComenzarLeer(int fila)
    {
        int cont = 0;
        for(int i = 0; i < fila; i++)
            cont += Integer.valueOf(String.valueOf(pBDefinicion.getObjetoTabla(i, 2))).intValue();

        cont /= 8;
        return String.valueOf(cont);
    }

    private String valorCampoClave(int fila)
    {
        return String.valueOf(pBDefinicion.getObjetoTabla(fila, 3));
    }

    private String convertirAlfanumerico(String cadena, String tamaF1o)
    {
        String binario = "";
        String cero = "0";
        char ac[];
        int j = (ac = cadena.toCharArray()).length;
        for(int i = 0; i < j; i++)
        {
            char letra = ac[i];
            String aux;
            for(aux = String.format(Integer.toBinaryString(letra), new Object[0]); aux.length() < 8; aux = cero.concat(aux));
            binario = (new StringBuilder(String.valueOf(binario))).append(aux).toString();
        }

        for(int tam = Integer.valueOf(tamaF1o).intValue(); binario.length() != tam; binario = cero.concat(binario));
        return binario;
    }

    private String convertirBinario(String cadena, String tipo, String tamaF1o)
    {
        if(tipo.equals("Booleano"))
            return convertirBooleano(cadena);
        if(tipo.equals("Numerico"))
            return convertirNumerico(cadena, tamaF1o);
        if(tipo.equals("Alfanumerico"))
            return convertirAlfanumerico(cadena, tamaF1o);
        else
            return null;
    }

    private String convertirNumerico(String cadena, String tamaF1o)
    {
        String cero = "0";
        int tam = Integer.valueOf(tamaF1o).intValue();
        String binario;
        for(binario = String.format(Integer.toBinaryString(Integer.valueOf(cadena).intValue()), new Object[0]); binario.length() != tam; binario = cero.concat(binario));
        return binario;
    }

    private String convertirBooleano(String cadena)
    {
        if(cadena.equals("verdadero") || cadena.equals("true"))
            return "1";
        else
            return "0";
    }

    private String recogerDato(String cadena, String tipo, String tamaF1o)
    {
        if(tipo.equals("Booleano"))
            return recogerBooleano(cadena);
        if(tipo.equals("Numerico"))
            return recogerNumerico(cadena, tamaF1o);
        if(tipo.equals("Alfanumerico"))
            return recogerAlfanumerico(cadena, tamaF1o);
        else
            return null;
    }

    private String recogerBooleano(String cadena)
    {
        if(cadena.equals("verdadero") || cadena.equals("true"))
            return "1";
        else
            return "0";
    }

    private String recogerNumerico(String cadena, String tamaF1o)
    {
        String aux = "";
        char cad[] = cadena.toCharArray();
        int tam = Integer.valueOf(tamaF1o).intValue() / 8;
        for(int i = 0; i < tam; i++)
            aux = (new StringBuilder(String.valueOf(aux))).append(cad[i]).toString();

        return aux;
    }

    private String recogerAlfanumerico(String cadena, String tamaF1o)
    {
        String binario = "";
        String aux = "";
        String cero = "0";
        char ac[];
        int j = (ac = cadena.toCharArray()).length;
        for(int i = 0; i < j; i++)
        {
            char letra = ac[i];
            aux = (new StringBuilder(String.valueOf(aux))).append(letra).toString();
        }

        for(int tam = Integer.valueOf(tamaF1o).intValue() / 8; aux.length() < tam; aux = cero.concat("0"));
        return aux;
    }

    private preferencesBeanDefinicion pBDefinicion;
    private preferencesOperation pOperation;
}
