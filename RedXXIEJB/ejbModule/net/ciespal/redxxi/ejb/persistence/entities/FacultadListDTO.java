package net.ciespal.redxxi.ejb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ate_facultad_vie database table.
 * 
 */
@Entity
@Table(name="ate_facultad_vie")
@NamedQuery(name="FacultadListDTO.findAll", query="SELECT f FROM FacultadListDTO f")
public class FacultadListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_cuidad")
	private String catCuidad;

	@Column(name="cat_pais")
	private String catPais;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="cen_anio_fundacion")
	private Integer cenAnioFundacion;

	@Column(name="cen_categoria")
	private String cenCategoria;

	@Column(name="cen_ciudad")
	private Integer cenCiudad;

	@Id
	@Column(name="cen_codigo")
	private Integer cenCodigo;

	@Column(name="cen_dato_institucional")
	private String cenDatoInstitucional;

	@Column(name="cen_nombre")
	private String cenNombre;

	@Column(name="cen_pagina_web")
	private String cenPaginaWeb;

	@Column(name="cen_pais")
	private Integer cenPais;

	@Column(name="cen_predecesor")
	private Integer cenPredecesor;

	@Column(name="cen_provincia")
	private Integer cenProvincia;

	@Column(name="cen_tipo")
	private Integer cenTipo;

	@Column(name="cen_universidad")
	private String cenUniversidad;

	
	public FacultadListDTO() {
	}

	public String getCatCuidad() {
		return this.catCuidad;
	}

	public void setCatCuidad(String catCuidad) {
		this.catCuidad = catCuidad;
	}

	public String getCatPais() {
		return this.catPais;
	}

	public void setCatPais(String catPais) {
		this.catPais = catPais;
	}

	public String getCatProvincia() {
		return this.catProvincia;
	}

	public void setCatProvincia(String catProvincia) {
		this.catProvincia = catProvincia;
	}

	public Integer getCenAnioFundacion() {
		return this.cenAnioFundacion;
	}

	public void setCenAnioFundacion(Integer cenAnioFundacion) {
		this.cenAnioFundacion = cenAnioFundacion;
	}

	public String getCenCategoria() {
		return this.cenCategoria;
	}

	public void setCenCategoria(String cenCategoria) {
		this.cenCategoria = cenCategoria;
	}

	public Integer getCenCiudad() {
		return this.cenCiudad;
	}

	public void setCenCiudad(Integer cenCiudad) {
		this.cenCiudad = cenCiudad;
	}

	public Integer getCenCodigo() {
		return this.cenCodigo;
	}

	public void setCenCodigo(Integer cenCodigo) {
		this.cenCodigo = cenCodigo;
	}

	public String getCenDatoInstitucional() {
		return this.cenDatoInstitucional;
	}

	public void setCenDatoInstitucional(String cenDatoInstitucional) {
		this.cenDatoInstitucional = cenDatoInstitucional;
	}

	public String getCenNombre() {
		return this.cenNombre;
	}

	public void setCenNombre(String cenNombre) {
		this.cenNombre = cenNombre;
	}

	public String getCenPaginaWeb() {
		return this.cenPaginaWeb;
	}

	public void setCenPaginaWeb(String cenPaginaWeb) {
		this.cenPaginaWeb = cenPaginaWeb;
	}

	public Integer getCenPais() {
		return this.cenPais;
	}

	public void setCenPais(Integer cenPais) {
		this.cenPais = cenPais;
	}

	public Integer getCenPredecesor() {
		return this.cenPredecesor;
	}

	public void setCenPredecesor(Integer cenPredecesor) {
		this.cenPredecesor = cenPredecesor;
	}

	public Integer getCenProvincia() {
		return this.cenProvincia;
	}

	public void setCenProvincia(Integer cenProvincia) {
		this.cenProvincia = cenProvincia;
	}

	public Integer getCenTipo() {
		return this.cenTipo;
	}

	public void setCenTipo(Integer cenTipo) {
		this.cenTipo = cenTipo;
	}

	public String getCenUniversidad() {
		return cenUniversidad;
	}

	public void setCenUniversidad(String cenUniversidad) {
		this.cenUniversidad = cenUniversidad;
	}

}