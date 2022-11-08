package curso.java.hibernate;

public class IGuerrero {

	public int Atacar(int ataque) {
		int ataquefinal=0;
		ataquefinal = ataque *(int)(Math.random()*10 + 1);
		return ataquefinal;
	}
	
	public void Defender(int ataque,int defensa ,int vida) {
		
	}
}
