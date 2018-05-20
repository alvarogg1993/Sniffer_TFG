
package dominio.export.script;

import java.io.PrintStream;

/** 
 * Clase GenerarSh. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class GenerarSh extends FachadaGenerarScript
{

    public GenerarSh()
    {
    }

    public void setParam(String tipo, String preferencias)
    {
        String aux = (new StringBuilder(String.valueOf(getTipoCommand(tipo)))).append(" ").append(preferencias).toString();
        param = aux;
    }

    public void writeToDiskCabecera()
    {
        try
        {
            buffer.println("#!/bin/bash");
            buffer.println("# *");
            buffer.println("# LEONARDO GARCIA Y JOSE RAMON GUTIERREZ");
            buffer.println("# Fichero creado por Sniffer");
            buffer.println("# *");
            buffer.println("# *");
            buffer.println("# Fichero de captura automatica en modo comando");
            buffer.println("# *");
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
            buffer.println((new StringBuilder("ruta=\"")).append(getRuta()).append("\"").toString());
            buffer.println((new StringBuilder("ejecutable=")).append(getEjecutable()).toString());
            buffer.println("");
            buffer.println((new StringBuilder("param=\"")).append(getParam()).append("\"").toString());
            buffer.println((new StringBuilder("mvm=")).append(getMvm()).toString());
            buffer.println("");
            buffer.println("cd $ruta");
            buffer.println("#set PATH=.;%PATH%");
            buffer.println("");
            buffer.println("if [[ $mvm = \"0\" ]]");
            buffer.println("then");
            buffer.println(" java=java");
            buffer.println("else");
            buffer.println(" java=\"java -Xmx\"$mvm\"m\"");
            buffer.println("fi");
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
            buffer.println("echo \"\"");
            buffer.println("echo \"           ###########################################################\"");
            buffer.println("echo \"           #                Proyecto Fin de Carrera                  #\"");
            buffer.println("echo \"           #                      SNIFFER I                          #\"");
            buffer.println("echo \"           #                                                         #\"");
            buffer.println("echo \"           #         Leonardo Garcia  - Jose Ramon Gutierrez         #\"");
            buffer.println("echo \"           #                                                         #\"");
            buffer.println("echo \"           ###########################################################\"");
            buffer.println("echo \"\"");
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
            buffer.println("$java -jar $ejecutable $param");
            buffer.println("");
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }
}
