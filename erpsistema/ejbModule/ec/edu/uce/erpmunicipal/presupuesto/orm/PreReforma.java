package ec.edu.uce.erpmunicipal.presupuesto.orm;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pre_reforma database table.
 * 
 */
@Entity
@Table(name="pre_reforma")
public class PreReforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRE_REFORMA_REFCODIGO_GENERATOR", sequenceName="PRE_REFORMA_REF_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRE_REFORMA_REFCODIGO_GENERATOR")
	@Column(name="ref_codigo")
	private Integer refCodigo;

	@Column(name="ref_fecha")
	private Timestamp refFecha;

	@Column(name="ref_motivo")
	private String refMotivo;

	//bi-directional many-to-one association to PreReformaDetalle
	@OneToMany(mappedBy="preReforma")
	private List<PreReformaDetalle> preReformaDetalles;

	//bi-directional many-to-one association to PreEstado
	@ManyToOne
	@JoinColumn(name="ref_estado")
	private PreEstado preEstado;

	//bi-directional many-to-one association to PreVerificacion
	@ManyToOne
	@JoinColumn(name="ref_verificacion")
	private PreVerificacion preVerificacion;

	public PreReforma() {
	}

	public Integer getRefCodigo() {
		return this.refCodigo;
	}

	public void setRefCodigo(Integer refCodigo) {
		this.refCodigo = refCodigo;
	}

	public Timestamp getRefFecha() {
		return this.refFecha;
	}

	public void setRefFecha(Timestamp refFecha) {
		this.refFecha = refFecha;
	}

	public String getRefMotivo() {
		return this.refMotivo;
	}

	public void setRefMotivo(String refMotivo) {
		this.refMotivo = refMotivo;
	}

	public List<PreReformaDetalle> getPreReformaDetalles() {
		return this.preReformaDetalles;
	}

	public void setPreReformaDetalles(List<PreReformaDetalle> preReformaDetalles) {
		this.preReformaDetalles = preReformaDetalles;
	}

	public PreEstado getPreEstado() {
		return this.preEstado;
	}

	public void setPreEstado(PreEstado preEstado) {
		this.preEstado = preEstado;
	}

	public PreVerificacion getPreVerificacion() {
		return this.preVerificacion;
	}

	public void setPreVerificacion(PreVerificacion preVerificacion) {
		this.preVerificacion = preVerificacion;
	}

}