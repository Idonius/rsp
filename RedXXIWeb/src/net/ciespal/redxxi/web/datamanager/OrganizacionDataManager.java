package net.ciespal.redxxi.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ciespal.redxxi.ejb.persistence.entities.OrganizacionDTO;


@ViewScoped
@ManagedBean(name="organizacionDataManager")
public class OrganizacionDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrganizacionDTO organizacion;
	private List<OrganizacionDTO> organizacionList;
	
	public OrganizacionDataManager() {
	}

	@PostConstruct
	private void init()
	{
		organizacion=new OrganizacionDTO();
		organizacionList=new ArrayList<OrganizacionDTO>();
	}
	
	public OrganizacionDTO getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(OrganizacionDTO organizacion) {
		this.organizacion = organizacion;
	}

	public List<OrganizacionDTO> getOrganizacionList() {
		return organizacionList;
	}

	public void setOrganizacionList(List<OrganizacionDTO> organizacionList) {
		this.organizacionList = organizacionList;
	}
	
	
}
