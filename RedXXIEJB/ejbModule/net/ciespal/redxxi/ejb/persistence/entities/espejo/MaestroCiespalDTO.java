package net.ciespal.redxxi.ejb.persistence.entities.espejo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the esp_maestro_ciespal database table.
 * 
 */
@Entity
@Table(name="esp_maestro_ciespal")
@NamedQuery(name="MaestroCiespalDTO.findAll", query="SELECT m FROM MaestroCiespalDTO m")
public class MaestroCiespalDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESP_MAESTRO_CIESPAL_MCICODIGO_GENERATOR", sequenceName="ESP_MAESTRO_CIESPAL_MCI_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESP_MAESTRO_CIESPAL_MCICODIGO_GENERATOR")
	@Column(name="mci_codigo")
	private Integer mciCodigo;

	@Column(name="mci_apellido")
	private String mciApellido;

	@Column(name="mci_campo_conocimiento")
	private String mciCampoConocimiento;

	@Column(name="mci_ciudad")
	private Integer mciCiudad;

	@Column(name="mci_fecha_nacimiento")
	private Timestamp mciFechaNacimiento;

	@Column(name="mci_foto")
	private byte[] mciFoto;

	@Column(name="mci_foto_nombre")
	private String mciFotoNombre;
	
	@Column(name="mci_nombre")
	private String mciNombre;

	@Column(name="mci_pais")
	private Integer mciPais;

	@Column(name="mci_perfil_biografico")
	private String mciPerfilBiografico;

	@Column(name="mci_provincia")
	private Integer mciProvincia;

	@Transient
	private long mciCount;
	
	//bi-directional many-to-one association to EntidadDTO
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="mci_entidad")
	private EntidadEspejoDTO espEntidad;

	public MaestroCiespalDTO() {
	}

	public MaestroCiespalDTO(long mciCount) {
		this.mciCount=mciCount;
	}
	
	public Integer getMciCodigo() {
		return this.mciCodigo;
	}

	public void setMciCodigo(Integer mciCodigo) {
		this.mciCodigo = mciCodigo;
	}

	public String getMciApellido() {
		return this.mciApellido;
	}

	public void setMciApellido(String mciApellido) {
		this.mciApellido = mciApellido;
	}

	public String getMciCampoConocimiento() {
		return this.mciCampoConocimiento;
	}

	public void setMciCampoConocimiento(String mciCampoConocimiento) {
		this.mciCampoConocimiento = mciCampoConocimiento;
	}

	public Integer getMciCiudad() {
		return this.mciCiudad;
	}

	public void setMciCiudad(Integer mciCiudad) {
		this.mciCiudad = mciCiudad;
	}

	public Timestamp getMciFechaNacimiento() {
		return this.mciFechaNacimiento;
	}

	public void setMciFechaNacimiento(Timestamp mciFechaNacimiento) {
		this.mciFechaNacimiento = mciFechaNacimiento;
	}

	public byte[] getMciFoto() {
		return this.mciFoto;
	}

	public void setMciFoto(byte[] mciFoto) {
		this.mciFoto = mciFoto;
	}

	public String getMciFotoNombre() {
		return mciFotoNombre;
	}

	public void setMciFotoNombre(String mciFotoNombre) {
		this.mciFotoNombre = mciFotoNombre;
	}

	public String getMciNombre() {
		return this.mciNombre;
	}

	public void setMciNombre(String mciNombre) {
		this.mciNombre = mciNombre;
	}

	public Integer getMciPais() {
		return this.mciPais;
	}

	public void setMciPais(Integer mciPais) {
		this.mciPais = mciPais;
	}

	public long getMciCount() {
		return mciCount;
	}

	public void setMciCount(long mciCount) {
		this.mciCount = mciCount;
	}

	public String getMciPerfilBiografico() {
		return this.mciPerfilBiografico;
	}

	public void setMciPerfilBiografico(String mciPerfilBiografico) {
		this.mciPerfilBiografico = mciPerfilBiografico;
	}

	public Integer getMciProvincia() {
		return this.mciProvincia;
	}

	public void setMciProvincia(Integer mciProvincia) {
		this.mciProvincia = mciProvincia;
	}

	public EntidadEspejoDTO getEspEntidad() {
		return this.espEntidad;
	}

	public void setEspEntidad(EntidadEspejoDTO espEntidad) {
		this.espEntidad = espEntidad;
	}

}