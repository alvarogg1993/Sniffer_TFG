package presentacion.capturandoDumper;

import dominio.pcapDumper.CountPacketHandler;
import dominio.pcapDumper.SavePacketHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import presentacion.Mediador;
import presentacion.propiedadesVentana.CentrarVentana;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Clase Fcaptura.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
public class Fcaptura extends JFrame {
	private JButton jButton1;
	private JButton jButton2;
	private JLabel jLabelpk;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JLabel jLabel17;
	private JLabel jLabel18;
	private JLabel jLabel19;
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JLabel jLabel22;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JProgressBar jProgressBar1;
	private JProgressBar jProgressBar2;
	private JProgressBar jProgressBar3;
	private JProgressBar jProgressBar4;
	private JProgressBar jProgressBar5;
	private JProgressBar jProgressBar6;
	private JProgressBar jProgressBar7;
	private JProgressBar jProgressBar8;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	public CountPacketHandler RCountPacketHandler;
	public TimePacket Hilocaptura;
	public Mediador mediador;
	private Thread captureThread;
	private boolean isLiveCapture;
	protected String newline = "\n";
	private int pktotales;
	private int PACKET_COUNT;
	private static boolean pararcaptura;

	public Fcaptura(CountPacketHandler CPH) {
		setpacketcount();
		initComponents();
		new CentrarVentana(this);

		this.jProgressBar1.setMinimum(0);
		this.jProgressBar2.setMinimum(0);
		this.jProgressBar3.setMinimum(0);
		this.jProgressBar4.setMinimum(0);
		this.jProgressBar5.setMinimum(0);
		this.jProgressBar6.setMinimum(0);
		this.jProgressBar7.setMinimum(0);
		this.jProgressBar8.setMinimum(0);

		this.isLiveCapture = true;

		this.RCountPacketHandler = CPH;

		this.Hilocaptura = new TimePacket(this, 1000L, this.RCountPacketHandler);
		this.Hilocaptura.setFrameVisibles(1000);

		this.Hilocaptura.start();

		this.mediador = new Mediador();
	}

	public Fcaptura(Mediador med, CountPacketHandler CPH) {
		setpacketcount();
		initComponents();
		new CentrarVentana(this);

		this.jProgressBar1.setMinimum(0);
		this.jProgressBar2.setMinimum(0);
		this.jProgressBar3.setMinimum(0);
		this.jProgressBar4.setMinimum(0);
		this.jProgressBar5.setMinimum(0);
		this.jProgressBar6.setMinimum(0);
		this.jProgressBar7.setMinimum(0);
		this.jProgressBar8.setMinimum(0);

		this.isLiveCapture = true;

		this.RCountPacketHandler = CPH;

		this.Hilocaptura = new TimePacket(this, 1000L, this.RCountPacketHandler);
		this.Hilocaptura.setFrameVisibles(1000);

		this.Hilocaptura.start();

		this.mediador = med;
	}

