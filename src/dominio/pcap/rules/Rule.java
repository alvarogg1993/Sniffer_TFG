
package dominio.pcap.rules;

import java.util.Vector;

/** 
 * Clase Rule. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Rule
    implements Comparable
{

    public Rule()
    {
        VectorContent = new Vector();
    }

    public int compareTo(Object o)
    {
        Rule obj = (Rule)o;
        return priority.compareTo(obj.getPriority());
    }

    public void setAccion(String Accion)
    {
        this.Accion = Accion;
    }

    public void setIpSrc(String IpSrc)
    {
        this.IpSrc = IpSrc;
    }

    public void setIpDst(String IpDst)
    {
        this.IpDst = IpDst;
    }

    public void setProtocolo(String Protocolo)
    {
        this.Protocolo = Protocolo;
    }

    public void setPortSrc(String PortSrc)
    {
        this.PortSrc = PortSrc;
    }

    public void setPortDest(String PortDest)
    {
        this.PortDest = PortDest;
    }

    public void setDireccion(String Direccion)
    {
        this.Direccion = Direccion;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public void setFlags(String flags)
    {
        this.flags = flags;
    }

    public void setTtl(String ttl)
    {
        this.ttl = ttl;
    }

    public void setid(String id)
    {
        this.id = id;
    }

    public void setContent(String content)
    {
        VectorContent.add(content);
    }

    public void setitype(String itype)
    {
        this.itype = itype;
    }

    public void setack(String ack)
    {
        this.ack = ack;
    }

    public void setseq(String seq)
    {
        this.seq = seq;
    }

    public void setoffset(String offset)
    {
        this.offset = offset;
    }

    public void setdsize(String dsize)
    {
        this.dsize = dsize;
    }

    public void seticode(String icode)
    {
        this.icode = icode;
    }

    public void setsid(String sid)
    {
        this.sid = sid;
    }

    public void setdepth(String depth)
    {
        this.depth = depth;
    }

    public void setrev(String rev)
    {
        this.rev = rev;
    }

    public void setclasstype(String classtype)
    {
        this.classtype = classtype;
    }

    public void setSession(String session)
    {
        this.session = session;
    }

    public void setpriority(String priority)
    {
        this.priority = priority;
    }

    public void setreference(String reference)
    {
        this.reference = reference;
    }

    public void setwithin(String within)
    {
        this.within = within;
    }

    public String getAccion()
    {
        return Accion;
    }

    public String getIpSrc()
    {
        return IpSrc;
    }

    public String getIpDst()
    {
        return IpDst;
    }

    public String getProtocolo()
    {
        return Protocolo;
    }

    public String getPortSrc()
    {
        return PortSrc;
    }

    public String getPortDest()
    {
        return PortDest;
    }

    public String getDireccion()
    {
        return Direccion;
    }

    public String getMsg()
    {
        return msg;
    }

    public String getFlags()
    {
        return flags;
    }

    public String getTtl()
    {
        return ttl;
    }

    public String getid()
    {
        return id;
    }

    public Vector getVectorContent()
    {
        return VectorContent;
    }

    public String getitype()
    {
        return itype;
    }

    public String getdsize()
    {
        return dsize;
    }

    public String geticode()
    {
        return icode;
    }

    public String getack()
    {
        return ack;
    }

    public String getseq()
    {
        return seq;
    }

    public String getoffset()
    {
        return offset;
    }

    public String getdepth()
    {
        return depth;
    }

    public String getsid()
    {
        return sid;
    }

    public String getrev()
    {
        return rev;
    }

    public String getclasstype()
    {
        return classtype;
    }

    public String getSession()
    {
        return session;
    }

    public String getPriority()
    {
        return priority;
    }

    public String getreference()
    {
        return reference;
    }

    public String getwithin()
    {
        return within;
    }

    private String rulefile;
    private String Accion;
    private String IpSrc;
    private String IpDst;
    private String Protocolo;
    private String PortSrc;
    private String PortDest;
    private String Direccion;
    private String msg;
    private String flags;
    private String ttl;
    private String id;
    private String itype;
    private String dsize;
    private String icode;
    private String session;
    private String priority;
    private String depth;
    private String sid;
    private String rev;
    private String classtype;
    private String reference;
    private String within;
    private String seq;
    private String ack;
    private String offset;
    public Vector VectorContent;
}
