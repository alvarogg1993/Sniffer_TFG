
package dominio;

import java.io.*;

/** 
 * Clase ModoComando. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ModoComando extends Thread
{

    public ModoComando(ComandoAcciones aux)
    {
        in = new BufferedReader(new InputStreamReader(System.in));
        running = true;
        CAcciones = aux;
        setTipo(0);
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

    private void menuHelp()
    {
        System.out.println("");
        System.out.println("**********************************");
        System.out.println("\tAyuda  Sniffer I");
        System.out.println("\t  Modo Comando\t");
        System.out.println("**********************************");
        System.out.println("");
        System.out.println("**********************************");
        System.out.println("\th -> Ayuda");
        System.out.println("\t0 -> Salir");
        System.out.println("**********************************");
    }

    public void run()
    {
        while(running) 
            try
            {
                String lee = "";
                String opc = "";
                int intOpt = in.read();
                switch(intOpt)
                {
                case 48: // '0'
                    System.out.println("   (( Exit command line ))");
                    running = false;
                    if(tipo == "Scan")
                    {
                        CAcciones.endScan(true);
                        running = false;
                    } else
                    if(tipo == "Export")
                    {
                        System.out.println("   (( Salida abrupta ))");
                        CAcciones.endExport(true);
                        running = false;
                    } else
                    if(tipo == "FromFile")
                    {
                        System.out.println("   (( Salida abrupta ))");
                        CAcciones.endFromFile(true);
                        running = false;
                    } else
                    if(tipo == "Estate")
                    {
                        System.out.println("   (( Salida abrupta ))");
                        CAcciones.endState(true);
                        running = false;
                    }
                    break;

                default:
                    menuHelp();
                    break;
                }
            }
            catch(IOException e)
            {
                System.out.println("Upps un error!!");
            }
            catch(Exception e)
            {
                menuHelp();
            }
    }

    public String pedirPorTeclado()
    {
        String lee = "";
        try
        {
            lee = in.readLine();
        }
        catch(IOException e)
        {
            System.out.println("Upps un error!!");
        }
        catch(Exception e)
        {
            System.out.println("Otro error");
        }
        return lee;
    }

    public void pararLeer()
    {
        try
        {
            in.close();
            interrupt();
        }
        catch(Exception exception) { }
    }

    private ComandoAcciones CAcciones;
    private BufferedReader in;
    private char c;
    private boolean running;
    private String tipo;
}
