package ec.edu.uce.erpmunicipal.presupuesto.orm;

import java.io.Serializable;
import javax.persistence.*;

import ec.edu.uce.erpmunicipal.contabilidad.orm.ConCuenta;

import java.math.BigDecimal;


/**
 * The persistent class for the pre_programa_cuenta database table.
 * 
 */
@Entity
@Table(name="pre_programa_cuenta")
public class PreProgramaCuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRE_PROGRAMA_CUENTA_PCUCODIGO_GENERATOR", sequenceName="PRE_PROGRAMA_CUENTA_PCU_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRE_PROGRAMA_CUENTA_PCUCODIGO_GENERATOR")
	@Column(name="pcu_codigo")
	private Integer pcuCodigo;

	@Column(name="pcu_valor")
	private BigDecimal pcuValor;

	@ManyToOne
	@JoinColumn(name="pcu_cuenta")
	private ConCuenta conCuenta;
	
	//bi-directional many-to-one association to PrePrograma
	@ManyToOne
	@JoinColumn(name="pcu_programa")
	private PrePrograma prePrograma;

	//bi-directional many-to-one association to PreTipoProgramaCuenta
	@ManyToOne
	@JoinColumn(name="pcu_tipo")
	private PreTipoProgramaCuenta preTipoProgramaCuenta;

	
	public PreProgramaCuenta() {
	}

	public Integer getPcuCodigo() {
		return this.pcuCodigo;
	}

	public void setPcuCodigo(Integer pcuCodigo) {
		this.pcuCodigo = pcuCodigo;
	}

	public ConCuenta getConCuenta() {
		return this.conCuenta;
	}

	public void setConCuenta(ConCuenta conCuenta) {
		this.conCuenta = conCuenta;
	}

	public BigDecimal getPcuValor() {
		return this.pcuValor;
	}

	public void setPcuValor(BigDecimal pcuValor) {
		this.pcuValor = pcuValor;
	}

	public PrePrograma getPrePrograma() {
		return this.prePrograma;
	}

	public void setPrePrograma(PrePrograma prePrograma) {
		this.prePrograma = prePrograma;
	}

	public PreTipoProgramaCuenta getPreTipoProgramaCuenta() {
		return this.preTipoProgramaCuenta;
	}

	public void setPreTipoProgramaCuenta(PreTipoProgramaCuenta preTipoProgramaCuenta) {
		this.preTipoProgramaCuenta = preTipoProgramaCuenta;
	}

}