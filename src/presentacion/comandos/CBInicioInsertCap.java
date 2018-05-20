// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CBInicioInsertCap.java

package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBInicioInsertCap. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBInicioInsertCap extends JButton
    implements Comando
{

    public CBInicioInsertCap(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("insercap.png").toString()));
        setToolTipText("Iniciar Insercion Paquetes Capturados");
        setMnemonic('I');
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioInsertCap(true);
    }

    private Mediador mediador;
}
