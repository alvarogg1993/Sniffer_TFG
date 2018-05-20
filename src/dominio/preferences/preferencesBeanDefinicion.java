// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   preferencesBeanDefinicion.java

package dominio.preferences;

import java.io.Serializable;
import presentacion.preferencias.PreferenciasDefinicion;
/** 
 * Clase preferencesBeanDefinicion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanDefinicion
    implements Serializable
{

    public preferencesBeanDefinicion()
    {
        numColumnas = 8;
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        numCampos = 0;
        numColumnas = 8;
        nomProtocolo = "";
        RFC = "";
        camposClave = "";
        nivel = 0;
    }

    public int getNumCampos()
    {
        return numCampos;
    }

    public int getNumColumnas()
    {
        return numColumnas;
    }

    public void setNumCampos(int aux)
    {
        numCampos = aux;
    }

    public void setNivel(int aux)
    {
        nivel = aux;
    }

    public String getNomProtocolo()
    {
        return nomProtocolo;
    }

    public void setNomProtocolo(String aux)
    {
        nomProtocolo = aux;
    }

    public String getRFCProtocolo()
    {
        return RFC;
    }

    public int getNivelProtocolo()
    {
        return nivel;
    }

    public void setRFCProtocolo(String aux)
    {
        RFC = aux;
    }

    public String getCamposClave()
    {
        return camposClave;
    }

    public void setCamposClave(String aux)
    {
        camposClave = aux;
    }

    public Object getObjetoTabla(int fila, int columna)
    {
        return valores[fila][columna];
    }

    public void setObjetoTabla(int fila, int columna, Object aux)
    {
        valores[fila][columna] = aux;
    }

    public void setTabla(Object tabla[][])
    {
        valores = new Object[numCampos][numColumnas];
        if(tabla != null)
            valores = tabla;
    }

    public Object[][] getTabla()
    {
        return valores;
    }

    public void setNivelProtocolo(int aux)
    {
        nivel = aux;
    }

    public void setValoresDefinicion(boolean fichero)
    {
        if(!fichero)
        {
            setNumCampos(PreferenciasDefinicion.getNumCamposProtocolo());
            setNomProtocolo(PreferenciasDefinicion.getNombreProtocolo());
            setRFCProtocolo(PreferenciasDefinicion.getRFCProtocolo());
            setCamposClave(PreferenciasDefinicion.getCamposClave());
            setNivelProtocolo(PreferenciasDefinicion.getNivelProtocolo());
            setTabla(PreferenciasDefinicion.getTabla());
        }
    }

    private int numCampos;
    private int numColumnas;
    private String nomProtocolo;
    private String RFC;
    private String camposClave;
    private int nivel;
    private Object valores[][];
}
