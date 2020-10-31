
public class Peticiones {
	private String objeto;
	private String numJugador;
	
	public Peticiones(String objeto, String numJugador) {
		this.objeto = objeto;
		this.numJugador = numJugador;
	}

	
	
	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	public String getNumJugador() {
		return numJugador;
	}

	public void setNumJugador(String numJugador) {
		this.numJugador = numJugador;
	}
	
}
