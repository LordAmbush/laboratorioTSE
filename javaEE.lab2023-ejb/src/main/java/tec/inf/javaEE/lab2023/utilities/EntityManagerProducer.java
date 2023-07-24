package tec.inf.javaEE.lab2023.utilities;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tec.inf.javaEE.lab2023.qualifier.JAVAEE2023;





@ApplicationScoped
public class EntityManagerProducer {

	@Produces
	@PersistenceContext(unitName="JAVAEE2023")
	@JAVAEE2023
	private EntityManager em;
	
}
