package dominio.statistics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import net.sourceforge.jpcap.client.CaptureHistory;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/** 
 * Clase StAplicationLayer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class StAplicationLayer
  extends JFrame
{
  protected Packet paquete;
  protected static int[] vaplicationlayer = new int[7];
  protected static String[][] hack = new String[10][2];
  
  public StAplicationLayer(CaptureHistory history)
  {
    super("Estadisticas Nivel Aplicacion");
    for (int j = 0; j < 7; j++) {
      vaplicationlayer[j] = 0;
    }
    for (int i = 0; i < history.size(); i++)
    {
      this.paquete = history.get(i);
      new StPacketHandlerTCP(this.paquete);
    }
    CategoryDataset dataset = createDataset();
    JFreeChart chart = createChart(dataset);
    chart = customizeChart(chart);
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(500, 270));
    getContentPane().add(chartPanel);
    pack();
    setVisible(true);
    
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        StAplicationLayer.this.exitForm(evt);
      }
    });
  }
  
  static void TCPPacket(TCPPacket tcpPacket)
  {
    if ((tcpPacket.getSourcePort() == 80) || (tcpPacket.getDestinationPort() == 80)) {
      vaplicationlayer[0] += 1;
    } else if ((tcpPacket.getSourcePort() == 20) || (tcpPacket.getDestinationPort() == 20) || 
      (tcpPacket.getSourcePort() == 21) || (tcpPacket.getDestinationPort() == 21)) {
      vaplicationlayer[1] += 1;
    } else if ((tcpPacket.getSourcePort() == 23) || (tcpPacket.getDestinationPort() == 23)) {
      vaplicationlayer[2] += 1;
    } else if ((tcpPacket.getSourcePort() == 22) || (tcpPacket.getDestinationPort() == 22)) {
      vaplicationlayer[3] += 1;
    } else if ((tcpPacket.getSourcePort() == 25) || (tcpPacket.getDestinationPort() == 25)) {
      vaplicationlayer[4] += 1;
    } else if ((tcpPacket.getSourcePort() == 110) || (tcpPacket.getDestinationPort() == 110)) {
      vaplicationlayer[5] += 1;
    } else {
      vaplicationlayer[6] += 1;
    }
  }
  
  private CategoryDataset createDataset()
  {
    String series1 = "HTTP";
    String series2 = "FTP";
    String series3 = "Telnet";
    String series4 = "SSH";
    String series5 = "SMTP";
    String series6 = "POP3";
    String series7 = "Others";
    

    String category1 = "Aplcation Layer Protocols";
    


    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    dataset.addValue(vaplicationlayer[0], series1, category1);
    dataset.addValue(vaplicationlayer[1], series2, category1);
    dataset.addValue(vaplicationlayer[2], series3, category1);
    dataset.addValue(vaplicationlayer[3], series4, category1);
    dataset.addValue(vaplicationlayer[4], series5, category1);
    dataset.addValue(vaplicationlayer[5], series6, category1);
    dataset.addValue(vaplicationlayer[6], series7, category1);
    

    return dataset;
  }
  
  private JFreeChart createChart(CategoryDataset dataset)
  {
    JFreeChart chart = ChartFactory.createBarChart3D(
      "Aplication Layer", 
      "Tipo Protocolo", 
      "Numero de paquetes", 
      dataset, 
      PlotOrientation.VERTICAL, 
      true, 
      true, 
      false);
    
    return chart;
  }
  
  private JFreeChart customizeChart(JFreeChart chart)
  {
    return chart;
  }
  
  private void exitForm(WindowEvent evt)
  {
    dispose();
  }
}
