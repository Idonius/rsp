package ec.edu.uce.besg.ejb.persistence.dao.impl;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.besg.common.util.CorvustecException;
import ec.edu.uce.besg.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.besg.ejb.persistence.entity.EmpresaDTO;



public class EmpresaDAOImpl extends AbstractFacadeImpl<EmpresaDTO> implements EmpresaDAO {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	public EmpresaDAOImpl() {
		super();
	}

	public EmpresaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<EmpresaDTO> getByAnd(EmpresaDTO empresaDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<EmpresaDTO> cq;
		Root<EmpresaDTO> from;
		List<EmpresaDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(EmpresaDTO.class);
			from= cq.from(EmpresaDTO.class);
			predicateList=new ArrayList<Predicate>();
			fields = empresaDTO.getClass().getDeclaredFields();
	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = empresaDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    value = getter.invoke(empresaDTO, new Object[0]);
				    if(value!=null && value!="")
				    {
			    		predicate=cb.equal(from.get(fieldName), value);
			    		predicateList.add(predicate);
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			TypedQuery<EmpresaDTO> tq=entityManager.createQuery(cq);
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
