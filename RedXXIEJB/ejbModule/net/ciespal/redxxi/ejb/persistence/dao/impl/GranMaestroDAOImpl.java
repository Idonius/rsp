package net.ciespal.redxxi.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.ciespal.redxxi.ejb.persistence.dao.GranMaestroDAO;
import net.ciespal.redxxi.ejb.persistence.entities.espejo.GranMaestroDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

public class GranMaestroDAOImpl extends AbstractFacadeImpl<GranMaestroDTO> implements GranMaestroDAO{

	private static final Logger logger = LoggerFactory.getLogger(GranMaestroDAOImpl.class);
	
	public GranMaestroDAOImpl() {
		super();
	}

	public GranMaestroDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<GranMaestroDTO> findAll(Object ciudad) throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<GranMaestroDTO> cq=cb.createQuery(GranMaestroDTO.class);
		Root<GranMaestroDTO> from= cq.from(GranMaestroDTO.class);
		
		cq.where(cb.equal(from.get("gmaCiudad"), ciudad));
		
		List<GranMaestroDTO> list=entityManager.createQuery(cq).getResultList();	
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	
	@Override
	public List<GranMaestroDTO> getByAnd(GranMaestroDTO granMaestroDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<GranMaestroDTO> cq;
		Root<GranMaestroDTO> from;
		List<GranMaestroDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(GranMaestroDTO.class);
			
			from= cq.from(GranMaestroDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = granMaestroDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID")&&!fieldName.equals("gmaCount"))
				{
				    getter = granMaestroDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(granMaestroDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<GranMaestroDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			logger.info(e.toString());
			throw new CorvustecException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}
	}

	
	@Override	
	public int count(Object pais) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<GranMaestroDTO> cq;
		Root<GranMaestroDTO> from;
		List<GranMaestroDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		try{
		
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(GranMaestroDTO.class);
			
			from= cq.from(GranMaestroDTO.class);
			
			cq.multiselect(cb.count(from.get("gmaCodigo")));
			
			predicateList=new ArrayList<Predicate>();
			if(pais!=null)
			{
				predicate=cb.equal(from.get("gmaPais"),pais);
				predicateList.add(predicate);
			}
			if(predicateList.size()>0)
				cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<GranMaestroDTO> tq=entityManager.createQuery(cq);
			
			list=tq.getResultList();
			
			if(!list.isEmpty())
				return (int)(long)list.get(0).getGmaCount();
			else
				return 0;
			
		}catch(Exception e){
			logger.info("count"+ e.toString());
			throw new CorvustecException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}
	}

	
}
