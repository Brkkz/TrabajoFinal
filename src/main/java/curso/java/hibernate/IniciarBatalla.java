package curso.java.hibernate;

import java.util.Scanner;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import curso.java.hibernate.entity.Guerrero;
import curso.java.hibernate.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class IniciarBatalla {
	
	private static final Logger logger = LogManager.getLogger(IniciarBatalla.class);
	public static void main(String[] args) {
		
		logger.debug("Se esta iniciando la batalla\n");
		 Scanner s = new Scanner(System.in);

		 	Long id1 = (long) 1;
		 	Long id2 = (long) 2;
		 	int contadorTurnos=1;
		 	int turno = 0;
		 	int ataque = 0;
		 	
	        EntityManager em = JpaUtil.getEntityManager();
	        Guerrero guerrero1 = em.find(Guerrero.class, id1);
	        Guerrero guerrero2 = em.find(Guerrero.class, id2);
	        
	        if(guerrero1 != null) {
	        	System.out.println("\n"+guerrero1+"\n"+guerrero2);
	        }else {
	        	logger.warn("El Alumno con id "+id1+" no existe");
	        	logger.warn("El Alumno con id "+id2+" no existe");
	        }
	        
	        
	        
	        do {
	        	System.out.print("Empieza el turno: "+contadorTurnos);
	        	turno++;
	        	//Si el turno es 1 ataca el primer guerrero
	        	if (turno == 1) {
	        		System.out.println(" atacando el guerrero "+guerrero1.getNombre());
	        		ataque = guerrero1.Atacar(guerrero1.getPuntosAtaque());
	        		guerrero2.Defender(ataque ,guerrero2.getPuntosDefensa() ,guerrero2.getPuntosVida());
	        		turno++;
	        	//Si el turno es 2 ataca el segundo guerrero
	        	}else if (turno == 3) {
	        		System.out.println(" atacando el guerrero "+guerrero2.getNombre());
	        		ataque = guerrero2.Atacar(guerrero2.getPuntosAtaque());
	        		guerrero1.Defender(ataque ,guerrero1.getPuntosDefensa() ,guerrero1.getPuntosVida());
	        		turno = 0;
	        	}
	        	contadorTurnos++;
	        	System.out.println("-Vida restante del guerrero "+guerrero1.getNombre()+"["+guerrero1.getPuntosVida()+"]");
	        	System.out.println("-Vida restante del guerrero "+guerrero2.getNombre()+"["+guerrero2.getPuntosVida()+"]\n");
	        }while(guerrero1.getPuntosVida() > 0 && guerrero2.getPuntosVida() > 0);
	        if(guerrero1.getPuntosVida() > 0 ) {
	        	System.out.println("\nEl guerrero"+guerrero1.getNombre()+" ha ganado el combate con un total de "+guerrero1.getPuntosVida()+" puntos de vida");
	        }else {
	        	System.out.println("\nEl guerrero "+guerrero2.getNombre()+" ha ganado el combate con un total de "+guerrero2.getPuntosVida()+" puntos de vida");
	        }
	        em.close();
	        System.out.println("\n");
	        logger.debug("Final busqueda");
	}
}

