package ec.edu.uce.indicadores.ejb.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the ind_usuario database table.
 * 
 */
@Entity
@Table(name="ind_usuario")
@NamedQuery(name="UsuarioDTO.findAll", query="SELECT u FROM UsuarioDTO u")
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IND_USUARIO_USUCODIGO_GENERATOR", sequenceName="IND_USUARIO_USU_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IND_USUARIO_USUCODIGO_GENERATOR")
	@Column(name="usu_codigo")
	private Integer usuCodigo;

	@Column(name="usu_clave")
	private String usuClave;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_mail")
	private String usuMail;

	@Column(name="usu_nombre")
	private String usuNombre;

	@Column(name="usu_apellido")
	private String usuApellido;
	
	//bi-directional many-to-one association to IesDTO
	@ManyToOne
	@JoinColumn(name="usu_ies")
	private IesDTO indy;
	
	//bi-directional many-to-one association to UsuarioPerfilDTO
	@OneToMany(mappedBy="indUsuario",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<UsuarioPerfilDTO> indUsuarioPerfils;
	
	public UsuarioDTO() {
	}

	public Integer getUsuCodigo() {
		return this.usuCodigo;
	}

	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuMail() {
		return this.usuMail;
	}

	public void setUsuMail(String usuMail) {
		this.usuMail = usuMail;
	}

	public String getUsuNombre() {
		return usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public IesDTO getIndy() {
		return this.indy;
	}

	public void setIndy(IesDTO indy) {
		this.indy = indy;
	}

	public String getUsuApellido() {
		return usuApellido;
	}

	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public List<UsuarioPerfilDTO> getIndUsuarioPerfils() {
		return indUsuarioPerfils;
	}

	public void setIndUsuarioPerfils(List<UsuarioPerfilDTO> indUsuarioPerfils) {
		this.indUsuarioPerfils = indUsuarioPerfils;
	}

	public UsuarioPerfilDTO addIndUsuarioPerfil(UsuarioPerfilDTO indUsuarioPerfil) {
		if(indUsuarioPerfil!=null)
		{
			if(getIndUsuarioPerfils()==null)
				setIndUsuarioPerfils(new ArrayList<UsuarioPerfilDTO>());
			getIndUsuarioPerfils().add(indUsuarioPerfil);
			indUsuarioPerfil.setIndUsuario(this);
		}
		return indUsuarioPerfil;
	}
}