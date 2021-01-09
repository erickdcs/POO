
public class Peticiones {
	private String objeto;
	private int numJugador;
	
	public Peticiones(String objeto, int numJugador) {
		this.objeto = objeto;
		this.numJugador = numJugador;
	}

	
	
	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	public int getNumJugador() {
		return numJugador;
	}

	public void setNumJugador(int numJugador) {
		this.numJugador = numJugador;
	}
	
}
