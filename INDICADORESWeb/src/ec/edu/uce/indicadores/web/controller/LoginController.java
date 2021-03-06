package ec.edu.uce.indicadores.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.uce.indicadores.commons.dto.util.CredencialesDTO;
import ec.edu.uce.indicadores.commons.util.IndicadoresException;
import ec.edu.uce.indicadores.ejb.negocio.AdministracionService;
import ec.edu.uce.indicadores.ejb.negocio.LoginService;
import ec.edu.uce.indicadores.ejb.persistence.entities.OpcionDTO;
import ec.edu.uce.indicadores.ejb.persistence.entities.UsuarioDTO;
import ec.edu.uce.indicadores.web.util.JsfUtil;


@SessionScoped
@ManagedBean(name = "loginController")
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private LoginService loginService;

	@EJB
	private AdministracionService administracionService;
	
	private CredencialesDTO credencialesDTO;


	
	public LoginController() {
	}

	@PostConstruct
	private void init()
	{
		credencialesDTO=new CredencialesDTO();
	}
	
	public CredencialesDTO getCredencialesDTO() {
		return credencialesDTO;
	}

	public void setCredencialesDTO(CredencialesDTO credencialesDTO) {
		this.credencialesDTO = credencialesDTO;
	}

	public void login()
	{
		try {
			HttpServletRequest request = (HttpServletRequest) JsfUtil.getExternalContext().getRequest();

			String path = request.getContextPath();
			UsuarioDTO user = loginService.autenticarUsuario(credencialesDTO);
			if (user != null) {
				JsfUtil.addInfoMessage("bien");
				JsfUtil.putObject("UsuarioDTO",user);
				JsfUtil.redirect(path+"/pages/home.xhtml");
				
				List<OpcionDTO> listOption;
				listOption=new ArrayList<OpcionDTO>();
				listOption= administracionService.readOpcion(user.getIndUsuarioPerfils().get(0).getIndPerfil());

				Map<Integer, String> mapMenuUsuario = new HashMap<Integer, String>();
				
		        for(OpcionDTO opt:listOption)
			        mapMenuUsuario.put(opt.getOpcCodigo(), opt.getOpcUrl());
		        
		        JsfUtil.getExternalContext().getSessionMap().put("menuUsuario", mapMenuUsuario);
				
			} else {
				JsfUtil.addErrorMessage("Datos Tncorrectos");
			}
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Datos Incorrectos");
		} catch (IndicadoresException e) {
			JsfUtil.addErrorMessage("Datos Incorrectos");
		}
	}
	
	
	public void logout()
	{
	      try {
			HttpSession session = JsfUtil.getSession();
		      session.invalidate();
			JsfUtil.redirect("/INDICADORESWeb/pages/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
