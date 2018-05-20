// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtilNavegador.java

package dominio.browser;

import java.io.IOException;
import java.lang.reflect.Method;
/** 
 * Clase UtilNavegador. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class UtilNavegador
{

    public UtilNavegador()
    {
    }

    public static void abrirURL(String url)
        throws IOException
    {
        String nombreSO = System.getProperty("os.name");
        try
        {
            if(nombreSO.startsWith("Mac OS"))
            {
                Class manager = Class.forName("com.apple.eio.FileManager");
                Method openURL = manager.getDeclaredMethod("openURL", new Class[] { String.class});
                openURL.invoke(null, new Object[] {
                    url
                });
            }
            if(nombreSO.startsWith("Windows"))
            {
                Runtime.getRuntime().exec((new StringBuilder("rundll32 url.dll,FileProtocolHandler ")).append(url).toString());
            } else
            {
                String navegadores[] = {
                    "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"
                };
                String navegador = null;
                for(int contador = 0; contador < navegadores.length && navegador == null; contador++)
                    if(Runtime.getRuntime().exec(new String[] {
    "which", navegadores[contador]
}).waitFor() == 0)
                        navegador = navegadores[contador];

                if(navegador == null)
                    throw new IOException("No se encuentra navegador web");
                Runtime.getRuntime().exec(new String[] {
                    navegador, url
                });
            }
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder("Error al intentar lanzar el navegador web:\n")).append(e.getLocalizedMessage()).toString());
        }
    }

    private static final String mensajeError = "Error al intentar lanzar el navegador web";
}
