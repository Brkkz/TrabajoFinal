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
		 	
		 	//Buscamos si los dos guerreros existen
	        EntityManager em = JpaUtil.getEntityManager();
	        Guerrero guerrero1 = em.find(Guerrero.class, id1);
	        Guerrero guerrero2 = em.find(Guerrero.class, id2);
	        
	        //Si existen los guerreros los muestra, sino sale un error warning 
	        if(guerrero1 != null && guerrero2 != null) {
	        	System.out.println("");
	        	logger.debug(guerrero1);
	        	logger.debug(guerrero2);
	        }else {
	        	logger.warn("El Alumno con id "+id1+" no existe");
	        	logger.warn("El Alumno con id "+id2+" no existe");
	        }
	        
	        
	        //Bucle do que sirve para seguir con la batalla hasta que algun guerrrero se quede sin puntos de vida       
	        do {
	        	//ContadorTurnos sirve para saber los turnos totales de la batalla,
	        	//Turno sirve para saber a quien le toca atacar y a quien defender
	        	logger.debug("Empieza el turno: "+contadorTurnos);
	        	turno++;
	        	
	        	//Si el turno es 1 ataca el primer guerrero
	        	if (turno == 1) {
	        		logger.debug("Ataca el guerrero "+guerrero1.getNombre());
	        		//Ataca el guerrero1
	        		ataque = guerrero1.Atacar(guerrero1.getPuntosAtaque());
	        		//Se defiende el guerrero2 del ataque
	        		guerrero2.Defender(ataque ,guerrero2.getPuntosDefensa() ,guerrero2.getPuntosVida());
	        		turno++;
	        		
	        	//Si el turno es 2 ataca el segundo guerrero
	        	}else if (turno == 3) {
	        		logger.debug("Ataca el guerrero "+guerrero2.getNombre());
	        		//Ataca el guerrero2
	        		ataque = guerrero2.Atacar(guerrero2.getPuntosAtaque());
	        		//Se defiende el guerrero1 del ataque
	        		guerrero1.Defender(ataque ,guerrero1.getPuntosDefensa() ,guerrero1.getPuntosVida());
	        		turno = 0;
	        	}
	        	//Sumamos los turnos totales al finalizar este 
	        	contadorTurnos++;
	        	
	        	//Mostramos la vida restante de los guerreros
	        	logger.debug("-Vida restante del guerrero "+guerrero1.getNombre()+"["+guerrero1.getPuntosVida()+"]");
	        	logger.debug("-Vida restante del guerrero "+guerrero2.getNombre()+"["+guerrero2.getPuntosVida()+"]\n");
	        }while(guerrero1.getPuntosVida() > 0 && guerrero2.getPuntosVida() > 0);
	        
	        //Se muestra el guerrero que no haya llegado a los 0 puntos de vida
	        if(guerrero1.getPuntosVida() > 0 ) {
	        	logger.debug("El guerrero"+guerrero1.getNombre()+" ha ganado el combate con un total de "+guerrero1.getPuntosVida()+" puntos de vida");
	        }else {
	        	logger.debug("El guerrero "+guerrero2.getNombre()+" ha ganado el combate con un total de "+guerrero2.getPuntosVida()+" puntos de vida");
	        }
	        
	        em.close();
	        System.out.println("\n");
	        logger.debug("Final de la batalla");
	}
}

