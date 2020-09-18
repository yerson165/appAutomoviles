package renta.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import renta.model.entities.Autmovile;

/**
 * Session Bean implementation class ManagerAutomovil
 */
@Stateless
@LocalBean
public class ManagerAutomovil {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerAutomovil() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void agregarAutomovil(String color, String marca, String modelo, String estadoAutomovil ) {
    	Autmovile a= new Autmovile();
    
    	a.setColor(color);
    	a.setEstadoAutomovil(estadoAutomovil);
    	a.setMarca(marca);
    	a.setModelo(modelo);
    	em.persist(a);
    }
    
    public List<Autmovile> findAllAutomoviles(){
    	Query q;
    	List<Autmovile> listadoA;
    	String sentenciaSQL;
    	sentenciaSQL= "SELECT a FROM Automovile a ORDER BY a.placa";
    	q = em.createQuery(sentenciaSQL);
    	listadoA=q.getResultList();
    	return listadoA;
    } 

    public Autmovile findAutomovil(String placa) {
    	Autmovile a=em.find(Autmovile.class,placa);
    	return a;
    }
    
    public void editarAutomovil(String color, String marca, String modelo, String placa,String estadoAutomovil )throws Exception {
    	Autmovile a= findAutomovil(placa);
    	if(a==null) 
    		throw new Exception("No existe el automovil especificado");
    	a.setColor(color);

    	a.setEstadoAutomovil(estadoAutomovil);
    	a.setModelo(modelo);
    	a.setMarca(marca);
    	em.merge(a);
    	
    }
	    public void eliminarAutomovil(String placa) throws Exception{
	    	Autmovile a=findAutomovil(placa);
	    	if(a== null)
	    		throw new Exception("No existe el automovil especificado");
	    	em.remove(a);
	    }


}
