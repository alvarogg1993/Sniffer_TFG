
package presentacion.comandos;

import java.awt.Font;
import javax.swing.JButton;
import presentacion.Mediador;
/** 
 * Clase CBChequear. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBChequear extends JButton
    implements Comando
{

    public CBChequear(Mediador mediador, String ventana)
    {
        setText("   Chequear  ");
        setMnemonic('S');
        setFont(new Font("Arial", 1, 12));
        setAlignmentY(0.5F);
        titulo = ventana;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        boolean estado = true;
        if(titulo.equals("ChequearDefinicionProtocolo"))
        {
            mediador.ChequearDefinicionProtocolo();
            estado = false;
        }
    }

    private Mediador mediador;
    private String titulo;
}
