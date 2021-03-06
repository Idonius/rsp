package ec.edu.uce.erpmunicipal.sistema.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ec.edu.uce.erpmunicipal.sistema.bsl.UserService;
import ec.edu.uce.erpmunicipal.sistema.orm.SisUsuario;
import ec.edu.uce.erpmunicipal.util.orm.SessionObject;
import ec.edu.uce.erpmunicipal.util.web.ThemeSwitcherBean;

@ManagedBean(name = "indexPage")
@SessionScoped
public class IndexPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "userService/local")
	private UserService userService;

	private SisUsuario sysUser;
	private String user;
	private String password;

	public IndexPage() {
		super();
		this.user = "";
		this.password = "";
		this.sysUser = new SisUsuario();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SisUsuario getSysUser() {
		return sysUser;
	}

	public void setSysUser(SisUsuario sysUser) {
		this.sysUser = sysUser;
	}

	public String authenticate() {
		String retorno;
		// RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (userService.authenticate(user, password)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome 2",
					user);
			setSysUser(userService.getUserInformation(user));

			SessionObject sessionObject = new SessionObject();
			sessionObject.setUser(getSysUser());
			sessionObject.setUserRol(userService.readUserRol(user));

			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("sessionObject", sessionObject);

			ThemeSwitcherBean a=new ThemeSwitcherBean();
			a.setTheme("start");
			
			retorno = "pages/system/selectOptions.jsf";
			
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Invalid credentials");
			retorno = "";
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		// context.addCallbackParam("menu", loggedIn);
		return retorno;
	}

}
