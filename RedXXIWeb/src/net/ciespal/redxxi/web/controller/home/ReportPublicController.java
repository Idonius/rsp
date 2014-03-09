package net.ciespal.redxxi.web.controller.home;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.corvustec.commons.util.CorvustecException;

import net.ciespal.redxxi.ejb.negocio.AteneaService;
import net.ciespal.redxxi.ejb.persistence.entities.AteneaDTO;
import net.ciespal.redxxi.ejb.persistence.entities.PaisDTO;
import net.ciespal.redxxi.web.commons.util.JsfUtil;
import net.ciespal.redxxi.web.datamanager.home.ReportPublicDataManager;

@ViewScoped
@ManagedBean(name = "reportPublicController")
public class ReportPublicController {

	
	@EJB
	private AteneaService ateneaService;

	
	@ManagedProperty(value="#{reportPublicDataManager}")
	private ReportPublicDataManager reportPublicDataManager;
	
	public ReportPublicController() {
		
	}

	@PostConstruct
	private void init()
	{
		readAtenea();
		readPais();
	}
	
	public ReportPublicDataManager getReportPublicDataManager() {
		return reportPublicDataManager;
	}

	public void setReportPublicDataManager(
			ReportPublicDataManager reportPublicDataManager) {
		this.reportPublicDataManager = reportPublicDataManager;
	}
	

	private void readAtenea()
	{
		try {
			reportPublicDataManager.setAteneaList(ateneaService.readAtenea(null));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	private void readPais()
	{
		try {
			reportPublicDataManager.setPaisList(ateneaService.readPais(null));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	public void selectAtenea(AteneaDTO atenea)
	{
		try {
			if(atenea.getPais()!=0)
			{
				reportPublicDataManager.setPaisList(ateneaService.readPais(null));
				reportPublicDataManager.setAteneaList(ateneaService.readAtenea(atenea.getPais()));
			}
			else
				reportPublicDataManager.setPaisList(ateneaService.readPais(atenea.getTipo()));
			reportPublicDataManager.setVisor(ateneaService.visor(atenea));
			JsfUtil.redirect("/"+JsfUtil.getExternalContext().getContextName()+"/public/home/ateneaVisor.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e.toString());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void selectPais(PaisDTO pais)
	{
		try {
			reportPublicDataManager.setAteneaList(ateneaService.readAtenea(pais.getCodigo()));
			JsfUtil.redirect("/"+JsfUtil.getExternalContext().getContextName()+"/public/home/ateneaVisor.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e.toString());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

}