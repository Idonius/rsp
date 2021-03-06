package ec.edu.uce.besg.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the cue_resultado database table.
 * 
 */
@Entity
@Table(name="cue_resultado")
@NamedQuery(name="ResultadoDTO.findAll", query="SELECT r FROM ResultadoDTO r")
public class ResultadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUE_RESULTADO_RSUCODIGO_GENERATOR", sequenceName="CUE_RESULTADO_RSU_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUE_RESULTADO_RSUCODIGO_GENERATOR")
	@Column(name="rsu_codigo")
	private Integer rsuCodigo;

	@Column(name="rsu_valor_string")
	private String resValorString;

	@Column(name="rsu_valor_int")
	private Integer resValorInt;

	@Column(name="rsu_valor_date")
	@Temporal(TemporalType.DATE)
	private Date resValorDate;

	@Transient
	private String[] resArrayString;
	
	//bi-directional many-to-one association to CandidatoDTO
	@ManyToOne
	@JoinColumn(name="rsu_candidato")
	private CandidatoDTO bemCandidato;

	//bi-directional many-to-one association to RespuestaDTO
	@ManyToOne
	@JoinColumn(name="rsu_respuesta")
	private RespuestaDTO cueRespuesta;

	public ResultadoDTO() {
	}

	public Integer getRsuCodigo() {
		return this.rsuCodigo;
	}

	public void setRsuCodigo(Integer rsuCodigo) {
		this.rsuCodigo = rsuCodigo;
	}

	public CandidatoDTO getBemCandidato() {
		return this.bemCandidato;
	}

	public void setBemCandidato(CandidatoDTO bemCandidato) {
		this.bemCandidato = bemCandidato;
	}

	public RespuestaDTO getCueRespuesta() {
		return this.cueRespuesta;
	}

	public void setCueRespuesta(RespuestaDTO cueRespuesta) {
		this.cueRespuesta = cueRespuesta;
	}

	public String getResValorString() {
		return resValorString;
	}

	public void setResValorString(String resValorString) {
		this.resValorString = resValorString;
	}

	public Integer getResValorInt() {
		return resValorInt;
	}

	public void setResValorInt(Integer resValorInt) {
		this.resValorInt = resValorInt;
	}

	public Date getResValorDate() {
		return resValorDate;
	}

	public void setResValorDate(Date resValorDate) {
		this.resValorDate = resValorDate;
	}

	public String[] getResArrayString() {
		return resArrayString;
	}

	public void setResArrayString(String[] resArrayString) {
		this.resArrayString = resArrayString;
	}

}