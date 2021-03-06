package net.ciespal.redxxi.ejb.persistence.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ate_organizacion database table.
 * 
 */
@Entity
@Table(name="ate_organizacion")
@NamedQuery(name="OrganizacionDTO.findAll", query="SELECT o FROM OrganizacionDTO o")
public class OrganizacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATE_ORGANIZACION_ORGCODIGO_GENERATOR", sequenceName="ATE_ORGANIZACION_ORG_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATE_ORGANIZACION_ORGCODIGO_GENERATOR")
	@Column(name="org_codigo")
	private Integer orgCodigo;

	@Column(name="org_anio_fundacion")
	private Integer orgAnioFundacion;

	@Column(name="org_datos_institucionales")
	private String orgDatosInstitucionales;

	@Column(name="org_mienbros")
	private Integer orgMienbros;

	@Column(name="org_mision")
	private String orgMision;

	@Column(name="org_nombre")
	private String orgNombre;

	@Column(name="org_institucion_superior")
	private String orgInstitucionSuperior;

	@Column(name="org_ciudad")
	private Integer orgCiudad;

	@Column(name="org_provincia")
	private Integer orgProvincia;

	@Column(name="org_pais")
	private Integer orgPais;

	@Transient
	private Long orgCount;

	//bi-directional many-to-one association to EntidadDTO
	@OneToMany(mappedBy="ateOrganizacion",cascade={CascadeType.ALL,CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<EntidadDTO> ateEntidads;

	public OrganizacionDTO() {
	}

	public OrganizacionDTO(Long orgCount) {
		this.orgCount = orgCount;
	}

	public Integer getOrgCodigo() {
		return this.orgCodigo;
	}

	public void setOrgCodigo(Integer orgCodigo) {
		this.orgCodigo = orgCodigo;
	}

	public Integer getOrgAnioFundacion() {
		return this.orgAnioFundacion;
	}

	public void setOrgAnioFundacion(Integer orgAnioFundacion) {
		this.orgAnioFundacion = orgAnioFundacion;
	}

	public String getOrgDatosInstitucionales() {
		return this.orgDatosInstitucionales;
	}

	public void setOrgDatosInstitucionales(String orgDatosInstitucionales) {
		this.orgDatosInstitucionales = orgDatosInstitucionales;
	}

	public Integer getOrgMienbros() {
		return this.orgMienbros;
	}

	public void setOrgMienbros(Integer orgMienbros) {
		this.orgMienbros = orgMienbros;
	}

	public String getOrgMision() {
		return this.orgMision;
	}

	public void setOrgMision(String orgMision) {
		this.orgMision = orgMision;
	}

	public String getOrgNombre() {
		return this.orgNombre;
	}

	public void setOrgNombre(String orgNombre) {
		this.orgNombre = orgNombre;
	}

	public String getOrgInstitucionSuperior() {
		return orgInstitucionSuperior;
	}

	public void setOrgInstitucionSuperior(String orgInstitucionSuperior) {
		this.orgInstitucionSuperior = orgInstitucionSuperior;
	}

	public Integer getOrgCiudad() {
		return orgCiudad;
	}

	public void setOrgCiudad(Integer orgCiudad) {
		this.orgCiudad = orgCiudad;
	}

	public Integer getOrgProvincia() {
		return orgProvincia;
	}

	public void setOrgProvincia(Integer orgProvincia) {
		this.orgProvincia = orgProvincia;
	}

	public Integer getOrgPais() {
		return orgPais;
	}

	public void setOrgPais(Integer orgPais) {
		this.orgPais = orgPais;
	}

	public Long getOrgCount() {
		return orgCount;
	}

	public void setOrgCount(Long orgCount) {
		this.orgCount = orgCount;
	}

	public List<EntidadDTO> getAteEntidads() {
		return this.ateEntidads;
	}

	public void setAteEntidads(List<EntidadDTO> ateEntidads) {
		this.ateEntidads = ateEntidads;
	}

	public EntidadDTO addAteEntidad(EntidadDTO ateEntidad) {
		if(ateEntidad!=null)
		{
			if(getAteEntidads()==null)
				setAteEntidads(new ArrayList<EntidadDTO>());
			getAteEntidads().add(ateEntidad);
			ateEntidad.setAteOrganizacion(this);
		}
		return ateEntidad;
	}

	public EntidadDTO removeAteEntidad(EntidadDTO ateEntidad) {
		getAteEntidads().remove(ateEntidad);
		ateEntidad.setAteOrganizacion(null);

		return ateEntidad;
	}

}