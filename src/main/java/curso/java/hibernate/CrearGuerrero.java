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
		
		//Creamos dos guerreros 
		logger.debug("Empezando a crear los guerreros");
        EntityManager em = JpaUtil.getEntityManager();
        Guerrero guerrero1 = new Guerrero("Borja","Humano",10,6,1000);
        Guerrero guerrero2 = new Guerrero("Carlos","Orco",8,10,1000);

        //Añadimos los guerreros a la tabla 
        em.getTransaction().begin();
        em.persist(guerrero1);
        em.persist(guerrero2);
        em.getTransaction().commit();
        
        //Mostramos que los guerreros se hayan creado bien 
        List<Guerrero> guerreros = em.createQuery("from Guerrero", Guerrero.class).getResultList();
        System.out.println("\n");
        guerreros.forEach(System.out::println); //Método Referencia
        em.close();
        logger.debug("Guerreros creados con exito");

	}

}
