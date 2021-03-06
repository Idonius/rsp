package ec.edu.uce.inventario.web.facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import ec.edu.uce.inventario.entidades.FacCliente;
import ec.edu.uce.inventario.entidades.FacDetalleVenta;
import ec.edu.uce.inventario.entidades.FacVenta;
import ec.edu.uce.inventario.entidades.InvArticulo;
import ec.edu.uce.inventario.facturacion.servicio.ClienteService;
import ec.edu.uce.inventario.facturacion.servicio.FacturaService;
import ec.edu.uce.inventario.inventario.servicio.ArticuloService;

@ManagedBean(name = "ventaPage")
@ViewScoped
public class VentaPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "articuloService/local")
	private ArticuloService articuloService;
	@EJB(name = "clienteService/local")
	private ClienteService clienteService;
	@EJB(name = "facturaService/local")
	private FacturaService facturaService;
	
	private FacCliente cliente;
	private FacVenta venta;
	private FacDetalleVenta detalle;
	private InvArticulo articulo;
	
	private List<FacCliente> listCliente;
	private List<FacVenta> ventas;
	private List<FacDetalleVenta> detalles;
	private List<InvArticulo> listArticulos;
	
	private String textCliente;
	private String textArticulo;
	
	private Date fecha;
	
	public VentaPage()
	{
		ventas=new ArrayList<FacVenta>();
		venta=new FacVenta();
		listCliente=new ArrayList<FacCliente>();
		cliente=new FacCliente();
		detalles=new ArrayList<FacDetalleVenta>();
		detalle=new FacDetalleVenta();
		detalle.setDveDescuentoPorcentaje(BigDecimal.valueOf(0.0));
		venta.setVenTotal(BigDecimal.valueOf(0.0));
		articulo=new InvArticulo();
		Calendar cal=Calendar.getInstance();
		fecha=cal.getTime();
		venta.setVenDescuentoPorcentaje(BigDecimal.valueOf(0.0));
	}

	public FacCliente getCliente() {
		return cliente;
	}

	public void setCliente(FacCliente cliente) {
		this.cliente = cliente;
	}

	public List<FacCliente> getListCliente() {
		return listCliente;
	}

	public void setListCliente(List<FacCliente> listCliente) {
		this.listCliente = listCliente;
	}
	
	public FacVenta getVenta() {
		return venta;
	}

	public void setVenta(FacVenta venta) {
		this.venta = venta;
	}

	public List<FacVenta> getVentas() {
		return ventas;
	}

	public void setVentas(List<FacVenta> ventas) {
		this.ventas = ventas;
	}

	public List<FacDetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<FacDetalleVenta> detalles) {
		this.detalles = detalles;
	}

	public String getTextCliente() {
		return textCliente;
	}

	public void setTextCliente(String textCliente) {
		this.textCliente = textCliente;
	}

	public List<InvArticulo> getListArticulos() {
		return listArticulos;
	}

	public void setListArticulos(List<InvArticulo> listArticulos) {
		this.listArticulos = listArticulos;
	}

	public String getTextArticulo() {
		return textArticulo;
	}

	public void setTextArticulo(String textArticulo) {
		this.textArticulo = textArticulo;
	}

	public FacDetalleVenta getDetalle() {
		return detalle;
	}

	public void setDetalle(FacDetalleVenta detalle) {
		this.detalle = detalle;
	}

	public InvArticulo getArticulo() {
		return articulo;
	}

	public void setArticulo(InvArticulo articulo) {
		this.articulo = articulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void create()
	{
		try{
			if(cliente.getCliCodigo()==null)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Cliente"));
				return;				
			}
			if(venta.getVenPedido()==null||venta.getVenPedido().trim().equals(""))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Fecha"));
				return;							
			}
			if(venta.getVenDescuentoPorcentaje()==null)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Descuento"));
				return;										
			}
			if(detalles.size()<=0)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Agregue Detalles"));
				return;	
			}
			venta.setVenFecha(new Timestamp(fecha.getTime()));
			facturaService.create(venta, detalles, cliente);
			
			Double descuento=venta.getVenTotal().doubleValue()*(venta.getVenDescuentoPorcentaje().doubleValue()/100);
			Double tot=venta.getVenTotal().doubleValue()-descuento;
			venta.setVenTotal(BigDecimal.valueOf(tot));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void add()
	{
		if(this.articulo.getArtCodigo()==null)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Articulo"));
			return;
		}
		if(this.detalle.getDvePrecio()==null||this.detalle.getDvePrecio().doubleValue()==0)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Precio"));
			return;
		}
		if(this.detalle.getDveCantidad()==null||this.detalle.getDveCantidad().doubleValue()==0)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Ingrese Cantidad"));
			return;
		}

		Double total=detalle.getDveCantidad().doubleValue()*detalle.getDvePrecio().doubleValue();
		
		this.detalle.setDveTotal(BigDecimal.valueOf(total));
		this.detalle.setInvArticulo(articulo);
		this.detalles.add(detalle);
		
		this.venta.setVenTotal(BigDecimal.valueOf(venta.getVenTotal().doubleValue()+total));
		
		detalle=new FacDetalleVenta();
		detalle.setDveDescuentoPorcentaje(BigDecimal.valueOf(0.0));
		articulo=new InvArticulo();
	}
	
	public void clear() {
		detalle=new FacDetalleVenta();
		detalle.setDveDescuentoPorcentaje(BigDecimal.valueOf(0.0));
		articulo=new InvArticulo();		
		detalles=new ArrayList<FacDetalleVenta>();
		venta=new FacVenta();
		venta.setVenTotal(BigDecimal.valueOf(0.0));
		venta.setVenDescuentoPorcentaje(BigDecimal.valueOf(0.0));
	}
	
	public void onRowSelect(SelectEvent event)
	{
		
	}

	public void onRowSelectArticulo(SelectEvent event)
	{
		this.articulo = (InvArticulo) event.getObject();
		this.detalle.setDvePrecio(this.articulo.getArtPrecio());
		this.textArticulo = "";
		this.listArticulos = new ArrayList<InvArticulo>();
	}

	public void onRowSelectCliente(SelectEvent event)
	{
		this.cliente = (FacCliente) event.getObject();
		this.textCliente = "";
		this.listCliente = new ArrayList<FacCliente>();
	}

	
	public void searchArticulo() {
		listArticulos = new ArrayList<InvArticulo>();
		listArticulos = articuloService.dynamicSearch(textArticulo);
	}
	
	public void searchCliente() {
		listCliente = new ArrayList<FacCliente>();
		listCliente = clienteService.dynamicSearch(textCliente);
	}
}
