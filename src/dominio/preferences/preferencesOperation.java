
package dominio.preferences;

/** 
 * Clase preferencesOperation. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesOperation
{

    public preferencesOperation()
    {
    }

    public boolean validate(String state)
    {
        boolean auxB;
        if(state.toUpperCase().equals("YES"))
            auxB = true;
        else
            auxB = false;
        return auxB;
    }

    public String validate(boolean state)
    {
        String auxS = String.valueOf(state);
        String aux;
        if(auxS.toUpperCase().equals("TRUE"))
            aux = "Yes";
        else
            aux = "No";
        return aux;
    }
}
