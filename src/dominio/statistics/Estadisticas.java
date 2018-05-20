package dominio.statistics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import net.sourceforge.jpcap.client.CaptureHistory;
import net.sourceforge.jpcap.net.ARPPacket;
import net.sourceforge.jpcap.net.EthernetPacket;
import net.sourceforge.jpcap.net.ICMPPacket;
import net.sourceforge.jpcap.net.IGMPPacket;
import net.sourceforge.jpcap.net.IPPacket;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;
import net.sourceforge.jpcap.net.UDPPacket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/** 
 * Clase Estadisticas. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Estadisticas
  extends JFrame
{
  protected Packet paquete;
  protected static int[] v = new int[7];
  
  public Estadisticas(CaptureHistory history)
  {
    super("Nivel Enlace/Red/Transporte");
    for (int j = 0; j < 7; j++) {
      v[j] = 0;
    }
    for (int i = 0; i < history.size(); i++)
    {
      this.paquete = history.get(i);
      new StPacketHandler(this.paquete);
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
        Estadisticas.this.exitForm(evt);
      }
    });
  }
  
  static void EtherPacket(EthernetPacket ethernetPacket)
  {
    v[0] += 1;
  }
  
  static void ARPPacket(ARPPacket arpPacket)
  {
    v[1] += 1;
  }
  
  static void IPPacket(IPPacket ipPacket)
  {
    v[2] += 1;
  }
  
  static void TCPPacket(TCPPacket tcpPacket)
  {
    v[3] += 1;
  }
  
  static void ICMPPacket(ICMPPacket icmpPacket)
  {
    v[4] += 1;
  }
  
  static void IGMPPacket(IGMPPacket igmpPacket)
  {
    v[5] += 1;
  }
  
  static void UDPPacket(UDPPacket udpPacket)
  {
    v[6] += 1;
  }
  
  private CategoryDataset createDataset()
  {
    String series1 = "ARP";
    String series2 = "TCP";
    String series3 = "ICMP";
    String series4 = "IGMP";
    String series5 = "UDP";
    String series6 = "Other";
    

    String category1 = "Protocolos";
    


    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    dataset.addValue(v[1], series1, category1);
    dataset.addValue(v[3], series2, category1);
    dataset.addValue(v[4], series3, category1);
    dataset.addValue(v[5], series4, category1);
    dataset.addValue(v[6], series5, category1);
    dataset.addValue(v[0], series6, category1);
    

    return dataset;
  }
  
  private JFreeChart createChart(CategoryDataset dataset)
  {
    JFreeChart chart = ChartFactory.createBarChart3D(
      "Link/Network/Transport Layer", 
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
