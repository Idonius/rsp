package ec.edu.uce.inventario.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the inv_kardex database table.
 * 
 */
@Entity
@Table(name="inv_kardex")
public class InvKardex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_KARDEX_KARCODIGO_GENERATOR", sequenceName="INV_KARDEX_KAR_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_KARDEX_KARCODIGO_GENERATOR")
	@Column(name="kar_codigo")
	private Integer karCodigo;

	@Column(name="kar_cantidad")
	private BigDecimal karCantidad;

	@Column(name="kar_fecha")
	private Timestamp karFecha;

	@Column(name="kar_valor_total")
	private BigDecimal karValorTotal;

	@Column(name="kar_valor_unitario")
	private BigDecimal karValorUnitario;

	@Column(name="kar_saldo")
	private BigDecimal karSaldo;

	//bi-directional many-to-one association to InvArticulo
	@ManyToOne
	@JoinColumn(name="kar_articulo")
	private InvArticulo invArticulo;

	//bi-directional many-to-one association to InvClase
	@ManyToOne
	@JoinColumn(name="kar_clase")
	private InvClase invClase;

	public InvKardex() {
	}

	public Integer getKarCodigo() {
		return this.karCodigo;
	}

	public void setKarCodigo(Integer karCodigo) {
		this.karCodigo = karCodigo;
	}

	public BigDecimal getKarCantidad() {
		return this.karCantidad;
	}

	public void setKarCantidad(BigDecimal karCantidad) {
		this.karCantidad = karCantidad;
	}

	public Timestamp getKarFecha() {
		return this.karFecha;
	}

	public void setKarFecha(Timestamp karFecha) {
		this.karFecha = karFecha;
	}

	public BigDecimal getKarValorTotal() {
		return this.karValorTotal;
	}

	public void setKarValorTotal(BigDecimal karValorTotal) {
		this.karValorTotal = karValorTotal;
	}

	public BigDecimal getKarValorUnitario() {
		return this.karValorUnitario;
	}

	public void setKarValorUnitario(BigDecimal karValorUnitario) {
		this.karValorUnitario = karValorUnitario;
	}

	public BigDecimal getKarSaldo() {
		return karSaldo;
	}

	public void setKarSaldo(BigDecimal karSaldo) {
		this.karSaldo = karSaldo;
	}

	public InvArticulo getInvArticulo() {
		return this.invArticulo;
	}

	public void setInvArticulo(InvArticulo invArticulo) {
		this.invArticulo = invArticulo;
	}

	public InvClase getInvClase() {
		return this.invClase;
	}

	public void setInvClase(InvClase invClase) {
		this.invClase = invClase;
	}

}