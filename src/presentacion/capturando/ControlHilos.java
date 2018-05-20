
package presentacion.capturando;

import presentacion.Mediador;
import presentacion.capturandoDumper.Fcaptura;
/** 
 * Clase ControlHilos. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
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
                    stop();
                }
                sleep(1000L);
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    private int i;
    private Mediador med;
}
