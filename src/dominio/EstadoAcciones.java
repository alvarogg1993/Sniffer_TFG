// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 


package dominio;

import java.io.*;

/** 
 * Clase EstadoAcciones. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class EstadoAcciones extends Thread
{

    public EstadoAcciones(ComandoAcciones aux)
    {
        in = new BufferedReader(new InputStreamReader(System.in));
        running = true;
        CAcciones = aux;
        setTipo(0);
        paradaAutomatica = true;
    }

    public void setTipo(int auxTipo)
    {
        if(auxTipo == 1)
            tipo = "Scan";
        else
        if(auxTipo == 2)
            tipo = "Export";
        else
        if(auxTipo == 3)
            tipo = "FromFile";
        else
        if(auxTipo == 4)
            tipo = "Estate";
        else
            tipo = "error";
    }

    public String getTipo()
    {
        return tipo;
    }

    public void run()
    {
        while(running) 
            try
            {
                if(tipo == "Scan" && FachadaDominio.getEndCapture())
                    running = false;
                if(tipo == "Export" && !FachadaDominio.getRunningExport())
                    running = false;
                if(tipo == "FromFile" && FachadaDominio.getEndFromFile())
                    running = false;
                if(tipo == "Estate" && !FachadaDominio.getRunningExport())
                    running = false;
                sleep(50L);
            }
            catch(Exception e)
            {
                System.out.println("Upps 2 error!!");
            }
        if(tipo == "Scan" && paradaAutomatica)
        {
            CAcciones.endScan(false);
            System.out.println("Upor!!");
        } else
        if(tipo == "Export" && paradaAutomatica)
            CAcciones.endExport(false);
        else
        if(tipo == "FromFile" && paradaAutomatica)
            CAcciones.endFromFile(false);
        else
        if(tipo == "Estate" && paradaAutomatica)
            CAcciones.endFromFile(false);
    }

    public void setEstado(boolean aux)
    {
        running = false;
        paradaAutomatica = false;
    }

    private ComandoAcciones CAcciones;
	private BufferedReader in;
    private boolean running;
    private String tipo;
    private boolean paradaAutomatica;
}
