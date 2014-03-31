package net.ciespal.redxxi.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ciespal.redxxi.ejb.persistence.entities.argos.ObservatorioDTO;
import net.ciespal.redxxi.ejb.persistence.entities.argos.RedDTO;

@ViewScoped
@ManagedBean(name="observatorioDataManager")
public class ObservatorioDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObservatorioDTO observatorio;
	private List<ObservatorioDTO> observatorioList;
	
	private List<RedDTO> redList;
	
	private Integer redValue;
	
	public ObservatorioDataManager() {
		observatorio=new ObservatorioDTO();
		observatorioList=new ArrayList<ObservatorioDTO>();
		redList=new ArrayList<RedDTO>();
	}

	public ObservatorioDTO getObservatorio() {
		return observatorio;
	}

	public void setObservatorio(ObservatorioDTO observatorio) {
		this.observatorio = observatorio;
	}

	public List<ObservatorioDTO> getObservatorioList() {
		return observatorioList;
	}

	public void setObservatorioList(List<ObservatorioDTO> observatorioList) {
		this.observatorioList = observatorioList;
	}

	public List<RedDTO> getRedList() {
		return redList;
	}

	public void setRedList(List<RedDTO> redList) {
		this.redList = redList;
	}

	public Integer getRedValue() {
		return redValue;
	}

	public void setRedValue(Integer redValue) {
		this.redValue = redValue;
	}
	
	
}