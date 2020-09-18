package renta.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import renta.model.entities.Gerente;





/**
 * Session Bean implementation class ManagerGerente
 */
@Stateless
@LocalBean
public class ManagerGerente {
	
	@PersistenceContext
	private EntityManager em;

	

    /**
     * Default constructor. 
     */
    public ManagerGerente() {
        // TODO Auto-generated constructor stub
    }
    
    public void agregarGerente(String nombre,String apellido,String direccion,String correo, String contrasena) {
    	Gerente u=new Gerente();
    	u.setContrasenia(contrasena);
    	u.setNombreG(nombre);
    	u.setApellidoG(apellido);
    	u.setDireccionG(direccion);
    	
    	u.setCorreoG(correo);
    	em.persist(u);
    }
    
    public List<Gerente> findAllGerentes(){
    	Query q;
    	List<Gerente> listadoG;
    	String sentenciaSQL;
    	sentenciaSQL= "SELECT g FROM Gerente g ORDER BY g.id_gerente";
    	q = em.createQuery(sentenciaSQL);
    	listadoG=q.getResultList();
    	return listadoG;
    } 

    public Gerente findGerente(int id) {
    	Gerente u=em.find(Gerente.class,id);
    	return u;
    }
    
    public void editarGerente(int id,String nombre, String apellido,String direccion,String correo, String contrasena)throws Exception {
    	Gerente u= findGerente(id);
    	if(u==null) {
    		throw new Exception("No existe el usuario especificado");
    	}else {
    		u.setNombreG(nombre);
    		u.setApellidoG(apellido);
    		u.setCorreoG(correo);
    		u.setDireccionG(direccion);
    		u.setContrasenia(contrasena);
    		
    		em.merge(u);
    	}
    }
	    public void eliminarGerente(int id) throws Exception{
	    	Gerente u=findGerente(id);
	    	if(u== null)
	    		throw new Exception("No existe el usuario especificado");
	    	em.remove(u);
	    }  


}
