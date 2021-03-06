package net.ciespal.redxxi.web.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import net.ciespal.redxxi.ejb.negocio.EspejoService;
import net.ciespal.redxxi.ejb.persistence.entities.espejo.LeyDTO;
import net.ciespal.redxxi.ejb.persistence.entities.espejo.NoticiaEspejoDTO;
import net.ciespal.redxxi.web.commons.util.JsfUtil;
import net.ciespal.redxxi.web.datamanager.LeyCodigoDataManager;

import com.corvustec.commons.util.CorvustecException;

@ViewScoped
@ManagedBean(name = "leyCodigoController")
public class LeyCodigoController extends SelectItemController {
	@EJB
	private EspejoService espejoService;
	
	@ManagedProperty(value="#{leyCodigoDataManager}")
	private LeyCodigoDataManager leyCodigoDataManager;

	public LeyCodigoController() {
	
	}
	
	@PostConstruct
	private void init()
	{
		
	}

	public LeyCodigoDataManager getLeyCodigoDataManager() {
		return leyCodigoDataManager;
	}

	public void setLeyCodigoDataManager(LeyCodigoDataManager leyCodigoDataManager) {
		this.leyCodigoDataManager = leyCodigoDataManager;
	}
	
	public void obtenerProvinciaChange() {
		try {
			getCatalogoProvincia();
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}		
	}
	
	public void obtenerCiudadChange() {
		try {
			getCatalogoCiudad();
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}		
	}
	
	public void save()
	{
		try {
			leyCodigoDataManager.getLeyDTO().setLeyPais(Integer.valueOf(getPais().toString()));
			leyCodigoDataManager.getLeyDTO().setLeyProvincia(Integer.valueOf(getProvincia().toString()));
			leyCodigoDataManager.getLeyDTO().setLeyCiudad(Integer.valueOf(getCiudad().toString()));
			leyCodigoDataManager.getLeyDTO().setLeyTipo(Integer.valueOf(leyCodigoDataManager.getTipoDocumento().toString()));
			espejoService.createOrUpdateley(leyCodigoDataManager.getLeyDTO());
			read();
			cancel();
			JsfUtil.addInfoMessage("Guardado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
		
	}

	public void cancel()
	{
		leyCodigoDataManager.setLeyDTO(new LeyDTO());
		leyCodigoDataManager.setTipoDocumento(null);
	}
	
	public void edit(LeyDTO ley)
	{
		leyCodigoDataManager.setLeyDTO(ley);
		leyCodigoDataManager.setTipoDocumento(ley.getLeyTipo());
	}

	public void delete(LeyDTO ley)
	{
		try {
			espejoService.deleteLeyCodigo(ley);
			read();
			JsfUtil.addInfoMessage("Eliminado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}

	}

	public void ciudadChange()
	{
		read();
	}
	
	private void read()
	{
		try {
			leyCodigoDataManager.setLeyList(espejoService.readLey(getCiudad()));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	
	public void leySelect(LeyDTO ley)
	{
		leyCodigoDataManager.setLeyDTO(ley);
		readNoticia();
	}
	
	public void createNoticia()
	{
		try {
			leyCodigoDataManager.getNoticia().setEspEntidad(leyCodigoDataManager.getLeyDTO().getEspEntidad());
			espejoService.createOrUpdateNoticia(leyCodigoDataManager.getNoticia());
			readNoticia();
			cancelNoticia();
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void cancelNoticia()
	{
		leyCodigoDataManager.setNoticia(new NoticiaEspejoDTO());
	}
	
	private void readNoticia()
	{
		try {
			leyCodigoDataManager.setNoticiaList(espejoService.readNoticia(leyCodigoDataManager.getLeyDTO()));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void editNoticia(NoticiaEspejoDTO noticia)
	{
		leyCodigoDataManager.setNoticia(noticia);
	}
	
	public void deleteNoticia(NoticiaEspejoDTO noticia)
	{
		try {
			espejoService.deleteNoticia(noticia);
			readNoticia();
			JsfUtil.addInfoMessage("Eliminado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	public void handleFileUploadArchivo(FileUploadEvent event)
	{
		JsfUtil.addInfoMessage("Archivo "+ event.getFile().getFileName() + " esta en memoria.");
		leyCodigoDataManager.getLeyDTO().setLeyArchivo(event.getFile().getContents());
		leyCodigoDataManager.getLeyDTO().setLeyArchivoNombre(event.getFile().getFileName());
		leyCodigoDataManager.getLeyDTO().setLeyArchivoPath(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
	}

}
