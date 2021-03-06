package ec.edu.uce.inventario.inventario.servicio.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import ec.edu.uce.inventario.entidades.InvClase;
import ec.edu.uce.inventario.entidades.InvKardex;
import ec.edu.uce.inventario.entidades.RepFactura;
import ec.edu.uce.inventario.inventario.servicio.ReportService;

@Stateless(name = "reportService")
public class ReportServiceBean implements ReportService{

	@PersistenceContext(name = "inventarioPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Override
	public List<InvKardex> reportKardex(Date desde,Date hasta) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<InvKardex> cq=cb.createQuery(InvKardex.class);
		Root<InvKardex> from = cq.from(InvKardex.class);
		
		cq.where(cb.between(from.get("karFecha").as(Timestamp.class), new Timestamp(desde.getTime()), new Timestamp(hasta.getTime())));

		//entityManager.createQuery(cq).setMaxResults(1000).getResultList();
		List<InvKardex> list= entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	
	@Override
	public List<InvKardex> reportIngreso(Date desde,Date hasta) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<InvKardex> cq=cb.createQuery(InvKardex.class);
		Root<InvKardex> from = cq.from(InvKardex.class);
		Path<InvClase> join=from.join("invClase").get("claCodigo");
		
		cq.where(cb.and(cb.equal(join,1),cb.between(from.get("karFecha").as(Timestamp.class), new Timestamp(desde.getTime()), new Timestamp(hasta.getTime()))));
		
		//entityManager.createQuery(cq).setMaxResults(1000).getResultList();
		List<InvKardex> list= entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	
	@Override
	public List<InvKardex> reportEgreso(Date desde,Date hasta) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<InvKardex> cq=cb.createQuery(InvKardex.class);
		Root<InvKardex> from = cq.from(InvKardex.class);
		Path<InvClase> join=from.join("invClase").get("claCodigo");
		
		cq.where(cb.and(cb.equal(join,2),cb.between(from.get("karFecha").as(Timestamp.class), new Timestamp(desde.getTime()), new Timestamp(hasta.getTime()))));
		
		//entityManager.createQuery(cq).setMaxResults(1000).getResultList();
		List<InvKardex> list= entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	
	@Override
	public List<RepFactura> reportFactura(Date desde,Date hasta) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<RepFactura> cq=cb.createQuery(RepFactura.class);
		Root<RepFactura> from = cq.from(RepFactura.class);
		
		cq.where(cb.between(from.get("venFecha").as(Timestamp.class), new Timestamp(desde.getTime()),new Timestamp(hasta.getTime())));
		
		List<RepFactura> list= entityManager.createQuery(cq).getResultList();
		
		return list;
	}
	
}
