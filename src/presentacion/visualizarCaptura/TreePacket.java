package presentacion.visualizarCaptura;

import dominio.pcapDumper.analyzer.ARPAnalyzer;
import dominio.pcapDumper.analyzer.EthernetAnalyzer;
import dominio.pcapDumper.analyzer.FTPAnalyzer;
import dominio.pcapDumper.analyzer.HTTPAnalyzer;
import dominio.pcapDumper.analyzer.ICMPAnalyzer;
import dominio.pcapDumper.analyzer.IPv4Analyzer;
import dominio.pcapDumper.analyzer.IPv6Analyzer;
import dominio.pcapDumper.analyzer.JDPacketAnalyzer;
import dominio.pcapDumper.analyzer.OtroAnalyzer;
import dominio.pcapDumper.analyzer.POP3Analyzer;
import dominio.pcapDumper.analyzer.PacketAnalyzer;
import dominio.pcapDumper.analyzer.SMTPAnalyzer;
import dominio.pcapDumper.analyzer.SSHAnalyzer;
import dominio.pcapDumper.analyzer.TCPAnalyzer;
import dominio.pcapDumper.analyzer.TelnetAnalyzer;
import dominio.pcapDumper.analyzer.UDPAnalyzer;
import dominio.preferences.identificacion.PrefIdentificacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.nio.Buffer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.PcapPacket;
//import jpcap.packet.Packet;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase FTreePacket. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
public class TreePacket
{
  private static DefaultMutableTreeNode Paquete;
  private JTree tree;
  private static int numero;
  static JBuffer buffer;
private static byte[] auxConver;
private static int size;

  
  
