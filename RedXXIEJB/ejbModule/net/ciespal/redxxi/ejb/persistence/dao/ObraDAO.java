package net.ciespal.redxxi.ejb.persistence.dao;

import java.util.List;

import com.corvustec.commons.util.CorvustecException;

import net.ciespal.redxxi.ejb.persistence.entities.DoctorDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ObraDTO;

public interface ObraDAO extends AbstractFacade<ObraDTO> {

	List<ObraDTO> getAll(DoctorDTO doctor) throws CorvustecException;

	void remove2(ObraDTO obra) throws CorvustecException;

}
