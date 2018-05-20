
package dominio.pcapDumper.rules;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import net.sourceforge.jpcap.net.Packet;
/** 
 * Clase Rules. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcap.rules:
//            ClassificationRules, TableAlerts, XMLlog, Rule

public class Rules
{

    public Rules(TableAlerts TablaAlertas, XMLlog log)
    {
        try
        {
            VectorRules = new Vector();
            lines = 0;
            int trat_cabezera = 0;
            this.TablaAlertas = TablaAlertas;
            this.log = log;
            VectorClasification = new Vector();
            for(BufferedReader lector = new BufferedReader(new FileReader("./rules/classification.config")); lector.ready();)
            {
                String cadena = lector.readLine();
                System.out.println((new StringBuilder("=>")).append(cadena).toString());
                if(cadena.indexOf("#") == 0 || cadena.length() == 0)
                {
                    System.out.println("Linea de comentario");
                    boolean comentario = true;
                } else
                {
                    boolean comentario = false;
                    inicio = cadena.indexOf(":");
                    cadena = cadena.substring(inicio + 1);
                    cadena = cadena.trim();
                    inicio = cadena.indexOf(",");
                    String des_corta = cadena.substring(0, inicio);
                    cadena = cadena.substring(inicio + 1);
                    inicio = cadena.indexOf(",");
                    String des_larga = cadena.substring(0, inicio);
                    cadena = cadena.substring(inicio + 1);
                    String prioridad = cadena;
                    System.out.println((new StringBuilder("\ndes_corta:")).append(des_corta).toString());
                    System.out.println((new StringBuilder("\ndes_larga:")).append(des_larga).toString());
                    System.out.println((new StringBuilder("\nprioriadad:")).append(prioridad).toString());
                    ClassificationRules l_classification = new ClassificationRules();
                    l_classification.setDescripcion_corta(des_corta);
                    l_classification.setDescripcion_larga(des_larga);
                    l_classification.setDescripcion_Prioridad(prioridad);
                    VectorClasification.add(l_classification);
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void LoadRules(File fichero)
    {
        System.out.println("Principio del tratamiento de fichero Rules");
        System.getProperties().list(System.out);
        try
        {
            this.fichero = fichero.getAbsolutePath();
            if(this.fichero.indexOf(".rules") == -1)
                throw new Exception("Fichero no de reglas");
            lines = 0;
            int trat_cabezera = 0;
            for(BufferedReader lector = new BufferedReader(new FileReader(fichero)); lector.ready();)
            {
                String cadena = lector.readLine();
                System.out.println((new StringBuilder("=>")).append(cadena).toString());
                if(cadena.indexOf("#") == 0 || cadena.length() == 0)
                {
                    System.out.println("Linea de comentario");
                    boolean comentario = true;
                } else
                {
                    boolean comentario = false;
                    Rule NuevaRegla = new Rule();
                    for(String char_ini = cadena.substring(0, 1); char_ini.compareTo("(") != 0; char_ini = cadena.substring(0, 1))
                    {
                        inicio = cadena.indexOf(" ", 0);
                        System.out.println((new StringBuilder("Pos")).append(inicio).toString());
                        if(trat_cabezera == 0)
                        {
                            accion = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("Accion :")).append(accion).toString());
                            NuevaRegla.setAccion(accion);
                        }
                        if(trat_cabezera == 1)
                        {
                            protocolo = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("Protocolo :")).append(protocolo).toString());
                            NuevaRegla.setProtocolo(protocolo);
                        }
                        if(trat_cabezera == 2)
                        {
                            IpOrigen = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("IPOrigen:")).append(IpOrigen).toString());
                            if(IpOrigen.equals(new String("$EXTERNAL_NET")))
                            {
                                IpOrigen = System.getProperty("EXTERNAL_NET");
                                System.out.println((new StringBuilder("IpOrigen:")).append(IpOrigen).toString());
                                NuevaRegla.setIpSrc(IpOrigen);
                            }
                            if(IpOrigen.equals(new String("$HOME_NET")))
                            {
                                IpOrigen = System.getProperty("HOME_NET");
                                System.out.println((new StringBuilder("IpOrigen:")).append(IpOrigen).toString());
                                NuevaRegla.setIpSrc(IpOrigen);
                            }
                            NuevaRegla.setIpSrc(IpOrigen);
                        }
                        if(trat_cabezera == 3)
                        {
                            PuertoSrc = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("Puerto Origen:")).append(PuertoSrc).toString());
                            NuevaRegla.setPortSrc(PuertoSrc);
                        }
                        if(trat_cabezera == 4)
                        {
                            Direccion = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("Direccion:")).append(Direccion).toString());
                            NuevaRegla.setDireccion(Direccion);
                        }
                        if(trat_cabezera == 5)
                        {
                            IpDestino = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("IpDestino:")).append(IpDestino).toString());
                            if(IpDestino.compareTo(new String("$HOME_NET")) == 0)
                            {
                                IpDestino = System.getProperty("HOME_NET");
                                System.out.println((new StringBuilder("IpDestino:")).append(IpDestino).toString());
                                NuevaRegla.setIpDst(IpDestino);
                            } else
                            {
                                NuevaRegla.setIpDst(IpDestino);
                            }
                            if(IpDestino.equals(new String("$EXTERNAL_NET")))
                            {
                                IpDestino = System.getProperty("EXTERNAL_NET");
                                System.out.println((new StringBuilder("IpDestino:")).append(IpDestino).toString());
                                NuevaRegla.setIpDst(IpDestino);
                            } else
                            {
                                NuevaRegla.setIpDst(IpDestino);
                            }
                        }
                        if(trat_cabezera == 6)
                        {
                            PuertoDst = cadena.substring(0, inicio);
                            System.out.println((new StringBuilder("Puerto Dst:")).append(PuertoDst).toString());
                            NuevaRegla.setPortDest(PuertoDst);
                        }
                        cadena = cadena.substring(inicio + 1);
                        System.out.println((new StringBuilder("resultado=====>")).append(cadena).toString());
                        trat_cabezera++;
                    }

                    for(String char_ini = cadena.substring(0, 1); char_ini.compareTo(")") != 0; char_ini = cadena.substring(0, 1))
                    {
                        if(char_ini.compareTo("(") == 0)
                            cadena = cadena.substring(1);
                        if(char_ini.compareTo(" ") == 0)
                            for(; char_ini.compareTo(" ") == 0; char_ini = cadena.substring(0, 1))
                                cadena = cadena.substring(1);

                        inicio = cadena.indexOf(";", 0);
                        System.out.println((new StringBuilder("\nPos:")).append(inicio).toString());
                        String contenido_opcion = cadena.substring(0, inicio);
                        tratarOpcion(contenido_opcion, NuevaRegla);
                        cadena = cadena.substring(inicio + 1);
                    }

                    lines++;
                    if(!comentario)
                        VectorRules.add(NuevaRegla);
                    trat_cabezera = 0;
                }
            }

            System.out.println((new StringBuilder("Lines del fichero :")).append(lines).toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void tratarOpcion(String contenido_opcion, Rule NuevaRegla)
    {
        String res = contenido_opcion;
        String contenido = null;
        int j = 0;
        boolean classification = false;
        int long_res = res.length();
        System.out.println((new StringBuilder("\nOpcion Completa:")).append(res).toString());
        int inicio = res.indexOf(":", 0);
        if(inicio != -1)
        {
            String opcion = res.substring(0, inicio);
            System.out.println((new StringBuilder("tipo Opcion:")).append(opcion).toString());
            if(opcion.compareTo("msg") == 0)
            {
                contenido = res.substring(inicio + 2, long_res - 1);
                NuevaRegla.setMsg(contenido);
            }
            if(opcion.compareTo("content") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setContent(contenido);
            }
            if(opcion.compareTo("itype") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setitype(contenido);
            }
            if(opcion.compareTo("dsize") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setdsize(contenido);
            }
            if(opcion.compareTo("id") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setid(contenido);
            }
            if(opcion.compareTo("ttl") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setTtl(contenido);
            }
            if(opcion.compareTo("icode") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.seticode(contenido);
            }
            if(opcion.compareTo("ack") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setack(contenido);
            }
            if(opcion.compareTo("seq") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setseq(contenido);
            }
            if(opcion.compareTo("offset") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setoffset(contenido);
            }
            if(opcion.compareTo("depth") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setdepth(contenido);
            }
            if(opcion.compareTo("flags") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setFlags(contenido);
            }
            if(opcion.compareTo("sid") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setsid(contenido);
            }
            if(opcion.compareTo("rev") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setrev(contenido);
            }
            if(opcion.compareTo("classtype") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setclasstype(contenido);
                for(; j < VectorClasification.size() && !classification; j++)
                {
                    ClassificationRules l_classification = (ClassificationRules)VectorClasification.elementAt(j);
                    if(l_classification.getDescripcion_corta().equals(contenido))
                    {
                        NuevaRegla.setpriority(l_classification.getPrioridad());
                        classification = true;
                    }
                }

            } else
            {
                NuevaRegla.setpriority("5");
            }
            if(opcion.compareTo("priority") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setpriority(contenido);
            }
            if(opcion.compareTo("reference") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setreference(contenido);
            }
            if(opcion.compareTo("within") == 0)
            {
                contenido = res.substring(inicio + 1, long_res);
                NuevaRegla.setwithin(contenido);
            }
        }
        System.out.println((new StringBuilder("contenido Opcion:")).append(contenido).toString());
    }

    public void DatosRules(String IpOrigen, String IpDestino, String portsrc, String portdest, String protocol, byte data[], String itype, 
            String icode, String dsize, String id, String ttl, String flags, String ack, String seq, 
            Packet packet, int numerodepaquete)
    {
        boolean rule_existente = false;
        for(int i = 0; i < VectorRules.size() && !rule_existente; i++)
        {
            Rule objetoRule = (Rule)VectorRules.elementAt(i);
            if(objetoRule.getDireccion().equals("->"))
            {
                if((IpOrigen.equals(objetoRule.getIpSrc()) || objetoRule.getIpSrc().equals("any")) && (IpDestino.equals(objetoRule.getIpDst()) || objetoRule.getIpDst().equals("any")) && (portsrc.equals(objetoRule.getPortSrc()) || objetoRule.getPortSrc().equals("any")) && (portdest.equals(objetoRule.getPortDest()) || objetoRule.getPortDest().equals("any")))
                {
                    rule_existente = TratarPropiedades(objetoRule, protocol, data, itype, icode, dsize, id, ttl, flags, ack, seq, numerodepaquete);
                    if(rule_existente)
                    {
                        System.out.println((new StringBuilder("ALerta Contenido ===>>>")).append(rule_existente).toString());
                        System.out.println((new StringBuilder("ALerta Contenido MSG===>>>")).append(objetoRule.getMsg()).append("paquete:").append(numerodepaquete).toString());
                        log.addAlert(objetoRule, numerodepaquete, packet, IpOrigen, IpDestino, portsrc, portdest);
                        TablaAlertas.DatosTablaAlerts(objetoRule, numerodepaquete, packet, IpOrigen, IpDestino, portsrc, portdest);
                    }
                }
            } else
            if(objetoRule.getDireccion().equals("<>") && ((IpOrigen.equals(objetoRule.getIpSrc()) || objetoRule.getIpSrc().equals("any")) && (IpDestino.equals(objetoRule.getIpDst()) || objetoRule.getIpDst().equals("any")) && (portsrc.equals(objetoRule.getPortSrc()) || objetoRule.getPortSrc().equals("any")) && (portdest.equals(objetoRule.getPortDest()) || objetoRule.getPortDest().equals("any")) || (IpOrigen.equals(objetoRule.getIpDst()) || objetoRule.getIpDst().equals("any")) && (IpDestino.equals(objetoRule.getIpSrc()) || objetoRule.getIpSrc().equals("any")) && (portsrc.equals(objetoRule.getPortDest()) || objetoRule.getPortDest().equals("any")) && (portdest.equals(objetoRule.getPortSrc()) || objetoRule.getPortSrc().equals("any"))))
            {
                rule_existente = TratarPropiedades(objetoRule, protocol, data, itype, icode, dsize, id, ttl, flags, ack, seq, numerodepaquete);
                if(rule_existente)
                {
                    log.addAlert(objetoRule, numerodepaquete, packet, IpOrigen, IpDestino, portsrc, portdest);
                    TablaAlertas.DatosTablaAlerts(objetoRule, numerodepaquete, packet, IpOrigen, IpDestino, portsrc, portdest);
                }
            }
        }

        System.out.println((new StringBuilder("\nVectorRules size :")).append(VectorRules.size()).toString());
    }

    public boolean TratarPropiedades(Rule objetoRule, String protocol, byte data[], String itype, String icode, String dsize, String id, 
            String ttl, String flags, String ack, String seq, int numerodepaquete)
    {
        int j = 0;
        String Contenido_std = "";
        String datospk = null;
        boolean contiene = true;
        boolean alert_contiene = true;
        try
        {
            if(data.length != 0)
            {
                for(; j < objetoRule.getVectorContent().size() && alert_contiene; j++)
                {
                    String Contenido = (String)objetoRule.getVectorContent().elementAt(j);
                    String char_ini = Contenido.substring(0, 1);
                    if(char_ini.equals("!"))
                    {
                        contiene = false;
                        Contenido = Contenido.substring(1);
                    }
                    Contenido = Contenido.substring(1, Contenido.length() - 1);
                    System.out.println((new StringBuilder("\nContenidoooooo:")).append(Contenido).append(contiene).toString());
                    System.out.println(" ");
                    char_ini = Contenido.substring(0, 1);
                    if(char_ini.equals("|"))
                    {
                        Contenido = Contenido.substring(1, Contenido.length() - 1);
                        String str1[] = Contenido.split(" ");
                        for(int i = 0; i < str1.length; i++)
                            Contenido_std = (new StringBuilder(String.valueOf(Contenido_std))).append(str1[i]).toString();

                        BigInteger bi = new BigInteger(data);
                        datospk = bi.toString(16);
                        if(datospk.length() % 2 != 0)
                            datospk = (new StringBuilder("0")).append(datospk).toString();
                        System.out.println((new StringBuilder("\nContenido:")).append(Contenido_std).toString());
                        System.out.println((new StringBuilder("\ndatospk:")).append(datospk).toString());
                        System.out.println();
                        if(contiene)
                        {
                            if(datospk.indexOf(Contenido_std) != -1)
                                alert_contiene = true;
                            else
                                alert_contiene = false;
                        } else
                        if(datospk.indexOf(Contenido_std) != -1)
                            alert_contiene = false;
                        else
                            alert_contiene = true;
                    } else
                    if(contiene)
                    {
                        datospk = new String(data, "ISO-8859-1");
                        if(datospk.indexOf(Contenido) != -1)
                            alert_contiene = true;
                        else
                            alert_contiene = false;
                    } else
                    if(datospk.indexOf(Contenido) != -1)
                        alert_contiene = false;
                    else
                        alert_contiene = true;
                }

                if(objetoRule.getProtocolo() != null && !objetoRule.getProtocolo().equals(protocol))
                    alert_contiene = false;
                else
                if(objetoRule.getitype() != null && !objetoRule.getitype().equals(itype))
                    alert_contiene = false;
                else
                if(objetoRule.geticode() != null && !objetoRule.geticode().equals(icode))
                    alert_contiene = false;
                else
                if(objetoRule.getdsize() != null && !objetoRule.getdsize().equals(dsize))
                    alert_contiene = false;
                else
                if(objetoRule.getid() != null && !objetoRule.getid().equals(id))
                    alert_contiene = false;
                else
                if(objetoRule.getTtl() != null && !objetoRule.getTtl().equals(ttl))
                    alert_contiene = false;
                else
                if(objetoRule.getFlags() != null && flags.indexOf(objetoRule.getFlags()) == -1)
                    alert_contiene = false;
                else
                if(objetoRule.getack() != null && !objetoRule.getack().equals(ack))
                    alert_contiene = false;
                else
                if(objetoRule.getseq() != null && !objetoRule.getseq().equals(seq))
                    alert_contiene = false;
            } else
            {
                alert_contiene = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return alert_contiene;
    }

    public Vector getVectorRules()
    {
        return VectorRules;
    }

    public void OrdenarReglas()
    {
        Collections.sort(VectorRules);
    }

    private String accion;
    private String protocolo;
    private String IpOrigen;
    private String PuertoSrc;
    private String IpDestino;
    private String PuertoDst;
    private String Direccion;
    private String fichero;
    int inicio;
    int lines;
    int res;
    int inicio_opcion;
    public Rule NuevaRegla;
    public Vector VectorRules;
    public Vector VectorClasification;
    public TableAlerts TablaAlertas;
    private XMLlog log;
}
