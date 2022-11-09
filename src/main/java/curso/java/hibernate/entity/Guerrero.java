package curso.java.hibernate.entity;

import jakarta.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.hibernate.IGuerrero;
import curso.java.hibernate.IniciarBatalla;

@Entity
@Table(name="TB_GUERRERO")

public class Guerrero extends IGuerrero{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
    private String tipo;
    private int puntosAtaque;
    private int puntosDefensa;
    private int puntosVida;

    //Debe existir obligatoriamente
    public Guerrero() {
    }

    
    public Guerrero(Long id, String nombre, String tipo, int puntosAtaque, int puntosDefensa, int puntosVida) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.puntosAtaque = puntosAtaque;
		this.puntosDefensa = puntosDefensa;
		this.puntosVida = puntosVida;
	}

    

	public Guerrero(String nombre, String tipo, int puntosAtaque, int puntosDefensa, int puntosVida) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.puntosAtaque = puntosAtaque;
		this.puntosDefensa = puntosDefensa;
		this.puntosVida = puntosVida;
	}


	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getPuntosAtaque() {
		return puntosAtaque;
	}


	public void setPuntosAtaque(int puntosAtaque) {
		this.puntosAtaque = puntosAtaque;
	}


	public int getPuntosDefensa() {
		return puntosDefensa;
	}


	public void setPuntosDefensa(int puntosDefensa) {
		this.puntosDefensa = puntosDefensa;
	}


	public int getPuntosVida() {
		return puntosVida;
	}


	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}


	@Override
	public String toString() {
		return "Guerrero= " + id +
                ", Nombre= " + nombre +
                ", Tipo= " + tipo +
                ", Puntos Ataque= " + puntosAtaque +
                ", Puntos Defensa= " + puntosDefensa +
                ", Puntos Vida= " + puntosVida;   
	}

	/*
	 * Se hace herencia de la interfaz y hacemos el metodo para la defensa del ataque
	 */
	@Override
	public void Defender(int ataque, int defensa,int vida) {
		super.Defender(ataque, defensa,vida);
		
		defensa*=(int)(Math.random()*5 + 1);
		int resultado = ataque - defensa;
		//Si el ataque es mayor que la defensa se quita vida, sino se para el ataque
		if(resultado > 0) {
			vida -= resultado;
			this.setPuntosVida(vida);
		}else {
			System.out.println("Se ha parado el ataque");
		}
		
		
	}
	
	
}

