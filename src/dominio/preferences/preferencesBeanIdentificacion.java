
package dominio.preferences;

import java.io.Serializable;
/** 
 * Clase preferencesBeanIdentificacion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanIdentificacion
    implements Serializable
{

    public preferencesBeanIdentificacion()
    {
        identificadores = new String[20][4];
        setDefaultSettings();
    }

    public void setDefaultSettings()
    {
        nomProtocolo = "";
    }

    public void setNomProtocolo(String nombre)
    {
        nomProtocolo = nombre;
    }

    public String getNomProtocolo()
    {
        return nomProtocolo;
    }

    public String getNivelProtocolo()
    {
        return nivel;
    }

    public void setNivelProtocolo(String niv)
    {
        nivel = niv;
    }

    public String getRutaProtocolo()
    {
        return ruta;
    }

    public void setRutaProtocolo(String rut)
    {
        ruta = rut;
    }

    public String getIdentificador(int fila, int columna)
    {
        return identificadores[fila][columna];
    }

    public void setIdentificador(int fila, int columna, String aux)
    {
        identificadores[fila][columna] = aux;
    }

    public String[][] getIdentificadores()
    {
        return identificadores;
    }

    private String nomProtocolo;
    private String identificadores[][];
    private String nivel;
    private String ruta;
}
