package ec.edu.uce.silsag.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.uce.silsag.commons.util.CalendarUtil;
import ec.edu.uce.silsag.ejb.persistence.dao.CatalogoDAO;
import ec.edu.uce.silsag.ejb.persistence.entities.CatalogoDTO;

public class CatalogoDAOImpl extends AbstractFacadeImpl<CatalogoDTO> implements CatalogoDAO{

	public CatalogoDAOImpl() {
		super();
	}

	public CatalogoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<CatalogoDTO> getAll(CatalogoDTO catalogo)
	{
		List<CatalogoDTO> list = null;
		if(catalogo.getCatCodigo()>0)
		{
		Query query = entityManager.createQuery("select cat from CatalogoDTO cat inner join cat.bemCatalogo catP where catP.catCodigo=?");
		query.setParameter(1, catalogo.getCatCodigo());
		
		list=query.getResultList();
		}
		else if(catalogo.getCatCodigo()==0){
			list=getYears();
		}
		else if (catalogo.getCatCodigo()==-1) {
			list=getMonths();
		}
		return list;
	}
	
	private List<CatalogoDTO> getYears()
	{
		List<CatalogoDTO> list=new ArrayList<CatalogoDTO>();
		CatalogoDTO catalogo;
		for(int i=CalendarUtil.getYear();i>1900;i--)
		{
			catalogo=new CatalogoDTO();
			catalogo.setCatCodigo(i);
			catalogo.setCatDescripcion(String.valueOf(i));
			list.add(catalogo);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	private List<CatalogoDTO> getMonths()
	{
		List<CatalogoDTO> list = new ArrayList<CatalogoDTO>();
		CatalogoDTO catalogo;
		Iterator it= getMonthList().entrySet().iterator();
		while(it.hasNext())
		{
			catalogo=new CatalogoDTO();
			Map.Entry e = (Map.Entry)it.next();
			catalogo.setCatCodigo((Integer) e.getKey());
			catalogo.setCatDescripcion((String) e.getValue());
			list.add(catalogo);
		}
		return list;
	}
	
	private Map<Integer, String> getMonthList()
	{
		Map<Integer, String> month=new HashMap<Integer, String>();
		month.put(1, "Enero");
		month.put(2, "Febrero");
		month.put(3, "Marzo");
		month.put(4, "Abril");
		month.put(5, "Mayo");
		month.put(6, "Junio");
		month.put(7, "Julio");
		month.put(8, "Agosto");
		month.put(9, "Septiembre");
		month.put(10, "Octubre");
		month.put(11, "Noviembre");
		month.put(12, "Diciembre");
		return month;
	}
}
