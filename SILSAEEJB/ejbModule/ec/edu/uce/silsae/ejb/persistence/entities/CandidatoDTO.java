package ec.edu.uce.silsae.ejb.persistence.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the bem_candidato database table.
 * 
 */
@Entity
@Table(name="bem_candidato")
public class CandidatoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BEM_CANDIDATO_CANCODIGO_GENERATOR", sequenceName="BEM_CANDIDATO_CAN_CODIGO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BEM_CANDIDATO_CANCODIGO_GENERATOR")
	@Column(name="can_codigo")
	private Integer canCodigo;

	@Column(name="can_apellido_materno")
	private String canApellidoMaterno;

	@Column(name="can_apellido_paterno")
	private String canApellidoPaterno;

	@Column(name="can_foto")
	private byte[] canFoto;

	@Column(name="can_identificacion")
	private String canIdentificacion;

	@Column(name="can_primer_nombre")
	private String canPrimerNombre;

	@Column(name="can_segundo_nombre")
	private String canSegundoNombre;

	@Column(name="can_tipo_identificacion")
	private Integer canTipoIdentificacion;
	
	@Column(name="can_fecha_nacimiento")
	private Timestamp canFechaNacimiento;

	@Column(name="can_lugar_nacimiento")
	private String canLugarNacimiento;
	
	@Column(name="can_estado_civil")
	private Integer canEstadoCivil;
	
	@Column(name="can_max_estudio")
	private Integer canMaxEstudio;
	

	//bi-directional many-to-one association to UsuarioDTO
    @ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="can_usuario")
	private UsuarioDTO bemUsuario;

	//bi-directional many-to-one association to PostulacionDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<PostulacionDTO> bemPostulacions;

	//bi-directional many-to-one association to EstudioDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<EstudioDTO> bemEstudios;

	//bi-directional many-to-one association to ExperienciaDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<ExperienciaDTO> bemExperiencias;

	//bi-directional many-to-one association to IdiomaDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<IdiomaDTO> bemIdiomas;

	//bi-directional many-to-one association to ReferenciaDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<ReferenciaDTO> bemReferencias;

	//bi-directional many-to-one association to SoftwareDTO
	@OneToMany(mappedBy="bemCandidato")
	private List<SoftwareDTO> bemSoftwares;

    public CandidatoDTO() {
    }
    
    

	public CandidatoDTO(Integer canCodigo) {
		this.canCodigo = canCodigo;
	}



	public Integer getCanCodigo() {
		return this.canCodigo;
	}

	public void setCanCodigo(Integer canCodigo) {
		this.canCodigo = canCodigo;
	}

	public String getCanApellidoMaterno() {
		return this.canApellidoMaterno;
	}

	public void setCanApellidoMaterno(String canApellidoMaterno) {
		this.canApellidoMaterno = canApellidoMaterno;
	}

	public String getCanApellidoPaterno() {
		return this.canApellidoPaterno;
	}

	public void setCanApellidoPaterno(String canApellidoPaterno) {
		this.canApellidoPaterno = canApellidoPaterno;
	}

	public byte[] getCanFoto() {
		return this.canFoto;
	}

	public void setCanFoto(byte[] canFoto) {
		this.canFoto = canFoto;
	}

	public String getCanIdentificacion() {
		return this.canIdentificacion;
	}

	public void setCanIdentificacion(String canIdentificacion) {
		this.canIdentificacion = canIdentificacion;
	}

	public String getCanPrimerNombre() {
		return this.canPrimerNombre;
	}

	public void setCanPrimerNombre(String canPrimerNombre) {
		this.canPrimerNombre = canPrimerNombre;
	}

	public String getCanSegundoNombre() {
		return this.canSegundoNombre;
	}

	public void setCanSegundoNombre(String canSegundoNombre) {
		this.canSegundoNombre = canSegundoNombre;
	}
	
	public Integer getCanTipoIdentificacion() {
		return this.canTipoIdentificacion;
	}

	public void setCanTipoIdentificacion(Integer canTipoIdentificacion) {
		this.canTipoIdentificacion = canTipoIdentificacion;
	}

	public UsuarioDTO getBemUsuario() {
		return this.bemUsuario;
	}

	public void setBemUsuario(UsuarioDTO bemUsuario) {
		this.bemUsuario = bemUsuario;
	}
	
	public List<PostulacionDTO> getBemPostulacions() {
		return this.bemPostulacions;
	}

	public void setBemPostulacions(List<PostulacionDTO> bemPostulacions) {
		this.bemPostulacions = bemPostulacions;
	}
	
	public List<EstudioDTO> getBemEstudios() {
		return this.bemEstudios;
	}

	public void setBemEstudios(List<EstudioDTO> bemEstudios) {
		this.bemEstudios = bemEstudios;
	}
	
	public List<ExperienciaDTO> getBemExperiencias() {
		return this.bemExperiencias;
	}

	public void setBemExperiencias(List<ExperienciaDTO> bemExperiencias) {
		this.bemExperiencias = bemExperiencias;
	}
	
	public List<IdiomaDTO> getBemIdiomas() {
		return this.bemIdiomas;
	}

	public void setBemIdiomas(List<IdiomaDTO> bemIdiomas) {
		this.bemIdiomas = bemIdiomas;
	}
	
	public List<ReferenciaDTO> getBemReferencias() {
		return this.bemReferencias;
	}

	public void setBemReferencias(List<ReferenciaDTO> bemReferencias) {
		this.bemReferencias = bemReferencias;
	}
	
	public List<SoftwareDTO> getBemSoftwares() {
		return this.bemSoftwares;
	}

	public void setBemSoftwares(List<SoftwareDTO> bemSoftwares) {
		this.bemSoftwares = bemSoftwares;
	}



	public Timestamp getCanFechaNacimiento() {
		return canFechaNacimiento;
	}



	public void setCanFechaNacimiento(Timestamp canFechaNacimiento) {
		this.canFechaNacimiento = canFechaNacimiento;
	}



	public String getCanLugarNacimiento() {
		return canLugarNacimiento;
	}



	public void setCanLugarNacimiento(String canLugarNacimiento) {
		this.canLugarNacimiento = canLugarNacimiento;
	}



	public Integer getCanEstadoCivil() {
		return canEstadoCivil;
	}



	public void setCanEstadoCivil(Integer canEstadoCivil) {
		this.canEstadoCivil = canEstadoCivil;
	}



	public Integer getCanMaxEstudio() {
		return canMaxEstudio;
	}



	public void setCanMaxEstudio(Integer canMaxEstudio) {
		this.canMaxEstudio = canMaxEstudio;
	}
	
}