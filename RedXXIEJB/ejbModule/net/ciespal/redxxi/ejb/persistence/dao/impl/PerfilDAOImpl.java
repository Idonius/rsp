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

import net.ciespal.redxxi.ejb.persistence.dao.PerfilDAO;
import net.ciespal.redxxi.ejb.persistence.entities.security.PerfilDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

public class PerfilDAOImpl extends AbstractFacadeImpl<PerfilDTO> implements PerfilDAO{

	private static final Logger logger = LoggerFactory.getLogger(PerfilDAOImpl.class);
	
	public PerfilDAOImpl() {
		super();
	}

	public PerfilDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}


	@Override
	public List<PerfilDTO> getByAnd(PerfilDTO perfilDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<PerfilDTO> cq;
		Root<PerfilDTO> from;
		List<PerfilDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(PerfilDTO.class);
			
			from= cq.from(PerfilDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = perfilDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = perfilDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(perfilDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	        
	    	predicate=cb.greaterThan(from.get("perCodigo").as(Integer.class), 0);
	    	predicateList.add(predicate);                		        
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<PerfilDTO> tq=entityManager.createQuery(cq);
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

	
}
