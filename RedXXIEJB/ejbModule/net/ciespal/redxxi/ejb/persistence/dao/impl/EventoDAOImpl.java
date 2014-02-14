package net.ciespal.redxxi.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import net.ciespal.redxxi.ejb.persistence.dao.EventoDAO;
import net.ciespal.redxxi.ejb.persistence.entities.CarreraDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EntidadDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EventoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ProyectoInvestigacionDTO;

import com.corvustec.commons.util.CorvustecException;

public class EventoDAOImpl extends AbstractFacadeImpl<EventoDTO> implements EventoDAO{

	public EventoDAOImpl() {
		super();
	}

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<EventoDTO> getAll(CarreraDTO carrera) throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EventoDTO> cq=cb.createQuery(EventoDTO.class);
		Root<EventoDTO> from = cq.from(EventoDTO.class);
		
		Path<CarreraDTO> join1=from.join("ateEntidads");
		
		cq.where(cb.equal(join1.get("ateCarrera"), carrera.getCarCodigo()));
		
		List<EventoDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	
	
	
	@Override
	public void remove2(EventoDTO eve)
	{
		Query query;
		for(EntidadDTO ent:eve.getAteEntidads())
		{	 
			query= entityManager.createQuery("delete from EntidadDTO where entCodigo=:codigo");
			query.setParameter("codigo", ent.getEntCodigo());
			query.executeUpdate();
		}
		
		query= entityManager.createQuery("delete from EventoDTO where eveCodigo=:codigo");
		query.setParameter("codigo", eve.getEveCodigo());
		query.executeUpdate();
	}
}
