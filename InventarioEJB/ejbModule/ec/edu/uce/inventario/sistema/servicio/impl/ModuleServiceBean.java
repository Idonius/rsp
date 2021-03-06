package ec.edu.uce.inventario.sistema.servicio.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ec.edu.uce.inventario.entidades.SisModulo;
import ec.edu.uce.inventario.sistema.servicio.ModuleService;

@Stateless(name = "moduleService")
public class ModuleServiceBean implements ModuleService{

	@PersistenceContext(name = "inventarioPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
    public ModuleServiceBean(){}
   
    @SuppressWarnings("unchecked")
    @Override
    public List<SisModulo> readAll() {
        List<SisModulo> objects;
       
        try {
            Query query = entityManager.createQuery("from SisModulo");
            objects = query.getResultList();
        } catch (Exception e) {
            objects=null;
        }
        return objects;
    }	
}
