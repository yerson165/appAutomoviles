package renta.model.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import renta.model.login.ManagerLogin;



@Named
@SessionScoped
public class BeanRenta implements Serializable {
	private static final long serialVersionUID = 1L;

	public BeanRenta() {
		// TODO Auto-generated constructor stub
	}
	
	@EJB
	private ManagerLogin mlogin;
	private String codigoUsuario;
	private String clave;
	private boolean verificado;
	
	
	public String actionValidarUsuario() {
		String ruta=mlogin.verificarUsuario(codigoUsuario, clave);
		System.out.println("Validar Usuario: " +codigoUsuario +" ruta: "+ruta+" fecha de ejecucion: "+new Date());
		
		return ruta;
	}
	
	public void actionListenerVerificarLogin() {
		ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
		String requestPath=ec.getRequestPathInfo();
		
		try {
		if(verificado==false) {
			//el usuario no ingreso por login
				ec.redirect(ec.getRequestContextPath()+"/faces/index.xhtml");	
			
		}else {
			//usuario hizo login pero se verifica el control de acceso
			System.out.println("Ruta request " +requestPath);
			if(requestPath.contains("usuario.xhtml")&&!codigoUsuario.equals("Lucero"))
				ec.redirect(ec.getRequestContextPath()+"/faces/index.xhtml");
			
			else if(requestPath.contains("gerente.xhtml")&&!codigoUsuario.equals("Santiago"))
				ec.redirect(ec.getRequestContextPath()+"/faces/index.xhtml");
			}	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	
		return "index?faces-redirect=true";
		
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	
	
	

}
