package curso.java.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.hibernate.entity.Guerrero;
import curso.java.hibernate.util.JpaUtil;
import jakarta.persistence.EntityManager;


public class CrearGuerrero {
	private static final Logger logger = LogManager.getLogger(CrearGuerrero.class);
	public static void main(String[] args) {
		
		logger.debug("Empezando");
        EntityManager em = JpaUtil.getEntityManager();
        Guerrero guerrero1 = new Guerrero("Borja","Humano",10,6,1000);
        Guerrero guerrero2 = new Guerrero("Carlos","Orco",8,10,1000);

        em.getTransaction().begin();
        em.persist(guerrero1);
        em.persist(guerrero2);
        em.getTransaction().commit();
        
        List<Guerrero> guerreros = em.createQuery("from Guerrero", Guerrero.class).getResultList();
        System.out.println("\n");
        guerreros.forEach(System.out::println);//Método Referencia
        //guerreros.forEach(guerrero->System.out.println(guerrero));
        em.close();
        logger.debug("Final");

	}

}
