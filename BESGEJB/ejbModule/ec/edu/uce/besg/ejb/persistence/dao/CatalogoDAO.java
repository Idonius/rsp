package ec.edu.uce.besg.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.besg.common.util.CorvustecException;
import ec.edu.uce.besg.ejb.persistence.entity.security.CatalogoDTO;

public interface CatalogoDAO extends AbstractFacade<CatalogoDTO>{

	List<CatalogoDTO> getByAnd(CatalogoDTO catalogo) throws CorvustecException;

}
