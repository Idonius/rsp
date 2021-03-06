package net.ciespal.redxxi.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.ciespal.redxxi.ejb.persistence.entities.NoticiaDTO;

@SessionScoped
@ManagedBean(name="noticiaPublicDataManager")
public class NoticiaPublicDataManager implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private NoticiaDTO noticia;
	private List<NoticiaDTO> noticiaList;
	private List<NoticiaDTO> noticiaPublicList;
	
	@PostConstruct
	private void init()
	{
		noticia=new NoticiaDTO();
		noticiaList=new ArrayList<NoticiaDTO>();
		noticiaPublicList=new ArrayList<NoticiaDTO>();
	}

	public NoticiaDTO getNoticia() {
		return noticia;
	}

	public void setNoticia(NoticiaDTO noticia) {
		this.noticia = noticia;
	}

	public List<NoticiaDTO> getNoticiaList() {
		return noticiaList;
	}

	public void setNoticiaList(List<NoticiaDTO> noticiaList) {
		this.noticiaList = noticiaList;
	}

	public List<NoticiaDTO> getNoticiaPublicList() {
		return noticiaPublicList;
	}

	public void setNoticiaPublicList(List<NoticiaDTO> noticiaPublicList) {
		this.noticiaPublicList = noticiaPublicList;
	}
}
