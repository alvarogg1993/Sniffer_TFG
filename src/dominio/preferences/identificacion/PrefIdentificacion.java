
package dominio.preferences.identificacion;

import dominio.preferences.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
/** 
 * Clase PrefIdentificacion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PrefIdentificacion
{

    public PrefIdentificacion()
    {
        listaIdentificacion = new ArrayList();
        tamaF1o = 0;
        leer = new preferencesFileRead();
        fich = new File("./files/definiciones");
        File todos[] = fich.listFiles();
        for(int i = 0; i < todos.length; i++)
            listaIdentificacion.add(leer.leerXMLIdentificacion(todos[i]));

    }

    public ArrayList getListaIdentificacion()
    {
        return listaIdentificacion;
    }

    public int getTamaF1oListaIdentificacion()
    {
        return listaIdentificacion.size();
    }

    public preferencesBeanIdentificacion getElementoListaIdentificacion(int pos)
    {
        return (preferencesBeanIdentificacion)listaIdentificacion.get(pos);
    }

    public String getIdentificacionProtocolo(byte cadena[])
    {
        String sms = "";
        boolean coincide = true;
        for(Iterator iterator = listaIdentificacion.iterator(); iterator.hasNext();)
        {
            preferencesBeanIdentificacion aux = (preferencesBeanIdentificacion)iterator.next();
            for(int i = 0; aux.getIdentificador(i, 0) != null && coincide; i++)
                coincide = checkContiene(cadena, aux.getIdentificador(i, 0), Integer.valueOf(aux.getIdentificador(i, 1)).intValue(), Integer.valueOf(aux.getIdentificador(i, 2)).intValue(), aux.getIdentificador(i, 3));

            if(coincide)
                return aux.getRutaProtocolo();
            coincide = true;
        }

        return sms;
    }

    private boolean checkContiene(byte protocolo[], String valor, int inicio, int tamaF1o, String tipo)
    {
        boolean iguales = true;
        if(protocolo.length != 0)
        {
            if(tipo.equals("Booleano"))
            {
                if(protocolo[inicio] == 0)
                    return true;
                if(protocolo[inicio] == 1)
                    return true;
            }
            if(tipo.equals("Alfanumerico"))
            {
                String aux = null;
                for(int i = inicio; i < inicio + tamaF1o; i++)
                    aux = (new StringBuilder(String.valueOf(aux))).append((char)protocolo[i]).toString();

                String val[] = valor.split(",");
                if(val.length != 0)
                {
                    for(int i = 0; i < val.length; i++)
                        if(val[i].equals(aux))
                            return true;

                }
            }
            if(tipo.equals("Numerico"))
            {
                byte valAux[] = new byte[tamaF1o];
                int j = 0;
                for(int i = inicio; i < inicio + tamaF1o; i++)
                {
                    valAux[j] = protocolo[i];
                    j++;
                }

                int valInt = byteArrayToInt(valAux);
                String val1[] = valor.split(",");
                String val2[] = valor.split("-");
                if(val1.length != 0 && val2.length == 1)
                {
                    for(int i = 0; i < val1.length; i++)
                        if(Integer.valueOf(val1[i]).intValue() == valInt)
                            return true;

                }
                if(val2.length == 2 && val1.length == 1)
                {
                    if(Integer.valueOf(val2[0]).intValue() > Integer.valueOf(val2[1]).intValue())
                    {
                        String aux = val2[0];
                        val2[0] = val2[1];
                        val2[1] = aux;
                    }
                    if(valInt >= Integer.valueOf(val2[0]).intValue() && valInt <= Integer.valueOf(val2[1]).intValue())
                        return true;
                }
            }
        }
        return false;
    }

    public static final int byteArrayToInt(byte b[])
    {
        if(b.length == 1)
            return b[0] & 0xff;
        if(b.length == 2)
            return ((b[0] & 0xff) << 8) + (b[1] & 0xff);
        if(b.length == 3)
            return ((b[0] & 0xff) << 16) + ((b[1] & 0xff) << 8) + (b[2] & 0xff);
        if(b.length == 4)
            return (b[0] << 24) + ((b[1] & 0xff) << 16) + ((b[2] & 0xff) << 8) + (b[3] & 0xff);
        else
            return 0;
    }

    public String[][] getCamposProtocoloIdentificado(String file, byte arr[])
    {
        int contPos = 0;
        File aux = new File(file);
        preferencesBeanDefinicion prefAux = leer.leerXMLProtocoloIdentificado(aux);
        String datos[][] = new String[prefAux.getNumCampos()][2];
        for(int i = 0; i < prefAux.getNumCampos(); i++)
        {
            datos[i][0] = String.valueOf(prefAux.getObjetoTabla(i, 1));
            datos[i][1] = setValor(String.valueOf(prefAux.getObjetoTabla(i, 5)), contPos, Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2))).intValue() / 8, arr);
            contPos += Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2))).intValue() / 8;
            tamaF1o += Integer.valueOf(String.valueOf(prefAux.getObjetoTabla(i, 2))).intValue() / 8;
        }

        return datos;
    }

    private static String setValor(String tipo, int inicio, int tamaF1o, byte protocolo[])
    {
        if(tipo.equals("Booleano"))
        {
            if(protocolo[inicio] == 0)
                return "Verdadero";
            if(protocolo[inicio] == 1)
                return "False";
        }
        if(tipo.equals("Alfanumerico"))
        {
            String aux = "";
            for(int i = inicio; i < inicio + tamaF1o; i++)
                aux = (new StringBuilder(String.valueOf(aux))).append((char)protocolo[i]).toString();

            return aux;
        }
        if(tipo.equals("Numerico"))
        {
            byte valAux[] = new byte[tamaF1o];
            int j = 0;
            for(int i = inicio; i < inicio + tamaF1o; i++)
            {
                valAux[j] = protocolo[i];
                j++;
            }

            int valInt = byteArrayToInt(valAux);
            return String.valueOf(valInt);
        } else
        {
            return "";
        }
    }

    private String getCampoLeido(char cadena[], int tamaF1o, int inicio, String tipo)
    {
        char campo[] = new char[tamaF1o];
        int j = 0;
        if(tipo.equals("Alfanumerico"))
        {
            for(int i = inicio; i < inicio + tamaF1o; i++)
            {
                campo[j] = cadena[i];
                j++;
            }

        }
        if(tipo.equals("Booleano"))
        {
            if(cadena[inicio] == '1')
                return "true";
            if(cadena[inicio] == '0')
                return "false";
        }
        String auxCampo;
        if(tipo.equals("Numerico"))
        {
            j = 0;
            for(int i = inicio; i < inicio + tamaF1o; i++)
            {
                campo[j] = cadena[i];
                j++;
            }

            String codificacion = "";
            for(int i = 0; i < campo.length; i++)
            {
                String cero = "0";
                for(codificacion = (new StringBuilder(String.valueOf(codificacion))).append(Integer.toBinaryString(campo[i])).toString(); codificacion.length() < 8;)
                {
                    cero = (new StringBuilder(String.valueOf(cero))).append(codificacion).toString();
                    codificacion = cero;
                    cero = "0";
                }

            }

            int pot = 0;
            int res = 0;
            char aux[] = codificacion.toCharArray();
            for(int i = aux.length - 1; i > 0; i--)
            {
                if(aux[i] == '1')
                    res = (int)((double)res + Math.pow(2D, pot));
                pot++;
            }

            return String.valueOf(res);
        } else
        {
            return auxCampo = new String(campo);
        }
    }

    public int getTamaF1o()
    {
        return tamaF1o;
    }

    private ArrayList listaIdentificacion;
    private File fich;
    private int tamaF1o;
    private preferencesFileRead leer;
}