  public TreePacket(int numero, PcapPacket paquete)
  {
    Paquete = new DefaultMutableTreeNode("Packet");
    DefaultMutableTreeNode child = new DefaultMutableTreeNode("Num :" + numero);
    Paquete.add(child);
    new TreeHandler(paquete);
    
    DefaultTreeModel modeloArbol = new DefaultTreeModel(Paquete);
    this.tree = new JTree(modeloArbol);
    this.tree.setCellRenderer(new MiRendererDeArbol());
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepk(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new PacketAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        Paquete.add(LayerP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo Ethernet.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkether(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new EthernetAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo ARP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkARP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new ARPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo IP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkIP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new IPv4Analyzer();
    if (packetAnalyzer.isAnalyzable(packet))
    {
      packetAnalyzer.analyze(packet);
    }
    else
    {
      packetAnalyzer = new IPv6Analyzer();
      packetAnalyzer.analyze(packet);
    }
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
    Paquete.add(LayerP);
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo ICMP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkICMP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new ICMPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo TCP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkTCP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new TCPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  
  static void treepkUDP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new UDPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + 
          " : " + String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo FTP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkFTP(PcapPacket packet)
  {
    String[][] aux = new String[7][2];
    
     buffer=packet;
      size=packet.size();
    
    JDPacketAnalyzer packetAnalyzer = new FTPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
  if ((packet.size() > 0) && (valueNames == null))
    {
    	
      aux[0][0] = "Type";
      auxConver = new byte[1];
      auxConver[0] = buffer.getByte(0);
      
    
      aux[0][1] = String.valueOf(byteArrayToInt(auxConver));
      aux[1][0] = "Info Count";
      auxConver = new byte[3];
      auxConver[0] = buffer.getByte(1);
      auxConver[1] = buffer.getByte(2);
      auxConver[2] = buffer.getByte(3);
      aux[1][1] = String.valueOf(byteArrayToInt(auxConver));
      aux[2][0] = "Null";
      aux[2][1] = "nulo";
      aux[3][0] = "Seq #";
      auxConver = new byte[2];
      auxConver[0] = buffer.getByte(5);
      auxConver[1] = buffer.getByte(6);
      aux[3][1] = String.valueOf(byteArrayToInt(auxConver));
      aux[4][0] = "Null";
      aux[4][1] = "nulo";
      aux[5][0] = "Filler count";
      auxConver = new byte[1];
      auxConver[0] = buffer.getByte(8);
      aux[5][1] = String.valueOf(byteArrayToInt(auxConver));
      aux[6][0] = "Data";
      aux[6][1] = "";
      for (int j = 9; j < size; j++)
          
      
      {
        int tmp293_292 = 1; String[] tmp293_291 = aux[6];tmp293_291[tmp293_292] = (tmp293_291[tmp293_292] + String.valueOf((char)buffer.getByte(j)));
      }
    }
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
    if (aux != null) {
      for (int i = 0; i < aux.length; i++)
      {
        String strAux = aux[i][0];
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          aux[i][1]);
        LayerP.add(childP);
      }
    }
  }
  
  public static final int byteArrayToInt(byte[] b)
  {
    if (b.length == 1) {
      return b[0] & 0xFF;
    }
    if (b.length == 2) {
      return ((b[0] & 0xFF) << 8) + (
        b[1] & 0xFF);
    }
    if (b.length == 3) {
      return ((b[0] & 0xFF) << 16) + (
        (b[1] & 0xFF) << 8) + (
        b[2] & 0xFF);
    }
    if (b.length == 4) {
      return (b[0] << 24) + (
        (b[1] & 0xFF) << 16) + (
        (b[2] & 0xFF) << 8) + (
        b[3] & 0xFF);
    }
    return 0;
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo HTTP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkHTTP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new HTTPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null)
    {
      String strAux;
      for (int i = 0; i < valueNames.length; i++) {
        strAux = valueNames[i].toString();
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo POP3.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkPOP3(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new POP3Analyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo SMTP.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkSMTP(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new SMTPAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo SSH.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkSSH(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new SSHAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo Telnet.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkTelnet(PcapPacket packet)
  {
    JDPacketAnalyzer packetAnalyzer = new TelnetAnalyzer();
    packetAnalyzer.analyze(packet);
    String protocolName = packetAnalyzer.getProtocolName();
    String[] valueNames = packetAnalyzer.getValueNames();
    
    DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
    Paquete.add(LayerP);
    if (valueNames != null) {
      for (int i = 0; i < valueNames.length; i++)
      {
        String strAux = valueNames[i].toString();
        DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
          String.valueOf(packetAnalyzer.getValue(strAux)));
        LayerP.add(childP);
      }
    }
  }
  /** Metodo   se visualiza los datos del paquete que hemos marcado en el arbol de datos de la parte
   * inferior de la pantalla, cuando es de tipo desconocido que no corresponde a ninguno anterior.
   * @param PcapPacket packet 
   * @return sin valor de retorno
   */
  static void treepkOtro(PcapPacket packet, PrefIdentificacion pref)
  {
    String protocolName = "Desconocido";

    OtroAnalyzer packetAnalyzer = new OtroAnalyzer();
    
   byte[] buffers = new byte [packet.getTotalSize()];
	 packet.transferStateAndDataTo(buffers);
    String path = pref.getIdentificacionProtocolo(buffers);
    if (!path.equals(""))
    {
    	OtroAnalyzer.analizar(buffers, path, pref);
        
    	
      protocolName = packetAnalyzer.getProtocolName();
      String[][] valueNames = packetAnalyzer.getValores();
      
      DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
      Paquete.add(LayerP);
      if (valueNames != null) {
        for (int i = 0; i < valueNames.length; i++)
        {
          String strAux = String.valueOf(valueNames[i][0]);
          DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
            String.valueOf(valueNames[i][1]));
          LayerP.add(childP);
        }
      }
      while (pref.getTamaF1o() < size)

      {
    	  byte[] data = restoData(pref.getTamaF1o(), buffers);

        path = pref.getIdentificacionProtocolo(data);
        if (!path.equals(""))
        {
          OtroAnalyzer.analizar(data, path, pref);
          protocolName = packetAnalyzer.getProtocolName();
          valueNames = packetAnalyzer.getValores();
          
          LayerP = new DefaultMutableTreeNode(protocolName);
          Paquete.add(LayerP);
          if (valueNames != null) {
            for (int i = 0; i < valueNames.length; i++)
            {
              String strAux = String.valueOf(valueNames[i][0]);
              DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
                String.valueOf(valueNames[i][1]));
              LayerP.add(childP);
            }
          }
        }
      }
    }

    if ((path.equals("")) && (size > 0))
    {
      String dat = "";
      protocolName = "Data";

      for (int h = 0; h < size; h++) {
    	  dat = dat + (char)packet.getByte(h);
 
      }
      DefaultMutableTreeNode LayerP = new DefaultMutableTreeNode(protocolName);
      Paquete.add(LayerP);
      String strAux = String.valueOf(dat);
      DefaultMutableTreeNode childP = new DefaultMutableTreeNode(strAux + " : " + 
        dat);
      LayerP.add(childP);
    }
  }
  
  public JTree Arbol()
  {
    return this.tree;
  }
  
  class MiRendererDeArbol
    extends JLabel
    implements TreeCellRenderer
  {
    private ImageIcon imgPaquete = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "packet.png");
    private ImageIcon imgLayer = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "layer.png");
    private ImageIcon imghoja = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "hoja.png");
    private ImageIcon imgFlags = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "Flag.png");
    private ImageIcon imgTransport = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "Ids.png");
    private boolean seleccionado;
    
    public MiRendererDeArbol() {}
    
    public Component getTreeCellRendererComponent(JTree arbol, Object valor, boolean seleccionado, boolean expandido, boolean rama, int fila, boolean conFoco)
    {
      DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)valor;
      String texto = (String)nodo.getUserObject();
      this.seleccionado = seleccionado;
      if (!seleccionado) {
        setForeground(Color.black);
      } else {
        setForeground(Color.blue);
      }
      if (texto.equals("Packet"))
      {
        setFont(new Font("MS Sans Serif", 3, 12));
        setIcon(this.imgPaquete);
      }
      else if ((texto.equals("Ethernet Frame")) || 
        (texto.equals("ARP / RARP")) || 
        (texto.equals("IP v4")) || 
        (texto.equals("IP v6")) || 
        (texto.equals("ICMP")) || 
        (texto.equals("IGMP Protocol")) || 
        (texto.equals("TCP")) || 
        (texto.equals("UDP")))
      {
        setIcon(this.imgLayer);
        setFont(new Font("MS Sans Serif", 3, 12));
      }
      else if (texto.equals("Flags"))
      {
        setIcon(this.imgFlags);
        setFont(new Font("MS Sans Serif", 3, 11));
      }
      else if ((texto.equals("FTP")) || 
        (texto.equals("HTTP")) || 
        (texto.equals("POP3")) || 
        (texto.equals("SMTP")) || 
        (texto.equals("SSH")) || 
        (texto.equals("SSH")) || 
        (texto.equals("Telnet")))
      {
        setIcon(this.imgTransport);
        setFont(new Font("MS Sans Serif", 3, 11));
      }
      else
      {
        setFont(new Font("MS Sans Serif", 0, 11));
        setIcon(this.imghoja);
      }
      setText(texto);
      
      return this;
    }
    
    public void paint(Graphics g)
    {
      Icon currentI = getIcon();
      
      Color color = this.seleccionado ? Color.white : Color.white;
      g.setColor(color);
      

      g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
      
      super.paint(g);
    }
  }
  
  public static String convertirBinario(byte[] data)
  {
    String convertido = "";
    for (int i = 0; i < data.length; i++) {
      convertido = convertido + (char)data[i];
    }
    return convertido;
  }
  
  public static byte[] restoData(int tamanho, byte[] data)
  {
    byte[] aux = new byte[data.length - tamanho];
    int j = 0;
    for (int i = tamanho; i < data.length; i++)
    {
      aux[j] = data[i];
      j++;
    }
    return aux;
  }
}
