
package dominio.preferences.definicion;

import presentacion.Mediador;
import presentacion.preferencias.PreferenciasDefinicion;
/** 
 * Clase PreferencesCheckDefinicion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferencesCheckDefinicion
{
	
	

    public PreferencesCheckDefinicion()
    {
        med = new Mediador();
        valores = new Object[PreferenciasDefinicion.getNumCamposProtocolo()][5];
        boolean valido = true;
        valores = PreferenciasDefinicion.getTabla();
        valido = checkCamposDefinicion();
        if(valido)
            valido = checkColumnasRellenas();
        if(valido)
            valido = checkTamaF1oCampos();
        if(valido)
            valido = checkValoresBoolean();
        if(valido)
            valido = checkIsNumeric();
        if(valido)
            valido = checkCamposClave();
        if(valido)
            valido = checkCamposOpcionales();
    }

    private boolean checkCamposDefinicion()
    {
        String mensaje = "Faltan campos por rellenar";
        if(PreferenciasDefinicion.getNombreProtocolo().equals(""))
        {
            med.lanzaraviso("Falta el nombre del protocolo", "Error");
            return false;
        }
        if(PreferenciasDefinicion.getRFCProtocolo().equals(""))
        {
            med.lanzaraviso("Falta el RFC del protocolo", "Error");
            return false;
        }
        if(PreferenciasDefinicion.getCamposClave().equals(""))
        {
            med.lanzaraviso("Falta especificar los campos clave", "Error");
            return false;
        }
        if(PreferenciasDefinicion.getNumCamposProtocolo() == 0)
        {
            med.lanzaraviso("Faltan la cantidad de campos del protocolo", "Error");
            return false;
        }
        if(PreferenciasDefinicion.getNivelProtocolo() == 0)
        {
            med.lanzaraviso("Falta especificar el nivel del protocolo", "Error");
            return false;
        } else
        {
            return true;
        }
    }

    private boolean checkColumnasRellenas()
    {
        boolean ok = true;
        String mensaje = "Las columnas de NOMBRE DE CAMPO, TAMAÑO y TIPO DE DATO deben estar completamente rellenadas";
        for(int j = 1; j <= 5 && ok; j++)
        {
            for(int i = 0; i < valores.length && ok; i++)
                if(valores[i][j] != null && String.valueOf(valores[i][j]) != "")
                    ok = true;
                else
                    ok = false;

            if(j == 2)
                j += 2;
        }

        if(!ok)
            med.lanzaraviso(mensaje, "Error");
        return ok;
    }

    private boolean checkValoresBoolean()
    {
        boolean ok = true;
        String mensaje = "Valores validos: TRUE, VERDADERO,FALSE O FALSO";
        String booleano;
		for(int i = 0; i < valores.length && ok; i++)
            if(valores[i][3] == null || String.valueOf(valores[i][3]).equals(""))
                ok = true;
            else
            if(String.valueOf(valores[i][5]).equals("Booleano")){
            	
                if(String.valueOf(valores[i][3]).toLowerCase().equalsIgnoreCase("false") || String.valueOf(valores[i][3]).toLowerCase().equalsIgnoreCase("falso") || String.valueOf(valores[i][3]).toLowerCase().equalsIgnoreCase("true") || String.valueOf(valores[i][3]).toLowerCase().equalsIgnoreCase("verdadero"))
                {
                    ok = true;
                } else
                {
                    ok = false;
                    med.lanzaraviso(mensaje, "Error");
                }}

        return ok;
    }

    private boolean checkTamaF1oCampos()
    {
        int j = 2;
        String mensaje = "Tamanho de campos Incorrecto a partir del Campo: ";
        int sum = 0;
        int sum2 = 0;
        int resto = 0;
        boolean ok = true;
        for(int i = 0; i < valores.length && ok; i++)
            if(sum == 0)
            {
                sum = Integer.parseInt(String.valueOf(valores[i][j]));
                if(sum == 32)
                {
                    sum = 0;
                } else
                {
                    resto = 32 - sum;
                    ok = true;
                }
            } else
            {
                sum2 = Integer.parseInt(String.valueOf(valores[i][j]));
                if(resto == sum2)
                {
                    sum = 0;
                    resto = 0;
                } else
                if(resto > sum2)
                {
                    resto -= sum2;
                    sum += sum2;
                } else
                {
                    ok = false;
                    mensaje = (new StringBuilder(String.valueOf(mensaje))).append(String.valueOf(i + 1)).toString();
                    med.lanzaraviso(mensaje, "Error");
                }
            }

        if(resto != 0 && ok)
        {
            mensaje = "El tamanho de los campos en su totalidad debe ser multiplo de 32";
            med.lanzaraviso(mensaje, "Error");
        }
        return ok;
    }

    private boolean checkCamposClave()
    {
        String claves = PreferenciasDefinicion.getCamposClave();
        boolean ok = true;
        String str[] = claves.split("-");
        for(int i = 0; i < str.length && ok; i++)
        {
            ok = isNumeric(str[i]);
            if(ok)
                ok = camposValidos(str[i]);
        }

        if(!ok)
        {
            String mensaje = "El campo clave no debe ser superior al numero de campos escojidos";
            med.lanzaraviso(mensaje, "Error");
        }
        return ok;
    }

    private boolean checkNumericos(String valor)
    {
        if(valor.indexOf("-") > 0)
        {
            String valores[] = valor.split("-");
            if(valores.length != 2)
            {
                String mensaje = "Especificacion de Rango incorrecta Ej: 12-27";
                med.lanzaraviso(mensaje, "Error");
            } else
            if(isNumeric(valores[0]) && isNumeric(valores[1]) && Integer.parseInt(valores[0]) < Integer.parseInt(valores[1]))
                return true;
        }    
        else if(valor.indexOf(",") > 0) {
                String valores[] = valor.split(",");
                for(int i = 0; i < valores.length; i++)
                    if(!isNumeric(valores[i]))
                        return false;

                return true;
            }
        
        else if(isNumeric(valor)){
                return true;
        }
        return false;
    }

    private boolean isNumeric(String cadena)
    {
        try
        {
            Integer.parseInt(cadena);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private boolean camposValidos(String campo)
    {
        return Integer.parseInt(campo) > 0 && Integer.parseInt(campo) <= PreferenciasDefinicion.getNumCamposProtocolo();
    }

    private boolean checkIsNumeric()
    {
        boolean ok = true;
        String mensaje = "Para tipos de datos Numericos no son validos los caracteres";
        for(int i = 0; i < valores.length && ok; i++)
            if(valores[i][3] == null || String.valueOf(valores[i][3]).equalsIgnoreCase(""))
                ok = true;
            else
            if(String.valueOf(valores[i][5]).equalsIgnoreCase("Numerico"))
                if(checkNumericos(String.valueOf(valores[i][3])))
                {
                    ok = true;
                } else
                {
                    ok = false;
                    med.lanzaraviso(mensaje, "Error");
                }

        return ok;
    }

    private boolean checkValorBinario()
    {
        boolean ok = true;
        for(int i = 0; i < PreferenciasDefinicion.getNumCamposProtocolo() && ok; i++)
        {
            if(PreferenciasDefinicion.getValorTabla(i, 5).equals("Booleano") && !PreferenciasDefinicion.getValorTabla(i, 4).equals("1"))
                ok = false;
            PreferenciasDefinicion.getValorTabla(i, 4).equals("Numerico");
            PreferenciasDefinicion.getValorTabla(i, 4).equals("Alfanumerico");
        }

        if(!ok)
        {
            String mensaje = "Numero de bits incorrecto para el tipo de dato";
            med.lanzaraviso(mensaje, "Error");
        }
        return ok;
    }

    private boolean checkCamposOpcionales()
    {
        String mensaje = "Campos Claves Opcionales";
        String claves = PreferenciasDefinicion.getCamposClave();
        boolean ok = true;
        String str[] = claves.split("-");
        for(int i = 0; i < str.length && ok; i++)
        	if (PreferenciasDefinicion.getValorTabla(Integer.valueOf(str[i]).intValue(), 6)!=null){
            if(PreferenciasDefinicion.getValorTabla(Integer.valueOf(str[i]).intValue() - 1, 6).equals("Si"))
                ok = false;
        	}else 
        		med.lanzaraviso("Elija Si o No en la columna Campos Opcionales", "Error");
        		
        if(!ok)
        {
            mensaje = "El campo clave no debe ser superior al numero de campos escojidos";
            med.lanzaraviso(mensaje, "Error");
        }
        return ok;
    }

    Object valores[][];
    Mediador med;
}
