
package dominio.export.script;

import java.io.PrintStream;

/** 
 * Clase GenerarBat. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class GenerarBat extends FachadaGenerarScript
{

    public GenerarBat()
    {
    }

    public void setParam(String tipo, String preferencias)
    {
        String aux = (new StringBuilder(String.valueOf(getTipoCommand(tipo)))).append(" \"").append(preferencias).append("\"").toString();
        param = aux;
    }

    public void writeToDiskCabecera()
    {
        try
        {
            buffer.println("@echo off");
            buffer.println("rem *");
            buffer.println("REM LEONARDO GARCIA Y JOSE RAMON GUTIERREZ");
            buffer.println("rem Fichero creado por Sniffer");
            buffer.println("rem *");
            buffer.println("rem *");
            buffer.println("rem Fichero de captura automatica en modo comando");
            buffer.println("rem *");
            buffer.println("");
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }

    public void writeToDiskVariables()
    {
        try
        {
            buffer.println((new StringBuilder("set ruta=")).append(getRuta()).toString());
            buffer.println((new StringBuilder("set ejecutable=")).append(getEjecutable()).toString());
            buffer.println("");
            buffer.println((new StringBuilder("set param=")).append(getParam()).toString());
            buffer.println((new StringBuilder("set mvm=")).append(getMvm()).toString());
            buffer.println("");
            buffer.println("cd %ruta%");
            buffer.println("set PATH=.;%PATH%");
            buffer.println("");
            buffer.println("set java=java");
            buffer.println("if not '%mvm%' == '0' set java=java -Xmx%mvm%m");
            buffer.println("");
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }

    public void writeToDiskCabeceraVisible()
    {
        try
        {
            buffer.println("echo.");
            buffer.println("echo           \332\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\277");
            buffer.println("echo           \263                Proyecto Fin de Carrera                  \263");
            buffer.println("echo           \263                      SNIFFER I                          \263");
            buffer.println("echo           \263                                                         \263");
            buffer.println("echo           \263         Leonardo Garcia  - Jose Ramon Gutierrez         \263");
            buffer.println("echo           \263                                                         \263");
            buffer.println("echo           \300\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\304\331");
            buffer.println("echo.");
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }

    public void writeToDiskEjecutable()
    {
        try
        {
            buffer.println("");
            buffer.println("%java% -jar %ejecutable% %param%");
            buffer.println("");
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }
}