	private void initComponents() {
		this.jPanel1 = new JPanel();

		this.jLabelpk = new JLabel();

		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();
		this.jLabel3 = new JLabel();
		this.jLabel4 = new JLabel();
		this.jLabel5 = new JLabel();
		this.jLabel6 = new JLabel();
		this.jLabel7 = new JLabel();
		this.jProgressBar1 = new JProgressBar();
		this.jProgressBar2 = new JProgressBar();
		this.jProgressBar3 = new JProgressBar();
		this.jProgressBar4 = new JProgressBar();
		this.jProgressBar5 = new JProgressBar();
		this.jProgressBar6 = new JProgressBar();
		this.jProgressBar7 = new JProgressBar();
		this.jProgressBar8 = new JProgressBar();
		this.jProgressBar8.setForeground(new Color(0, 153, 0));

		this.jLabel8 = new JLabel();
		this.jLabel8.setForeground(new Color(0, 0, 255));
		this.jLabel9 = new JLabel();
		this.jLabel9.setForeground(new Color(0, 0, 255));
		this.jLabel10 = new JLabel();
		this.jLabel10.setForeground(new Color(0, 0, 255));
		this.jLabel11 = new JLabel();
		this.jLabel11.setForeground(new Color(0, 0, 255));
		this.jLabel12 = new JLabel();
		this.jLabel12.setForeground(new Color(0, 0, 255));
		this.jLabel13 = new JLabel();
		this.jLabel13.setForeground(new Color(0, 0, 255));
		this.jLabel14 = new JLabel();
		this.jLabel14.setForeground(new Color(0, 0, 255));

		this.jLabel15 = new JLabel();
		this.jLabel15.setFont(new Font("Tahoma", 0, 12));
		this.jLabel16 = new JLabel();
		this.jLabel16.setFont(new Font("Tahoma", 0, 12));
		this.jLabel17 = new JLabel();
		this.jLabel17.setFont(new Font("Tahoma", 0, 12));
		this.jLabel18 = new JLabel();
		this.jLabel18.setFont(new Font("Tahoma", 0, 12));
		this.jLabel19 = new JLabel();
		this.jLabel19.setFont(new Font("Tahoma", 0, 12));
		this.jLabel20 = new JLabel();
		this.jLabel20.setFont(new Font("Tahoma", 0, 12));
		this.jLabel21 = new JLabel();
		this.jLabel21.setFont(new Font("Tahoma", 0, 12));
		this.jLabel22 = new JLabel();
		this.jLabel22.setFont(new Font("Tahoma", 1, 10));

		this.jPanel2 = new JPanel();
		this.jScrollPane1 = new JScrollPane();
		this.jScrollPane1.setAutoscrolls(false);
		this.jTextArea1 = new JTextArea();

		this.jButton1 = new JButton();
		this.jButton2 = new JButton();

		getContentPane().setLayout(new AbsoluteLayout());
		if (this.PACKET_COUNT == -1) {
			this.jProgressBar8.setEnabled(false);
			this.jProgressBar8.setStringPainted(false);
		} else {
			this.jProgressBar8.setEnabled(true);
			this.jProgressBar8.setStringPainted(true);
		}
		setTitle("Paquetes capturados");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				Fcaptura.this.exitForm(evt);
			}
		});
		this.jPanel1.setLayout(new AbsoluteLayout());

		this.jPanel1.setBorder(new TitledBorder("Estadística de Paquetes"));
		this.jPanel1.add(this.jProgressBar1, new AbsoluteConstraints(70, 30,
				60, 20));

		this.jLabel1.setText("Ethernet");
		this.jPanel1.add(this.jLabel1, new AbsoluteConstraints(10, 30, -1, -1));

		this.jLabel2.setText("Arp");
		this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(10, 60, -1, -1));

		this.jLabel3.setText("Ip");
		this.jPanel1.add(this.jLabel3, new AbsoluteConstraints(10, 90, -1, -1));

		this.jLabel4.setText("Icmp");
		this.jPanel1
				.add(this.jLabel4, new AbsoluteConstraints(10, 120, -1, -1));

		this.jLabel5.setText("Igmp");
		this.jPanel1
				.add(this.jLabel5, new AbsoluteConstraints(10, 150, -1, -1));

		this.jLabel6.setText("Tcp");
		this.jPanel1
				.add(this.jLabel6, new AbsoluteConstraints(10, 180, -1, -1));

		this.jLabel7.setText("Udp");
		this.jPanel1
				.add(this.jLabel7, new AbsoluteConstraints(10, 210, -1, -1));

		this.jPanel1.add(this.jProgressBar2, new AbsoluteConstraints(70, 60,
				60, 20));

		this.jPanel1.add(this.jProgressBar3, new AbsoluteConstraints(70, 90,
				60, 20));

		this.jPanel1.add(this.jProgressBar4, new AbsoluteConstraints(70, 120,
				60, 20));

		this.jPanel1.add(this.jProgressBar5, new AbsoluteConstraints(70, 150,
				60, 20));

		this.jPanel1.add(this.jProgressBar6, new AbsoluteConstraints(70, 180,
				60, 20));

		this.jPanel1.add(this.jProgressBar7, new AbsoluteConstraints(70, 210,
				60, 20));
		this.jPanel1.add(this.jLabel22,
				new AbsoluteConstraints(185, 10, -1, -1));
		this.jPanel1
				.add(this.jLabel8, new AbsoluteConstraints(150, 30, -1, -1));
		this.jPanel1.add(this.jLabel15,
				new AbsoluteConstraints(200, 30, -1, -1));
		this.jPanel1.add(this.jLabel20, new AbsoluteConstraints(200, 180, -1,
				-1));
		this.jPanel1
				.add(this.jLabel9, new AbsoluteConstraints(150, 60, -1, -1));
		this.jPanel1.add(this.jLabel16,
				new AbsoluteConstraints(200, 60, -1, -1));
		this.jPanel1.add(this.jLabel10,
				new AbsoluteConstraints(150, 90, -1, -1));
		this.jPanel1.add(this.jLabel10,
				new AbsoluteConstraints(150, 90, -1, -1));
		this.jPanel1.add(this.jLabel17,
				new AbsoluteConstraints(200, 90, -1, -1));
		this.jPanel1.add(this.jLabel11, new AbsoluteConstraints(150, 120, -1,
				-1));
		this.jPanel1.add(this.jLabel18, new AbsoluteConstraints(200, 120, -1,
				-1));
		this.jPanel1.add(this.jLabel12, new AbsoluteConstraints(150, 150, -1,
				-1));
		this.jPanel1.add(this.jLabel19, new AbsoluteConstraints(200, 150, -1,
				-1));
		this.jPanel1.add(this.jLabel13, new AbsoluteConstraints(150, 180, -1,
				-1));
		this.jPanel1.add(this.jLabel14, new AbsoluteConstraints(150, 210, -1,
				-1));
		this.jPanel1.add(this.jLabel21, new AbsoluteConstraints(200, 210, -1,
				-1));

		getContentPane().add(this.jPanel1,
				new AbsoluteConstraints(0, 0, 230, 250));

		this.jPanel2.setLayout(new BorderLayout());

		this.jPanel2.setBorder(new TitledBorder("Paquetes"));

		this.jTextArea1.setBackground(new Color(0, 0, 0));
		this.jTextArea1.setForeground(new Color(0, 153, 0));
		this.jScrollPane1.setViewportView(this.jTextArea1);

		this.jPanel2.add(this.jLabelpk, "North");
		this.jPanel2.add(this.jScrollPane1, "Center");

		getContentPane().add(this.jPanel2,
				new AbsoluteConstraints(238, 0, 460, 250));

		this.jButton1.setIcon(new ImageIcon(FachadaFicheroDirectorios
				.getdirectorioData("DIR_IMAGES")
				+ System.getProperty("file.separator") + "stop.png"));
		this.jButton1.setText("PARAR Captura");
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Fcaptura.this.jButton1ActionPerformed(evt);
			}
		});
		getContentPane().add(this.jButton1,
				new AbsoluteConstraints(280, 260, -1, -1));

		pack();
	}

	private void exitForm(WindowEvent evt) {
		salir();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		
		//setPararcaptura( true);
		salir();
	}

	

	public void salir() {
		this.mediador.irFinCapturePcapLib();

		this.mediador.habiliatarBHelemento(3);
		this.mediador.habiliatarBHelemento(4);

		this.mediador.deshabilitarComponenteBarraMenus(1, 1);

		this.mediador.habilitarComponenteBarraMenus(0, 1);
		this.mediador.EnabledComponenteBarraMenus(0, 3, 0, true);
		dispose();
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
	}

	public void pkrecibidos(int pktotales) {
		this.pktotales = (pktotales + 1);
	}

	public void pkether(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkARP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkIP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkICMP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkIGMP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkTCP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void pkUDP(String pakete) {
		this.jTextArea1.append(pakete + this.newline);
	}

	public void setpacketcount() {
	}

	public void borrarTextArea() {
		this.jTextArea1.setText("");
	}

	public void mostrarPackets(int numP, Vector aux) {
		int numSize = aux.size();
		for (int i = 0; i < numSize; i++) {
			int cont = numP - numSize + i;
			this.jTextArea1.append("(" + cont + ")" + aux.elementAt(i)
					+ this.newline);
		}
	}

	public void intervalo(int total, int numpackets, int ethernet, int arp,
			int ip, int icmp, int igmp, int tcp, int udp) {
		this.jLabel22.setText("Num.");
		this.jLabelpk.setText("Total paquetes :" + String.valueOf(total));

		this.jProgressBar8.setMaximum(this.PACKET_COUNT);
		this.jProgressBar8.setValue(total);
		int p_udp;
		int p_ethernet;
		int p_arp;
		int p_ip;
		int p_icmp;
		int p_igmp;
		int p_tcp;

		if (numpackets != 0) {
			p_ethernet = ethernet * 100 / numpackets;
			p_arp = arp * 100 / numpackets;
			p_ip = ip * 100 / numpackets;
			p_icmp = icmp * 100 / numpackets;
			p_igmp = igmp * 100 / numpackets;
			p_tcp = tcp * 100 / numpackets;
			p_udp = udp * 100 / numpackets;
		} else {
			p_ethernet = ethernet;
			p_arp = 0;
			p_ip = 0;
			p_icmp = 0;
			p_igmp = 0;
			p_tcp = 0;
			p_udp = 0;
		}
		this.jProgressBar1.setMaximum(numpackets);
		this.jProgressBar1.setValue(ethernet);

		this.jLabel8.setText(String.valueOf(p_ethernet) + "%");
		this.jLabel15.setText(String.valueOf(ethernet));

		this.jProgressBar2.setMaximum(numpackets);
		this.jProgressBar2.setValue(arp);

		this.jLabel9.setText(String.valueOf(p_arp) + "%");
		this.jLabel16.setText(String.valueOf(arp));

		this.jProgressBar3.setMaximum(numpackets);
		this.jProgressBar3.setValue(ip);

		this.jLabel10.setText(String.valueOf(p_ip) + "%");
		this.jLabel17.setText(String.valueOf(ip));

		this.jProgressBar4.setMaximum(numpackets);
		this.jProgressBar4.setValue(icmp);

		this.jLabel11.setText(String.valueOf(p_icmp) + "%");
		this.jLabel18.setText(String.valueOf(icmp));

		this.jProgressBar5.setMaximum(numpackets);
		this.jProgressBar5.setValue(igmp);

		this.jLabel12.setText(String.valueOf(p_igmp) + "%");
		this.jLabel19.setText(String.valueOf(igmp));

		this.jProgressBar6.setMaximum(numpackets);
		this.jProgressBar6.setValue(tcp);

		this.jLabel13.setText(String.valueOf(p_tcp) + "%");
		this.jLabel20.setText(String.valueOf(tcp));

		this.jProgressBar7.setMaximum(numpackets);
		this.jProgressBar7.setValue(udp);

		this.jLabel14.setText(String.valueOf(p_udp) + "%");
		this.jLabel21.setText(String.valueOf(udp));

		this.jTextArea1.repaint();
		repaint();
	}
}
