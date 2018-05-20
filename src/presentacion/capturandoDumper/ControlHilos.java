
package presentacion.capturandoDumper;

import presentacion.Mediador;
/** 
 * Clase ControlHilos. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.capturandoDumper:
//            Fcaptura

public class ControlHilos extends Thread
{

    public ControlHilos(int hilos, Mediador m)
    {
        i = hilos;
        med = m;
    }

    public void run()
    {
        do
            try
            {
                if(Thread.activeCount() < i)
                {
                    med.getFCapture().dispose();
                    med.irFinCapture();
                    med.irFinCapturePcapLib();
                    stop();
                }
                sleep(500L);
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    private int i;
    private Mediador med;
}
