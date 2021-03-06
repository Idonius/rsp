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

import net.ciespal.redxxi.ejb.persistence.dao.DefensorDAO;
import net.ciespal.redxxi.ejb.persistence.entities.argos.DefensorDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

public class DefensorDAOImpl extends AbstractFacadeImpl<DefensorDTO> implements DefensorDAO {

	private static final Logger logger = LoggerFactory.getLogger(DefensorDAOImpl.class);
	
	public DefensorDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<DefensorDTO> getByAnd(DefensorDTO defensorDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<DefensorDTO> cq;
		Root<DefensorDTO> from;
		List<DefensorDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(DefensorDTO.class);
			
			from= cq.from(DefensorDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = defensorDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = defensorDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(defensorDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<DefensorDTO> tq=entityManager.createQuery(cq);
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
