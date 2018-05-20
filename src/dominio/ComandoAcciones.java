

package dominio;

import servicioAccesoDatos.FabricaAccesoDatos;
import servicioAccesoDatos.FabricaAccesoDatosIF;
import servicioAccesoDatos.FachadaFichero;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/** 
 * Clase ComandoAcciones. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ComandoAcciones
{

    public ComandoAcciones()
    {
        CommandMode = new ModoComando(this);
        AccionsState = new EstadoAcciones(this);
    }

    public void runScan(String ficheroXML)
    {
        System.out.println("--> Run Scan");
        System.out.println((new StringBuilder("----> Fichero Parametros => ")).append(ficheroXML).toString());
        cargardirectorios();
        try
        {
            if(runScanGetPreferencesCapture(ficheroXML))
            {
            	FachadaDominio.getCapDispositivosPcapLibDes();	//Rellenamos alldevs para que pueda comprobar más adelante que es un dispositivo.
                FachadaDominio.openCapturaPcapLib();
                FachadaDominio.setPrefCapturaDumper();
                FachadaDominio.setListenerPcapLib();
                FachadaDominio.setControlPacket(true);
                FachadaDominio.startCapturaPcapLib();
                setCommandListeners(1);
            } else
            {
                System.out.println("\n-----> FALLO en el fichero de parametrizaci\363n o no existe");
                endScan(false);
            }
        }
        catch(Exception exception) { }
    }

    public void runScanDispo(String ficheroXML, int dispositivo)
    {
        System.out.println("--> Run Scan");
        System.out.println((new StringBuilder("----> Fichero Parametros => ")).append(ficheroXML).toString());
        cargardirectorios();
        try
        {
            String strDispo = FachadaDominio.isDispositivo(dispositivo).toString();
            if(strDispo != null)
            {
                if(runScanGetPreferencesCapture(ficheroXML))
                {
                    FachadaDominio.getPrefBeanCaptura().setCapDispositive(strDispo);
                    FachadaDominio.openCapturaPcapLib();
                    FachadaDominio.setPrefCapturaDumper();
                    FachadaDominio.setListenerPcapLib();
                    FachadaDominio.startCapturaPcapLib();
                    setCommandListeners(1);
                } else
                {
                    System.out.println("\n-----> FALLO en el fichero de parametrizaci\363n o no existe");
                    endScan(false);
                }
            } else
            {
                System.out.println("\n-----> FALLO en la lectura del dispositivo");
                endScan(false);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean runScanGetPreferencesCapture(String ficheroXML)
    {
        FachadaDominio.setPreferencesCapture(ficheroXML);
        return FachadaDominio.getPreferencesCapture(false);
    }

    @SuppressWarnings("deprecation")
	public void endScan(boolean grabar)
    {
        if(grabar)
        {
            AccionsState.setEstado(false);
            System.out.println("--> Parado por usuario");
            FachadaDominio.stopCapturaDumperCommand();
            FachadaDominio.saveMetaCaptura();
            System.out.println("\n--> End Scan");
            System.out.println("-> Fin.Modo Texto");
        } else
        {
            FachadaDominio.stopCapturaDumperCommandOnly();
            System.out.println("\n--> End Scan");
            System.out.println("-> Fin Modo Texto");
            CommandMode.stop();
            System.exit(1);
        }
    }

    public void runExport(String ficheroXML)
    {
        System.out.println("--> Run Export");
        System.out.println((new StringBuilder("----> Fichero Parametros => ")).append(ficheroXML).toString());
        cargardirectorios();
        try
        {
            setCommandListeners(2);
            FachadaDominio.setPreferencesExport(ficheroXML);
            FachadaDominio.getPreferencesExport();
            FachadaDominio.saveXMLOffline(FachadaDominio.getPrefBeanExport());
        }
        catch(Exception e)
        {
            System.out.println("Algo fallo en la exportacion");
        }
    }

    public void endExport(boolean grabar)
    {
        System.out.println("--> End Export");
        System.out.println("-> Fin Modo Texto");
        System.exit(1);
    }

    public void runFromFile(String ficheroXML)
    {
        System.out.println("--> Run FromFile");
        System.out.println((new StringBuilder("----> Fichero Parametros => ")).append(ficheroXML).toString());
        cargardirectorios();
        try
        {
            FachadaDominio.setPreferencesFromFile(ficheroXML);
            FachadaDominio.getPreferencesFromFile();
            FachadaDominio.saveMetaOrPcapToFile();
        }
        catch(Exception e)
        {
            System.out.println("Algo fallo en la captura desde fichero...");
        }
    }

    public void endFromFile(boolean grabar)
    {
        System.out.println("--> End FromFile");
        System.out.println("-> Fin Modo Texto");
        System.exit(1);
    }

    public void runState(String ficheroXML)
    {
        System.out.println("--> Run State");
        System.out.println((new StringBuilder("----> Fichero Parametros => ")).append(ficheroXML).toString());
        cargardirectorios();
        try
        {
            setCommandListeners(4);
            FachadaDominio.saveXMLfromEstate(ficheroXML);
        }
        catch(Exception e)
        {
            System.out.println("Algo fallo en la captura desde fichero...");
        }
    }

    public void endState(boolean grabar)
    {
        System.out.println("--> End Estate");
        System.out.println("-> Fin Modo Texto");
        System.exit(1);
    }

    private void setCommandListeners(int tipo)
    {
        CommandMode.setTipo(tipo);
        AccionsState.setTipo(tipo);
        CommandMode.start();
        AccionsState.start();
    }

    public int pedirDispositivoCaptura(boolean pedirDispo)
    {
        String lee = "";
        try
        {
            String dispo[] = FachadaDominio.getCapDispositivosPcapLibDes();
            int opc;
            if(pedirDispo)
            {
                for(opc = -1; opc < 0 || opc > dispo.length;)
                {
                    System.out.println("\nDispositivos disponibles:\n");
                    for(int i = 0; i < dispo.length; i++)
                    {
                        String name = dispo[i].toString().trim();
                        System.out.println((new StringBuilder(" ")).append(i + 1).append(" -> ").append(name).toString());
                    }

                    System.out.println("\n Pulse 0 para Salir -> ");
                    System.out.print("\nSelecciona el dispositivo: ");
                    lee = CommandMode.pedirPorTeclado();
                    opc = Integer.parseInt(lee);
                    if(opc < 0 || opc > dispo.length)
                        System.out.println("\n!!!Opcion no valida: \n");
                }

            } else
            {
                opc = 0;
                System.out.println("\nDispositivos disponibles:\n");
                for(int i = 0; i < dispo.length; i++)
                {
                    String name = dispo[i].toString().trim();
                    System.out.println((new StringBuilder(" ")).append(i + 1).append(" -> ").append(name).toString());
                }

            }
            return opc;
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
    }
    
    
	public void cargardirectorios() {
		FabricaAccesoDatosIF fabrica = new FabricaAccesoDatos();
		String auxDirectorios = FachadaFicheroDirectorios
				.getdirectorioData("DIR_PROPERTIES")
				+ System.getProperty("file.separator") + "userfiles.property";
		FachadaFichero f = fabrica.creaFachadaFichero("directorios",
				auxDirectorios);
		Object[] directoriosnom = f.leer();
	}

    private ModoComando CommandMode;
    private EstadoAcciones AccionsState;
}
