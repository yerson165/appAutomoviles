package renta.model.login;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {

    /**
     * Default constructor. 
     */
    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }
    
    public String verificarUsuario(String codigoUsuario, String clave) {
    	if(codigoUsuario.equals("Lucero") && clave.equals("555"))
    		return "usuario";
    	else if (codigoUsuario.equals("Santiago")&& clave.equals("123")) 
    		return "gerente";
    	else
    		return "usuario no existe";
    }

}
