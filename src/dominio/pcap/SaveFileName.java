// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SaveFileName.java

package dominio.pcap;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * Clase SaveFileName. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class SaveFileName
{

    public SaveFileName(String path, String file)
    {
        setPath(path);
        setFile(file);
        separator();
        getPartFile();
        setTime();
        setSiguiente(0);
        setPila(0);
        setContador(0);
        setMaximo(0);
        setContadorTotal(0);
        setUltimo(1);
        setPrimero(1);
    }

    public SaveFileName(String fullPath)
    {
        getPartFullPath(fullPath);
        separator();
        getPartFile();
        setTime();
        setSiguiente(0);
        setPila(0);
        setContador(0);
        setMaximo(0);
        setContadorTotal(0);
        setUltimo(1);
        setPrimero(1);
    }

    public SaveFileName()
    {
        separator();
        setTime();
        setSiguiente(0);
        setPila(0);
        setContador(0);
        setMaximo(0);
        setContadorTotal(0);
        setUltimo(1);
        setPrimero(1);
    }

    public void setSaveFileName(String fullPath)
    {
        getPartFullPath(fullPath);
        getPartFile();
        setTime();
    }

    public void setSaveFileName(String path, String file)
    {
        setPath(path);
        setFile(file);
        getPartFile();
        setTime();
    }

    public int SFNOffline(int iPila, int iStart, int iEnd)
    {
        int errorTipo = 0;
        next = iStart;
        setPila(iPila);
        if(getPila() == 0)
        {
            if(iStart < iEnd)
                setMaximo(iEnd - iStart);
            else
                errorTipo = 1;
        } else
        if(iStart < iEnd)
            setMaximo(iEnd - iStart);
        else
            setMaximo(iPila);
        return errorTipo;
    }

    public String getNameTime()
    {
        String aux = (new StringBuilder(String.valueOf(getNameFile()))).append("_").append(getDateTime()).append("_").append(getNext()).append(getNameExtension()).toString();
        return aux;
    }

    public String getNT()
    {
        String aux = (new StringBuilder(String.valueOf(getNameFile()))).append("_").append(getDateTime()).append(getNameExtension()).toString();
        return aux;
    }

    public String getNTwithout()
    {
        String aux = (new StringBuilder(String.valueOf(getNameFile()))).append("_").append(getDateTime()).toString();
        return aux;
    }

    public String getFullNameTime()
    {
        String aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getNameTime()).toString();
        return aux;
    }

    public String getFNT()
    {
        String aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getNT()).toString();
        return aux;
    }

    public String getFullPath()
    {
        String aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getFile()).toString();
        return aux;
    }

    private void separator()
    {
        String aux;
        if(System.getProperty("os.name").compareTo("Linux") == 0)
            aux = "/";
        else
            aux = "\\";
        separator = aux;
    }

    public void setTime()
    {
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmss");
        String horaActual = new String(sdf.format(hoy));
        dateTime = horaActual;
    }

    public void getPartFile()
    {
        String auxFile = file;
        int dotPlace = auxFile.lastIndexOf(".");
        String ext;
        String name;
        if(dotPlace >= 0)
        {
            name = auxFile.substring(0, dotPlace);
            ext = auxFile.substring(dotPlace);
        } else
        {
            name = auxFile;
            ext = ".pcap";
        }
        nameFile = name;
        nameExtension = ext;
    }

    public void getPartFullPath(String fullPath)
    {
        String auxFile = fullPath;
        separator();
        auxFile = auxFile.replace("/", getSeparator());
        auxFile = auxFile.replace("\\", getSeparator());
        int dotPlace = auxFile.lastIndexOf(getSeparator());
        String fil;
        String path;
        if(dotPlace >= 0)
        {
            path = auxFile.substring(0, dotPlace);
            fil = auxFile.substring(dotPlace + getSeparator().length());
        } else
        {
            path = "./files/capturas";
            fil = auxFile;
        }
        setPath(path);
        setFile(fil);
    }

    public void setPath(String str)
    {
        path = str;
    }

    public String getPath()
    {
        return path;
    }

    public void setFile(String str)
    {
        file = str;
    }

    public String getFile()
    {
        return (new StringBuilder(String.valueOf(getNameFile()))).append(getNameExtension()).toString();
    }

    public String getNameFile()
    {
        return nameFile;
    }

    public String getNameExtension()
    {
        return nameExtension;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public String getSeparator()
    {
        return separator;
    }

    public String getBarra()
    {
        if(separator.length() < 1)
            return separator.substring(0);
        else
            return separator;
    }

    public void setContador(int nt)
    {
        contador = nt;
    }

    public int getContador()
    {
        return contador;
    }

    public void setContadorTotal(int nt)
    {
        contador_total = nt;
    }

    public int getContadorTotal()
    {
        return contador_total;
    }

    public void setPila(int nt)
    {
        pila = nt;
    }

    public int getPila()
    {
        return pila;
    }

    public void setMaximo(int nt)
    {
        maximo = nt;
    }

    public int getMaximo()
    {
        return maximo;
    }

    private void setSiguiente(int nt)
    {
        next = nt;
        contador++;
    }

    public int getSiguiente()
    {
        return next;
    }

    private void setUltimo(int nt)
    {
        last = nt;
    }

    public int getPrimero()
    {
        return first;
    }

    private void setPrimero(int nt)
    {
        first = nt;
    }

    public int getUltimo()
    {
        return last;
    }

    public void setNext()
    {
        int aux = getSiguiente() + 1;
        setContadorTotal(getContadorTotal() + 1);
        if(maximo != 0 && maximo < getContadorTotal())
        {
            if(getPrimero() > getPila())
                setPrimero(1);
            aux = -1;
        } else
        {
            setUltimo(aux);
            if(getPila() != 0)
                if(aux > getPila())
                {
                    paso = true;
                    aux = 1;
                    setUltimo(aux);
                    if(getPila() == 1)
                        setPrimero(1);
                    else
                        setPrimero(aux + 1);
                } else
                if(paso)
                {
                    setUltimo(aux);
                    if(aux > getPila())
                        setPrimero(1);
                    else
                        setPrimero(aux + 1);
                }
        }
        setSiguiente(aux);
    }

    public int getNext()
    {
        return next;
    }

    public String getFullPathOffline()
    {
        String aux;
        if(getPila() == 0)
        {
            if(getNext() != 0)
                aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getNameFile()).append("_").append(getNext()).append(getNameExtension()).toString();
            else
                aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getFile()).toString();
        } else
        if(getNext() != 0)
            aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getNameFile()).append("_").append(getNext()).append(getNameExtension()).toString();
        else
            aux = (new StringBuilder(String.valueOf(getPath()))).append(getSeparator()).append(getFile()).toString();
        return aux;
    }

    private String path;
    private String file;
    private String separator;
    private String nameFile;
    private String nameExtension;
    private String dateTime;
    private int next;
    private int pila;
    private int contador;
    private int maximo;
    private int contador_total;
    private int last;
    private int first;
    private boolean paso;
}
