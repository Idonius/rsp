package ec.edu.uce.silsag.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ec.edu.uce.silsag.commons.util.SilsagException;
import ec.edu.uce.silsag.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.silsag.ejb.persistence.entities.EmpresaDTO;

public class EmpresaDAOImpl extends AbstractFacadeImpl<EmpresaDTO> implements EmpresaDAO{

	public EmpresaDAOImpl() {
		super();
	}

	public EmpresaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<EmpresaDTO> getAll() throws SilsagException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpresaDTO> cq=cb.createQuery(EmpresaDTO.class);
		cq.from(EmpresaDTO.class);
				
		List<EmpresaDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}	
}
