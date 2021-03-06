package net.ciespal.redxxi.web.datamanager.home;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.ciespal.redxxi.ejb.persistence.entities.util.dto.CredencialesDTO;

@SessionScoped
@ManagedBean(name = "loginDataManager")
public class LoginDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CredencialesDTO credencialesDTO;
	
	public LoginDataManager() {
		credencialesDTO=new CredencialesDTO();
	}

	public CredencialesDTO getCredencialesDTO() {
		return credencialesDTO;
	}

	public void setCredencialesDTO(CredencialesDTO credencialesDTO) {
		this.credencialesDTO = credencialesDTO;
	}
}
